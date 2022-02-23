package bankteller;

/**
 * The type Account database.
 */
public class AccountDatabase {
    private Account[] accounts;
    private int numAcct;

    /**
     * Instantiates a new Account database.
     */
    public AccountDatabase() {
        this.accounts = new Account[4];
        this.numAcct = 0;
    }

    private int find(Account account) {
        for (int i = 0; i < this.accounts.length; i++) {
            if (this.accounts[i] != null) {
                if (this.accounts[i].equals(account)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Public find int.
     *
     * @param account the account
     * @return the int
     */
    public int publicFind(Account account) {
        return this.find(account);
    }

    private void grow() {
        if (this.numAcct == this.accounts.length) {
            Account[] newAccounts = new Account[this.accounts.length + 4];
            for (int i = 0; i < numAcct; i++) {
                newAccounts[i] = this.accounts[i];
            }
            this.accounts = newAccounts;
        }
    }

    /**
     * Open boolean.
     *
     * @param account the account
     * @return the boolean
     */
    public boolean open(Account account) {

        if (this.find(account) != -1) {

            return false;
        }
        this.grow();
        this.accounts[this.numAcct] = account;
        this.numAcct++;
        return true;
    }

    /**
     * Close boolean.
     *
     * @param account the account
     * @return the boolean
     */
    public boolean close(Account account) {
        if (this.find(account) == -1) {
            return false;
        }
        this.accounts[this.find(account)].close();
        return true;
    }

    /**
     * Deposit.
     *
     * @param account the account
     */
    public void deposit(Account account) {
        if (this.find(account) == -1) {
            return;
        }
        if (this.accounts[this.find(account)].isClosed()) {
            return;
        }
        this.accounts[this.find(account)].deposit(account.getBalance());
    }

    /**
     * Withdraw boolean.
     *
     * @param account the account
     * @return the boolean
     */
    public boolean withdraw(Account account) {
        if (this.find(account) == -1) {
            return false;
        }
        if (this.accounts[this.find(account)].isClosed()) {
            return false;
        }
        if (!this.accounts[this.find(account)].isSufficentFunds(account.getBalance())) {
            return false;
        }
        this.accounts[this.find(account)].withdraw(account.getBalance());

        return true;
    } //return false if insufficient fund


    /**
     * Print.
     */
    public void print() {

        for (Account account : this.accounts) {
            if (account != null) {
                System.out.println(account.toString());
            }
        }


    }


    /**
     * Sort by account type.
     */
    public void sortByAccountType() {
        for (int i = 0; i < this.numAcct; i++) {
            int swaps = 0;
            for (int j = 0; j < this.numAcct - i - 1; j++) {
                if (this.accounts[j].getType().compareTo(this.accounts[j + 1].getType()) > 0) {
                    Account tmp = this.accounts[j];
                    this.accounts[j] = this.accounts[j + 1];
                    this.accounts[j + 1] = tmp;
                    swaps++;
                }
            }
            if (swaps == 0) {
                break;
            }
        }
    }


    /**
     * Print by account type.
     */
    public void printByAccountType() {
        System.out.println("*list of accounts by account type.");
        this.sortByAccountType();
        this.print();
        System.out.println("*end of list.");

    }

    /**
     * Print fee and interest.
     */
    public void printFeeAndInterest() {
        System.out.println("*list of accounts with fee and monthly interest");
        for (int i = 0; i < numAcct; i++) {
            System.out.println(this.accounts[i].interestPreview());
        }
        System.out.println("*end of list.");
    }

}
