package com.inteam.hakaton.controller;

import com.inteam.hakaton.model.dto.AddressDto;
import com.inteam.hakaton.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v0/addresses")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> getAddressByPredication() {
        List<AddressDto> response = addressService.getAddressesByPredication();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/unom/{unom}")
    public ResponseEntity<AddressDto> getAddressByUNOM(@PathVariable Long unom) {
        AddressDto response = addressService.getByUnom(unom);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/area/{area}")
    public ResponseEntity<List<AddressDto>> getAddressByArea(@PathVariable String area) {
        List<AddressDto> response = addressService.getByArea(area);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/district/{district}")
    public ResponseEntity<List<AddressDto>> getAddressByDistrict(@PathVariable String district) {
        List<AddressDto> response = addressService.getByDistrict(district);
        return ResponseEntity.ok(response);
    }
}
