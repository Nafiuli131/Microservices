package com.example.HotelService.controller;

import com.example.HotelService.entities.Hotel;
import com.example.HotelService.repository.HotelRepository;
import com.example.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;
    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping
    public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel){
        Hotel hotelBody = hotelService.saveHotel(hotel);
        return ResponseEntity.ok(hotelBody);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        Hotel hotel = hotelService.getHotelInfo(hotelId);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotelInfo(){
        List<Hotel> hotelList = hotelService.getAllHotelInfo();
        return ResponseEntity.ok(hotelList);
    }
}
