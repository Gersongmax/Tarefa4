package inf300.dominio;

import java.io.Serializable;
import java.util.Date;

/**
 * *<img src="./doc-files/CCTransaction.png" alt="CCTransaction">
 */
public class CCTransaction implements Serializable {

    private static final long serialVersionUID = 5470177450411822726L;

    private final String type;
    private final long num;
    private final String name;
    private final Date expire;
    private final String authId;
    private final double amount;
    private final Date date;
    private final Country country;

    /**
     *
     * @param type
     * @param num
     * @param name
     * @param expire
     * @param authId
     * @param amount
     * @param date
     * @param country
     */
    public CCTransaction(String type, long num, String name, Date expire,
            String authId, double amount, Date date, Country country) {
        this.type = type;
        this.num = num;
        this.name = name;
        this.expire = expire;
        this.authId = authId;
        this.amount = amount;
        this.date = date;
        this.country = country;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return
     */
    public long getNum() {
        return num;
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
    public Date getExpire() {
        return expire;
    }

    /**
     *
     * @return
     */
    public String getAuthId() {
        return authId;
    }

    /**
     *
     * @return
     */
    public double getAmount() {
        return amount;
    }

    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return
     */
    public Country getCountry() {
        return country;
    }

}
