package service;

import ui.ScreenService;


public class ScreenServiceImpl implements ScreenService {
   public void displayMessage(String message) {
      System.out.print(message); 
   }

   public void displayMessageLine(String message) {
      System.out.println(message);   
   }

   public void displayDollarAmount(double amount) {
      System.out.printf("$%,.2f", amount);   
   }
}