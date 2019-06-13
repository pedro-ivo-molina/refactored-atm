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

   public double getTotalBalance(int accountNumber){
		return bankDatabase.getTotalBalance(accountNumber);
   }

   public double getAvailableBalance(int accountNumber){
		return bankDatabase.getAvailableBalance(accountNumber);
   }
}
