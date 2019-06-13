package ui;

public interface AccountService {
	boolean authenticateUser(int accountNumber, int pin);
	double getAvailableBalance(int accountNumber);
	double getTotalBalance(int accountNumber);
}
