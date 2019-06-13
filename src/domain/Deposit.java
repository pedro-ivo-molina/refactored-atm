package domain;

import constants.Messages;
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

   public int execute()
   {
      BankDatabase bankDatabase = getBankDatabase();

      amount = promptForDepositAmount();

      if (amount != CANCELED) {
         screenService.displayMessage(
            "\nPlease insert a deposit envelope containing ");
         screenService.displayDollarAmount(amount);
         screenService.displayMessageLine(".");

         boolean envelopeReceived = depositSlot.isEnvelopeReceived();

         if (envelopeReceived) {
            bankDatabase.credit(getAccountNumber(), amount);
            return Messages.ENVELOPE_RECEIVED.ordinal();
         }

         return Messages.NO_ENVELOPE.ordinal();
      }
      return Messages.TRANSACTION_CANCELED.ordinal();
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