package pl.com.karwowskigrzegorz;

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
     * Checks if in investment no money fraction parts is stored.
     * If so, it tries to reallocate money between funds to get rid of the fraction parts.
     * If successful, returns 0. If fail eliminates fraction parts and returns it
     *
     * @return the double rest
     */
    double investmentCheck() {
        double restFromInvestment = 0;
        double moneyInvestedInFund;
        double fraction;
        Set<FundType> fractionType = new LinkedHashSet<>();
        for (Fund fund : investMap.keySet()) {
            moneyInvestedInFund = investMap.get(fund);
            fraction = Math.abs(moneyInvestedInFund - Math.floor(moneyInvestedInFund));
            investMap.put(fund, investMap.get(fund) - fraction);
            if (fraction != 0) {
                fractionType.add(fund.getType());
            }
            restFromInvestment += fraction;
        }
        return restFromInvestmentManager(restFromInvestment, fractionType);
    }

    /**
     * Manages fraction parts of money. If fraction parts exist for more than one type of funds it returns cumulative
     * sum of fractions (restFromInvestment). If for only one type of funds it adds this part to first free fund
     * of that type
     *
     * @param restFromInvestment
     * @param fractionType
     * @return the double rest
     */
    private double restFromInvestmentManager(double restFromInvestment, Set<FundType> fractionType) {
        if (fractionType.size() > 1) {
            return restFromInvestment;
        } else if (fractionType.size() == 1) {
            investMap.keySet().stream()
                    .filter(fund -> fractionType.contains(fund.getType()))
                    .findFirst()
                    .ifPresent(fund -> investMap.put(fund, investMap.get(fund) + Math.round(restFromInvestment)));
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
