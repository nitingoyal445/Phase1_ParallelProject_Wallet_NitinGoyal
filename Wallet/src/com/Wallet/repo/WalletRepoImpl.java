package com.Wallet.repo;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.Wallet.bean.Customer;
import com.Wallet.bean.Transaction;
import com.Wallet.exception.MobileNoDoesNotExistException;

public class WalletRepoImpl implements WalletRepo{

	Customer customer;
	HashMap<String,Customer> map=new HashMap<>();
	@Override
	public boolean save(Customer customer) {
		if(map.put(customer.getMobileNo(),customer) != null)
			return true;
		return false;
	}
	int flag=0;
	@Override
	public Customer findOne(String mobileNo) {
		if(map.containsKey(mobileNo)){
			return map.get(mobileNo);
		}
		return null;
	}
	@Override
	public boolean save(Transaction transaction,Customer customer) {
		LinkedList<Transaction> list=customer.getTransaction();
		list.add(transaction);
		customer.setTransaction(list);
		return true;
	}
	@Override
	public LinkedList<Transaction> searchTransaction(String mobileNo) {
		customer=new Customer();
		customer=map.get(mobileNo);
		LinkedList<Transaction> list1=customer.getTransaction();
		Collections.reverse(list1);
		if(list1.size()<10) {
			return list1;
		}
		else
		{
		LinkedList<Transaction> list2=new LinkedList<>();
		for(int i=0;i<10;i++) {
			list2.add(customer.getTransaction().get(i));
			}
		return list2;
		}
	}
}
			

