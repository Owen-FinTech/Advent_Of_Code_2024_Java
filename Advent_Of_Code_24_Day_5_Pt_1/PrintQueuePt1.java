import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PrintQueuePt1 {

    public static ArrayList<ArrayList<Integer>> getPairs(String pathName) {
        ArrayList<ArrayList<Integer>> pairs = new ArrayList<ArrayList<Integer>>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                
                if (data.equals("")) {
                    break;
                }
                else {
                    ArrayList<Integer> subList = new ArrayList<Integer>();
                    Integer firstDigits = Integer.parseInt(data.substring(0, 2));
                    subList.add(firstDigits);
                    Integer secondDigits = Integer.parseInt(data.substring(3, 5));
                    subList.add(secondDigits);
                    pairs.add(subList);
                }
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return pairs;
    }

    public static ArrayList<ArrayList<Integer>> getLists(String pathName) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            boolean separatorReached = false;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                
                if (data.equals("")) {
                    separatorReached = true;
                    continue;
                }

                if (separatorReached) {
                    ArrayList<Integer> subList = new ArrayList<Integer>();
                    String[] splitString = data.split(",");

                    for (int i = 0; i < splitString.length; ++i) {
                        subList.add(Integer.parseInt(splitString[i]));
                    }
                    lists.add(subList);
                }

            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return lists;
    }

    public static boolean isOrdered(ArrayList<ArrayList<Integer>> pairs, ArrayList<Integer> subList) {
        boolean ordered = true;
        
        for (int i = 0; i < pairs.size(); ++i) {
            int firstIndex = -1;
            int secondIndex = -1;

            if (subList.contains(pairs.get(i).get(0)) && subList.contains(pairs.get(i).get(1))) {
                for (int j = 0; j < subList.size(); ++j) {
                    if (subList.get(j).equals(pairs.get(i).get(0))) {
                        firstIndex = j;
                    }
                    else if (subList.get(j).equals(pairs.get(i).get(1))) {
                        secondIndex = j;
                    } 
                }

                if (firstIndex > secondIndex) {
                    ordered = false;
                    break;
                }
            }
        }
        
        return ordered;
    }

    public static int middleSum(ArrayList<ArrayList<Integer>> pairs, ArrayList<ArrayList<Integer>> lists) {
        int sum = 0;

        for (int i = 0; i < lists.size(); ++i) {
            if (isOrdered(pairs, lists.get(i))) {
                sum += lists.get(i).get(lists.get(i).size() / 2);
            }
        }

        return sum;
    }
    
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> pairs = getPairs("input.txt");
        ArrayList<ArrayList<Integer>> lists = getLists("input.txt");

        System.out.println(middleSum(pairs, lists));
    }
    
}
