package pl.com.karwowskigrzegorz;


import pl.com.karwowskigrzegorz.InvestingStyle;

/**
 * Created by gkarwows on 2018-09-05
 */
interface IInvestable {

    /**
     * Invest.
     *
     * @param money the money
     * @param style the style
     * @throws Exception the exception
     */
    void invest(double money, InvestingStyle style) throws Exception;
}
