package com.rbc.challenge.dao;

import com.rbc.challenge.model.entity.IndexData;
import com.rbc.challenge.model.entity.IndexDataPK;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * IndexData Repository to interact with database. (Data Access Object)
 */
@Async
@Repository
public interface IndexDataRepository extends ReactiveCrudRepository<IndexData, IndexDataPK> {

    /**
     * Find all IndexData by Stock
     *
     * @param stockSymbol Stock abbreviation eg. AA
     * @return Flux stream with IndexData rows.
     */
    @Query("SELECT i.* FROM index_data i WHERE i.stock_symbol = :stockSymbol")
    Flux<IndexData> findAllByStockSymbol(String stockSymbol);

}
