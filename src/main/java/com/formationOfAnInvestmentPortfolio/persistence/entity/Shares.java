package com.formationOfAnInvestmentPortfolio.persistence.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shares {

    private Coin coin;
    private Double count;

    public Shares(Coin coin, Double count) {
        this.coin = coin;
        this.count = count;
    }
}
