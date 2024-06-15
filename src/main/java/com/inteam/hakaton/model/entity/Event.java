package com.inteam.hakaton.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;

    private String source;
    private OffsetDateTime startTimestamp;
    private OffsetDateTime factEndTimestamp;
    private Long unom;
    private OffsetDateTime endTimestamp;

    public Event(String eventName,
                 String source,
                 OffsetDateTime startTimestamp,
                 OffsetDateTime factEndTimestamp,
                 Long unom,
                 OffsetDateTime endTimestamp) {
        this.eventName = eventName;
        this.source = source;
        this.startTimestamp = startTimestamp;
        this.factEndTimestamp = factEndTimestamp;
        this.unom = unom;
        this.endTimestamp = endTimestamp;
    }
}
