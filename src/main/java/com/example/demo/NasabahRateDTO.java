package com.example.demo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NasabahRateDTO {
    private String type;
    private Double rateValue;
}
