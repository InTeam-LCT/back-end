package com.inteam.hakaton.mapper;

import com.inteam.hakaton.model.dto.PredicationDto;
import com.inteam.hakaton.model.entity.Predication;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PredicationMapper {
    Predication convertToEntity(PredicationDto predication);
    PredicationDto convertToDto(Predication predication);
    List<Predication> convertToEntity(List<PredicationDto> predication);

}
