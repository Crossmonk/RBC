package com.rbc.challenge.service;

import com.rbc.challenge.model.dto.IndexData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Business rules definition
 */
public interface IndexDataRule {
    /**
     * Find all IndexData.
     *
     * @return List with all the filtered data from IndexData.
     */
    List<IndexData> getByStockTicket(String stockTicket);

    /**
     * Create new record for IndexData.
     *
     * @param indexData IndexData transfer object to be saved in database.
     */
    IndexData addIndexData(IndexData indexData);

    /**
     * Add all records in multipart CSV file.
     *
     * @param file Multipart file with records
     */
    Iterable<com.rbc.challenge.model.entity.IndexData> bulkAdd(MultipartFile file);
}
