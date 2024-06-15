package com.inteam.hakaton.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CharacteristicStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long unom;

    private Integer unad;
    private String material;
    private String assignmentStructure;
    private String classStructure;
    private String buildingType;
    private Integer floorArea;
    private Double square;

    public CharacteristicStructure(Long unom,
                                   Integer unad,
                                   String material,
                                   String assignmentStructure,
                                   String classStructure,
                                   String buildingType,
                                   Integer floorArea,
                                   Double square) {
        this.unom = unom;
        this.unad = unad;
        this.material = material;
        this.assignmentStructure = assignmentStructure;
        this.classStructure = classStructure;
        this.buildingType = buildingType;
        this.floorArea = floorArea;
        this.square = square;
    }
}
