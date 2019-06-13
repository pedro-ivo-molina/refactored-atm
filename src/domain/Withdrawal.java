package domain;

import service.BankDatabase;
import ui.WithdrawalService;
import ui.ScreenService;
import factory.ServiceFactory;

public class Withdrawal extends Transaction {
   private int amount;
   private WithdrawalService withdrawalService;
   private CashDispenser cashDispenser; 
   private BankDatabase bankDatabase;
   private ScreenService screenService;
   private final static int CANCELED = 6;

   public Withdrawal(int userAccountNumber, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser) {
      super(userAccountNumber, atmBankDatabase);
      
      bankDatabase = getBankDatabase();
      cashDispenser = atmCashDispenser;
      screenService = ServiceFactory.getScreenService();
      withdrawalService = ServiceFactory.getWithdrawalService();
   } 
   
   public void execute() {
      boolean cashDispensed = false;
      double availableBalance; 
      
      

      do {
         amount = withdrawalService.displayMenuOfAmounts();
         
         if (amount != CANCELED) {
            availableBalance = 
               bankDatabase.getAvailableBalance(getAccountNumber());
      
            if (amount <= availableBalance) {   
               if (cashDispenser.isSufficientCashAvailable(amount)) {
                  bankDatabase.debit(getAccountNumber(), amount);
                  
                  cashDispenser.dispenseCash(amount);
                  cashDispensed = true;

                  screenService.displayMessageLine("\nYour cash has been" +
                     " dispensed. Please take your cash now.");
               } else
                  screenService.displayMessageLine(
                     "\nInsufficient cash available in the ATM." +
                     "\n\nPlease choose a smaller amount.");
            } else {
               screenService.displayMessageLine(
                  "\nInsufficient funds in your account." +
                  "\n\nPlease choose a smaller amount."); 
            }
         } else {
            screenService.displayMessageLine("\nCanceling transaction...");
            return;
         }
      } while (!cashDispensed);

   }
}