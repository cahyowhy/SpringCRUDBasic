package cahyo.batch5.dao.impl;

import cahyo.batch5.dao.MatakuliahKelasDao;
import cahyo.batch5.entity.Dosen;
import cahyo.batch5.entity.Matakuliah;
import cahyo.batch5.entity.MatakuliahKelas;
import cahyo.batch5.util.Capitalize;
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
public class MatakuliahKelasDaoImpl implements MatakuliahKelasDao {

    private final String sqlGetDefault = "SELECT " + Table.MATAKULIAH_KELAS + ".*, " +
            Table.MATAKULIAH + ".id as mk_id, " +
            Table.MATAKULIAH + ".name as mk_nm, " +
            Table.MATAKULIAH + ".sks_count as mk_sks, " +
            Table.DOSEN + ".id as ds_id, " +
            Table.DOSEN + ".name as ds_nm " +
            "FROM " + Table.MATAKULIAH_KELAS + " " +
            "INNER JOIN " + Table.MATAKULIAH + " ON " +
            Table.MATAKULIAH_KELAS + ".matakuliah_id = " +
            Table.MATAKULIAH + ".id " +
            "INNER JOIN " + Table.DOSEN + " " +
            "ON " + Table.MATAKULIAH_KELAS + ".dosen_id = " +
            Table.DOSEN + ".id";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public MatakuliahKelas save(MatakuliahKelas entity) {
        String sql = "INSERT INTO " + Table.MATAKULIAH_KELAS + "("
                + "name, " + "created_at, " + "matakuliah_id, "
                + "room, " + "dosen_id)"
                + " VALUES(?,?,?,?,?) ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setDate(2, DateConversion.convertToSqlDate(new Date()));
            ps.setInt(3, entity.getMatakuliah().getId());
            ps.setString(4, entity.getRoom());
            ps.setInt(5, entity.getDosen().getId());

            return ps;
        }, keyHolder);

        entity.setId(keyHolder.getKey().intValue());
        return entity;
    }

    @Override
    public MatakuliahKelas update(MatakuliahKelas entity) {
        String sql = "UPDATE " + Table.MATAKULIAH_KELAS + " SET " +
                "name = ?, " +
                "matakuliah_id = ?, " +
                "dosen_id = ?, " +
                "room = ?" +
                "WHERE id = ? ";
        jdbcTemplate.update(sql,
                entity.getName(),
                entity.getMatakuliah().getId(),
                entity.getDosen().getId(),
                entity.getRoom(),
                entity.getId());

        return entity;
    }

    @Override
    public String delete(int id) {
        return null;
    }

    @Override
    public List<MatakuliahKelas> findAll(int offset, int limit) {
        String sql = sqlGetDefault + " WHERE 1=1 LIMIT ?,? ";
        List<Object> params = new ArrayList<>();
        params.add(offset);
        params.add(limit);

        return jdbcTemplate.query(sql, params.toArray(), new MatakuliahKelasRowMapper());
    }

    @Override
    public MatakuliahKelas findbyId(int id) {
        String sql = sqlGetDefault + " WHERE " +
                Table.MATAKULIAH_KELAS + ".id = ? ";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new MatakuliahKelasRowMapper());
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<MatakuliahKelas> findBy(MatakuliahKelas param, int offset, int limit) {
        String sql = sqlGetDefault + " ";
        List<Object> params = new ArrayList<>();
        if (param.getName() != null) {
            sql += "AND " + Table.MATAKULIAH_KELAS + ".name LIKE ? ";
            params.add("%" + param.getName() + "%");

            sql += "AND " + Table.MATAKULIAH_KELAS + ".name LIKE ? ";
            params.add("%" + Capitalize.capitalize(param.getName()) + "%");
        }

        if (param.getRoom() != null) {
            sql += "AND " + Table.MATAKULIAH_KELAS + ".room LIKE ? ";
            params.add("%" + param.getRoom() + "%");
        }

        if (param.getMatakuliah().getId() != 0) {
            sql += "AND " + Table.MATAKULIAH_KELAS + ".matakuliah_id = ?";
            params.add(param.getMatakuliah().getId());
        }

        if (param.getDosen().getId() != 0) {
            sql += "AND " + Table.MATAKULIAH_KELAS + ".dosen_id = ? ";
            params.add(param.getDosen().getId());
        }

        sql += "LIMIT ?,?";

        params.add(offset);
        params.add(limit);
        return jdbcTemplate.query(sql, params.toArray(), new MatakuliahKelasRowMapper());
    }

    private class MatakuliahKelasRowMapper implements RowMapper<MatakuliahKelas> {

        @Override
        public MatakuliahKelas mapRow(ResultSet resultSet, int i) throws SQLException {
            MatakuliahKelas matakuliahKelas = new MatakuliahKelas();
            matakuliahKelas.setId(resultSet.getInt("id"));
            matakuliahKelas.setRoom(resultSet.getString("room"));
            matakuliahKelas.setName(resultSet.getString("name"));
            matakuliahKelas.setCreatedAt(resultSet.getDate("created_at"));

            Dosen dosen = new Dosen();
            dosen.setId(resultSet.getInt("ds_id"));
            dosen.setName(resultSet.getString("ds_nm"));

            Matakuliah matakuliah = new Matakuliah();
            matakuliah.setId(resultSet.getInt("mk_id"));
            matakuliah.setName(resultSet.getString("mk_nm"));
            matakuliah.setSks(resultSet.getString("mk_sks"));

            matakuliahKelas.setDosen(dosen);
            matakuliahKelas.setMatakuliah(matakuliah);
            return matakuliahKelas;
        }
    }
}
