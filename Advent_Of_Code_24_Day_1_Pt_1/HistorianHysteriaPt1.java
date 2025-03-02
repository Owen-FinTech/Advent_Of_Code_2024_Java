import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HistorianHysteriaPt1 {

    public static ArrayList<Integer> buildList(String pathName, int firstOrSecond) {
        ArrayList<Integer> unorderedList = new ArrayList<Integer>();
        
        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrayOfStringPairs = data.split("   ", 0);
                unorderedList.add(Integer.parseInt(arrayOfStringPairs[firstOrSecond]));
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return unorderedList;
    }

    public static int findDistance(Object[] orderedList1, Object[] orderedList2) {
        int distance = 0;

        for (int i = 0; i < orderedList1.length; ++i) {
            distance += Math.abs(((Integer) orderedList1[i]) - ((Integer) orderedList2[i]));
        }

        return distance;
    }

    public static void main(String[] args) {
        ArrayList<Integer> unorderedList1 = buildList("input.txt", 0);
        ArrayList<Integer> unorderedList2 = buildList("input.txt", 1);
        Object[] orderedList1 = unorderedList1.toArray();
        Object[] orderedList2 = unorderedList2.toArray();
        Arrays.sort(orderedList1);
        Arrays.sort(orderedList2);

        System.out.println(findDistance(orderedList1, orderedList2));
    }
}
