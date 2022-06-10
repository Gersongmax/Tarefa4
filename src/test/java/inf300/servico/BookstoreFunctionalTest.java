package inf300.servico;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import inf300.dominio.Address;
import inf300.dominio.Author;
import inf300.dominio.Book;
import inf300.dominio.Cart;
import inf300.dominio.Customer;
import inf300.dominio.Order;

/**
 *
 * @author arthur
 */
public class BookstoreFunctionalTest {

    public BookstoreFunctionalTest() {
    }

    static Bookstore instance;

    @BeforeClass
    public static void setUpClass() {
        instance = Bookstore.getInstance();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isPopulated method, of class Bookstore.
     */
    @Test
    public void testIsPopulated() {
        System.out.println("isPopulated");
        boolean expResult = true;
        boolean result = instance.isPopulated();
        assertEquals(expResult, result);
    }

    /**
     * Test of alwaysGetAddress method, of class Bookstore.
     */
    @Test
    public void testAlwaysGetAddress() {
        System.out.println("alwaysGetAddress");
        String street1 = "";
        String street2 = "";
        String city = "";
        String state = "";
        String zip = "";
        String countryName = "";
        final String latitude = "";
        final String longitude = "";
        final String buildingNumber = "";
        Address result = instance.alwaysGetAddress(street1, street2, city, state, zip, countryName, latitude, longitude, buildingNumber);
        Address expResult = new Address(0, street1, street2, city, state, zip, result.getCountry(), latitude, longitude, buildingNumber);
        assertEquals(expResult, result);

    }

    /**
     * Test of getCustomer method, of class Bookstore.
     */
    @Test
    public void testGetCustomer_int() {
        System.out.println("getCustomer");
        int cId = 0;
        Customer result = instance.getCustomer(cId);
        assertEquals(cId, result.getId());
    }

    /**
     * Test of getCustomer method, of class Bookstore.
     */
    @Test
    public void testGetCustomer_String() {
        System.out.println("getCustomer");
        String username = instance.getCustomer(10).getUname();
        Customer result = instance.getCustomer(username).get();
        assertEquals(username, result.getUname());

    }

    /**
     * Test of createCustomer method, of class Bookstore.
     */
    @Test
    public void testCreateCustomer() {
        System.out.println("createCustomer");
        String fname = "";
        String lname = "";
        String street1 = "";
        String street2 = "";
        String city = "";
        String state = "";
        String zip = "";
        String countryName = "";
        final String latitude = "";
        final String longitude = "";
        final String buildingNumber = "";
        String phone = "";
        String email = "";
        double discount = 0.0;
        Date birthdate = null;
        String data = "";
        long now = 0L;

        Customer result = instance.createCustomer(fname, lname, street1, street2,
                city, state, zip, countryName, latitude, longitude, buildingNumber, phone,
                email, discount, birthdate, data, now);
        int id = result.getId();
        String uname = result.getUname();
        Date since = result.getSince();
        Date lastVisit = result.getLastVisit();
        Date login = result.getLogin();
        Date expiration = result.getExpiration();
        Address address = result.getAddress();
        Customer expResult = new Customer(id, uname, uname.toLowerCase(), fname, lname, phone, email, since, lastVisit,
                login, expiration, discount, 0, 0, birthdate, data, address);
        assertEquals(expResult, result);

    }

    /**
     * Test of refreshCustomerSession method, of class Bookstore.
     */
    @Test
    public void testRefreshCustomerSession() {
        System.out.println("refreshCustomerSession");
        int cId = 0;
        long now = 0L;
        instance.refreshCustomerSession(cId, now);
    }

    /**
     * Test of getBook method, of class Bookstore.
     */
    @Test
    public void testGetBook() {
        System.out.println("getBook");
        int bId = 0;
        Book result = instance.getBook(bId).get();
        assertEquals(bId, result.getId());

    }

    /**
     * Test of getBooksBySubject method, of class Bookstore.
     */
    @Test
    public void testGetBooksBySubject() {
        System.out.println("getBooksBySubject");
        String subject = "ARTS";
        List<Book> result = instance.getBooksBySubject(subject);
        assertEquals(51, result.stream().filter(book -> book.getSubject().equals(subject)).count());

    }

    /**
     * Test of getBooksByTitle method, of class Bookstore.
     */
    @Test
    public void testGetBooksByTitle() {
        System.out.println("getBooksByTitle");
        String title = instance.getBook(0).get().getTitle().substring(0, 4);
        List<Book> result = instance.getBooksByTitle(title);
        assertEquals(result.size(), result.stream().filter(book -> book.getTitle().startsWith(title)).count());
    }

    /**
     * Test of getBooksByAuthor method, of class Bookstore.
     */
    @Test
    public void testGetBooksByAuthor() {
        System.out.println("getBooksByAuthor");
        Author author = instance.getBook(0).get().getAuthor();
        List<Book> result = instance.getBooksByAuthor(author.getLname());
        assertEquals(result.size(),
                result.stream().filter(book -> book.getAuthor().getLname().equals(author.getLname())).count());

    }

    /**
     * Test of getNewBooks method, of class Bookstore.
     */
    @Test
    public void testGetNewBooks() {
        System.out.println("getNewBooks");
        String subject = instance.getBook(0).get().getSubject();
        List<Book> result = instance.getNewBooks(subject);
        assertEquals(result.size(), result.stream().filter(book -> book.getSubject().equals(subject)).count());

    }

    /**
     * Test of updateBook method, of class Bookstore.
     */
    @Test
    public void testUpdateBook() {
        System.out.println("updateBook");
        int bId = 0;
        double cost = 0.0;
        String image = "";
        String thumbnail = "";
        long now = 0L;
        Book book = instance.getBook(bId).get();
        instance.updateBook(bId, cost, image, thumbnail, now);
        assertEquals(bId, book.getId());
        assertEquals(cost, book.getCost(), 0.0);
        assertEquals(image, book.getImage());
        assertEquals(thumbnail, book.getThumbnail());
    }

    /**
     * Test of getCart method, of class Bookstore.
     */
    @Test
    public void testGetCart() {
        System.out.println("getCart");

    }

    /**
     * Test of cartUpdate method, of class Bookstore.
     */
    @Test
    public void testCartUpdate() {
        System.out.println("cartUpdate");
        long now = 0L;
        Cart expResult = instance.createCart(now);
        int cId = 0;
        Integer bId = 1;
        List<Integer> bIds = Arrays.asList(10, 20);
        List<Integer> quantities = Arrays.asList(10, 20);
        Cart result = instance.updateCart(cId, bId, bIds, quantities, now);
        assertEquals(expResult, result);

    }

    /**
     * Test of confirmBuy method, of class Bookstore.
     */
    @Test
    public void testConfirmBuy() {
        System.out.println("confirmBuy");
        int customerId = 0;
        int cartId = 0;
        String comment = "";
        String ccType = "";
        long ccNumber = 0L;
        String ccName = "";
        Date ccExpiry = null;
        String shipping = "Teste";
        Date shippingDate = null;
        int addressId = 0;
        long now = 0L;
        Order result = instance.confirmBuy(customerId, cartId, comment, ccType, ccNumber, ccName, ccExpiry, shipping,
                shippingDate, addressId, now);
        Order expResult = instance.getOrderById(result.getId());
        assertEquals(expResult, result);
    }

    /**
     * Test of confirmBuy method, of class Bookstore.
     */
    @Test
    public void testCancelOrder() {
        String expResult = "CANCELED";
        Order order1 = instance.getOrderById(0);
        Order order2 = instance.updateOrder(0, "PENDING");
        Order order3 = instance.cancelOrder(0);
        assertEquals(order1, order2);
        assertEquals(order2, order3);
        assertEquals(expResult, order1.getStatus());
    }

    /**
     * Desenvolver o teste funcional do método.
     */
    @Test
    public void testGetBestSellers() {
        System.out.println("testGetBestSellers");
         
       List<Book> books = instance.getBestSellers("ROMANCE");
       assertTrue(books.size() <=50 );
       
      
           
          
         
      
    
      
     
      
        // ... 
    }

    /**
     * Test of testGetNewBooksVersions method, of class Bookstore.
     */
    @Test
    public void testGetNewBooksVersions() {
        System.out.println("testGetNewBooksVersions");
        String subject = instance.getBook(10).get().getSubject();
        List<Book> result0 = instance.getNewBooks(subject);
        List<Book> result1 = instance.getNewBooks2(subject);
        List<Book> result2 = instance.getNewBooks3(subject);
        List<Book> result3 = instance.getNewBooks4(subject);

        assertArrayEquals(result0.toArray(), result1.toArray());
        assertArrayEquals(result0.toArray(), result2.toArray());
        assertArrayEquals(result0.toArray(), result3.toArray());
        
        //teste
       
        
     
    
       
    }

}
