package ch.heigvd.amt.project.infrastructure.persistence.jdbc.pgsql;

import ch.heigvd.amt.project.domain.tag.ITagRepository;
import ch.heigvd.amt.project.domain.tag.Tag;
import ch.heigvd.amt.project.domain.tag.TagId;
import ch.heigvd.amt.project.domain.vote.IVoteRepository;
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
@Named("PgsqlTagRepository")
public class PgsqlTagRepository extends PgsqlRepository<Tag, TagId> implements ITagRepository {

    @Resource(lookup = "jdbc/stackoverflowsimple")
    private DataSource dataSource;

    public static final String TABLE_ATTRIBUT_CLE = "pk_tag";
    public static final String TABLE_ATTRIBUT_NAME = "name";


    public static final String SQL_INSERT = "INSERT INTO postgres.stackoverflowsimple.tags "
            + "("+TABLE_ATTRIBUT_CLE+", "
            + TABLE_ATTRIBUT_NAME +")"
            + " VALUES (?, ?)";

    public static final String SQL_UPDATE_BY_ID = "UPDATE postgres.stackoverflowsimple.tags "
            + "SET "+ TABLE_ATTRIBUT_NAME +" = ?,"
            + TABLE_ATTRIBUT_NAME +" = ? "
            + "WHERE " + TABLE_ATTRIBUT_CLE +" = ? ";

    public static final String SQL_DELETE_BY_ID = "DELETE FROM postgres.stackoverflowsimple.tags "
            + "WHERE "+TABLE_ATTRIBUT_CLE+" = ?";

    public static final String SQL_SELECT_ALL = "SELECT "+TABLE_ATTRIBUT_CLE+", "+ TABLE_ATTRIBUT_NAME +" "
            + "FROM postgres.stackoverflowsimple.tags ";

    public static final String SQL_SELECT_BY_ID = SQL_SELECT_ALL
            + " WHERE "+TABLE_ATTRIBUT_CLE+" = ? ";

    public static final String SQL_SELECT_BY_NAME = SQL_SELECT_ALL
            + " WHERE "+ TABLE_ATTRIBUT_NAME +" = ? ";


    protected Optional<Tag> createEntite(ResultSet result) throws DataCorruptionException {
        Optional<Tag> tag = Optional.empty();
        try {
            tag = Optional.ofNullable(Tag.builder().id(new TagId(result.getString(TABLE_ATTRIBUT_CLE)))
                    .name(result.getString(TABLE_ATTRIBUT_NAME))
                    .build());

        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }
        return tag;
    }



    @Override
    public void save(Tag entity) {
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_INSERT);

            ps.setObject(1, entity.getId().getId());
            ps.setString(2, entity.getName());
            ps.close();
            con.close();
        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }
    }

    @Override
    public void remove(TagId id) {
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
    public Optional<Tag> findById(TagId id) {
        Optional<Tag> tag = Optional.empty();
        try {
            if (id != null) {
                Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_ID);
                ps.setObject(1, id.getId());

                try (ResultSet result = ps.executeQuery()) {
                    while (result.next()) {
                        tag = createEntite(result);
                    }

                }
                ps.close();
                con.close();
            }
        } catch (Exception e) {
            throw new DataCorruptionException(e.toString());
        }

        return tag;
    }

    @Override
    public Collection<Tag> findAll() {

        Collection<Tag> list = new ArrayList<Tag>();
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_ALL);

            try (ResultSet result = ps.executeQuery()) {
                while (result.next()) {
                    Optional<Tag> entite = this.createEntite(result);
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
