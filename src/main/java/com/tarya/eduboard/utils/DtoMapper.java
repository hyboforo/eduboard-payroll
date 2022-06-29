/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tarya.eduboard.utils;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

/**
 *
 * @author hybof
 */
public class DtoMapper {
    
    private static ModelMapper mapper = new ModelMapper();

    public static <T>T toDto(Object object, Class<T> dtoType) {
        return mapper.map(object, dtoType);
    }

    @SuppressWarnings("unchecked")
    public static <T>List<T> toDtoList(List list, Class<T> dtoType) {
        return (List<T>) list.stream()
                .map(rec -> toDto(rec, dtoType))
                .collect(Collectors.toList());
    }

    public static <T>T fromDto(Object dto, Class<T> type) {
        return mapper.map(dto, type);
    }
    
}
