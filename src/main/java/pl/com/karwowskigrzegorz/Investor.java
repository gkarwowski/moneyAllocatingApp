package pl.com.karwowskigrzegorz;

import java.util.*;

import pl.com.karwowskigrzegorz.constant.FundType;
import pl.com.karwowskigrzegorz.constant.InvestingStyleDef;

/**
 * Created by gkarwows on 2018-09-04
 */

public class Investor implements IInvestable {

    private double moneyToInvest = 0;
    private List<Investment> investmentList = new ArrayList<>();
    private FundsPacket fundsPacket = new FundsPacket();
//    private InvestingStyleDef style;

    public Investment invest(double money, InvestingStyleDef investingStyle) throws Exception {

        if (money <= moneyToInvest && money > 0) {
            Investment newInvestment = new Investment();
            double investmentCapital;
            int fundCount = 1;
            double fundTypeRatio = 1;

            for (Fund fund : fundsPacket.getFunds()) {
                if (fund.getType().equals(FundType.POLISH)) {
                    fundCount = fundsPacket.getPolishCount();
                    fundTypeRatio = investingStyle.getPolishRatio();
                } else if (fund.getType().equals(FundType.FOREIGN)) {
                    fundCount = fundsPacket.getForeignCount();
                    fundTypeRatio = investingStyle.getForeignRatio();
                } else if (fund.getType().equals(FundType.MONETARY)) {
                    fundCount = fundsPacket.getMonetaryCount();
                    fundTypeRatio = investingStyle.getMonetaryRatio();
                }

                investmentCapital = money * fundTypeRatio / fundCount;
                newInvestment.getInvestMap().put(fund, investmentCapital);
                moneyToInvest -= investmentCapital;
            }
            moneyToInvest += newInvestment.investmentCheck(newInvestment.getInvestMap());
             Math.round(moneyToInvest);
            this.investmentList.add(newInvestment);
            return newInvestment;
        } else {
            throw new Exception("Investor doesn't have enough money for doing new investmentList");
        }
    }

     FundsPacket getFundsPacket() {
        return fundsPacket;
    }

     Investment getInvestmentId(int id) throws Exception {
        if (investmentList.size() > id) {
            return investmentList.get(id);
        } else {
            throw new Exception("Investment with id = " + id + " doesn't exists");
        }
    }

     double getMoneyToInvest() {
        return moneyToInvest;
    }

     Investor(double amountOfMoney, FundsPacket fundsPacket) {
        this(amountOfMoney);
        this.fundsPacket = fundsPacket;
    }

     Investor(double amountOfMoney) {
        this();
        this.moneyToInvest = amountOfMoney;
    }

     Investor() {
    }

}
