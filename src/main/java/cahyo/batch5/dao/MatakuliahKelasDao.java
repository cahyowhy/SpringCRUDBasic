package cahyo.batch5.dao;

import cahyo.batch5.entity.MatakuliahKelas;

import java.util.List;

public interface MatakuliahKelasDao extends BaseDao<MatakuliahKelas> {
    List<MatakuliahKelas> findBy(MatakuliahKelas param, int offset, int limit);
}
