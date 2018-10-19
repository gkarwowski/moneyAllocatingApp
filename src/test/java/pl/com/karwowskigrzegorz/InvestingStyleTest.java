package pl.com.karwowskigrzegorz;

import org.junit.Assert;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Created by gkarwows on 2018-10-12
 */
public class InvestingStyleTest {

    PolishType polishType = new PolishType();
    FundType foreignType = new ForeignType();
    MonetaryType monetaryType = new MonetaryType();
    Fund<PolishType> polishFund = new Fund("Polski Fundusz 1", polishType);
    InvestingStyle safe = new InvestingStyle();

    @Test
    public void getRatioTest() throws Exception {

        Map<FundType, Integer> map = new HashMap<>();
        map.put(polishType, 20);
        map.put(foreignType, 75);
        map.put(monetaryType, 5);
        safe.setPercentageOfInvestmentPerFundType(map);

        Assert.assertEquals(0.2, safe.getRatio(polishType), 0.0);
        Assert.assertEquals(0.75, safe.getRatio(foreignType), 0.0);
        Assert.assertEquals(0.05, safe.getRatio(monetaryType), 0.0);
    }
}
