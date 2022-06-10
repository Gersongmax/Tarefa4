package inf300.servico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import inf300.dominio.Address;
import inf300.dominio.Author;
import inf300.dominio.Book;
import inf300.dominio.CCTransaction;
import inf300.dominio.Cart;
import inf300.dominio.Country;
import inf300.dominio.Customer;
import inf300.dominio.Order;
import inf300.dominio.OrderLine;
import inf300.util.Populator;
import inf300.util.TPCW_Util;

/**
 * *<img src="./doc-files/Bookstore.png" alt="Bookstore">
 */
public class Bookstore implements Serializable {

    private static final long serialVersionUID = -3099048826035606338L;
    private static Bookstore instance;

    private boolean populated;
    private final List<Country> countryById;
    private final Map<String, Country> countryByName;
    private final List<Address> addressById;
    private final Map<Address, Address> addressByAll;
    private final List<Customer> customersById;
    private final Map<String, Customer> customersByUsername;
    private final List<Author> authorsById;
    private final List<Book> booksById;
    private final List<Cart> cartsById;
    private final List<Order> ordersById;
    private final LinkedList<Order> ordersByCreation;

    /**
     * <pre>
     *         populated = false;
     *         countryById = new ArrayList&lt;&gt;();
     *         countryByName = new HashMap&lt;&gt;();
     *         addressById = new ArrayList&lt;&gt;();
     *         addressByAll = new HashMap&lt;&gt;();
     *         customersById = new ArrayList&lt;&gt;();
     *         customersByUsername = new HashMap&lt;&gt;();
     *         authorsById = new ArrayList&lt;&gt;();
     *         booksById = new ArrayList&lt;&gt;();
     *         cartsById = new ArrayList&lt;&gt;();
     *         ordersById = new ArrayList&lt;&gt;();
     *         ordersByCreation = new LinkedList&lt;&gt;();
     * </pre>
     */
    private Bookstore() {
        countryById = new ArrayList<>();
        countryByName = new HashMap<>();
        addressById = new ArrayList<>();
        addressByAll = new HashMap<>();
        customersById = new ArrayList<>();
        customersByUsername = new HashMap<>();
        authorsById = new ArrayList<>();
        booksById = new ArrayList<>();
        cartsById = new ArrayList<>();
        ordersById = new ArrayList<>();
        ordersByCreation = new LinkedList<>();

    }

    public synchronized static Bookstore getInstance() {
        if (instance == null) {
            instance = new Bookstore();
        }
        if (!instance.populated) {
            long seed = 0;
            long now = System.currentTimeMillis();
            int items = 100000;
            int customers = 1000;
            int addresses = 1000;
            int authors = 100;
            int orders = 100000;
            new Populator(instance).populate(seed, now, items, customers, addresses, authors, orders);
            instance.populated = true;
        }
        return instance;
    }

    public List<Book> getBooksById() {
        return booksById;
    }

    /**
     *
     * @return
     */
    public boolean isPopulated() {
        return populated;
    }

    public void setPopulated(boolean populated) {
        this.populated = populated;
    }

    /**
     * <pre>
     *         Country country = countryByName.get(name);
     *         if (country == null) {
     *             country = createCountry(name, "", 0);
     *         }
     *         return country;
     * </pre>
     *
     * @param name
     * @return
     */
    private Country alwaysGetCountry(String name) {
        Country country = countryByName.get(name);
        if (country == null) {
            country = createCountry(name, "", 0);
        }
        return country;
    }

    /**
     * return countryById.get(random.nextInt(countryById.size()));
     *
     * @param random
     * @return
     */
    public Country getACountryAnyCountry(Random random) {
        return countryById.get(random.nextInt(countryById.size()));
    }

    public Country getCountryByName(String countryName) {
        Optional<Country> opC = countryById.stream().filter(c -> c.getName().toUpperCase().equals(countryName.toUpperCase())).findFirst();
        return opC.isPresent() ? opC.get() : null;
    }

    /**
     * <pre>
     * int id = countryById.size();
     * Country country = new Country(id, name, currency, exchange);
     * countryById.add(country);
     * countryByName.put(name, country);
     * return country;
     * </pre>
     *
     * @param name
     * @param currency
     * @param exchange
     * @return
     */
    public Country createCountry(String name, String currency, double exchange) {
        int id = countryById.size();
        Country country = new Country(id, name, currency, exchange);
        countryById.add(country);
        countryByName.put(name, country);
        return country;
    }

    /**
     * <pre>
     * Country country = alwaysGetCountry(countryName);
     * Address key = new Address(0, street1, street2, city, state, zip, country);
     * Address address = addressByAll.get(key);
     * if (address == null) {
     *  address = createAddress(street1, street2, city, state, zip, country);
     * }
     * return address;
     * </pre>
     *
     * @param street1
     * @param street2
     * @param city
     * @param state
     * @param zip
     * @param countryName
     * @return
     */
    public Address alwaysGetAddress(String street1, String street2,
            String city, String state, String zip, String countryName,
            String latitude, String longitude, String buildingNumber) {
        Country country = alwaysGetCountry(countryName);
        Address key = new Address(0, street1, street2, city, state, zip, country,
         latitude, longitude,  buildingNumber);
        Address address = addressByAll.get(key);
        if (address == null) {
            address = createAddress(street1, street2, city, state, zip,
                    country, latitude, longitude, buildingNumber);
        }
        return address;
    }

    /**
     *
     * @param random
     * @return
     */
    public Address getAnAddressAnyAddress(Random random) {
        return addressById.get(random.nextInt(addressById.size()));
    }

    public Address createAddress(String street1, String street2,
            String city, String state, String zip, Country country, 
            String latitude, String longitude, String buildingNumber) {
        int id = addressById.size();
        Address address = new Address(id, street1, street2, city, state, zip, country, latitude, longitude, buildingNumber);
        addressById.add(address);
        addressByAll.put(address, address);
        return address;
    }

    /**
     *
     * @param cId
     * @return
     */
    public Customer getCustomer(int cId) {
        return (cId >= customersById.size()) ? null : customersById.get(cId);
    }

    /**
     *
     * @param username
     * @return
     */
    public Optional<Customer> getCustomer(String username) {
        return Optional.ofNullable(customersByUsername.get(username));
    }

    public Customer getACustomerAnyCustomer(Random random) {
        return customersById.get(random.nextInt(customersById.size()));
    }

    /**
     * <pre>
     * Address address = alwaysGetAddress(street1, street2, city, state, zip,
     * countryName);
     * return createCustomer(fname, lname, address, phone, email,
     * new Date(now), new Date(now), new Date(now),
     * new Date(now + 7200000 ), discount, birthdate,
     * data);
     * </pre>
     *
     * @param fname
     * @param lname
     * @param street1
     * @param street2
     * @param city
     * @param state
     * @param zip
     * @param countryName
     * @param phone
     * @param email
     * @param discount
     * @param birthdate
     * @param data
     * @param now
     * @return
     */
    public Customer createCustomer(String fname, String lname, String street1,
            String street2, String city, String state, String zip,
            String countryName, String latitude, String longitude, 
            String buildingNumber, String phone, String email, double discount,
            Date birthdate, String data, long now) {
        Address address = alwaysGetAddress(street1, street2, city, state, zip,
                countryName, latitude,  longitude, buildingNumber);
        return createCustomer(fname, lname, address, phone, email,
                new Date(now), new Date(now), new Date(now),
                new Date(now + 7200000 /* 2 hours */), discount, birthdate,
                data);
    }

    /**
     * <pre>
     *  int id = customersById.size();
     * String uname = TPCW_Util.DigSyl(id, 0);
     * Customer customer = new Customer(id, uname, uname.toLowerCase(), fname,
     * lname, phone, email, since, lastVisit, login, expiration,
     * discount, 0, 0, birthdate, data, address);
     * customersById.add(customer);
     * customersByUsername.put(uname, customer);
     * return customer;
     * </pre>
     *
     * @param fname
     * @param lname
     * @param address
     * @param phone
     * @param email
     * @param since
     * @param lastVisit
     * @param login
     * @param expiration
     * @param discount
     * @param birthdate
     * @param data
     * @return
     */
    public Customer createCustomer(String fname, String lname, Address address,
            String phone, String email, Date since, Date lastVisit,
            Date login, Date expiration, double discount, Date birthdate,
            String data) {
        int id = customersById.size();
        String uname = TPCW_Util.DigSyl(id, 0);
        Customer customer = new Customer(id, uname, uname.toLowerCase(), fname,
                lname, phone, email, since, lastVisit, login, expiration,
                discount, 0, 0, birthdate, data, address);
        customersById.add(customer);
        customersByUsername.put(uname, customer);
        return customer;
    }

    /**
     * <pre>
     * Customer customer = getCustomer(cId);
     * if (customer != null) {
     * customer.setLogin(new Date(now));
     * customer.setExpiration(new Date(now + 7200000 ));
     * }
     * </pre>
     *
     * @param cId
     * @param now
     */
    public void refreshCustomerSession(int cId, long now) {
        Customer customer = getCustomer(cId);
        if (customer != null) {
            customer.setLogin(new Date(now));
            customer.setExpiration(new Date(now + 7200000 /* 2 hours */));
        }
    }

    public Author getAnAuthorAnyAuthor(Random random) {
        return authorsById.get(random.nextInt(authorsById.size()));
    }

    public Author createAuthor(String fname, String mname, String lname,
            Date birthdate, String bio) {
        Author author = new Author(fname, mname, lname, birthdate, bio);
        authorsById.add(author);
        return author;
    }

    /**
     *
     * @param bId
     * @return
     */
    public Optional<Book> getBook(int bId) {
        return Optional.ofNullable(booksById.get(bId));
    }

    public Book getABookAnyBook(Random random) {
        return booksById.get(random.nextInt(booksById.size()));
    }

    /**
     * <pre>
     * ArrayList&lt;Book&gt; books = new ArrayList&lt;&gt;();
     * for (Book book : booksById) {
     * if (subject.equals(book.getSubject())) {
     * books.add(book);
     * if (books.size() &gt; 50) {
     * break;
     * }
     * }
     * }
     * Collections.sort(books, (Book a, Book b) -&gt; a.getTitle().compareTo(b.getTitle()));
     * return books;
     * </pre>
     *
     * @param subject
     * @return
     */
    public List<Book> getBooksBySubject(String subject) {
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : booksById) {
            if (subject.equals(book.getSubject())) {
                books.add(book);
                if (books.size() > 50) {
                    break;
                }
            }
        }
        Collections.sort(books, (Book a, Book b) -> a.getTitle().compareTo(b.getTitle()));
        return books;
    }

    /**
     *
     * @param title
     * @return
     */
    public List<Book> getBooksByTitle(String title) {
        Pattern regex = Pattern.compile("^" + title);
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : booksById) {
            if (regex.matcher(book.getTitle()).matches()) {
                books.add(book);
                if (books.size() > 50) {
                    break;
                }
            }
        }
        Collections.sort(books, (Book a, Book b) -> a.getTitle().compareTo(b.getTitle()));
        return books;
    }

    /**
     *
     * @param author
     * @return
     */
    public List<Book> getBooksByAuthor(String author) {
        Pattern regex = Pattern.compile("^" + author);
        ArrayList<Book> books = new ArrayList<>();
        for (Book book : booksById) {
            if (regex.matcher(book.getAuthor().getLname()).matches()) {
                books.add(book);
                if (books.size() > 50) {
                    break;
                }
            }
        }
        Collections.sort(books, (Book a, Book b) -> a.getTitle().compareTo(b.getTitle()));
        return books;
    }

    /**
     * Retorna os 50 livros mais recentes (PubDate) por assunto
     *
     * @param subject
     * @return
     */
    public List<Book> getNewBooks(String subject) {
        return getNewBooks0(subject);
    }

    /**
     *
     * @param subject
     * @return
     */
    List<Book> getNewBooks0(String subject) {
        ArrayList<Book> books = new ArrayList<Book>();
        for (Book book : booksById) {
            if (subject.equals(book.getSubject())) {
                books.add(book);
            }
        }
        Collections.sort(books, new Comparator<Book>() {
            public int compare(Book a, Book b) {
                int result = b.getPubDate().compareTo(a.getPubDate());
                if (result == 0) {
                    result = a.getTitle().compareTo(b.getTitle());
                }
                return result;
            }
        });
        return books.subList(0, books.size() >= 50 ? 50 : books.size());
    }

    List<Book> getNewBooks1(String subject) {
        ArrayList<Book> books = new ArrayList<Book>();

        booksById.stream().forEach(book -> {
            if (subject.equals(book.getSubject())) {
                books.add(book);
            }
        });

        return books.stream().sorted((a, b) -> {
            int result = b.getPubDate().compareTo(a.getPubDate());
            if (result == 0) {
                result = a.getTitle().compareTo(b.getTitle());
            }
            return result;
        }).limit(50).collect(Collectors.toList());
    }

    /**
     *
     * @param subject
     * @return
     */
    List<Book> getNewBooks2(String subject) {
        List<Book> books = new ArrayList<>();
        booksById.stream()
                .filter((book) -> (subject.equals(book.getSubject())))
                .forEachOrdered((book) -> {
                    books.add(book);
                });
        Collections.sort(books, (Book a, Book b) -> {
            int result = b.getPubDate().compareTo(a.getPubDate());
            if (result == 0) {
                result = a.getTitle().compareTo(b.getTitle());
            }
            return result;
        });
        return books.subList(0, books.size() >= 50 ? 50 : books.size());
    }

    /**
     *
     * @param subject
     * @return
     */
    List<Book> getNewBooks3(String subject) {
        return booksById.stream()
                .filter((book) -> (subject.equals(book.getSubject())))
                .sorted((Book a, Book b) -> {
                    int result = b.getPubDate().compareTo(a.getPubDate());
                    if (result == 0) {
                        result = a.getTitle().compareTo(b.getTitle());
                    }
                    return result;
                }).limit(50)
                .collect(Collectors.toList());

    }

    /**
     *
     * @param subject
     * @return
     */
    List<Book> getNewBooks4(String subject) {
        return booksById.parallelStream()
                .filter((book) -> (subject.equals(book.getSubject())))
                .sorted((Book a, Book b) -> {
                    int result = b.getPubDate().compareTo(a.getPubDate());
                    if (result == 0) {
                        result = a.getTitle().compareTo(b.getTitle());
                    }
                    return result;
                }).limit(50)
                .collect(Collectors.toList());
    }

    /**
     *
     */
    public static class Counter {

        /**
         *
         */
        public final Book book;
        public int count;

        public Counter(Book book, int count) {
            this.book = book;
            this.count = count;
        }
        
        

        public Book getBook() {
            return this.book;
        }

        /**
         *
         */
        public int getCounter() {
            return this.count;
        }
    }

    /**
     * <b><u>Tarefa: desenvolver o método getBestSellers. </u></b><br><br>
     *  1 - Desenvolver o método
     * que busca os 50 livros mais vendidos "bestsellers" para um determinado
     * assunto (subject).<br> 2 - Desenvolver o teste funcional do método.<br>
     *
     *
     * @param subject
     * @return
     */
    public List<Book> getBestSellers(final String subject) {
    	
 HashMap<Book,Integer> sellCounter = new HashMap<Book,Integer>();
 List<Book> books = Bookstore.getInstance().getBooksById();
 
 
 int quantity;

 for(Book book : books) {
	 quantity = 0;
 
	 if ( book.getSubject().equals(subject)) {
		for (Order order : ordersByCreation) {
			 if (order.getStatus().equals("SHIPPED")){				 				 
		         for (OrderLine line : order.getLines()) {
			       if (line.getBook().equals(book)) {
			    	   quantity += line.getQty();
			       }
		         }
			 } 
		 }
		 Integer value = sellCounter.get(book);
		 if (value != null) {
			 sellCounter.put(book, value +quantity);
		 }else {
			 sellCounter.put(book, quantity);
		 }
		 
	 }
 }	 

LinkedHashMap<Book, Integer> sorted =
	sellCounter.entrySet()
		.stream()
		.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(50)
        .collect(Collectors.toMap(e-> e.getKey(), e-> e.getValue(),
        		(e1, e2) -> null,
        		() -> new LinkedHashMap<Book,Integer>()));
     System.out.println(sorted) ;

List<Book> sortedList = new ArrayList<> (sorted.keySet());

System.out.println(sortedList);

return sortedList;
        
	
 
 }
 	
       	
    
    /**
     *
     * @param subject
     * @return
     */
    public Book createBook(String title, Date pubDate, String publisher,
            String subject, String desc, String thumbnail,
            String image, double srp, double cost, Date avail, String isbn,
            int page, String backing, String dimensions, Author author,
            int stock) {
        int id = booksById.size();
        Book book = new Book(id, title, pubDate, publisher, subject, desc,
                thumbnail, image, srp, cost, avail, isbn, page, backing,
                dimensions, author, stock);
        booksById.add(book);
        return book;
    }

    /**
     *
     * @param bId
     * @param cost
     * @param image
     * @param thumbnail
     * @param now
     */
    public void updateBook(int bId, double cost, String image,
            String thumbnail, long now) {
        Book book = getBook(bId).get();
        book.setCost(cost);
        book.setImage(image);
        book.setThumbnail(thumbnail);
        book.setPubDate(new Date(now));
        updateRelatedBooks(book);
    }

    /**
     * For all the clients that bought this book in the last 10000 orders, what
     * are the five most sold books except this one.
     */
    private void updateRelatedBooks(Book targetBook) {
        HashSet<Integer> clientIds = new HashSet<>();
        int j = 0;
        Iterator<Order> i = ordersByCreation.iterator();
        while (i.hasNext() && j <= 10000) {
            Order order = i.next();
            for (OrderLine line : order.getLines()) {
                Book book = line.getBook();
                if (targetBook.getId() == book.getId()) {
                    clientIds.add(order.getCustomer().getId());
                    break;
                }
            }
            j++;
        }
        HashMap<Integer, Counter> counters = new HashMap<>();
        i = ordersByCreation.iterator();
        while (i.hasNext()) {
            Order order = i.next();
            if (clientIds.contains(order.getCustomer().getId())) {
                order.getLines().forEach((line) -> {
                    Book book = line.getBook();
                    if (targetBook.getId() != book.getId()) {
                        Counter counter = counters.get(book.getId());
                        if (counter == null) {
                            counter = new Counter(book, 0);
                            counters.put(book.getId(), counter);
                        }
                        counter.count += line.getQty();
                    }
                });
            }
        }
        Counter[] sorted = counters.values().toArray(new Counter[]{});
        Arrays.sort(sorted, (Counter a, Counter b) -> {
            if (b.count > a.count) {
                return 1;
            }
            return b.count < a.count ? -1 : 0;
        });
        Book[] related = new Book[]{targetBook, targetBook, targetBook,
            targetBook, targetBook};
        for (j = 0; j < 5 && j < sorted.length; j++) {
            related[j] = sorted[j].book;
        }
        targetBook.setRelated1(related[0]);
        targetBook.setRelated2(related[1]);
        targetBook.setRelated3(related[2]);
        targetBook.setRelated4(related[3]);
        targetBook.setRelated5(related[4]);
    }

    /**
     *
     * @param id
     * @return
     */
    public Cart getCart(int id) {
        return cartsById.get(id);
    }

    /**
     *
     * @param now
     * @return
     */
    public Cart createCart(long now) {
        int id = cartsById.size();
        Cart cart = new Cart(id, new Date(now));
        cartsById.add(cart);
        return cart;
    }

    /**
     *
     * @param cId
     * @param bId
     * @param bIds
     * @param quantities
     * @param now
     * @return
     */
    public Cart updateCart(int cId, Integer bId, List<Integer> bIds,
                           List<Integer> quantities, long now) {
        Cart cart = getCart(cId);

        if (bId != null) {
            cart.increaseLine(getBook(bId).get(), 1);
        }

        for (int i = 0; i < bIds.size(); i++) {
            cart.changeLine(booksById.get(bIds.get(i)), quantities.get(i));
        }

        cart.setTime(new Date(now));

        return cart;
    }

    /**
     *
     * @param customerId
     * @param cartId
     * @param comment
     * @param ccType
     * @param ccNumber
     * @param ccName
     * @param ccExpiry
     * @param shipping
     * @param shippingDate
     * @param addressId
     * @param now
     * @return
     */
    public Order confirmBuy(int customerId, int cartId, String comment,
            String ccType, long ccNumber, String ccName, Date ccExpiry,
            String shipping, Date shippingDate, int addressId, long now) {
        Customer customer = getCustomer(customerId);
        Cart cart = getCart(cartId);
        Address shippingAddress = customer.getAddress();
        if (addressId != -1) {
            shippingAddress = addressById.get(addressId);
        }
        cart.getLines().stream().map((cartLine) -> {
            Book book = cartLine.getBook();
            book.addStock(-cartLine.getQty());
            return book;
        }).filter((book) -> (book.getStock() < 10)).forEachOrdered((book) -> {
            book.addStock(21);
        });
        CCTransaction ccTransact = new CCTransaction(ccType, ccNumber, ccName,
                ccExpiry, "", cart.total(customer.getDiscount()),
                new Date(now), shippingAddress.getCountry());
        return createOrder(customer, new Date(now), cart, comment, shipping,
                shippingDate, "Pending", customer.getAddress(),
                shippingAddress, ccTransact);
    }

    public Order createOrder(Customer customer, Date date, Cart cart,
            String comment, String shipType, Date shipDate,
            String status, Address billingAddress, Address shippingAddress,
            CCTransaction cc) {
        int id = ordersById.size();
        Order order = new Order(id, customer, date, cart, comment, shipType,
                shipDate, status, billingAddress, shippingAddress, cc);
        ordersById.add(order);
        ordersByCreation.addFirst(order);
        customer.logOrder(order);
        cart.clear();
        return order;
    }

    public Order getOrderById(int id){
        if(id >= ordersById.size())
            return null;
        if(id < 0)
            return null;
        return ordersById.get(id);
    }

    public Order updateOrder(int id, String status) {
        Order order = getOrderById(id);
        if(order != null) {
            order.setStatus(status);
        }
        return order;
    }

    public Order cancelOrder(int id) {
        Order order = getOrderById(id);
        if(order != null) {
            if ("PENDING".equals(order.getStatus().toUpperCase())) {
                order.setStatus("CANCELED");
            }
        }
        return order;
    }

}
