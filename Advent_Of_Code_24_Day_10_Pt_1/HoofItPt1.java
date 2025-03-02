import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HoofItPt1 {

    public static ArrayList<ArrayList<Integer>> getMap(String pathName) {
        ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                ArrayList<Integer> subMap = new ArrayList<Integer>();

                for (int i = 0; i < data.length(); ++i) {
                    subMap.add(Integer.parseInt(data.substring(i, i + 1)));
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

    public static int getTrailheads(ArrayList<ArrayList<Integer>> map) {
        int sum = 0;
        ArrayList<ArrayList<Integer>> visitedHeads = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < map.size(); ++i) {
            for (int j = 0; j < map.get(0).size(); ++j) {

                if (map.get(i).get(j) == 0) {
                    boolean inVisitedHeads = false;

                    for (int k = 0; k < visitedHeads.size(); ++k) {
                        if (i == visitedHeads.get(k).get(0) &&
                            j == visitedHeads.get(k).get(1)) {
                            inVisitedHeads = true;
                            break;
                        }
                    }

                    if (!inVisitedHeads) {
                        ArrayList<Integer> visitedHead = new ArrayList<Integer>();
                        visitedHead.add(i);
                        visitedHead.add(j);
                        visitedHeads.add(visitedHead);
                        int currScore = 0;
                        ArrayList<ArrayList<Integer>> visitedTrails = new ArrayList<ArrayList<Integer>>();
                        ArrayList<Integer> visitedTrail = new ArrayList<Integer>();
                        visitedTrail.add(i);
                        visitedTrail.add(j);
                        visitedTrails.add(visitedTrail);
                        boolean trailAdded = true;

                        while (trailAdded) {
                            trailAdded = false;
                            ArrayList<ArrayList<Integer>> tempVisitedTrails = new ArrayList<ArrayList<Integer>>();

                            for (int k = 0; k < visitedTrails.size(); ++k) {

                                if ((visitedTrails.get(k).get(0) - 1) >= 0) {
                                    boolean inVisited = false;

                                    for (int l = 0; l < visitedTrails.size(); ++l) {
                                        if (visitedTrails.get(l).get(0).equals(visitedTrails.get(k).get(0) - 1) &&
                                            visitedTrails.get(l).get(1).equals(visitedTrails.get(k).get(1))) {
                                            inVisited = true;
                                            break;
                                        }
                                    }

                                    if (!inVisited && (map.get(visitedTrails.get(k).get(0) - 1).get(visitedTrails.get(k).get(1)) == 
                                        map.get(visitedTrails.get(k).get(0)).get(visitedTrails.get(k).get(1)) + 1)) {
                                        boolean inTemp = false;

                                        for (int l = 0; l < tempVisitedTrails.size(); ++l) {
                                            if (tempVisitedTrails.get(l).get(0).equals(visitedTrails.get(k).get(0) - 1) &&
                                                tempVisitedTrails.get(l).get(1).equals(visitedTrails.get(k).get(1))) {
                                                inTemp = true;
                                                break;
                                            }
                                        }

                                        if (!inTemp) {
                                            ArrayList<Integer> tempTrail = new ArrayList<Integer>();
                                            tempTrail.add(visitedTrails.get(k).get(0) - 1);
                                            tempTrail.add(visitedTrails.get(k).get(1));
                                            tempVisitedTrails.add(tempTrail);
                                            trailAdded = true;

                                            if (map.get(visitedTrails.get(k).get(0) - 1).get(visitedTrails.get(k).get(1)) == 9) {
                                                currScore += 1;
                                            }
                                        }
                                    }
                                }

                                if ((visitedTrails.get(k).get(0) + 1) <= (map.size() - 1)) {
                                    boolean inVisited = false;

                                    for (int l = 0; l < visitedTrails.size(); ++l) {
                                        if (visitedTrails.get(l).get(0).equals(visitedTrails.get(k).get(0) + 1) &&
                                            visitedTrails.get(l).get(1).equals(visitedTrails.get(k).get(1))) {
                                            inVisited = true;
                                            break;
                                        }
                                    }

                                    if (!inVisited && (map.get(visitedTrails.get(k).get(0) + 1).get(visitedTrails.get(k).get(1)) == 
                                        map.get(visitedTrails.get(k).get(0)).get(visitedTrails.get(k).get(1)) + 1)) {
                                        boolean inTemp = false;

                                        for (int l = 0; l < tempVisitedTrails.size(); ++l) {
                                            if (tempVisitedTrails.get(l).get(0).equals(visitedTrails.get(k).get(0) + 1) &&
                                                tempVisitedTrails.get(l).get(1).equals(visitedTrails.get(k).get(1))) {
                                                inTemp = true;
                                                break;
                                            }
                                        }

                                        if (!inTemp) {
                                            ArrayList<Integer> tempTrail = new ArrayList<Integer>();
                                            tempTrail.add(visitedTrails.get(k).get(0) + 1);
                                            tempTrail.add(visitedTrails.get(k).get(1));
                                            tempVisitedTrails.add(tempTrail);
                                            trailAdded = true;

                                            if (map.get(visitedTrails.get(k).get(0) + 1).get(visitedTrails.get(k).get(1)) == 9) {
                                                currScore += 1;
                                            }
                                        }
                                    }
                                }

                                if ((visitedTrails.get(k).get(1) - 1) >= 0) {
                                    boolean inVisited = false;

                                    for (int l = 0; l < visitedTrails.size(); ++l) {
                                        if (visitedTrails.get(l).get(0).equals(visitedTrails.get(k).get(0)) &&
                                            visitedTrails.get(l).get(1).equals(visitedTrails.get(k).get(1) - 1)) {
                                            inVisited = true;
                                            break;
                                        }
                                    }

                                    if (!inVisited && (map.get(visitedTrails.get(k).get(0)).get(visitedTrails.get(k).get(1) - 1) == 
                                        map.get(visitedTrails.get(k).get(0)).get(visitedTrails.get(k).get(1)) + 1)) {
                                        boolean inTemp = false;

                                        for (int l = 0; l < tempVisitedTrails.size(); ++l) {
                                            if (tempVisitedTrails.get(l).get(0).equals(visitedTrails.get(k).get(0)) &&
                                                tempVisitedTrails.get(l).get(1).equals(visitedTrails.get(k).get(1) - 1)) {
                                                inTemp = true;
                                                break;
                                            }
                                        }

                                        if (!inTemp) {
                                            ArrayList<Integer> tempTrail = new ArrayList<Integer>();
                                            tempTrail.add(visitedTrails.get(k).get(0));
                                            tempTrail.add(visitedTrails.get(k).get(1) - 1);
                                            tempVisitedTrails.add(tempTrail);
                                            trailAdded = true;

                                            if (map.get(visitedTrails.get(k).get(0)).get(visitedTrails.get(k).get(1) - 1) == 9) {
                                                currScore += 1;
                                            }
                                        }
                                    }
                                }

                                if ((visitedTrails.get(k).get(1) + 1) <= (map.get(0).size() - 1)) {
                                    boolean inVisited = false;

                                    for (int l = 0; l < visitedTrails.size(); ++l) {
                                        if (visitedTrails.get(l).get(0).equals(visitedTrails.get(k).get(0)) &&
                                            visitedTrails.get(l).get(1).equals(visitedTrails.get(k).get(1) + 1)) {
                                            inVisited = true;
                                            break;
                                        }
                                    }

                                    if (!inVisited && (map.get(visitedTrails.get(k).get(0)).get(visitedTrails.get(k).get(1) + 1) == 
                                        map.get(visitedTrails.get(k).get(0)).get(visitedTrails.get(k).get(1)) + 1)) {
                                        boolean inTemp = false;

                                        for (int l = 0; l < tempVisitedTrails.size(); ++l) {
                                            if (tempVisitedTrails.get(l).get(0).equals(visitedTrails.get(k).get(0)) &&
                                                tempVisitedTrails.get(l).get(1).equals(visitedTrails.get(k).get(1) + 1)) {
                                                inTemp = true;
                                                break;
                                            }
                                        }

                                        if (!inTemp) {
                                            ArrayList<Integer> tempTrail = new ArrayList<Integer>();
                                            tempTrail.add(visitedTrails.get(k).get(0));
                                            tempTrail.add(visitedTrails.get(k).get(1) + 1);
                                            tempVisitedTrails.add(tempTrail);
                                            trailAdded = true;

                                            if (map.get(visitedTrails.get(k).get(0)).get(visitedTrails.get(k).get(1) + 1) == 9) {
                                                currScore += 1;
                                            }
                                        }
                                    }
                                }
                            }

                            for (int k = 0; k < tempVisitedTrails.size(); ++k) {
                                ArrayList<Integer> tempTrail = new ArrayList<Integer>();
                                tempTrail.add(tempVisitedTrails.get(k).get(0));
                                tempTrail.add(tempVisitedTrails.get(k).get(1));
                                visitedTrails.add(tempTrail);
                            }
                        }
                        sum += currScore;
                    }
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> map = getMap("input.txt");

        System.out.println(getTrailheads(map));
    }
    
}
