package com.pribas.task.rest.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*  Timeline object/entity,
    fields: id(id), title & description(indexed for object),
            userid, creation_date(declared when an object is created),
            tags[], moments[], score(calculated when performing a search)   */
@Data
@Document
public class Timeline {
    @Id
    private String id;
    @TextIndexed
    private String title;
    @TextIndexed
    private String description;
    private String userid;
    private LocalDateTime creation_date;
    private List<String> tags;
    private List<Moment> moments;
    @TextScore
    private Float score;

    public Timeline(String title, String description, String userid, List<String> tags) {
        this.title = title;
        this.description = description;
        this.userid = userid;
        this.tags = tags;
        this.creation_date = LocalDateTime.now();
        this.moments = new ArrayList<>();
    }
}
