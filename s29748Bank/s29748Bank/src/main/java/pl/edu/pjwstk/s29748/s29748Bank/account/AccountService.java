package pl.edu.pjwstk.s29748.s29748Bank.account;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.s29748.s29748Bank.finances.Finances;
import pl.edu.pjwstk.s29748.s29748Bank.finances.FinancesService;

@Service
public class AccountService {
    private final AccountStorage accountStorage;
    private final FinancesService financesService;

    public AccountService(AccountStorage accountStorage, FinancesService financesService) {
        this.accountStorage = accountStorage;
        this.financesService = financesService;
    }
    public Account findByAccountID(int accountID) {
        Account selectedAccount= null;
        for(Account account: accountStorage.getAccounts()) {
            if(account.getAccountID() == accountID) {
               selectedAccount = account;
            }
        }
        if(selectedAccount == null){
            throw new RuntimeException("Account not found, access DENIED");
        }
        return selectedAccount;
    }
    public void newAccount(double amount) {
        int newAccountID = 1;
        for (Account account : accountStorage.getAccounts()) {
            newAccountID++;
        }
        int clientID= newAccountID;
        financesService.newFinances(amount);
       int financeID= financesService.findByFinancesID(newAccountID).getFinancesID();

        accountStorage.newAccount(new Account(newAccountID,financeID,clientID) );
        System.out.println("Congratulations! you created an account. your client ID is: "+clientID);
    }

    public Account findByClientID(int clientID) {
        Account accountByClient= null;
        for(Account account: accountStorage.getAccounts()) {
            if(account.getClientID() == clientID) {
                accountByClient = account;
            }
        }
        if(accountByClient == null){
            throw new RuntimeException("No account with that client found. Access DENIED");
        }
        return accountByClient;
    }

    public void przelew(int clientID,double amount) {
       Account selectedAccount= findByClientID(clientID);
       Finances finances=financesService.findByFinancesID(selectedAccount.getFinancesID());
       System.out.println("Finances before przelew: "+ finances.getFinancesAmount());
       if(finances.getFinancesAmount() < amount) {
           throw new RuntimeException("Transfer DENIED, You don't have enough money");
       }
       finances.financesAmount= finances.financesAmount - amount;
        System.out.println("Transfer ACCEPTED. New balance is: "+ finances.getFinancesAmount());
    }
    public void deposit(int clientID,double amount) {
        Account selectedAccount= findByClientID(clientID);
        Finances finances=financesService.findByFinancesID(selectedAccount.getFinancesID());
        System.out.println("Finances before przelew: "+ finances.getFinancesAmount());
        finances.financesAmount= finances.financesAmount + amount;
        System.out.println("Finances after przelew: "+ finances.getFinancesAmount());
    }
    public void readClient(Account account) {
        System.out.println("Client id for account "+account.getAccountID() + " is: " + account.getClientID());
    }
}
