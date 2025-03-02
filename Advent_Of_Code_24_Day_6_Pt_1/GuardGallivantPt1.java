import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GuardGallivantPt1 {

    public static ArrayList<ArrayList<Character>> getMap(String pathName) {
        ArrayList<ArrayList<Character>> map = new ArrayList<ArrayList<Character>>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<Character> subMap = new ArrayList<Character>();
                
                for (int i = 0; i < data.length(); ++i) {
                    subMap.add(data.charAt(i));
                }
                map.add(subMap);
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return map;
    }

    public static void createPath(ArrayList<ArrayList<Character>> map) {
        int currRow = -1;
        int currCol = -1;
        boolean onMap = true;

        for (int i = 0; i < map.size(); ++i) {
            for (int j = 0; j < map.get(0).size(); ++j) {
                if (map.get(i).get(j).equals('^')) {
                    currRow = i;
                    currCol = j;
                }
            }
        }

        while (onMap) {
            if (map.get(currRow).get(currCol).equals('^')) {
                
                if ((currRow - 1) < 0) {
                    map.get(currRow).set(currCol, 'X');
                    onMap = false;
                }
                else if (map.get(currRow - 1).get(currCol).equals('.') ||
                    map.get(currRow - 1).get(currCol).equals('X')) {
                    map.get(currRow).set(currCol, 'X');
                    currRow -= 1;
                    map.get(currRow).set(currCol, '^');
                }
                else {
                    map.get(currRow).set(currCol, '>');
                }

            }
            else if (map.get(currRow).get(currCol).equals('>')) {
                
                if ((currCol + 1) >= map.get(0).size()) {
                    map.get(currRow).set(currCol, 'X');
                    onMap = false;
                }
                else if (map.get(currRow).get(currCol + 1).equals('.') ||
                    map.get(currRow).get(currCol + 1).equals('X')) {
                    map.get(currRow).set(currCol, 'X');
                    currCol += 1;
                    map.get(currRow).set(currCol, '>');
                }
                else {
                    map.get(currRow).set(currCol, 'v');
                }

            }
            else if (map.get(currRow).get(currCol).equals('v')) {
                
                if ((currRow + 1) >= map.size()) {
                    map.get(currRow).set(currCol, 'X');
                    onMap = false;
                }
                else if (map.get(currRow + 1).get(currCol).equals('.') ||
                    map.get(currRow + 1).get(currCol).equals('X')) {
                    map.get(currRow).set(currCol, 'X');
                    currRow += 1;
                    map.get(currRow).set(currCol, 'v');
                }
                else {
                    map.get(currRow).set(currCol, '<');
                }

            }
            else {

                if ((currCol - 1) < 0) {
                    map.get(currRow).set(currCol, 'X');
                    onMap = false;
                }
                else if (map.get(currRow).get(currCol - 1).equals('.') ||
                    map.get(currRow).get(currCol - 1).equals('X')) {
                    map.get(currRow).set(currCol, 'X');
                    currCol -= 1;
                    map.get(currRow).set(currCol, '<');
                }
                else {
                    map.get(currRow).set(currCol, '^');
                }

            }
        }
    }

    public static int countX(ArrayList<ArrayList<Character>> map) {
        int count = 0;
        
        for (int i = 0; i < map.size(); ++i) {
            for (int j = 0; j < map.get(0).size(); ++j) {
                if (map.get(i).get(j).equals('X')) {
                    count += 1;
                }
            }
        }
        return count;

    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> map = getMap("input.txt");
        createPath(map);

        System.out.println(countX(map));
    }
    
}
