package com.weverton.rest_with_SpringBoot.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectMapper {

    //esse cara mapeia de entidade para dto e vice-versa.
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    //primeiro metodo: vai fazer parse de entidade para DTO e de DTO para entidade
    //recebbo O origin do tipo Person e converto para PersonDTO.class
    public static <O, D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }

    //segundo metodo: mema ideia mas temos uma lista
    //destinationObjects: lista temporária para retornar
    //iteramos sobre os objetos originais
    //convertemos um a um pro destino
    //e depois retornamos esssa lista.
    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination){

        List<D> destinationObjects = new ArrayList<D>();

        for (Object o : origin){
            destinationObjects.add(mapper.map(o, destination));
        }

        return destinationObjects;
    }
}
