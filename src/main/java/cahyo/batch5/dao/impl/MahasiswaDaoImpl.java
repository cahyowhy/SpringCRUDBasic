package cahyo.batch5.dao.impl;

import cahyo.batch5.dao.MahasiswaDao;
import cahyo.batch5.entity.Mahasiswa;
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
public class MahasiswaDaoImpl implements MahasiswaDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Mahasiswa save(Mahasiswa entity) {
        String sql = "INSERT INTO " + Table.MAHASISWA + "("
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
    public Mahasiswa update(Mahasiswa entity) {
        String sql = "UPDATE " + Table.MAHASISWA + " SET " +
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
        String sql = "DELETE FROM " + Table.MAHASISWA + " WHERE id = ? ";
        jdbcTemplate.update(sql, id);

        return "Delete success";
    }

    @Override
    public List<Mahasiswa> findAll(int offset, int limit) {
        String sql = "SELECT " + "*" + " FROM "
                + Table.MAHASISWA+ " WHERE 1=1 LIMIT ?,? ";
        List<Object> params = new ArrayList<>();
        params.add(offset);
        params.add(limit);

        return jdbcTemplate.query(sql, params.toArray(), new MahasiswaRowMapper());
    }

    @Override
    public Mahasiswa findbyId(int id) {
        String sql = "SELECT " + "*" + " FROM "
                + Table.MAHASISWA+ " WHERE id = ? ";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new MahasiswaRowMapper());
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private class MahasiswaRowMapper implements RowMapper<Mahasiswa> {

        @Override
        public Mahasiswa mapRow(ResultSet resultSet, int i) throws SQLException {
            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setId(resultSet.getInt("id"));
            mahasiswa.setName(resultSet.getString("name"));
            mahasiswa.setBirthDate(resultSet.getDate("birth_date"));
            mahasiswa.setCreatedAt(resultSet.getDate("created_at"));

            return mahasiswa;
        }
    }
}