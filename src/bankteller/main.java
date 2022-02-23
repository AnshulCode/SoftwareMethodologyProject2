package bankteller;

public class main {
    public static void main(String[] args) {
        Profile holder = new Profile("Anshul","prasad","06/18/2001");
        System.out.println(holder.toString());
        Profile holder2 = new Profile("Anshul","prasad","06/18/2001");

        Account account2 = new MoneyMarketAccount(holder,3000);
        Account account = new CollegeCheckingAccount(holder2,100.0,1);
        MoneyMarketAccount mm = (MoneyMarketAccount)account;

        account2 = (Account)mm;
        account.setMonthlyInterest();
        System.out.println(account2.equals(account));






    }
}
