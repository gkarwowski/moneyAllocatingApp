package pl.com.karwowskigrzegorz;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gkarwows on 2018-10-17
 */
public class InvestmentTest {

    @Mock
    Investment investment;
    @Test
    public void add() {
        List<Investment> investmentList = new ArrayList<>();
        investmentList.add(investment);
        Assert.assertEquals(1, investmentList.size());
    }
}
