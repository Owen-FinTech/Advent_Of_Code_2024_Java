import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MullItOverPt1 {

    public static String getString(String pathName) {
        String inputString = "";

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputString += data;
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return inputString;
    }

    public static int processInputString(String inputString) {
        int result = 0;
        String firstNum = "";
        String secondNum = "";
        boolean mulDetected = false;
        boolean firstNumFull = false;
        
        for (int i = 3; i < inputString.length(); ++i) {
            if (mulDetected == false && inputString.substring(i - 3, i + 1).equals("mul(")) {
                mulDetected = true;
            }
            else if (mulDetected == true && Character.isDigit(inputString.charAt(i)) &&
                firstNumFull == false) {
                firstNum += inputString.substring(i, i + 1);
            }
            else if (mulDetected == true && Character.isDigit(inputString.charAt(i)) &&
                firstNumFull == true) {
                secondNum += inputString.substring(i, i + 1);
            }
            else if (mulDetected == true && inputString.substring(i, i + 1).equals(",") &&
                firstNum.length() > 0) {
                firstNumFull = true;
            }
            else if (mulDetected == true && inputString.substring(i, i + 1).equals(")") &&
                firstNumFull == true && secondNum.length() > 0) {
                result += Integer.parseInt(firstNum) * Integer.parseInt(secondNum);
                firstNum = "";
                secondNum = "";
                mulDetected = false;
                firstNumFull = false;
            }
            else {
                firstNum = "";
                secondNum = "";
                mulDetected = false;
                firstNumFull = false;
            }   
        }
        return result;
    }

    public static void main(String[] args) {
        String inputString = getString("input.txt");
        System.out.println(processInputString(inputString));
    }
    
}
