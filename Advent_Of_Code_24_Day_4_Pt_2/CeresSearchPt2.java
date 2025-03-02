import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CeresSearchPt2 {

    public static ArrayList<String> getPuzzle(String pathName) {
        ArrayList<String> puzzle = new ArrayList<String>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                puzzle.add(data);
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return puzzle;
    }

    public static int countX(ArrayList<String> puzzle) {
        int count = 0;
        
        for (int i = 2; i < puzzle.get(0).length(); ++i) {
            for (int j = 2; j < puzzle.size(); ++j) {
                String sub1 = puzzle.get(j).substring(i, i + 1) + 
                            puzzle.get(j - 1).substring(i - 1, i) +
                            puzzle.get(j - 2).substring(i - 2, i - 1);
                String sub2 = puzzle.get(j).substring(i - 2, i - 1) + 
                            puzzle.get(j - 1).substring(i - 1, i) +
                            puzzle.get(j - 2).substring(i, i + 1);
                if ((sub1.equals("MAS") || sub1.equals("SAM")) &&
                    (sub2.equals("MAS") || sub2.equals("SAM"))) {
                    count += 1;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        ArrayList<String> puzzle = getPuzzle("input.txt");
        int totalCount = countX(puzzle);
        System.out.println(totalCount);
    }
}
