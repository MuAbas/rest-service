package com.pribas.task.rest.controller;

import com.pribas.task.rest.entity.Moment;
import com.pribas.task.rest.service.MomentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/moments")
@AllArgsConstructor
public class MomentController {

    private final MomentService momentService;

    @GetMapping("/all")
    public List<Moment> fetchAllMoments(@RequestParam(required = false, defaultValue = "0") Integer page,
                                        @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return momentService.getAllMoments(page, pageSize).getContent();
    }

    @GetMapping("/find")
    public List<Moment> findMoment(@RequestParam(required = false, defaultValue = "") String searchText,
                                   @RequestParam(required = false, defaultValue = "0") Integer page,
                                   @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return momentService.findMoment(searchText, page, pageSize).getContent();
    }

    @PostMapping("/add")
    public String addMoment(@RequestBody Moment moment) {
        return momentService.addMoment(moment);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMoment(@PathVariable String id) {
        return momentService.deleteMoment(id);
    }

    @PutMapping("/update")
    public String updateMoment(@RequestBody Map<String, String> body) {
        return momentService.update(body);
    }
}
