import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WarehouseWoesPt1 {

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
                        row.add(data.charAt(i));
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
                else if (warehouse.get(currRow - 1).get(currCol) == 'O') {
                    for (int j = (currRow - 2); j > 0; --j) {
                        if (warehouse.get(j).get(currCol) == '.') {
                            warehouse.get(j).set(currCol, 'O');
                            warehouse.get(currRow).set(currCol, '.');
                            currRow -= 1;
                            warehouse.get(currRow).set(currCol, '@');
                            break;
                        }
                        else if (warehouse.get(j).get(currCol) == '#') {
                            break;
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
                else if (warehouse.get(currRow).get(currCol + 1) == 'O') {
                    for (int j = (currCol + 2); j < warehouse.get(0).size(); ++j) {
                        if (warehouse.get(currRow).get(j) == '.') {
                            warehouse.get(currRow).set(j, 'O');
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
                else if (warehouse.get(currRow + 1).get(currCol) == 'O') {
                    for (int j = (currRow + 2); j < warehouse.size(); ++j) {
                        if (warehouse.get(j).get(currCol) == '.') {
                            warehouse.get(j).set(currCol, 'O');
                            warehouse.get(currRow).set(currCol, '.');
                            currRow += 1;
                            warehouse.get(currRow).set(currCol, '@');
                            break;
                        }
                        else if (warehouse.get(j).get(currCol) == '#') {
                            break;
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
                else if (warehouse.get(currRow).get(currCol - 1) == 'O') {
                    for (int j = (currCol - 2); j > 0; --j) {
                        if (warehouse.get(currRow).get(j) == '.') {
                            warehouse.get(currRow).set(j, 'O');
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
                if (warehouse.get(i).get(j) == 'O') {
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
