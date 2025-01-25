package pl.edu.pjwstk.s29748.s29748Bank.finances;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.s29748.s29748Bank.account.Account;

@Service
public class FinancesService {
    private final FinancesStorage financesStorage;

    public FinancesService(FinancesStorage financesStorage) {
        this.financesStorage = financesStorage;
    }

    public Finances findByFinancesID(int financesID) {
        Finances selectedFinances = null;
        for (Finances finances : financesStorage.getAllFinances()) {
            if (finances.getFinancesID() == financesID) {
                selectedFinances = finances;
            }
        }
        if (selectedFinances == null) {
            throw new RuntimeException("Finances not found");
        }
        return selectedFinances;
    }

    public void newFinances(double amount) {
        int newFinancesID = 1;
        for (Finances finances : financesStorage.getAllFinances()) {
            newFinancesID++;
        }
        financesStorage.newFinances(new Finances(newFinancesID, amount));
    }
}