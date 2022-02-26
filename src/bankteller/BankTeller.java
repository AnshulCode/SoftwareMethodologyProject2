package bankteller;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The type Bank teller.
 *
 * @author Alexander Reyes, Anshul P
 */
public class BankTeller {

    /**
     * The Scan.
     */
    public Scanner scan = new Scanner(System.in);
    /**
     * The Input.
     */
    public String input = "";
    /**
     * The Max len of bank cmd.
     */
    public int maxLenOfBankCmd = 6;


    /**
     * Run.
     */
    public void run() {
        /*
        System.out.println("Bank Teller is running");

        while(!input.equals("Q")){
            input = scan.nextLine();
            //System.out.println("command recived" + input);
            this.inputProcessor(input);




        }

        System.out.print("Bank Teller is terminated.");
        */
        String input = " O MM  April March 1/15/1987 7000 W MM  April March 1/15/1987 -100 PT PI";
        input = input.trim();
        input = this.formatInput(input);
        AccountDatabase db = new AccountDatabase();
        this.inputProcessor(input,db);

        /*AccountDatabase db = new AccountDatabase();


         */

    }

    /**
     * Format input string.
     *
     * @param input the input
     * @return the string
     */
    public String formatInput(String input) {
        String formattedInput = "";
        String[] tokens = input.split(" ");
        int cCount = 0;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("C") && cCount == 0) {
                tokens[i] = "CL";
                cCount++;
            } else if (tokens[i].equals("C") && cCount == 1) {

                cCount++;
            } else if (tokens[i].equals("C") && cCount == 2) {

                tokens[i] = "CL";
                cCount = 1;
            } else {
                cCount = 0;
            }
        }
        String out = "";
        for (String tok : tokens) {
            if(!tok.equals(" ")){
                out = out + " " + tok + " ";
            }

        }
        return out;

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        BankTeller b = new BankTeller();
        b.run();
    }

    /**
     * Is print cmd boolean.
     *
     * @param token the token
     * @return the boolean
     */
    public boolean isPrintCmd(String token) {
        return token.equals("P") || token.equals("PI") || token.equals("UB") ||
                token.equals("Q") || token.equals("PT");
    }

    /**
     * Is bank cmd boolean.
     *
     * @param token the token
     * @return the boolean
     */
    public boolean isBankCmd(String token) {
        return token.equals("W") || token.equals("D") || token.equals("O") ||
                token.equals("CL");
    }

    /**
     * Input processor.
     *
     * @param input the input
     * @param db    the db
     */
    public void inputProcessor(String input, AccountDatabase db) {
        StringTokenizer strTok = new StringTokenizer(input, " ");

        while (strTok.hasMoreTokens()) {
            String token = strTok.nextToken();
            //System.out.println("TOKEN FROM STRTOK: " + token);
            if (this.isPrintCmd(token)) {
                this.switchBoard(token, db);
            } else if (isBankCmd(token)) {
                if (strTok.toString().isEmpty()) {
                    break;
                }
                if (token.equals("C")) {
                    this.maxLenOfBankCmd = 5;
                    fixBankCmds(strTok, token, db);
                } else {
                    this.maxLenOfBankCmd = 6;
                    fixBankCmds(strTok, token, db);
                }

            } else {
                System.out.println("Invalid command!");
            }

        }


    }

    /**
     * Fix bank cmds.
     *
     * @param strTok the str tok
     * @param token  the token
     * @param db     the db
     */
    public void fixBankCmds(StringTokenizer strTok, String token, AccountDatabase db) {
        StringTokenizer whatsLeft = strTok;
       // System.out.println("________________________________________________");
        int counter = 1;
        String cmd = token + " ";
        boolean allowC = false;
        boolean ifTerminatedEarly = false;
        while (whatsLeft.hasMoreTokens()) {
            String tok = whatsLeft.nextToken();
           // System.out.println("Token next :" + tok);
            if (this.isPrintCmd(tok)) {
                this.switchBoard(cmd, db);
                this.switchBoard(tok,db);
                ifTerminatedEarly = true;
                break;
            } else if (this.isBankCmd(tok)) {
                this.switchBoard(cmd, db);
                cmd = tok + " ";
                counter = 1;
            } else if (counter < this.maxLenOfBankCmd) {
                cmd = cmd + tok + " ";
                counter++;
            } else {
                break;
            }
            strTok = whatsLeft;

        }
        if (!ifTerminatedEarly) {
            this.switchBoard(cmd, db);
        }
        return;
    }

    /**
     * Process college checking account.
     *
     * @param fname      the fname
     * @param lname      the lname
     * @param dob        the dob
     * @param amount     the amount
     * @param location   the location
     * @param opperation the opperation
     * @return the account
     */
    public Account processCollegeChecking(String fname, String lname, String dob,
                                          String amount, String location, String opperation) {
        try {
            int loc = Integer.parseInt(location);
            if (!(loc == 1 || loc == 0 || loc == 2)) {
                System.out.println("Invalid campus code.");
                return null;
            }
            Date date = new Date(dob);
            if (!date.isValid()) {
                System.out.println("Invalid date of birth.");
                return null;
            }
            Profile pro = new Profile(fname, lname, dob);
            try {
                double money = Double.parseDouble(amount);
                if (money <= 0) {
                    System.out.println(opperation + " " + "cannot be 0 or negative.");
                    return null;
                }
                Account returnVal = new CollegeCheckingAccount(pro, money, loc);
                return returnVal;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid amount.");
                return null;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid campus code.");
            return null;
        }
    }

    /**
     * Process money market account.
     *
     * @param fname      the fname
     * @param lname      the lname
     * @param dob        the dob
     * @param amount     the amount
     * @param opperation the opperation
     * @return the account
     */
    public Account processMoneyMarket(String fname, String lname, String dob,
                                      String amount, String opperation) {
        Date date = new Date(dob);
        if (!date.isValid()) {
            System.out.println("Invalid date of birth.");
            return null;
        }
        Profile pro = new Profile(fname, lname, dob);
        try {
            double money = Double.parseDouble(amount);
            if (money <= 0) {
                System.out.println(opperation + " " + "cannot be 0 or negative.");
                return null;
            }
            Account returnVal = new MoneyMarketAccount(pro, money);
            return returnVal;
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
            return null;
        }
    }

    /**
     * Process savings account.
     *
     * @param fname      the fname
     * @param lname      the lname
     * @param dob        the dob
     * @param loyalty    the loyalty
     * @param amount     the amount
     * @param opperation the opperation
     * @return the account
     */
    public Account processSavings(String fname, String lname, String dob,
                                  String loyalty, String amount, String opperation) {
        Date date = new Date(dob);
        if (!date.isValid()) {
            System.out.println("Invalid date of birth.");
            return null;
        }
        Profile pro = new Profile(fname, lname, dob);
        try {
            int isLoyal = Integer.parseInt(loyalty);
            if (isLoyal != 0 || isLoyal != 1) {
                System.out.println("invalid loyalty code");
            }
        } catch (NumberFormatException e) {
            System.out.println("Not a valid loyalty +code.");
            return null;
        }
        try {
            System.out.println("Money in savings: " + amount);
            double money = Double.parseDouble(amount);

            if (money <= 0) {
                System.out.println(opperation + " " + "cannot be 0 or negative.");
                return null;
            }
            Account returnVal = new Savings(pro, money, Integer.parseInt(loyalty));
            return returnVal;
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
            return null;
        }
    }

    /**
     * Process checking account.
     *
     * @param fname      the fname
     * @param lname      the lname
     * @param dob        the dob
     * @param amount     the amount
     * @param opperation the opperation
     * @return the account
     */
    public Account processChecking(String fname, String lname, String dob,
                                   String amount, String opperation) {
        Date date = new Date(dob);
        if (!date.isValid()) {
            System.out.println("Invalid date of birth.");
            return null;
        }
        Profile pro = new Profile(fname, lname, dob);
        try {
            double money = Double.parseDouble(amount);

            if (money <= 0) {
                System.out.println(opperation + " " + "cannot be 0 or negative.");
                return null;
            }
            Account returnVal = new Checking(pro, money);
            return returnVal;
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
            return null;
        }
    }

    /**
     * Is opened.
     *
     * @param open the open
     * @param db   the db
     */
    public void isOpened(Account open, AccountDatabase db) {
        if (open != null) {
            if (db.publicFind(open) != null) {
                Account found = db.publicFind(open);
                if(open.getType().equals("Money Market Savings")){
                    if(open.getBalance() < 2500.00){
                        System.out.println("Minimum of $2500 to open a MoneyMarket account.");
                        return;
                    }
                }
                if (db.open(open)) {
                    System.out.println("Account reopened.");
                    return;
                }


            }
            if(open.getType().equals("Money Market Savings")){
                if(open.getBalance() < 2500.00){
                    System.out.println("Minimum of $2500 to open a MoneyMarket account.");
                    return;
                }
            }
            if(db.open(open)){
                System.out.println("Account opened.");
                return;
            }

            System.out.println(open.getHolder().toString() + " " + "same " +
                    "account(type) is in the database.");
        }
    }

    /**
     * Deposit message.
     *
     * @param deposit the deposit
     * @param db      the db
     */
    public void depositMessage(Account deposit, AccountDatabase db){
        if(deposit != null){
            if(db.publicFind(deposit) == null){
                System.out.println(deposit.getHolder().toString() + " " +
                        deposit.getType() + " is not in the database.");
                return;
            }
            if(db.publicFind(deposit).isClosed()){
                System.out.println("Account is closed.");
                return;
            }
            db.deposit(deposit);
            System.out.println("Deposit - balance updated.");
        }




    }

    /**
     * Process open.
     *
     * @param cmd the cmd
     * @param db  the db
     */
    public void processOpen(String cmd, AccountDatabase db) {
        try {
            String[] options = cmd.split(" ");
            String acctType = options[1];
            String acctFname = options[2];
            String acctLastName = options[3];
            String acctDob = options[4];
            String deposit = options[5];
            if (acctType.equals("CC")) {
                Account open = this.processCollegeChecking(acctFname, acctLastName, acctDob, deposit,
                        options[6], "Initial Deposit");
                this.isOpened(open, db);
            } else if (acctType.equals("MM")) {
                Account open = this.processMoneyMarket(acctFname, acctLastName, acctDob, deposit,
                        "Initial Deposit");
                this.isOpened(open, db);
            } else if (acctType.equals("C")) {
                Account open = this.processChecking(acctFname, acctLastName, acctDob, deposit,
                        "Initial Deposit");
                this.isOpened(open, db);
            } else if (acctType.equals("S")) {
                Account open = this.processSavings(acctFname, acctLastName, acctDob, options[6], deposit,
                        "Initial Deposit");
                this.isOpened(open, db);
            } else {
                System.out.println("Invalid command.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for opening an account.");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }
    }

    /**
     * Close message.
     *
     * @param close the close
     * @param db    the db
     */
    public void  closeMessage (Account close, AccountDatabase db){
        if(close != null){
            if(db.publicFind(close)!= null){
                Account found = db.publicFind(close);
                if(found.isClosed()){
                    System.out.println("Account is closed already.");
                    return;
                }
                if(db.close(close)){
                    System.out.println("Account is closed.");
                }
            }else{
                System.out.println("Account does not exist.");
            }

            }


    }

    /**
     * Process close.
     *
     * @param cmd the cmd
     * @param db  the db
     */
    public void processClose(String cmd, AccountDatabase db) {
        try {
            String[] options = cmd.split(" ");
            String acctType = options[1];
            String acctFname = options[2];
            String acctLastName = options[3];
            String acctDob = options[4];
            Account close = null;
            if (acctType.equals("CC")) {
                close = this.processCollegeChecking(acctFname, acctLastName, acctDob,
                        "1", "1", "");
                this.closeMessage(close,db);
            } else if (acctType.equals("MM")) {
                close = this.processMoneyMarket(acctFname, acctLastName, acctDob,"1", "");
                this.closeMessage(close,db);
            } else if (acctType.equals("C")) {
                close = this.processChecking(acctFname, acctLastName, acctDob, "1", "");
                this.closeMessage(close,db);
            } else if (acctType.equals("S")) {
                close = this.processSavings(acctFname, acctLastName, acctDob,
                        "0", "1", "");
                this.closeMessage(close,db);
            } else {
                System.out.println("Invalid command.");
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for closing an account.");
            return;
        }
    }

    /**
     * Process deposit.
     *
     * @param cmd the cmd
     * @param db  the db
     */
    public void processDeposit(String cmd, AccountDatabase db) {
        try {
            String[] options = cmd.split(" ");
            String acctType = options[1];
            String acctFname = options[2];
            String acctLastName = options[3];
            String acctDob = options[4];
            String deposit = options[5];
            if (acctType.equals("CC")) {
                Account depo = this.processCollegeChecking(acctFname, acctLastName, acctDob, deposit,
                        "1", "Deposit - amount");
                this.depositMessage(depo, db);
            } else if (acctType.equals("MM")) {
                Account depo = this.processMoneyMarket(acctFname, acctLastName, acctDob, deposit,
                        "Deposit - amount");
                this.depositMessage(depo, db);
            } else if (acctType.equals("C")) {
                Account depo = this.processChecking(acctFname, acctLastName, acctDob, deposit,
                        "Deposit - amount");
                this.depositMessage(depo, db);
            } else if (acctType.equals("S")) {
                Account depo = this.processSavings(acctFname, acctLastName, acctDob, options[6], deposit,
                        "Deposit - amount");
                this.depositMessage(depo, db);
            } else {
                System.out.println("Invalid command.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for depositing into account.");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }

    }

    /**
     * Withdraw message.
     *
     * @param withdraw the withdraw
     * @param db       the db
     */
    public void withdrawMessage(Account withdraw,AccountDatabase db){
        if(withdraw != null) {
            if (db.publicFind(withdraw) == null) {
                System.out.println(withdraw.getHolder().toString() + " " +
                        withdraw.getType() + " is not in the database.");
                return;
            }

            if (db.publicFind(withdraw).isClosed()) {
                System.out.println("Account is closed.");
                return;
            }
            double amount = withdraw.getBalance();
            if (db.publicFind(withdraw).isSufficentFunds(amount)) {
                System.out.println("Withdraw - insufficient fund.");
                return;
            }
            db.withdraw(withdraw);
            System.out.println("Withdraw - balance updated.");
        }

    }

    /**
     * Process withdraw.
     *
     * @param cmd the cmd
     * @param db  the db
     */
    public void processWithdraw(String cmd, AccountDatabase db) {
        try {
            String[] options = cmd.split(" ");
            String acctType = options[1];
            String acctFname = options[2];
            String acctLastName = options[3];
            String acctDob = options[4];
            String deposit = options[5];
            if (acctType.equals("CC")) {
                Account depo = this.processCollegeChecking(acctFname, acctLastName, acctDob, deposit,
                        "1", "Withdraw - amount");
                this.withdrawMessage(depo, db);
            } else if (acctType.equals("MM")) {
                Account depo = this.processMoneyMarket(acctFname, acctLastName, acctDob, deposit,
                        "Withdraw - amount");
                this.withdrawMessage(depo, db);
            } else if (acctType.equals("C")) {
                Account depo = this.processChecking(acctFname, acctLastName, acctDob, deposit,
                        "Withdraw - amount");
                this.withdrawMessage(depo, db);
            } else if (acctType.equals("S")) {
                Account depo = this.processSavings(acctFname, acctLastName, acctDob, options[6], deposit,
                        "Withdraw - amount");
                this.withdrawMessage(depo, db);
            } else {
                System.out.println("Invalid command.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data for withdrawing into account.");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Not a valid amount.");
        }



    }

    /**
     * Switch board.
     *
     * @param cmd the cmd
     * @param db  the db
     */
    public void switchBoard(String cmd, AccountDatabase db) {
        System.out.println("CMD given: "+cmd);
        if (cmd.startsWith("O")) {
            this.processOpen(cmd, db);
        } else if (cmd.startsWith("W")) {
            this.processWithdraw(cmd, db);
        } else if (cmd.startsWith("D")) {
            this.processDeposit(cmd, db);
        } else if (cmd.startsWith("CL")) {
            this.processClose(cmd, db);
        }else if(this.isPrintCmd(cmd)){
            this.processPrint(cmd,db);
        }
    }

    /**
     * Process print.
     *
     * @param cmd the cmd
     * @param db  the db
     */
    public void processPrint(String cmd, AccountDatabase db){
        if(db.isEmpty()){
            System.out.println("Account Database is empty!");
        }else{
            if(cmd.equals("P")){
                System.out.println("*list of accounts in the database*");
                db.print();
                System.out.println("*end of list*");
                return;
            }
            if(cmd.equals("PI")){
                db.printFeeAndInterest();
                return;
            }
            if(cmd.equals("PT")){
                db.printByAccountType();
                return;
            }
            if(cmd.equals("UB")){
                System.out.println("*list of accounts with updated balance");
                db.updateAccounts();
                db.print();
                System.out.println("*end of list.");
            }
        }
    }
}
