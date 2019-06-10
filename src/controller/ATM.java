package service;

public class ATM{
   private boolean userAuthenticated;
   private int currentAccountNumber;
   private Screen screen;
   private Keypad keypad;
   private CashDispenser cashDispenser;
   private DepositSlot depositSlot;
   private BankDatabase bankDatabase;
   private static UI ui;
   
   public ATM(){
      userAuthenticated = false;
      currentAccountNumber = 0;
      keypad = new Keypad(); 
      cashDispenser = new CashDispenser();
      depositSlot = new DepositSlot();
      bankDatabase = new BankDatabase();
   } 

   public void run(){
      while (true)
      {
         performTransactions();
         userAuthenticated = false;
         currentAccountNumber = 0;
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

   public boolean validatePIN(int userAccountNumber, int userPIN){
      Account userAccount = bankDatabase.getAccount(userAccountNumber);

      return userAccount != null && userPIN == userAccount.getPin();
   }

   private void performTransactions(){
      Transaction currentTransaction = null;
      
      boolean userExited = false;

      while (!userExited){     
         int mainMenuSelection = displayMainMenu();

         switch (mainMenuSelection){
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
               screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
               break;
         }
      }
   }
}