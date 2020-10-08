package ch.heigvd.amt.project.infrastructure.persistence.jdbc.pgsql;

import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.user.User;
import ch.heigvd.amt.project.domain.user.UserId;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class PgsqlUserRepository extends PgsqlRepository<User, UserId> implements IUserRepository {

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
    public Optional<User> findByUsername(String username) {

        return Optional.empty();
    }

    @Override
    public void save(User entity) {

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
    public void remove(UserId id) {

    }

    @Override
    public Optional<User> findById(UserId id) {
        return Optional.empty();
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }
}
