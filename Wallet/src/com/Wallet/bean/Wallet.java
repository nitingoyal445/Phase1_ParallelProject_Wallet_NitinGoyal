package com.Wallet.bean;

import java.math.BigDecimal;

public class Wallet {
@Override
	public String toString() {
		return "Wallet [balance=" + balance + "]";
	}
private BigDecimal balance;
public BigDecimal getBalance() {
	return balance;
}
public void setBalance(BigDecimal balance) {
	this.balance=balance;
}
}
