package cahyo.batch5.dao.impl;

import cahyo.batch5.dao.DosenDao;
import cahyo.batch5.entity.Dosen;
import cahyo.batch5.util.DateConversion;
import cahyo.batch5.util.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DosenDaoImpl implements DosenDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Dosen save(Dosen entity) {
        String sql = "INSERT INTO " + Table.DOSEN + "("
                + "name, " + "created_at, " + "birth_date)"
                + " VALUES(?,?,?) ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setDate(2, DateConversion.convertToSqlDate(new Date()));
            ps.setDate(3, DateConversion.convertToSqlDate(entity.getBirthDate()));
            return ps;
        }, keyHolder);

        entity.setId(keyHolder.getKey().intValue());
        return entity;
    }

    @Override
    public Dosen update(Dosen entity) {
        String sql = "UPDATE " + Table.DOSEN + " SET " +
                "name = ?, " +
                "birth_date = ?" +
                "WHERE id = ? ";
        jdbcTemplate.update(sql,
                entity.getName(),
                DateConversion.convertToSqlDate(entity.getBirthDate()),
                entity.getId());

        return entity;
    }

    @Override
    public String delete(int id) {
        String sql = "DELETE FROM " + Table.DOSEN + " WHERE id = ? ";
        jdbcTemplate.update(sql, id);

        return "Delete success";
    }

    @Override
    public List<Dosen> findAll(int offset, int limit) {
        String sql = "SELECT " + "*" + " FROM "
                + Table.DOSEN+ " WHERE 1=1 LIMIT ?,? ";
        List<Object> params = new ArrayList<>();
        params.add(offset);
        params.add(limit);

        return jdbcTemplate.query(sql, params.toArray(), new DosenRowMapper());
    }

    @Override
    public Dosen findbyId(int id) {
        String sql = "SELECT " + "*" + " FROM "
                + Table.DOSEN+ " WHERE id = ? ";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new DosenRowMapper());
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private class DosenRowMapper implements RowMapper<Dosen> {

        @Override
        public Dosen mapRow(ResultSet resultSet, int i) throws SQLException {
            Dosen dosen = new Dosen();
            dosen.setId(resultSet.getInt("id"));
            dosen.setName(resultSet.getString("name"));
            dosen.setBirthDate(resultSet.getDate("birth_date"));
            dosen.setCreatedAt(resultSet.getDate("created_at"));

            return dosen;
        }
    }
}
