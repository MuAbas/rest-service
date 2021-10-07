package com.pribas.task.rest;

import com.pribas.task.rest.entity.Moment;
import com.pribas.task.rest.entity.Timeline;
import com.pribas.task.rest.entity.User;
import com.pribas.task.rest.repository.MomentRepository;
import com.pribas.task.rest.repository.TimelineRepository;
import com.pribas.task.rest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserRepository userRepository, TimelineRepository timelineRepository, MomentRepository momentRepository) {
        return args -> {
            User user = new User(
                    "mubas",
                    "mohalabas@yahoo.com",
                    "1234"
            );
//            userRepository.insert(user);

            Moment moment1 = new Moment(
                    "Company Establishment",
                    "Established in Frankfurt",
                    "12.12"
            );
            Moment moment2 = new Moment(
                    "B2B Booking",
                    "First Implementation",
                    "14.14"
            );
//            momentRepository.insert(moment1);
//            momentRepository.insert(moment2);

//            List<Moment> moments = new ArrayList<>();
//            moments.add(moment1);
//            moments.add(moment2);

            List<String> tags = new ArrayList<>();
            tags.add("tag1");
            tags.add("tag2");

            Timeline timeline = new Timeline(
                    "Great Timeline",
                    "important moments",
                    user.getId(),
                    tags
            );
//            timelineRepository.insert(timeline);

        };
    }

}
