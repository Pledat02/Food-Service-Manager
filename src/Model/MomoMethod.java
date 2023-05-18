package Model;

public class MomoMethod implements PaymentMethod  {

	@Override
	public double payment(double amount) {
		// TODO Auto-generated method stub
		return (double)Math.round(amount*(1-0.04) * 1000) / 1000;
		
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "(Discount 4%)";
	}
	
}
