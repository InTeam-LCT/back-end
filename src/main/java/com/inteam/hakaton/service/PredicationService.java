package com.inteam.hakaton.service;

import com.inteam.hakaton.model.dto.PredicationDto;
import com.inteam.hakaton.model.entity.Predication;

import java.util.List;

public interface PredicationService {
    void saveAll(List<PredicationDto> predications);
    List<Predication> findAll();
}
