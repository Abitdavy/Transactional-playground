package com.example.demo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="Nasabah")
public class Nasabah {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String namaNasabah;
    private Double rateValue;
    private String time_updated;
}
