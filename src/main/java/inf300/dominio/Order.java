package inf300.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * *<img src="./doc-files/Order.png" alt="Order">
 */
public class Order implements Serializable {

    private static final long serialVersionUID = -1106285234830970111L;

    private final int id;
    private final Customer customer;
    private final Date date;
    private final double subtotal;
    private final double tax;
    private final double total;
    private final String shipType;
    private final Date shipDate;
    private String status;
    private final Address billingAddress;
    private final Address shippingAddress;
    private final CCTransaction cc;
    private final ArrayList<OrderLine> lines;

    /**
     * <pre>
     * this.id = id;
     * this.customer = customer;
     * this.date = date;
     * subtotal = cart.subTotal(customer.getDiscount());
     * tax = 8.25;
     * total = cart.total(customer.getDiscount());
     * this.shipType = shipType;
     * this.shipDate = shipDate;
     * this.status = status;
     * this.billingAddress = billingAddress;
     * this.shippingAddress = shippingAddress;
     * this.cc = cc;
     * lines = new ArrayList&lt;OrderLine&gt;(cart.getLines().size());
     * for (CartLine cartLine : cart.getLines()) {
     * OrderLine line = new OrderLine(cartLine.getBook(),
     * cartLine.getQty(), customer.getDiscount(),
     * comment);
     * lines.add(line);
     * }
     * </pre>
     *
     * @param id
     * @param customer
     * @param date
     * @param cart
     * @param comment
     * @param shipType
     * @param shipDate
     * @param status
     * @param billingAddress
     * @param shippingAddress
     * @param cc
     */
    public Order(int id, Customer customer, Date date, Cart cart,
            String comment, String shipType, Date shipDate, String status,
            Address billingAddress, Address shippingAddress, CCTransaction cc) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        subtotal = cart.subTotal(customer.getDiscount());
        tax = 8.25;
        total = cart.total(customer.getDiscount());
        this.shipType = shipType;
        this.shipDate = shipDate;
        this.status = status;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.cc = cc;
        lines = new ArrayList<OrderLine>(cart.getLines().size());
        for (CartLine cartLine : cart.getLines()) {
            OrderLine line = new OrderLine(cartLine.getBook(),
                    cartLine.getQty(), customer.getDiscount(),
                    comment);
            lines.add(line);
        }
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
    public Customer getCustomer() {
        return customer;
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
    public double getSubtotal() {
        return subtotal;
    }

    /**
     *
     * @return
     */
    public double getTax() {
        return tax;
    }

    /**
     *
     * @return
     */
    public double getTotal() {
        return total;
    }

    /**
     *
     * @return
     */
    public String getShipType() {
        return shipType;
    }

    /**
     *
     * @return
     */
    public Date getShipDate() {
        return shipDate;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     *
     * @return
     */
    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     *
     * @return
     */
    public CCTransaction getCC() {
        return cc;
    }

    /**
     *
     * @return
     */
    public ArrayList<OrderLine> getLines() {
        return lines;
    }

}
