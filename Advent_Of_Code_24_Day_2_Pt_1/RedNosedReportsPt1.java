import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RedNosedReportsPt1 {
    public static ArrayList<ArrayList<Integer>> buildList(String pathName) {
        ArrayList<ArrayList<Integer>> nestedList = new ArrayList<ArrayList<Integer>>();
        
        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrayOfStrings = data.split(" ", 0);
                ArrayList<Integer> interiorList = new ArrayList<Integer>();

                for (int i = 0; i < arrayOfStrings.length; ++i) {
                    interiorList.add(Integer.parseInt(arrayOfStrings[i]));
                }  
                nestedList.add(interiorList);
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return nestedList;
    }

    public static Boolean isSafe(ArrayList<Integer> interiorList) {
        Boolean safety = false;
        int increasing = 0;
        int decreasing = 0;

        for (int i = 1; i < interiorList.size(); ++i) {
            if (i == 1 || increasing > 0) {
                if (interiorList.get(i - 1) - interiorList.get(i) > 0 &&
                    interiorList.get(i - 1) - interiorList.get(i) < 4) {
                    increasing += 1;
                }
                else {
                    increasing = 0;
                    if (i > 1) {
                        break;
                    }
                }
            }

            if (i == 1 || decreasing > 0) {
                if (interiorList.get(i) - interiorList.get(i - 1) > 0 &&
                    interiorList.get(i) - interiorList.get(i - 1) < 4) {
                    decreasing += 1;
                }
                else {
                    decreasing = 0;
                    if (i > 1) {
                        break;
                    }
                }
            }
        }

        if (increasing == interiorList.size() - 1 || decreasing == interiorList.size() - 1) {
            safety = true;
        }

        return safety;
    }

    public static int safeCount(ArrayList<ArrayList<Integer>> nestedList) {
        int count = 0;

        for (int i = 0; i < nestedList.size(); ++i) {
            if (isSafe(nestedList.get(i))) {
                count += 1;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> nestedList = buildList("input.txt");
        
        System.out.println(safeCount(nestedList));
    }
}
