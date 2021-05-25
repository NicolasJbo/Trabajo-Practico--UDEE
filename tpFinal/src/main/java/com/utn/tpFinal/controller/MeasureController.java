package com.utn.tpFinal.controller;

import com.utn.tpFinal.exception.EnergyMeterNotExists;
import com.utn.tpFinal.exception.IncorrectPasswordException;
import com.utn.tpFinal.model.Measure;
import com.utn.tpFinal.model.dto.MeasureDto;
import com.utn.tpFinal.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurements")
public class MeasureController {

    @Autowired
    MeasureService measureService;

    @PostMapping()
    public void  addMeasure(@RequestBody MeasureDto dto) throws Exception {
        Measure measure= MeasureDto.from(dto);
        measureService.add(measure,dto.getSerialNumber(),dto.getPassword());

    }
}
