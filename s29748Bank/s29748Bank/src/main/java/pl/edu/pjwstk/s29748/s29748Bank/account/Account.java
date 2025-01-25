package pl.edu.pjwstk.s29748.s29748Bank.account;

public class Account {
    int accountID;
    int financesID;
    int clientID;

    public Account(int accountID, int financesID, int clientID) {
        this.accountID = accountID;
        this.financesID = financesID;
        this.clientID = clientID;
    }
    public int getAccountID() {return accountID;}
    public int getFinancesID() {return financesID;}
    public int getClientID() {return clientID;}
}
