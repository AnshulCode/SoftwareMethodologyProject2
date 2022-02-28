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

        Profile holder2 = new Profile("April", "March", "1/15/1987");

        Profile holder3 = new Profile("Aayush", "prasad", "06/18/2001");

        Account account2 = new MoneyMarketAccount(holder2, 2600);
        Account account5 = new Savings(holder2, 100.0,1);

        AccountDatabase test = new AccountDatabase();

        test.open(account5);
        test.open(account2);
        test.print();



    }
}
