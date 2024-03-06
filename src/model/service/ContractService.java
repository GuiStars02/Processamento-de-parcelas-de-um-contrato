package model.service;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	//Processamento do valor das parcelas
	private OnlinePaymentService onlinePayment;
	
	public ContractService(OnlinePaymentService onlinePayment) {
		this.onlinePayment = onlinePayment;
	}

	public void processContract(Contract contract, int months) {
		double basicAliq = contract.getContractValue() / months;
		
		for (int i = 1; i<= months; i++ ) {
			
			double valueInst = basicAliq + onlinePayment.interest(basicAliq, i);
			valueInst  += onlinePayment.paymentFee(valueInst);
			LocalDate dueDate = contract.getDate().plusMonths(i);
			
			contract.getInstallment().add(new Installment(dueDate, valueInst));
		}
	}
}
