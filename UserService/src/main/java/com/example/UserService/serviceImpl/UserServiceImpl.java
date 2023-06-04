package com.example.UserService.serviceImpl;

import com.example.UserService.entities.Hotel;
import com.example.UserService.entities.Rating;
import com.example.UserService.entities.User;
import com.example.UserService.exceptions.ResourceNotFoundException;
import com.example.UserService.external.service.HotelService;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HotelService hotelService;

    @Override
    public User saveUser(User user) {

        String uniqueId = UUID.randomUUID().toString();
        user.setUserId(uniqueId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //change the host and port name by service name that is used by service registry
        Rating[] ratingArray = restTemplate.getForObject("http://RATING-SERVICE/rating/userId/"
                + userId, Rating[].class);
        List<Rating> ratingList = Arrays.asList(Objects.requireNonNull(ratingArray));

        //call hotel service by using open feign interface method
        Objects.requireNonNull(ratingList).forEach(rating -> {
            Hotel hotel = hotelService.getHotelId(rating.getHotelId());
            rating.setHotel(hotel);
        });

        User user = userRepository.findById(userId).orElseThrow(() -> new
                ResourceNotFoundException("Resource not found according this id: " + userId));
        user.setRatingList(ratingList);
        return user;
    }
}
