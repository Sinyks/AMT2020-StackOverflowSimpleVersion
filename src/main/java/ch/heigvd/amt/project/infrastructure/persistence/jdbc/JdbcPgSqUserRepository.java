package ch.heigvd.amt.project.infrastructure.persistence.jdbc;

import ch.heigvd.amt.project.application.core.domain.exceptions.PersistenceException;
import ch.heigvd.amt.project.application.core.domain.user.IUserRepository;
import ch.heigvd.amt.project.application.core.domain.user.User;
import ch.heigvd.amt.project.application.core.domain.user.UserId;
import ch.heigvd.amt.project.infrastructure.persistence.exceptions.DataCorruptionException;
import ch.heigvd.amt.project.infrastructure.persistence.exceptions.IntegrityConstraintViolationException;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JdbcPgSqUserRepository extends JdbcPgSqRepository<User, UserId> implements IUserRepository {

    @Resource(lookup = "jdbc/gza")
    private DataSource dataSource;

    public static final String SQL_INSERT = "INSERT INTO users "
            + "(pk_user,username, email, aboutMe, password)"
            + " VALUES (?, ?, ?, ?, ?)";

    public static final String SQL_UPDATE_BY_ID = "UPDATE users "
            + "SET username = ?,"
            + "email = ?, "
            + "aboutMe = ?, "
            + "password = ? "
            + "WHERE pk_user = ? ";

    public static final String SQL_DELETE_BY_ID = "DELETE FROM users "
            + "WHERE pk_user = ?";

    public static final String SQL_SELECT_ALL = "SELECT pk_user, username, email, "
            + "aboutMe, password, "
            + "FROM users ";

    public static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL
            + " WHERE pk_user = ? ";

    @Override
    public Optional<User> retrieveByUsername(String username) throws DataCorruptionException {
        return Optional.empty();
    }

    @Override
    public User create(User entity) throws PersistenceException {
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT);

            ps.executeUpdate();

            ps.close();
            con.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public Optional<User> retrieve(UserId id) throws PersistenceException {
        return Optional.empty();
    }

    @Override
    public Collection<User> retrieve() throws PersistenceException {
        return null;
    }

    @Override
    public Collection<User> retrieve(String string) throws PersistenceException {
        return null;
    }

    @Override
    public User update(User entity) throws PersistenceException {
        return null;
    }

    @Override
    public void delete(User entity) throws PersistenceException {

    }

    @Override
    public void delete(UserId id) throws PersistenceException {

    }
}
