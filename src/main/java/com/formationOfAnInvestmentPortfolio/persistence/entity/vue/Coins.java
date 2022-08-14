package com.formationOfAnInvestmentPortfolio.persistence.entity.vue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coins {
    private String id;
    private Double risk;
    private Double expectedReturn;

    public Coins(String id, Double risk, Double expectedReturn) {
        this.id = id;
        this.risk = risk;
        this.expectedReturn = expectedReturn;
    }
}
