import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CeresSearchPt1 {

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

    public static int countHorizontal(ArrayList<String> puzzle) {
        int count = 0;

        for (int i = 0; i < puzzle.size(); ++i) {
            for (int j = 3; j < puzzle.get(i).length(); ++j) {
                if (puzzle.get(i).substring(j - 3, j + 1).equals("XMAS") ||
                    puzzle.get(i).substring(j - 3, j + 1).equals("SAMX")) {
                    count += 1;
                }
            }
        }

        return count;
    }

    public static int countVertical(ArrayList<String> puzzle) {
        int count = 0;

        for (int i = 0; i < puzzle.get(0).length(); ++i) {
            for (int j = 3; j < puzzle.size(); ++j) {
                String sub = puzzle.get(j).substring(i, i + 1) + 
                            puzzle.get(j - 1).substring(i, i + 1) +
                            puzzle.get(j - 2).substring(i, i + 1) + 
                            puzzle.get(j - 3).substring(i, i + 1);
                if (sub.equals("XMAS") || sub.equals("SAMX")) {
                    count += 1;
                }
            }
        }

        return count;
    }

    public static int countDownRight(ArrayList<String> puzzle) {
        int count = 0;
        
        for (int i = 3; i < puzzle.get(0).length(); ++i) {
            for (int j = 3; j < puzzle.size(); ++j) {
                String sub = puzzle.get(j).substring(i, i + 1) + 
                            puzzle.get(j - 1).substring(i - 1, i) +
                            puzzle.get(j - 2).substring(i - 2, i - 1) + 
                            puzzle.get(j - 3).substring(i - 3, i -2);
                if (sub.equals("XMAS") || sub.equals("SAMX")) {
                    count += 1;
                }
            }
        }

        return count;
    }

    public static int countUpRight(ArrayList<String> puzzle) {
        int count = 0;
        
        for (int i = 0; i < puzzle.get(0).length() - 3; ++i) {
            for (int j = 3; j < puzzle.size(); ++j) {
                String sub = puzzle.get(j).substring(i, i + 1) + 
                            puzzle.get(j - 1).substring(i + 1, i + 2) +
                            puzzle.get(j - 2).substring(i + 2, i + 3) + 
                            puzzle.get(j - 3).substring(i + 3, i + 4);
                if (sub.equals("XMAS") || sub.equals("SAMX")) {
                    count += 1;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        ArrayList<String> puzzle = getPuzzle("input.txt");
        int totalCount = countHorizontal(puzzle) + countVertical(puzzle) + 
                        countDownRight(puzzle) + countUpRight(puzzle);
        System.out.println(totalCount);
    }
}
