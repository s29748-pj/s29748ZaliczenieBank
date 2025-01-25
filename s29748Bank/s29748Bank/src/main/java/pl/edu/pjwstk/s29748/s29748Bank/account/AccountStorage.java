package pl.edu.pjwstk.s29748.s29748Bank.account;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountStorage {
List<Account> accounts;
public AccountStorage(List<Account> accounts) {
    this.accounts = accounts;
    accounts.add(new Account(1,1,1));
    accounts.add(new Account(2,2,2));
    accounts.add(new Account(3,3,3));
    accounts.add(new Account(4,4,4));
}

public List<Account> getAccounts() {return accounts;}
public void newAccount(Account account) {accounts.add(account);}
}
