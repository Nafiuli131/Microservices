package com.example.UserService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/about")
public class AboutController {

    String about = "Nafiul Islam, Software Engineer";

    @GetMapping("/mySelf")
    public ResponseEntity<String> getDetails(){
        return ResponseEntity.ok(about);
    }
}
