package com.rbc.challenge.service;

import com.rbc.challenge.model.entity.IndexData;
import reactor.core.publisher.Flux;

/**
 * Business rules definition
 */
public interface IndexDataRules {
    /**
     * Find all IndexData.
     *
     * @return Flux with all the data from IndexData
     */
    Flux<IndexData> getByStockTicket(String stockTicket);
}
