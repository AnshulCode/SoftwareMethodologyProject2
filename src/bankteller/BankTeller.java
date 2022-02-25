package bankteller;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Alexander Reyes, Anshul P
 *
 */
public class BankTeller {

    public Scanner scan = new Scanner(System.in);
    public String input = "";
    public int maxLenOfBankCmd = 7;



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
        String input = "o\n" +
                "p\n" +
                "q\n" +
                "d\n" +
                "w\n" +
                "i\n" +
                "pt\n" +
                "pi\n" +
                "ub\n" +
                "a\n" +
                "A\n" +
                "c\n" +
                "b\n" +
                "B\n" +
                "P " +
                " PT" +
                " PI\n" +
                "UB\n O  CC   April March 1/15/1987 300 0 O  CC   April March 1/15/1987 300 0 O  CC   April March 1/15/1987 300 0  O  CC   Kate Lindsey 8/31/2001 450 2 D cb";
        input = input.replaceAll("\n","  ");
        this.inputProcessor(input);
    }
    public static void main(String[] args) {
        BankTeller b = new BankTeller();
        b.run();
    }
    public boolean isPrintCmd(String token) {
        return token.equals("P") || token.equals("PI") || token.equals("UB") ||
                token.equals("Q") ||token.equals("PT");
    }
    public boolean isBankCmd(String token) {
        return token.equals("W") || token.equals("D") || token.equals("O") ||
                token.equals("C");
    }
    public void inputProcessor(String input){
        StringTokenizer strTok = new StringTokenizer(input," ");

        while(strTok.hasMoreTokens()){
            String token = strTok.nextToken();
            if(this.isPrintCmd(token)){
                System.out.println(token);
            }else if(isBankCmd(token)){
                if(strTok.toString().isEmpty()){
                    break;
                }
                strTok = fixBankCmds(strTok,token);
            } else {
                System.out.println("Invalid command!");
            }

        }
    }

    public StringTokenizer fixBankCmds(StringTokenizer strTok,String token){
        StringTokenizer whatsLeft = strTok;
        int counter =0;
        String cmd = token + " ";
        boolean ifTerminatedEarly = false;
        while (whatsLeft.hasMoreTokens()) {
            String tok = whatsLeft.nextToken();
            if(this.isPrintCmd(tok)){
                System.out.println(cmd);
                ifTerminatedEarly = true;
                break;
            }
            if(this.isBankCmd(tok) && !tok.equals("C")){
                System.out.println(cmd);
                cmd = tok + " ";
                counter = 1;
            }else if(counter < this.maxLenOfBankCmd){
                cmd = cmd+tok+ " ";
                counter++;
            }else{
                break;
            }
        }
        if(!ifTerminatedEarly){
            System.out.println(cmd);
        }
        return whatsLeft;
    }

    public void processOpen(String cmd, AccountDatabase db){

    }
    public void processClose(String cmd,AccountDatabase db){

    }
    public void processDeposit(String cmd,AccountDatabase db){

    }
    public void processWithdraw(String cmd,AccountDatabase db){

    }
    public void switchBoard(String cmd, AccountDatabase db) {
        if(cmd.startsWith("O")){
            this.processOpen(cmd,db);
        }
        if(cmd.startsWith("W")){
            this.processWithdraw(cmd,db);
        }
        if (cmd.startsWith("D")) {
            this.processDeposit(cmd,db);
        }
        if (cmd.startsWith("C")) {
            this.processClose(cmd,db);
        }

    }
}
