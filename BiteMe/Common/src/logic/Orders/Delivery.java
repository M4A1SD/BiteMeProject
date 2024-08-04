package logic.Orders;

import java.io.Serializable;
import java.util.ArrayList;

import EnumsAndConstants.TypeOfOrder;

/**
 *
 */
public class Delivery implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String Phone;
	private int deliveryId=(int) serialVersionUID;
	private String Address;

	private TypeOfOrder Type;
	private float DeliveryFee;
	private int orderId;

	private ArrayList<String> UsernamesOfParticipants;
	private int NumOfParticipants;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Delivery(int deliveryId, String address, TypeOfOrder type, float deliveryFee, int orderId,
			ArrayList<String> usernamesOfParticipants, int numOfParticipants) {
		super();
		this.deliveryId = deliveryId;
		Address = address;
		Type = type;
		DeliveryFee = deliveryFee;
		this.orderId = orderId;
		UsernamesOfParticipants = usernamesOfParticipants;
		NumOfParticipants = numOfParticipants;

	}

	public Delivery(int orderId2, int NumOfParticipants, TypeOfOrder type, int DeliveryFee) {
		this.orderId = orderId2;
		this.NumOfParticipants = NumOfParticipants;
		this.Type = type;
		this.DeliveryFee = DeliveryFee;
		UsernamesOfParticipants = new ArrayList<String>();

	}

	public String toString() {
		StringBuilder b = new StringBuilder("Delivery Details:\n");
		if (Address != null) {
			b.append("Address: " + Address);
			return b.toString();
		}
		return "Take Away";
	}

	public TypeOfOrder getType() {
		return Type;
	}

	public void setType(TypeOfOrder type) {
		Type = type;
	}

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

	public float getDeliveryFee() {
		return DeliveryFee;
	}

	public void setDeliveryFee(float deliveryFee) {
		DeliveryFee = deliveryFee;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getNumOfParticipants() {
		return NumOfParticipants;
	}

	public void setNumOfParticipants(int numOfParticipants) {
		NumOfParticipants = numOfParticipants;
	}

	public ArrayList<String> getUsernamesOfParticipants() {
		return UsernamesOfParticipants;
	}

	public void setUsernamesOfParticipants(ArrayList<String> usernamesOfParticipants) {
		UsernamesOfParticipants = usernamesOfParticipants;
	}

}
