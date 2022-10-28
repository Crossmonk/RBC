package com.rbc.challenge.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * Composite PK for IndexData table.
 */
@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class IndexDataPK implements Serializable {

    @Column
    private String stockSymbol;

    @Column
    private Date operationDate;
}
