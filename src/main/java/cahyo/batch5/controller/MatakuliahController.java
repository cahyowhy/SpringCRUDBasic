package cahyo.batch5.controller;

import cahyo.batch5.dao.MatakuliahDao;
import cahyo.batch5.entity.Matakuliah;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "matakuliahs")
public class MatakuliahController {
    @Autowired
    private MatakuliahDao matakuliahDao;

    @GetMapping
    public @ResponseBody
    List<Matakuliah> findAll(@RequestParam("offset") String offset, @RequestParam("limit") String limit) {
        return matakuliahDao.findAll(Integer.parseInt(offset), Integer.parseInt(limit));
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Matakuliah findById(@PathVariable String id) {
        return matakuliahDao.findbyId(Integer.parseInt(id));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Matakuliah save(@RequestBody Matakuliah matakuliahRequestBody) {
        Matakuliah matakuliah = new Matakuliah();
        matakuliah.setName(matakuliahRequestBody.getName());
        matakuliah.setSks(matakuliahRequestBody.getSks());

        return matakuliahDao.save(matakuliah);
    }

    @PutMapping(path = "/{id}", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Matakuliah update(@PathVariable String id, @RequestBody Matakuliah matakuliahRequestBody) {
        Matakuliah matakuliah = new Matakuliah();
        matakuliah.setName(matakuliahRequestBody.getName());
        matakuliah.setSks(matakuliahRequestBody.getSks());
        matakuliah.setId(Integer.parseInt(id));

        return matakuliahDao.update(matakuliah);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    String delete(@PathVariable String id) {
        return matakuliahDao.delete(Integer.parseInt(id));
    }
}
