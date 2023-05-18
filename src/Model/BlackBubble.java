package Model;

public class BlackBubble extends DrinkDecorator {

	public BlackBubble(Drink drink) {
		super(drink);
		// TODO Auto-generated constructor stub
		this.name = drink.name;
		this.price = 0.1 + this.drink.getPrice();
		this.description = this.drink.getDescription() + ", " + "Black Bubble";
	}

	@Override
	public double getPrice() {
		return this.price;
	}

}
