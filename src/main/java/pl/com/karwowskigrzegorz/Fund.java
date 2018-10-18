package pl.com.karwowskigrzegorz;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gkarwows on 2018-09-04.
 */
public class Fund<T extends FundType> {

    private static AtomicInteger uniqueId = new AtomicInteger();

    private int id;
    private String name;
    private T type;

    public Fund(String fundName, T type) {
        this.name = fundName;
        this.type = type;
        id = uniqueId.incrementAndGet();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    int getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    String getName() {
        return name;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    T getType() {
        return type;
    }

    /**
     * Has name boolean.
     *
     * @param fundName the fund name
     * @return the boolean
     */

    boolean hasName(String fundName) {
        return this.name.equals(fundName);
    }
}



