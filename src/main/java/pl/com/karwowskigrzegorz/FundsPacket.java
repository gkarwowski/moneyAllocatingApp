package pl.com.karwowskigrzegorz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by gkarwows on 2018-09-04.
 */
class FundsPacket {

    private final static Logger logger = LogManager.getLogger(FundsPacket.class);

    private Set<Fund> funds = new LinkedHashSet<>();
    private Map<String, Integer> typeCounter = new HashMap<>();

    /**
     * Add funds packet.
     *
     * @param newFunds the new funds
     */
    void addFundsPacket(Set<Fund> newFunds) {
        //TODO stream()?
        for (Fund fund : newFunds) {
            if (!fundExists(fund)) {
                this.funds.add(fund);
                incrementCounter(fund.getType());
            }
        }
    }

    /**
     * Adds fund to set of funds and simultaneously increments counter for funds of given type.
     *
     * @param newfund the newfund
     */
    void addFund(Fund newfund) {
        if (!fundExists(newfund)) {
            funds.add(newfund);
            incrementCounter(newfund.getType());
        } else {
            logger.warn("Fund \"{}\" couldn't be added", newfund.getName());
        }
    }

    /**
     * Fund exists boolean. Checks if fund exists in funds set field
     *
     * @param fund the fund
     * @return the boolean
     */
    boolean fundExists(Fund fund) {
        return funds.stream().anyMatch(f -> f.hasName(fund.getName()));
    }

    /**
     * Gets fund by name.
     *
     * @param name the name
     * @return the fund by name
     * @throws Exception the exception
     */
    Fund getFundByName(String name) throws Exception {
        //TODO: stream()?
        for (Fund fund : funds) {
            if (fund.hasName(name)) {
                return fund;
            }
        }
        throw new Exception("No fund found with \"" + name + "\" name");
    }

    private void incrementCounter(String fundType) {
        if (typeCounter.containsKey(fundType)) {
            typeCounter.put(fundType, typeCounter.get(fundType) + 1);
        } else {
            typeCounter.put(fundType, 1);
        }
    }


    /**
     * Gets funds.
     *
     * @return funds set.
     */
    Set<Fund> getFunds() {
        return funds;
    }

    /**
     * Gets count.
     *
     * @param fundType the fund type
     * @return the count
     */
    int getCount(String fundType) {
        return typeCounter.get(fundType);
    }
}
