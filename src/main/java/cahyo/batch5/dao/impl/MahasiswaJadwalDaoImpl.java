package cahyo.batch5.dao.impl;

import cahyo.batch5.dao.MahasiswaJadwalDao;
import cahyo.batch5.entity.*;
import cahyo.batch5.util.Table;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@Repository
public class MahasiswaJadwalDaoImpl implements MahasiswaJadwalDao {

    private String sqlGetDefault = "SELECT " + Table.MAHASISWA_JADWAL + ".*, " + Table.MAHASISWA +
            ".id as mhs_id, " + Table.MAHASISWA + ".name as mhs_nm, " +
            Table.MATAKULIAH + ".id as mk_id, " + Table.MATAKULIAH + ".name as mk_nm, " +
            Table.MATAKULIAH_KELAS + ".id as mkul_id, " + Table.MATAKULIAH_KELAS + ".name as mkul_nm, " +
            Table.MATAKULIAH_KELAS + ".room as mkul_room, " + Table.MATAKULIAH_KELAS +
            ".created_at as mkul_created, " + Table.MATAKULIAH + ".sks_count as mk_sks, " +
            Table.DOSEN + ".id as ds_id, " + Table.DOSEN + ".name as ds_nm FROM " + Table.MAHASISWA_JADWAL + " " +
            "INNER JOIN " + Table.MATAKULIAH_KELAS + " ON " + Table.MAHASISWA_JADWAL +
            ".kelas_matakuliah_id = " + Table.MATAKULIAH_KELAS + ".id " +
            "INNER JOIN " + Table.MAHASISWA + " ON " + Table.MAHASISWA_JADWAL +
            ".mahasiswa_id = " + Table.MAHASISWA + ".id " +
            "INNER JOIN " + Table.DOSEN + " ON " + Table.MATAKULIAH_KELAS +
            ".dosen_id = " + Table.DOSEN + ".id " +
            "INNER JOIN " + Table.MATAKULIAH + " ON " + Table.MATAKULIAH_KELAS +
            ".matakuliah_id = " + Table.MATAKULIAH + ".id";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<MahasiswaJadwal> findBy(MahasiswaJadwal param, int offset, int limit) {
        String sql = sqlGetDefault + " ";
        List<Object> params = new ArrayList<>();
        if (param.getId() != 0) {
            sql += "AND " + Table.MAHASISWA_JADWAL + ".id = ? ";
            params.add(param.getId());
        }

        if (param.getMahasiswa().getId() != 0) {
            sql += "AND " + Table.MAHASISWA + ".id = ? ";
            params.add(param.getMahasiswa().getId());
        }

        if (param.getMatakuliah().getId() != 0) {
            sql += "AND " + Table.MATAKULIAH + ".id = ? ";
            params.add(param.getMatakuliah().getId());
        }

        if (param.getDosen().getId() != 0) {
            sql += "AND " + Table.DOSEN + ".id = ? ";
            params.add(param.getDosen().getId());
        }

        if (param.getMatakuliahKelas().getId() != 0) {
            sql += "AND " + Table.MATAKULIAH_KELAS + ".id = ? ";
            params.add(param.getMatakuliahKelas().getId());
        }

        sql += "LIMIT ?,? ";
        params.add(offset);
        params.add(limit);

        return jdbcTemplate.query(sql, params.toArray(), new MahasiswaJadwalRowMapper());
    }

    @Override
    public MahasiswaJadwal save(MahasiswaJadwal entity) {
        String sql = "INSERT INTO " + Table.MAHASISWA_JADWAL + "("
                + "mahasiswa_id, " + "kelas_matakuliah_id)"
                + " VALUES(?,?) ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entity.getMahasiswa().getId());
            ps.setInt(2, entity.getMatakuliahKelas().getId());

            return ps;
        }, keyHolder);

        entity.setId(keyHolder.getKey().intValue());
        return entity;
    }

    @Override
    public MahasiswaJadwal update(MahasiswaJadwal entity) {
        String sql = "UPDATE " + Table.MAHASISWA_JADWAL + " SET " +
                "kelas_matakuliah_id = ? " +
                "WHERE " + Table.MAHASISWA_JADWAL + ".id = ? ";
        jdbcTemplate.update(sql,
                entity.getMatakuliahKelas().getId(),
                entity.getId());

        return entity;
    }

    @Override
    public String delete(int id) {
        String sql = "DELETE FROM " + Table.MAHASISWA_JADWAL + " WHERE id = ? ";
        jdbcTemplate.update(sql, id);

        return "Delete success";
    }

    @Override
    public List<MahasiswaJadwal> findAll(int offset, int limit) {
        return null;
    }

    @Override
    public MahasiswaJadwal findbyId(int id) {
        return null;
    }

    private class MahasiswaJadwalRowMapper implements RowMapper<MahasiswaJadwal> {

        @Override
        public MahasiswaJadwal mapRow(ResultSet resultSet, int i) throws SQLException {
            MahasiswaJadwal mahasiswaJadwal = new MahasiswaJadwal();

            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setId(resultSet.getInt("mhs_id"));
            mahasiswa.setName(resultSet.getString("mhs_nm"));

            Dosen dosen = new Dosen();
            dosen.setId(resultSet.getInt("ds_id"));
            dosen.setName(resultSet.getString("ds_nm"));

            Matakuliah matakuliah = new Matakuliah();
            matakuliah.setId(resultSet.getInt("mk_id"));
            matakuliah.setSks(resultSet.getString("mk_sks"));
            matakuliah.setName(resultSet.getString("mk_nm"));

            MatakuliahKelas matakuliahKelas = new MatakuliahKelas();
            matakuliahKelas.setId(resultSet.getInt("mkul_id"));
            matakuliahKelas.setName(resultSet.getString("mkul_nm"));
            matakuliahKelas.setRoom(resultSet.getString("mkul_room"));
            matakuliahKelas.setCreatedAt(resultSet.getDate("mkul_created"));

            mahasiswaJadwal.setMahasiswa(mahasiswa);
            mahasiswaJadwal.setDosen(dosen);
            mahasiswaJadwal.setMatakuliah(matakuliah);
            mahasiswaJadwal.setMatakuliahKelas(matakuliahKelas);
            return mahasiswaJadwal;
        }
    }
}
