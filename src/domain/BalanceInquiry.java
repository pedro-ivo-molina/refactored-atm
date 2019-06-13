package domain;

import service.BankDatabase;
import ui.ScreenService;

public class BalanceInquiry extends Transaction
{
   // BalanceInquiry constructor
   public BalanceInquiry(int userAccountNumber, ScreenService screen, 
      BankDatabase atmBankDatabase)
   {
       super(userAccountNumber, screen, atmBankDatabase);
   } 

   // performs the transaction
   public void execute()
   {
      // get references to bank database and screen
      BankDatabase bankDatabase = getBankDatabase();
      ScreenService screen = getScreen();

      // get the available balance for the account involved
      double availableBalance = 
         bankDatabase.getAvailableBalance(getAccountNumber());

      // get the total balance for the account involved
      double totalBalance = 
         bankDatabase.getTotalBalance(getAccountNumber());
      
      // display the balance information on the screen
      screen.displayMessageLine("\nBalance Information:");
      screen.displayMessage(" - Available balance: "); 
      screen.displayDollarAmount(availableBalance);
      screen.displayMessage("\n - Total balance:     ");
      screen.displayDollarAmount(totalBalance);
      screen.displayMessageLine("");
   } // end method execute
}