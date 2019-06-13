package service;

import domain.Deposit;
import domain.Transaction;
import domain.Withdrawal;
import domain.CashDispenser;
import domain.DepositSlot;
import domain.Keypad;
import ui.ScreenService;
import ui.TransactionService;

public class TransactionServiceImpl implements TransactionService {
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int EXIT = 4;
	
	private BankDatabase bankDatabase;
	private CashDispenser cashDispenser;
	private DepositSlot depositSlot;
	private ScreenService screenService;
	private Keypad keypad;
	
	public TransactionServiceImpl(){
		bankDatabase = new BankDatabase();
		cashDispenser = new CashDispenser();
		depositSlot = new DepositSlot();
		screenService = new ScreenServiceImpl();
		keypad = new Keypad();
	}
	
	public void executeTransaction(int type, int currentAccountNumber){
      Transaction transaction = null;
      
      switch (type){
         case WITHDRAWAL:
            transaction = new Withdrawal(currentAccountNumber, bankDatabase, cashDispenser);
            break; 
         case DEPOSIT:
            transaction = new Deposit(currentAccountNumber, screenService, 
               bankDatabase, keypad, depositSlot);
            break;
         case EXIT:
        	 break;
      }

      transaction.execute();
    }
}
