/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Maze;
import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;
import model.Room;
import model.User;

/**
 * @author Cordel
 *
 */
class RoomTest {
    Room roomInactive;
    Room roomOpen;
    Room roomClosed;
    Room roomFake;
    Maze maze;
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
        roomInactive = new Room(1, 1, DoorStatus.INACTIVE, 1);
        roomOpen = new Room(1, 1, DoorStatus.OPEN, 1);
        roomClosed = new Room(1, 2, DoorStatus.CLOSED, 1);
        roomFake = new Room(2, 1, DoorStatus.FAKE, 1);
        maze = new Maze();
    }

    /**
     * Test method for {@link model.Room#hashCode()}.
     */
    @Test
    void testHashCode() {
        assertEquals(Objects.hash(roomInactive.getMyRow(), 
                roomInactive.getMyCol()), roomInactive.hashCode(), "Hash code failed.");
    }

    /**
     * Test method for {@link model.Room#getMyDoor(model.RealDoor.DoorDirection)}.
     */
    @Test
    void testGetMyDoor() {
        assertEquals(roomInactive.getMyNorthDoor(), 
                roomInactive.getMyDoor(DoorDirection.NORTH), "getMyDoor failed.");
        assertEquals(roomInactive.getMyEastDoor(), 
                roomInactive.getMyDoor(DoorDirection.EAST), "getMyDoor failed.");
        assertEquals(roomInactive.getMyWestDoor(), 
                roomInactive.getMyDoor(DoorDirection.WEST), "getMyDoor failed.");
        assertEquals(roomInactive.getMySouthDoor(), 
                roomInactive.getMyDoor(DoorDirection.SOUTH), "getMyDoor failed.");
    }

    /**
     * Test method for {@link model.Room#getMyRow()}.
     */
    @Test
    void testGetMyRow() {
        assertEquals(1, roomInactive.getMyRow(), "getMyRow failed.");
    }

    /**
     * Test method for {@link model.Room#getMyCol()}.
     */
    @Test
    void testGetMyCol() {
        assertEquals(1, roomInactive.getMyCol(), "getMyCol failed.");
    }

    /**
     * Test method for {@link model.Room#getAccessableDoors()}.
     */
    @Test
    void testGetAccessableDoors() {
        assertFalse(roomInactive.getAccessableDoors().contains
                (roomInactive.getMyEastDoor()), "getAccessableDoors failed.");
        assertFalse(roomInactive.getAccessableDoors().contains
                (roomInactive.getMySouthDoor()), "getAccessableDoors failed.");
        assertFalse(roomInactive.getAccessableDoors().contains
                (roomInactive.getMyNorthDoor()), "getAccessableDoors failed.");
        assertFalse(roomInactive.getAccessableDoors().contains
                (roomInactive.getMyWestDoor()), "getAccessableDoors failed.");
        
        assertTrue(roomOpen.getAccessableDoors().contains
                (roomOpen.getMyEastDoor()), "getAccessableDoors failed.");
        assertTrue(roomOpen.getAccessableDoors().contains
                (roomOpen.getMySouthDoor()), "getAccessableDoors failed.");
        assertTrue(roomOpen.getAccessableDoors().contains
                (roomOpen.getMyNorthDoor()), "getAccessableDoors failed.");
        assertTrue(roomOpen.getAccessableDoors().contains
                (roomOpen.getMyWestDoor()), "getAccessableDoors failed.");
    }

    /**
     * Test method for {@link model.Room#getDoors()}.
     */
    @Test
    void testGetDoors() {
        assertTrue(maze.getMyEntrance().getDoors().contains
                (maze.getMyEntrance().getMyEastDoor()), "getDoors failed.");
        assertTrue(maze.getMyEntrance().getDoors().contains
                (maze.getMyEntrance().getMySouthDoor()), "getDoors failed.");
        assertFalse(maze.getMyEntrance().getDoors().contains
                (maze.getMyEntrance().getMyNorthDoor()), "getDoors failed.");
        assertFalse(maze.getMyEntrance().getDoors().contains
                (maze.getMyEntrance().getMyWestDoor()), "getDoors failed.");
        
        assertFalse(maze.getMyExit().getDoors().contains
                (maze.getMyExit().getMyEastDoor()), "getDoors failed.");
        assertFalse(maze.getMyExit().getDoors().contains
                (maze.getMyExit().getMySouthDoor()), "getDoors failed.");
        assertTrue(maze.getMyExit().getDoors().contains
                (maze.getMyExit().getMyNorthDoor()), "getDoors failed.");
        assertTrue(maze.getMyExit().getDoors().contains
                (maze.getMyExit().getMyWestDoor()), "getDoors failed.");

    }

    /**
     * Test method for {@link model.Room#getDoors(model.RealDoor.DoorStatus)}.
     */
    @Test
    void testGetDoorsDoorStatus() {
        assertTrue(maze.getMyEntrance().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyEntrance().getMyEastDoor()), "getDoors failed.");
        assertTrue(maze.getMyEntrance().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyEntrance().getMySouthDoor()), "getDoors failed.");
        assertFalse(maze.getMyEntrance().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyEntrance().getMyNorthDoor()), "getDoors failed.");
        assertFalse(maze.getMyEntrance().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyEntrance().getMyWestDoor()), "getDoors failed.");
        
        assertFalse(maze.getMyExit().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyExit().getMyEastDoor()), "getDoors failed.");
        assertFalse(maze.getMyExit().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyExit().getMySouthDoor()), "getDoors failed.");
        assertTrue(maze.getMyExit().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyExit().getMyNorthDoor()), "getDoors failed.");
        assertTrue(maze.getMyExit().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyExit().getMyWestDoor()), "getDoors failed.");
    }

    /**
     * Test method for {@link model.Room#setDoors(model.RealDoor.DoorStatus)}.
     */
    @Test
    void testSetDoors() {
        roomInactive.setDoors(DoorStatus.CLOSED);
        assertTrue(maze.getMyEntrance().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyEntrance().getMyEastDoor()), "getDoors failed.");
        assertTrue(maze.getMyEntrance().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyEntrance().getMySouthDoor()), "getDoors failed.");
        assertFalse(maze.getMyEntrance().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyEntrance().getMyNorthDoor()), "getDoors failed.");
        assertFalse(maze.getMyEntrance().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyEntrance().getMyWestDoor()), "getDoors failed.");
        
        assertFalse(maze.getMyExit().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyExit().getMyEastDoor()), "getDoors failed.");
        assertFalse(maze.getMyExit().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyExit().getMySouthDoor()), "getDoors failed.");
        assertTrue(maze.getMyExit().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyExit().getMyNorthDoor()), "getDoors failed.");
        assertTrue(maze.getMyExit().getDoors(DoorStatus.CLOSED).contains
                (maze.getMyExit().getMyWestDoor()), "getDoors failed.");

    }

    /**
     * Test method for {@link model.Room#isAccessable()}.
     */
    @Test
    void testIsAccessable() {
        assertTrue(roomOpen.isAccessable(), "isAccessable failed.");
        roomInactive.getMyEastDoor().setMyDoorStatus(DoorStatus.FAKE);
        assertFalse(roomInactive.isAccessable(), "isAccessable failed.");
        assertFalse(roomFake.isAccessable(), "isAccessable failed.");
    }

    /**
     * Test method for {@link model.Room#getMyVisited()}.
     */
    @Test
    void testGetMyVisited() {
        assertFalse(roomOpen.getMyVisited(), "getMyVisited failed.");
        
    }

    /**
     * Test method for {@link model.Room#setMyVisited(boolean)}.
     */
    @Test
    void testSetMyVisited() {
        roomOpen.setMyVisited(true);
        assertTrue(roomOpen.getMyVisited(), "setMyVisited failed.");
    }

    /**
     * Test method for {@link model.Room#toString()}.
     */
    @Test
    void testToString() {
        assertEquals("1, 1", roomOpen.toString(), "toString failed.");
    }

    /**
     * Test method for {@link model.Room#equals(java.lang.Object)}.
     */
    @Test
    void testEqualsObjectFailure() {
        assertNotEquals(roomOpen, null, "equals failed.");
        assertNotEquals(roomOpen, "1, 1", "equals failed.");
        assertNotEquals(roomOpen, roomClosed, "equals failed.");
        assertNotEquals(roomOpen, roomFake, "equals failed.");
    }
    
    /**
     * Test method for {@link model.Room#equals(java.lang.Object)}.
     */
    @Test
    void testEqualsObjectSuccess() {
        assertEquals(roomOpen, roomOpen, "equals failed.");
        assertEquals(roomOpen, roomInactive, "equals failed.");
    }

}
