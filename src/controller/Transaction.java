package controller;

public abstract class Transaction{
   private int accountNumber;
   private BankDatabase bankDatabase;

   public Transaction(int userAccountNumber, BankDatabase atmBankDatabase){
      accountNumber = userAccountNumber;
      bankDatabase = atmBankDatabase;
   }

   public int getAccountNumber(){
      return accountNumber; 
   }

   public BankDatabase getBankDatabase(){
      return bankDatabase;
   }

   abstract public void execute();
}