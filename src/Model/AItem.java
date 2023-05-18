package Model;

public abstract class AItem  {
	protected String name;
	protected double price;
	protected int quantity;
	

	protected AItem(String name, double price) {
		this.name = name;
		this.price = price;
		this.quantity = 0;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	public double computeAmount() {
		return quantity*price;
	}


}
