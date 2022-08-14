package com.formationOfAnInvestmentPortfolio.persistence.entity.vue;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Share {
    private String id;
    private Double count;

    public Share(String id, Double count) {
        this.id = id;
        this.count = count;
    }
}
