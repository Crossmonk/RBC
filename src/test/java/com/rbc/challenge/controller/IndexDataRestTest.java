package com.rbc.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.challenge.model.dto.IndexData;
import com.rbc.challenge.service.implementation.IndexDataRuleImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IndexDataRest.class)
class IndexDataRestTest {

    private final ObjectMapper jackson = new ObjectMapper();
    @Autowired
    private MockMvc mvc;
    @MockBean
    private IndexDataRuleImp service;


    @Test
    void testGetByStockTicket() throws Exception {
        IndexData dtoData = new IndexData();
        dtoData.setStockSymbol("AA");
        dtoData.setOperationDate(new Date());
        dtoData.setQuarter(1);
        dtoData.setOpen("$00.00");
        dtoData.setClose("$00.00");
        dtoData.setHigh("$00.00");
        dtoData.setLow("$00.00");
        dtoData.setNextWeeksClose("$00.00");
        dtoData.setNextWeeksClose("$00.00");
        dtoData.setVolume(0L);
        dtoData.setPreviousWeeksVolume(0L);
        dtoData.setPercentChangePrice(0.0D);
        dtoData.setPercentChangeNextWeeksPrice(0.0D);
        dtoData.setPercentChangeVolumeOverLastWeek(0.0D);
        dtoData.setPercentReturnNextDividend(0.0D);
        dtoData.setDaysToNextDividend(0);

        List<IndexData> indexData = new ArrayList<>();
        indexData.add(dtoData);


        Mockito.when(service.getByStockTicket(ArgumentMatchers.matches("AA")))
                .thenReturn(indexData);

        //Act
        mvc.perform(MockMvcRequestBuilders.get("/index/data/sets?stockTicket=AA")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stockSymbol").value("AA"));
    }

    @Test
    void testAddNewRecord() throws Exception {
        IndexData dtoData = new IndexData();
        dtoData.setStockSymbol("AA");
        dtoData.setOperationDate(new Date());
        dtoData.setQuarter(1);
        dtoData.setOpen("$00.00");
        dtoData.setClose("$00.00");
        dtoData.setHigh("$00.00");
        dtoData.setLow("$00.00");
        dtoData.setNextWeeksClose("$00.00");
        dtoData.setNextWeeksClose("$00.00");
        dtoData.setVolume(0L);
        dtoData.setPreviousWeeksVolume(0L);
        dtoData.setPercentChangePrice(0.0D);
        dtoData.setPercentChangeNextWeeksPrice(0.0D);
        dtoData.setPercentChangeVolumeOverLastWeek(0.0D);
        dtoData.setPercentReturnNextDividend(0.0D);
        dtoData.setDaysToNextDividend(0);


        Mockito.when(service.addIndexData(ArgumentMatchers.any(IndexData.class)))
                .thenReturn(dtoData);

        //Act
        mvc.perform(MockMvcRequestBuilders.post("/index/data/sets")
                .accept(MediaType.APPLICATION_JSON)
                .content(jackson.writeValueAsString(dtoData))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stockSymbol").value("AA"));
    }

    @Test
    void testBulkAdd_MissingFile() throws Exception {
        IndexData dtoData = new IndexData();
        dtoData.setStockSymbol("AA");
        dtoData.setOperationDate(new Date());
        dtoData.setQuarter(1);
        dtoData.setOpen("$00.00");
        dtoData.setClose("$00.00");
        dtoData.setHigh("$00.00");
        dtoData.setLow("$00.00");
        dtoData.setNextWeeksClose("$00.00");
        dtoData.setNextWeeksClose("$00.00");
        dtoData.setVolume(0L);
        dtoData.setPreviousWeeksVolume(0L);
        dtoData.setPercentChangePrice(0.0D);
        dtoData.setPercentChangeNextWeeksPrice(0.0D);
        dtoData.setPercentChangeVolumeOverLastWeek(0.0D);
        dtoData.setPercentReturnNextDividend(0.0D);
        dtoData.setDaysToNextDividend(0);


        Mockito.doNothing().when(service).bulkAdd(ArgumentMatchers.any());

        //Act
        mvc.perform(MockMvcRequestBuilders.post("/index/data/sets/bulk/csv")
                .accept(MediaType.MULTIPART_FORM_DATA_VALUE)
                .content(jackson.writeValueAsString(dtoData))
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void testBulkAdd() throws Exception {
        InputStream inputFile = getClass().getClassLoader().getResourceAsStream("dow_jones_index.data");

        MockMultipartFile file =
                new MockMultipartFile("upFile", "dow_jones_index.data", "text/csv", inputFile);

        Mockito.doNothing().when(service).bulkAdd(ArgumentMatchers.any());

        //Act
        mvc.perform(MockMvcRequestBuilders.multipart("/index/data/sets/bulk/csv")
                .file(file)
                .accept(MediaType.MULTIPART_FORM_DATA_VALUE)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
