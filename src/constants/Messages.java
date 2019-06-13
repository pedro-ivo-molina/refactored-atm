package constants;

public enum Messages {

    ENVELOPE_RECEIVED("\nYour envelope has been received.\\nNOTE: The money just deposited will not be available until we verify the amount of any enclosed cash and your checks clear."),
    NO_ENVELOPE("You did not insert an envelope, so the ATM has canceled your transaction."),
    TRANSACTION_CANCELED("Canceling transaction..."),
    CASH_DISPENSED("Your cash has been dispensed. Please take your cash now."),
    INSUFFICIENT_CASH_ATM("\nInsufficient cash available in the ATM.\n\nPlease choose a smaller amount."),
    INSUFFICIENT_FUNDS("\nInsufficient funds in your account.\n\nPlease choose a smaller amount.");

    private String message;

    Messages(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public Messages getById(int id){
        for(Messages message : values())
            if(message.ordinal() == id)
                return message;
    }
}
