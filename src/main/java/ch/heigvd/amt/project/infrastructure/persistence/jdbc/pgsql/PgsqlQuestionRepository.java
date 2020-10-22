package ch.heigvd.amt.project.infrastructure.persistence.jdbc.pgsql;

import ch.heigvd.amt.project.application.questionmgmt.QuestionsQuery;
import ch.heigvd.amt.project.domain.question.IQuestionRepository;
import ch.heigvd.amt.project.domain.question.Question;
import ch.heigvd.amt.project.domain.question.QuestionId;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.user.User;
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
@Named("PgsqlUserRepository")
public class PgsqlQuestionRepository extends PgsqlRepository<Question, QuestionId> implements IQuestionRepository {

    @Resource(lookup = "jdbc/stackoverflowsimple")
    private DataSource dataSource;

    public static final String TABLE_ATTRIBUT_CLE = "pk_question";
    public static final String TABLE_ATTRIBUT_OWNER = "fk_ownerId";
    public static final String TABLE_ATTRIBUT_TITLE = "title";
    public static final String TABLE_ATTRIBUT_CREATION_DATE = "creationDate";
    public static final String TABLE_ATTRIBUT_LAST_EDIT_DATE = "lastEditDate";
    public static final String TABLE_ATTRIBUT_BODY = "body";




    public static final String SQL_INSERT = "INSERT INTO postgres.stackoverflowsimple.questions "
            + "("+TABLE_ATTRIBUT_CLE+"," +
            " "+TABLE_ATTRIBUT_OWNER+", "
            +TABLE_ATTRIBUT_TITLE+", "+
            TABLE_ATTRIBUT_CREATION_DATE+", "
            +TABLE_ATTRIBUT_LAST_EDIT_DATE+", "
            +TABLE_ATTRIBUT_BODY+")"
            + " VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SQL_UPDATE_BY_ID = "UPDATE postgres.stackoverflowsimple.questions "
            + "SET "+TABLE_ATTRIBUT_TITLE+" = ?,"
            + TABLE_ATTRIBUT_LAST_EDIT_DATE+" = ?, "
            + TABLE_ATTRIBUT_BODY +" = ? "
            + "WHERE " + TABLE_ATTRIBUT_CLE +" = ? ";

    public static final String SQL_DELETE_BY_ID = "DELETE FROM postgres.stackoverflowsimple.questions "
            + "WHERE "+TABLE_ATTRIBUT_CLE+" = ?";

    public static final String SQL_SELECT_ALL = "SELECT "
            +TABLE_ATTRIBUT_CLE+"," +
            " "+TABLE_ATTRIBUT_OWNER+", "
            +TABLE_ATTRIBUT_TITLE+", "
            +TABLE_ATTRIBUT_CREATION_DATE+", "
            +TABLE_ATTRIBUT_LAST_EDIT_DATE+", "
            +TABLE_ATTRIBUT_BODY+ " "
            + "FROM postgres.stackoverflowsimple.questions ";

    public static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL
            + " WHERE "+TABLE_ATTRIBUT_CLE+" = ? ";


    @Override
    public Collection<Question> find(QuestionsQuery query) {
        return findAll();
    }

    @Override
    public void save(Question entity) {
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT);

            ps.setObject(1, entity.getId().getId());
            ps.setObject(2, entity.getOwnerId().getId());
            ps.setString(3, entity.getTitle());
            ps.setDate(4, new java.sql.Date(entity.getCreationDate().getTime()));
            ps.setDate(5, new java.sql.Date(entity.getLastEditDate().getTime()));
            ps.setString(6, entity.getBody());
            ps.executeUpdate();
            ps.close();
            con.close();


        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }

    }

    @Override
    public void remove(QuestionId id) {

    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        return Optional.empty();
    }

    @Override
    public Collection<Question> findAll() {
        return null;
    }
}
