package ch.heigvd.amt.project.infrastructure.persistence.jdbc.pgsql;

import ch.heigvd.amt.project.domain.answer.Answer;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.answer.IAnswerRepository;
import ch.heigvd.amt.project.domain.comment.Comment;
import ch.heigvd.amt.project.domain.comment.CommentId;
import ch.heigvd.amt.project.domain.comment.ICommentRepository;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import ch.heigvd.amt.project.infrastructure.persistence.DataCorruptionException;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("PgsqlCommentRepository")
public class PgsqlCommentRepository extends PgsqlRepository<Comment, CommentId> implements ICommentRepository {

    @Resource(lookup = "jdbc/stackoverflowsimple")
    private DataSource dataSource;

    public static final String TABLE_ATTRIBUT_CLE = "pk_comment";
    public static final String TABLE_ATTRIBUT_OWNER = "fk_ownerId";
    public static final String TABLE_ATTRIBUT_QUESTION = "fk_questionId";
    public static final String TABLE_ATTRIBUT_ANSWER = "fk_answerId";
    public static final String TABLE_ATTRIBUT_CREATION_DATE = "creationDate";
    public static final String TABLE_ATTRIBUT_LAST_EDIT_DATE = "lastEditDate";
    public static final String TABLE_ATTRIBUT_BODY = "body";




    public static final String SQL_INSERT = "INSERT INTO postgres.stackoverflowsimple.comments "
            + "("+TABLE_ATTRIBUT_CLE+","
            + TABLE_ATTRIBUT_OWNER+", "
            + TABLE_ATTRIBUT_QUESTION + ", "
            + TABLE_ATTRIBUT_ANSWER + ", "
            + TABLE_ATTRIBUT_CREATION_DATE+", "
            + TABLE_ATTRIBUT_LAST_EDIT_DATE+", "
            + TABLE_ATTRIBUT_BODY+")"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)";

   public static final String SQL_UPDATE_BY_ID = "UPDATE postgres.stackoverflowsimple.comments "
            + "SET "+TABLE_ATTRIBUT_LAST_EDIT_DATE+" = ?,"
            + TABLE_ATTRIBUT_BODY+" = ? "
            + "WHERE " + TABLE_ATTRIBUT_CLE +" = ? ";

   public static final String SQL_DELETE_BY_ID = "DELETE FROM postgres.stackoverflowsimple.comments "
            + "WHERE "+TABLE_ATTRIBUT_CLE+" = ?";

   public static final String SQL_SELECT_ALL = "SELECT "
           +TABLE_ATTRIBUT_CLE+","
           + TABLE_ATTRIBUT_OWNER+", "
           + TABLE_ATTRIBUT_QUESTION + ", "
           + TABLE_ATTRIBUT_ANSWER + ", "
           + TABLE_ATTRIBUT_CREATION_DATE+", "
           + TABLE_ATTRIBUT_LAST_EDIT_DATE+", "
           + TABLE_ATTRIBUT_BODY+ " "
            + "FROM postgres.stackoverflowsimple.comments  ";

   public static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL
            + " WHERE "+TABLE_ATTRIBUT_CLE+" = ? ";


    @Override
    public void save(Comment entity) {
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT);

            ps.setObject(1, entity.getId().getId());
            ps.setObject(2, entity.getOwnerId().getId());
            ps.setObject(3, entity.getQuestionId().getId());
            ps.setObject(4,entity.getAnswerId().getId());
            ps.setDate(5, new java.sql.Date(entity.getCreationDate().getTime()));
            ps.setDate(6, new java.sql.Date(entity.getLastEditDate().getTime()));
            ps.setString(7, entity.getBody());
            ps.executeUpdate();
            ps.close();
            con.close();


        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }
    }

    @Override
    public void remove(CommentId id) {
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_DELETE_BY_ID);
            ps.setObject(1, id.getId());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            throw new DataCorruptionException(e.toString());
        }
    }

    @Override
    public Optional<Comment> findById(CommentId id) {
        Optional<Comment>  comme = Optional.empty();
        try {
            if (id != null) {
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID);
                ps.setObject(1, id.getId());

                try (ResultSet result = ps.executeQuery()) {
                    while (result.next()) {
                        comme = createEntite(result);
                    }

                }
                ps.close();
                con.close();
            }
        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }

        return comme;
    }

    protected Optional<Comment> createEntite(ResultSet result) throws DataCorruptionException {
        Optional<Comment> Comme = Optional.empty();
        try {
            Comme = Optional.ofNullable(Comment.builder().id(new CommentId(result.getString(TABLE_ATTRIBUT_CLE)))
                    .ownerId(new UserId(result.getString(TABLE_ATTRIBUT_OWNER)))
                    .questionId(new QuestionId(result.getString(TABLE_ATTRIBUT_QUESTION)))
                    .answerId(new AnswerId(result.getString(TABLE_ATTRIBUT_ANSWER)))
                    .creationDate(result.getDate(TABLE_ATTRIBUT_CREATION_DATE))
                    .lastEditDate(result.getDate(TABLE_ATTRIBUT_LAST_EDIT_DATE))
                    .body(result.getString(TABLE_ATTRIBUT_BODY))
                    .build());

        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }
        return Comme;
    }

    @Override
    public Collection<Comment> findAll() {
        Collection<Comment> list = new ArrayList<Comment>();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);

            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    Optional<Comment> entite = this.createEntite(result);
                    list.add(entite.get());
                }
            }
            ps.close();
            con.close();
        } catch (SQLException e) {
            throw new DataCorruptionException(e.toString());
        }

        return list;
    }
}
