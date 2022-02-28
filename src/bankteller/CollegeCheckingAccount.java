package bankteller;


import java.text.DecimalFormat;


/**
 * The type College checking account.
 */
public class CollegeCheckingAccount extends Checking {


    private static final String NEW_BRUNSWICK = "NEW_BRUNSWICK";
    private static final String CAMDEN = "CAMDEN";
    private static final String NEWARK = "NEWARK";
    private static final String TYPE = "College Checking";



    private int location;



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
        super.rate = 0.0025 / 12;

    }

    /**
     *
     */
    @Override
    public void close(){
        super.close();
    }

    /**
     *Gets fee for account
     * @return the fee for a College checking Account
     */

    @Override
    public double fee() {
        return 0;
    }
    /**
     * returns the class type for a College checking Account
     * @return
     */
    @Override
    public String getType() {
        return TYPE;
    }



    @Override
    public void open(double amount) {
        super.open(amount);
    }
    /**
     * turns the CollageCheckingAccount into readable string format
     * Implemented from Accounts class
     * @return CollageCheckingAccount in string format
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Check if there is enoguht for a withdrawl
     * Implemented from Account class
     * @param amount amount to check if there is enough  for a withdraw
     * @return True if there is, false otherwise
     */
    @Override
    public boolean isSufficentFunds(double amount) {
        return super.isSufficentFunds(amount);
    }

    /**
     * Gives the format for toString to print
     * Implemented from Accounts class
     * This is needed to get the right input for the toString method
     * @return the CollageCheckingAccount in string format
     */
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

    /**
     *Invokes the Account method's deposit function
     * @param amount the amount to deposit
     */

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
    }

    /**
     * Invokes the Account method's equals function
     * @param obj object to comapre
     * @return True if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Invokes the Account method's withdraw function
     * @param amount the amount to withdraw
     */
    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
    }

    /**
     * implementation of abstract method in Account with
     * same name
     * gets the monthly interest
     * @return the monthly intrest
     */
    @Override
    public double monthlyInterest() {

       return super.monthlyInterest();
    }

    /**
     * implementation of abstract method in Account
     * with same name
     * This is mainly used in the printFeeAndInterest method in
     * AccountDatabase
     * @return the String format with fee and interest
     */
    @Override
    public String interestPreview() {
        return super.interestPreview();
    }

    /**
     * Updates the balance with the monthly interest
     */
    @Override
    public void setMonthlyInterest() {
        super.balance += this.monthlyInterest();
    }


}
