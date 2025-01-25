package pl.edu.pjwstk.s29748.s29748Bank.finances;

public class Finances {
    int financesID;
    public double financesAmount;

    public Finances(int financesID, double financesAmount) {
        this.financesID = financesID;
        this.financesAmount = financesAmount;
    }

    public int getFinancesID() {
        return financesID;
    }

    public double getFinancesAmount() {
        return financesAmount;
    }
}
