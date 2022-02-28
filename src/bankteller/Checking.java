package bankteller;


import java.text.DecimalFormat;

/**
 * The type Checking.
 */
public class Checking extends Account {
    /**
     * The Fee.
     */
    protected double fee;
    /**
     * The Rate.
     */
    protected double rate = .001/12;

    private final String TYPE = "Checking";

    /**
     * Instantiates a new Checking.
     */
    public Checking() {

    }

    /**
     * Instantiates a new Checking.
     *
     * @param holder  the holder
     * @param balance the balance
     */
    public Checking(Profile holder, double balance) {
        super.holder = holder;
        super.balance = balance;


    }

    /**
     *
     * @param amount the amount
     */
    @Override
    public void open(double amount) {
        super.open(amount);
    }

    /**
     *
     * @param amount the amount
     * @return
     */
    @Override
    public boolean isSufficentFunds(double amount) {
        return super.isSufficentFunds(amount);
    }

    /**
     *
     */

    @Override
    public void close() {
        super.close();
    }

    /**
     *
     * @return
     */
    @Override
    public double fee() {
        if (super.balance >= 1000) {
            this.fee = 0;
            return this.fee;
        }
        this.fee = 25;
        return this.fee;
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
    public String toString() {
        return super.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public String printFormat() {
        DecimalFormat deciFormat = new DecimalFormat("###,###,###.##");

        deciFormat.setMaximumFractionDigits(2);
        deciFormat.setMinimumFractionDigits(2);

        String rateRounded = deciFormat.format(super.rounder(super.balance));
        if (super.closed) {
            return this.TYPE + "::" + super.holder.toString() + "::Balance $" + rateRounded + "::CLOSED";
        }
        return this.TYPE + "::" + super.holder.toString() + "::Balance $" + rateRounded;
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
     * @param amount the amount
     */

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
    }

    /**
     *
     * @return
     */
    @Override
    public String interestPreview() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###.##");

        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        String acct = this.toString();
        return acct + "::fee $" + decimalFormat.format(this.fee()) + "::monthly interest $"
                + decimalFormat.format(this.monthlyInterest());
    }

    /**
     *
     */
    @Override
    public void setMonthlyInterest() {
        if (super.isClosed()) {
            return;
        }
        super.balance -= this.fee();
        super.balance += this.monthlyInterest();
        super.balance = super.rounder(super.balance);
    }

    /**
     *
     * @return
     */
    @Override
    public double monthlyInterest() {
        if (super.isClosed()) {
            return 0.00;
        }

        double monthlyInterest = this.balance * this.rate;
        return super.rounder(monthlyInterest);
    }
}
