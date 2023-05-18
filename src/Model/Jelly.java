package Model;

public class Jelly extends DrinkDecorator {

	public Jelly(Drink drink) {
		super(drink);
		// TODO Auto-generated constructor stub
		this.name = drink.name;
		this.price = 0.19 + this.drink.getPrice();
		this.description = this.drink.getDescription() + ", " + "Jelly";
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

}
