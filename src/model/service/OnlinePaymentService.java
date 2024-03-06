package model.service;

public interface OnlinePaymentService{
	
	//Interface para possibilidade de novos serviços com taxas diferentes

	public double paymentFee(double amount);
	
	public double interest(double amount, int months);
	
}
