package com.utn.tpFinal.controller;

import com.utn.tpFinal.util.PostResponse;
import com.utn.tpFinal.model.Residence;
import com.utn.tpFinal.service.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residence")
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;

    @PostMapping
    public PostResponse addResidence(@RequestBody Residence residence){

       return residenceService.addResidence(residence);
    }

    @GetMapping
    public List<Residence> getAll(@RequestParam(required = false) String street) {
        return residenceService.getAll(street);
    }

    @PutMapping("/{idResidence}/energyMeter/{idEnergyMeter}")
    public void addEnergyMeterToResidence(@PathVariable Integer idResidence,@PathVariable Integer idEnergyMeter ){
        residenceService.addEnergyMeterToResidence(idResidence,idEnergyMeter);
    }

    @PutMapping("/{idResidence}/tariff/{idTariff}")
    public void addTariffToResidence(@PathVariable Integer idResidence,@PathVariable String idTariff ){
        residenceService.addTariffToResidence(idResidence,idTariff);
    }
    @DeleteMapping("/{idResidence}/remove")
    public PostResponse removeResidenceById(@PathVariable Integer idResidence){
        return residenceService.removeResidenceById(idResidence);
    }

}
