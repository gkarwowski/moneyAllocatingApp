package pl.com.karwowskigrzegorz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gkarwows on 2018-09-05
 */

public class FundTest {

    private final static Logger logger = LogManager.getLogger(FundTest.class);
    private static Investor investor;
    private static String testFundName = "Fundusz Polski 1";
    private static Fund testFund = new Fund(testFundName, "Polish");
    private static Fund falseFund = new Fund("False Fund", "English");

    static InvestingStyle safe = new InvestingStyle();


    @BeforeClass
    public static void setUp() {
        double moneyForInvestitions = 100000;
        investor = new Investor(moneyForInvestitions, new FundsPacket());
        investor.getFundsPacket().addFund(testFund);
        safe.setPercentageOfInvestmentPerFundType(20, "Polish");
        safe.setPercentageOfInvestmentPerFundType(75, "Foreign");
        safe.setPercentageOfInvestmentPerFundType(15, "Monetary");
        safe.setPercentageOfInvestmentPerFundType(5, "Monetary");

    }

    // Fund.Class
    @Test
    public void addFundTest() {
        investor.getFundsPacket().addFund(testFund);
        Assert.assertEquals(1, investor.getFundsPacket().getFunds().size());
    }

    @Test
    public void hasNameTest() {
        Assert.assertTrue(testFund.hasName("Fundusz Polski 1"));
    }

    // FundsPacket.Class
    @Test
    public void getFundByNameTest() {
        try {
            Assert.assertEquals(testFund, investor.getFundsPacket().getFundByName(testFundName));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    @Test(expected = Exception.class)
    public void getFundByNameExceptionTest() throws Exception {
            investor.getFundsPacket().getFundByName("Uknown fund");
    }

    @Test
    public void fundExistsTest() {
        FundsPacket fundsPacket = new FundsPacket();
        fundsPacket.addFund(testFund);

        Assert.assertFalse(fundsPacket.fundExists(falseFund));
        Assert.assertTrue(fundsPacket.fundExists(testFund));

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
    @Mock
    Investment investment;
    @Test
    public void add() {
        List<Investment> investmentList = new ArrayList<>();
        investmentList.add(investment);
        Assert.assertEquals(1, investmentList.size());
    }

}
