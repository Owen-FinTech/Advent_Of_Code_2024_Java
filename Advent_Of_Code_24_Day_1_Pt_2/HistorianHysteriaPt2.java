import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HistorianHysteriaPt2 {

    public static ArrayList<Long> buildList(String pathName, int firstOrSecond) {
        ArrayList<Long> unorderedList = new ArrayList<Long>();
        
        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrayOfStringPairs = data.split("   ", 0);
                unorderedList.add(Long.parseLong(arrayOfStringPairs[firstOrSecond]));
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return unorderedList;
    }

    public static Long similarityScore(ArrayList<Long> unorderedList1, ArrayList<Long> unorderedList2) {
        Long similarity = 0L;

        for (int i = 0; i < unorderedList1.size(); ++i) {
            Long count = 0L;

            for (int j = 0; j < unorderedList2.size(); ++j) {
                if (unorderedList1.get(i).equals(unorderedList2.get(j))) {
                    count += 1L;
                }
            }
            similarity += (count * unorderedList1.get(i));
        }

        return similarity;
    }

    public static void main(String[] args) {
        ArrayList<Long> unorderedList1 = buildList("input.txt", 0);
        ArrayList<Long> unorderedList2 = buildList("input.txt", 1);

        System.out.println(similarityScore(unorderedList1, unorderedList2));
    }
}
