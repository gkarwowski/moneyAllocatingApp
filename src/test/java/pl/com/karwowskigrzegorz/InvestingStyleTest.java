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
        Map<FundType, Integer> map = new HashMap<>(Stream.of(new AbstractMap.SimpleEntry<>(polishType, 20),
                new AbstractMap.SimpleEntry<>(foreignType,  75),
                new AbstractMap.SimpleEntry<>(monetaryType, 5))
                .collect(toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));
        safe.setPercentageOfInvestmentPerFundType(map);

//        safe.setPercentageOfInvestmentPerFundType(20, "Polish");
//        safe.setPercentageOfInvestmentPerFundType(75, "Foreign");
//        safe.setPercentageOfInvestmentPerFundType(15, "Monetary");
//        safe.setPercentageOfInvestmentPerFundType(5, "Monetary");
        Assert.assertEquals(0.2 , safe.getRatio(polishType), 0.0);
        Assert.assertEquals(0.75 , safe.getRatio(foreignType), 0.0);
        Assert.assertEquals(0.05 , safe.getRatio(monetaryType), 0.0);
    }
}
