package br.com.zherro.kafkaapi.config.modelmapper;

import org.modelmapper.ModelMapper;

public abstract class UtilDTO<T, E> {

    public E toEntity(ModelMapper modelMapper, Class type){
        return (E) modelMapper.map(this, type);
    }

    public void mergeDTO(ModelMapper modelMapper, T obj, E entity){
        modelMapper.map(obj, entity);
    }

    public T toDTO(ModelMapper modelMapper, E entity, Class type){
        return (T) modelMapper.map(entity, type);
    }
}
