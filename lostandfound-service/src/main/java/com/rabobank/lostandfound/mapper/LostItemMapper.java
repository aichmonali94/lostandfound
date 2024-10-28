package com.rabobank.lostandfound.mapper;

import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.model.LostItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface LostItemMapper {

    LostItemMapper INSTANCE = Mappers.getMapper(LostItemMapper.class);

    //@Mapping(target = "claimedByUsers", ignore = true)
    LostItemDto mapToLostItemDto(LostItem lostItem);

    //@Mapping(target = "claimedByUsers", ignore = true)
    LostItem mapToLostItemEntity(LostItemDto lostItemDto);

}
