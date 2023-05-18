package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class NetManagement {
	private List<Food> foodList;
	private List<Drink> drinkList;
	private List<Combo> comboList;
	private List<AItem> itemlist = new ArrayList<AItem>();
	private LinkedHashMap<String, Double> cartItems;
	private LinkedHashMap<String, Double> priceHashMap;
	private volatile static NetManagement uniqueInstance;
	private HashMap<String, String> accounts;
	private PaymentMethod computingAmountBehavior ;

	public NetManagement() {
		comboList = new ArrayList<>();
		foodList = new ArrayList<>();
		drinkList = new ArrayList<>();
		cartItems = new LinkedHashMap<>();
		accounts = new HashMap<>();
		Data();
		itemlist.addAll(comboList);
		itemlist.addAll(foodList);
		itemlist.addAll(drinkList);

		priceHashMap = new LinkedHashMap<>();

	}

	public static NetManagement getInstance() {
		if (uniqueInstance == null) {
			synchronized (NetManagement.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new NetManagement();
				}
			}
		}
		return uniqueInstance;
	}

	public Drink searchDrink(String nameDrink) {
		Drink result = null;
		for (Drink drink : drinkList) {
			if (drink.name.equals(nameDrink)) {
				result = drink;
			}
		}
		return result;
	}

	public String[] getListNameItem() {
		int size = 0;
		int countCombo = 0;
		for (int i = 0; i < itemlist.size(); i++) {
			if (!(itemlist.get(i) instanceof Combo)) {
				size++;
			} else {
				countCombo++;
			}
		}
		String[] result = new String[size];
		for (int i = 0, j = countCombo; i < size && j < size + countCombo; i++, j++) {
			String[] strs = new String[size];
			if (!(itemlist.get(j) instanceof Combo)) {
				strs = itemlist.get(j).toString().split("/");
			}
			result[i] = strs[0];

		}
		return result;

	}
	public double getDiscountMomo(double amount ) {
		computingAmountBehavior = new MomoMethod();
		return computingAmountBehavior.payment(amount);
	}
	public double getDiscountCash(double amount ) {
		computingAmountBehavior = new CashMethod();
		return computingAmountBehavior.payment(amount);
	}
	public double getDiscountBA(double amount ) {
		computingAmountBehavior = new BankAccountMethod();
		return computingAmountBehavior.payment(amount);
	}
	public String[] getListPriceItem() {
		int size = 0;
		int countCombo = 0;
		for (int i = 0; i < itemlist.size(); i++) {
			if (!(itemlist.get(i) instanceof Combo)) {
				size++;
			} else {
				countCombo++;
			}
		}
		String[] result = new String[size];
		for (int i = 0, j = countCombo; i < size && j < size + countCombo; i++, j++) {
			String[] strs = new String[size];
			if (!(itemlist.get(j) instanceof Combo)) {
				strs = itemlist.get(j).toString().split("/");
			}
			result[i] = strs[1];

		}
		return result;

	}

	public AItem searchItem(String nameItem) {
		AItem result = null;
		for (AItem item : itemlist) {
			if (nameItem.equals(item.getName() + "  " + item.getPrice())) {
				result = item;
			}
		}
		return result;
	}

	public void getAmounttoPriceHashMap() {

		for (AItem item : itemlist) {

			if (priceHashMap.containsKey(item.getName() + "  " + item.getPrice())) {
				double quantity = priceHashMap.get(item.getName() + "  " + item.getPrice());
				item.setQuantity((int) quantity);
				priceHashMap.put(item.getName() + "  " + item.getPrice(), item.computeAmount());

			}
		}
	}

	public List<AItem> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<AItem> itemlist) {
		this.itemlist = itemlist;
	}

	public LinkedHashMap<String, Double> getPriceHashMap() {
		return priceHashMap;
	}

	public void setPriceHashMap(LinkedHashMap<String, Double> priceHashMap) {
		this.priceHashMap = priceHashMap;
	}

	public Double getAmount() {
		this.getAmounttoPriceHashMap();
		Double result = 0.0;
		for (java.util.Map.Entry<String, Double> entry : priceHashMap.entrySet()) {
			result += entry.getValue();
		}
		return result;
	}

	public void addToCart(AItem item) {
		if (!cartItems.containsKey(item.getName() + "  " + item.getPrice())) {
			cartItems.put(item.getName() + "  " + item.getPrice(), 1.0);
		} else {
			cartItems.put(item.getName() + "  " + item.getPrice(),
					cartItems.get(item.getName() + "  " + item.getPrice()) + 1.0);
		}
		double quantity = cartItems.get(item.getName() + "  " + item.getPrice());
		item.setQuantity((int) quantity);
		priceHashMap.putAll(cartItems);
	}

	public void removeFromCart(AItem item) {

		cartItems.put(item.getName() + "  " + item.getPrice(),
				cartItems.get(item.getName() + "  " + item.getPrice()) - 1);
		double quantity = cartItems.get(item.getName() + "  " + item.getPrice());
		item.setQuantity((int) quantity);
		priceHashMap.putAll(cartItems);
	}

// CountPanel 
	public int countPanel(List<?> list, int itemNum) {
		int size = list.size();
		int count = 0;
		while (size > itemNum) {
			count++;
			size -= itemNum;
		}
		count++;
		return count;
	}

	private void Data() {
		// TODO Auto-generated method stub
		// food
		Food SoupCake = new Food("Soup Cake", 2.99, "..\\Image\\SoupCake.png");
		Food FriedRice = new Food("Fried Rice", 2.99, "..\\Image\\FriedRice.png");
		Food FriedNoodles = new Food("Fried Noodles", 2.29, "..\\Image\\FriedNoodles.png");
		Food NoodleSoup = new Food("Noodle Soup", 2.19, "..\\Image\\NoodleSoup.png");
		Food Spaghetti = new Food("Spaghetti", 2.99, "..\\Image\\Spaghetti.png");
		Food BeefPoached = new Food("Beef Poached", 2.39, "..\\Image\\BeefPoached.png");
		Food SpicyNoodles = new Food("Spicy Noodles", 2.29, "..\\Image\\SpicyNoodles.png");
		Food BrokenRice = new Food("Broken Rice", 2.29, "..\\Image\\BrokenRice.png");
		foodList.add(SoupCake);
		foodList.add(FriedRice);
		foodList.add(FriedNoodles);
		foodList.add(Spaghetti);
		foodList.add(BeefPoached);
		foodList.add(SpicyNoodles);
		foodList.add(BrokenRice);
		foodList.add(NoodleSoup);
//		-------------------------------------------------------------------------------------	
		// Drink
		Drink CocaCola = new BottledDrink("CocaCola", 1.99, "..\\Image\\CocaCola.png");
		Drink Pepsi = new BottledDrink("Pepsi", 1.99, "..\\Image\\Pepsi.png");
		Drink Sprite = new BottledDrink("Sprite", 1.99, "..\\Image\\Sprite.png");
		Drink Capuchino = new PreparedDrink("Capuchino", 2.29, "..\\Image\\Capuchino.png", Size.SMALL);
		Drink PeachTea = new PreparedDrink("PeachTea", 1.99, "..\\Image\\PeachTea.png", Size.SMALL);
		Drink MilkCoffee = new PreparedDrink("Milk Coffee", 2.29, "..\\Image\\MilkCoffee.png", Size.SMALL);
		Drink MatchaLatte = new PreparedDrink("Matcha Latte", 2.29, "..\\Image\\MatchaLatte.png", Size.SMALL);
		Drink SevenUp = new BottledDrink("7 Up", 1.99, "..\\Image\\SevenUp.png");
		Drink WakeUp247 = new BottledDrink("WakeUp247", 1.99, "..\\Image\\WakeUp247.png");
		Drink KhongDo = new BottledDrink("KhongDo", 1.99, "..\\Image\\KhongDo.png");
		Drink TeaPlus = new BottledDrink("TeaPlus", 1.99, "..\\Image\\TeaPlus.png");
		Drink NumberOne = new BottledDrink("NumberOne", 1.99, "..\\Image\\NumberOne.png");
		Drink Sting = new BottledDrink("Sting", 1.99, "..\\Image\\Sting.png");

		drinkList.add(Sting);
		drinkList.add(CocaCola);
		drinkList.add(Pepsi);
		drinkList.add(Sprite);
		drinkList.add(Capuchino);
		drinkList.add(PeachTea);
		drinkList.add(MilkCoffee);
		drinkList.add(MatchaLatte);
		drinkList.add(SevenUp);
		drinkList.add(WakeUp247);
		drinkList.add(KhongDo);
		drinkList.add(TeaPlus);
		drinkList.add(NumberOne);

//		-------------------------------------------------------------------------------------

		// combo
		Drink ChooseDrink = new BottledDrink("Choose Drink", 0, "..\\Image\\ChooseDrink.png");

		Combo combo1 = new Combo("combo1", SoupCake, ChooseDrink);
		Combo combo2 = new Combo("combo2", FriedRice, ChooseDrink);
		Combo combo3 = new Combo("combo3", BeefPoached, ChooseDrink);
		Combo combo4 = new Combo("combo4", Spaghetti, ChooseDrink);
		Combo combo5 = new Combo("combo5", FriedNoodles, ChooseDrink);
		Combo combo6 = new Combo("combo6", NoodleSoup, ChooseDrink);
		comboList.add(combo1);
		comboList.add(combo2);
		comboList.add(combo3);
		comboList.add(combo4);
		comboList.add(combo5);
		comboList.add(combo6);
		// user
		accounts.put("phamledat", "21130022");
		accounts.put("nguyentankhoa", "21130079");
		accounts.put("dinhvankhai", "21130070");
		accounts.put("doanquocdang", "21130014");
		accounts.put("doannhatlinh", "21130091");
		accounts.put("nguyenleduong", "21130030");
		accounts.put("lamtrachdong", "1");
		accounts.put("1", "1");
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}

	public List<Drink> getDrinkList() {
		return drinkList;
	}

	public void setDrinkList(List<Drink> drinkList) {
		this.drinkList = drinkList;
	}

	public List<Combo> getComboList() {
		return comboList;
	}

	public void setComboList(List<Combo> comboList) {
		this.comboList = comboList;
	}

	public LinkedHashMap<String, Double> getCartItems() {
		return cartItems;
	}

	public void setCartItems(LinkedHashMap<String, Double> cartItems) {
		this.cartItems = cartItems;
	}

	public HashMap<String, String> getAccounts() {
		return accounts;
	}
	public String getMomodescription(){
		computingAmountBehavior = new MomoMethod();
		return computingAmountBehavior.description();
	}
	public String getBAdescription(){
		computingAmountBehavior = new BankAccountMethod();
		return computingAmountBehavior.description();
	}
	public String getCashdescription(){
		computingAmountBehavior = new CashMethod();
		return computingAmountBehavior.description();
	}
	
public static void main(String[] args) {
	
}
	
}