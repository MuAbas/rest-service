package com.pribas.task.rest.repository;

import com.pribas.task.rest.entity.Moment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MomentRepository extends MongoRepository<Moment, String> {
    Page<Moment> findAllBy(TextCriteria criteria, Pageable pageable);
}
