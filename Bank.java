package com.example.banking.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Bank {
	private final int id;
	private String commercialName;
	// OK.TODO: Create an enum type: BankType
	// NOTE: BankType has two values: PUBLIC and PRIVATE
	
	private final BankType type;

	private Map<String, Customer> customers = new HashMap<>();

	// OK.TODO: Add Constructor(id,commercialName,type)

	// OK.TODO: Add getters for id, commercialName, type

	// OK.TODO: Add setter for commercialName


	public Bank(int id, String commercialName, BankType type) {
		this.id = id;
		this.commercialName = commercialName;
		this.type = type;
	}
	public String getCommercialName() {
		return commercialName;
	}

	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}

	public int getId() {
		return id;
	}

	public BankType getType() {
		return type;
	}

	public List<Customer> getCustomers() {
		// TODO: return all customers
		//List<Customer> getCustomers = new ArrayList<Customer>(customers.values());
		var getCustomers = new ArrayList<Customer>(customers.values());
		return Collections.unmodifiableList(getCustomers);
		//return getCustomers;
		//!!! NOTE: Make sure that you do not violate information hiding principle!!!!!
	}

	public Customer createCustomer(String identity, String fullname) {
		// TODO: Create a new customer and put the customer into the Map
		Customer customer=new Customer(identity,fullname);
		customers.put(customer.getIdentity(), customer);
		return customer;
		//return null;
	}

	public Optional<Customer> findCustomerByIdentity(String identity) {
		// TODO: find the customer by identity
		Optional<Customer> foundCustomer = Optional.ofNullable(customers.get(identity));
		return foundCustomer;
	}
	
	public double getTotalBalance(AccountStatus... status) {
		// TODO: Return total balances of all customers where accounts are in the given status
		//var totalBalanceWrtStatus =  

		// NOTE: "AccountStatus... status" means it is an array
		// You can call getTotalBalance() as in the following examples:
		// var activeTotal = garantiBbva.getTotalBalance(AccountStatus.ACTIVE);
		// var activeAndBlockedTotal = garantiBbva.getTotalBalance(AccountStatus.ACTIVE,
		// AccountStatus.BLOCKED);
		// var allTotal = garantiBbva.getTotalBalance(); // or
		// var allTotal = garantiBbva.getTotalBalance( AccountStatus.ACTIVE,
		// AccountStatus.BLOCKED,AccountStatus.CLOSED);
		return 0.0;
	}
}