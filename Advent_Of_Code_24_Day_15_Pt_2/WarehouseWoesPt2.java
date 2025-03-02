import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WarehouseWoesPt2 {

    public static ArrayList<ArrayList<Character>> getWarehouse(String pathName) {
        ArrayList<ArrayList<Character>> warehouse = new ArrayList<ArrayList<Character>>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            Boolean breakFound = false;
            
            while (myReader.hasNextLine() && !breakFound) {
                String data = myReader.nextLine();
                ArrayList<Character> row = new ArrayList<Character>();

                if (data.isEmpty()) {
                    breakFound = true;
                }
                else {
                    for (int i = 0; i < data.length(); ++i) {

                        if (data.charAt(i) == '#') {
                            row.add('#');
                            row.add('#');
                        }
                        else if (data.charAt(i) == 'O') {
                            row.add('[');
                            row.add(']');
                        }
                        else if (data.charAt(i) == '.') {
                            row.add('.');
                            row.add('.');
                        }
                        else if (data.charAt(i) == '@') {
                            row.add('@');
                            row.add('.');
                        }
                    }
                    warehouse.add(row);
                }
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return warehouse;
    }

    public static ArrayList<Character> getMoves(String pathName) {
        ArrayList<Character> moves = new ArrayList<Character>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            Boolean breakFound = false;
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if (data.isEmpty()) {
                    breakFound = true;
                    continue;
                }

                if (breakFound) {
                    for (int i = 0; i < data.length(); ++i) {
                        moves.add(data.charAt(i));
                    }
                }
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return moves;
    }

    public static ArrayList<ArrayList<Character>> getFinalState(ArrayList<ArrayList<Character>> warehouse, ArrayList<Character> moves) {
        int currRow = 0;
        int currCol = 0;

        for (int i = 0; i < warehouse.size(); ++i) {
            for (int j = 0; j < warehouse.get(0).size(); ++j) {
                if (warehouse.get(i).get(j) == '@') {
                    currRow = i;
                    currCol = j;
                }
            }
        }

        for (int i = 0; i < moves.size(); ++i) {
            if (moves.get(i) == '^') {
                if (warehouse.get(currRow - 1).get(currCol) == '.') {
                    warehouse.get(currRow).set(currCol, '.');
                    currRow -= 1;
                    warehouse.get(currRow).set(currCol, '@');
                }
                else if (warehouse.get(currRow - 1).get(currCol) == '[' ||
                    warehouse.get(currRow - 1).get(currCol) == ']') {
                    
                    ArrayList<ArrayList<Integer>> leadEdges = new ArrayList<ArrayList<Integer>>();

                    for (int j = 0; j < warehouse.size(); ++j) {
                        ArrayList<Integer> leadEdge = new ArrayList<Integer>();
                        leadEdges.add(leadEdge);
                    }

                    if (warehouse.get(currRow - 1).get(currCol) == '[') {
                        leadEdges.get(currRow - 1).add(currCol);
                        leadEdges.get(currRow - 1).add(currCol + 1);
                    }
                    else {
                        leadEdges.get(currRow - 1).add(currCol - 1);
                        leadEdges.get(currRow - 1).add(currCol);
                    }

                    Boolean wall = false;

                    for (int j = (currRow - 2); j > 0; --j) {
                        Boolean shiftable = false;
                        int dotCount = 0;

                        for (int k = 0; k < leadEdges.get(j + 1).size(); ++k) {
                            
                            if (warehouse.get(j).get(leadEdges.get(j + 1).get(k)) == '.') {
                                dotCount += 1;
                            }
                            else if (warehouse.get(j).get(leadEdges.get(j + 1).get(k)) == '[') {
                                if (!leadEdges.get(j).contains(leadEdges.get(j + 1).get(k))) {
                                    leadEdges.get(j).add(leadEdges.get(j + 1).get(k));
                                }

                                if (!leadEdges.get(j).contains(leadEdges.get(j + 1).get(k) + 1)) {
                                    leadEdges.get(j).add(leadEdges.get(j + 1).get(k) + 1);
                                }
                            }
                            else if (warehouse.get(j).get(leadEdges.get(j + 1).get(k)) == ']') {
                                if (!leadEdges.get(j).contains(leadEdges.get(j + 1).get(k) - 1)) {
                                    leadEdges.get(j).add(leadEdges.get(j + 1).get(k) - 1);
                                }

                                if (!leadEdges.get(j).contains(leadEdges.get(j + 1).get(k))) {
                                    leadEdges.get(j).add(leadEdges.get(j + 1).get(k));
                                }
                            }
                            else if (warehouse.get(j).get(leadEdges.get(j + 1).get(k)) == '#') {
                                wall = true; 
                                break;
                            }

                            if (dotCount == leadEdges.get(j + 1).size()) {
                                shiftable = true;
                            }

                        }

                        if (wall) {
                            break;
                        }

                        if (shiftable) {
                            for (int k = 0; k < leadEdges.size(); ++k) {
                                for (int l = 0; l < leadEdges.get(k).size(); ++l) {
                                    warehouse.get(k).set(leadEdges.get(k).get(l), '.');
                                }
                            }

                            for (int k = 0; k < leadEdges.size(); ++k) {
                                for (int l = 0; l < leadEdges.get(k).size(); ++l) {
                                    if (l % 2 == 0) {
                                        warehouse.get(k - 1).set(leadEdges.get(k).get(l), '[');
                                    }
                                    else {
                                        warehouse.get(k - 1).set(leadEdges.get(k).get(l), ']');
                                    }
                                }
                            }

                            warehouse.get(currRow).set(currCol, '.');
                            currRow -= 1;
                            warehouse.get(currRow).set(currCol, '@');
                        }
                    }
                }
            }
            else if (moves.get(i) == '>') {
                if (warehouse.get(currRow).get(currCol + 1) == '.') {
                    warehouse.get(currRow).set(currCol, '.');
                    currCol += 1;
                    warehouse.get(currRow).set(currCol, '@');
                }
                else if (warehouse.get(currRow).get(currCol + 1) == '[') {
                    for (int j = (currCol + 2); j < warehouse.get(0).size(); ++j) {
                        if (warehouse.get(currRow).get(j) == '.') {

                            warehouse.get(currRow).set(j, ']');

                            for (int k = (j - 1); k >= (currCol + 2); --k) {
                                if (warehouse.get(currRow).get(k) == '[') {
                                    warehouse.get(currRow).set(k, ']');
                                }
                                else {
                                    warehouse.get(currRow).set(k, '[');
                                }
                            }
                            warehouse.get(currRow).set(currCol, '.');
                            currCol += 1;
                            warehouse.get(currRow).set(currCol, '@');
                            break;
                        }
                        else if (warehouse.get(currRow).get(j) == '#') {
                            break;
                        }
                    }
                }
            }
            else if (moves.get(i) == 'v') {
                if (warehouse.get(currRow + 1).get(currCol) == '.') {
                    warehouse.get(currRow).set(currCol, '.');
                    currRow += 1;
                    warehouse.get(currRow).set(currCol, '@');
                }
                else if (warehouse.get(currRow + 1).get(currCol) == '[' ||
                    warehouse.get(currRow + 1).get(currCol) == ']') {
                    
                    ArrayList<ArrayList<Integer>> leadEdges = new ArrayList<ArrayList<Integer>>();

                    for (int j = 0; j < warehouse.size(); ++j) {
                        ArrayList<Integer> leadEdge = new ArrayList<Integer>();
                        leadEdges.add(leadEdge);
                    }

                    if (warehouse.get(currRow + 1).get(currCol) == '[') {
                        leadEdges.get(currRow + 1).add(currCol);
                        leadEdges.get(currRow + 1).add(currCol + 1);
                    }
                    else {
                        leadEdges.get(currRow + 1).add(currCol - 1);
                        leadEdges.get(currRow + 1).add(currCol);
                    }

                    Boolean wall = false;

                    for (int j = (currRow + 2); j < (warehouse.size() - 1); ++j) {
                        Boolean shiftable = false;
                        int dotCount = 0;

                        for (int k = 0; k < leadEdges.get(j - 1).size(); ++k) {
                            
                            if (warehouse.get(j).get(leadEdges.get(j - 1).get(k)) == '.') {
                                dotCount += 1;
                            }
                            else if (warehouse.get(j).get(leadEdges.get(j - 1).get(k)) == '[') {
                                if (!leadEdges.get(j).contains(leadEdges.get(j - 1).get(k))) {
                                    leadEdges.get(j).add(leadEdges.get(j - 1).get(k));
                                }

                                if (!leadEdges.get(j).contains(leadEdges.get(j - 1).get(k) + 1)) {
                                    leadEdges.get(j).add(leadEdges.get(j - 1).get(k) + 1);
                                }
                            }
                            else if (warehouse.get(j).get(leadEdges.get(j - 1).get(k)) == ']') {
                                if (!leadEdges.get(j).contains(leadEdges.get(j - 1).get(k) - 1)) {
                                    leadEdges.get(j).add(leadEdges.get(j - 1).get(k) - 1);
                                }

                                if (!leadEdges.get(j).contains(leadEdges.get(j - 1).get(k))) {
                                    leadEdges.get(j).add(leadEdges.get(j - 1).get(k));
                                }
                            }
                            else if (warehouse.get(j).get(leadEdges.get(j - 1).get(k)) == '#') {
                                wall = true; 
                                break;
                            }

                            if (dotCount == leadEdges.get(j - 1).size()) {
                                shiftable = true;
                            }

                        }

                        if (wall) {
                            break;
                        }

                        if (shiftable) {
                            for (int k = 0; k < leadEdges.size(); ++k) {
                                for (int l = 0; l < leadEdges.get(k).size(); ++l) {
                                    warehouse.get(k).set(leadEdges.get(k).get(l), '.');
                                }
                            }

                            for (int k = 0; k < leadEdges.size(); ++k) {
                                for (int l = 0; l < leadEdges.get(k).size(); ++l) {
                                    if (l % 2 == 0) {
                                        warehouse.get(k + 1).set(leadEdges.get(k).get(l), '[');
                                    }
                                    else {
                                        warehouse.get(k + 1).set(leadEdges.get(k).get(l), ']');
                                    }
                                }
                            }

                            warehouse.get(currRow).set(currCol, '.');
                            currRow += 1;
                            warehouse.get(currRow).set(currCol, '@');
                        }
                    }
                }
            }
            else {
                if (warehouse.get(currRow).get(currCol - 1) == '.') {
                    warehouse.get(currRow).set(currCol, '.');
                    currCol -= 1;
                    warehouse.get(currRow).set(currCol, '@');
                }
                else if (warehouse.get(currRow).get(currCol - 1) == ']') {
                    for (int j = (currCol - 2); j > 0; --j) {
                        if (warehouse.get(currRow).get(j) == '.') {
                            
                            warehouse.get(currRow).set(j, '[');

                            for (int k = (j + 1); k <= (currCol - 2); ++k) {
                                if (warehouse.get(currRow).get(k) == '[') {
                                    warehouse.get(currRow).set(k, ']');
                                }
                                else {
                                    warehouse.get(currRow).set(k, '[');
                                }
                            }
                            warehouse.get(currRow).set(currCol, '.');
                            currCol -= 1;
                            warehouse.get(currRow).set(currCol, '@');
                            break;
                        }
                        else if (warehouse.get(currRow).get(j) == '#') {
                            break;
                        }
                    }
                }
            }
        }
        return warehouse;
    }

    public static int sumGPS(ArrayList<ArrayList<Character>> warehouse) {
        int sum = 0;

        for (int i = 0; i < warehouse.size(); ++i) {
            for (int j = 0; j < warehouse.get(0).size(); ++j) {
                if (warehouse.get(i).get(j) == '[') {
                    sum += (100 * i) + j;
                }
            }
        } 
        return sum;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> warehouse = getWarehouse("input.txt");
        ArrayList<Character> moves = getMoves("input.txt");
        warehouse = getFinalState(warehouse, moves);
        System.out.println(sumGPS(warehouse));
    }
    
}
