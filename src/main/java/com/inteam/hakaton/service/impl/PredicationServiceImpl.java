package com.inteam.hakaton.service.impl;

import com.inteam.hakaton.mapper.PredicationMapper;
import com.inteam.hakaton.model.dto.PredicationDto;
import com.inteam.hakaton.model.entity.Predication;
import com.inteam.hakaton.repository.PredicationRepository;
import com.inteam.hakaton.service.PredicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PredicationServiceImpl implements PredicationService {
    private final PredicationRepository predicationRepository;
    private final PredicationMapper predicationMapper;
    @Override
    public void saveAll(List<PredicationDto> predications) {
        List<Predication> predicationList = predicationMapper.convertToEntity(predications);
        predicationRepository.saveAll(predicationList);
    }

    @Override
    public List<Predication> findAll() {
        return predicationRepository.findAll();
    }
}
