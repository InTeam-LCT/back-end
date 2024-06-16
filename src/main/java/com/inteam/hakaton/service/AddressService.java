package com.inteam.hakaton.service;

import com.inteam.hakaton.model.dto.AddressDto;
import com.inteam.hakaton.model.dto.PredicationDto;
import com.inteam.hakaton.model.entity.Address;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AddressService {
    void asyncSaveAddresses(Set<Address> addresses);

    Optional<Address> findByUnom(Long unom);

    Optional<Address> findByFullAddress(String area, String district, String street,
                                        String numberHouse, String enclosure, String structure);

    Optional<Address> findByAddressByVillage(String area, String district, String village,
                                             String numberHouse, String enclosure, String structure);

    AddressDto getByUnom(Long unom);

    List<AddressDto> getByArea(String area);

    List<AddressDto> getByDistrict(String district);
    List<AddressDto> getAddressesByPredication();
    public List<AddressDto> getAddressesByPredication(List<PredicationDto> predicationDto);
}
