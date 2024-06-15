package com.inteam.hakaton.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "schema_moek")
public class SchemaMOEK {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number_tp")
    private String numberTP;
    @Column(name = "unom_tp")
    private Long unomTP;
    @Column(name = "type_tp")
    private String typeTP;
    private String placementType;
    private String heatSource;
    private OffsetDateTime dateCommissioning;
    private String balancerAgent;
    private Long unomBuilding;
    @Column(name = "heat_load_dhw_average")
    private Double heatLoadDHWAverage;
    @Column(name = "heat_load_dhw_actual")
    private Double heatLoadDHWActual;
    @Column(name = "heat_load_building")
    private Double heatLoadBuilding;
    @Column(name = "heat_load_ventilation_building")
    private Double heatLoadVentilationBuilding;
    private Boolean isDispatching;

    public SchemaMOEK(String numberTP,
                      Long unomTP,
                      String typeTP,
                      String placementType,
                      String heatSource,
                      OffsetDateTime dateCommissioning,
                      String balancerAgent,
                      Long unomBuilding,
                      Double heatLoadDHWAverage,
                      Double heatLoadDHWActual,
                      Double heatLoadBuilding,
                      Double heatLoadVentilationBuilding,
                      Boolean isDispatching) {
        this.numberTP = numberTP;
        this.unomTP = unomTP;
        this.typeTP = typeTP;
        this.placementType = placementType;
        this.heatSource = heatSource;
        this.dateCommissioning = dateCommissioning;
        this.balancerAgent = balancerAgent;
        this.unomBuilding = unomBuilding;
        this.heatLoadDHWAverage = heatLoadDHWAverage;
        this.heatLoadDHWActual = heatLoadDHWActual;
        this.heatLoadBuilding = heatLoadBuilding;
        this.heatLoadVentilationBuilding = heatLoadVentilationBuilding;
        this.isDispatching = isDispatching;
    }
}
