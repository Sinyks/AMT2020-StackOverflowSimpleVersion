package ch.heigvd.amt.project.infrastructure.persistence.jdbc.pgsql;

import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.user.User;
import ch.heigvd.amt.project.domain.user.UserId;
import ch.heigvd.amt.project.infrastructure.persistence.DataCorruptionException;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@ApplicationScoped
@Named("PgsqlUserRepository")
public class PgsqlUserRepository extends PgsqlRepository<User, UserId> implements IUserRepository {

    @Resource(lookup = "jdbc/stackoverflowsimple")
    private DataSource dataSource;

    public static final String TABLE_ATTRIBUT_CLE = "pk_user";
    public static final String TABLE_ATTRIBUT_USERNAME = "username";
    public static final String TABLE_ATTRIBUT_EMAIL = "email";
    public static final String TABLE_ATTRIBUT_ABOUT_ME = "aboutMe";
    public static final String TABLE_ATTRIBUT_PASSWORD = "password";

    public static final String SQL_INSERT = "INSERT INTO postgres.stackoverflowsimple.users "
            + "("+TABLE_ATTRIBUT_CLE+", "
            +TABLE_ATTRIBUT_USERNAME+", "
            +TABLE_ATTRIBUT_EMAIL+", "
            +TABLE_ATTRIBUT_ABOUT_ME+", "
            +TABLE_ATTRIBUT_PASSWORD+")"
            + " VALUES (?, ?, ?, ?, ?)";

    public static final String SQL_UPDATE_BY_ID = "UPDATE postgres.stackoverflowsimple.users "
            + "SET "+TABLE_ATTRIBUT_USERNAME+" = ?,"
            + TABLE_ATTRIBUT_USERNAME+" = ?, "
            + TABLE_ATTRIBUT_ABOUT_ME +" = ?, "
            + TABLE_ATTRIBUT_PASSWORD +" = ? "
            + "WHERE " + TABLE_ATTRIBUT_CLE +" = ? ";

    public static final String SQL_DELETE_BY_ID = "DELETE FROM postgres.stackoverflowsimple.users "
            + "WHERE "+TABLE_ATTRIBUT_CLE+" = ?";

    public static final String SQL_SELECT_ALL = "SELECT "+TABLE_ATTRIBUT_CLE+", "+TABLE_ATTRIBUT_USERNAME+", "+TABLE_ATTRIBUT_EMAIL+", "
            + TABLE_ATTRIBUT_ABOUT_ME+", "+TABLE_ATTRIBUT_PASSWORD+" "
            + "FROM postgres.stackoverflowsimple.users ";

    public static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL
            + " WHERE "+TABLE_ATTRIBUT_CLE+" = ? ";

    public static final String SQL_SELECT_BY_NAME = SQL_SELECT_ALL
            + " WHERE "+TABLE_ATTRIBUT_USERNAME+" = ? ";




    @Override
    public Optional<User> findByUsername(String username) {
        Optional<User> usr = Optional.empty();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_NAME);

            try {
                ps.setString(1, username);
            } catch (NumberFormatException ex) {

            }

            try (ResultSet result = ps.executeQuery()) {
                if (result.isBeforeFirst() ) {
                    usr = this.createEntite(result);
                }

            }
        } catch (SQLException e) {
            throw new DataCorruptionException(e.toString());
        }

        return usr;
    }

    protected Optional<User> createEntite(ResultSet result) throws DataCorruptionException {
        Optional<User> user = Optional.empty();
        try {
            user = Optional.ofNullable(User.builder().id(new UserId(result.getString(TABLE_ATTRIBUT_CLE)))
                    .username(result.getString(TABLE_ATTRIBUT_USERNAME))
                    .email(result.getString(TABLE_ATTRIBUT_EMAIL))
                    .aboutMe(result.getString(TABLE_ATTRIBUT_ABOUT_ME))
                    .hashedPassword(result.getString(TABLE_ATTRIBUT_PASSWORD))
                    .build());

        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }
        return user;
    }



    @Override
    public void save(User entity) {
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT);

            ps.setObject(1, entity.getId().getId());
            ps.setString(2, entity.getUsername());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getAboutMe());
            ps.setString(5, entity.getHashedPassword());
            ps.executeUpdate();
            ps.close();
            con.close();


        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }
    }

    @Override
    public void remove(UserId id) {
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
    public Optional<User> findById(UserId id) {
        Optional<User> usr = Optional.empty();
        try {
            if (id != null) {
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID);
                ps.setObject(1, id.getId());

                try (ResultSet result = ps.executeQuery()) {
                    if (result.isBeforeFirst() ) {
                        usr = this.createEntite(result);
                    }

                }
                ps.close();
                con.close();
            }
        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }

        return usr;
    }

    @Override
    public Collection<User> findAll() {

        Collection<User> list = new ArrayList<User>();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);

            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    Optional<User> entite = this.createEntite(result);
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
