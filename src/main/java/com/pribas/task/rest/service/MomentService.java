package com.pribas.task.rest.service;

import com.pribas.task.rest.entity.Moment;
import com.pribas.task.rest.repository.MomentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/*  Service for Moment collection
    Handles all the logic and
    calls MomentRepository to fetch data    */
@AllArgsConstructor
@Service
public class MomentService {

    private final MomentRepository momentRepository;

    /*  fetch all Moment documents.
        show pageSize amount of documents per page   */
    public Page<Moment> getAllMoments(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return momentRepository.findAll(pageable);
    }

    /*  fetch all Moment documents where title or description fields match
        searchText string. Show pageSize amount of documents per page   */
    public Page<Moment> findMoment(String searchText, int page, int pageSize) {
        if (searchText.isEmpty())
            throw new NullPointerException("no search text");
        Sort sort = Sort.by("score");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(searchText);
        return momentRepository.findAllBy(criteria, pageable);
    }

    // add new Moment document to Moment collection
    public String addMoment(Moment moment) {
        momentRepository.save(moment);
        return "Moment Added::" + moment.getTitle();
    }

    /*  delete Moment document from
        Moment collection using its id   */
    public String deleteMoment(String id) {
        momentRepository.deleteById(id);
        return "Deleted Moment::" + id;
    }

    /*  takes a Map(JSON file) as a parameter to retrieve values form keys.
        updates a specific Moment document's fields (title, description, date(moment_date)) */
    public String update(Map<String, String> body) {
        String id = body.get("id");
        String title = body.get("title");
        String description = body.get("description");
        String date = body.get("date");
        Moment moment = momentRepository.findById(id).orElse(null);
        if (moment == null)
            throw new NullPointerException("not found");
        if (title != null && !title.isEmpty())
            moment.setTitle(title);
        if (description != null && !description.isEmpty())
            moment.setDescription(description);
        if (date != null && !date.isEmpty())
            moment.setMoment_date(date);
        momentRepository.save(moment);
        return "Moment Updated::" + id;
    }

    // fetch a Moment document using its id
    public Optional<Moment> getMomentById(String id) {
        return momentRepository.findById(id);
    }
}
