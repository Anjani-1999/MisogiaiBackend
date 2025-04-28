package com.erp.jwt.mapper;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class GlobalMapper {

    public static ModelMapper modelMapper = new ModelMapper();


    public <T, U> U entityToDto(T entity, Class<U> dtoClass) {
        try {
            return modelMapper.map(entity, dtoClass);
        } catch (Exception e) {
            log.error("Error in converting entity to dto", e);
            return null;
        }
    }

    // List of Entities to List of DTOs
    public <T, U> List<U> entityToDto(List<T> entities, Class<U> dtoClass) {
        try {
            return modelMapper.map(entities, new TypeToken<List<U>>() {}.getType());
        } catch (Exception e) {
            log.error("Error in converting entities to dtos", e);
            return null;
        }
    }


    // Single DTO to Entity
    public <T, U> U dtoToEntity(T dto, Class<U> entityClass) {
        try {
            return modelMapper.map(dto, entityClass);
        } catch (Exception e) {
            log.error("Error in converting dto to entity", e);
            return null;
        }
    }

    // List of DTOs to List of Entities
    public <T, U> List<U> dtoToEntity(List<T> dtos, Class<U> entityClass) {
        try {
            return modelMapper.map(dtos, new TypeToken<List<U>>() {}.getType());
        } catch (Exception e) {
            log.error("Error in converting dtos to entities", e);
            return null;
        }
    }




}
