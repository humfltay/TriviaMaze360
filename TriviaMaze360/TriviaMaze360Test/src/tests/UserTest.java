/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import model.MazeGenerator;
import model.RealDoor;
import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;
import model.User;

/**
 * Completed tests for User class.
 * @author Cordel
 */
class UserTest {
    User user1;
    MazeGenerator MG = new MazeGenerator();
    User user2;
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
        user1 = new User();
        user2 = new User(MG.getMaze());
    }

    /**
     * Test method for {@link model.User#getMyRow()}.
     */
    @Test
    void testGetMyRow() {
        assertEquals(user1.getMyMaze().getMyEntrance().getMyRow(),
                user1.getMyRow(), "getMyRow failed");
        assertEquals(MG.getMaze().getMyEntrance().getMyRow(),
                user2.getMyRow(), "getMyRow failed");
    }

    /**
     * Test method for {@link model.User#getMyCol()}.
     */
    @Test
    void testGetMyCol() {
        assertEquals(user1.getMyMaze().getMyEntrance().getMyCol(),
                user1.getMyCol(), "getMyRow failed");
        assertEquals(MG.getMaze().getMyEntrance().getMyCol(),
                user2.getMyCol(), "getMyRow failed");
    }

    /**
     * Test method for {@link model.User#getMyMaze()}.
     */
    @Test
    void testGetMyMaze() {
        assertEquals(MG.getMaze(), user2.getMyMaze(),
                "getMyMaze failed");
    }

    /**
     * Test method for {@link model.User#getMyRoom()}.
     */
    @Test
    void testGetMyRoom() {
        assertEquals(user1.getMyMaze().getMyEntrance(), 
                user1.getMyRoom(), "getMyRoom failed");
        assertEquals(MG.getMaze().getMyEntrance(),
                user2.getMyRoom(), "getMyRoom failed");
    }

    /**
     * Test method for {@link model.User#move(model.RealDoor)}.
     */
    @Test
    void testMove() {
        //Testing boundary room.
        assertEquals(user1.getMyMaze().getMyEntrance(), 
                user1.move(user1.getMyRoom().getMyDoor(DoorDirection.WEST)), "move failed.");
        //Testing movement through a closed door.
        assertEquals(user1.getMyMaze().getRoom(user1.getMyRow(), user1.getMyCol() + 1), 
                user1.move(user1.getMyRoom().getMyDoor(DoorDirection.EAST)), "move failed.");
        assertEquals(user1.getMyMaze().getRoom(user1.getMyRow() + 1, user1.getMyCol()), 
                user1.move(user1.getMyRoom().getMyDoor(DoorDirection.SOUTH)), "move failed.");
    }
    /**
     * Test method for {@link model.User#move(model.RealDoor)}.
     */
    @Test
    void testMoveNull() {
        //Testing an open door that leads to a null room.
        user1.getMyRoom().getMyDoor(DoorDirection.NORTH).setMyDoorStatus(DoorStatus.OPEN);
        assertEquals(user1.getMyMaze().getMyEntrance(), 
                user1.move(user1.getMyRoom().getMyDoor(DoorDirection.NORTH)), "move failed.");
        //Testing a null door.
        assertEquals(user1.getMyMaze().getMyEntrance(), user1.move(null), "move failed.");

        //Testing door with null direction.
        //RealDoor door = new RealDoor(null);
        //assertThrows(NullPointerException.class, () -> user1.move(door), "move failed.");
        //Testing door with null status.
        //RealDoor door2 = new RealDoor(DoorDirection.EAST);
        //door2.setMyDoorStatus(null);
        //assertThrows(NullPointerException.class, () -> user1.move(door2), "move failed.");

    }
}
