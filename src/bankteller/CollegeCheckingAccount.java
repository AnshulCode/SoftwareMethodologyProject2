package bankteller;


import java.text.DecimalFormat;


/**
 * The type College checking account.
 */
public class CollegeCheckingAccount extends Account {


    private static final String NEW_BRUNSWICK = "NEW_BRUNSWICK";
    private static final String CAMDEN = "CAMDEN";
    private static final String NEWARK = "NEWARK";
    private static final String TYPE = "College Checking";

    private int location;
    private double rate = 0.0025 / 12;


    /**
     * Instantiates a new College checking account.
     *
     * @param holder   the holder
     * @param balance  the balance
     * @param location the location
     */
    public CollegeCheckingAccount(Profile holder, double balance, int location) {

        super.holder = holder;
        super.balance = balance;
        this.location = location;

    }


    @Override
    public double fee() {
        return 0;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean isSufficentFunds(double amount) {
        if (super.balance - amount <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public String printFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##");
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);

        String rateRounded = decimalFormat.format(super.rounder(super.balance));

        if (!super.closed) {
            if (this.location == 0) {
                return this.TYPE + "::" + super.holder.toString() + "::Balance $" + rateRounded + "::" + this.NEW_BRUNSWICK;
            } else if (this.location == 1) {
                return this.TYPE + "::" + super.holder.toString() + "::Balance $" + rateRounded + "::" + this.NEWARK;
            } else {
                return this.TYPE + "::" + super.holder.toString() + "::Balance $" + rateRounded + "::" + this.CAMDEN;
            }
        }
        if (this.location == 0) {
            return this.TYPE + "::" + super.holder.toString() + "::Balance $::CLOSED::"
                    + rateRounded + "::" + this.NEW_BRUNSWICK;
        } else if (this.location == 1) {
            return this.TYPE + "::" + super.holder.toString() + "::Balance $::CLOSED::" + rateRounded + "::" + this.NEWARK;
        } else {
            return this.TYPE + "::" + super.holder.toString() + "::Balance $::CLOSED::" + rateRounded + "::" + this.CAMDEN;
        }


    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
    }


    @Override
    public double monthlyInterest() {


        double monthlyInterest = this.balance * this.rate;


        return super.rounder(monthlyInterest);
    }

    @Override
    public String interestPreview() {
        DecimalFormat decimalFormat = new DecimalFormat("##,###,###,###,###.##");
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        return this.toString() + "::fee $" + decimalFormat.format(this.fee()) + "::monthly interest $" +
                super.rounder(this.monthlyInterest());
    }

    @Override
    public void setMonthlyInterest() {
        super.balance += this.monthlyInterest();
    }


}
