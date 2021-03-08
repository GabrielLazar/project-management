package com.gabriellazar.projectmanagement.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;

public class MapperUtil {

    private ModelMapper modelMapper;

    @Autowired
    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    <T> T convertToEntity(Object objectToBeConverted,T convertedObject){
      return   modelMapper.map(objectToBeConverted,(Type) convertedObject.getClass());
    }
}
