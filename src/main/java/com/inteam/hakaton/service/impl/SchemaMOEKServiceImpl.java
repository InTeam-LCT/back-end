package com.inteam.hakaton.service.impl;

import com.inteam.hakaton.model.entity.SchemaMOEK;
import com.inteam.hakaton.repository.SchemaMOEKRepository;
import com.inteam.hakaton.service.SchemaMOEKService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@AllArgsConstructor
@Service
public class SchemaMOEKServiceImpl implements SchemaMOEKService {

    private final SchemaMOEKRepository moekRepository;

    @Async
    @Transactional
    @Override
    public void asyncSaveMOEK(Set<SchemaMOEK> events) {
        moekRepository.saveAll(events);
    }
}
