import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HoofItPt2 {

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
                        ArrayList<ArrayList<Integer>> leadTrails = new ArrayList<ArrayList<Integer>>();
                        leadTrails.add(visitedHead);

                        while (leadTrails.size() > 0) {
                            ArrayList<ArrayList<Integer>> tempLeadTrails = new ArrayList<ArrayList<Integer>>();

                            for (int k = 0; k < leadTrails.size(); ++k) {

                                if (map.get(leadTrails.get(k).get(0)).get(leadTrails.get(k).get(1)) == 9) {
                                    sum += 1;
                                }
                                
                                if ((leadTrails.get(k).get(0) - 1) >= 0) {

                                    if (map.get(leadTrails.get(k).get(0) - 1).get(leadTrails.get(k).get(1)) ==
                                        map.get(leadTrails.get(k).get(0)).get(leadTrails.get(k).get(1)) + 1) {
                                        ArrayList<Integer> tempTrail = new ArrayList<Integer>();
                                        tempTrail.add(leadTrails.get(k).get(0) - 1);
                                        tempTrail.add(leadTrails.get(k).get(1));
                                        tempLeadTrails.add(tempTrail);
                                    }
                                }

                                if ((leadTrails.get(k).get(0) + 1) <= map.size() - 1) {

                                    if (map.get(leadTrails.get(k).get(0) + 1).get(leadTrails.get(k).get(1)) ==
                                        map.get(leadTrails.get(k).get(0)).get(leadTrails.get(k).get(1)) + 1) {
                                        ArrayList<Integer> tempTrail = new ArrayList<Integer>();
                                        tempTrail.add(leadTrails.get(k).get(0) + 1);
                                        tempTrail.add(leadTrails.get(k).get(1));
                                        tempLeadTrails.add(tempTrail);
                                    }
                                }

                                if ((leadTrails.get(k).get(1) - 1) >= 0) {

                                    if (map.get(leadTrails.get(k).get(0)).get(leadTrails.get(k).get(1) - 1) ==
                                        map.get(leadTrails.get(k).get(0)).get(leadTrails.get(k).get(1)) + 1) {
                                        ArrayList<Integer> tempTrail = new ArrayList<Integer>();
                                        tempTrail.add(leadTrails.get(k).get(0));
                                        tempTrail.add(leadTrails.get(k).get(1) - 1);
                                        tempLeadTrails.add(tempTrail);
                                    }
                                }

                                if ((leadTrails.get(k).get(1) + 1) <= map.get(0).size() - 1) {

                                    if (map.get(leadTrails.get(k).get(0)).get(leadTrails.get(k).get(1) + 1) ==
                                        map.get(leadTrails.get(k).get(0)).get(leadTrails.get(k).get(1)) + 1) {
                                        ArrayList<Integer> tempTrail = new ArrayList<Integer>();
                                        tempTrail.add(leadTrails.get(k).get(0));
                                        tempTrail.add(leadTrails.get(k).get(1) + 1);
                                        tempLeadTrails.add(tempTrail);
                                    }
                                }
                            }

                            leadTrails = new ArrayList<ArrayList<Integer>>();
                            
                            for (int k = 0; k < tempLeadTrails.size(); ++k) {
                                ArrayList<Integer> tempTrail = new ArrayList<Integer>();
                                tempTrail.add(tempLeadTrails.get(k).get(0));
                                tempTrail.add(tempLeadTrails.get(k).get(1));
                                leadTrails.add(tempTrail);
                            }
                        }
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
