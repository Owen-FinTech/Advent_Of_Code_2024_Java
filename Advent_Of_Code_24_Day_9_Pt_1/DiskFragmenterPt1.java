import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DiskFragmenterPt1 {

    public static ArrayList<Integer> getDiskMap(String pathName) {
        ArrayList<Integer> diskMap = new ArrayList<Integer>();

        try {
            File myObj = new File(pathName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                for (int i = 0; i < data.length(); ++i) {
                    diskMap.add(Integer.parseInt(data.substring(i, i + 1)));
                }
            }
            myReader.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return diskMap;
    }

    public static ArrayList<Integer> getBlocks(ArrayList<Integer> diskMap) {
        ArrayList<Integer> blocks = new ArrayList<Integer>();
        int id = 0;

        for (int i = 0; i < diskMap.size(); ++i) {
            
            if (i != 0) {
                id = i / 2;
            }

            for (int j = 0; j < diskMap.get(i); ++j) {
                
                if (i % 2 == 0) {
                    blocks.add(id);
                }
                else {
                    blocks.add(-1);
                }
            }
        }
        return blocks;
    }

    public static void moveBlocks(ArrayList<Integer> blocks) {

        for (int i = blocks.size() - 1; i >= 0; --i) {
            
            if (blocks.get(i) > -1) {
            
                for (int j = 0; j < blocks.size(); ++j) {
                    boolean noFilesAhead = true;
                    
                    if (blocks.get(j).equals(-1)) {
                        
                        for (int k = (j + 1); k < blocks.size(); ++k) {
                            if (blocks.get(k) > -1) {
                                noFilesAhead = false;
                                break;
                            }
                        }

                        if (!noFilesAhead) {
                            blocks.set(j, blocks.get(i));
                            blocks.set(i, -1);
                        }
                        break;
                    }
                }
            }
        }
    }

    public static Long checksum(ArrayList<Integer> blocks) {
        Long sum = 0L;

        for (int i = 0; i < blocks.size(); ++i) {
            
            if (blocks.get(i).equals(-1)) {
                break;
            }
            else {
                sum += Long.valueOf(i) * Long.valueOf(blocks.get(i));
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ArrayList<Integer> diskMap = getDiskMap("input.txt");
        ArrayList<Integer> blocks = getBlocks(diskMap);
        moveBlocks(blocks);
        System.out.println(checksum(blocks));
    }
}
