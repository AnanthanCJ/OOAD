package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;
import com.ilp.services.BankService;

public class BankUtility {

	public static void main(String[] args) {
		String mainChoice;
		int switchChoice;
		int leave=0;
		ArrayList<String> serviceCodes = new ArrayList<String>();
		ArrayList<String> productCodes = new ArrayList<String>();
		ArrayList<String> accountNumbers = new ArrayList<String>();
		
		Customer customer = null;
		 ArrayList<Service> serviceList = new ArrayList<Service>();
		 ArrayList<Product> productList = new ArrayList<Product>();
	        
		Scanner scanner = new Scanner(System.in);
		
		do {
			System.out.println("\t\tWelcome to bank");
			System.out.println("Please enter your choice");
			System.out.println();
			System.out.println("1.Create Service");
			System.out.println("2.Create Product");
			System.out.println("3.Create Customer");
			System.out.println("4.Manage Accounts");
			System.out.println("5.Display Customer");
			System.out.println("6.Display Services");
			System.out.println("7.Display Products");
			System.out.println("8.Exit");
			
			switchChoice = scanner.nextInt();
			switch(switchChoice)
			{
			case 1 : serviceList.add(BankService.addService(serviceCodes));
			   		 if(serviceCodes!= null)
			   		 {
			         serviceCodes.add(serviceList.get(serviceList.size()-1).getServiceCode());
			   		 }
					 break;
					
			case 2 :Product product = BankService.createProduct(serviceList,productCodes);
					if(product!=null) {
						productList.add(product);
					}
					 if(productCodes != null && productList.size()!= 0) {
			         productCodes.add(productList.get(productList.size()-1).getProductCode());
					 }
					 break;
			case 3 : if(customer == null) {
				      
					 	customer = BankService.createCustomer(productList,accountNumbers);
						}
					 else {
						 System.out.println("a customer already exists");
					 }
						if(accountNumbers.size() != 0 && customer.getAccountList().size() != 0) {
			 			accountNumbers.add(customer.getAccountList().get(customer.getAccountList().size()-1).getAccountNo());
						}
			 			break;
			case 4 : customer = BankService.manageAccount(customer);
					 break;
			case 5 : BankService.displayCustomer(customer);
					 break;
			case 6 : BankService.displayService(serviceList); 
					 break;
			case 7 : BankService.displayProduct(productList);
			   		 break;
			
			default: System.out.println("bye bye");
					 leave=1;
					 
			}
			if(leave ==1) {
				break;
			}
			System.out.println("Do you want to go back to menu (y/n)");
			mainChoice = scanner.next();
		} while(mainChoice.equals("y"));

	}

}
