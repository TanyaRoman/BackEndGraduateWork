package com.formationOfAnInvestmentPortfolio.persistence.entity;

import com.litesoftwares.coingecko.domain.Coins.CoinList;
import com.litesoftwares.coingecko.domain.Coins.MarketChart;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Getter
@Setter
public class Coin {

    private CoinList coinList;

    private MarketChart priceList;
    private List<List<String>> rentabilityList = new ArrayList<>();
    private List<Double> average = new ArrayList<>();

    private FuzzyNumber assetReturn;
    private Double risk;
    private Double expectedReturn;

    public Coin(CoinList coinList) {
        this.coinList = coinList;
    }

    public void setPriceList(MarketChart priceList) {
        this.priceList = priceList;
        countAll();
    }

    public void countAll(){
        if (!priceList.equals(null)) {
            countRentabilityList();
            countAverageMonth();
            countAssetReturn();
            countRisk();
            countExpectedReturn();
        } else {
            System.out.println("ОШИБКА! Заполните первоначально priceList");
        }
    }

    private void countRentabilityList(){
        Double firstPrice = Double.valueOf(priceList.getPrices().get(0).get(1));
        for (List<String> price: priceList.getPrices()) {
            Double r = (Double.valueOf(price.get(1)) - firstPrice) / firstPrice;
            List<String> list = new ArrayList<>();
            list.add(price.get(0));
            list.add(String.valueOf(r));
            rentabilityList.add(list);
        }
    }

    private void countAverageMonth(){
        double count = 0;
        double n =0;
        int t = 30;
        int j = 1;
        for (int i = 0; i < rentabilityList.size(); i++) {
            if (n < t){
                count += Double.valueOf(rentabilityList.get(i).get(1));
                n++;
            } else {
                average.add(count / n);
                n = 0;
                count = 0;
                i = j;
                j++;
            }
        }
    }

    private void countAssetReturn(){
        Collections.sort(average);
        Double ra = average.get(0);
        Double rc = average.get(average.size()-1);
        Double rb;
        if (average.size() % 2 == 1){
            rb = average.get((average.size()-1)/2);
        } else {
            rb = (average.get(average.size()/2) + average.get(average.size()/2 - 1))/2;
        }
        this.assetReturn = new FuzzyNumber(ra, rb, rc);
    }

    private void countRisk(){
        double ra = assetReturn.getRa();
        double rb = assetReturn.getRb();
        double rc = assetReturn.getRc();
        double risk = (Math.pow(rb-ra, 2) + Math.pow(rc-rb, 2))/4 + 2*(ra*(rb-ra) - rc*(rc-rb))/3
                + (Math.pow(ra, 2) + Math.pow(rc, 2))/2 - Math.pow((ra+4*rb+rc)/6, 2)/2;
        setRisk(risk);
    }

    private void countExpectedReturn(){
        expectedReturn = (assetReturn.getRa() + 4*assetReturn.getRb() + assetReturn.getRc()) / 6;
    }
}
