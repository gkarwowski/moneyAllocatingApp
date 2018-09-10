package pl.com.karwowskigrzegorz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.com.karwowskigrzegorz.constant.FundType;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by gkarwows on 2018-09-04.
 */

class FundsPacket {

    private final static Logger logger = LogManager.getLogger(FundsPacket.class);
    private Set<Fund> funds = new LinkedHashSet<>();
    private int polishCounter = 0;
    private int foreignCounter = 0;
    private int monetaryCounter = 0;

    void addFundsPacket(Set<Fund> newFunds) {
        for (Fund fund : newFunds) {
            if (!fundExists(fund)) {
                this.funds.add(fund);
                incrementCounter(fund);
            }
        }
    }

    void addFund(Fund newfund) {
        if (!fundExists(newfund)) {
            funds.add(newfund);
            incrementCounter(newfund);
        } else {
            logger.warn("Fund \"{}\" couldn't be added", newfund.getName());
        }
    }

    private boolean fundExists(Fund fund) {
        for (Fund fundFromPacket : funds) {
            if (fundFromPacket.hasName(fund.getName())) {
                return true;
            }
        }
        return false;
    }

    Fund getFundByName(String name) throws Exception {
        for (Fund fund : funds) {
            if (fund.hasName(name)) {
                return fund;
            }
        }
        throw new Exception("No fund found with \"" + name + "\" name");
    }

    private void incrementCounter(Fund fund) {
        if (fund.getType().equals(FundType.POLISH)) {
            polishCounter++;
        } else if (fund.getType().equals(FundType.FOREIGN)) {
            foreignCounter++;
        } else if (fund.getType().equals(FundType.MONETARY)) {
            monetaryCounter++;
        }
    }

    int getPolishCount() {
        return polishCounter;
    }

    int getForeignCount() {
        return foreignCounter;
    }

    int getMonetaryCount() {
        return monetaryCounter;
    }

    Set<Fund> getFunds() {
        return funds;
    }
}
