package Model;

public abstract class DrinkDecorator extends Drink {
	protected Drink drink;

	public DrinkDecorator( Drink drink) {
		super("Unknow", 0, null,Size.MEDIUM);
		this.drink = drink;
		this.description=this.name;
	}
	public abstract double getPrice();
	@Override
	public double computeAmount() {
		return price;
	}


	
	
	

}
