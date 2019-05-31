package ui;

public class Menu implements UI {
  private static final int BALANCE_INQUIRY = 1;
  private static final int WITHDRAWAL = 2;
  private static final int DEPOSIT = 3;
  private static final int EXIT = 4;
  private Scanner in;
  
  // display the main menu and return an input selection
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

  // return object of specified Transaction subclass
  private Transaction createTransaction(int type)
  {
     Transaction temp = null; // temporary Transaction variable
     
     // determine which type of Transaction to create     
     switch (type)
     {
        case BALANCE_INQUIRY: // create new BalanceInquiry transaction
           temp = new BalanceInquiry(
              currentAccountNumber, screen, bankDatabase);
           break;
        case WITHDRAWAL: // create new Withdrawal transaction
           temp = new Withdrawal(currentAccountNumber, screen, 
              bankDatabase, keypad, cashDispenser);
           break; 
        case DEPOSIT: // create new Deposit transaction
           temp = new Deposit(currentAccountNumber, screen, 
              bankDatabase, keypad, depositSlot);
           break;
     } // end switch

     return temp; // return the newly created object
  } // end method createTransaction
}

