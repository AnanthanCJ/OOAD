package com.ilp.services;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class BankService {

	public static Service addService() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("1.Cash Deposit  2. ATM withdrawal  3.Online banking  4.Mobile banking 5.Cheque Deposit ");

		System.out.println("Enter the service code");
		String serviceCode = scanner.nextLine();
		System.out.println("Enter the service name");
		String serviceName = scanner.nextLine();
		System.out.println("Enter the service rate");
		double rate = scanner.nextDouble();
		scanner.nextLine();
		Service service = new Service(serviceCode, serviceName, rate);

		return service;
	}

	public static Product createProduct(ArrayList<Service> serviceList) {
		ArrayList<Service> newServiceList = new ArrayList<Service>();
		String repeatChoice;
		Product product = null;
		
		if (serviceList == null || serviceList.isEmpty()) {
			System.out.println("No services in the list");
			
		} else {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1. SavingsMax account  2. Current Account 3. Loan account");
		int choice = scanner.nextInt();

		System.out.println("Enter the product code");
		String productCode = scanner.next();

		System.out.println("Enter the product name");
		String productName = scanner.next();

		

		
			System.out.println("Available services are:");
			displayService(serviceList);

			
				System.out.println("Do you want to add any services? (y/n)");
				scanner.nextLine(); // Consume newline character
				String serviceChoice = scanner.nextLine();

				if (serviceChoice.equals("y")) {
					do {
						System.out.println("Enter the service code you want to add");
						String selectedServiceCode = scanner.nextLine();
						int flag=0;
						for (Service service : serviceList) {
							if(newServiceList.contains(service)) {
								System.out.println(" sorry ,you can't add the same service twice");
								flag=1;
							}
							else {
								if (selectedServiceCode.equalsIgnoreCase(service.getServiceCode())) 
								{
									flag=1;
									newServiceList.add(service);
									System.out.println("Service added");
								
								}
								
							}
						}
						if(flag==0) {
							System.out.println("No Such Service");
						}
						System.out.println("Do you want to add more services? (y/n)");
						repeatChoice = scanner.nextLine();
				}while ("y".equals(repeatChoice));
				
			} 

			switch (choice) {
			case 1:
				SavingsMaxAccount savingsMaxAccount = new SavingsMaxAccount(productCode, productName, newServiceList,1000);
				product = savingsMaxAccount;
				break;
			case 2:
				// CurrentAccount 
				CurrentAccount currentAccount = new CurrentAccount(productCode, productName, newServiceList);
				product = currentAccount;
				break;
			case 3:
				// LoanAccount 
				LoanAccount loanAccount = new LoanAccount(productCode, productName, newServiceList, 0.3);
				product = loanAccount;
				break;
			}
		}

		return product;
	}

	
	
	public static void displayService(ArrayList<Service> serviceList) {
		if (serviceList == null || serviceList.isEmpty()) {
			System.out.println("no services in the list");
		} else {
			for (Service service : serviceList) {
				System.out.println(
						service.getServiceCode() + "\t" + service.getServiceName() + "\t" + service.getRate() + "\n");
			}
		}

	}

	public static Customer createCustomer(ArrayList<Product> productList) {
		
		if (productList.size()<=0) {
			System.out.println("No products in the list");
			return null;
		}
		else { 
		ArrayList<Account> accountList = new ArrayList<Account>();
		String accountChoice ;
		Product customerProduct = null;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter customer code");
		String customerCode = scanner.nextLine();
		System.out.println("Enter customer name");
		String customerName = scanner.nextLine();

	do {
		System.out.println("Enter account number");
		String accountNo = scanner.nextLine();
		System.out.println("Enter account Type");
		String accountType = scanner.nextLine();
		
		
			displayProduct(productList);
		System.out.println("Enter product code you want");
		
		String wantProductCode = scanner.nextLine();
		 
			for (Product product : productList) {

				if (product.getProductCode().equalsIgnoreCase(wantProductCode)) {

					customerProduct = product;
					if(product instanceof SavingsMaxAccount) {
						customerProduct = (SavingsMaxAccount) customerProduct;
					}
				}
				
			}
			
			System.out.println("Enter account balance");
			double accountBalance = scanner.nextDouble();
			if(customerProduct instanceof SavingsMaxAccount && accountBalance<1000) {
				System.out.println("minimum balance of 1000 required , try again later");
				return null;
			}
			
			Account account = new Account(accountNo, accountType, accountBalance, customerProduct);
			accountList.add(account);
		   System.out.println("product added");
	
		
		System.out.println("Do you want to add more accounts y/n");
		accountChoice = scanner.nextLine();
	}while(accountChoice.equalsIgnoreCase("y"));
		
		Customer customer = new Customer(customerCode, customerName, accountList);
		return customer;
		}
	}

	
	public static void displayProduct(ArrayList<Product> productList) {
		if (productList == null || productList.isEmpty()) {
			System.out.println("No products in the list");
			
		} else {
			for (Product product : productList) {
				System.out.println("Product Code: " + product.getProductCode());
				System.out.println("Product Name: " + product.getProductName());
				System.out.println("Services:");
				displayService(product.getServiceList());
				System.out.println(); // Add a line break between products
			}
		}
	}
	
	

	public static void displayCustomer(Customer customer) {
		if(customer != null) {
		System.out.println("Customer code \t" + "customer name \t"  );
		System.out.println(customer.getCustomerCode() + "\t\t" + customer.getCustomerName());
		
		
		for (Account account : customer.getAccountList()) {
			System.out.println("account number \t"+ "account type\t"+ "account balance" );
			System.out.println(account.getAccountNo() + "\t\t" + account.getAccountType() + "\t\t" + account.getBalance());
			
			System.out.println("product code\t" + "product name");
			Product p = account.getProduct();
			System.out.println(p.getProductCode() + "\t\t" + p.getProductName());
			
			System.out.println("service code \t" + "service name" + "\t service rate");
			for (Service service : p.getServiceList()) {
				System.out.println(service.getServiceCode() + "\t\t" + service.getServiceName() + "\t\t" + service.getRate());

			}
			
				System.out.println();
		}
	}
	
	else 
	{
		System.out.println("no customer data exists, come back later");
	}
	}

	
	
	public static Customer manageAccount(Customer customer) {
		String mainChoice = null;
		int switchChoice;
		
		
		if(customer == null)
		{
			System.out.println("No customer data exists , please create an account ");
		}
		else
		{ 
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("\t\tWelcome to Accounts,please provide you customer id");
			String customerCode = scanner.nextLine();
			
				if(customerCode.equalsIgnoreCase(customer.getCustomerCode()))
				{ 
			do 
			{
				
								

				System.out.println();
				System.out.println("Enter you choice");

				System.out.println("1.Deposit");
				System.out.println("2.Withdraw");
				System.out.println("3.Check Balance");
				System.out.println("4.Exit");
				System.out.println();

				
				
				switchChoice = scanner.nextInt();
				switch(switchChoice)
				{
				case 1 : customer = depositMoney(customer);
						 break;
						
				case 2 : customer = withdrawMoney(customer);
						 break;
				case 3 : customer =checkBalance(customer);
						 break;
				
				default: System.out.println("bye bye");
						 
				}
				System.out.println("Do you want to go back to Account  menu (y/n)");
				scanner.nextLine();
				mainChoice = scanner.nextLine();
					}while(mainChoice.equals("y"));

					} 
		
		}
		return customer;
	}
	
	

	private static Customer checkBalance(Customer customer) {
		ArrayList<Account> accountList = customer.getAccountList();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the account Number");
		String accountNo = scanner.nextLine();
		for(Account account:accountList) {
			if(account.getAccountNo().equalsIgnoreCase(accountNo)) {
				System.out.println("Your balance amount is " + account.getBalance());
				
			}
			
		}
		return customer;
	}

	
	private static Customer withdrawMoney(Customer customer) {
		ArrayList<Account> accountList = customer.getAccountList();

		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the account Number");
		String accountNo = scanner.nextLine();
		System.out.println("Enter the amount you want to withdraw");
		double amount = scanner.nextDouble();
		if(amount <= 0) {
			System.out.println("nice try ,try again");
		}
		for(Account account:accountList) {
			if(account.getAccountNo().equalsIgnoreCase(accountNo))
			{
				if(account.getProduct() instanceof SavingsMaxAccount){
					SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount) account.getProduct();
					if(account.getBalance()-amount<savingsMaxAccount.getMinimumBalance()) {
						System.out.println("insufficient balance , minimum balance has to maintained");
						return customer;
					}else {
						account.setBalance(account.getBalance()-amount);
					}
				}else {
					double balance = account.getBalance();
					if(amount>balance)
					{
						System.out.println("insufficient balance ");
						return customer;
					}
					else {
						account.setBalance(balance - amount);
					}
				}
				
				
			}
		
		
	}
		System.out.println("Successfully money withdrawn");
		return customer;
	}

	
	
	private static Customer depositMoney(Customer customer) {
		ArrayList<Account> accountList = customer.getAccountList();
		Account customerAccount = null;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the account Number");
		String accountNo = scanner.nextLine();
		System.out.println("Enter the amount you want to deposit");
		
		double amount = scanner.nextDouble();
		
		
		
		for(Account account:accountList) {
			if(account.getAccountNo().equalsIgnoreCase(accountNo))
			{
				
					customerAccount = account;
			}
		}
			if(customerAccount != null) 
			{
			double balance = customerAccount.getBalance();
			if(customerAccount.getProduct() instanceof LoanAccount)
			{
				System.out.println("Are you using ChequeDeposit true/false");
				boolean isCheque = scanner.nextBoolean();
				if(isCheque) {
		  	  LoanAccount loanAccount = (LoanAccount) customerAccount.getProduct();
		  	  Double chequeDeposit = loanAccount.getChequeDeposit();
		  	  customerAccount.setBalance((balance + amount)*chequeDeposit);
				}
				else
				{
					customerAccount.setBalance(balance+amount);
				}
				
			}

			System.out.println("Successfully money deposited");
			return customer;
			}
			else {
				System.out.println("no such account exists, please try again later");
				return customer;
			}
		
		
	
	}

}
