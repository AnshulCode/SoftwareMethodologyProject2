package bankteller;


import java.text.DecimalFormat;

/**
 * The type Savings.
 */
public class Savings extends Account {
    private final String TYPE = "Savings";
    private int fee = 6;
    private double rate = .03 / 12;


    private boolean isLoyal;

    /**
     * Instantiates a new Savings.
     *
     * @param holder  the holder
     * @param balance the balance
     * @param loyalty the loyalty
     */
    public Savings(Profile holder, double balance, int loyalty) {
        super.holder = holder;
        super.balance = balance;
        if (loyalty == 1) {
            this.isLoyal = true;
        }
        if (this.isLoyal) {
            this.rate = 0.045 / 12;
        }


    }
    @Override
    public void open(double amount) {
        super.open(amount);
    }

    @Override
    public double fee() {
        if (super.balance >= 300) {
            this.fee = 0;
        }
        this.fee = 6;
        return this.fee;
    }

    @Override
    public void close() {
        super.close();
    }


    @Override
    public String getType() {
        return this.TYPE;
    }

    @Override
    public String toString() {
        return super.toString();
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
    public String interestPreview() {
        DecimalFormat decimalFormat = new DecimalFormat("##,###,###,###.##");

        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        String acct = this.toString();
        return acct + "::fee $" + decimalFormat.format(this.fee()) + "::monthly interest $" + decimalFormat.format(this.monthlyInterest());
    }

    @Override
    public boolean isSufficentFunds(double amount) {
        return super.isSufficentFunds(amount);
    }

    @Override
    public void setMonthlyInterest() {
        if (super.isClosed()) {
            return;
        }
        super.balance -= this.fee();
        super.balance += this.monthlyInterest();
        super.balance = super.rounder(super.balance);
    }

    @Override
    public String printFormat() {
        DecimalFormat deciFormat = new DecimalFormat("###,###,###.##");

        deciFormat.setMaximumFractionDigits(2);
        deciFormat.setMinimumFractionDigits(2);

        String rateRounded = deciFormat.format(super.rounder(super.balance));
        if (super.closed) {
            return this.TYPE + "::" + super.holder.toString() + ":: Balance $" + rateRounded + "::CLOSED";
        }
        return this.TYPE + "::" + super.holder.toString() + ":: Balance $" + rateRounded;
    }


    @Override
    public double monthlyInterest() {
        return super.rounder(this.balance * this.rate);
    }
}
