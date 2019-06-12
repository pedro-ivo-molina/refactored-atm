package controller;

public class BalanceInquiry extends Transaction{
   public BalanceInquiry(int userAccountNumber, BankDatabase atmBankDatabase){
      super(userAccountNumber, atmBankDatabase);
   }

   @Override
   public void execute(){

      BankDatabase bankDatabase = getBankDatabase();

      double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());

      double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
      
      //mandar essa porra pro menu
      screen.displayMessageLine("\nBalance Information:");
      screen.displayMessage(" - Available balance: "); 
      screen.displayDollarAmount(availableBalance);
      screen.displayMessage("\n - Total balance:     ");
      screen.displayDollarAmount(totalBalance);
      screen.displayMessageLine("");
   }
}