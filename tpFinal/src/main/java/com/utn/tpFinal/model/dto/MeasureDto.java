package com.utn.tpFinal.model.dto;

import com.utn.tpFinal.model.Measure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeasureDto {

    float value;
    String date;

    String password;
    String serialNumber;

    public static Measure from(MeasureDto measureDto){
        return Measure.builder()
                .total(measureDto.getValue())
                .date(Date.valueOf(measureDto.getDate()))
                .build();
    }

}