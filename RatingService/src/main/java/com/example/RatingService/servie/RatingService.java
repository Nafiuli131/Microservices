package com.example.RatingService.servie;

import com.example.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating saveRating(Rating rating);

    Rating getRating(String ratingId);

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

    List<Rating> getAllRating();
}
