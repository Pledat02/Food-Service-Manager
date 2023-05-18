package Model;

public class Flan extends DrinkDecorator {

	public Flan( Drink drink) {
		super(drink);
		// TODO Auto-generated constructor stub
		this.name = drink.name;
		this.price=0.19+ this.drink.getPrice();
		this.description = this.drink.getDescription()+", " +"Flan";
	}

	@Override
	public double getPrice() {
		return this.price ;
	}

	
}
