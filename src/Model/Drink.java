package Model;

public abstract class Drink extends AItem  {

	private String srcImage;
	protected Size size;
	protected String description;
	

	public Drink(String name, double price, String srcImage, Size size) {
		super(name, price);
		this.srcImage = srcImage;
		this.size = size;
		this.description = "UnKnow Drink";
	}
	
	@Override
	public String getName() {
		return super.getName();
	}

	public Size getSize() {
		return size;
	}


	public void setSize(Size size) {
		this.size = size;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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
