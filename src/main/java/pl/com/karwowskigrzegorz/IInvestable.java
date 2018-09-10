package pl.com.karwowskigrzegorz;


import pl.com.karwowskigrzegorz.constant.InvestingStyleDef;

/**
 * Created by gkarwows on 2018-09-05
 */

interface IInvestable {
     Investment invest(double money, InvestingStyleDef style) throws Exception;
}
