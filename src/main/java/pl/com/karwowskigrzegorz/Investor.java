package pl.com.karwowskigrzegorz;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Investor.
 */
public class Investor implements IInvestable {
    private final static Logger logger = LogManager.getLogger(Investor.class);

    private double moneyToInvest = 0;
    private List<Investment> investmentList = new ArrayList<>();
    private FundsPacket fundsPacket = new FundsPacket();

    public void invest(double money, InvestingStyle investingStyle) throws Exception {

        Investment newInvestment = new Investment();
        double investmentCapital;
        int fundCount;
        double fundTypeRatio;

        if (canInvest(money)) {
            for (Fund fund : fundsPacket.getFunds()) {

                fundCount = fundsPacket.getCount(fund.getType());
                fundTypeRatio = investingStyle.getRatio(fund.getType());

                investmentCapital = money * fundTypeRatio / fundCount;
                newInvestment.getInvestMap().put(fund, investmentCapital);
                moneyToInvest -= investmentCapital;
            }

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
