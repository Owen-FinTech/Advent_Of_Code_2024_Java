import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClawContraptionPt1 {

    public static ArrayList<ArrayList<Integer>> getMachines(String pathName) {
        ArrayList<ArrayList<Integer>> machines = new ArrayList<ArrayList<Integer>>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<Integer> machine = new ArrayList<Integer>();

                if (!data.isEmpty()) {
                    String currNum = "";

                    for (int i = 0; i < data.length(); ++i) {
                        if (data.charAt(i) == ',') {
                            machine.add(Integer.parseInt(currNum));
                            currNum = "";
                        }
                        else if (Character.isDigit(data.charAt(i))) {
                            currNum += data.substring(i, i + 1); 
                        }

                        if (i == data.length() - 1) {
                            machine.add(Integer.parseInt(currNum));
                        }
                    }
                    machines.add(machine);
                }
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return machines;
    }

    public static int calculateTokens(ArrayList<ArrayList<Integer>> machines) {
        int tokens = 0;

        for (int i = 0; i < machines.size(); i += 3) {
            int currMinTokens = 400;

            for (int j = 0; j <= 100; ++j) {
                for (int k = 0; k <= 100; ++k) {
                    if ((j * machines.get(i).get(0)) + (k * machines.get(i + 1).get(0)) == 
                        machines.get(i + 2).get(0) &&
                        (j * machines.get(i).get(1)) + (k * machines.get(i + 1).get(1)) == 
                        machines.get(i + 2).get(1)) {
                        if ((j * 3) + k < currMinTokens) {
                            currMinTokens = (j * 3) + k;
                        }
                    }
                }
            }

            if (currMinTokens < 400) {
                tokens += currMinTokens;
            }

        }

        return tokens;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> machines = getMachines("input.txt");
        System.out.println(calculateTokens(machines));
    }
    
}
