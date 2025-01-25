package pl.edu.pjwstk.s29748.s29748Bank.finances;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.s29748.s29748Bank.account.Account;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FinancesStorage {
    List<Finances> allFinances = new ArrayList<Finances>();

    public FinancesStorage(List<Finances> allFinances) {
        this.allFinances = allFinances;
        allFinances.add(new Finances(1, 4200d));
        allFinances.add(new Finances(2, 3200d));
        allFinances.add(new Finances(3, 2200d));
        allFinances.add(new Finances(4, 1200d));
    }

    public List<Finances> getAllFinances() {
        return allFinances;
    }
    public void newFinances(Finances finances) {allFinances.add(finances);}
}
