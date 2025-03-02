import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BridgeRepairPt1 {

    public static ArrayList<ArrayList<Long>> getEquations(String pathName) {
        ArrayList<ArrayList<Long>> equations = new ArrayList<ArrayList<Long>>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<Long> subList = new ArrayList<Long>();
                String currDigits = "";

                for (int i = 0; i < data.length(); ++i) {
                    if (data.substring(i, i + 1).equals(":") ||
                        data.substring(i, i + 1).equals(" ")) {
                        
                        if (!currDigits.equals("")) {
                            subList.add(Long.parseLong(currDigits));
                        }    
                        currDigits = "";
                    }
                    else if (i == data.length() - 1) {
                        currDigits += data.substring(i, i + 1);
                        subList.add(Long.parseLong(currDigits));
                    }
                    else {
                        currDigits += data.substring(i, i + 1);
                    }
                }
                equations.add(subList);
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return equations;
    }

    public static boolean evaluatesTrue(ArrayList<Long> equation) {
        boolean canEquate = false;
        ArrayList<ArrayList<Long>> permutations = new ArrayList<ArrayList<Long>>();
        
        for (int i = 2; i < equation.size(); ++i) {
            ArrayList<Long> permutation = new ArrayList<Long>();

            if (i == 2) {
                permutation.add(equation.get(i) + equation.get(i - 1));
                permutation.add(equation.get(i) * equation.get(i - 1));
                permutations.add(permutation);
            }
            else {
                for (int j = 0; j < permutations.get(permutations.size() - 1).size(); ++j) {
                    permutation.add(equation.get(i) + permutations.get(permutations.size() - 1).get(j));
                    permutation.add(equation.get(i) * permutations.get(permutations.size() - 1).get(j));
                }
                permutations.add(permutation);
            }
        }

        for (int i = 0; i < permutations.get(permutations.size() - 1).size(); ++i) {
            if (equation.get(0).equals(permutations.get(permutations.size() - 1).get(i))) {
                canEquate = true;
                break;
            }
        }
        return canEquate;
    }

    public static Long equateCount(ArrayList<ArrayList<Long>> equations) {
        Long count = 0L;

        for (int i = 0; i < equations.size(); ++i) {
            if (evaluatesTrue(equations.get(i))) {
                count += equations.get(i).get(0);
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        ArrayList<ArrayList<Long>> equations = getEquations("input.txt");

        System.out.println(equateCount(equations));
    }
    
}
