import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GardenGroupsPt1 {

    public static ArrayList<ArrayList<Character>> getRegions(String pathName) {
        ArrayList<ArrayList<Character>> regions = new ArrayList<ArrayList<Character>>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<Character> regionsRow = new ArrayList<Character>();

                for (int i = 0; i < data.length(); ++i) {
                    regionsRow.add(data.charAt(i));
                }
                regions.add(regionsRow);
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return regions;
    }

    public static int calculateFencing(ArrayList<ArrayList<Character>> regions) {
        ArrayList<ArrayList<Integer>> visited = new ArrayList<ArrayList<Integer>>();
        int cost = 0;

        for (int i = 0; i < regions.size(); ++i) {
            ArrayList<Integer> visitedRow = new ArrayList<Integer>();
            
            for (int j = 0; j < regions.get(0).size(); ++j) {
                visitedRow.add(0);
            }
            visited.add(visitedRow);
        }

        for (int i = 0; i < regions.size(); ++i) {
            for (int j = 0; j < regions.get(0).size(); ++j) {
                if (visited.get(i).get(j) == 0) {
                    ArrayList<ArrayList<Integer>> localVisited = new ArrayList<ArrayList<Integer>>();
                    Boolean added = true;
                    int regionArea = 0;
                    int regionPerim = 0;

                    for (int k = 0; k < regions.size(); ++k) {
                        ArrayList<Integer> localVisitedRow = new ArrayList<Integer>();
                        
                        for (int l = 0; l < regions.get(0).size(); ++l) {
                            localVisitedRow.add(0);
                        }
                        localVisited.add(localVisitedRow);
                    }

                    visited.get(i).set(j, 1);
                    localVisited.get(i).set(j, 1);

                    while (added) {
                        ArrayList<ArrayList<Integer>> tempLocalCoords = new ArrayList<ArrayList<Integer>>();
                        added = false;

                        for (int k = 0; k < localVisited.size(); ++k) {
                            for (int l = 0; l < localVisited.get(0).size(); ++l) {

                                if (localVisited.get(k).get(l) == 1) {
                                    
                                    if ((k - 1) >= 0) {
                                        if (regions.get(k).get(l).equals(regions.get(k - 1).get(l)) &&
                                            localVisited.get(k - 1).get(l) == 0) {
                                            ArrayList<Integer> tempPair = new ArrayList<Integer>();
                                            tempPair.add(k - 1);
                                            tempPair.add(l);
                                            tempLocalCoords.add(tempPair);
                                            added = true;
                                        }
                                    }

                                    if ((k + 1) <= (regions.size() - 1)) {
                                        if (regions.get(k).get(l).equals(regions.get(k + 1).get(l)) &&
                                            localVisited.get(k + 1).get(l) == 0) {
                                            ArrayList<Integer> tempPair = new ArrayList<Integer>();
                                            tempPair.add(k + 1);
                                            tempPair.add(l);
                                            tempLocalCoords.add(tempPair);
                                            added = true;
                                        }
                                    }

                                    if ((l - 1) >= 0) {
                                        if (regions.get(k).get(l).equals(regions.get(k).get(l - 1)) &&
                                            localVisited.get(k).get(l - 1) == 0) {
                                            ArrayList<Integer> tempPair = new ArrayList<Integer>();
                                            tempPair.add(k);
                                            tempPair.add(l - 1);
                                            tempLocalCoords.add(tempPair);
                                            added = true;
                                        }
                                    }

                                    if ((l + 1) <= (regions.get(0).size() - 1)) {
                                        if (regions.get(k).get(l).equals(regions.get(k).get(l + 1)) &&
                                            localVisited.get(k).get(l + 1) == 0) {
                                            ArrayList<Integer> tempPair = new ArrayList<Integer>();
                                            tempPair.add(k);
                                            tempPair.add(l + 1);
                                            tempLocalCoords.add(tempPair);
                                            added = true;
                                        }
                                    }
                                }
                            }
                        }

                        for (int k = 0; k < tempLocalCoords.size(); ++k) {
                            localVisited.get(tempLocalCoords.get(k).get(0)).set(tempLocalCoords.get(k).get(1), 1);
                            visited.get(tempLocalCoords.get(k).get(0)).set(tempLocalCoords.get(k).get(1), 1);
                        }

                    }

                    for (int k = 0; k < localVisited.size(); ++k) {
                        for (int l = 0; l < localVisited.get(0).size(); ++l) {

                            if (localVisited.get(k).get(l) == 1) {
                                regionArea += 1;

                                if ((k - 1) >= 0) {
                                    if (localVisited.get(k - 1).get(l) == 0) {
                                        regionPerim += 1;
                                    }
                                }
                                else {
                                    regionPerim += 1;
                                }

                                if ((k + 1) <= (localVisited.size() - 1)) {
                                    if (localVisited.get(k + 1).get(l) == 0) {
                                        regionPerim += 1;
                                    }
                                }
                                else {
                                    regionPerim += 1;
                                }

                                if ((l - 1) >= 0) {
                                    if (localVisited.get(k).get(l - 1) == 0) {
                                        regionPerim += 1;
                                    }
                                }
                                else {
                                    regionPerim += 1;
                                }

                                if ((l + 1) <= (localVisited.get(0).size() - 1)) {
                                    if (localVisited.get(k).get(l + 1) == 0) {
                                        regionPerim += 1;
                                    }
                                }
                                else {
                                    regionPerim += 1;
                                }
                            }
                        }
                    }
                    cost += (regionArea * regionPerim);
                }
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> regions = getRegions("input.txt");
        System.out.println(calculateFencing(regions));
    }
    
}
