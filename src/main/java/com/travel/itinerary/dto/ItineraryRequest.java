package com.travel.itinerary.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryRequest {
    @NotBlank(message = "Destination is required")
    private String destination;

    @NotBlank(message = "Full itinerary is required")
    private String fullItinerary;

    private String startDate;
    private String endDate;
    private Integer numberOfDays;
    private String budgetRange;
    private String travelStyle;
}
