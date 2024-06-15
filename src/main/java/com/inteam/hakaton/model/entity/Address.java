package com.inteam.hakaton.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Include
    private Long globalId;
    @EqualsAndHashCode.Include
    private Long unom;
    private String buildingType;
    private String city;
    private String area;
    private String district;
    private String street;
    private String numberHouse;
    private String enclosure;
    private String structure;
    private String snt;
    private String village;
    @Column(name = "is_snt")
    private Boolean isSNT;
    private Boolean isVillage;
    private OffsetDateTime dateAddressRegister;
    private OffsetDateTime dateStateAddressRegister;
    private OffsetDateTime dateDocumentAddressRegister;
    @Embedded
    private Point coordinate;

    public Address(Long globalId,
                   String buildingType,
                   Long unom,
                   String city,
                   String village,
                   String street,
                   String snt,
                   String numberHouse,
                   String enclosure,
                   String structure,
                   String area,
                   String district,
                   OffsetDateTime dateAddressRegister,
                   OffsetDateTime dateStateAddressRegister,
                   OffsetDateTime dateDocumentAddressRegister,
                   Point coordinate,
                   Boolean isSNT,
                   Boolean isVillage) {
        this.globalId = globalId;
        this.unom = unom;
        this.buildingType = buildingType;
        this.city = city;
        this.area = area;
        this.district = district;
        this.street = street;
        this.numberHouse = numberHouse;
        this.enclosure = enclosure;
        this.structure = structure;
        this.snt = snt;
        this.village = village;
        this.dateAddressRegister = dateAddressRegister;
        this.dateStateAddressRegister = dateStateAddressRegister;
        this.dateDocumentAddressRegister = dateDocumentAddressRegister;
        this.coordinate = coordinate;
        this.isSNT = isSNT;
        this.isVillage = isVillage;
    }
}
