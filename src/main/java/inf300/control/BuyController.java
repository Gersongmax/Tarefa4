package inf300.control;

import java.sql.Date;
import java.util.List;

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

@Path("buy")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class BuyController {
	// getCart
	@GET
	@Path("/cart/{id}")
	public BuyController.CartTO getCart(@PathParam(value = "id") int idCard) {
		return null;
	}

	// getOrder
	@GET
	@Path("/order/{id}")
	public BuyController.OrderTO getOrder(@PathParam(value = "id") int id){
		return null;
		
	}

	// createCart
	@POST
	@Path("cart/create")
	public BuyController.CartTO createCart(){
		return null;
		
		
	}

	// updateCart
	@PUT
	@Path("/cart/update")
	public BuyController.CartTO updateCart(BuyController.CartTO cart){
		return null;
		
		
	}

	// confirmBuy
	@POST
	@Path("/create/")
	public BuyController.OrderTO confirmBuy(BuyController.BuyTO buy){
		return null;
		
	}
	

	// updateOrder
	@PUT
	@Path(value = "/order/{id:[0-9]{1,9}}/status/{status: (PROCESSING|SHIPPED|PENDING|DENIED|CANCELED)}")
	public BuyController.OrderTO updateOrder(@PathParam(value = "id") int idOrder,
			@PathParam(value = "status") String newStatus){
				return null;
		
		
	}

	// cancelOrder
	@PUT
	@Path(value = "/order/cancel")
	public BuyController.OrderTO cancelOrder(@DefaultValue(value = "1") @QueryParam(value = "id") int id1){
		return null;
		
		
	}
	
	

	@XmlRootElement(name = "BuyTO")
	public static class BuyTO 	{

		public BuyTO() {
		}

		int cartId;
		String comment;
		String ccType;
		long ccNumber;
		String ccName;
		Date ccExpiry;
		String shipping;
		Date shippingDate;
		int addressId;

		int customerId;

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

		public CartTO(Cart cart) {

		}

		List<Integer> bIds;
		int Id;
		List<Integer> Qty;

		public List<Integer> getbIds() {
			return bIds;
		}

		public void setbIds(List<Integer> bIds) {
			this.bIds = bIds;
		}

		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public List<Integer> getQty() {
			return Qty;
		}

		public void setQty(List<Integer> qty) {
			Qty = qty;
		}

	}



	public static class OrderTO
	{

		public  OrderTO(Order order) {
	      }

		List<Integer> bIds;
		int CustomerId;
		int Id;
		List<Integer> Qty;
		Date ShipDate;
		String Status;

		public List<Integer> getbIds() {
			return bIds;
		}

		public void setbIds(List<Integer> bIds) {
			this.bIds = bIds;
		}

		public int getCustomerId() {
			return CustomerId;
		}

		public void setCustomerId(int customerId) {
			CustomerId = customerId;
		}

		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public List<Integer> getQty() {
			return Qty;
		}

		public void setQty(List<Integer> qty) {
			Qty = qty;
		}

		public Date getShipDate() {
			return ShipDate;
		}

		public void setShipDate(Date shipDate) {
			ShipDate = shipDate;
		}

		public String getStatus() {
			return Status;
		}

		public void setStatus(String status) {
			Status = status;
		}

	}

}

