package domain;

import service.BankDatabase;
import ui.ScreenService;

public class BalanceInquiry extends Transaction {
   private ScreenService screenService;
   
   public BalanceInquiry(int userAccountNumber, ScreenService screen, 
      BankDatabase atmBankDatabase) {
       super(userAccountNumber, atmBankDatabase);
       
       screenService = screen;
   } 

   public void execute() {
      BankDatabase bankDatabase = getBankDatabase();

      double availableBalance = 
         bankDatabase.getAvailableBalance(getAccountNumber());

      double totalBalance = 
         bankDatabase.getTotalBalance(getAccountNumber());
      
      screenService.displayMessageLine("\nBalance Information:");
      screenService.displayMessage(" - Available balance: "); 
      screenService.displayDollarAmount(availableBalance);
      screenService.displayMessage("\n - Total balance:     ");
      screenService.displayDollarAmount(totalBalance);
      screenService.displayMessageLine("");
   }
}