package inf300.control;

import inf300.dominio.Book;
import inf300.servico.Bookstore;
import inf300.servico.Bookstore.Counter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 
 */
@Path("book")
public class BookController {

    @GET
    @Path("oi")
    @Produces(MediaType.TEXT_PLAIN)
    public Response oi() {
        return Response
                .ok("Olá!!")
                .build();
    }

    @GET
    @Path("oi-html")
    @Produces(MediaType.TEXT_HTML)
    public String oiHtml() {
        return "<html lang=\"pt-br\">"
                + "<head><META http-equiv=\"Content-Type\"content=\"text/html; charset=UTF-8\">"
                + "<title>Informações</title>"
                + "</head>"
                + "<body><h1>"
                + "Olá!"
                + "</h1></body>"
                + "<html>";
    }


    /**
     * curl http://localhost:8080/esoftXX/store/book/count?qty=50&subject=ARTS
     * @param qty
     * @param subject
     * @return
     */
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CounterTO> getCounters(@QueryParam("qty") final int qty, @QueryParam("subject") final String subject) {
        List<Counter> bestSellers = Bookstore.getInstance().getCounterSellers(qty,subject);
        return bestSellers
                .stream()
                .map(c -> new CounterTO(c)).collect(Collectors.toList());
    }

    @XmlRootElement(name = "book")
    public static class BookTO implements Serializable {

        private int id;
        private String title;
        private String subject;

        public BookTO() {
        }

        public BookTO(@NotNull Book book) {
            this.id = book.getId();
            this.title = book.getTitle();
            this.subject = book.getSubject();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
    }

    @XmlRootElement(name = "counter")
    public static class CounterTO extends BookTO implements Serializable {

        private int qty;

        public CounterTO() {
        }

        public CounterTO(@NotNull Counter counter) {
            super(counter.getBook());
            this.qty = counter.getCounter();
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }
    }

}
