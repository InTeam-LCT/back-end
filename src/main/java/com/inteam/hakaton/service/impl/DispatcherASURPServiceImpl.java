package com.inteam.hakaton.service.impl;

import com.inteam.hakaton.model.entity.DispatcherASURP;
import com.inteam.hakaton.repository.DispatcherASURPRepository;
import com.inteam.hakaton.service.DispatcherASURPService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@AllArgsConstructor
@Service
public class DispatcherASURPServiceImpl implements DispatcherASURPService {
    private final DispatcherASURPRepository asurpRepository;

    @Async
    @Transactional
    @Override
    public void asyncSaveASURP(Set<DispatcherASURP> asurps) {
        asurpRepository.saveAll(asurps);
    }
}
