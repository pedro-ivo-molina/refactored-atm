package ui;

import domain.Keypad;
import service.ScreenServiceImpl;
import service.AccountServiceImpl;
import service.TransactionServiceImpl;


public class ATM {
   private boolean userAuthenticated;
   private ScreenService screen;
   private Keypad keypad;
   private AccountService accountService;
   private TransactionService transactionService;
   
   private int currentAccountNumber;

   private static final int BALANCE_INQUIRY = 1;
   private static final int WITHDRAWAL = 2;
   private static final int DEPOSIT = 3;
   private static final int EXIT = 4;

   public ATM() {
      userAuthenticated = false;
      screen = new ScreenServiceImpl();
      keypad = new Keypad();
      accountService = new AccountServiceImpl();
      transactionService = new TransactionServiceImpl();
   }

   public void run()
   {
      while (true)
      {
         while (!userAuthenticated) 
         {
            screen.displayMessageLine("\nWelcome!");       
            authenticateUser();
         }
         
         performTransactions();
         userAuthenticated = false;
         screen.displayMessageLine("\nThank you! Goodbye!");
      } 
   }

   private void authenticateUser() 
   {
      screen.displayMessage("\nPlease enter your account number: ");
      int accountNumber = keypad.getInput();
      screen.displayMessage("\nEnter your PIN: ");
      int pin = keypad.getInput();

      userAuthenticated = accountService.authenticateUser(accountNumber, pin);
      
      if(userAuthenticated){
    	  currentAccountNumber = accountNumber;
      } else{
    	  screen.displayMessageLine("Error! You must enter a valid account number!");    	  
      }
   }
   
   private void performTransactions() {  
      boolean userExited = false;

      while (!userExited)
      {     
         int mainMenuSelection = displayMainMenu();

         switch (mainMenuSelection)
         {
            case BALANCE_INQUIRY: 
            case WITHDRAWAL: 
            case DEPOSIT:
               transactionService.executeTransaction(mainMenuSelection, currentAccountNumber);
               break; 
            case EXIT:
               screen.displayMessageLine("\nExiting the system...");
               userExited = true;
               break;
            default:
               screen.displayMessageLine(
                  "\nYou did not enter a valid selection. Try again.");
               break;
         }
      }
   }
   
   private int displayMainMenu()
   {
      screen.displayMessageLine("\nMain menu:");
      screen.displayMessageLine("1 - View my balance");
      screen.displayMessageLine("2 - Withdraw cash");
      screen.displayMessageLine("3 - Deposit funds");
      screen.displayMessageLine("4 - Exit\n");
      screen.displayMessage("Enter a choice: ");
      return keypad.getInput();
   }
}