package com.rbc.challenge.mapper;

import com.rbc.challenge.model.dto.IndexData;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import reactor.core.publisher.Flux;

/**
 * IndexData Struct Mapper
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IndexDataMap {
    /**
     * Map Entity into DTO.
     *
     * @return DTO with Entity information.
     */
    IndexData toDto(com.rbc.challenge.model.entity.IndexData source);

    /**
     * Map Entity list into DTO list.
     *
     * @return DTO with Entity information.
     */
    default Flux<IndexData> toDto(Flux<com.rbc.challenge.model.entity.IndexData> source) {
        return source.map(this::toDto);
    }

    /**
     * Map DTO into Entity object.
     *
     * @return Entity with DTO info.
     */
    com.rbc.challenge.model.entity.IndexData toEntity(IndexData source);

    /**
     * Map DTO list into list of Entity object.
     *
     * @return Entity with DTO info.
     */
    default Flux<com.rbc.challenge.model.entity.IndexData> toEntity(Flux<IndexData> source) {
        return source.map(this::toEntity);
    }


}
