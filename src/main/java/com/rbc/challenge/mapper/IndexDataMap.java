package com.rbc.challenge.mapper;

import com.rbc.challenge.model.dto.IndexData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

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

    @Mapping(source = "stockDateComposePk.operationDate", target = "operationDate")
    @Mapping(source = "stockDateComposePk.stockSymbol", target = "stockSymbol")
    @Mapping(source = "open", target = "open", numberFormat = "\u00A4##.##")
    @Mapping(source = "high", target = "high", numberFormat = "$##.##")
    @Mapping(source = "low", target = "low", numberFormat = "$##.##")
    @Mapping(source = "close", target = "close", numberFormat = "$##.##")
    @Mapping(source = "nextWeeksOpen", target = "nextWeeksOpen", numberFormat = "$##.##")
    @Mapping(source = "nextWeeksClose", target = "nextWeeksClose", numberFormat = "$##.##")
    @Mapping(source = "percentChangeVolumeOverLastWeek", target = "percentChangeVolumeOverLastWeek", defaultValue = "0D")
    @Mapping(source = "previousWeeksVolume", target = "previousWeeksVolume", defaultValue = "0L")
    IndexData toDto(com.rbc.challenge.model.entity.IndexData source);

    /**
     * Map Entity list into DTO list.
     *
     * @return DTO with Entity information.
     */
    List<IndexData> toDto(List<com.rbc.challenge.model.entity.IndexData> source);

    /**
     * Map DTO into Entity object.
     *
     * @return Entity with DTO info.
     */
    @Mapping(source = "operationDate", target = "stockDateComposePk.operationDate")
    @Mapping(source = "stockSymbol", target = "stockDateComposePk.stockSymbol")
    @Mapping(source = "open", target = "open", numberFormat = "\u00A4##.##")
    @Mapping(source = "high", target = "high", numberFormat = "\u00A4##.##")
    @Mapping(source = "low", target = "low", numberFormat = "\u00A4##.##")
    @Mapping(source = "close", target = "close", numberFormat = "\u00A4##.##")
    @Mapping(source = "nextWeeksOpen", target = "nextWeeksOpen", numberFormat = "\u00A4##.##")
    @Mapping(source = "nextWeeksClose", target = "nextWeeksClose", numberFormat = "\u00A4##.##")
    @Mapping(source = "percentChangeVolumeOverLastWeek", target = "percentChangeVolumeOverLastWeek", defaultValue = "0D")
    @Mapping(source = "previousWeeksVolume", target = "previousWeeksVolume", defaultValue = "0L")
    com.rbc.challenge.model.entity.IndexData toEntity(IndexData source);

    /**
     * Map DTO list into list of Entity object.
     *
     * @return Entity with DTO info.
     */
    List<com.rbc.challenge.model.entity.IndexData> toEntity(List<IndexData> source);


}
