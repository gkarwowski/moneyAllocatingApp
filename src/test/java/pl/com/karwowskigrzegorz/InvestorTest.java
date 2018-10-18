package pl.com.karwowskigrzegorz;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

/**
 * Created by gkarwows on 2018-09-05
 */

public class InvestorTest {

    static FundsPacket fundsPacket = new FundsPacket();

    static final PolishType polishType = new PolishType();
    static final ForeignType foreignType = new ForeignType();
    static final MonetaryType monetaryType = new MonetaryType();
    static InvestingStyle safe = new InvestingStyle();
    static Fund fundOne = new Fund("Fundusz Polski 1", polishType);
    static Fund fundTwo = new Fund("Fundusz Polski 2", polishType);
    static Fund fundThree = new Fund("Fundusz Zagraniczny 1", foreignType);
    static Fund fundFour = new Fund("Fundusz Zagraniczny 2", foreignType);
    static Fund fundFive = new Fund("Fundusz Zagraniczny 3", foreignType);
    static Fund fundSix = new Fund("Fundusz Pieniężny 1", monetaryType);
    static Fund fundSeven = new Fund("Fundusz Pieniężny 2", monetaryType);

    double moneyforInvestitions = 10001;
    FundInvestor investor = new FundInvestor(moneyforInvestitions, fundsPacket);


    @BeforeClass
    public static void setUp() {
        safe.setPercentageOfInvestmentPerFundType(20, polishType);
        safe.setPercentageOfInvestmentPerFundType(75, foreignType);
        safe.setPercentageOfInvestmentPerFundType(15, monetaryType);
        safe.setPercentageOfInvestmentPerFundType(5, monetaryType);
        fundsPacket.addSetOfFunds(Sets.newSet(fundOne, fundTwo, fundThree, fundFour, fundFive, fundSix));
    }

    @Test
    public void investTestFirstVariant() throws Exception {
        investor.invest(10000, safe);
        Assert.assertEquals(new Double(1000), investor.getInvestmentId(0).getInvestMap().get(fundOne));
        Assert.assertEquals(new Double(1000), investor.getInvestmentId(0).getInvestMap().get(fundTwo));
        Assert.assertEquals(new Double(2500), investor.getInvestmentId(0).getInvestMap().get(fundThree));
        Assert.assertEquals(new Double(2500), investor.getInvestmentId(0).getInvestMap().get(fundFour));
        Assert.assertEquals(new Double(2500), investor.getInvestmentId(0).getInvestMap().get(fundFive));
        Assert.assertEquals(new Double(500), investor.getInvestmentId(0).getInvestMap().get(fundSix));
    }

    @Test
    public void investTestSecondVariant() throws Exception {
        investor.invest(10001, safe);
        Assert.assertEquals(new Double(1000), investor.getInvestmentId(0).getInvestMap().get(fundOne));
        Assert.assertEquals(new Double(1000), investor.getInvestmentId(0).getInvestMap().get(fundTwo));
        Assert.assertEquals(new Double(2500), investor.getInvestmentId(0).getInvestMap().get(fundThree));
        Assert.assertEquals(new Double(2500), investor.getInvestmentId(0).getInvestMap().get(fundFour));
        Assert.assertEquals(new Double(2500), investor.getInvestmentId(0).getInvestMap().get(fundFive));
        Assert.assertEquals(new Double(500), investor.getInvestmentId(0).getInvestMap().get(fundSix));
        Assert.assertEquals(1, Math.round(investor.getAmountOfMoney()));
    }


}
