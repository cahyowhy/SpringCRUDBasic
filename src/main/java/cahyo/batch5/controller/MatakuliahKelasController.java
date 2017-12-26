package cahyo.batch5.controller;

import cahyo.batch5.dao.MatakuliahKelasDao;
import cahyo.batch5.entity.Dosen;
import cahyo.batch5.entity.Matakuliah;
import cahyo.batch5.entity.MatakuliahKelas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "matakuliahkelass")
public class MatakuliahKelasController {

    @Autowired
    private MatakuliahKelasDao matakuliahKelasDao;

    @GetMapping
    public @ResponseBody
    List<MatakuliahKelas> findAll(@RequestParam("offset") String offset, @RequestParam("limit") String limit) {
        return matakuliahKelasDao.findAll(Integer.parseInt(offset), Integer.parseInt(limit));
    }

    @GetMapping(path = "/like")
    public @ResponseBody
    List<MatakuliahKelas> findBy(
            @RequestParam(value = "matakuliah_id", required = false) String matakuliahId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "room", required = false) String room,
            @RequestParam(value = "dosen_id", required = false) String dosenId,
            @RequestParam("offset") String offset,
            @RequestParam("limit") String limit) {

        MatakuliahKelas matakuliahKelas = new MatakuliahKelas();
        Matakuliah matakuliah = new Matakuliah();
        Dosen dosen = new Dosen();

        if (matakuliahId != null) matakuliah.setId(Integer.parseInt(matakuliahId));

        if (dosenId != null) dosen.setId(Integer.parseInt(dosenId));

        if (name != null) matakuliahKelas.setName(name);

        if (room != null) matakuliahKelas.setRoom(room);

        matakuliahKelas.setDosen(dosen);
        matakuliahKelas.setMatakuliah(matakuliah);
        return matakuliahKelasDao.findBy(matakuliahKelas, Integer.parseInt(offset), Integer.parseInt(limit));
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    MatakuliahKelas findById(@PathVariable String id) {
        return matakuliahKelasDao.findbyId(Integer.parseInt(id));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody
    MatakuliahKelas save(@RequestBody MatakuliahKelas matakuliahKelasRequestBody) {
        MatakuliahKelas matakuliahKelas = new MatakuliahKelas();
        matakuliahKelas.setName(matakuliahKelasRequestBody.getName());
        matakuliahKelas.setRoom(matakuliahKelasRequestBody.getRoom());

        Matakuliah matakuliah = new Matakuliah();
        matakuliah.setId(matakuliahKelasRequestBody.getMatakuliah().getId());

        Dosen dosen = new Dosen();
        dosen.setId(matakuliahKelasRequestBody.getDosen().getId());

        matakuliahKelas.setMatakuliah(matakuliah);
        matakuliahKelas.setDosen(dosen);
        return matakuliahKelasDao.save(matakuliahKelas);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    MatakuliahKelas update(@PathVariable String id, @RequestBody MatakuliahKelas matakuliahKelasRequestBody) {
        MatakuliahKelas matakuliahKelas = new MatakuliahKelas();
        matakuliahKelas.setName(matakuliahKelasRequestBody.getName());
        matakuliahKelas.setRoom(matakuliahKelasRequestBody.getRoom());
        matakuliahKelas.setId(Integer.parseInt(id));

        Matakuliah matakuliah = new Matakuliah();
        matakuliah.setId(matakuliahKelasRequestBody.getMatakuliah().getId());

        Dosen dosen = new Dosen();
        dosen.setId(matakuliahKelasRequestBody.getDosen().getId());

        matakuliahKelas.setMatakuliah(matakuliah);
        matakuliahKelas.setDosen(dosen);

        return matakuliahKelasDao.update(matakuliahKelas);
    }
}
