package com.utn.tpFinal.controller;

import com.utn.tpFinal.UTILS_TESTCONSTANTS;
import com.utn.tpFinal.exception.EnergyMeterNotExists;
import com.utn.tpFinal.exception.NoContentException;
import com.utn.tpFinal.exception.ResidenceNotDefined;
import com.utn.tpFinal.model.EnergyMeter;
import com.utn.tpFinal.model.Tariff;
import com.utn.tpFinal.model.dto.EnergyMeterDto;
import com.utn.tpFinal.model.dto.ResidenceDto;
import com.utn.tpFinal.model.dto.TariffDto;
import com.utn.tpFinal.service.BillService;
import com.utn.tpFinal.service.ClientService;
import com.utn.tpFinal.service.EnergyMeterService;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnergyMeterControllerTest {
     private final Integer PAGE=0;
    private final Integer SIZE=10;
    private final Integer IDMETER=1;
    EnergyMeterService energyMeterService;
    EnergyMeterController energyMeterController;

    @Before
    public void setUp(){
        energyMeterService = mock(EnergyMeterService.class);
        energyMeterController = new EnergyMeterController(energyMeterService);
    }
    @Test
    public void getAllEnergy_Test200()  {
        //give

        Specification<EnergyMeter> specification = mock(Specification.class);

        List<EnergyMeterDto> energyMeterDtos = UTILS_TESTCONSTANTS.getEnergyMeterDTO_List();

        List<Sort.Order> orders =UTILS_TESTCONSTANTS.getOrders("id","serialNumber");


        Page<EnergyMeterDto> mockedPage = mock(Page.class);

        when(mockedPage.getContent()).thenReturn(energyMeterDtos);
        when(mockedPage.getTotalElements()).thenReturn(Long.valueOf(energyMeterDtos.size()));
        when(mockedPage.getTotalPages()).thenReturn(1);
        when(energyMeterService.getAll(specification,PAGE,SIZE,orders)).thenReturn(mockedPage);

        //then
        ResponseEntity<List<EnergyMeterDto>> response = energyMeterController.getAll(PAGE,SIZE,"id","serialNumber",specification);

        //assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Integer.parseInt(response.getHeaders().get("X-Total-Elements").get(0)));
        assertEquals(1, Integer.parseInt(response.getHeaders().get("X-Total-Pages").get(0)));
        assertEquals("A1", response.getBody().get(0).getSerialNumber());

    }
    @Test
    public void getAllEnergy_Test204(){
        //give

        Specification<EnergyMeter> specification = mock(Specification.class);
        List<Sort.Order> orders =UTILS_TESTCONSTANTS.getOrders("id","serialNumber");

        Page<EnergyMeterDto> mockedPage =Page.empty();

        when(energyMeterService.getAll(specification,PAGE,SIZE,orders)).thenReturn(mockedPage);
        //then
        ResponseEntity<List<EnergyMeterDto>> response = energyMeterController.getAll(PAGE,SIZE,"id","serialNumber",specification);
        //assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    @Test
    public void deleteEnergyMeterById_Test200() throws EnergyMeterNotExists {


        ResponseEntity response = energyMeterController.deleteEnergyMeterById(IDMETER);
        //assert
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    @Test
    public void  getResidenceByEnergyMeterId_Test200() throws EnergyMeterNotExists, ResidenceNotDefined {
        ResidenceDto residenceDto= UTILS_TESTCONSTANTS.getResidendesDTO_List().get(0);

        when(energyMeterService.getResidenceByEnergyMeterId(IDMETER)).thenReturn(residenceDto);
        //then
        ResponseEntity<ResidenceDto> response = energyMeterController.getResidenceByEnergyMeterId(IDMETER);
        //assert
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("calle1",response.getBody().getStreet());
    }

}