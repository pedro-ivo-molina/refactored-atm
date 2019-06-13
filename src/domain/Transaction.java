package domain;

import service.BankDatabase;

public abstract class Transaction {
   private int accountNumber;
   private BankDatabase bankDatabase;

   public Transaction(int accountNumber, 
      BankDatabase bankDatabase) {
      this.accountNumber = accountNumber;
      this.bankDatabase = bankDatabase;
   }

   public int getAccountNumber() {
      return accountNumber; 
   }

   public BankDatabase getBankDatabase() {
      return bankDatabase;
   }

   abstract public int execute();
}