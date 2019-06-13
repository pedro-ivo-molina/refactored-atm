package domain;

import service.BankDatabase;
import ui.ScreenService;

public class Deposit extends Transaction
{
   private double amount;
   private Keypad keypad;
   private DepositSlot depositSlot;
   private ScreenService screenService;
   private final static int CANCELED = 0;

   public Deposit(int userAccountNumber, ScreenService atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      DepositSlot atmDepositSlot){
      super(userAccountNumber, atmBankDatabase);

      keypad = atmKeypad;
      depositSlot = atmDepositSlot;
      screenService = atmScreen;
   }

   public void execute()
   {
      BankDatabase bankDatabase = getBankDatabase();

      amount = promptForDepositAmount();

      if (amount != CANCELED)
      {
         screenService.displayMessage(
            "\nPlease insert a deposit envelope containing ");
         screenService.displayDollarAmount(amount);
         screenService.displayMessageLine(".");

         boolean envelopeReceived = depositSlot.isEnvelopeReceived();

         if (envelopeReceived)
         {  
            screenService.displayMessageLine("\nYour envelope has been " + 
               "received.\nNOTE: The money just deposited will not " + 
               "be available until we verify the amount of any " +
               "enclosed cash and your checks clear.");
            
            bankDatabase.credit(getAccountNumber(), amount); 
         } else {
            screenService.displayMessageLine("\nYou did not insert an " +
               "envelope, so the ATM has canceled your transaction.");
         }
      } else {
         screenService.displayMessageLine("\nCanceling transaction...");
      }
   }
   
   private double promptForDepositAmount() {
      
      screenService.displayMessage("\nPlease enter a deposit amount in " + 
         "CENTS (or 0 to cancel): ");
      int input = keypad.getInput();
      
      if (input == CANCELED) 
         return CANCELED;
      else
         return (double) input / 100; 
   }
} 