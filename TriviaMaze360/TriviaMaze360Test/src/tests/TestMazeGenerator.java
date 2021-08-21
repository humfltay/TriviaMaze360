/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Maze;
import model.MazeGenerator;
import model.Room;
import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;
    
/**
 * @author Taylor Humfleet, Cordel Hampshire
 *
 */
class TestMazeGenerator {
    private Maze myTestMaze = new Maze(5, 5, DoorStatus.CLOSED);
    private MazeGenerator m = new MazeGenerator();
    /**
     * Test method for {@link model.MazeGenerator#goLeft(DoorDirection)}
     */
    @Test
    void testGoLeftTwice() {
        DoorDirection d = DoorDirection.NORTH;
        d = m.goLeft(m.goLeft(d));
        assertEquals(d, DoorDirection.SOUTH);
    }

    /**
     * Test method for {@link model.MazeGenerator#goRight(DoorDirection)}
     */
    @Test
    void testGoRight() {
        assertTrue(m.goRight(DoorDirection.NORTH) == DoorDirection.EAST);
        assertTrue(m.goRight(DoorDirection.EAST) == DoorDirection.SOUTH);
        assertTrue(m.goRight(DoorDirection.SOUTH) == DoorDirection.WEST);
        assertTrue(m.goRight(DoorDirection.WEST) == DoorDirection.NORTH); 
    }
    /**
     * Test method for {@link model.MazeGenerator#goLeft(DoorDirection)}
     */
    @Test
    void testGoLeft() {
        assertTrue(m.goLeft(DoorDirection.NORTH) == DoorDirection.WEST);
        assertTrue(m.goLeft(DoorDirection.WEST) == DoorDirection.SOUTH);
        assertTrue(m.goLeft(DoorDirection.SOUTH) == DoorDirection.EAST);
        assertTrue(m.goLeft(DoorDirection.EAST) == DoorDirection.NORTH);
        
    }
    /**
     * Test method for {@link model.MazeGenerator#getDirect()}
     */
    @Test
    void testGetDirect() {
        Maze maze = m.getMaze();
        maze.setEntrance(4, 1);
        maze.setExit(1, 1);
        Room entr = maze.getMyEntrance();
   
        DoorDirection straight = m.getDirect(entr);
        assertEquals(DoorDirection.NORTH, straight, "We should be going straight due to our points");
    }
}
