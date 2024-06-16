package com.inteam.hakaton.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record PredicationDto(Long unom,
                             Double prediction,
                             @JsonProperty(value = "usage_priority_type")
                             Double usagePriorityType,
                             @JsonProperty(value = "prediction_date")
                             LocalDate predictionDate,
                             @JsonProperty(value = "n_flats")
                             Double numberFlats,
                             Double square,
                             String material,
                             @JsonProperty(value = "assignment_structure")
                             String assignmentStructure,
                             @JsonProperty(value = "distance_to_moscow_center")
                             Double distanceToMoscowCenter,
                             @JsonProperty(value = "temp_mean_day")
                             Double tempMeanDay,
                             String weather) {
}
