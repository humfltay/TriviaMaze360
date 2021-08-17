package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Maze;
import model.MazeGenerator;
import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;
import model.Room;

class MazeTests {
  Maze tester;
  Maze genMaze;
  @BeforeEach
  void setUp() throws Exception {
    tester = new Maze();
    MazeGenerator m = new MazeGenerator();
    genMaze = m.getMaze();
  }

  @Test
  void testIsWinnable() {
    
    for (int i = 1; i < 15; i++) {
      MazeGenerator m = new MazeGenerator();
      genMaze = m.getMaze();
      boolean win = genMaze.isWinnable();
      assertTrue(win,"Checking to see if newly generated maze is winnable");
    }
    boolean lose = tester.isWinnable();
    assertFalse(lose, "Checking to see if empty maze will fail");
  }
  @Test
  void testOpenDoors() {
    Maze m = new Maze(3);
    m.openDoor(DoorDirection.SOUTH, 1, 1, DoorStatus.OPEN);
    System.out.println(m);
    assertTrue(m.getRoom(2, 1).getMyNorthDoor().getMyDoorStatus() == DoorStatus.OPEN);
    
    m.openDoor(DoorDirection.NORTH, 2, 2, DoorStatus.OPEN);
    System.out.println(m);
    assertTrue(m.getRoom(1, 2).getMySouthDoor().getMyDoorStatus() == DoorStatus.OPEN);
    
    m.openDoor(DoorDirection.WEST, 2, 2, DoorStatus.OPEN);
    System.out.println(m);
    assertTrue(m.getRoom(2, 1).getMyEastDoor().getMyDoorStatus() == DoorStatus.OPEN);
    
    m.openDoor(DoorDirection.EAST, 2, 2, DoorStatus.OPEN);
    System.out.println(m);
    assertTrue(m.getRoom(2, 3).getMyWestDoor().getMyDoorStatus() == DoorStatus.OPEN);
    
    
  }
  @Test
  void testGoRight() {
    MazeGenerator m = new MazeGenerator(0,0,4);
    assertTrue(m.goRight(DoorDirection.NORTH) == DoorDirection.EAST);
    assertTrue(m.goRight(DoorDirection.EAST) == DoorDirection.SOUTH);
    assertTrue(m.goRight(DoorDirection.SOUTH) == DoorDirection.WEST);
    assertTrue(m.goRight(DoorDirection.WEST) == DoorDirection.NORTH);
    
    
  }
  @Test
  void testGoLeft() {
    MazeGenerator m = new MazeGenerator(0,0,4);
    assertTrue(m.goLeft(DoorDirection.NORTH) == DoorDirection.WEST);
    assertTrue(m.goLeft(DoorDirection.WEST) == DoorDirection.SOUTH);
    assertTrue(m.goLeft(DoorDirection.SOUTH) == DoorDirection.EAST);
    assertTrue(m.goLeft(DoorDirection.EAST) == DoorDirection.NORTH);
    
    
  }
  @Test
  void testLookInDirection() {
    MazeGenerator m = new MazeGenerator(0,0,4);
    Room southRoom = m.lookInDirection(DoorDirection.SOUTH, 1, 1);
    assertTrue(southRoom.getMyRow() == 2 && southRoom.getMyCol() == 1);
    
    Room northRoom = m.lookInDirection(DoorDirection.NORTH, 2, 1);
    assertTrue(northRoom.getMyRow() == 1 && northRoom.getMyCol() == 1);
    
    Room westRoom = m.lookInDirection(DoorDirection.WEST, 2, 2);
    assertTrue(westRoom.getMyRow() == 2 && westRoom.getMyCol() == 1);
    
    Room eastRoom = m.lookInDirection(DoorDirection.EAST, 2, 2);
    assertTrue(eastRoom.getMyRow() == 2 && eastRoom.getMyCol() == 3);
    
  }
  @Test
  void testGetDirect() {
    MazeGenerator m = new MazeGenerator(0,0,4);
    Maze maze = m.getMaze();
    maze.setEntrance(4, 1);
    maze.setExit(1, 1);
    Room entr = maze.getMyEntrance();
   
    DoorDirection straight = m.getDirect(entr);
    assertEquals(DoorDirection.NORTH, straight, "We should be going straight due to our points");
  }
  void testGetPath() {
    
  }
  @Test
  void testMaze() {
    Maze mazerzsky = new Maze();
    System.out.println(mazerzsky);
  }

}