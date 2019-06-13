package domain;

import constants.Messages;
import service.BankDatabase;
import ui.WithdrawalService;
import ui.ScreenService;
import factory.ServiceFactory;

public class Withdrawal extends Transaction {
   private WithdrawalService withdrawalService;
   private CashDispenser cashDispenser; 
   private BankDatabase bankDatabase;
   private final static int CANCELED = 6;

   public Withdrawal(int userAccountNumber, BankDatabase atmBankDatabase, CashDispenser atmCashDispenser) {
      super(userAccountNumber, atmBankDatabase);
      
      bankDatabase = getBankDatabase();
      cashDispenser = atmCashDispenser;
      withdrawalService = ServiceFactory.getWithdrawalService();
   } 
   
   public int execute() {
      double availableBalance;
      int amount = withdrawalService.displayMenuOfAmounts();

      if (amount != CANCELED) {
         availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());

         if (amount <= availableBalance) {
            if (cashDispenser.isSufficientCashAvailable(amount)) {
               bankDatabase.debit(getAccountNumber(), amount);

               cashDispenser.dispenseCash(amount);

               return Messages.CASH_DISPENSED.ordinal();
            }
            return Messages.INSUFFICIENT_CASH_ATM.ordinal();
         }
         return Messages.INSUFFICIENT_FUNDS.ordinal();
      }
      return Messages.TRANSACTION_CANCELED.ordinal();
   }
}