package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Maze;
import model.MazeGenerator;

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
    
    for (int i = 1; i < 100; i++) {
      MazeGenerator m = new MazeGenerator();
      genMaze = m.getMaze();
      boolean win = genMaze.isWinnable();
      assertTrue(win,"Checking to see if newly generated maze is winnable");
    }
    boolean lose = tester.isWinnable();
    assertFalse(lose, "Checking to see if empty maze will fail");
  }

}
