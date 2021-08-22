/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Maze;
import model.MazeGenerator;
import model.Monster;
import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;
import model.Room;
import model.User;

/**
 * @author Cordel
 */
class MonsterTest {
    private Maze myMaze;
    private MazeGenerator myGen;
    private Monster myMonster;
    private User myUser;

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        myMaze = new Maze();
        myGen = new MazeGenerator();
        myMaze = myGen.getMaze();
        myUser = new User();
        myMonster = new Monster(myUser, 5, "Test Monster");
    }

    /**
     * Test method for
     * {@link model.Monster#Monster(model.User, int, java.lang.String)}.
     */
    @Test
    void testMonster() {
        myMonster = new Monster(myUser, 5, "Test Monster");
        assertNotNull(myMonster, "Monster should not be null after declaration");
        assertEquals(myMonster.getMyWaitTurns(), 5, "We expect wait turns to be 5");
    }

    /**
     * Test method for {@link model.Monster#move()}.
     */
    @Test
    void testMove() {
        myMonster.move();
        myMonster.move();
        assertEquals(myMonster.getMyWaitTurns(), 3, "We expect wait turns to be 3 after two moves");
        while(!myMonster.isUserInRoom()) {
            myMonster.move();
        }
        assertEquals(myMonster.getMyRoom(), myUser.getMyRoom());
    }

    /**
     * Test method for {@link model.Monster#isWithinTwoRooms()}.
     */
    @Test
    void testIsWithinTwoRooms() {
        //move till we satisfy condition
        while(!myMonster.isWithinTwoRooms()) {
            myMonster.move();
        }
        int difCol = myMonster.getMyRoom().getMyCol() - myUser.getMyRoom().getMyCol();
        int difRow = myMonster.getMyRoom().getMyRow() - myUser.getMyRoom().getMyRow();
        assertTrue(difCol > -3 && difCol < 3, "Checking column differences");
        assertTrue(difRow > -3 && difRow < 3, "Checking row differences");
    }

    /**
     * Test method for {@link model.Monster#whereIsMonster()}.
     */
    @Test
    void testWhereIsMonster() {
        //random for maze generator
        DoorDirection direction = myMonster.whereIsMonster();
        assertNotNull(direction, "Direction should be returned non null");
        
    }

    /**
     * Test method for {@link model.Monster#getMyRoom()}.
     */
    @Test
    void testGetMyRoom() {
        Room monsRoom = myMonster.getMyRoom();
        assertTrue(myMaze.isValid(monsRoom.getMyRow(), monsRoom.getMyCol()), "Monster is in valid room");
        while (!myMonster.isUserInRoom()) {
            myMonster.move();
            assertTrue(myMaze.isValid(monsRoom.getMyRow(), monsRoom.getMyCol()), "Monster is still in valid room");
        }
    }

    /**
     * Test method for {@link model.Monster#getMyWaitTurns()}.
     */
    @Test
    void testGetMyWaitTurns() {
        myMonster = new Monster(myUser, 5, "Test Monster");
        assertEquals(myMonster.getMyWaitTurns(), 5, "Number of turns is correct");
        //5 moves
        myMonster.move();myMonster.move();myMonster.move();myMonster.move();myMonster.move();
        assertEquals(myMonster.getMyWaitTurns(), 0, "Number of turns is correct at 0 after five moves");
        myMonster.move();
        assertEquals(myMonster.getMyWaitTurns(), 0, "Should be 0 not -1");
        
    }

}