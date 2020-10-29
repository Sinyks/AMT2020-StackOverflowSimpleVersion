package ch.heigvd.amt.project.infrastructure.persistence.jdbc.pgsql;

import ch.heigvd.amt.project.domain.answer.Answer;
import ch.heigvd.amt.project.domain.answer.AnswerId;
import ch.heigvd.amt.project.domain.answer.IAnswerRepository;
import ch.heigvd.amt.project.domain.comment.Comment;
import ch.heigvd.amt.project.domain.comment.CommentId;
import ch.heigvd.amt.project.domain.comment.ICommentRepository;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.UserId;
import ch.heigvd.amt.project.domain.vote.IVoteRepository;
import ch.heigvd.amt.project.domain.vote.Vote;
import ch.heigvd.amt.project.domain.vote.VoteId;
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
@Named("PgsqlVoteRepository")
public class PgsqlVoteRepository extends PgsqlRepository<Vote, VoteId> implements IVoteRepository {


    @Resource(lookup = "jdbc/stackoverflowsimple")
    private DataSource dataSource;
    public static final String TABLE_ATTRIBUT_CLE = "pk_vote";
    public static final String TABLE_ATTRIBUT_OWNER = "fk_ownerId";
    public static final String TABLE_ATTRIBUT_QUESTION = "fk_questionId";
    public static final String TABLE_ATTRIBUT_ANSWER = "fk_answerId";
    public static final String TABLE_ATTRIBUT_UP_VOTE = "isUpVote";




    public static final String SQL_INSERT = "INSERT INTO postgres.stackoverflowsimple.votes "
            + "("+TABLE_ATTRIBUT_CLE+","
            + TABLE_ATTRIBUT_OWNER+", "
            + TABLE_ATTRIBUT_QUESTION + ", "
            + TABLE_ATTRIBUT_ANSWER + ", "
            + TABLE_ATTRIBUT_UP_VOTE+")"
            + " VALUES (?, ?, ?, ?, ?)";

   public static final String SQL_UPDATE_BY_ID = "UPDATE postgres.stackoverflowsimple.votes "
            + "SET "+TABLE_ATTRIBUT_UP_VOTE+" = ? "
            + "WHERE " + TABLE_ATTRIBUT_CLE +" = ? ";

   public static final String SQL_DELETE_BY_ID = "DELETE FROM postgres.stackoverflowsimple.votes "
            + "WHERE "+TABLE_ATTRIBUT_CLE+" = ?";

   public static final String SQL_SELECT_ALL = "SELECT "
           + TABLE_ATTRIBUT_CLE+","
           + TABLE_ATTRIBUT_OWNER+", "
           + TABLE_ATTRIBUT_QUESTION + ", "
           + TABLE_ATTRIBUT_ANSWER + ", "
           + TABLE_ATTRIBUT_UP_VOTE+ " "
            + "FROM postgres.stackoverflowsimple.votes  ";

   public static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL
            + " WHERE "+TABLE_ATTRIBUT_CLE+" = ? ";

    public static final String SQL_SELECT_BY_QUESTIONID = SQL_SELECT_ALL
            + " WHERE "+TABLE_ATTRIBUT_QUESTION + " = ?" ;

    public static final String SQL_SELECT_BY_ANSWERID = SQL_SELECT_ALL
            + " WHERE "+TABLE_ATTRIBUT_ANSWER + " = ?" ;


    @Override
    public void save(Vote entity) {
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT);

            ps.setObject(1, entity.getId().getId());
            ps.setObject(2, entity.getOwnerId().getId());
            ps.setObject(3, entity.getQuestionId().getId());
            ps.setObject(4,entity.getAnswerId().getId());
            ps.setBoolean(5, entity.isUpVote());
            ps.executeUpdate();
            ps.close();
            con.close();


        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }
    }

    @Override
    public void remove(VoteId id) {
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
    public Optional<Vote> findById(VoteId id) {
        Optional<Vote>  vote = Optional.empty();
        try {
            if (id != null) {
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID);
                ps.setObject(1, id.getId());

                try (ResultSet result = ps.executeQuery()) {
                    while (result.next()) {
                        vote = createEntite(result);
                    }

                }
                ps.close();
                con.close();
            }
        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }

        return vote;
    }

    protected Optional<Vote> createEntite(ResultSet result) throws DataCorruptionException {
        Optional<Vote> vote = Optional.empty();
        try {
            String answerIDString = result.getString(TABLE_ATTRIBUT_ANSWER);
            String questionIDString = result.getString(TABLE_ATTRIBUT_QUESTION);

            vote = Optional.ofNullable(Vote.builder().id(new VoteId(result.getString(TABLE_ATTRIBUT_CLE)))
                    .ownerId(new UserId(result.getString(TABLE_ATTRIBUT_OWNER)))
                    .questionId(questionIDString == null?null:new QuestionId(questionIDString))
                    .answerId(answerIDString == null?null:new AnswerId(answerIDString))
                    .isUpVote(result.getBoolean(TABLE_ATTRIBUT_UP_VOTE))
                    .build());

        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }
        return vote;
    }

    @Override
    public Collection<Vote> findAll() {
        Collection<Vote> list = new ArrayList<Vote>();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);

            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    Optional<Vote> entite = this.createEntite(result);
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

    @Override
    public Collection<Vote> findByQuestionID(QuestionId questionId) {

        Collection<Vote> list = new ArrayList<>();

        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_QUESTIONID);
            ps.setObject(1, questionId.getId());

            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    Optional<Vote> entite = this.createEntite(result);
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

    @Override
    public Collection<Vote> findByAnswerID(AnswerId answerId) {

        Collection<Vote> list = new ArrayList<>();

        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ANSWERID);
            ps.setObject(1, answerId.getId());

            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    Optional<Vote> entite = this.createEntite(result);
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
