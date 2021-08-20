/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.RealDoor;
import model.RealDoor.DoorDirection;
import model.RealDoor.DoorStatus;

/**
 * @author Cordel
 *
 */
class RealDoorTest {
    RealDoor doorOpen;
    RealDoor doorInactive;
    RealDoor doorFake;
    RealDoor doorClosed;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        doorOpen = new RealDoor(DoorDirection.NORTH, 
                DoorStatus.OPEN, 1);
        doorInactive = new RealDoor(DoorDirection.SOUTH,
                DoorStatus.INACTIVE, 1);
        doorFake = new RealDoor(DoorDirection.EAST,
                DoorStatus.FAKE, 1);
        doorClosed = new RealDoor(DoorDirection.WEST,
                DoorStatus.CLOSED, 1);
    }

    /**
     * Test method for {@link model.RealDoor#isPassable()}.
     */
    @Test
    void testIsPassable() {
        assertTrue(doorOpen.isPassable(), "isPassable failed.");
        assertFalse(doorInactive.isPassable(), "isPassable failed.");
        assertFalse(doorFake.isPassable(), "isPassable failed.");
        assertTrue(doorOpen.isPassable(), "isPassable failed.");
    }

    /**
     * Test method for {@link model.RealDoor#getMyDoorStatus()}.
     */
    @Test
    void testGetMyDoorStatus() {
       assertEquals(DoorStatus.OPEN, doorOpen.getMyDoorStatus());
    }

    /**
     * Test method for {@link model.RealDoor#getMyQuestion()}.
     */
    @Test
    void testGetMyQuestion() {
        assertEquals(doorClosed.getMyQuestion(), 
                doorClosed.getMyQuestion(), "askQuestion failed.");

    }

    /**
     * Test method for {@link model.RealDoor#getMyChoices()}.
     */
    @Test
    void testGetMyChoices() {
        //fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.RealDoor#setMyDoorStatus(model.RealDoor.DoorStatus)}.
     */
    @Test
    void testSetMyDoorStatus() {
        doorClosed.setMyDoorStatus(DoorStatus.FAKE);
        assertEquals(DoorStatus.FAKE, doorClosed.getMyDoorStatus(), 
                "setMyDoorStatus failed.");
    }

    /**
     * Test method for {@link model.RealDoor#getMyDoorDirection()}.
     */
    @Test
    void testGetMyDoorDirection() {
        assertEquals(DoorDirection.SOUTH, 
                doorInactive.getMyDoorDirection(), "getMyDoorDirection failed.");
    }

    /**
     * Test method for {@link model.RealDoor#getOppositeDirection()}.
     */
    @Test
    void testGetOppositeDirection() {
        assertEquals(DoorDirection.NORTH, doorInactive.getOppositeDirection(),
                "getOppositeDirection failed.");
        assertEquals(DoorDirection.SOUTH, doorOpen.getOppositeDirection(),
                "getOppositeDirection failed.");
        assertEquals(DoorDirection.WEST, doorFake.getOppositeDirection(),
                "getOppositeDirection failed.");
        assertEquals(DoorDirection.EAST, doorClosed.getOppositeDirection());
    }

}
