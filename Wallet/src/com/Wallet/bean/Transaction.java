package com.Wallet.bean;

import java.math.BigDecimal;

public class Transaction {
	String id;
	String mobileNo;
	BigDecimal deposit;
	BigDecimal withdraw;
	BigDecimal total;
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getId() {
		return id;
	}
	public void setId(String id1) {
		this.id = id1;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public BigDecimal getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(BigDecimal withdraw) {
		this.withdraw = withdraw;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", mobileNo=" + mobileNo + ", deposit=" + deposit + ", withdraw=" + withdraw
				+ "]";
	}
	

}
