package pl.com.karwowskigrzegorz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by gkarwows on 2018-09-05
 */
public class InvestorTestThirdVariant {

    private double moneyforInvestitions = 10001;
    private PolishType polishType = new PolishType();
    private ForeignType foreignType = new ForeignType();
    private MonetaryType monetaryType = new MonetaryType();
    private Fund fundOne = new Fund("Fundusz Polski 1", polishType);
    private Fund fundTwo = new Fund("Fundusz Polski 2", polishType);
    private Fund fundThree = new Fund("Fundusz Polski 3", polishType);
    private Fund fundFour = new Fund("Fundusz Zagraniczny 2", foreignType);
    private Fund fundFive = new Fund("Fundusz Zagraniczny 3", foreignType);
    private Fund fundSix = new Fund("Fundusz Pieniężny 1", monetaryType);
    private FundsPacket fundsPacket = new FundsPacket();
    private FundInvestor investor = new FundInvestor(moneyforInvestitions, fundsPacket);
    private Fund[] funds = {fundOne, fundTwo, fundThree, fundFour, fundFive, fundSix};
    private InvestingStyle safe = new InvestingStyle();

    @Before
    public void setUp() {
        for (int fundIndex = 0; fundIndex < funds.length; fundIndex++){
            fundsPacket.addFund(funds[fundIndex]);
        }
        safe.setPercentageOfInvestmentPerFundType(20, polishType);
        safe.setPercentageOfInvestmentPerFundType(75, foreignType);
        safe.setPercentageOfInvestmentPerFundType(15, monetaryType);
        safe.setPercentageOfInvestmentPerFundType(5, monetaryType);
    }
    @Test
    public void investTestThirdVariant() throws Exception {
        investor.invest(10000, safe);
        Assert.assertEquals(new Double(668), investor.getInvestmentId(0).getInvestMap().get(fundOne));
        Assert.assertEquals(new Double(666), investor.getInvestmentId(0).getInvestMap().get(fundTwo));
        Assert.assertEquals(new Double(666), investor.getInvestmentId(0).getInvestMap().get(fundThree));
        Assert.assertEquals(new Double(3750), investor.getInvestmentId(0).getInvestMap().get(fundFour));
        Assert.assertEquals(new Double(3750), investor.getInvestmentId(0).getInvestMap().get(fundFive));
        Assert.assertEquals(new Double(500), investor.getInvestmentId(0).getInvestMap().get(fundSix));
        Assert.assertEquals(1, Math.round(investor.getAmountOfMoney()));
    }
}
