package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.service.ContractService;
import model.service.PaypalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		//Contrato
		System.out.println("Entre com os dados do contrato:");
		System.out.print("Número: ");
		int number = sc.nextInt();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), dtf);
		System.out.print("Valor do contrato: ");
		double value = sc.nextDouble();
		
		Contract contract = new Contract(number, date, value);
		
		//Quantidade de parcelas e o processamento delas
		System.out.print("Entre com o número de parcelas: ");
		int installment = sc.nextInt();
		
		ContractService process = new ContractService(new PaypalService());
		
		process.processContract(contract, installment);
		
		System.out.println("Parcelas: ");
		
		for (Installment installments : contract.getInstallment()) {
			System.out.println(installments);
		
		}
		
		sc.close();
	}

}
