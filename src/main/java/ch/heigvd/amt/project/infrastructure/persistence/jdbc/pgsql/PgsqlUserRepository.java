package ch.heigvd.amt.project.infrastructure.persistence.jdbc.pgsql;

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
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("PgsqlUserRepository")
public class PgsqlUserRepository extends PgsqlRepository<User, UserId> implements IUserRepository {

    @Resource(lookup = "jdbc/gza")
    private DataSource dataSource;

    public static final String SQL_INSERT = "INSERT INTO stackoverflowsimple.users "
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

            ps.setString(1, entity.getId().toString());
            ps.setString(2, entity.getUsername());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getAboutMe());
            ps.setString(5, entity.getEncryptedPassword());
            ps.executeUpdate();
            ps.close();
            con.close();


        } catch (Exception e) {
            System.out.println(e);
            throw new DataCorruptionException(e.toString());
        } //entity;
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
