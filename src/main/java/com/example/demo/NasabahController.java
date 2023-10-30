package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nasabah")
public class NasabahController {
    @Autowired
    private NasabahService nasabahService;

    @PostMapping("/rate")
    public ResponseEntity<Nasabah> getNasabahRate(@RequestParam("type") String type){
        return ResponseEntity.ok().body(nasabahService.getRateValue(type));
    }
}
