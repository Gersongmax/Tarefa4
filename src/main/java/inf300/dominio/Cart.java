package inf300.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 * *<img src="./doc-files/Cart.png" alt="Cart">
 */
public class Cart implements Serializable {

    private static final long serialVersionUID = -4194553499937996531L;

    private final int id;
    private Date time;
    private HashMap<Integer, CartLine> linesByBookId;
    private double aggregateCost;
    private int aggregateQuantity;

    /**
     *
     * @param id
     * @param time
     */
    public Cart(int id, Date time) {
        this.id = id;
        this.time = time;
        clear();
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
    public Date getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * <pre>
     * linesByBookId = new HashMap&lt;Integer, CartLine&gt;();
     * aggregateCost = 0;
     * aggregateQuantity = 0;
     * </pre>
     */
    public void clear() {
        linesByBookId = new HashMap<Integer, CartLine>();
        aggregateCost = 0;
        aggregateQuantity = 0;
    }

    /**
     *
     * @return
     */
    public Collection<CartLine> getLines() {
        return linesByBookId.values();
    }

    /**
     * <pre>
     * CartLine line = linesByBookId.get(book.getId());
     * if (line == null) {
     * line = new CartLine(0, book);
     * linesByBookId.put(book.getId(), line);
     * }
     * aggregateCost += book.getCost() * quantity;
     * aggregateQuantity += quantity;
     * line.setQty(line.getQty() + quantity);
     * if (quantity == 0) {
     * linesByBookId.remove(book.getId());
     * }
     * </pre>
     *
     * @param book
     * @param quantity
     */
    public void increaseLine(Book book, int quantity) {
        CartLine line = linesByBookId.get(book.getId());
        if (line == null) {
            line = new CartLine(0, book);
            linesByBookId.put(book.getId(), line);
        }
        aggregateCost += book.getCost() * quantity;
        aggregateQuantity += quantity;
        line.setQty(line.getQty() + quantity);
        if (quantity == 0) {
            linesByBookId.remove(book.getId());
        }
    }

    /**
     * <pre>
     *   CartLine line = linesByBookId.get(book.getId());
     * if (line == null) {
     * line = new CartLine(0, book);
     * linesByBookId.put(book.getId(), line);
     * }
     * aggregateCost += book.getCost() * (quantity - line.getQty());
     * aggregateQuantity += (quantity - line.getQty());
     * line.setQty(quantity);
     * if (quantity == 0) {
     * linesByBookId.remove(book.getId());
     * }
     * </pre>
     *
     * @param book
     * @param quantity
     */
    public void changeLine(Book book, int quantity) {
        CartLine line = linesByBookId.get(book.getId());
        if (line == null) {
            line = new CartLine(0, book);
            linesByBookId.put(book.getId(), line);
        }
        aggregateCost += book.getCost() * (quantity - line.getQty());
        aggregateQuantity += (quantity - line.getQty());
        line.setQty(quantity);
        if (quantity == 0) {
            linesByBookId.remove(book.getId());
        }
    }

    /**
     * <pre>
     * return aggregateCost * ((100 - discount) * 0.01);
     * </pre>
     *
     * @param discount
     * @return
     */
    public double subTotal(double discount) {
        return aggregateCost * ((100 - discount) * 0.01);
    }

    /**
     * <pre>
     * return subTotal(discount) * 0.0825;
     * </pre>
     *
     * @param discount
     * @return
     */
    public double tax(double discount) {
        return subTotal(discount) * 0.0825;
    }

    /**
     * <pre>
     * return 3.00 + (1.00 * aggregateQuantity);
     * </pre>
     *
     * @return
     */
    public double shipCost() {
        return 3.00 + (1.00 * aggregateQuantity);
    }

    /**
     * <pre>
     * return subTotal(discount) + shipCost() + tax(discount);
     * </pre>
     *
     * @param discount
     * @return
     */
    public double total(double discount) {
        return subTotal(discount) + shipCost() + tax(discount);
    }

}
