package pl.com.karwowskigrzegorz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by gkarwows on 2018-10-17
 */
public class FundsPacketTest {

    private final static Logger logger = LogManager.getLogger(FundsPacketTest.class);

    private PolishType polishType = new PolishType();
    private ForeignType foreignType = new ForeignType();
    private Fund<PolishType> polishFundOne = new Fund("Polish Fund 1", polishType);
    private Fund<PolishType>  polishFundTwo = new Fund("Polish Fund 2", polishType);
    private Fund<ForeignType>  foreignFundOne = new Fund("Foreign Fund 1", foreignType);
    private FundsPacket fundsPacket = new FundsPacket();
    private Fund noInitiatedFund;
//    private Fund notToBeAddedFund = new Fund("Not added fund");
    private Fund notToBeAddedFund = new Fund("Not added fund", foreignType);

    @Before
    public void setUp() {
        fundsPacket.getFunds().clear();
        fundsPacket.addFund(polishFundOne);
    }

    @Test
    public void getFundByNameTest() {
        try {
            Assert.assertEquals(polishFundOne, fundsPacket.getFundByName(polishFundOne.getName()));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void getFundByNameExceptionTestForNotExistingFund() {
        fundsPacket.getFundByName("Not existing fund");
    }

    @Test(expected = NoSuchElementException.class)
    public void getFundByNameExceptionTest() {
        fundsPacket.getFundByName(notToBeAddedFund.getName());
    }

    @Test
    public void fundExistsTest() {
        Assert.assertFalse(fundsPacket.fundExists(notToBeAddedFund));
        Assert.assertTrue(fundsPacket.fundExists(polishFundOne));
    }

    @Test
    public void fundExistsTestOnEmptySetOfFunds() {
        fundsPacket.getFunds().clear();
        Assert.assertFalse(fundsPacket.fundExists(polishFundOne));
        fundsPacket.getFunds().clear();
    }

    @Test
    public void getNumberTest() {
        Assert.assertTrue(1 == fundsPacket.getNumber(polishType));
        fundsPacket.addFund(polishFundTwo);
        Assert.assertTrue(2 == fundsPacket.getNumber(polishType));
        fundsPacket.addFund(foreignFundOne);
        Assert.assertTrue(2 == fundsPacket.getNumber(polishType));
        Assert.assertTrue(1 == fundsPacket.getNumber(foreignType));
        fundsPacket.getFunds().clear();
        Assert.assertTrue(0 == fundsPacket.getNumber(polishType));
    }

    @Test
    public void addFundTest() {
        Assert.assertTrue( 1 == fundsPacket.getFunds().size());
        fundsPacket.addFund(polishFundTwo);
        Assert.assertTrue( 2 == fundsPacket.getFunds().size());
        fundsPacket.addFund(polishFundTwo);
        Assert.assertTrue( 2 == fundsPacket.getFunds().size());
    }

    @Test
    public void addAlreadyAddedFundTest() {
        Assert.assertTrue( 1 == fundsPacket.getFunds().size());
        fundsPacket.addFund(polishFundOne);
        Assert.assertTrue( 1 == fundsPacket.getFunds().size());
    }

    @Test
    public void addSetOfFundTest() {
        fundsPacket.getFunds().clear();
        Set<Fund> fundSet = new HashSet<>();
        fundSet.add(polishFundOne);
        fundSet.add(polishFundOne);
        fundSet.add(polishFundTwo);
        fundSet.add(foreignFundOne);
        fundsPacket.addSetOfFunds(fundSet);
        Assert.assertTrue( 3 == fundsPacket.getFunds().size());
    }
}
