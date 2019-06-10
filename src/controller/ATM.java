package service;

public class ATM 
{
   private boolean userAuthenticated;
   private int currentAccountNumber;
   private Screen screen;
   private Keypad keypad;
   private CashDispenser cashDispenser;
   private DepositSlot depositSlot;
   private BankDatabase bankDatabase;
   private static UI ui;
   
   public ATM() 
   {
      userAuthenticated = false; // user is not authenticated to start
      currentAccountNumber = 0; // no current account number to start
      keypad = new Keypad(); // create keypad 
      cashDispenser = new CashDispenser(); // create cash dispenser
      depositSlot = new DepositSlot(); // create deposit slot
      bankDatabase = new BankDatabase(); // create acct info database
   } // end no-argument ATM constructor

   // start ATM 
   public void run()
   {
      // welcome and authenticate user; perform transactions
      while (true)
      {
         // loop while user is not yet authenticated
         while (!userAuthenticated) 
         {
            screen.displayMessageLine("\nWelcome!");       
            authenticateUser(); // authenticate user
         } // end while
         
         performTransactions(); // user is now authenticated 
         userAuthenticated = false; // reset before next ATM session
         currentAccountNumber = 0; // reset before next ATM session 
         screen.displayMessageLine("\nThank you! Goodbye!");
      } 
   }

   private boolean authenticateUser(int accountNumber, int pin){      
      boolean userAuthenticated = validatePIN(accountNumber, pin);
      
      if (userAuthenticated){
         currentAccountNumber = accountNumber;
         return true;
      }
      else{
         return false;
      }
   } 

   public boolean validatePIN(int userAccountNumber, int userPIN)
   {
      Account userAccount = bankDatabase.getAccount(userAccountNumber);

      return userAccount != null && userPIN == userAccount.getPin();
   }

   private void performTransactions() 
   {
      Transaction currentTransaction = null;
      
      boolean userExited = false;

      while (!userExited)
      {     
         int mainMenuSelection = displayMainMenu();

         switch (mainMenuSelection)
         {
            case BALANCE_INQUIRY: 
            case WITHDRAWAL: 
            case DEPOSIT:

               currentTransaction = createTransaction(mainMenuSelection);

               currentTransaction.execute();
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
}