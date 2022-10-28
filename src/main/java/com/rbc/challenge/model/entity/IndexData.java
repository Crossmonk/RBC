package com.rbc.challenge.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Object representing one row of the database table IndexData
 */
@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndexData {

    @EmbeddedId
    private IndexDataPK stockDateComposePk;

    @Column
    private Integer quarter;

    @Column
    private Double open;

    @Column
    private Double high;

    @Column
    private Double low;

    @Column
    private Double close;

    @Column
    private Long volume;

    @Column
    private Double percentChangePrice;

    @Column(name = "percent_chagne_volume_over_last_wek")
    private Double percentChangeVolumeOverLastWeek;

    @Column
    private Long previousWeeksVolume;

    @Column(name = "next_week_open")
    private Double nextWeeksOpen;

    @Column
    private Double nextWeeksClose;

    @Column
    private Double percentChangeNextWeeksPrice;

    @Column
    private Integer daysToNextDividend;

    @Column
    private Double percentReturnNextDividend;
}
