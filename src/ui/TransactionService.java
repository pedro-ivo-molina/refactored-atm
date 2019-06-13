package ui;

import ui.ScreenService;
import domain.Keypad;

public interface TransactionService {
	void executeTransaction(int type, int accountNumber);
}
