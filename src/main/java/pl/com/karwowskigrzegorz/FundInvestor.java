package pl.com.karwowskigrzegorz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 *  FundInvestor implements Investor interface
 */
public class FundInvestor implements Investor {

    private final static Logger logger = LogManager.getLogger(Investor.class);

    private double amountOfMoney = 0;
    private List<Investment> investmentList = new LinkedList<>();
    private FundsPacket fundsPacket = new FundsPacket();

    /**
     * Invests specified part of  money of FundInvestor instance in funds from funds packet
     * using specified investing style
     *
     * @param moneyToInvest money to invest
     * @param investingStyle used to do investment
     * @return the newInvestment
     * @throws Exception the exception
     */
    public Investment invest(double moneyToInvest, InvestingStyle investingStyle) {

        Investment newInvestment = new Investment();
        double investedMoney;

        if (canInvest(moneyToInvest)) {
            for (Fund fund : fundsPacket.getFunds()) {
                try {
                    investedMoney = allocationOfMoneyPerFund(moneyToInvest, fund, investingStyle);
                    newInvestment.getInvestMap().put(fund, investedMoney);
                    amountOfMoney -= investedMoney;
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            amountOfMoney += newInvestment.investmentCheck();
            this.investmentList.add(newInvestment);
        }
        return newInvestment;
    }

    /**
     * Computes how much money shall be allocated to fund passed as parameter when using specified investing style
     *
     * @param moneyToInvest money to invest
     * @param fund money to invest
     * @param investingStyle investing style
     * @return double value
     * @throws Exception the exception
     */
    private double allocationOfMoneyPerFund(double moneyToInvest, Fund fund, InvestingStyle investingStyle) throws Exception {
        try {
            int fundCount = fundsPacket.getNumber(fund.getType());
            double fundTypeRatio = investingStyle.getRatio(fund.getType());
            return moneyToInvest * fundTypeRatio / fundCount;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception("Error during money per fund allocation!");
        }
    }
    /**
     * Checks if investor can invest specified amount of money
     *
     * @param moneyToInvest money to invest
     * @return boolean value
     */
    private boolean canInvest(double moneyToInvest) {
        if (moneyToInvest <= amountOfMoney && moneyToInvest > 0) {
            if (fundsPacket.getFunds().size() > 0) {
                return true;
            } else {
                logger.warn("You have to add at least one fund to your funds packet. Invest operation stopped.");
                return false;
            }
        } else {
            logger.warn("Fund Investor doesn't have enough money for doing new investments");
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
     * Gets amount of money.
     *
     * @return the amount of money
     */
    double getAmountOfMoney() {
        return amountOfMoney;
    }


    /**
     * Sets amount of money.
     *
     * @param amountOfMoney the amount of money
     */
    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    /**
     * Instantiates a new Fund investor.
     *
     * @param amountOfMoney the amount of money
     * @param fundsPacket   the funds packet
     */
    FundInvestor(double amountOfMoney, FundsPacket fundsPacket) {
        this(amountOfMoney);
        this.fundsPacket = fundsPacket;
    }

    /**
     * Instantiates a new Fund investor.
     *
     * @param amountOfMoney the amount of money
     */
    FundInvestor(double amountOfMoney) {
        this();
        this.amountOfMoney = amountOfMoney;
    }

    /**
     * Instantiates a new Fund investor.
     */
    FundInvestor() {
    }

}
