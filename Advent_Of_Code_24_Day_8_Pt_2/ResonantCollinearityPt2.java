import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ResonantCollinearityPt2 {

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

    public static ArrayList<ArrayList<Character>> createAntinodes(ArrayList<ArrayList<Character>> map) {
        ArrayList<ArrayList<Character>> antinodes = new ArrayList<ArrayList<Character>>();
        ArrayList<Character> visited = new ArrayList<Character>();
        Character currChar = '.';

        for (int i = 0; i < map.size(); ++i) {
            ArrayList<Character> subList = new ArrayList<Character>();
            
            for (int j = 0; j < map.get(0).size(); ++j) {
                subList.add('.');
            }
            antinodes.add(subList);
        }

        for (int i = 0; i < map.size(); ++i) {
            for (int j = 0; j < map.get(0).size(); ++j) {
                if (Character.isLetterOrDigit(map.get(i).get(j)) && 
                    !visited.contains(map.get(i).get(j))) {
                    visited.add(map.get(i).get(j));
                    currChar = map.get(i).get(j);
                    ArrayList<ArrayList<Integer>> coordPairs = new ArrayList<ArrayList<Integer>>();

                    for (int k = 0; k < map.size(); ++k) {
                        for (int l = 0; l < map.get(0).size(); ++l) {
                            if (map.get(k).get(l).equals(currChar)) {
                                ArrayList<Integer> pair = new ArrayList<Integer>();
                                pair.add(k);
                                pair.add(l);
                                coordPairs.add(pair);
                            }
                        }
                    }

                    for (int k = 0; k < coordPairs.size(); ++k) {
                        for (int l = 0; l < coordPairs.size(); ++l) {
                            Integer vertDist = 0;
                            Integer horiDist = 0;
                            vertDist = Math.abs(coordPairs.get(k).get(0) - coordPairs.get(l).get(0));
                            horiDist = Math.abs(coordPairs.get(k).get(1) - coordPairs.get(l).get(1));
                            
                            if (coordPairs.get(k).get(0) <= coordPairs.get(l).get(0) && 
                                coordPairs.get(k).get(1) <= coordPairs.get(l).get(1)) {
                                int mult = 1;
                                boolean firstWithin = true;
                                boolean secondWithin = true;

                                while (firstWithin || secondWithin) {
                                    if ((coordPairs.get(k).get(0) - (vertDist * mult)) >= 0 && 
                                        (coordPairs.get(k).get(1) - (horiDist * mult)) >= 0) {
                                        antinodes.get(coordPairs.get(k).get(0) - (vertDist * mult)).set(coordPairs.get(k).get(1) - (horiDist * mult), '#');
                                        firstWithin = true;
                                    }
                                    else {
                                        firstWithin = false;
                                    }

                                    if ((coordPairs.get(l).get(0) + (vertDist * mult)) <= (antinodes.size() - 1) && 
                                        (coordPairs.get(l).get(1) + (horiDist * mult)) <= (antinodes.get(0).size() - 1)) {
                                        antinodes.get(coordPairs.get(l).get(0) + (vertDist * mult)).set(coordPairs.get(l).get(1) + (horiDist * mult), '#');
                                        secondWithin = true;
                                    }
                                    else {
                                        secondWithin = false;
                                    }
                                    mult += 1;

                                    if (k == l) {
                                        firstWithin = false;
                                        secondWithin = false;
                                    }
                                }
                            }
                            else if (coordPairs.get(k).get(0) >= coordPairs.get(l).get(0) && 
                                coordPairs.get(k).get(1) >= coordPairs.get(l).get(1)) {
                                int mult = 1;
                                boolean firstWithin = true;
                                boolean secondWithin = true;
                            
                                while (firstWithin || secondWithin) {
                                    if ((coordPairs.get(k).get(0) + (vertDist * mult)) <= (antinodes.size() - 1) && 
                                        (coordPairs.get(k).get(1) + (horiDist * mult)) <= (antinodes.get(0).size() - 1)) {
                                        antinodes.get(coordPairs.get(k).get(0) + (vertDist * mult)).set(coordPairs.get(k).get(1) + (horiDist * mult), '#');
                                        firstWithin = true;
                                    }
                                    else {
                                        firstWithin = false;
                                    }

                                    if ((coordPairs.get(l).get(0) - (vertDist * mult)) >= 0 && 
                                        (coordPairs.get(l).get(1) - (horiDist * mult)) >= 0) {
                                        antinodes.get(coordPairs.get(l).get(0) - (vertDist * mult)).set(coordPairs.get(l).get(1) - (horiDist * mult), '#');
                                        secondWithin = true;
                                    }
                                    else {
                                        secondWithin = false;
                                    }
                                    mult += 1;

                                    if (k == l) {
                                        firstWithin = false;
                                        secondWithin = false;
                                    }
                                }
                            }
                            else if (coordPairs.get(k).get(0) >= coordPairs.get(l).get(0) && 
                                coordPairs.get(k).get(1) <= coordPairs.get(l).get(1)) {
                                int mult = 1;
                                boolean firstWithin = true;
                                boolean secondWithin = true;
                            
                                while (firstWithin || secondWithin) {
                                    if ((coordPairs.get(k).get(0) + (vertDist * mult)) <= (antinodes.size() - 1) && 
                                        (coordPairs.get(k).get(1) - (horiDist * mult)) >= 0) {
                                        antinodes.get(coordPairs.get(k).get(0) + (vertDist * mult)).set(coordPairs.get(k).get(1) - (horiDist * mult), '#');
                                        firstWithin = true;
                                    }
                                    else {
                                        firstWithin = false;
                                    }

                                    if ((coordPairs.get(l).get(0) - (vertDist * mult)) >= 0 && 
                                        (coordPairs.get(l).get(1) + (horiDist * mult)) <= (antinodes.get(0).size() - 1)) {
                                        antinodes.get(coordPairs.get(l).get(0) - (vertDist * mult)).set(coordPairs.get(l).get(1) + (horiDist * mult), '#');
                                        secondWithin = true;
                                    }
                                    else {
                                        secondWithin = false;
                                    }
                                    mult += 1;

                                    if (k == l) {
                                        firstWithin = false;
                                        secondWithin = false;
                                    }
                                }
                            }
                            else if (coordPairs.get(k).get(0) <= coordPairs.get(l).get(0) && 
                                coordPairs.get(k).get(1) >= coordPairs.get(l).get(1)) {
                                int mult = 1;
                                boolean firstWithin = true;
                                boolean secondWithin = true;
                            
                                while (firstWithin || secondWithin) {
                                    if ((coordPairs.get(k).get(0) - (vertDist * mult)) >= 0 && 
                                        (coordPairs.get(k).get(1) + (horiDist * mult)) <= (antinodes.get(0).size() - 1)) {
                                        antinodes.get(coordPairs.get(k).get(0) - (vertDist * mult)).set(coordPairs.get(k).get(1) + (horiDist * mult), '#');
                                        firstWithin = true;
                                    }
                                    else {
                                        firstWithin = false;
                                    }

                                    if ((coordPairs.get(l).get(0) + (vertDist * mult)) <= (antinodes.size() - 1) && 
                                        (coordPairs.get(l).get(1) - (horiDist * mult)) >= 0) {
                                        antinodes.get(coordPairs.get(l).get(0) + (vertDist * mult)).set(coordPairs.get(l).get(1) - (horiDist * mult), '#');
                                        secondWithin = true;
                                    }
                                    else {
                                        secondWithin = false;
                                    }
                                    mult += 1;

                                    if (k == l) {
                                        firstWithin = false;
                                        secondWithin = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return antinodes;
    }

    public static int countAntinodes(ArrayList<ArrayList<Character>> antinodes) {
        int count = 0;

        for (int i = 0; i < antinodes.size(); ++i) {
            for (int j = 0; j < antinodes.get(0).size(); ++j) {
                if (antinodes.get(i).get(j).equals('#')) {
                    count += 1;
                }
            }
        }
        return count;
    }

    
    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> map = getMap("input.txt");
        ArrayList<ArrayList<Character>> antinodes = createAntinodes(map);

        System.out.println(countAntinodes(antinodes));
    }
    
}
