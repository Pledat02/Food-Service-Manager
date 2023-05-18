package Model;

public class CashMethod implements PaymentMethod {

	@Override
	public double payment(double amount) {
		return (double) Math.round(amount * 100) / 100;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return "(Discount 0%)";
	}

}
