package com.formationOfAnInvestmentPortfolio.service.logic;

import com.formationOfAnInvestmentPortfolio.persistence.entity.*;
import com.formationOfAnInvestmentPortfolio.persistence.entity.vue.Coins;
import com.formationOfAnInvestmentPortfolio.persistence.entity.vue.Pers;
import com.formationOfAnInvestmentPortfolio.persistence.entity.vue.Portfolios;
import com.formationOfAnInvestmentPortfolio.persistence.entity.vue.Share;
import com.formationOfAnInvestmentPortfolio.service.optimization.Optimization;
import com.formationOfAnInvestmentPortfolio.service.optimization.SPEA;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.domain.Coins.CoinList;
import com.litesoftwares.coingecko.domain.Coins.MarketChart;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ApiService {

    private CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
    private Optimization optimization;

    private List<CoinList> allCoins = new ArrayList<>();
    private Portfolio portfolio;

    private File file = new File("src/main/resources/popularCoinsName.txt");
    private List<String> symbolsPopularCoins = new ArrayList<>();
    private List<CoinList> popularCoins = new ArrayList<>();

    private List<CoinList> selectedCoins = new ArrayList<>();
    private Integer period = 1;

    private List<Coin> coins = new ArrayList<>();
    private List<Portfolio> portfolioList = new ArrayList<>();


    public void addData() throws IOException {
        selectCoins(); // обращается ко всем данным, считывает файл, заполняет popularCoins
    }



    public void selectCoins() throws IOException {
        if (allCoins.size() == 0) {
            allCoins();
        }

        if (symbolsPopularCoins.size() == 0) {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null){
                symbolsPopularCoins.add(line);
                line = reader.readLine();
            }
        }

        if (popularCoins.size() == 0) {
            for (CoinList coin : allCoins) {
                for (String symbol : symbolsPopularCoins) {
                    if (coin.getName().equals(symbol)){
                        popularCoins.add(coin);
                    }
                }
            }
        }
    }

    public void allCoins(){
        allCoins = client.getCoinList();
    }

    public List<CoinList> getPopularCoins() throws IOException {
        return popularCoins;
    }

    public void setSelectedCoins(List<String> listId) {
        for (CoinList coin: popularCoins) {
            for (String id: listId) {
                if (coin.getId().equals(id)){
                    selectedCoins.add(coin);
                }
            }
        }
    }

    public void setHistory() {
        for (CoinList coinList : selectedCoins) {
            Coin coin = new Coin(coinList);
//            coin.setPriceList(client.getCoinMarketChartById(coinList.getId(), "rub", 365));
            coin.setPriceList(client.getCoinMarketChartById(coinList.getId(), "rub", 365));
            coins.add(coin);
        }
    }

    public void setPortfolio(){
        portfolio = new Portfolio(coins);
    }

    public List<List<Double>> doOptimization(){
        optimization = new SPEA(getExpReturnCoins(), getCov());
        return optimization.count();
    }

//    -*-*-*-
    public Pers getPortfolios(List<String> listId){
        selectedCoins.clear();
        portfolioList.clear();
        coins.clear();
        setSelectedCoins(listId);
        setHistory();
        setPortfolio();
        setPortfolioList();

        return setPrPr();
    }

//    public void testOfMyStupid(){
//        List<List<Double>> co = new ArrayList<>();
//
//        List<Double> ccc = new ArrayList<>();
//        ccc.add(0.11236862384715644);
//        ccc.add(0.12052826143388778);
//        ccc.add(0.05083579536025625);
//        co.add(ccc);
//        ccc.clear();
//
//        ccc.add(0.12052826143388778);
//        ccc.add(0.1817140402229305);
//        ccc.add(-0.0097875149746843);
//        co.add(ccc);
//        ccc.clear();
//
//        ccc.add(0.05083579536025625);
//        ccc.add(-0.009718751497468453);
//        ccc.add(0.10207266050259277);
//        co.add(ccc);
//        ccc.clear();
//
//        List<Double> expCoins = new ArrayList<>();
//
//        expCoins.add(0.12714355646837158);
//        expCoins.add(0.30174576960868404);
//        expCoins.add(-0.1444388214061943);
//
//        optimization = new SPEA(expCoins, co);
//        List<List<Double>> l_o = optimization.count();
//        System.out.println("print result");
//        for (List<Double> ll: l_o) {
//            System.out.println(ll);
//        }
//    }

    public void testOfMyStupid(){
        List<List<Double>> co = new ArrayList<>();

        List<Double> ccc = new ArrayList<>();
        ccc.add(0.000961);
        ccc.add(-0.000798);
        ccc.add(0.000360);
        co.add(ccc);

        List<Double> ccc2 = new ArrayList<>();
        ccc2.add(-0.000798);
        ccc2.add(0.010970);
        ccc2.add(-0.001418);
        co.add(ccc2);

        List<Double> ccc3 = new ArrayList<>();
        ccc3.add(0.000360);
        ccc3.add(-0.001418);
        ccc3.add(0.003653);
        co.add(ccc3);

        List<Double> expCoins = new ArrayList<>();

        expCoins.add(3.02);
        expCoins.add(3.7);
        expCoins.add(2.37);

        optimization = new SPEA(expCoins, co);
        List<List<Double>> l_o = optimization.count();
        System.out.println("print result");
        for (List<Double> ll: l_o) {
            System.out.println(ll);
        }
    }

//    public void getPortfolios(List<String> listId){
//        selectedCoins.clear();
//        portfolioList.clear();
//        coins.clear();
//        setSelectedCoins(listId);
//        setHistory();
//        setPortfolio();
////        testOfMyStupid();
//        setPortfolioList();
////        return setPrPr();
//    }

//    вычисление оптимизированных портфелей
    public void setPortfolioList(){
        if (portfolioList.size() == 0) {
            List<List<Double>> optim = doOptimization();  // - говно не работающее по какой-то неведанной причине. Фу таким быть
            for (List<Double> list : optim) {
                Portfolio p = new Portfolio(coins);
                p.countParams(list);
                portfolioList.add(p);
            }
        }
    }

//    пересобирает вид данных в необходимый вид json (необходимо переписать!)
    public Pers setPrPr(){
        List<Coins> coins = new ArrayList<>();
        int count =0;
        for (Coin coin:portfolio.getAssets()) {
            if (count< selectedCoins.size()) {
                coins.add(new Coins(coin.getCoinList().getId(), coin.getRisk(), coin.getExpectedReturn()));
                count++;
            }
        }
        List<Portfolios> portfoliosList = new ArrayList<>();
        for (Portfolio portfolio: portfolioList) {
            List<Share> share = new ArrayList<>();
            for (Shares shares: portfolio.getShare()) {
                share.add(new Share(shares.getCoin().getCoinList().getId(),shares.getCount()));
            }
            Portfolios portfolios = new Portfolios(portfolio.getRisk(), portfolio.getExpectedReturn(), share);
            portfoliosList.add(portfolios);
        }
        Pers persList = new Pers(coins, portfoliosList);
        return persList;
    }


    public List<Portfolio> getPortfolioList(){
        setPortfolioList();
        return portfolioList;
    }

    public List<CoinList> getSelectedCoins() {
        return selectedCoins;
    }

    public List<MarketChart> getHistory(){
        List<MarketChart> list = new ArrayList<>();
        for (Coin coin: coins) {
            list.add(coin.getPriceList());
        }
        return list;
    }

    public List<Double> getRisk(){
        List<Double> list = new ArrayList<>();
        for (Coin coin: coins){
            list.add(coin.getRisk());
        }
        return list;
    }

    public List<FuzzyNumber> getAssetReturn(){
        List<FuzzyNumber> list = new ArrayList<>();
        for (Coin coin: coins) {
            list.add(coin.getAssetReturn());
        }
        return list;
    }

    public Portfolio getPortfolio(){
        return portfolio;
    }

    public List<List<Double>> getCov(){
        return portfolio.getCov();
    }

    public List<Double> getExpReturnCoins() {
        return portfolio.getExpReturnCoins();
    }
}
