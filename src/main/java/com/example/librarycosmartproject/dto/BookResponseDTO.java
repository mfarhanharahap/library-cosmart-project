package com.example.librarycosmartproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookResponseDTO {
    private String title;
    private Integer firstPublishYear;
    private String editionCount;
    private AvailabilityDTO availability;
    private List<AuthorDTO> authors;
}
