package factory;

import service.WithdrawalServiceImpl;
import service.ScreenServiceImpl;
import service.TransactionServiceImpl;
import service.AccountServiceImpl;
import ui.WithdrawalService;
import ui.ScreenService;
import ui.TransactionService;
import ui.AccountService;

public class ServiceFactory {
	public static WithdrawalService getWithdrawalService() {
        return new WithdrawalServiceImpl();
	}
	
	public static ScreenService getScreenService() {
		return new ScreenServiceImpl();
	}
	
	public static TransactionService getTransactionService() {
		return new TransactionServiceImpl();
	}
	
	public static AccountService getAccountService() {
		return new AccountServiceImpl();
	}
}
