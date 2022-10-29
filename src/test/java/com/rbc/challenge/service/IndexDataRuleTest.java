package com.rbc.challenge.service;

import com.rbc.challenge.dao.IndexDataRepository;
import com.rbc.challenge.mapper.IndexDataMap;
import com.rbc.challenge.model.dto.IndexData;
import com.rbc.challenge.model.entity.IndexDataPK;
import com.rbc.challenge.service.implementation.IndexDataRuleImp;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class IndexDataRuleTest {

    @Spy
    private final IndexDataMap map = Mappers.getMapper(IndexDataMap.class);
    @InjectMocks
    private IndexDataRuleImp service;
    @Mock
    private IndexDataRepository repository;

    @Test
    void getByStockTicket_NoRecordFound_ThrowsException() {
        Mockito.when(repository.findAllByStockSymbol(ArgumentMatchers.anyString()))
                .thenReturn(new ArrayList<>());

        ResponseStatusException statusException = Assertions.assertThrows(ResponseStatusException.class, () -> {
            service.getByStockTicket("AA");
        });
        Assertions.assertEquals(HttpStatus.NO_CONTENT, statusException.getStatus());
        Assertions.assertEquals("No records found for filter.", statusException.getReason());
    }

    @Test
    void getByStockTicket_RecordsFound_ReturnsList() {
        com.rbc.challenge.model.entity.IndexData testData = new com.rbc.challenge.model.entity.IndexData();
        testData.setStockDateComposePk(new IndexDataPK("AA", new Date()));
        testData.setQuarter(1);
        testData.setOpen(00.00);
        testData.setClose(00.00);
        testData.setHigh(00.00);
        testData.setLow(00.00);
        testData.setNextWeeksClose(00.00);
        testData.setNextWeeksClose(00.00);
        testData.setVolume(0L);
        testData.setPreviousWeeksVolume(0L);
        testData.setPercentChangePrice(0.0D);
        testData.setPercentChangeNextWeeksPrice(0.0D);
        testData.setPercentChangeVolumeOverLastWeek(0.0D);
        testData.setPercentReturnNextDividend(0.0D);
        testData.setDaysToNextDividend(0);

        ArrayList<com.rbc.challenge.model.entity.IndexData> testList = new ArrayList<>();
        testList.add(testData);

        Mockito.when(repository.findAllByStockSymbol(ArgumentMatchers.anyString()))
                .thenReturn(testList);

        List<IndexData> response = service.getByStockTicket("AA");
        Assertions.assertEquals(testList.get(0).getStockDateComposePk().getStockSymbol(), response.get(0).getStockSymbol());
    }

    @Test
    void addIndexData_MapAndSave_ReturnSavedObjectMapped() {
        com.rbc.challenge.model.entity.IndexData testData = new com.rbc.challenge.model.entity.IndexData();
        testData.setStockDateComposePk(new IndexDataPK("AA", new Date()));
        testData.setQuarter(1);
        testData.setOpen(00.00);
        testData.setClose(00.00);
        testData.setHigh(00.00);
        testData.setLow(00.00);
        testData.setNextWeeksClose(00.00);
        testData.setNextWeeksClose(00.00);
        testData.setVolume(0L);
        testData.setPreviousWeeksVolume(0L);
        testData.setPercentChangePrice(0.0D);
        testData.setPercentChangeNextWeeksPrice(0.0D);
        testData.setPercentChangeVolumeOverLastWeek(0.0D);
        testData.setPercentReturnNextDividend(0.0D);
        testData.setDaysToNextDividend(0);

        Mockito.when(repository.save(ArgumentMatchers.any()))
                .thenReturn(testData);

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

        IndexData indexData = service.addIndexData(dtoData);

        Assertions.assertNotEquals(dtoData.hashCode(), indexData.hashCode());
        Assertions.assertEquals(dtoData.getStockSymbol(), indexData.getStockSymbol());
    }

    @Test
    void addIndexData_DataIntegrityException_ReturnControlledException() {
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

        Mockito.when(repository.save(ArgumentMatchers.any()))
                .thenThrow(DataIntegrityViolationException.class);

        ResponseStatusException statusException = Assertions.assertThrows(ResponseStatusException.class, () -> {
            service.addIndexData(dtoData);
        });
        Assertions.assertEquals(HttpStatus.CONFLICT, statusException.getStatus());
        Assertions.assertEquals("IndexData - Data integrity violation with data provided", statusException.getReason());

    }

    @Test
    void bulkAdd_EmptyFile_ThrowException() {
        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);
        Mockito.when(multipartFile.isEmpty()).thenReturn(true);

        ResponseStatusException statusException = Assertions.assertThrows(ResponseStatusException.class, () -> {
            service.bulkAdd(multipartFile);
        });

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, statusException.getStatus());
        Assertions.assertEquals("Please upload a file.", statusException.getReason());
    }

    @Test
    void bulkAdd_IOErrorWithInputStream_ThrowException() throws IOException {
        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);
        Mockito.when(multipartFile.isEmpty()).thenReturn(false);
        Mockito.when(multipartFile.getInputStream()).thenThrow(IOException.class);

        ResponseStatusException statusException = Assertions.assertThrows(ResponseStatusException.class, () -> {
            service.bulkAdd(multipartFile);
        });

        Assertions.assertEquals(HttpStatus.CONFLICT, statusException.getStatus());
        Assertions.assertEquals("I/O Error processing file.", statusException.getReason());
    }

    @Test
    void bulkAdd_NormalProcess_Void() throws IOException {
        InputStream inputFile = getClass().getClassLoader().getResourceAsStream("dow_jones_index.data");
        MockMultipartFile file =
                new MockMultipartFile("upFile", "dow_jones_index.data", "text/csv", inputFile);

        Mockito.when(repository.saveAll(ArgumentMatchers.any()))
                .thenReturn(new ArrayList<>());

        Iterable<com.rbc.challenge.model.entity.IndexData> dataFromFile = service.bulkAdd(file);

        Assertions.assertTrue(IterableUtils.isEmpty(dataFromFile));

    }


}
