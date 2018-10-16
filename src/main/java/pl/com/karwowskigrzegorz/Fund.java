package pl.com.karwowskigrzegorz;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gkarwows on 2018-09-04.
 */
public class Fund {

    private static AtomicInteger uniqueId = new AtomicInteger();

    private int id;
    private String name;
//    TODO: abstract fund type; create polish foreign monetary class;
    private String type;


    /**
     * Instantiates a new Fund.
     *
     * @param fundName the fund name
     * @param fundType the fund type
     */
    public Fund(String fundName, String fundType) {
        this.name = fundName;
        this.type = fundType;
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
    String getType() {
        return type;
    }

    /**
     * Has name boolean.
     *
     * @param fundName the fund name
     * @return the boolean
     */

    //TODO is this method really needed
    boolean hasName(String fundName) {
        return this.name.equals(fundName);
    }
}



