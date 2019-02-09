package com.Wallet.bean;

import java.util.LinkedList;

public class Customer {
	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobileNo=" + mobileNo + ", wallet=" + wallet + "]";
	}
	private String name;
	private String mobileNo;
	private Wallet wallet;
	LinkedList<Transaction> transaction=new LinkedList<>();
	
	public LinkedList<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(LinkedList<Transaction> transaction) {
		this.transaction = transaction;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
}
