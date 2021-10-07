package com.pribas.task.rest.controller;

import com.pribas.task.rest.entity.Timeline;
import com.pribas.task.rest.service.TimelineService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/timelines")
@AllArgsConstructor
public class TimelineController {

    private final TimelineService timelineService;

    @GetMapping("/all")
    public List<Timeline> fetchAllTimelines(@RequestParam(required = false, defaultValue = "0") Integer page,
                                            @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return timelineService.getAllTimelines(page, pageSize).getContent();
    }

    @GetMapping("/find")
    public List<Timeline> findTimeline(@RequestParam(required = false, defaultValue = "") String searchText,
                                       @RequestParam(required = false, defaultValue = "0") Integer page,
                                       @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
        return timelineService.findTimeline(searchText, page, pageSize).getContent();
    }

    @PostMapping("/add")
    public String addTimeline(@RequestBody Timeline timeline) {
        return timelineService.addTimeline(timeline);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTimeline(@PathVariable String id) {
        return timelineService.deleteTimeline(id);
    }

    @PostMapping("/addMoment")
    public String addMoment(@RequestBody Map<String, String> body) {
        return timelineService.editMoments(body, true);
    }

    @DeleteMapping("/deleteMoment")
    public String deleteMoment(@RequestBody Map<String, String> body) {
        return timelineService.editMoments(body, false);
    }

    @PutMapping("/update")
    public String updateTimeline(@RequestBody Map<String, String> body) {
        return timelineService.update(body);
    }
}
