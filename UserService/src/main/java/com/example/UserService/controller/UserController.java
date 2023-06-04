package com.example.UserService.controller;

import com.example.UserService.entities.Rating;
import com.example.UserService.entities.User;
import com.example.UserService.exceptions.ResourceNotFoundException;
import com.example.UserService.external.service.RatingService;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    RatingService ratingService;

    int count = 0;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User userBody = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userBody);
    }

    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
//    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        count++;
        System.out.println(count);
        User getUser = userService.getUser(userId);
        return ResponseEntity.ok(getUser);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
        System.out.println("Call fallback method");
        User user = User.builder().email("dummy@gmail.com").name("Dummy")
                .about("This user is created dummy because some service is down")
                .userId("141234").build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> user = userService.getAllUser();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/createRating/feign")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return ResponseEntity.ok(ratingService.createRating(rating));
    }
}
