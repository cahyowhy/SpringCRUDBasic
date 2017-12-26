package cahyo.batch5.controller;

import cahyo.batch5.dao.MahasiswaDao;
import cahyo.batch5.entity.Mahasiswa;
import cahyo.batch5.util.DateConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "mahasiswas")
public class MahasiswaController {
    @Autowired
    private MahasiswaDao mahasiswaDao;

    @GetMapping
    public @ResponseBody
    List<Mahasiswa> findAll(@RequestParam("offset") String offset, @RequestParam("limit") String limit) {
        return mahasiswaDao.findAll(Integer.parseInt(offset), Integer.parseInt(limit));
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Mahasiswa findById(@PathVariable String id) {
        return mahasiswaDao.findbyId(Integer.parseInt(id));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Mahasiswa save(@RequestBody Mahasiswa mahasiswaRequestBody) {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setName(mahasiswaRequestBody.getName());

        String birthDate = String.valueOf(mahasiswaRequestBody.getBirthDate());
        mahasiswa.setBirthDate(DateConversion.deserializeToDate(birthDate));
        return mahasiswaDao.save(mahasiswa);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Mahasiswa update(@PathVariable String id, @RequestBody Mahasiswa mahasiswaRequestBody) {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setName(mahasiswaRequestBody.getName());
        mahasiswa.setBirthDate(mahasiswaRequestBody.getBirthDate());
        mahasiswa.setId(Integer.parseInt(id));

        return mahasiswaDao.update(mahasiswa);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    String delete(@PathVariable String id) {
        return mahasiswaDao.delete(Integer.parseInt(id));
    }
}
