package Model;

public class Food extends AItem {
	private String srcImage;
	

	public Food(String name,double price, String srcImage) {
		super(name, price);
		this.srcImage = srcImage;
	}

	public String getSrcImage() {
		return srcImage;
	}

	public void setSrcImage(String srcImage) {
		this.srcImage = srcImage;
	}

	

	@Override
	public String toString() {
		return this.name+"/"+this.price;
	}

	

	

	
	

}
