package pl.com.karwowskigrzegorz.constant;

/**
 * Created by gkarwows on 2018-09-04
 */
public enum InvestingStyleDef {

    SAFE(20, 75, 5),
    BALANCED(30, 60, 10),
    AGGRESSIVE(40, 20, 40);

    private final int polishPercentageShare;
    private final int foreignPercentageShare;
    private final int monetaryPercentageRatio;
    private final static double HELPER_DIVIDER = 100;

    InvestingStyleDef(int polishFunds, int foreignFunds, int monetaryFunds) {
        this.polishPercentageShare = polishFunds;
        this.foreignPercentageShare = foreignFunds;
        this.monetaryPercentageRatio = monetaryFunds;
    }

    public double getPolishRatio() {
        return polishPercentageShare / HELPER_DIVIDER;
    }

    public double getForeignRatio() {
        return foreignPercentageShare / HELPER_DIVIDER;
    }

    public double getMonetaryRatio() {
        return monetaryPercentageRatio / HELPER_DIVIDER;
    }
}

