package com.inteam.hakaton.service.impl;


import com.inteam.hakaton.model.entity.Characteristic;
import com.inteam.hakaton.repository.CharacteristicRepository;
import com.inteam.hakaton.service.CharacteristicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class CharacteristicServiceImpl implements CharacteristicService {
    private final CharacteristicRepository repository;
    @Override
    public void saveAsync(Set<Characteristic> characteristics) {
        repository.saveAll(characteristics);
    }
}
