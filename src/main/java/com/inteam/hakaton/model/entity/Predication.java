package com.inteam.hakaton.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Predication {
    @Id
    private Long unom;
    private Double prediction;
    private Integer usagePriorityType;
    private LocalDate predictionDate;
    private Double square;
    @Column(name = "n_flats")
    private Double numberFlats;
    private String material;
    private String assignmentStructure;
    private Double distanceToMoscowCenter;
    private Double tempMeanDay;
    private String weather;

}
