package inf300.control;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import inf300.dominio.Cart;
import inf300.dominio.Order;
import inf300.servico.Bookstore;

@Path("buy")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class BuyController {
	
	public BuyController() {
	}
	
	@GET
	@Path("Oi")
	@Produces(MediaType.TEXT_PLAIN)
	public Response oi() {
		return Response.ok("Olá").build();
	}
	
	
	Bookstore bookstore = Bookstore.getInstance();
	
	// getCart
	@GET
	@Path("/cart/{id}")
	public BuyController.CartTO getCart(@PathParam(value = "id") int idCart) {
		return new CartTO(bookstore.getCart(idCart));
	}

	// getOrder
	@GET
	@Path("/order/{id}")
	public BuyController.OrderTO getOrder(@PathParam(value = "id") int id){
		return new OrderTO(bookstore.getOrderById(id));
	}

	// createCart
	@POST
	@Path("cart/create")
	public BuyController.CartTO createCart(){
	
	return new CartTO(bookstore.createCart(Instant.now().getEpochSecond()));
		

				
	}

	// updateCart
	@PUT
	@Path("/cart/update")
	public BuyController.CartTO updateCart(BuyController.CartTO cart){
		
		return new CartTO(bookstore.updateCart(cart.getId(), null, cart.getbIds(), cart.getQty(), new Date().getTime()));
		
		
	}

	// confirmBuy
	@POST
	@Path("/create/")
	public BuyController.OrderTO confirmBuy(BuyController.BuyTO buy){
		
		return new OrderTO(bookstore.confirmBuy(buy.getCustomerId(),buy.getCartId(), buy.getComment(), buy.getCcType(), buy.getCcNumber(), buy.getCcName(),buy.getCcExpiry(),buy.getShipping(),buy.getShippingDate(), buy.getAddressId(), new Date().getTime()));
		
	}
	

	// updateOrder
	@PUT
	@Path(value = "/order/{id:[0-9]{1,9}}/status/{status: (PROCESSING|SHIPPED|PENDING|DENIED|CANCELED)}")
	public BuyController.OrderTO updateOrder(@PathParam(value = "id") int idOrder,
			@PathParam(value = "status") String newStatus){
				return new OrderTO(bookstore.updateOrder(idOrder, newStatus));
		
		
	}

	// cancelOrder
	@PUT
	@Path(value = "/order/cancel")
	public BuyController.OrderTO cancelOrder(@DefaultValue(value = "1") @QueryParam(value = "id") int id1){
		return new OrderTO(bookstore.cancelOrder(id1));
		
		
	}
	
	

	@XmlRootElement(name = "buy")
	public static class BuyTO 	{

		public BuyTO() {
		}
		
		public BuyTO 
			(int customerId, int cartId, String comment, String ccType, long ccNumber, String ccName, Date ccExpiry, String shipping, Date shippingDate, int addressId){
			
		}

		private int cartId;
		private String comment;
		String ccType;
		private long ccNumber;
		private String ccName;
		private Date ccExpiry;
		private String shipping;
		private Date shippingDate;
		private int addressId;
		private int customerId;

		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

		public int getCartId() {
			return cartId;
		}

		public void setCartId(int cartId) {
			this.cartId = cartId;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public String getCcType() {
			return ccType;
		}

		public void setCcType(String ccType) {
			this.ccType = ccType;
		}

		public long getCcNumber() {
			return ccNumber;
		}

		public void setCcNumber(long ccNumber) {
			this.ccNumber = ccNumber;
		}

		public String getCcName() {
			return ccName;
		}

		public void setCcName(String ccName) {
			this.ccName = ccName;
		}

		public Date getCcExpiry() {
			return ccExpiry;
		}

		public void setCcExpiry(Date ccExpiry) {
			this.ccExpiry = ccExpiry;
		}

		public String getShipping() {
			return shipping;
		}

		public void setShipping(String shipping) {
			this.shipping = shipping;
		}

		public Date getShippingDate() {
			return shippingDate;
		}

		public void setShippingDate(Date shippingDate) {
			this.shippingDate = shippingDate;
		}

		public int getAddressId() {
			return addressId;
		}

		public void setAddressId(int addressId) {
			this.addressId = addressId;
		}

	}
	
	@XmlRootElement(name = "CartTO")
	public static class CartTO {
		
	   public CartTO() {
		
	    }


		public CartTO(int id) {

		}


		private List<Integer> bIds;
		private int id;
		private List<Integer> qty;
		
		public CartTO(Cart cart) {
            this.id = cart.getId();
            this.bIds = cart.getLines().stream().map(orderLine ->
                orderLine.getBook().getId()).collect(Collectors.toList());
            this.qty = cart.getLines().stream().map(orderLine ->
                orderLine.getQty()).collect(Collectors.toList());
        }

		public List<Integer> getbIds() {
			return bIds;
		}

		public void setbIds(List<Integer> bIds) {
			this.bIds = bIds;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public List<Integer> getQty() {
			return qty;
		}

		public void setQty(List<Integer> qty) {
			this.qty = qty;
		}

	}



	public static class OrderTO
	{

		

		private int id;
        private int customerId;
        private Date shipDate;
        private String status;
        private List<Integer> bIds;
        private List<Integer> qty;

        public OrderTO(Order order) {
            this.id = order.getId();
            this.customerId = order.getCustomer().getId();
            this.shipDate = (Date) order.getDate();
            this.status = order.getStatus();
            this.bIds = order.getLines().stream().map(orderLine ->
                orderLine.getBook().getId()).collect(Collectors.toList());
            this.qty = order.getLines().stream().map(orderLine ->
                orderLine.getQty()).collect(Collectors.toList());
        }
        public OrderTO() {
        	
        }

		public List<Integer> getbIds() {
			return bIds;
		}

		public void setbIds(List<Integer> bIds) {
			this.bIds = bIds;
		}

		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public List<Integer> getQty() {
			return qty;
		}

		public void setQty(List<Integer> qty) {
			this.qty = qty;
		}

		public Date getShipDate() {
			return shipDate;
		}

		public void setShipDate(Date shipDate) {
			this.shipDate = shipDate;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}

}

