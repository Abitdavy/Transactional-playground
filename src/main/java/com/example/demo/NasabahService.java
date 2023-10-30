package com.example.demo;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class NasabahService {

    @Autowired
    private NasabahRepository nasabahRepository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public Nasabah getRateValue(String type){
        HttpHeaders headers = new HttpHeaders();
        NasabahRateDTO tempNasabah = new NasabahRateDTO();
        Nasabah nasabah = new Nasabah();
        Address address = new Address();

        address.setAddress("bojong");

        ResponseEntity<NasabahRateDTO> response = restTemplate.getForEntity(
                "http://localhost:9000/api/rates/" + type.toUpperCase(), NasabahRateDTO.class
        );
        tempNasabah = response.getBody();
        nasabah.setRateValue(tempNasabah.getRateValue());
        nasabah.setNamaNasabah("test1");
        nasabah.setTime_updated(String.valueOf(new Date()));
        nasabahRepository.save(nasabah);
        return nasabah;
    }
}
