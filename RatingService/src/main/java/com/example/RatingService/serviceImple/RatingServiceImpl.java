package com.example.RatingService.serviceImple;

import com.example.RatingService.entities.Rating;
import com.example.RatingService.exception.ResourceNotFoundException;
import com.example.RatingService.repository.RatingRepository;
import com.example.RatingService.servie.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    RatingRepository ratingRepository;
    @Override
    public Rating saveRating(Rating rating) {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRating(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(()->
                new ResourceNotFoundException(ratingId+" this id is not exist"));
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public  List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }
}
