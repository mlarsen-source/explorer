import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Random;

import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    // Add more tests here!
    // Come up with varied cases

 
    @Test
    public void findExplorerFirstRowFirstColumn() {
        int[][] island = {
            {0,3,2,3,3},
            {3,2,3,2,3},
            {2,3,4,3,3},
            {3,2,2,2,3},
            {2,3,2,2,3},
        };
        int[] actual = ExplorerSearch.findExplorer(island);
        int[] expected = {0, 0};
        assertArrayEquals(expected, actual);
    }

     @Test
    public void findExplorerMiddleRowMiddleColumn() {
        int[][] island = {
            {2,3,2,3,3},
            {3,2,3,2,3},
            {2,3,0,3,3},
            {3,2,2,2,3},
            {2,3,2,2,3},
        };
        int[] actual = ExplorerSearch.findExplorer(island);
        int[] expected = {2, 2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void findExplorerLastRowLastColumn() {
        int[][] island = {
            {2,3,2,3,3},
            {3,2,3,2,3},
            {2,3,1,3,3},
            {3,2,2,2,3},
            {2,3,2,2,0},
        };
        int[] actual = ExplorerSearch.findExplorer(island);
        int[] expected = {4, 4};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void findExplorerRandomLocation() {
        int[][] island = {
            {2,2,2,2,2},
            {2,2,2,2,2},
            {2,2,2,2,2},
            {2,2,2,2,2},
            {2,2,2,2,2},
        };
        Random random = new Random();
        int row = random.nextInt(island.length);
        int col = random.nextInt(island[0].length);
        island[row][col] = 0;

        int[] actual = ExplorerSearch.findExplorer(island);
        int[] expected = {row, col};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void findExplorerNoExplorerFound() {
        int[][] island = {
            {2,3,2,3,3},
            {3,2,3,2,3},
            {2,3,4,3,3},
            {3,2,2,2,3},
            {2,3,2,2,3},
        };
         Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.findExplorer(island);
        });
        assertEquals("No explorer found", exception.getMessage()); 
    }

}
    

   
