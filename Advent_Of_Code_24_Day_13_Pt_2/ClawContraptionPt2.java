import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClawContraptionPt2 {

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

    public static Long calculateTokens(ArrayList<ArrayList<Integer>> machines) {
        Long tokens = 0L;

        for (int i = 0; i < machines.size(); i += 3) {
            Long px = machines.get(i + 2).get(0) + 10000000000000L;
            Long py = machines.get(i + 2).get(1) + 10000000000000L;
            Long ca1 = px * machines.get(i + 1).get(1) - py * machines.get(i + 1).get(0); 
            Long ca2 = Long.valueOf(machines.get(i).get(0) * machines.get(i + 1).get(1) - machines.get(i).get(1) * machines.get(i + 1).get(0));
            Long cb1 = -1L;
            Long cb2 = -1L;
            if (ca1 % ca2 == 0) {
                cb1 = Long.valueOf(px - (machines.get(i).get(0) * (ca1 / ca2)));
                cb2 = Long.valueOf(machines.get(i + 1).get(0));
            }
            
            if (ca1 % ca2 == 0 && cb1 != -1L && cb2 != -1L && cb1 % cb2 == 0) {
                tokens += Long.valueOf(ca1 / ca2) * 3L + Long.valueOf(cb1 / cb2);
            } 
        }

        return tokens;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> machines = getMachines("input.txt");
        System.out.println(calculateTokens(machines));
    }
    
}
