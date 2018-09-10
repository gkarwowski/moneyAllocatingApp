package pl.com.karwowskigrzegorz;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by gkarwows on 2018-09-07
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        FundTest.class,
        InvestorTest.class,
        InvestorTestThirdVariant.class
})
public class AllTests {
}
