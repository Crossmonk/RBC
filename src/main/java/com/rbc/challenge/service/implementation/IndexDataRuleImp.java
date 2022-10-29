package com.rbc.challenge.service.implementation;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rbc.challenge.dao.IndexDataRepository;
import com.rbc.challenge.mapper.IndexDataMap;
import com.rbc.challenge.model.dto.IndexData;
import com.rbc.challenge.service.IndexDataRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Business rules implementation.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class IndexDataRuleImp implements IndexDataRule {

    private final IndexDataMap map;
    private final IndexDataRepository indexDataRepository;

    @Override
    public List<IndexData> getByStockTicket(String stockTicket) {
        List<IndexData> indexData = map.toDto(indexDataRepository.findAllByStockSymbol(stockTicket));

        if (indexData.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,
                    "No records found for filter.");
        }

        return indexData;
    }

    @Override
    public IndexData addIndexData(IndexData indexData) {
        try {
            com.rbc.challenge.model.entity.IndexData entity = map.toEntity(indexData);
            return map.toDto(indexDataRepository.save(entity));
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "IndexData - Data integrity violation with data provided");
        }

    }

    @Override
    public Iterable<com.rbc.challenge.model.entity.IndexData> bulkAdd(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please upload a file.");
        } else {
            try (Reader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                CsvToBean<IndexData> csvToBean = new CsvToBeanBuilder<IndexData>(fileReader)
                        .withType(IndexData.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                //FOR BULK UPLOAD I WOULD EVALUATE OTHER APPROACHES.
                List<IndexData> dtoList = csvToBean.parse();
                return indexDataRepository.saveAll(map.toEntity(dtoList));
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "I/O Error processing file.");
            }
        }
    }
}
