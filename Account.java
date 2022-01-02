package com.example.banking.domain;

//objectlerde kalıtım var. yazmıyoruz ama uzun hali: public class Account extends Object{
public class Account { // kalıcı, entity class
	//iban:instance+reference variable --> null(başlangıç değeri)
	private String iban; // attribute/state/propert/data
	//balance:instance+value-typed variable --> 0.0(başlangıç değeri)
	protected double balance;
	
	private AccountStatus status;
	
	/*
	public Account(String iban, double balance) { // constructor
		this.iban = iban;
		this.balance = balance;
	}
	*/
	public Account(String iban, double balance, AccountStatus status) {
		this.iban = iban;
		this.balance = balance;
		this.status = status;
	}
	
	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public String getIban() { // getter methodları, görebilmek için
		return this.iban;
	}

	public double getBalance() { // getter methodları
		return this.balance;
	}

	public boolean deposit(double amount) {
		// validation
		if (amount <= 0)
			return false;// eksi para yatıramaz
		// business logic
		this.balance = this.balance + amount;
		return true;
	}

	public boolean withdraw(double amount) {
		System.out.println("Account::withdraw");
		// validation
		if (amount <= 0)
			return false;// eksi para yatıramaz
		// business rule
		if (amount > this.balance)
			return false; // fazla para da çekemez
		// this.balance=this.balance-amount;
		this.balance -= amount;
		return true;
	}

	@Override
	public String toString() {
		return "Account [iban=" + iban + ", balance=" + balance + ", status=" + status + "]";
	}

	public double withdrawAll() {
		var balance=this.balance;
		this.balance=0.0;
		return balance;
	}

}
