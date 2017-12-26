package cahyo.batch5.controller;

import cahyo.batch5.dao.MahasiswaJadwalDao;
import cahyo.batch5.entity.*;
import cahyo.batch5.util.DateConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "mahasiswa-jadwals")
public class MahasiswaJadwalController {
    @Autowired
    private MahasiswaJadwalDao mahasiswaJadwalDao;

    @GetMapping
    public @ResponseBody
    List<MahasiswaJadwal> findBy(
            @RequestParam(value = "offset") String offset,
            @RequestParam(value = "limit") String limit,
            @RequestParam(value = "matkul_kelas_id") String matkulKelasId,
            @RequestParam(value = "mahasiswa_id", required = false) String mahasiswaId,
            @RequestParam(value = "matakuliah_id", required = false) String matakuliahId,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "dosen_id", required = false) String dosenId) {

        MahasiswaJadwal mahasiswaJadwal = new MahasiswaJadwal();
        Mahasiswa mahasiswa = new Mahasiswa();
        Matakuliah matakuliah = new Matakuliah();
        MatakuliahKelas matakuliahKelas = new MatakuliahKelas();

        Dosen dosen = new Dosen();

        if (id != null) mahasiswaJadwal.setId(Integer.parseInt(id));

        if (mahasiswaId != null) mahasiswa.setId(Integer.parseInt(mahasiswaId));

        if (dosenId != null) dosen.setId(Integer.parseInt(dosenId));

        if (matakuliahId != null) matakuliah.setId(Integer.parseInt(matakuliahId));

        if (matkulKelasId != null) matakuliahKelas.setId(Integer.parseInt(matkulKelasId));

        mahasiswaJadwal.setMahasiswa(mahasiswa);
        mahasiswaJadwal.setDosen(dosen);
        mahasiswaJadwal.setMatakuliah(matakuliah);
        mahasiswaJadwal.setMatakuliahKelas(matakuliahKelas);

        return mahasiswaJadwalDao.findBy(mahasiswaJadwal, Integer.parseInt(offset), Integer.parseInt(limit));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody
    MahasiswaJadwal save(@RequestBody MahasiswaJadwal mahasiswaJadwalRequestBody) {
        MahasiswaJadwal mahasiswaJadwal = new MahasiswaJadwal();
        Mahasiswa mahasiswa = new Mahasiswa();
        MatakuliahKelas matakuliahKelas = new MatakuliahKelas();

        mahasiswa.setId(mahasiswaJadwalRequestBody.getMahasiswa().getId());
        matakuliahKelas.setId(mahasiswaJadwalRequestBody.getMatakuliahKelas().getId());

        mahasiswaJadwal.setMatakuliahKelas(matakuliahKelas);
        mahasiswaJadwal.setMahasiswa(mahasiswa);
        return mahasiswaJadwalDao.save(mahasiswaJadwal);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    MahasiswaJadwal update(@PathVariable String id, @RequestBody MahasiswaJadwal mahasiswaJadwalRequestBody) {
        MahasiswaJadwal mahasiswaJadwal = new MahasiswaJadwal();
        Mahasiswa mahasiswa = new Mahasiswa();
        MatakuliahKelas matakuliahKelas = new MatakuliahKelas();

        mahasiswa.setId(mahasiswaJadwalRequestBody.getMahasiswa().getId());
        matakuliahKelas.setId(mahasiswaJadwalRequestBody.getMatakuliahKelas().getId());

        mahasiswaJadwal.setMatakuliahKelas(matakuliahKelas);
        mahasiswaJadwal.setMahasiswa(mahasiswa);
        mahasiswaJadwal.setId(Integer.parseInt(id));
        return mahasiswaJadwalDao.save(mahasiswaJadwal);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    String delete(@PathVariable String id) {
        return mahasiswaJadwalDao.delete(Integer.parseInt(id));
    }
}
