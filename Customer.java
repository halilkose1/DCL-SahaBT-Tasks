package com.example.banking.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
//diamond operatörü --> <>
public class Customer {
	private final String identity;  //final(tc no deðiþmez.)
	private String fullName;
	
	//private List<Account> accounts= new ArrayList<>(); //List of account diye okunur. Liste her tip atýlýyordu. Þimdi sadece Account tipinde atýlýyor.
	private Map<String,Account> accounts= new HashMap<>();
	
	
	public Customer(String identity, String fullName) {
		this.identity = identity; //finaller mutlaka initialize edilmeli!!!
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIdentity() {
		return identity;
	}
	public void addAccount(Account account) {
		accounts.put(account.getIban(),account);
	}

	//for (Account account:accounts) Account --> var yeni gösterim zaten accounts'un içinde Accountdan baþka tip olmaz.
	//public Account removeAccount(String iban) {
	//Exampleda olmayan ibaný silerken nullpoiner hatasý veriyordu. Optional ekledik.
	
	// Overloading: Same class & method name, different signature
	// Overriding: Inherited Classes, same method name & signature
	
	/*
	public Optional<Account> removeAccount(int index) {
		if (index < 0 || index >= accounts.size())
			return Optional.empty();
		return Optional.of(accounts.remove(index));
	}
	*/
	
	/***************************************
	public Optional<Account> removeAccount(int index){
		//Overloading: Same class and methond name, different signature
		//Overriding: (between super and sub classses)Inherited Classes, same method name & signature
		//ayný sýnýfýn içindeki metotlara farklý deðiþkenler atýyoruz.OVERLOADÝNG!
	}
	**************************************/

	public Optional<Account> removeAccount(String iban) {
		Account foundAccount = null;

		for (var account : accounts.values()) { // for each account in accounts
				if (account.getIban().equals(iban)) {
					foundAccount = account;	
					break; 
				}
		}

		if (Objects.nonNull(foundAccount)) {
			accounts.remove(foundAccount.getIban());
		}
		return Optional.ofNullable(foundAccount);
	}
	
	public Map<String,Account> getAccounts() {
		return Collections.unmodifiableMap(accounts); //hesap bilgilerini koruyarak sadece görüntüler. 
													   //remove,add metotlarýný çaðýramayýz!!!
	}
	
	//Task #3
	public double getTotalBalance(){   
		//TODO: returns Customer's total balance in all his/her accounts.
		var totalBalance=accounts.values().stream().mapToDouble(account->account.getBalance()).sum();
		return totalBalance;
	}
	
	@Override
	public String toString() {
		return "Customer [identity=" + identity + ", fullName=" + fullName + ", accounts=" + accounts + "]";
	}
	
}
