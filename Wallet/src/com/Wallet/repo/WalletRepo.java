package com.Wallet.repo;
import java.util.LinkedList;

import com.Wallet.bean.Customer;
import com.Wallet.bean.Transaction;
public interface WalletRepo {
	public boolean save(Customer customer);
	public Customer findOne(String mobileNo);
	boolean save(Transaction transaction, Customer customer);
	LinkedList<Transaction> searchTransaction(String mobileNo);
}
