package cahyo.batch5.controller;

import cahyo.batch5.dao.DosenDao;
import cahyo.batch5.entity.Dosen;
import cahyo.batch5.util.DateConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "dosens")
public class DosenController {
    @Autowired
    private DosenDao dosenDao;

    @GetMapping
    public @ResponseBody
    List<Dosen> findAll(@RequestParam("offset") String offset, @RequestParam("limit") String limit) {
        return dosenDao.findAll(Integer.parseInt(offset), Integer.parseInt(limit));
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Dosen findById(@PathVariable String id) {
        return dosenDao.findbyId(Integer.parseInt(id));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Dosen save(@RequestBody Dosen dosenRequestBody) {
        Dosen dosen = new Dosen();
        dosen.setName(dosenRequestBody.getName());

        String birthDate = String.valueOf(dosenRequestBody.getBirthDate());
        dosen.setBirthDate(DateConversion.deserializeToDate(birthDate));
        return dosenDao.save(dosen);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Dosen update(@PathVariable String id, @RequestBody Dosen dosenRequestBody) {
        Dosen dosen = new Dosen();
        dosen.setName(dosenRequestBody.getName());
        dosen.setBirthDate(dosenRequestBody.getBirthDate());
        dosen.setId(Integer.parseInt(id));

        return dosenDao.update(dosen);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    String delete(@PathVariable String id) {
        return dosenDao.delete(Integer.parseInt(id));
    }
}
