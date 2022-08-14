import com.formationOfAnInvestmentPortfolio.service.optimization.SPEA;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class testSPEA {

    @Test
    public void countSPEA(){
        List<Double> er = new ArrayList<>();
        er.add(3.02); er.add(3.7); er.add(2.37);

        List<List<Double>> cov = new ArrayList<>();
        List<Double> l1 = new ArrayList<>();
        l1.add(0.000961); l1.add(-0.000798); l1.add(0.000360);
        List<Double> l2 = new ArrayList<>();
        l2.add(-0.000798); l2.add(0.010970); l2.add(-0.001418);
        List<Double> l3 = new ArrayList<>();
        l3.add(0.000360); l3.add(-0.001418); l3.add(0.003653);
        cov.add(l1); cov.add(l2); cov.add(l3);

        List<List<Double>> res = new ArrayList<>();
        List<Double> r1 = new ArrayList<>();
        r1.add(0.8181818181818182); r1.add(-0.1590909090909091); r1.add(0.022727272727272728);
        List<Double> r2 = new ArrayList<>();
        r2.add(0.125); r2.add(0.875); r2.add(0.0);
        List<Double> r3 = new ArrayList<>();
        r3.add(0.8421052631578947); r3.add(0.15789473684210525); r3.add(0.0);
        res.add(r1); res.add(r2); res.add(r3);

        SPEA spea = new SPEA(er, cov);
        List<List<Double>> resSPEA = spea.count();

        assertEquals(res.get(0), resSPEA.get(0));
        assertEquals(res.get(1), resSPEA.get(1));
        assertEquals(res.get(2), resSPEA.get(2));
    }

//[3.02, 3.7, 2.37],
//        [[0.000961, -0.000798, 0.000360], [-0.000798, 0.010970, -0.001418], [0.000360, -0.001418, 0.003653]])
//
//        [[0.8181818181818182, 0.1590909090909091, 0.022727272727272728], [0.125, 0.875, 0.0], [0.8421052631578947, 0.15789473684210525, 0.0], [0.46835443037974683, 0.5316455696202531, 0.0], [0.8571428571428571, 0.14285714285714285, 0.0], [0.6923076923076923, 0.3076923076923077, 0.0], [0.0, 1.0, 0.0], [0.7037037037037037, 0.25925925925925924, 0.037037037037037035], [0.5454545454545454, 0.4431818181818182, 0.011363636363636364], [0.6428571428571429, 0.35714285714285715, 0.0], [0.36363636363636365, 0.6363636363636364, 0.0], [0.16666666666666666, 0.8333333333333334, 0.0], [0.5967741935483871, 0.4032258064516129, 0.0], [0.782608695652174, 0.21739130434782608, 0.0], [0.09523809523809523, 0.9047619047619048, 0.0], [0.45569620253164556, 0.5443037974683544, 0.0], [0.6666666666666666, 0.3333333333333333, 0.0], [0.02564102564102564, 0.9743589743589743, 0.0], [0.3, 0.7, 0.0], [0.4222222222222222, 0.5777777777777777, 0.0]]

}
