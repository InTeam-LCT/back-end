package com.inteam.hakaton.service.impl;

import com.inteam.hakaton.model.entity.CharacteristicStructure;
import com.inteam.hakaton.repository.CharacteristicStructureRepository;
import com.inteam.hakaton.service.CharacteristicStructureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class CharacteristicStructureServiceImpl implements CharacteristicStructureService {
    private final CharacteristicStructureRepository characteristicRepository;
    @Async
    @Transactional
    @Override
    public void asyncSaveCharacteristics(Set<CharacteristicStructure> characteristicStructures) {
        characteristicRepository.saveAll(characteristicStructures);
    }

    @Override
    public Optional<CharacteristicStructure> findByUnom(Long unom) {
        return characteristicRepository.findByUnom(unom);
    }
}
