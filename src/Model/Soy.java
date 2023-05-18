package Model;

public class Soy extends DrinkDecorator {

	public Soy( Drink drink) {
		super(drink);
		this.name=drink.name;
		this.price=0.19+this.drink.getPrice();
		this.description = this.drink.getDescription()+", "+"Soy";
	}
	@Override
	public double getPrice() {
		return this.price;
	}
	

}
