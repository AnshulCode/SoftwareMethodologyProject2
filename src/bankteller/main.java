package bankteller;

/**
 * The type Main.
 */
public class main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Profile holder = new Profile("Anshul", "prasad", "06/18/2001");

        Profile holder2 = new Profile("Anshul", "prasad", "06/18/2001");

        Profile holder3 = new Profile("Aayush", "prasad", "06/18/2001");

        Account account = new MoneyMarketAccount(holder, 3000);
        Account account2 = new CollegeCheckingAccount(holder2, 100.0, 1);
        Account account5 = new Checking(holder2, 100.0);
        Account account3 = new Savings(holder3, 300, 1);
        Account account4 = new Checking(holder3, 3000);

        AccountDatabase test = new AccountDatabase();

        test.open(account);
        test.open(account2);
        test.open(account3);
        test.open(account4);
        Account deposit = new MoneyMarketAccount(holder, 39990);
        Account withdraw = new Checking(holder3, 781);
        test.close(deposit);
        test.open(deposit);



        test.printFeeAndInterest();


    }
}
