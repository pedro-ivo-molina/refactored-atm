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
   private final static int CANCELED = 0;

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
   
   private int displayMainMenu(){
      screen.displayMessageLine("\nMain menu:");
      screen.displayMessageLine("1 - View my balance");
      screen.displayMessageLine("2 - Withdraw cash");
      screen.displayMessageLine("3 - Deposit funds");
      screen.displayMessageLine("4 - Exit\n");
      screen.displayMessage("Enter a choice: ");
      return keypad.getInput();
   }
   
   public int displayMenuOfAmounts() {
	   int userChoice = 0;
	      
	    int[] amounts = {0, 20, 40, 60, 100, 200};

	    while (userChoice == 0) {
	    	screen.displayMessageLine("\nWithdrawal Menu:");
	        screen.displayMessageLine("1 - $20");
	        screen.displayMessageLine("2 - $40");
	        screen.displayMessageLine("3 - $60");
	        screen.displayMessageLine("4 - $100");
	        screen.displayMessageLine("5 - $200");
	        screen.displayMessageLine("0 - Cancel transaction");
	        screen.displayMessage("\nChoose a withdrawal amount: ");

	        int input = keypad.getInput();
	         
	        switch (input) {
	           case 1: 
	           case 2:
	           case 3:
	           case 4:
	           case 5:
	              userChoice = amounts[input];
	              break;       
	           case CANCELED:
	              userChoice = CANCELED;
	              break;
	           default:
	              screen.displayMessageLine(
	                  "\nInvalid selection. Try again.");
	         }
	      }

	    return userChoice;
   }
   
   public double promptForDepositAmount() {
      
      screen.displayMessage("\nPlease enter a deposit amount in " + 
         "(or 0 to cancel): ");
      int input = keypad.getInput();
      
      if (input == CANCELED) 
         return CANCELED;
      else
         return (double) input; 
   }
}