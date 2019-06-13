package service;

import ui.ATM;
import ui.WithdrawalService;

public class WithdrawalServiceImpl implements WithdrawalService {
	private ATM atm;
	
	public WithdrawalServiceImpl() {
		atm = new ATM();
	}
	
	public int displayMenuOfAmounts() {
		return atm.displayMenuOfAmounts();
	}
}
