import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RestroomRedoubtPt1 {

    public static ArrayList<ArrayList<Integer>> getRobots(String pathName) {
        ArrayList<ArrayList<Integer>> posVel = new ArrayList<ArrayList<Integer>>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<Integer> posVelLine = new ArrayList<Integer>();
                String currNum = "";

                for (int i = 0; i < data.length(); ++i) {
                    if (data.charAt(i) == ',' || data.charAt(i) == ' ') {
                        posVelLine.add(Integer.parseInt(currNum));
                        currNum = "";
                    }
                    else if (Character.isDigit(data.charAt(i)) ||
                        data.charAt(i) == '-') {
                        currNum += data.substring(i, i + 1); 
                    }

                    if (i == data.length() - 1) {
                        posVelLine.add(Integer.parseInt(currNum));
                    }
                }
                posVel.add(posVelLine);
                
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return posVel;
    }

    public static void safetyFactor(ArrayList<ArrayList<Integer>> posVel, int width, int height) {

        ArrayList<ArrayList<ArrayList<Integer>>> map = new ArrayList<ArrayList<ArrayList<Integer>>>();

        for (int i = 0; i < height; ++i) {
            ArrayList<ArrayList<Integer>> row = new ArrayList<ArrayList<Integer>>();

            for (int j = 0; j < width; ++j) {
                ArrayList<Integer> robots = new ArrayList<Integer>();
                robots.add(-1);
                row.add(robots);
            }
            map.add(row);
        }

        for (int i = 0; i < posVel.size(); ++i) {
            if (map.get(posVel.get(i).get(1)).get(posVel.get(i).get(0)).get(0) == -1) {
                map.get(posVel.get(i).get(1)).get(posVel.get(i).get(0)).set(0, i);
            }
            else {
                map.get(posVel.get(i).get(1)).get(posVel.get(i).get(0)).add(i);
            }
        }

        for (int i = 0; i < 10000; ++i) {
            ArrayList<Integer> visited = new ArrayList<Integer>();
            ArrayList<ArrayList<ArrayList<Integer>>> newMap = new ArrayList<ArrayList<ArrayList<Integer>>>();

            for (int j = 0; j < height; ++j) {
                ArrayList<ArrayList<Integer>> row = new ArrayList<ArrayList<Integer>>();

                for (int k = 0; k < width; ++k) {
                    ArrayList<Integer> robots = new ArrayList<Integer>();
                    robots.add(-1);
                    row.add(robots);
                }
                newMap.add(row);
            }

            for (int j = 0; j < map.size(); ++j) {
                for (int k = 0; k < map.get(j).size(); ++k) {
                    for (int l = 0; l < map.get(j).get(k).size(); ++l) {
                        if (map.get(j).get(k).get(l) != -1 && 
                            !visited.contains(map.get(j).get(k).get(l))) {
                            if (newMap.get(Math.floorMod((j + posVel.get(map.get(j).get(k).get(l)).get(3)), height)).get(Math.floorMod(k + posVel.get(map.get(j).get(k).get(l)).get(2), width)).get(0) == -1) {
                                newMap.get(Math.floorMod((j + posVel.get(map.get(j).get(k).get(l)).get(3)), height)).get(Math.floorMod(k + posVel.get(map.get(j).get(k).get(l)).get(2), width)).set(0, map.get(j).get(k).get(l));
                            }
                            else {
                                newMap.get(Math.floorMod((j + posVel.get(map.get(j).get(k).get(l)).get(3)), height)).get(Math.floorMod(k + posVel.get(map.get(j).get(k).get(l)).get(2), width)).add(map.get(j).get(k).get(l));
                            }
                            visited.add(map.get(j).get(k).get(l));
                        }
                    }
                }
            }
            map = newMap;
            int maxConsec = 0;

            for (int j = 0; j < map.size(); ++j) {
                int currConsec = 0;
                for (int k = 0; k < map.get(j).size(); ++k) {
                    if (map.get(j).get(k).get(0) != -1) {
                        currConsec += 1;
                        if (currConsec > maxConsec) {
                            maxConsec = currConsec;
                        }
                    }
                    else {
                        currConsec = 0;
                    }
                }
            }

            if (maxConsec >= 8) {
                for (int j = 0; j < map.size(); ++j) {
                    for (int k = 0; k < map.get(j).size(); ++k) {
                        
                        if (map.get(j).get(k).get(0) != -1 ) {
                            System.out.print(map.get(j).get(k).size());
                        }
                        else {
                            System.out.print(".");
                        }
                    }
                    System.out.println();
                }
            }
            System.out.println(i + 1);
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> posVel = getRobots("input.txt");
        safetyFactor(posVel, 101, 103);
    }
    
}
