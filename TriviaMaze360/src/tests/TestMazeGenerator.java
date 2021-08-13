/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Maze;
import model.MazeGenerator;
import model.RealDoor.DoorDirection;

/**
 * @author thehu
 *
 */
class TestMazeGenerator {
  private static Maze myTestMaze;
  /**
   * @throws java.lang.Exception
   */
  
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    myTestMaze = new Maze(10,10);
  }

  @Test
  void testGoLeft() {
    DoorDirection d = DoorDirection.NORTH;
    MazeGenerator m = new MazeGenerator(1,1,5);
    d = m.goLeft(m.goLeft(d));
    assertEquals(d, DoorDirection.SOUTH);
  }
  @Test
  void test() {
    fail("Not yet implemented");
  }

}
