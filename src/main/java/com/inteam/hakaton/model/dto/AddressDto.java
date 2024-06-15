package com.inteam.hakaton.model.dto;

public record AddressDto(Long unom,
                         String city,
                         String area,
                         String district,
                         String street,
                         String numberHouse,
                         String enclosure,
                         String structure,
                         String snt,
                         String village,
                         GeoDataDto coordinate,
                         PredicationDto predication) {
}
