package inf300.dominio;

import java.io.Serializable;

/**
 * *<img src="./doc-files/CartLine.png" alt="CartLine">
 */
public class CartLine implements Serializable {

    private static final long serialVersionUID = 7390646727961714957L;

    private int qty;
    private final Book book;

    /**
     *
     * @param qty
     * @param book
     */
    public CartLine(int qty, Book book) {
        this.qty = qty;
        this.book = book;
    }

    /**
     *
     * @param qty
     */
    public void setQty(int qty) {
        this.qty = qty;
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
    public Book getBook() {
        return book;
    }

}
