package com.example.librarycosmartproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TicketResponseDTO {
    private String title;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    List<String> authors;

    private String edition;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date pickUpSchedule;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expiredDate;
}
