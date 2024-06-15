package com.inteam.hakaton.mapper;

import com.inteam.hakaton.model.dto.AddressDto;
import com.inteam.hakaton.model.dto.GeoDataDto;
import com.inteam.hakaton.model.dto.PredicationDto;
import com.inteam.hakaton.model.entity.Address;
import com.inteam.hakaton.model.entity.Point;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    List<AddressDto> convertListToDto(List<Address> addresses);

    @Mapping(target = "unom", source = "address.unom")
    @Mapping(target = "predication", source = "predication")
    AddressDto convertToDto(Address address, PredicationDto predication);
    @Mapping(target = "predication", ignore = true)
    AddressDto convertToDto(Address address);

    default GeoDataDto convertPoint(Point point) {
        if (point == null) {
            return null;
        }
        return new GeoDataDto(List.of(point.getX(), point.getY()), "Point");
    }
}
