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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dispatcher_asurp")
public class DispatcherASURP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_yy")
    private Long idYY;
    private Long unomServiceAddress;
    private String groupBuilding;
    private String numberJointDispatchService;
    private String consumer;
    @Column(name = "number_tp")
    private String numberTP;

    public DispatcherASURP(Long idYY,
                           Long unomServiceAddress,
                           String groupBuilding,
                           String numberJointDispatchService,
                           String consumer,
                           String numberTP) {
        this.idYY = idYY;
        this.unomServiceAddress = unomServiceAddress;
        this.groupBuilding = groupBuilding;
        this.numberJointDispatchService = numberJointDispatchService;
        this.consumer = consumer;
        this.numberTP = numberTP;
    }
}
