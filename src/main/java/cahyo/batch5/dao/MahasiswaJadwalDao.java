package cahyo.batch5.dao;

import cahyo.batch5.entity.MahasiswaJadwal;

import java.util.List;

public interface MahasiswaJadwalDao extends BaseDao<MahasiswaJadwal> {
    List<MahasiswaJadwal> findBy(MahasiswaJadwal param, int offset, int limit);
}
