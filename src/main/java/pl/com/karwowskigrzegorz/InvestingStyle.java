package pl.com.karwowskigrzegorz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by gkarwows on 2018-10-12
 */
public class InvestingStyle {

    private final static Logger logger = LogManager.getLogger(InvestingStyle.class);
    static final double ONE_HUNDRED = 100;

    private Map<FundType, Integer> percentOfInvestmentPerFundType = new HashMap<>();

    /**
     * Gets ratio.
     *
     * @param fundType the fund type
     * @return the ratio
     * @throws Exception the exception
     */
    double getRatio(FundType fundType) throws Exception {
        if (percentOfInvestmentPerFundType.containsKey(fundType)) {
            return percentOfInvestmentPerFundType.get(fundType) / ONE_HUNDRED;
        } else {
            throw new Exception("This investing style doesn't have specified percentage of investment for "
                    + fundType + " type!");
        }
    }

    /**
     * Sets percentage of investment per fund type.
     *
     * @param percentage the percentage
     * @param fundType   the fund type
     */
    void setPercentageOfInvestmentPerFundType(int percentage, FundType fundType) {
        percentOfInvestmentPerFundType.put(fundType, percentage);
        logger.info("Defining investing style...\n" + toString());
        if (!definedCorrectly()) {
            percentOfInvestmentPerFundType.remove(fundType);
            logger.warn("Improper definition of investing style. Undoing your last definition...\n"
                    + toString());
        }
    }

    /**
     * Sets percentage of investment per fund type. It takes Map as a parameter
     *
     * @param percentOfInvestmentPerFundType the percent of investment per fund type
     */
    void setPercentageOfInvestmentPerFundType(Map<FundType, Integer> percentOfInvestmentPerFundType) {
        logger.info("Defining investing style...");
        this.percentOfInvestmentPerFundType.putAll(percentOfInvestmentPerFundType);
        logger.info(toString());
        if (!definedCorrectly()) {
            percentOfInvestmentPerFundType.keySet().removeAll(percentOfInvestmentPerFundType.keySet());
            logger.warn("Improper definition of investing style. Undoing last definition...\n"
                    + toString());
        }
    }

    /**
     * Checks if sum of percents per each fund doesn't exceed 100%.
     *
     * @return the boolean
     */
    boolean definedCorrectly() {

        int percentSum = percentOfInvestmentPerFundType.values().stream()
                .mapToInt(percent -> percent.intValue())
                .sum();
        if (percentSum > ONE_HUNDRED) {
            logger.warn("100% exceeded!");
            return false;
        } else if (percentSum < ONE_HUNDRED) {
            logger.warn("Investing style is partially defined. Using this style you will invest only " + percentSum
                    + "% of money designated for investment.\n" + toString());
            return true;
        } else {
            logger.info("Defining investing style done\n" + toString());
            return true;
        }
    }

    @Override
    public String toString() {
        return percentOfInvestmentPerFundType
                .entrySet().stream()
                .map(fundTypeIntegerEntry -> fundTypeIntegerEntry.getKey().getType() + " Type: " + fundTypeIntegerEntry.getValue() + ", ")
                .collect(Collectors.joining());
    }
}
