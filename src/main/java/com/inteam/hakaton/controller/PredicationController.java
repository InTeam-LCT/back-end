package com.inteam.hakaton.controller;

import com.inteam.hakaton.model.dto.AddressDto;
import com.inteam.hakaton.model.dto.PredicationDto;
import com.inteam.hakaton.service.AddressService;
import com.inteam.hakaton.service.PredicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v0/predications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PredicationController {
    private final PredicationService predicationService;
    private final AddressService addressService;
    @PostMapping("/save")
    public ResponseEntity<Void> getAddressByPredication(@RequestBody List<PredicationDto> predications) {
        predicationService.saveAll(predications);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<AddressDto>> getAddressByPredication(@PathVariable String date) {
        List<PredicationDto> predications = predicationService.getByDatePredict(date);
        List<AddressDto> addressDtos = addressService.getAddressesByPredication(predications);
        return ResponseEntity.ok(addressDtos);
    }
}
