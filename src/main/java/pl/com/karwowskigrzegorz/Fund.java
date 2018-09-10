package pl.com.karwowskigrzegorz;

import pl.com.karwowskigrzegorz.constant.FundType;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gkarwows on 2018-09-04.
 */
public class Fund {

    private static AtomicInteger uniqueId = new AtomicInteger();

    private int id;
    private String name;
    private final FundType type;


    public Fund(String fundName, FundType fundType) {
        this.name = fundName;
        this.type = fundType;
        id = uniqueId.incrementAndGet();
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    FundType getType() {
        return type;
    }

    boolean hasName(String fundName) {
        return this.name.equals(fundName);
    }
}



