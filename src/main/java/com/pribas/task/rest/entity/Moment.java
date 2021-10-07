package com.pribas.task.rest.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.time.LocalDateTime;

@Data
@Document
public class Moment {
    @Id
    private String id;
    @TextIndexed
    private String title;
    @TextIndexed
    private String description;
    private String moment_date;
    private LocalDateTime creation_date;
    @TextScore
    private Float score;

    public Moment(String title, String description, String moment_date) {
        this.title = title;
        this.description = description;
        this.moment_date = moment_date;
        this.creation_date = LocalDateTime.now();
    }
}
