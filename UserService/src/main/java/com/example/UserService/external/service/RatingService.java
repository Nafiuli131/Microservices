package com.example.UserService.external.service;

import com.example.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/rating")
    Rating createRating(Rating rating);
}
