package bankteller;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Checking extends Account{
    private double fee = 25;
    private double rate = .001/12;
    private final String TYPE = "Checking";

    public Checking(Profile holder , double balance){
        super.holder = holder;
        super.balance = balance;
    }

    @Override
    public boolean isSufficentFunds(double amount) {
        if(super.balance-this.fee-amount <= 0){
            return false;
        }
        return true;
    }
    @Override
    public void setStatus(boolean toClose){
        super.setStatus(toClose);
    }
    @Override
    public double fee() {
        if(super.balance >= 1000){
            this.fee = 0;
            return this.fee;
        }
        return this.fee;
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
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
    }

    @Override
    public String interestPreview() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###,###,###.##");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);
        String acct = this.toString();
        return acct+"::fee $"+decimalFormat.format(this.fee())+"::monthly interest $"+this.monthlyInterest();
    }

    @Override
    public void setMonthlyInterest(){
        super.balance -= this.fee();
        super.balance += this.monthlyInterest();
    }


    @Override
    public double monthlyInterest() {
        return this.balance*this.rate;
    }
}
