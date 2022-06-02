package inf300.dominio;

import java.io.Serializable;

/**
 * *<img src="./doc-files/OrderLine.png" alt="OrderLine">
 */
public class OrderLine implements Serializable {

    private static final long serialVersionUID = -5063511252485472431L;

    private final Book book;
    private final int qty;
    private final double discount;
    private final String comments;

    /**
     *
     * @param book
     * @param qty
     * @param discount
     * @param comments
     */
    public OrderLine(Book book, int qty, double discount, String comments) {
        this.book = book;
        this.qty = qty;
        this.discount = discount;
        this.comments = comments;
    }

    /**
     *
     * @return
     */
    public Book getBook() {
        return book;
    }

    /**
     *
     * @return
     */
    public int getQty() {
        return qty;
    }

    /**
     *
     * @return
     */
    public double getDiscount() {
        return discount;
    }

    /**
     *
     * @return
     */
    public String getComments() {
        return comments;
    }

}
