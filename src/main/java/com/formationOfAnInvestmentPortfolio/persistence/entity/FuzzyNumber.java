package com.formationOfAnInvestmentPortfolio.persistence.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuzzyNumber {

    private Double ra;
    private Double rb;
    private Double rc;

    public FuzzyNumber(Double ra, Double rb, Double rc) {
        this.ra = ra;
        this.rb = rb;
        this.rc = rc;
    }
}
