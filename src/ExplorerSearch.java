import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    public static void main(String[] args) {
        int[][] island1 = {
            {0,1,1,2,1,1},
            {2,1,1,2,1,3},
            {2,1,2,2,1,3},
            {3,1,1,1,1,3},
        };

        int[][] island2 = {
            {3,1,3,3,1,3},
            {3,1,3,3,1,3},
            {3,1,1,1,1,3},
            {1,1,3,3,1,0},
        };
        
        System.out.println("Total Spaces Explored: " + reachableArea(island1));
        System.out.println("Total Spaces Explored: " + reachableArea(island2));
    }

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following numbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        // Implement your method here!
        // Please also make more test cases
        // I STRONGLY RECOMMEND testing some helpers you might make too
        int[] start = findExplorer(island);
        boolean[][] visited = new boolean[island.length][island[0].length];
        return reachableArea(island, start, visited);
    }

    public static int reachableArea(int[][] island, int[]current, boolean[][] visited) {
        PrettyPrint.prettyPrintln(current);
        PrettyPrint.prettyPrintln(visited);
       
        int curR = current[0];
        int curC = current[1];
        
        if (visited[curR][curC]) return 0;
        visited[curR][curC] = true;

        int count = 1;
        List<int[]> neighbors = possibleToExplore(island, current);
        for(int[] neighbor : neighbors) { 
            count+= reachableArea(island, neighbor, visited);
        }
        return count;
    }

    public static int[] findExplorer(int[][] island) {
        for(int r = 0;  r < island.length; r++) {
            for(int c = 0 ; c < island[r].length; c++) {
                if(island[r][c] == 0) {
                    int[]location = new int[] {r, c};
                    return location;
                }
            }
        }
        throw new IllegalArgumentException("No explorer found");
    }

    public static List<int[]> possibleToExplore(int[][] island, int[] current) {
        List<int[]> explorable = new ArrayList<>();
        int curR = current[0];
        int curC = current[1];
        explorable.add(new int[]{curR, curC});
        
        // up
        int newR = curR -1;
        int newC = curC;
        if(newR >= 0 && island[newR][newC] == 1) {
           explorable.add(new int[]{newR, newC});
        }

        // down
        newR = curR +1;
        newC = curC;
        if(newR < island.length && island[newR][newC] == 1) {
            explorable.add(new int[]{newR, newC});
       }

       // left
        newR = curR;
        newC = curC -1;
        if(newC >=0 && island[newR][newC] == 1) {
            explorable.add(new int[]{newR, newC});
       }

        // right
        newR = curR;
        newC = curC +1;
        if(newC <island[newR].length && island[newR][newC] == 1) {
            explorable.add(new int[]{newR, newC});
       }
       return explorable;
    }
}
