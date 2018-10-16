package pl.com.karwowskigrzegorz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Investor.
 */
public class Investor implements IInvestable {
    private final static Logger logger = LogManager.getLogger(Investor.class);

    private double moneyToInvest = 0;
    private List<Investment> investmentList = new LinkedList<>();
    private FundsPacket fundsPacket = new FundsPacket();

//    TODO: maybe Investor should have choice which funds to invest in
//    public void invest(double money, InvestingStyle investingStyle, Fund[]/FundsPacket funds) {
    public void invest(double money, InvestingStyle investingStyle) {

        Investment newInvestment = new Investment();

        if (canInvest(money)) {
            fundsPacket.getFunds().forEach(fund -> {
                try {
                    int fundCount = fundsPacket.getCount(fund.getType());
                    double fundTypeRatio = investingStyle.getRatio(fund.getType());
                    double investmentCapital = money * fundTypeRatio / fundCount;
                    newInvestment.getInvestMap().put(fund, investmentCapital);
                    moneyToInvest -= investmentCapital;
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            });

            moneyToInvest += newInvestment.investmentCheck();
            this.investmentList.add(newInvestment);
        }
    }

    private boolean canInvest(double money) {
        if (money <= moneyToInvest && money > 0) {
            if (fundsPacket.getFunds().size() > 0) {
                return true;
            } else {
                logger.warn("You have to add at least one fund to your funds packet. Invest operation stopped.");
                return false;
            }
        } else {
            logger.warn("Investor doesn't have enough money for doing new investment");
            return false;
        }
    }

    /**
     * Gets funds packet.
     *
     * @return the funds packet
     */
    FundsPacket getFundsPacket() {
        return fundsPacket;
    }

    /**
     * Gets investment id.
     *
     * @param id the id
     * @return the investment id
     * @throws Exception the exception
     */
    Investment getInvestmentId(int id) throws Exception {
        if (investmentList.size() > id) {
            return investmentList.get(id);
        } else {
            throw new Exception("Investment with id = " + id + " doesn't exists");
        }
    }

    /**
     * Gets Investor's money to invest.
     *
     * @return the money to invest
     */
    double getMoneyToInvest() {
        return moneyToInvest;
    }

    /**
     * Instantiates a new Investor.
     *
     * @param amountOfMoney the amount of money
     * @param fundsPacket   the funds packet
     */
    Investor(double amountOfMoney, FundsPacket fundsPacket) {
        this(amountOfMoney);
        this.fundsPacket = fundsPacket;
    }

    /**
     * Instantiates a new Investor.
     *
     * @param amountOfMoney the amount of money
     */
    Investor(double amountOfMoney) {
        this();
        this.moneyToInvest = amountOfMoney;
    }

    /**
     * Instantiates a new Investor.
     */
    Investor() {
    }

}
