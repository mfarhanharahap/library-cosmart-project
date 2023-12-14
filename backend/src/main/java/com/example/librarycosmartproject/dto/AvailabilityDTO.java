package com.example.librarycosmartproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AvailabilityDTO {
    private String status;
    private Boolean availableToBorrow;
    private Date lastLoanDate;
}
