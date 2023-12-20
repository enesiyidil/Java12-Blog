package com.enes.converter;

public interface Converter<ENTITY, DTO> {

    DTO entityToDto(ENTITY entity);
}
