package pl.com.karwowskigrzegorz;

//import java.util.;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by gkarwows on 2018-09-04
 */
class Investment {

    private Map<Fund, Double> investMap = new LinkedHashMap<>();

    /**
     * Investment check double.
     *
     * @return the double
     */
    double investmentCheck() {
        double restFromInvestment = 0;
        double investment;
        double fraction;
        Set<String> fractionType = new LinkedHashSet<>();
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
                if (fractionType.contains(fund.getType())) {
                    investMap.put(fund, investMap.get(fund) + Math.round(restFromInvestment));
                    break;
                }
            }
            return 0;
        }

        return restFromInvestment;
    }

    /**
     * Gets invest map.
     *
     * @return the invest map
     */
    Map<Fund, Double> getInvestMap() {
        return investMap;
    }
}
