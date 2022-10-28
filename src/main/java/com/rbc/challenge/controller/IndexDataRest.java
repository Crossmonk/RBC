package com.rbc.challenge.controller;


import com.rbc.challenge.model.entity.IndexData;
import com.rbc.challenge.service.implementation.IndexDataImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * RestApi endpoints definition
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/index/data/set")
public class IndexDataRest {

    private final IndexDataImp service;

    /**
     * Get endpoint following best practices to fetch data from RestApi.
     *
     * @return Flux stream with multiple IndexData.
     */
    @GetMapping
    public Flux<IndexData> getAllDataByStockTicket(@RequestParam("stockTicket") String stockTicket) {
        return service.getByStockTicket(stockTicket);
    }

}
