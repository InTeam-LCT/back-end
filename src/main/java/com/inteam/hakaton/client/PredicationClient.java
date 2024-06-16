package com.inteam.hakaton.client;


import com.inteam.hakaton.model.dto.PredicationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "predication-ML", url = "${predication.url}")
public interface PredicationClient {
    @GetMapping(value = "/predict?date={date}", consumes = "application/json")
    List<PredicationDto> getPredication(@PathVariable("date") String date);
}
