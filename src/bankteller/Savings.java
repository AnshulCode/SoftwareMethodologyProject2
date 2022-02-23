package bankteller;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Savings extends Account {
    private final String TYPE = "Savings";
    private int fee = 6;
    private double rate = .03/12;

    private boolean isLoyal;

    public Savings(Profile holder, double balance , int loyalty){
        super.holder = holder;
        super.balance = balance;
        if(loyalty == 1){
            this.isLoyal = true;
        }
        if(this.isLoyal){
            this.rate = 0.045/12;
        }


    }
    @Override
    public double fee() {
        if(super.balance >= 300){
            this.fee = 0;
        }
        this.fee = 6;
        return this.fee;
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
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        String acct = this.toString();
        return acct+"::fee $"+decimalFormat.format(this.fee())+"::monthly interest $"+this.monthlyInterest();
    }

    @Override
    public boolean isSufficentFunds(double amount) {
        if(super.balance-this.fee-amount <= 0){
            return false;
        }
        return true;
    }

    @Override
    public void setMonthlyInterest(){
        super.balance -= this.fee();
        super.balance += this.monthlyInterest();
    }

    @Override
    public String printFormat() {
        DecimalFormat deciFormat = new DecimalFormat("###,###,###.##");
        deciFormat.setRoundingMode(RoundingMode.CEILING);
        deciFormat.setMaximumFractionDigits(2);
        deciFormat.setMinimumFractionDigits(2);

        String rateRounded = deciFormat.format(super.balance);
        if(super.closed){
            return this.TYPE+"::"+super.holder.toString()+":: Balance $"+rateRounded+"::CLOSED::";
        }
        return this.TYPE+"::"+super.holder.toString()+":: Balance $"+rateRounded;
    }


    @Override
    public double monthlyInterest() {
        return 0;
    }
}
