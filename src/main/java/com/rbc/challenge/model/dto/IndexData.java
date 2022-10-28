package com.rbc.challenge.model.dto;

import com.rbc.challenge.model.entity.IndexDataPK;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * IndexData DTO the purpose of this class is not expose the Entity itself in the Rest Api.
 */
@Getter
@Setter
@NoArgsConstructor
public class IndexData {
    private IndexDataPK stockDateComposePk;
    private Integer quarter;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Long volume;
    private Double percentChangePrice;
    private Double percentChangeVolumeOverLastWeek;
    private Long previousWeeksVolume;
    private Double nextWeekOpen;
    private Double nextWeeksClose;
    private Double percentChangeNextWeeksPrice;
    private Integer daysToNextDividend;
    private Double percentReturnNextDividend;
}
