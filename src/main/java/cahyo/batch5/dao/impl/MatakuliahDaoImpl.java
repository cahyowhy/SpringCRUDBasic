package cahyo.batch5.dao.impl;

import cahyo.batch5.dao.MatakuliahDao;
import cahyo.batch5.entity.Matakuliah;
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
public class MatakuliahDaoImpl implements MatakuliahDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Matakuliah save(Matakuliah entity) {
        String sql = "INSERT INTO " + Table.MATAKULIAH + "("
                + "name, " + "created_at, " + "sks_count)"
                + " VALUES(?,?,?) ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setDate(2, DateConversion.convertToSqlDate(new Date()));
            ps.setString(3, entity.getSks());
            return ps;
        }, keyHolder);

        entity.setId(keyHolder.getKey().intValue());
        return entity;
    }

    @Override
    public Matakuliah update(Matakuliah entity) {
        String sql = "UPDATE " + Table.MATAKULIAH + " SET " +
                "name = ?, " +
                "sks_count = ?" +
                "WHERE id = ? ";
        jdbcTemplate.update(sql,
                entity.getName(),
                entity.getSks(),
                entity.getId());

        return entity;
    }

    @Override
    public String delete(int id) {
        String sql = "DELETE FROM " + Table.MATAKULIAH + " WHERE id = ? ";
        jdbcTemplate.update(sql, id);

        return "Delete success";
    }

    @Override
    public List<Matakuliah> findAll(int offset, int limit) {
        String sql = "SELECT " + "*" + " FROM "
                + Table.MATAKULIAH + " WHERE 1=1 LIMIT ?,? ";
        List<Object> params = new ArrayList<>();
        params.add(offset);
        params.add(limit);

        return jdbcTemplate.query(sql, params.toArray(), new MatakuliahRowMapper());
    }

    @Override
    public Matakuliah findbyId(int id) {
        String sql = "SELECT " + "*" + " FROM "
                + Table.MATAKULIAH + " WHERE id = ? ";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new MatakuliahRowMapper());
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private class MatakuliahRowMapper implements RowMapper<Matakuliah> {

        @Override
        public Matakuliah mapRow(ResultSet resultSet, int i) throws SQLException {
            Matakuliah matakuliah = new Matakuliah();
            matakuliah.setId(resultSet.getInt("id"));
            matakuliah.setName(resultSet.getString("name"));
            matakuliah.setSks(resultSet.getString("sks_count"));
            matakuliah.setCreatedAt(resultSet.getDate("created_at"));

            return matakuliah;
        }
    }
}
