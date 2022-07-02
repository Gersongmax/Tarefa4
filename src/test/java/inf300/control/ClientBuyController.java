package inf300.control;

import inf300.control.BookController.CounterTO;
import inf300.control.BuyController.CartTO;
import inf300.control.BuyController.OrderTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



public class ClientBuyController {
	  public ClientBuyController() {
	    }

	    public static void main(String args[]) {
	        String url = "http://localhost:9999/esoft41/store";
	        System.out.println("get");
	        Client client = ClientBuilder.newClient();
	        
	        
	        ////////////////////////////////////////
	        List<CounterTO> counter = client.target(url + "/book/count")
	                .queryParam("qty", 1)
	                .queryParam("subject", "ARTS")
	                .request(MediaType.APPLICATION_JSON)
	                .get(new GenericType<List<CounterTO>>() {
	                });
	        System.out.println("1 " + counter);
	        ////////////////////
	        Response resp = client.target(url + "/buy/cart/create")
	                .request()
	                .post(Entity.entity(new CartTO(), MediaType.APPLICATION_JSON));

	        ////////////////////
	        CartTO cart = resp.readEntity(CartTO.class);
	        System.out.println("2 " + resp.getStatus());
	        System.out.println("2 " + cart);
	        ///////////////////
	        CartTO cart2 = (CartTO) client.target(url + "/buy/cart")
	                .path(String.valueOf(cart.getId()))
	                .request(MediaType.APPLICATION_JSON).get(CartTO.class);
	        System.out.println("3 " + cart2);
	        ///////////////////
	        List<Integer> qty = new ArrayList<>();
	        List<Integer> bIds = new ArrayList<>();
	        qty.add(100);
	        bIds.add(counter.get(0).getId());
	        cart2.setQty(qty);
	        cart2.setbIds(bIds);
	        client.target(url + "/buy/cart/update")
	                .request(MediaType.APPLICATION_JSON)
	                .put(Entity.entity(cart2, MediaType.APPLICATION_JSON))
	                .readEntity(CartTO.class);

	        ///////////////////
	        CartTO cart3 = (CartTO) client.target(url + "/buy/cart")
	                .path(String.valueOf(cart2.getId()))
	                .request(MediaType.APPLICATION_JSON).get(CartTO.class);
	        System.out.println("4 " + cart3);
	        ////////////////////
	        OrderTO order = client.target(url + "/buy/create")
	                .request()
	                .post(Entity.entity(new BuyController.BuyTO(403, cart3.getId(), "", "VISA", 1234, "XX YY",
	                        new Date(), "FEDEX",
	                        new Date(), 1), MediaType.APPLICATION_JSON)).readEntity(OrderTO.class);	        
	        System.out.println("5 " + order);
	        /////////////////////////
	        OrderTO order1 = client.target(url + "/buy/order")
	                .path(String.valueOf(order.getId()))
	                .request()
	                .get(OrderTO.class);
	        System.out.println("6 " + order1);
	        //////////////////////
	        OrderTO order2 = client.target(url + "/buy/order")
	                .path(String.valueOf(order.getId()))
	                .path("status")
	                .path("SHIPPED")
	                .request()
	                .put(Entity.entity(order1, MediaType.APPLICATION_JSON))
	                .readEntity(OrderTO.class);
	        System.out.println("7 " + order2);
	        ///////////////////////
	        counter = client.target(url + "/book/count")
	                .queryParam("qty", 1)
	                .queryParam("subject", "ARTS")
	                .request(MediaType.APPLICATION_JSON)
	                .get(new GenericType<List<CounterTO>>() {
	                });
	        System.out.println("8 " + counter);

	    }

	}



