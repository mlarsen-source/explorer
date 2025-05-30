import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    public void testReachableArea_AllReachable() {
        int[][] island = {
            {0,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(30, actual);
    }

    @Test
    public void testReachableArea_OnlyStartingLocationReachable() {
        int[][] island = {
            {3,3,3,3,3,3},
            {3,3,3,3,3,3},
            {3,3,3,3,3,3},
            {3,3,3,3,0,3},
            {3,3,3,3,3,3},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(1, actual);
    }

    @Test
    public void testReachableArea_OnlyBorderReachable() {
        int[][] island = {
            {1,1,1,1,1,1},
            {1,2,2,2,2,1},
            {1,2,3,3,2,1},
            {1,2,2,2,2,1},
            {1,1,0,1,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(18, actual);
    }

    @Test
    public void testReachableArea_OnlyDiagonalFields() {
        int[][] island = {
            {2,1,2,1,2,1},
            {1,2,1,2,1,2},
            {2,1,2,1,2,1},
            {1,2,1,2,1,2},
            {2,1,2,1,2,0},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(1, actual);
    }

    @Test
    public void testReachableArea_NoExplorer() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,3,3,3},
            {1,1,1,2,3,1},
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.reachableArea(island);
        });
        assertEquals("No explorer found", exception.getMessage()); 
    }

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
    
    @Test
    public void testPossibleToExploreCenterStartAllDirectionsOpen() {
        int[][] island = {
            {1,1,1,1,1},
            {1,1,1,1,1},
            {1,1,0,1,1},
            {1,1,1,1,1},
            {1,1,1,1,1}
        };
        int[] explorer = {2, 2};
        List<int[]> explorable = ExplorerSearch.possibleToExplore(island, explorer);
        Set<String> explorableSet = toSet(explorable);
        assertEquals(5, explorable.size());
        assertTrue(explorableSet.contains("2,2")); // start
        assertTrue(explorableSet.contains("1,2")); // up
        assertTrue(explorableSet.contains("3,2")); // down
        assertTrue(explorableSet.contains("2,1")); // left
        assertTrue(explorableSet.contains("2,3")); // right
    }

    @Test
    public void testPossibleToExploreCenterStartAllWater() {
        int[][] island = {
            {2,2,2,2,2},
            {2,2,2,2,2},
            {2,2,0,2,2},
            {2,2,2,2,2},
            {2,2,2,2,2}
        };
        int[] explorer = {2, 2};
        List<int[]> explorable = ExplorerSearch.possibleToExplore(island, explorer);
        Set<String> explorableSet = toSet(explorable);
        assertEquals(1, explorable.size());
        assertTrue(explorableSet.contains("2,2")); // start
    }

    @Test
    public void testPossibleToExploreCenterStartAllMountains() {
        int[][] island = {
            {3,3,3,3,3},
            {3,3,3,3,3},
            {3,3,0,3,3},
            {3,3,3,3,3},
            {3,3,3,3,3}
        };
        int[] explorer = {2, 2};
        List<int[]> explorable = ExplorerSearch.possibleToExplore(island, explorer);
        Set<String> explorableSet = toSet(explorable);
        assertEquals(1, explorable.size());
        assertTrue(explorableSet.contains("2,2")); // start
    }

    @Test
    public void testPossibleToExploreCenterStarUpAndDownOnly() {
        int[][] island = {
            {3,3,1,3,3},
            {3,3,1,3,3},
            {3,3,0,3,3},
            {3,3,1,3,3},
            {3,3,1,3,3}
        };
        int[] explorer = {2, 2};
        List<int[]> explorable = ExplorerSearch.possibleToExplore(island, explorer);
        Set<String> explorableSet = toSet(explorable);
        assertEquals(3, explorable.size());
        assertTrue(explorableSet.contains("2,2")); // start
        assertTrue(explorableSet.contains("1,2")); // up
        assertTrue(explorableSet.contains("3,2")); // down
    }

    @Test
    public void testPossibleToExploreCenterStartLeftAndRightOnly() {
        int[][] island = {
            {3,3,3,3,3},
            {3,3,3,3,3},
            {1,1,0,1,1},
            {3,3,3,3,3},
            {3,3,3,3,3}
        };
        int[] explorer = {2, 2};
        List<int[]> explorable = ExplorerSearch.possibleToExplore(island, explorer);
        Set<String> explorableSet = toSet(explorable);
        assertEquals(3, explorable.size());
        assertTrue(explorableSet.contains("2,2")); // start
        assertTrue(explorableSet.contains("2,1")); // left
        assertTrue(explorableSet.contains("2,3")); // right
    }
    
    @Test
    public void testPossibleToExploreTopLeftStartRightOnly() {
        int[][] island = {
            {0,1,1,1,1},
            {3,3,3,3,3},
            {3,3,3,3,3},
            {3,3,3,3,3},
            {3,3,3,3,3}
        };
        int[] explorer = {0, 0};
        List<int[]> explorable = ExplorerSearch.possibleToExplore(island, explorer);
        Set<String> explorableSet = toSet(explorable);
        assertEquals(2, explorable.size());
        assertTrue(explorableSet.contains("0,0")); // start
        assertTrue(explorableSet.contains("0,1")); // right
    }
    
    @Test
    public void testPossibleToExploreTopRightStartLeftOnly() {
        int[][] island = {
            {1,1,1,1,0},
            {3,3,3,3,3},
            {3,3,3,3,3},
            {3,3,3,3,3},
            {3,3,3,3,3}
        };
        int[] explorer = {0, 4};
        List<int[]> explorable = ExplorerSearch.possibleToExplore(island, explorer);
        Set<String> explorableSet = toSet(explorable);
        assertEquals(2, explorable.size());
        assertTrue(explorableSet.contains("0,4")); // start
        assertTrue(explorableSet.contains("0,3")); // left
    }
    
    @Test
    public void testPossibleToExploreBottomLeftStartRightOnly() {
        int[][] island = {
            {3,3,3,3,3},
            {3,3,3,3,3},
            {3,3,3,3,3},
            {3,3,3,3,3},
            {0,1,1,1,1}
        };
        int[] explorer = {4, 0};
        List<int[]> explorable = ExplorerSearch.possibleToExplore(island, explorer);
        Set<String> explorableSet = toSet(explorable);
        assertEquals(2, explorable.size());
        assertTrue(explorableSet.contains("4,0")); // start
        assertTrue(explorableSet.contains("4,1")); // right
    }

    @Test
    public void testPossibleToExploreBottomRightStartLeftOnly() {
        int[][] island = {
            {3,3,3,3,3},
            {3,3,3,3,3},
            {3,3,3,3,3},
            {3,3,3,3,3},
            {1,1,1,1,0}
        };
        int[] explorer = {4, 4};
        List<int[]> explorable = ExplorerSearch.possibleToExplore(island, explorer);
        Set<String> explorableSet = toSet(explorable);
        assertEquals(2, explorable.size());
        assertTrue(explorableSet.contains("4,4")); // start
        assertTrue(explorableSet.contains("4,3")); // left
    }

    private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
}