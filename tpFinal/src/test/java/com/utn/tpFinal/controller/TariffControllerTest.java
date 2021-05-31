package com.utn.tpFinal.controller;

import com.utn.tpFinal.AbstractController;
import com.utn.tpFinal.service.TariffService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TariffController.class)  //especificamos sobre que clase va a trabajar asi se ejecuta cuando corro los test
public class TariffControllerTest extends AbstractController {

    @MockBean
    TariffService tariffService;


    //Result Action -> obj que permite el resulta de la interaccion entre el contexto
// generado y la accion que estamos haciendo

    @Test
    public void getAll() throws Exception {  //given controller -> levanta el contexto del metodo
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders //inicializar mock
                .get("/tariff")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(), resultActions.andReturn().getResponse().getStatus());
    }



}