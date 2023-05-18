package Model;

public class PreparedDrink extends Drink {

	public PreparedDrink(String name, double price, String srcImage, Size size) {
		super(name, price, srcImage, size);
		// TODO Auto-generated constructor stub
		this.description ="Prepared Drink" +" "+ this.getSize();
		if(this.size==Size.LARGE) {
			this.price=this.price*1.4;
		}else if(this.size==Size.MEDIUM) {
			this.price=this.price*1.2;
		}else if(this.size==Size.SMALL) {
			this.price=this.price*1;
		}
	}



	
	
}
