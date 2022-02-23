package bankteller;

public class AccountDatabase {
    private Account [] accounts;
    private int numAcct;
    public AccountDatabase() {
        this.accounts = new Account[4];
        this.numAcct = 0;
    }
    private int find(Account account) {
        for(int i = 0; i < this.accounts.length; i++){
            if(this.accounts[i] != null){
                if(this.accounts[i].equals(account)){
                    return i;
                }
            }
        }
        return -1;
    }
    private void grow() {
        if(this.numAcct == this.accounts.length){
            Account[] newAccounts = new Account[this.accounts.length+4];
            for(int i = 0; i<numAcct; i++){
                newAccounts[i] = this.accounts[i];
            }
            this.accounts = newAccounts;
        }
    }
    public boolean open(Account account) {
        if(this.find(account) != -1){
            return false;
        }
        this.numAcct++;
        this.accounts = new Account[this.numAcct-1];

        return true;
    }
    public boolean close(Account account) {
        if(this.find(account) == -1){
            return false;
        }
        this.accounts[this.find(account)].setStatus(true);
        return true;
    }
    public void deposit(Account account) {
        if(this.find(account) == -1){
            return;
        }
        this.accounts[this.find(account)].deposit(account.getBalance());
    }
    public boolean withdraw(Account account) {
        if(this.find(account) == -1){
            return false;
        }
        return this.accounts[this.find(account)].isSufficentFunds(account.getBalance());
    } //return false if insufficient fund
    public void print() {
        for (Account account : this.accounts) {
            System.out.println(account.toString());
        }
    }
    public void printByAccountType() { }
    public void printFeeAndInterest() { }
}
