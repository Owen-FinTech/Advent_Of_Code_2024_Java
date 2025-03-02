import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DiskFragmenterPt2 {

    public static int[] getDiskMap(String pathName) {
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

        int[] diskMapArray = new int[diskMap.size()];

        for (int i = 0; i < diskMap.size(); ++i) {
            diskMapArray[i] = diskMap.get(i);
        }

        return diskMapArray;
    }

    public static int[] getBlocks(int[] diskMapArray) {
        ArrayList<Integer> initialBlocks = new ArrayList<Integer>();
        int id = 0;

        for (int i = 0; i < diskMapArray.length; ++i) {
            
            id = i / 2;

            for (int j = 0; j < diskMapArray[i]; ++j) {
                
                if (i % 2 == 0) {
                    initialBlocks.add(id);
                }
                else {
                    initialBlocks.add(-1);
                }
            }
        }

        int[] blocks = new int[initialBlocks.size()];

        for (int i = 0; i < initialBlocks.size(); ++i) {
            blocks[i] = initialBlocks.get(i);
        }

        return blocks;
    }

    public static void moveBlocks(int[] blocks) {

        for (int i = blocks.length - 1; i >= 0; --i) {
            System.out.print(i);
            System.out.print(" of ");
            System.out.print(blocks.length);
            System.out.println();

            if (blocks[i] > -1) {
                int endFileIndex = i;
                int startFileIndex = i;
                
                if (i > 0) {
                    while (startFileIndex > 0 && 
                        blocks[startFileIndex - 1] == blocks[endFileIndex]) {
                        startFileIndex -= 1;
                        i -= 1;
                    }
                } 
            
                for (int j = 0; j < blocks.length; ++j) {
                    boolean noFilesAhead = true;
                    
                    if (blocks[j] == -1) {
                        int startSpaceIndex = j;
                        int endSpaceIndex = j;

                        for (int k = (j + 1); k < blocks.length; ++k) {
                            if (blocks[k] > -1) {
                                noFilesAhead = false;
                                break;
                            }
                        }

                        if (!noFilesAhead) {

                            while (endSpaceIndex <= (blocks.length - 2) &&
                                blocks[endSpaceIndex + 1] == -1) {
                                endSpaceIndex += 1;
                            }

                            if (((endSpaceIndex - startSpaceIndex) >= (endFileIndex - startFileIndex)) &&
                                startFileIndex > endSpaceIndex) {

                                for (int k = startFileIndex; k <= endFileIndex; ++k) {
                                    blocks[startSpaceIndex] = blocks[k];
                                    blocks[k] = -1;
                                    startSpaceIndex += 1;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public static Long checksum(int[] blocks) {
        Long sum = 0L;

        for (int i = 0; i < blocks.length; ++i) {
            
            if (blocks[i] == -1) {
                continue;
            }
            else {
                sum += Long.valueOf(i) * Long.valueOf(blocks[i]);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] diskMapArray = getDiskMap("input.txt");
        int[] blocks = getBlocks(diskMapArray);
        moveBlocks(blocks);
        System.out.println(checksum(blocks));
    }
}
