package com.formationOfAnInvestmentPortfolio.controller.api;

import com.formationOfAnInvestmentPortfolio.persistence.entity.*;
import com.formationOfAnInvestmentPortfolio.persistence.entity.vue.Pers;
import com.formationOfAnInvestmentPortfolio.service.logic.ApiService;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.domain.Coins.CoinList;
import com.litesoftwares.coingecko.domain.Coins.MarketChart;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/coins")
public class CoinController {

    private final CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
    private ApiService apiService = new ApiService();

    @GetMapping("/popular")
    List<CoinList> getPopularCoins()throws IOException {
        apiService.addData();
        return apiService.getPopularCoins();
    }

//    -*-*-*-
    @PostMapping("/portfolios")
    Pers getPortfolios(@RequestBody Map<String, List<String>> params){
        return apiService.getPortfolios(params.get("selected"));
    }

//    @PostMapping("/portfolios")
//    void getPortfolios(@RequestBody Map<String, List<String>> params){
//        System.out.println("try to understand what's going on");
//        System.out.println(params);
//        System.out.println(params.get("selected"));
//        apiService.getPortfolios(params.get("selected"));
//    }


    @GetMapping("/portfolioList")
    List<Portfolio> getPortfolioList(){
        return apiService.getPortfolioList();
    }

    @GetMapping("/")
    void setData() throws IOException {
        apiService.addData();
    }

    @GetMapping("/select")
    List<CoinList> getSelectedCoin() {
        return apiService.getSelectedCoins();
    }

    @GetMapping("/history")
    List<MarketChart> getHistory(){
        return apiService.getHistory();
    }

    @GetMapping("/risks")
    List<Double> getRisk(){
        return apiService.getRisk();
    }

    @GetMapping("/expectReturn")
    List<Double> getExpReturnCoins(){
        return apiService.getExpReturnCoins();
    }

    @GetMapping("/assetReturn")
    List<FuzzyNumber> getAssetReturn(){
        return apiService.getAssetReturn();
    }

    @GetMapping("/cov")
    List<List<Double>> getCov(){
        return apiService.getCov();
    }

    @GetMapping("/portfolio")
    Portfolio getPortfolio(){
        return apiService.getPortfolio();
    }

    @GetMapping("/optimization")
    List<List<Double>> doOptimization(){
        return apiService.doOptimization();
    }
}
