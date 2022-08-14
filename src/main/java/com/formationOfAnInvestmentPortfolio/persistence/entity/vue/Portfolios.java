package com.formationOfAnInvestmentPortfolio.persistence.entity.vue;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Portfolios {
    Double risk;
    Double expectedReturn;
    List<Share> share;

    public Portfolios(Double risk, Double expectedReturn, List<Share> share) {
        this.risk = risk;
        this.expectedReturn = expectedReturn;
        this.share = share;
    }
}
