package bankteller;


import java.text.DecimalFormat;

/**
 * The type Money market account.
 */
public class MoneyMarketAccount extends Account {
    private boolean isLoyal = true;
    private double fee = 10;
    private double rate = .095 / 12;
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

        if (this.balance < 2500) {
            this.isLoyal = false;
            this.rate = .08 / 12;
        }

        this.fee = this.fee();
    }

    @Override
    public void close() {
        super.close();
    }


    @Override
    public double fee() {

        if (super.balance >= 2500) {
            this.fee = 0;
        }

        if (execessWithdrawl) {
            return 10;
        }

        return this.fee;
    }

    private void updateLoyalty() {
        if (super.balance < 2500) {
            this.isLoyal = false;
            this.fee = fee;
            this.rate = .08 / 12;
        }

        this.isLoyal = true;
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        this.updateLoyalty();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String printFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##");
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);

        double monthlyInterest = this.balance * this.rate;

        String rateRounded = decimalFormat.format(super.rounder(monthlyInterest));

        if (!super.closed) {
            if (this.isLoyal)
                return this.TYPE + "::" + super.holder.toString() + "::Balance $" +
                        rateRounded + ":Loyal:withdrawl: " + Integer.toString(numWithdrawls);
            return this.TYPE + "::" + super.holder.toString() + "::Balance $" +
                    rateRounded + ":Loyal:withdrawl: " + Integer.toString(numWithdrawls);
        }
        return this.TYPE + "::" + super.holder.toString() + "::Balance $" + rateRounded + "::CLOSED::"
                + "::withdrawl: " + Integer.toString(numWithdrawls);
    }

    @Override
    public boolean isSufficentFunds(double amount) {
        if (super.balance - this.fee - amount <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public void withdraw(double amount) {
        this.numWithdrawls++;
        if (numWithdrawls > 3) {
            execessWithdrawl = true;
        }
        this.fee = fee();
        super.withdraw(amount);

        this.updateLoyalty();
    }

    @Override
    public String interestPreview() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##");
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);

        return this.toString() + "::fee $" + decimalFormat.format(this.fee()) + "::monthly interest $"
                + decimalFormat.format(this.monthlyInterest());
    }

    @Override
    public void setMonthlyInterest() {
        super.balance -= this.fee();
        super.balance += this.monthlyInterest();
    }


    @Override
    public double monthlyInterest() {


        double monthlyInterest = this.balance * this.rate;


        return (super.rounder(monthlyInterest));
    }
}
