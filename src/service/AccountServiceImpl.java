package service;

import ui.AccountService;
import service.BankDatabase;


public class AccountServiceImpl implements AccountService{
	private BankDatabase bankDatabase;
	
	public AccountServiceImpl(){
		this.bankDatabase = new BankDatabase();
	}
	
	public boolean authenticateUser(int accountNumber, int pin) {
		return bankDatabase.authenticateUser(accountNumber, pin);
   }
}
