package domain;

import constants.Messages;
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

   public int execute()
   {
      BankDatabase bankDatabase = getBankDatabase();

      amount = depositService.promptForDepositAmount();

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
} 