package com.inteam.hakaton.service.impl;

import com.inteam.hakaton.model.entity.Event;
import com.inteam.hakaton.repository.EventRepository;
import com.inteam.hakaton.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Async
    @Transactional
    @Override
    public void asyncSaveEvent(Set<Event> events) {
        eventRepository.saveAll(events);
    }

    @Override
    public List<Event> findByUnom(Long unom) {
        return eventRepository.findAllByUnom(unom);
    }
}
