package com.rbc.challenge.service.implementation;

import com.rbc.challenge.dao.IndexDataRepository;
import com.rbc.challenge.mapper.IndexDataMap;
import com.rbc.challenge.model.entity.IndexData;
import com.rbc.challenge.service.IndexDataRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * Business rules implementation.
 */
@Component
@RequiredArgsConstructor
public class IndexDataImp implements IndexDataRules {

    private final IndexDataMap map;
    private final IndexDataRepository indexDataRepository;

    @Override
    public Flux<IndexData> getByStockTicket(String stockTicket) {
        return indexDataRepository.findAllByStockSymbol(stockTicket);
    }
}
