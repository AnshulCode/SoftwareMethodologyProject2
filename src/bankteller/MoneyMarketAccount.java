package bankteller;


import java.text.DecimalFormat;

/**
 * The type Money market account.
 */
public class MoneyMarketAccount extends Savings{


    private final String TYPE = "Money Market Savings";

    private boolean execessWithdrawl = false;
    private int numWithdrawls = 0;

    /**
     * Instantiates a new Money market account.
     *
     * @param holder  the holder
     * @param balance the balance
     */
    public MoneyMarketAccount(Profile holder, double balance) {
        super.holder = holder;
        super.balance = balance;

        if (super.balance < 2500) {
            super.isLoyal = false;
            super.rate = .08 / 12;
        } else {
            super.isLoyal = true;
        }
        super.rate = .095 / 12;
        this.fee();
    }

    /**
     *
     * @param amount
     */
    @Override
    public void open(double amount) {
        super.open(amount);
        this.updateLoyalty();
    }

    /**
     * implements the Account close function
     */

    @Override
    public void close() {
        super.close();
    }

    /**
     * gets fee
     *
     * @return the fee
     */
    @Override
    public double fee() {

        if (super.balance >= 2500) {
            super.fee = 0;
        }

        if (this.execessWithdrawl) {
            super.fee = 10;
            return 10;
        }

        return super.fee;
    }

    /**
     * updates loyalty status and adjust fees and raters accordingly
     */
    private void updateLoyalty() {
        if (super.balance < 2500) {
            super.isLoyal = false;
            super.rate = .08 / 12;
            return;
        }
        this.isLoyal = true;
    }

    /**
     *
     * @param amount the amount
     */
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        this.updateLoyalty();
    }

    /**
     *
     * @return
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     *
     * @return
     */
    @Override
    public String printFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##");
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);




        String balenceRounded = decimalFormat.format(super.rounder(super.getBalance()));

        if (!super.closed) {
            if (this.isLoyal)
                return this.TYPE + "::" + super.holder.toString() + "::Balance $" +
                        balenceRounded + "::Loyal::withdrawl: " + Integer.toString(this.numWithdrawls);
            return this.TYPE + "::" + super.holder.toString() + "::Balance $" +
                    balenceRounded + ":Loyal:withdrawl: " + Integer.toString(numWithdrawls);
        }
        return this.TYPE + "::" + super.holder.toString() + "::Balance $" + balenceRounded + "::CLOSED"
                + "::withdrawl: " + Integer.toString(this.numWithdrawls);
    }

    /**
     *
     * @param amount
     * @return
     */
    @Override
    public boolean isSufficentFunds(double amount) {
        return super.isSufficentFunds(amount);
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     *
     * @param amount
     */
    @Override
    public void withdraw(double amount) {
        double prev_balence = super.getBalance();
        super.withdraw(amount);
        if (prev_balence != super.getBalance()) {
            this.numWithdrawls++;
            if (numWithdrawls > 3) {
                execessWithdrawl = true;
            }

        }

        super.fee = this.fee();
        this.updateLoyalty();
    }

    /**
     *
     * @return
     */
    @Override
    public String interestPreview() {
        return super.interestPreview();
    }

    /**
     *
     */
    @Override
    public void setMonthlyInterest() {
      super.setMonthlyInterest();
    }

    /**
     *
     * @return
     */
    @Override
    public double monthlyInterest() {
        return super.monthlyInterest();
    }
}
