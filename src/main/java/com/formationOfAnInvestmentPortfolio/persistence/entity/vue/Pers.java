package com.formationOfAnInvestmentPortfolio.persistence.entity.vue;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Pers {
    List<Coins> coins;
    List<Portfolios> portfolios;

    public Pers(List<Coins> coins, List<Portfolios> portfolios) {
        this.coins = coins;
        this.portfolios = portfolios;
    }
}
