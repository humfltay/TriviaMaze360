package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Maze;
import model.MazeGenerator;
import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;
import model.Room;
/**
 * @author Taylor, Cordel
 *
 */
class MazeTests {
    Maze tester;
    Maze genMaze;
    /**
     * @throws Exception java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        tester = new Maze(4, 1, DoorStatus.INACTIVE);
        MazeGenerator m = new MazeGenerator();
        genMaze = m.getMaze();
    }
    /**
     * Test method for {@link model.Maze#isWinnable(Room)}
     */
    @Test
    void testIsWinnable() {
        for (int i = 1; i < 15; i++) {
            MazeGenerator m = new MazeGenerator();
            genMaze = m.getMaze();
            boolean win = genMaze.isWinnable(genMaze.getMyEntrance());
            assertTrue(win,"Checking to see if newly generated maze is winnable");
        }
        boolean lose = tester.isWinnable(genMaze.getMyEntrance());
        assertFalse(lose, "Checking to see if empty maze will fail");
    }
    /**
     * Test method for {@link model.Maze#setOppositeDoorToSame(DoorDirection, Room)}
     */
    @Test
    void testSetOppositeDoorToSame() {
        Maze maze = new Maze(3);
        Room room = maze.getRoom(2, 2);
        room.getMyDoor(DoorDirection.EAST).setMyDoorStatus(DoorStatus.OPEN);
        assertFalse(maze.getRoom(2, 3).getMyDoor(DoorDirection.WEST).getMyDoorStatus() == DoorStatus.OPEN);
        maze.setOppositeDoorToSame(DoorDirection.EAST, room);
        assertTrue(maze.getRoom(2, 3).getMyDoor(DoorDirection.WEST).getMyDoorStatus() == DoorStatus.OPEN);
      
        room.getMyDoor(DoorDirection.WEST).setMyDoorStatus(DoorStatus.OPEN);
        assertFalse(maze.getRoom(2, 1).getMyDoor(DoorDirection.EAST).getMyDoorStatus() == DoorStatus.OPEN);
        maze.setOppositeDoorToSame(DoorDirection.WEST, room);
        assertTrue(maze.getRoom(2, 1).getMyDoor(DoorDirection.EAST).getMyDoorStatus() == DoorStatus.OPEN);
      
        room.getMyDoor(DoorDirection.NORTH).setMyDoorStatus(DoorStatus.OPEN);
        assertFalse(maze.getRoom(1, 2).getMyDoor(DoorDirection.SOUTH).getMyDoorStatus() == DoorStatus.OPEN);
        maze.setOppositeDoorToSame(DoorDirection.NORTH, room);
        assertTrue(maze.getRoom(1, 2).getMyDoor(DoorDirection.SOUTH).getMyDoorStatus() == DoorStatus.OPEN);
      
        room.getMyDoor(DoorDirection.SOUTH).setMyDoorStatus(DoorStatus.OPEN);
        assertFalse(maze.getRoom(3, 2).getMyDoor(DoorDirection.NORTH).getMyDoorStatus() == DoorStatus.OPEN);
        maze.setOppositeDoorToSame(DoorDirection.SOUTH, room);
        assertTrue(maze.getRoom(3, 2).getMyDoor(DoorDirection.NORTH).getMyDoorStatus() == DoorStatus.OPEN);
    }
    /**
     * Test method for {@link model.Maze#getMyMazeSize()}
     */
    @Test
    void testGetMyMazeSize() {
        assertEquals(4, tester.getMyMazeSize(), "getMyMazeSize failed.");
    }
    
    void testGetPath() {
        
    }
    @Test
    void testMaze() {
        Maze mazerzsky = new Maze();
        System.out.println(mazerzsky);
    }
}