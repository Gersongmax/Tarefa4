package inf300.util;

import com.github.javafaker.Faker;
import com.github.javafaker.Internet;
import com.github.javafaker.Name;
import com.github.javafaker.PhoneNumber;
import inf300.dominio.Address;
import inf300.dominio.Author;
import inf300.dominio.Book;
import inf300.dominio.CCTransaction;
import inf300.dominio.Cart;
import inf300.dominio.Country;
import inf300.dominio.Customer;
import inf300.servico.Bookstore;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author 
 */
public class Populator {

    private final Bookstore bookstore;
    private final Faker faker = new Faker();

    public Populator(Bookstore bookstore) {
        this.bookstore = bookstore;
    }

    /**
     *
     * @param seed
     * @param now
     * @param items
     * @param customers
     * @param addresses
     * @param authors
     * @param orders
     * @return
     */
    public boolean populate(long seed, long now, int items, int customers,
            int addresses, int authors, int orders) {
        if (bookstore.isPopulated()) {
            return false;
        }
        System.out.println("Beginning TPCW population.");
        Random rand = new Random(seed);
        populateCountries();
        populateAddresses(addresses, rand);
        populateCustomers(customers, rand, now);
        populateAuthorTable(authors, rand);
        populateBooks(items, rand);
        populateOrders(orders, rand, now);
        bookstore.setPopulated(true);
        System.out.println("Finished TPCW population.");
        return true;
    }

    private void populateCountries() {
        String[] countries = {"United States", "United Kingdom", "Canada",
            "Germany", "France", "Japan", "Netherlands",
            "Italy", "Switzerland", "Australia", "Algeria",
            "Argentina", "Armenia", "Austria", "Azerbaijan",
            "Bahamas", "Bahrain", "Bangla Desh", "Barbados",
            "Belarus", "Belgium", "Bermuda", "Bolivia",
            "Botswana", "Brazil", "Bulgaria", "Cayman Islands",
            "Chad", "Chile", "China", "Christmas Island",
            "Colombia", "Croatia", "Cuba", "Cyprus",
            "Czech Republic", "Denmark", "Dominican Republic",
            "Eastern Caribbean", "Ecuador", "Egypt",
            "El Salvador", "Estonia", "Ethiopia",
            "Falkland Island", "Faroe Island", "Fiji",
            "Finland", "Gabon", "Gibraltar", "Greece", "Guam",
            "Hong Kong", "Hungary", "Iceland", "India",
            "Indonesia", "Iran", "Iraq", "Ireland", "Israel",
            "Jamaica", "Jordan", "Kazakhstan", "Kuwait",
            "Lebanon", "Luxembourg", "Malaysia", "Mexico",
            "Mauritius", "New Zealand", "Norway", "Pakistan",
            "Philippines", "Poland", "Portugal", "Romania",
            "Russia", "Saudi Arabia", "Singapore", "Slovakia",
            "South Africa", "South Korea", "Spain", "Sudan",
            "Sweden", "Taiwan", "Thailand", "Trinidad",
            "Turkey", "Venezuela", "Zambia"};

        double[] exchanges = {1, .625461, 1.46712, 1.86125, 6.24238, 121.907,
            2.09715, 1842.64, 1.51645, 1.54208, 65.3851,
            0.998, 540.92, 13.0949, 3977, 1, .3757,
            48.65, 2, 248000, 38.3892, 1, 5.74, 4.7304,
            1.71, 1846, .8282, 627.1999, 494.2, 8.278,
            1.5391, 1677, 7.3044, 23, .543, 36.0127,
            7.0707, 15.8, 2.7, 9600, 3.33771, 8.7,
            14.9912, 7.7, .6255, 7.124, 1.9724, 5.65822,
            627.1999, .6255, 309.214, 1, 7.75473, 237.23,
            74.147, 42.75, 8100, 3000, .3083, .749481,
            4.12, 37.4, 0.708, 150, .3062, 1502, 38.3892,
            3.8, 9.6287, 25.245, 1.87539, 7.83101,
            52, 37.8501, 3.9525, 190.788, 15180.2,
            24.43, 3.7501, 1.72929, 43.9642, 6.25845,
            1190.15, 158.34, 5.282, 8.54477, 32.77, 37.1414,
            6.1764, 401500, 596, 2447.7};

        String[] currencies = {"Dollars", "Pounds", "Dollars", "Deutsche Marks",
            "Francs", "Yen", "Guilders", "Lira", "Francs",
            "Dollars", "Dinars", "Pesos", "Dram",
            "Schillings", "Manat", "Dollars", "Dinar", "Taka",
            "Dollars", "Rouble", "Francs", "Dollars",
            "Boliviano", "Pula", "Real", "Lev", "Dollars",
            "Franc", "Pesos", "Yuan Renmimbi", "Dollars",
            "Pesos", "Kuna", "Pesos", "Pounds", "Koruna",
            "Kroner", "Pesos", "Dollars", "Sucre", "Pounds",
            "Colon", "Kroon", "Birr", "Pound", "Krone",
            "Dollars", "Markka", "Franc", "Pound", "Drachmas",
            "Dollars", "Dollars", "Forint", "Krona", "Rupees",
            "Rupiah", "Rial", "Dinar", "Punt", "Shekels",
            "Dollars", "Dinar", "Tenge", "Dinar", "Pounds",
            "Francs", "Ringgit", "Pesos", "Rupees", "Dollars",
            "Kroner", "Rupees", "Pesos", "Zloty", "Escudo",
            "Leu", "Rubles", "Riyal", "Dollars", "Koruna",
            "Rand", "Won", "Pesetas", "Dinar", "Krona",
            "Dollars", "Baht", "Dollars", "Lira", "Bolivar",
            "Kwacha"};

        System.out.print("Creating " + countries.length + " countries...");

        for (int i = 0; i < countries.length; i++) {
            bookstore.createCountry(countries[i], currencies[i], exchanges[i]);
        }

        System.out.println(" Done");
    }

    private void populateAddresses(int number, Random rand) {
        System.out.print("Creating " + number + " addresses...");

        for (int i = 0; i < number; i++) {
            if (i % 10000 == 0) {
                System.out.print(".");
            }

            final com.github.javafaker.Address fa = faker.address();
            final String street1 = fa.streetAddress();
            final String street2 = fa.streetAddress(true);
            final String city = fa.cityName();
            final String state = fa.state();
            final String zip = fa.zipCode();
            final String latitude = fa.latitude();
            final String longitude = fa.longitude();
            final String buildingNumber = fa.buildingNumber();
            
            Country country = bookstore.getCountryByName(fa.country());
            if(country == null){
                country = bookstore.getACountryAnyCountry(rand);
            }
            bookstore.createAddress(street1, street2,
                    city, state, zip, country, latitude,longitude, buildingNumber);
        }

        System.out.println(" Done");
    }

    private void populateCustomers(int number, Random rand, long now) {
        System.out.print("Creating " + number + " customers...");

        for (int i = 0; i < number; i++) {
            if (i % 10000 == 0) {
                System.out.print(".");
            }

            final Name name = faker.name();
            final PhoneNumber phoneNumber = faker.phoneNumber();
            final Internet internet = faker.internet();
            //
            final String fname = name.firstName();
            final String lname = name.lastName();
            final Address address = bookstore.getAnAddressAnyAddress(rand);
            final String phone = phoneNumber.phoneNumber();
            final String email = internet.emailAddress();
            final Date since = new Date(now - TPCW_Util.getRandomInt(rand, 1, 730) * 86400000 /* a day */);
            final Date lastVisit = new Date(now - TPCW_Util.getRandomInt(rand, 1, 730) * 86400000 + TPCW_Util.getRandomInt(rand, 0, 60) * 86400000 /* a day */);
            final Date login = new Date(now);
            final Date expiration = new Date(now + 7200000 /* 2 hours */);
            final double discount = rand.nextInt(51);
            final Date birthdate = TPCW_Util.getRandomBirthdate(rand);
            final String data = TPCW_Util.getRandomString(rand, 100, 500);

            bookstore.createCustomer(fname, lname, address,
                    phone, email, since, lastVisit,
                    login, expiration, discount, birthdate,
                    data);
        }

        System.out.println(" Done");
    }

    private void populateAuthorTable(int number, Random rand) {
        System.out.print("Creating " + number + " authors...");

        for (int i = 0; i < number; i++) {
            if (i % 10000 == 0) {
                System.out.print(".");
            }
            final String fname = TPCW_Util.getRandomString(rand, 3, 20);
            final String mname = TPCW_Util.getRandomString(rand, 1, 20);
            final String lname = TPCW_Util.getRandomLname(rand);
            final Date birthdate = TPCW_Util.getRandomBirthdate(rand);
            final String bio = TPCW_Util.getRandomString(rand, 125, 500);
            bookstore.createAuthor(fname, mname, lname,
                    birthdate, bio);
        }

        System.out.println(" Done");
    }

    private void populateBooks(int number, Random rand) {
        String[] SUBJECTS = {"ARTS", "BIOGRAPHIES", "BUSINESS", "CHILDREN",
            "COMPUTERS", "COOKING", "HEALTH", "HISTORY",
            "HOME", "HUMOR", "LITERATURE", "MYSTERY",
            "NON-FICTION", "PARENTING", "POLITICS",
            "REFERENCE", "RELIGION", "ROMANCE",
            "SELF-HELP", "SCIENCE-NATURE", "SCIENCE_FICTION",
            "SPORTS", "YOUTH", "TRAVEL"};
        String[] BACKINGS = {"HARDBACK", "PAPERBACK", "USED", "AUDIO",
            "LIMITED-EDITION"};

        System.out.print("Creating " + number + " books...");

        for (int i = 0; i < number; i++) {
            if (i % 10000 == 0) {
                System.out.print(".");
            }

            final com.github.javafaker.Book fbook = faker.book();
            final String title = fbook.title();
            final Date pubDate = TPCW_Util.getRandomPublishdate(rand);
            final String publisher = fbook.publisher();
            final String subject = SUBJECTS[rand.nextInt(SUBJECTS.length)];
            final String desc = TPCW_Util.getRandomString(rand, 100, 500);
            final String thumbnail = "img" + i % 100 + "/thumb_" + i + ".gif";
            final String image = "img" + i % 100 + "/image_" + i + ".gif";
            final double srp = TPCW_Util.getRandomInt(rand, 100, 99999) / 100.0;
            final double cost = srp * TPCW_Util.getRandomInt(rand, 50, 100) / 100.0;
            final Date avail = new Date(pubDate.getTime()
                    + TPCW_Util.getRandomInt(rand, 1, 30) * 86400000 /* a day */);
            final String isbn = TPCW_Util.getRandomString(rand, 13, 13);
            final int page = TPCW_Util.getRandomInt(rand, 20, 9999);
            final String backing = BACKINGS[rand.nextInt(BACKINGS.length)];
            final String dimensions = (TPCW_Util.getRandomInt(rand, 1, 9999) / 100.0) + "x"
                    + (TPCW_Util.getRandomInt(rand, 1, 9999) / 100.0) + "x"
                    + (TPCW_Util.getRandomInt(rand, 1, 9999) / 100.0);
            final Author author = bookstore.getAnAuthorAnyAuthor(rand);
            final int stock = TPCW_Util.getRandomInt(rand, 10, 30);
            bookstore.createBook(title, pubDate, publisher,
                    subject, desc, thumbnail,
                    image, srp, cost, avail, isbn,
                    page, backing, dimensions, author,
                    stock);
        }

        for (int i = 0; i < number; i++) {
            Book book = bookstore.getBooksById().get(i);
            HashSet<Book> related = new HashSet<>();
            while (related.size() < 5) {
                Book relatedBook = bookstore.getABookAnyBook(rand);
                if (relatedBook.getId() != i) {
                    related.add(relatedBook);
                }
            }
            Book[] relatedArray = new Book[]{bookstore.getBooksById().get(TPCW_Util.getRandomInt(rand, 0, number - 1)),
                bookstore.getBooksById().get(TPCW_Util.getRandomInt(rand, 0, number - 1)),
                bookstore.getBooksById().get(TPCW_Util.getRandomInt(rand, 0, number - 1)),
                bookstore.getBooksById().get(TPCW_Util.getRandomInt(rand, 0, number - 1)),
                bookstore.getBooksById().get(TPCW_Util.getRandomInt(rand, 0, number - 1))};
            relatedArray = related.toArray(relatedArray);
            book.setRelated1(relatedArray[0]);
            book.setRelated2(relatedArray[1]);
            book.setRelated3(relatedArray[2]);
            book.setRelated4(relatedArray[3]);
            book.setRelated5(relatedArray[4]);
        }

        System.out.println(" Done");
    }

    void populateOrders(int number, Random rand, long now) {
        String[] credit_cards = {"VISA", "MASTERCARD", "DISCOVER",
            "AMEX", "DINERS"};
        String[] ship_types = {"AIR", "UPS", "FEDEX", "SHIP", "COURIER",
            "MAIL"};
        String[] status_types = {"PROCESSING", "SHIPPED", "PENDING",
            "DENIED", "CANCELED"};

        System.out.print("Creating " + number + " orders...");

        for (int i = 0; i < number; i++) {
            if (i % 10000 == 0) {
                System.out.print(".");
            }
            int nBooks = TPCW_Util.getRandomInt(rand, 1, 5);
            Cart cart = new Cart(-1, null);
            String comment = TPCW_Util.getRandomString(rand, 20, 100);
            for (int j = 0; j < nBooks; j++) {
                Book book = bookstore.getABookAnyBook(rand);
                int quantity = TPCW_Util.getRandomInt(rand, 1, 300);
                cart.changeLine(book, quantity);
            }

            Customer customer = bookstore.getACustomerAnyCustomer(rand);
            CCTransaction ccTransact = new CCTransaction(
                    credit_cards[rand.nextInt(credit_cards.length)],
                    TPCW_Util.getRandomLong(rand, 1000000000000000L, 9999999999999999L),
                    TPCW_Util.getRandomString(rand, 14, 30),
                    new Date(now + TPCW_Util.getRandomInt(rand, 10, 730) * 86400000 /* a day */),
                    TPCW_Util.getRandomString(rand, 15, 15),
                    cart.total(customer.getDiscount()),
                    new Date(now),
                    bookstore.getACountryAnyCountry(rand));
            long orderDate = now - TPCW_Util.getRandomInt(rand, 53, 60) * 86400000 /* a day */;
            long shipDate = orderDate + TPCW_Util.getRandomInt(rand, 0, 7) * 86400000 /* a day */;
            bookstore.createOrder(
                    customer,
                    new Date(orderDate),
                    cart, comment,
                    ship_types[rand.nextInt(ship_types.length)],
                    new Date(shipDate),
                    status_types[rand.nextInt(status_types.length)],
                    bookstore.getAnAddressAnyAddress(rand),
                    bookstore.getAnAddressAnyAddress(rand),
                    ccTransact);
        }

        System.out.println(" Done");
    }

}
