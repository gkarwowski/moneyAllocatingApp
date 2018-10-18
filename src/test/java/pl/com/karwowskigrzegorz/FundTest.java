package pl.com.karwowskigrzegorz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by gkarwows on 2018-09-05
 */

public class FundTest {

    private final static Logger logger = LogManager.getLogger(FundTest.class);
    private static FundInvestor investor;
    private static PolishType polishType = new PolishType();
    private static ForeignType foreignType = new ForeignType();
    private static MonetaryType monetaryType = new MonetaryType();
    private static String testFundName = "Fundusz Polski 1";
    private static Fund<PolishType> polishFund = new Fund(testFundName, polishType);

    static InvestingStyle safe = new InvestingStyle();


    @BeforeClass
    public static void setUp() {
        double moneyForInvestitions = 100000;
        investor = new FundInvestor(moneyForInvestitions, new FundsPacket());
        investor.getFundsPacket().addFund(polishFund);
        safe.setPercentageOfInvestmentPerFundType(20, polishType);
        safe.setPercentageOfInvestmentPerFundType(75, foreignType);
        safe.setPercentageOfInvestmentPerFundType(15, monetaryType);
        safe.setPercentageOfInvestmentPerFundType(5, monetaryType);

    }

    // Fund.Class
    @Test
    public void addFundTest() {
        investor.getFundsPacket().addFund(polishFund);
        Assert.assertEquals(1, investor.getFundsPacket().getFunds().size());
    }

    @Test
    public void hasNameTest() {
        Assert.assertTrue(polishFund.hasName("Fundusz Polski 1"));
    }



    //Investor.Class
    @Test(expected = Exception.class)
    public void exceptionTest1() throws Exception {
            investor.getInvestmentId(0);
    }

//    @Test(expected = Exception.class)
//    public void investExceptionTest() throws Exception {
//
//            investor.invest(1000000, safe);
//    }

    //mockTest


}
