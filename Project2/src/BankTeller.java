import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author Alexander Reyes, Anshul P
 *
 */
public class BankTeller {

	Scanner scan = new Scanner(System.in);
	String input = "";
	
	public void run() {
        while(!input.equals("Q")){
            input = scan.nextLine();
            //System.out.println("command recived" + input);
            if(input.equals("Q")){
                break;
            }

            System.out.println(commandCheck(input));

        }
        System.out.print("Bank Teller is terminated.");
	}
	
	public String commandCheck(String commandGiven) {
        if(commandGiven.isEmpty()){
            return "Invalid Command!\n";
        }
        
        String[] array = this.splitString(commandGiven);
        
        if(!(array[0].equals("Q") ||array[0].equals("O") || array[0].equals("D") || array[0].equals("C")  || array[0].equals("W")  || array[0].equals("P") || array[0].equals("PT") 
        			|| array[0].equals("PI") || array[0].equals("UB"))) {
            return "Invalid Command!\n";
        }

        if ((array[0].equals("Q")||array[0].equals("P") ||array[0].equals("PT")|| array[0].equals("PI")|| array[0].equals("UB") ) && array.length >1) {
            return "Invalid Command!\n";
        }
        
        if(array[0].equals("O") ){
            if(array.length !=6){
                return "Invalid Command!\n";
            }

            String acctType = array[1];
            String fname = array[2];
            String lname = array[3];
            String DOB = array[4];
            String money = array[5];
            Date dob = new Date(DOB);

            if(!dob.isValid()){
                return "Date of Birth Invalid";
            }
            
            Date curr = new Date();
            
            if(dob.compareTo(curr) >= 0){
                return "Date Birth Invalid -> it is a future date";
            }
        }
        
        if(array[0].equals("C")){
            if(array.length !=5){
                return "Invalid Command!\n";
            }
            String acctType = array[1];
            String fname = array[2];
            String lname = array[3];
            String DOB = array[4];
            Date dob = new Date(DOB);
            
            if(!dob.isValid()){
                return "Invalid DOB";
            }

            Date curr = new Date();
            
            if(dob.compareTo(curr) >= 0){
                return "Date Birth Invalid -> it is a future date";
            }
        }
        
        if(array[0].equals("D")){
        	if(array.length !=6){
        		return "Invalid Command!\n";
            }
            String acctType = array[1];
            String fname = array[2];
            String lname = array[3];
            String DOB = array[4];
            String money = array[5];
            Date dob = new Date(DOB);
            
            if(!dob.isValid()){
                return "Invalid DOB";
            }

            Date curr = new Date();
            
            if(dob.compareTo(curr) >= 0){
                return "Date Birth Invalid -> it is a future date";
            }
        }
        
        if(array[0].equals("W")){
        	if(array.length !=6){
        		return "Invalid Command!\n";
            }
            String acctType = array[1];
            String fname = array[2];
            String lname = array[3];
            String DOB = array[4];
            String money = array[5];
            Date dob = new Date(DOB);
            
            if(!dob.isValid()){
                return "Invalid DOB";
            }

            Date curr = new Date();
            
            if(dob.compareTo(curr) >= 0){
                return "Date Birth Invalid -> it is a future date";
            }
        }
        
        if(array[0].equals("P")){
            s.print();
            return "";
        }
        if(array[0].equals("PZ")){
            s.printByZip();
            return "";
        }
        if(array[0].equals("PP")){
            s.printByPatient();
            return "";
        }


        return "Valid command";
    }
	
	/**
    * This method takes the input given from the user and splits it to its parts.
    * This will be done using the string tokenizer library.
    * @param inputGiven This is the input given from the user in the console
    * @return an array of strings that is the input from the console split into singular Strings
    */
    private String[] splitString(String inputGiven) {
        StringTokenizer st = new StringTokenizer(inputGiven);
        String[] details = new String[st.countTokens()];
        int arrPos = 0;
        while (arrPos < details.length) {
            details[arrPos] = st.nextToken();
            arrPos++;
        }
        return details;

    }
}
