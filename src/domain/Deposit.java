package domain;

import service.BankDatabase;
import ui.ScreenService;
import ui.DepositService;
import factory.ServiceFactory;

public class Deposit extends Transaction
{
   private double amount;
   private DepositSlot depositSlot;
   private ScreenService screenService;
   private DepositService depositService;
   private final static int CANCELED = 0;

   public Deposit(int userAccountNumber, ScreenService atmScreen, 
      BankDatabase atmBankDatabase, 
      DepositSlot atmDepositSlot){
      super(userAccountNumber, atmBankDatabase);

      depositSlot = atmDepositSlot;
      screenService = atmScreen;
      depositService = ServiceFactory.getDepositService();
   }

   public void execute()
   {
      BankDatabase bankDatabase = getBankDatabase();

      amount = depositService.promptForDepositAmount();

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
} 