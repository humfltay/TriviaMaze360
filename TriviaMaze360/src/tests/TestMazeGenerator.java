/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Maze;

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
  void test() {
    fail("Not yet implemented");
  }

}
