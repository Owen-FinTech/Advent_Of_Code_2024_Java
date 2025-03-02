import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PlutonianPebblesPt2 {

    public static HashMap<Long, Long> getStones(String pathName) {
        HashMap<Long, Long> stones = new HashMap<Long, Long>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] splitStr = data.split(" ");

                for (int i = 0; i < splitStr.length; ++i) {
                    if (stones.containsKey(Long.parseLong(splitStr[i]))) {
                        stones.put(Long.parseLong(splitStr[i]), stones.get(Long.parseLong(splitStr[i])) + 1L);
                    }
                    else {
                        stones.put(Long.parseLong(splitStr[i]), 1L);
                    }
                }
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return stones;
    }

    public static HashMap<Long, Long> blink(HashMap<Long, Long> stones) {
        HashMap<Long, Long> newStones = new HashMap<Long, Long>();

        stones.forEach((k, v) -> {
            String longAsStr = Long.toString(k);

            if (k == 0L) {
                if (newStones.containsKey(1L)) {
                    newStones.put(1L, newStones.get(1L) + v);
                }
                else {
                    newStones.put(1L, v);
                }
            }
            else if (longAsStr.length() % 2 == 0) {

                if (newStones.containsKey(Long.parseLong(longAsStr.substring(longAsStr.length() / 2)))) {
                    newStones.put(Long.parseLong(longAsStr.substring(longAsStr.length() / 2)), newStones.get(Long.parseLong(longAsStr.substring(longAsStr.length() / 2))) + v);
                }
                else {
                    newStones.put(Long.parseLong(longAsStr.substring(longAsStr.length() / 2)), v);
                }

                if (newStones.containsKey(Long.parseLong(longAsStr.substring(0, longAsStr.length() / 2)))) {
                    newStones.put(Long.parseLong(longAsStr.substring(0, longAsStr.length() / 2)), newStones.get(Long.parseLong(longAsStr.substring(0, longAsStr.length() / 2))) + v);
                }
                else {
                    newStones.put(Long.parseLong(longAsStr.substring(0, longAsStr.length() / 2)), v);
                }
            }
            else {
                if (newStones.containsKey(k * 2024L)) {
                    newStones.put(k * 2024L, newStones.get(k * 2024L) + v);
                }
                else {
                    newStones.put(k * 2024L, v);
                }
            }
        });
        return newStones;
    }



    public static void main(String[] args) {
        HashMap<Long, Long> stones = getStones("input.txt");
        
        for (int i = 0; i < 75; ++i) {
            stones = blink(stones);
        }

        Long answer = 0L;
        ArrayList<Long> stonesVals = new ArrayList<Long>(stones.values());

        for (int i = 0; i < stonesVals.size(); ++i) {
            answer += stonesVals.get(i);
        }

        System.out.println(answer);
    }
    
}
