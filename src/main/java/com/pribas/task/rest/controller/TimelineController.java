package com.pribas.task.rest.controller;

import com.pribas.task.rest.entity.Timeline;
import com.pribas.task.rest.service.TimelineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Timeline collection controller, handles requests sent to api/timelines
@RestController
@RequestMapping("api/timelines")
@AllArgsConstructor
public class TimelineController {

    private final TimelineService timelineService;

    /*  GET request to fetch all Timeline documents,
        takes page and pageSize parameters (not required)    */
    @GetMapping("/all")
    public List<Timeline> fetchAllTimelines(@RequestParam(required = false, defaultValue = "0") Integer page,
                                            @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return timelineService.getAllTimelines(page, pageSize).getContent();
    }

    /*  GET request to find a specific Timeline document,
        takes:
            - searchText: search term
            - page and pageSize parameters (not required)   */
    @GetMapping("/find")
    public List<Timeline> findTimeline(@RequestParam(required = false, defaultValue = "") String searchText,
                                       @RequestParam(required = false, defaultValue = "0") Integer page,
                                       @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return timelineService.findTimeline(searchText, page, pageSize).getContent();
    }

    /*  POST request to add a new Timeline to Timeline collection
        Request must have a JSON body with required Timeline fields (title, description, userid, tags[])  */
    @PostMapping("/add")
    public String addTimeline(@RequestBody Timeline timeline) {
        return timelineService.addTimeline(timeline);
    }

    /*  DELETE request to delete a Timeline from Timeline collection
        takes a path variable 'id'   */
    @DeleteMapping("/delete/{id}")
    public String deleteTimeline(@PathVariable String id) {
        return timelineService.deleteTimeline(id);
    }

    /*  POST request to add a new Moment to a Timeline's list of moments
        Request must have a JSON body with required fields (timelineId, momentId)  */
    @PostMapping("/addMoment")
    public String addMoment(@RequestBody Map<String, String> body) {
        return timelineService.editMoments(body, true);
    }

    /*  DELETE request to remove a Moment from a Timeline's list of moments
        Request must have a JSON body with required fields (timelineId, momentId)  */
    @DeleteMapping("/deleteMoment")
    public String deleteMoment(@RequestBody Map<String, String> body) {
        return timelineService.editMoments(body, false);
    }

    /*  PUT request to update fields of a Timeline document
        Request must have a JSON body of the timeline's id
        with any of the following fields (title, description, userid, tags[])  */
    @PutMapping("/update")
    public String updateTimeline(@RequestBody Map<String, String> body) {
        return timelineService.update(body);
    }
}
