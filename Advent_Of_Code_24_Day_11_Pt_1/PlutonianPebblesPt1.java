import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PlutonianPebblesPt1 {

    public static ArrayList<Long> getStones(String pathName) {
        ArrayList<Long> stones = new ArrayList<Long>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] splitStr = data.split(" ");

                for (int i = 0; i < splitStr.length; ++i) {
                    stones.add(Long.parseLong(splitStr[i]));
                }
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return stones;
    }

    public static void blinks(ArrayList<Long> stones) {

        for (int i = 0; i < 25; ++i) {
            int count = 0;

            while (count < stones.size()) {
                String longAsStr = Long.toString(stones.get(count));
                
                if (stones.get(count) == 0L) {
                    stones.set(count, 1L);
                }
                else if (longAsStr.length() % 2 == 0) {
                    stones.set(count, Long.parseLong(longAsStr.substring(longAsStr.length() / 2)));
                    stones.add(count, Long.parseLong(longAsStr.substring(0, longAsStr.length() / 2)));
                    count += 1;
                }
                else {
                    stones.set(count, stones.get(count) * 2024L);
                }
                count += 1;
            }
        }
    }



    public static void main(String[] args) {
        ArrayList<Long> stones = getStones("input.txt");
        blinks(stones);
        System.out.println(stones.size());
    }
    
}
