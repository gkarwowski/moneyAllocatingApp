package pl.com.karwowskigrzegorz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by gkarwows on 2018-09-04.
 */
class FundsPacket {

    private final static Logger logger = LogManager.getLogger(FundsPacket.class);

    private Set<Fund> funds = new LinkedHashSet<>();


    /**
     * Fund exists boolean. Checks if fund exists in funds set field
     *
     * @param fund the fund
     * @return the boolean
     */
    boolean fundExists(Fund fund) throws NullPointerException{
        return funds.stream().anyMatch(f -> f.hasName(fund.getName()));
    }

    /**
     * Gets fund by name.
     *
     * @param name the name
     * @return the fund by name
     * @throws NoSuchElementException the no such element exception
     */
    Fund getFundByName(String name) throws NoSuchElementException {

           return funds.stream()
                   .filter(fund ->  fund.hasName(name))
                   .findFirst()
                   .orElseThrow(() -> new NoSuchElementException("No fund with \"" + name + "\" name found."));

    }

    /**
     * Gets number of the funds of given type in fundsPacket
     *
     * @param fundType the fund type
     * @return the number
     */
    int getNumber(FundType fundType) {
        return (int) funds.stream()
                .filter(fund -> fund.getType().equals(fundType))
                .count();
    }

    /**
     * Adds fund to set of funds.
     *
     * @param newfund the newfund
     */
    void addFund(Fund newfund) {
        if (fundExists(newfund)) {
            logger.warn("Fund \"{}\" couldn't be added. It's not possible to have two funds with the same name",
                    newfund.getName());
        } else {
            funds.add(newfund);
            System.out.println();
        }
    }

    /**
     * Adds set of funds to fundsPacket.
     *
     * @param newFunds the new funds
     */
    void addSetOfFunds(Set<Fund> newFunds) {
        newFunds.stream()
                .filter(fund -> !fundExists(fund))
                .forEach(FundsPacket.this::addFund);
    }

    /**
     * Gets funds.
     *
     * @return funds set.
     */
    Set<Fund> getFunds() {
        return funds;
    }
}
