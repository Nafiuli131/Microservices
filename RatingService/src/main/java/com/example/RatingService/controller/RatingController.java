package com.example.RatingService.controller;

import com.example.RatingService.entities.Rating;
import com.example.RatingService.servie.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity.ok(ratingService.saveRating(rating));
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRating(@PathVariable String ratingId) {
        return ResponseEntity.ok(ratingService.getRating(ratingId));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating() {
        return ResponseEntity.ok(ratingService.getAllRating());
    }

    @GetMapping("userId/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("hotelId/{hotelId}")
    public ResponseEntity< List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

}
