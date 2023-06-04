package com.example.HotelService.service.serviceImp;

import com.example.HotelService.entities.Hotel;
import com.example.HotelService.exception.ResourceNotFound;
import com.example.HotelService.repository.HotelRepository;
import com.example.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImp implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelInfo(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFound(hotelId+" hotel id not found "));
    }

    @Override
    public List<Hotel> getAllHotelInfo() {
        return hotelRepository.findAll();
    }
}
