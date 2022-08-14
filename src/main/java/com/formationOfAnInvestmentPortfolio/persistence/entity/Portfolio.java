package com.formationOfAnInvestmentPortfolio.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Portfolio {

    private List<Coin> assets;

    private List<List<Double>> cov = new ArrayList<>();
    private List<Double> expReturnCoins = new ArrayList<>();

    private Double risk;
    private Double expectedReturn;
    private List<Shares> share = new ArrayList<>();

    public Portfolio(List<Coin> coinList) {
        this.assets = coinList;
        countCov();
        for (Coin coin:assets) {
            expReturnCoins.add(coin.getExpectedReturn());
        }
    }

    private void countCov(){
        for (Coin coin1: assets) {
            List<Double> list = new ArrayList<>();
            for (Coin coin2: assets) {
                Cov c = new Cov(coin1, coin2);
                list.add(c.getCov());
            }
            cov.add(list);
        }
    }

    public void countParams(List<Double> listCount){
        int i = 0;
        for (Coin coin: assets) {
            this.share.add(new Shares(coin, listCount.get(i)));
            i++;
        }
        countRisk();
        countExpectReturn();
    }

//
    public void countExpectReturn(){
        double count = 0;
        for (Shares coinCount: share) {
            count += coinCount.getCoin().getExpectedReturn()*coinCount.getCount();
        }
        this.expectedReturn = count;
    }

    public void countRisk(){
        double count = 0;
        for (int i = 0; i < share.size(); i++) {
            for (int j = 0; j < share.size(); j++) {
                count += cov.get(i).get(j)*share.get(i).getCount()*share.get(j).getCount();
            }
        }
        this.risk = count;
    }
}
