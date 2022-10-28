package com.rbc.challenge.dao;

import com.rbc.challenge.model.entity.IndexData;
import com.rbc.challenge.model.entity.IndexDataPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * IndexData Repository to interact with database. (Data Access Object)
 */
@Repository
public interface IndexDataRepository extends CrudRepository<IndexData, IndexDataPK> {

    /**
     * Find all IndexData by Stock
     *
     * @param stockSymbol Stock abbreviation eg. AA
     * @return List of IndexData rows.
     */
    @Query("SELECT i FROM IndexData i WHERE i.stockDateComposePk.stockSymbol = :stockSymbol")
    List<IndexData> findAllByStockSymbol(String stockSymbol);

}
