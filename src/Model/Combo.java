package Model;

public class Combo extends AItem {
	private Food food;
	private Drink drink;
	private double priceCombo;

	public Combo(String name, Food food, Drink drink) {
		super(name, 0);
		this.food = food;
		this.drink = drink;
		priceCombo = this.food.getPrice() + this.drink.getPrice();
		this.price = this.getPriceCombo();
	}

	

	@Override
	public String toString() {
		return "Combo [food=" + food + ", drink=" + drink + ", priceCombo=" + priceCombo + "]";
	}

	public double getPriceCombo() {
		return priceCombo;
	}

	public void setPriceCombo(double priceCombo) {
		this.priceCombo = priceCombo;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
		priceCombo = this.food.getPrice() + drink.getPrice();
	}

}
