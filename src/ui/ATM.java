package ui;

import domain.Keypad;
import factory.ServiceFactory;
import service.ScreenServiceImpl;
import service.AccountServiceImpl;
import service.TransactionServiceImpl;


public class ATM {
   private boolean userAuthenticated;
   private ScreenService screenService;
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
      screenService = ServiceFactory.getScreenService();
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
            screenService.displayMessageLine("\nWelcome!");
            authenticateUser();
         }
         
         performTransactions();
         userAuthenticated = false;
         screenService.displayMessageLine("\nThank you! Goodbye!");
      } 
   }

   private void authenticateUser() 
   {
      screenService.displayMessage("\nPlease enter your account number: ");
      int accountNumber = keypad.getInput();
      screenService.displayMessage("\nEnter your PIN: ");
      int pin = keypad.getInput();

      userAuthenticated = accountService.authenticateUser(accountNumber, pin);
      
      if(userAuthenticated){
    	  currentAccountNumber = accountNumber;
      } else{
    	  screenService.displayMessageLine("Error! You must enter a valid account number!");
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
                double availableBalance = accountService.getAvailableBalance(currentAccountNumber);
                double totalBalance = accountService.getTotalBalance(currentAccountNumber);
                screenService.displayMessageLine("\nBalance Information:");
                screenService.displayMessage(" - Available balance: ");
                screenService.displayDollarAmount(availableBalance);
                screenService.displayMessage("\n - Total balance:     ");
                screenService.displayDollarAmount(totalBalance);
                screenService.displayMessageLine("");
            case WITHDRAWAL: 
            case DEPOSIT:
               transactionService.executeTransaction(mainMenuSelection, currentAccountNumber);
               break; 
            case EXIT:
               screenService.displayMessageLine("\nExiting the system...");
               userExited = true;
               break;
            default:
               screenService.displayMessageLine(
                  "\nYou did not enter a valid selection. Try again.");
               break;
         }
      }
   }
   
   private int displayMainMenu(){
      screenService.displayMessageLine("\nMain menu:");
      screenService.displayMessageLine("1 - View my balance");
      screenService.displayMessageLine("2 - Withdraw cash");
      screenService.displayMessageLine("3 - Deposit funds");
      screenService.displayMessageLine("4 - Exit\n");
      screenService.displayMessage("Enter a choice: ");
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
	              screenService.displayMessageLine(
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