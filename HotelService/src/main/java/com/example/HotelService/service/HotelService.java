package com.example.HotelService.service;

import com.example.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create hotel
    Hotel saveHotel(Hotel hotel);

    //get hotel info
    Hotel getHotelInfo(String hotelId);

    //get all hotel info
    List<Hotel> getAllHotelInfo();
}
