package inf300.dominio;

import java.io.Serializable;
import java.util.Date;

/**
 * *<img src="./doc-files/Book.png" alt="Book">
 */
public class Book implements Serializable {

    private static final long serialVersionUID = 6505830715531808617L;

    private final int id;
    private final String title;
    private Date pubDate;
    private final String publisher;
    private final String subject;
    private final String desc;
    private Book related1;
    private Book related2;
    private Book related3;
    private Book related4;
    private Book related5;
    private String thumbnail;
    private String image;
    private final double srp;
    private double cost;
    private final Date avail;
    private final String isbn;
    private final int page;
    private final String backing;
    private final String dimensions;
    private final Author author;
    private int stock;

    /**
     *
     * @param id
     * @param title
     * @param pubDate
     * @param publisher
     * @param subject
     * @param desc
     * @param thumbnail
     * @param image
     * @param srp
     * @param cost
     * @param avail
     * @param isbn
     * @param page
     * @param backing
     * @param dimensions
     * @param author
     * @param stock
     */
    public Book(int id, String title, Date pubDate, String publisher,
            String subject, String desc, String thumbnail,
            String image, double srp, double cost, Date avail, String isbn,
            int page, String backing, String dimensions, Author author,
            int stock) {
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
        this.publisher = publisher;
        this.subject = subject;
        this.desc = desc;
        this.related1 = null;
        this.related2 = null;
        this.related3 = null;
        this.related4 = null;
        this.related5 = null;
        this.thumbnail = thumbnail;
        this.image = image;
        this.srp = srp;
        this.cost = cost;
        this.avail = avail;
        this.isbn = isbn;
        this.page = page;
        this.backing = backing;
        this.dimensions = dimensions;
        this.author = author;
        this.stock = stock;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     *
     * @param thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     *
     * @param cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     *
     * @return
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     */
    public Author getAuthor() {
        return author;
    }

    /**
     *
     * @return
     */
    public double getSrp() {
        return srp;
    }

    /**
     *
     * @return
     */
    public double getCost() {
        return cost;
    }

    /**
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     *
     * @return
     */
    public int getPage() {
        return page;
    }

    /**
     *
     * @return
     */
    public String getBacking() {
        return backing;
    }

    /**
     *
     * @return
     */
    public Date getPubDate() {
        return pubDate;
    }

    /**
     *
     * @param pubDate
     */
    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    /**
     *
     * @return
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     *
     * @return
     */
    public String getIsbn() {
        return isbn;
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
    public String getDimensions() {
        return dimensions;
    }

    /**
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @return
     */
    public Date getAvail() {
        return avail;
    }

    /**
     *
     * @return
     */
    public Book getRelated1() {
        return related1;
    }

    /**
     *
     * @return
     */
    public Book getRelated2() {
        return related2;
    }

    /**
     *
     * @return
     */
    public Book getRelated3() {
        return related3;
    }

    /**
     *
     * @return
     */
    public Book getRelated4() {
        return related4;
    }

    /**
     *
     * @return
     */
    public Book getRelated5() {
        return related5;
    }

    /**
     *
     * @param related1
     */
    public void setRelated1(Book related1) {
        this.related1 = related1;
    }

    /**
     *
     * @param related2
     */
    public void setRelated2(Book related2) {
        this.related2 = related2;
    }

    /**
     *
     * @param related3
     */
    public void setRelated3(Book related3) {
        this.related3 = related3;
    }

    /**
     *
     * @param related4
     */
    public void setRelated4(Book related4) {
        this.related4 = related4;
    }

    /**
     *
     * @param related5
     */
    public void setRelated5(Book related5) {
        this.related5 = related5;
    }

    /**
     *
     * @return
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     *
     * @param amount
     */
    public void addStock(int amount) {
        stock += amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Book) {
            Book book = (Book) o;
            return id == book.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
