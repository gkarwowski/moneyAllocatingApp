package pl.com.karwowskigrzegorz;

import pl.com.karwowskigrzegorz.constant.FundType;
import java.util.*;

/**
 * Created by gkarwows on 2018-09-04
 */

class Investment {

    private Map<Fund, Double> investMap = new LinkedHashMap<>();

     double investmentCheck(Map<Fund, Double> investMap) {
        double restFromInvestment = 0;
        double investment;
        double fraction;
        Set<FundType> fractionType = new LinkedHashSet<>();
        for (Fund fund : investMap.keySet()) {
            investment = investMap.get(fund);
            fraction = Math.abs(investment - Math.floor(investment));
            investMap.put(fund, investMap.get(fund) - fraction);
            if (fraction != 0) {
                fractionType.add(fund.getType());
            }
            restFromInvestment += fraction;
        }

        if (fractionType.size() > 1) {
            return restFromInvestment;
        } else if (fractionType.size() == 1) {
            for (Fund fund : investMap.keySet()) {
                if (fractionType.contains(fund.getType())){
                    investMap.put(fund, investMap.get(fund) + Math.round(restFromInvestment));
                    break;
                }
            }
            return 0;
        }

        return restFromInvestment;
    }

     Map<Fund, Double> getInvestMap() {
        return investMap;
    }
}
