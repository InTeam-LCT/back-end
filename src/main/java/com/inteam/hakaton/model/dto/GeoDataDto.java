package com.inteam.hakaton.model.dto;

import java.util.List;

public record GeoDataDto(List<Double> coordinates,
                         String type) {
}
