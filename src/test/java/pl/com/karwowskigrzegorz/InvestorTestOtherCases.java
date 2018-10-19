package pl.com.karwowskigrzegorz;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

/**
 * Created by gkarwows on 2018-10-19
 */
public class InvestorTestOtherCases {
    static FundsPacket fundsPacket = new FundsPacket();

    static final PolishType polishType = new PolishType();
    static final ForeignType foreignType = new ForeignType();
    static final MonetaryType monetaryType = new MonetaryType();
    static InvestingStyle myStyle = new InvestingStyle();
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
        myStyle.setPercentageOfInvestmentPerFundType(25, polishType);
        myStyle.setPercentageOfInvestmentPerFundType(25, foreignType);
        myStyle.setPercentageOfInvestmentPerFundType(50, monetaryType);
        fundsPacket.addSetOfFunds(Sets.newSet(fundOne, fundTwo, fundThree, fundFour, fundFive, fundSix));
    }

    @Test
    public void investWithIncompletelyDefinedInvestingStyle() {

        myStyle.setPercentageOfInvestmentPerFundType(100, monetaryType);
        investor.invest(10000, myStyle);
        Assert.assertEquals(5001, Math.round(investor.getAmountOfMoney()));
    }
}
