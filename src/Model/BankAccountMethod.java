package Model;

public class BankAccountMethod implements PaymentMethod{

	@Override
	public double payment(double amount) {
		// TODO Auto-generated method stub
		return ((double) Math.round(amount*(1-0.02) * 100) / 100);
	}

	@Override
	public String description() {
		return "(Discount 2%)";
	}

}
