package pl.com.karwowskigrzegorz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.com.karwowskigrzegorz.constant.FundType;
import pl.com.karwowskigrzegorz.constant.InvestingStyleDef;

/**
 * Created by gkarwows on 2018-09-05
 */
public class InvestorTestThirdVariant {

    private double moneyforInvestitions = 10001;
    private Fund fundOne = new Fund("Fundusz Polski 1", FundType.POLISH);
    private Fund fundTwo = new Fund("Fundusz Polski 2", FundType.POLISH);
    private Fund fundThree = new Fund("Fundusz Polski 3", FundType.POLISH);
    private Fund fundFour = new Fund("Fundusz Zagraniczny 2", FundType.FOREIGN);
    private Fund fundFive = new Fund("Fundusz Zagraniczny 3", FundType.FOREIGN);
    private Fund fundSix = new Fund("Fundusz Pieniężny 1", FundType.MONETARY);
    private FundsPacket fundsPacket = new FundsPacket();
    private Investor investor = new Investor(moneyforInvestitions, fundsPacket);
    private Fund[] funds = {fundOne, fundTwo, fundThree, fundFour, fundFive, fundSix};

    @Before
    public void setUp() {
        for (int fundIndex = 0; fundIndex < funds.length; fundIndex++){
            fundsPacket.addFund(funds[fundIndex]);
        }
    }
    @Test
    public void investTestThirdVariant() throws Exception {
        investor.invest(10000, InvestingStyleDef.SAFE);
        Assert.assertEquals(new Double(668), investor.getInvestmentId(0).getInvestMap().get(fundOne));
        Assert.assertEquals(new Double(666), investor.getInvestmentId(0).getInvestMap().get(fundTwo));
        Assert.assertEquals(new Double(666), investor.getInvestmentId(0).getInvestMap().get(fundThree));
        Assert.assertEquals(new Double(3750), investor.getInvestmentId(0).getInvestMap().get(fundFour));
        Assert.assertEquals(new Double(3750), investor.getInvestmentId(0).getInvestMap().get(fundFive));
        Assert.assertEquals(new Double(500), investor.getInvestmentId(0).getInvestMap().get(fundSix));
    }
}
