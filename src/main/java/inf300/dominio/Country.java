package inf300.dominio;

import java.io.Serializable;

/**
 * *<img src="./doc-files/Country.png" alt="Country">
 */
public class Country implements Serializable {

    private static final long serialVersionUID = 5171617014956861344L;

    private final int id;
    private final String name;
    private final String currency;
    private final double exchange;

    /**
     *
     * @param id
     * @param name
     * @param currency
     * @param exchange
     */
    public Country(int id, String name, String currency, double exchange) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.exchange = exchange;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @return
     */
    public double getExchange() {
        return exchange;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Country) {
            Country country = (Country) o;
            return name.equals(country.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
