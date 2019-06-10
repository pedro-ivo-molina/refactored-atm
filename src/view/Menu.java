package ui;

import main.ATM;
import main.UI;
import Keypad;


public class Menu implements UI 
{
  private ATM atm;  
  private Keypad keypad;

  public Menu()
  {
	atm = new ATM();
	keypad = new Keypad();
  }
  
  public int menu()
  {
    System.out.println("\nMain menu:");
    System.out.println("1 - View my balance");
    System.out.println("2 - Withdraw cash");
    System.out.println("3 - Deposit funds");
    System.out.println("4 - Exit\n");
    System.out.println("Enter a choice: ");
    return keypad.getInput();
  }
  
  private Transaction createTransaction(int type)
  {
	 atm.executeTransaction(type);
  }

  /** NÃO ESQUECER DE FAZER UM MÉTODO QUE VAI PEGAR ESSA PORRA!!!!!!!!!!!!!!!!!!!!!!!!!!
  System.out.displayMessage("\nPlease enter your account number: ");
  int accountNumber = keypad.getInput();
  screen.displayMessage("\nEnter your PIN: ");
  int pin = keypad.getInput();
  */
}

