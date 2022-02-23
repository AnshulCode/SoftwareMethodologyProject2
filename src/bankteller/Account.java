package bankteller;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public abstract class Account {

        protected Profile holder;
        protected boolean closed;
        protected double balance;

        public double getBalance() {
            return this.balance;
        }


        public void setStatus(boolean toClose){
                if(this.closed == toClose){
                        return;
                }
                this.closed = toClose;
        }



        public Profile getHolder() {
                return this.holder;
        }
        @Override
        public boolean equals(Object obj) {
                Account compare = (Account)obj;

                if(!compare.getType().equals(this.getType())){
                        return false;
                }
                if(!this.holder.equals(compare.getHolder())){
                        return false;
                }

                return true;
        }
        @Override
        public String toString() {
                return this.printFormat();
        }

        public void withdraw(double amount) {
                if(this.balance - amount <= 0){
                        return;
                }
                this.balance -= amount;
        }

        public void deposit(double amount) {
                if(amount<0){
                        return;
                }
                this.balance += amount;
        }
        public abstract String interestPreview();
        public abstract boolean isSufficentFunds(double amount);
        public abstract void setMonthlyInterest();
        public abstract String  printFormat();
        public abstract double monthlyInterest(); //return the monthly interest
        public abstract double fee(); //return the monthly fee
        public abstract String getType(); //return the account type (class name)

}
