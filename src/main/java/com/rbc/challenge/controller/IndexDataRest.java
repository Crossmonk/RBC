package com.rbc.challenge.controller;


import com.rbc.challenge.model.dto.IndexData;
import com.rbc.challenge.service.implementation.IndexDataRuleImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * RestApi endpoints definition
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/index/data/sets")
public class IndexDataRest {

    private final IndexDataRuleImp service;

    /**
     * Get endpoint following best practices to fetch data from RestApi.
     *
     * @return List with multiple IndexData.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IndexData> getAllDataByStockTicket(@RequestParam("stockTicket") String stockTicket) {
        return service.getByStockTicket(stockTicket);
    }

    /**
     * Add a new IndexData row.
     *
     * @return Created IndexData information.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IndexData addNewRecord(@RequestBody IndexData data) {
        return service.addIndexData(data);
    }

    /**
     * CSV File upload endpoint for bulk loading.
     *
     * @param file CSV File
     */
    @PostMapping(value = "/bulk/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void bulkAdd(@RequestPart(value = "upFile") MultipartFile file) {
        service.bulkAdd(file);
    }

}
