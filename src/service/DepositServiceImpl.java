package service;

import ui.DepositService;
import ui.ATM;

public class DepositServiceImpl implements DepositService {
	private ATM atm;
	
	public DepositServiceImpl() {
		atm = new ATM();
	}
	
	public double promptForDepositAmount() {
		return atm.promptForDepositAmount();
	}
}
