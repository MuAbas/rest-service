package com.pribas.task.rest.controller;

import com.pribas.task.rest.entity.Moment;
import com.pribas.task.rest.service.MomentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Moment collection controller, handles requests sent to api/moments
@RestController
@RequestMapping("api/moments")
@AllArgsConstructor
public class MomentController {

    private final MomentService momentService;

    /*  GET request to fetch all Moment documents,
        takes page and pageSize parameters (not required)    */
    @GetMapping("/all")
    public List<Moment> fetchAllMoments(@RequestParam(required = false, defaultValue = "0") Integer page,
                                        @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return momentService.getAllMoments(page, pageSize).getContent();
    }

    /*  GET request to find a specific Moment document,
        takes:
            - searchText: search term
            - page and pageSize parameters (not required)   */
    @GetMapping("/find")
    public List<Moment> findMoment(@RequestParam(required = false, defaultValue = "") String searchText,
                                   @RequestParam(required = false, defaultValue = "0") Integer page,
                                   @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return momentService.findMoment(searchText, page, pageSize).getContent();
    }

    /*  POST request to add a new Moment to Moment collection
        Request must have a JSON body with required Moment fields (title, description, moment_date)  */
    @PostMapping("/add")
    public String addMoment(@RequestBody Moment moment) {
        return momentService.addMoment(moment);
    }
    /*  DELETE request to delete a Moment from Moment collection
        takes a path variable 'id'   */
    @DeleteMapping("/delete/{id}")
    public String deleteMoment(@PathVariable String id) {
        return momentService.deleteMoment(id);
    }
    /*  PUT request to update fields of a Moment document
        Request must have a JSON body of the moment's id
        with any of the following fields (title, description, moment_date)  */
    @PutMapping("/update")
    public String updateMoment(@RequestBody Map<String, String> body) {
        return momentService.update(body);
    }
}
