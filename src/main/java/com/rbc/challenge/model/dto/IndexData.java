package com.rbc.challenge.model.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * IndexData DTO the purpose of this class is not expose the Entity itself in the Rest Api.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndexData {
    @NotNull
    @CsvBindByName(column = "stock")
    private String stockSymbol;

    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @CsvBindByName(column = "date")
    @CsvDate(value = "MM/dd/yyyy")
    private Date operationDate;

    @Min(1)
    @Max(2)
    @CsvBindByName
    @Digits(integer = 1, fraction = 0)
    private Integer quarter;

    @CsvBindByName
    private String open;
    @CsvBindByName
    private String high;
    @CsvBindByName
    private String low;
    @CsvBindByName
    private String close;
    @CsvBindByName
    private Long volume;
    @CsvBindByName(column = "percent_change_price")
    private Double percentChangePrice;

    @CsvBindByName(column = "percent_change_volume_over_last_wk")
    private Double percentChangeVolumeOverLastWeek;
    @CsvBindByName(column = "previous_weeks_volume")
    private Long previousWeeksVolume;

    @CsvBindByName(column = "next_weeks_open")
    private String nextWeeksOpen;
    @CsvBindByName(column = "next_weeks_close")
    private String nextWeeksClose;
    @CsvBindByName(column = "percent_change_next_weeks_price")
    private Double percentChangeNextWeeksPrice;
    @CsvBindByName(column = "days_to_next_dividend")
    private Integer daysToNextDividend;
    @CsvBindByName(column = "percent_return_next_dividend")
    private Double percentReturnNextDividend;
}
