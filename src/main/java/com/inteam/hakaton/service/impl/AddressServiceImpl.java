package com.inteam.hakaton.service.impl;

import com.inteam.hakaton.exception.AddressNotFoundException;
import com.inteam.hakaton.mapper.AddressMapper;
import com.inteam.hakaton.mapper.PredicationMapper;
import com.inteam.hakaton.model.dto.AddressDto;
import com.inteam.hakaton.model.dto.PredicationDto;
import com.inteam.hakaton.model.entity.Address;
import com.inteam.hakaton.model.entity.Predication;
import com.inteam.hakaton.repository.AddressRepository;
import com.inteam.hakaton.service.AddressService;
import com.inteam.hakaton.service.PredicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressMapper addressMapper;
    private final PredicationMapper predicationMapper;
    private final AddressRepository addressRepository;
    private final PredicationService predicationService;

    @Async
    @Transactional
    @Override
    public void asyncSaveAddresses(Set<Address> addresses) {
        addressRepository.saveAll(addresses);
    }

    @Override
    public Optional<Address> findByUnom(Long unom) {
        return addressRepository.findByUnom(unom);
    }

    @Override
    public Optional<Address> findByFullAddress(String area, String district, String street,
                                               String numberHouse, String enclosure, String structure) {
        Optional<Address> address = Optional.empty();
        try {
            address = addressRepository
                    .findByStreetHouseEnclosureStructure(
                            area,
                            district,
                            street != null && !street.isBlank() ? street : "",
                            numberHouse != null && !numberHouse.isBlank() ? numberHouse : "",
                            enclosure != null && !enclosure.isBlank() ? enclosure : "",
                            structure != null && !structure.isBlank() ? structure : "");
        } catch (IncorrectResultSizeDataAccessException ex) {
            log.warn("[AddressServiceImpl] Появился дубликат адрес: {} {} {} {} {} {}",
                    area, district, street, numberHouse, enclosure, structure);
        }
        return address;
    }

    @Override
    public Optional<Address> findByAddressByVillage(String area, String district, String village,
                                                    String numberHouse, String enclosure, String structure) {
        Optional<Address> address = Optional.empty();
        try {
            address = addressRepository
                    .findByAddressByVillage(
                            area != null && !area.isBlank() ? area : "",
                            district != null && !district.isBlank() ? district : "",
                            village,
                            numberHouse != null && !numberHouse.isBlank() ? numberHouse : "",
                            enclosure != null && !enclosure.isBlank() ? enclosure : "",
                            structure != null && !structure.isBlank() ? structure : "");
        } catch (IncorrectResultSizeDataAccessException ex) {
            log.warn("[AddressServiceImpl] Появился дубликат адрес: {} {} {} {} {} {}",
                    area, district, village, numberHouse, enclosure, structure);
        }
        return address;
    }

    @Override
    public AddressDto getByUnom(Long unom) {
        Optional<Address> address = addressRepository.findByUnom(unom);
        return addressMapper.convertToDto(
                address.orElseThrow(() -> new AddressNotFoundException("Адрес не найден - UNOM: " + unom))
        );
    }

    @Override
    public List<AddressDto> getByArea(String area) {
        List<Address> addresses = addressRepository.findAllByArea(area);
        if (addresses == null || addresses.isEmpty()) {
            throw new AddressNotFoundException("Адреса по заданному округу не найдены - AREA: " + area);
        }
        return addressMapper.convertListToDto(addresses);
    }

    @Override
    public List<AddressDto> getByDistrict(String district) {
        List<Address> addresses = addressRepository.findAllByDistrict(district);
        if (addresses == null || addresses.isEmpty()) {
            throw new AddressNotFoundException("Адреса по заданному району не найдены - AREA: " + district);
        }
        return addressMapper.convertListToDto(addresses);
    }

    @Override
    public List<AddressDto> getAddressesByPredication() {
        List<Predication> predications = predicationService.findAll();
        List<AddressDto> addressDtos = new ArrayList<>();

        for (Predication predication : predications ) {
            if (predication.getPrediction() != null && predication.getPrediction() > 0) {
                Optional<Address> address = addressRepository.findByUnom(predication.getUnom());
                address.ifPresent(add -> {
                    PredicationDto predicationDto = predicationMapper.convertToDto(predication);
                    AddressDto addressDto = addressMapper.convertToDto(add, predicationDto);
                    addressDtos.add(addressDto);
                });
            }
        }
        return addressDtos;
    }

    @Override
    public List<AddressDto> getAddressesByPredication(List<PredicationDto> predicationDto) {
        List<AddressDto> addressDtos = new ArrayList<>();

        for (PredicationDto predication : predicationDto ) {
            if (predication.prediction() != null && predication.prediction() > 0) {
                Optional<Address> address = addressRepository.findByUnom(predication.unom());
                address.ifPresent(add -> {
                    AddressDto addressDto = addressMapper.convertToDto(add, predication);
                    addressDtos.add(addressDto);
                });
            }
        }
        return addressDtos;
    }
}
