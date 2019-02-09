package com.Wallet.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.Wallet.bean.Customer;
import com.Wallet.bean.Transaction;
import com.Wallet.bean.Wallet;
import com.Wallet.exception.DuplicateMobileNumberException;
import com.Wallet.exception.InsufficientAmountException;
import com.Wallet.exception.MobileNoDoesNotExistException;
import com.Wallet.repo.WalletRepo;

public class WalletServiceImpl implements WalletService {
	
	WalletRepo walletRepo;
	
	public WalletServiceImpl(WalletRepo walletRepo) {
		super();
		this.walletRepo = walletRepo;
	}
	Customer customer;
	Transaction transaction;
	Wallet w;
	int counter=1;
	@Override
	public Customer createAccount(String name, String mobileNo, BigDecimal amount) throws DuplicateMobileNumberException {
		LinkedList<Transaction> list=new LinkedList<>();
		customer=new Customer();
		customer.setName(name);
		customer.setMobileNo(mobileNo);
		if(walletRepo.findOne(mobileNo)!=null) {
			throw new DuplicateMobileNumberException();
		}
		w=new Wallet();
		w.setBalance(amount);
		customer.setWallet(w);
		customer.setTransaction(list);
		walletRepo.save(customer);
		return customer;
	}

	@Override
	public Customer showBalance(String mobileNo) throws MobileNoDoesNotExistException {
		if(walletRepo.findOne(mobileNo)==null)
			throw new MobileNoDoesNotExistException();
		return walletRepo.findOne(mobileNo);
	}

	@Override
	public List<Customer> fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) throws MobileNoDoesNotExistException, InsufficientAmountException {
		Transaction transaction=new Transaction();
		Customer customer1=new Customer();
		List<Customer> list=new LinkedList<>();
		customer1=walletRepo.findOne(sourceMobileNo);
		if(customer1!=null) {
			Customer customer2=new Customer();
			customer2=walletRepo.findOne(targetMobileNo);
			if(customer2!=null) {
				list.add(withdrawAmount(sourceMobileNo, amount));
				list.add(depositAmount(targetMobileNo, amount));
			}
			
		}
		
		return list;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws MobileNoDoesNotExistException {
		customer=showBalance(mobileNo);
		w=customer.getWallet();
		w.setBalance(w.getBalance().add(amount));
		customer.setWallet(w);
		transaction=createTransaction("deposit",amount,mobileNo, customer);
		walletRepo.save(transaction, customer);
		return customer;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws MobileNoDoesNotExistException, InsufficientAmountException {
		
		customer=showBalance(mobileNo);
		w=customer.getWallet();
		if(w.getBalance().compareTo(amount)==-1)
			throw new InsufficientAmountException();
		w.setBalance(w.getBalance().subtract(amount));
		customer.setWallet(w);
		transaction=createTransaction("withdraw",amount,mobileNo, customer);
		walletRepo.save(transaction, customer);
		return customer;
	}

	@Override
	public LinkedList<Transaction> findTransaction(String mobileNo) {
		LinkedList<Transaction> list=new LinkedList<>();
		list=walletRepo.searchTransaction(mobileNo);
		if(list.size()!=0) {
			return list;
		}
		else
		return null;
	}
	@Override
	public Transaction createTransaction(String string, BigDecimal amount, String mobileNo, Customer customer) {
		String id1="";
		String id="KJNH";
		Transaction transaction =new Transaction();
		switch(string)
		{
			case "deposit":id1=id+Integer.toString(counter);
							transaction.setId(id1);
							transaction.setMobileNo(mobileNo);
							transaction.setDeposit(amount);
							transaction.setTotal(customer.getWallet().getBalance());
							counter++;
							break;
			case "withdraw":id1=id+Integer.toString(counter);
							transaction.setId(id1);
							transaction.setMobileNo(mobileNo);
							transaction.setWithdraw(amount);
							transaction.setTotal(customer.getWallet().getBalance());
							counter++;
							break;
		}
		return transaction;
	}

}
