package com.pribas.task.rest.repository;

import com.pribas.task.rest.entity.Timeline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimelineRepository extends MongoRepository<Timeline, String> {
    Page<Timeline> findAllBy(TextCriteria criteria, Pageable pageable);
}
