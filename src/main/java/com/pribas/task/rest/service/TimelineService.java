package com.pribas.task.rest.service;

import com.pribas.task.rest.entity.Moment;
import com.pribas.task.rest.entity.Timeline;
import com.pribas.task.rest.repository.MomentRepository;
import com.pribas.task.rest.repository.TimelineRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*  Service for Timeline collection
    Handles all the logic and
    calls TimelineRepository to fetch data    */
@AllArgsConstructor
@Service
public class TimelineService {

    private final TimelineRepository timelineRepository;
    private final MomentRepository momentRepository;

    /*  fetch all Timeline documents.
        show pageSize amount of documents per page   */
    public Page<Timeline> getAllTimelines(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return timelineRepository.findAll(pageable);
    }

    /*  fetch all Timeline documents where title or description fields match
        searchText string. Show pageSize amount of documents per page   */
    public Page<Timeline> findTimeline(String searchText, int page, int pageSize) {
        if (searchText.isEmpty())
            throw new NullPointerException("no search text");
        Sort sort = Sort.by("score");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(searchText);
        return timelineRepository.findAllBy(criteria, pageable);
    }

    // add new Timeline document to Timeline collection
    public String addTimeline(Timeline timeline) {
        timelineRepository.save(timeline);
        return "Timeline Added::" + timeline.getTitle();
    }

    /*  delete Timeline document from
        Timeline collection using its id   */
    public String deleteTimeline(String id) {
        timelineRepository.deleteById(id);
        return "Deleted Timeline::" + id;
    }

    /*  edit moments[] field in a Timeline document.
        takes:
        - Map(JSON file): to retrieve values from keys.
        - boolean addFlag: indicates whether to add a moment or remove from moments list  */
    public String editMoments(Map<String, String> body, boolean addFlag) {
        String timelineId = body.get("timelineId");
        String momentId = body.get("momentId");
        Timeline timeline = timelineRepository.findById(timelineId).orElse(null);
        if (timeline == null)
            throw new NullPointerException("not found");
        if (momentId == null || momentId.isEmpty())
            throw new NullPointerException("no moment id");
        List<Moment> moments = getMomentsList(timelineId);
        if (addFlag)
            moments.add(momentRepository.findById(momentId).orElse(null));
        else
            moments.remove(momentRepository.findById(momentId).orElse(null));
        timeline.setMoments(moments);
        timelineRepository.save(timeline);
        return ((addFlag) ? "Moment Added::" : "Moment Removed::") + timelineId;
    }

    /*  takes a Map(JSON file) as a parameter to retrieve values form keys.
        updates a specific Timeline document's fields (userid, title, description, tags) */
    public String update(Map<String, String> body) {
        String id = body.get("id");
        String userid = body.get("userid");
        String title = body.get("title");
        String description = body.get("description");
        String tags = body.get("tags");
        Timeline timeline = timelineRepository.findById(id).orElse(null);
        if (timeline == null) {
            throw new NullPointerException("not found");
        }
        if (userid != null && !userid.isEmpty())
            timeline.setUserid(userid);
        if (title != null && !title.isEmpty())
            timeline.setTitle(title);
        if (description != null && !description.isEmpty())
            timeline.setDescription(description);
        if (tags != null && !tags.isEmpty())
            timeline.setTags(Arrays.asList(tags.split("\\s*,\\s*")));
        timelineRepository.save(timeline);
        return "Timeline Updated::" + id;
    }

    // get moments list of a specific Timeline
    public List<Moment> getMomentsList(String id) {
        return timelineRepository.findById(id).orElse(null).getMoments();
    }
}
