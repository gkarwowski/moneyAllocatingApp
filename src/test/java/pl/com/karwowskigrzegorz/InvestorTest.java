package pl.com.karwowskigrzegorz;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import pl.com.karwowskigrzegorz.constant.FundType;
import pl.com.karwowskigrzegorz.constant.InvestingStyleDef;

/**
 * Created by gkarwows on 2018-09-05
 */

public class InvestorTest {

    static FundsPacket fundsPacket = new FundsPacket();
    static Fund fundOne = new Fund("Fundusz Polski 1", FundType.POLISH);
    static Fund fundTwo = new Fund("Fundusz Polski 2", FundType.POLISH);
    static Fund fundThree = new Fund("Fundusz Zagraniczny 1", FundType.FOREIGN);
    static Fund fundFour = new Fund("Fundusz Zagraniczny 2", FundType.FOREIGN);
    static Fund fundFive = new Fund("Fundusz Zagraniczny 3", FundType.FOREIGN);
    static Fund fundSix = new Fund("Fundusz Pieniężny 1", FundType.MONETARY);
    double moneyforInvestitions = 10001;
    Investor investor = new Investor(moneyforInvestitions, fundsPacket);

    @BeforeClass
    public static void setUp() {
        fundsPacket.addFundsPacket(Sets.newSet(fundOne,fundTwo, fundThree, fundFour, fundFive, fundSix));
    }

    @Test
    public void investTestFirstVariant() throws Exception {
        investor.invest(10000, InvestingStyleDef.SAFE);
        Assert.assertEquals(new Double(1000), investor.getInvestmentId(0).getInvestMap().get(fundOne));
        Assert.assertEquals(new Double(1000), investor.getInvestmentId(0).getInvestMap().get(fundTwo));
        Assert.assertEquals(new Double(2500), investor.getInvestmentId(0).getInvestMap().get(fundThree));
        Assert.assertEquals(new Double(2500), investor.getInvestmentId(0).getInvestMap().get(fundFour));
        Assert.assertEquals(new Double(2500), investor.getInvestmentId(0).getInvestMap().get(fundFive));
        Assert.assertEquals(new Double(500), investor.getInvestmentId(0).getInvestMap().get(fundSix));
    }

    @Test
    public void investTestSecondVariant() throws Exception {
        investor.invest(10001, InvestingStyleDef.SAFE);
        Assert.assertEquals(new Double(1000), investor.getInvestmentId(0).getInvestMap().get(fundOne));
        Assert.assertEquals(new Double(1000), investor.getInvestmentId(0).getInvestMap().get(fundTwo));
        Assert.assertEquals(new Double(2500), investor.getInvestmentId(0).getInvestMap().get(fundThree));
        Assert.assertEquals(new Double(2500), investor.getInvestmentId(0).getInvestMap().get(fundFour));
        Assert.assertEquals(new Double(2500), investor.getInvestmentId(0).getInvestMap().get(fundFive));
        Assert.assertEquals(new Double(500), investor.getInvestmentId(0).getInvestMap().get(fundSix));
        Assert.assertEquals(1, Math.round(investor.getMoneyToInvest()));
    }






}
