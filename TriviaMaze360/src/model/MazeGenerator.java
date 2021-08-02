/*TCSS 360 Maze Project*/
package model;

import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Taylor Humfleet
 * @author Mason Hanson
 * @author Cordell Hampshire
 */
public class MazeGenerator {
    //default
    public MazeGenerator() {
        //default values may likely change
        this(3, 8, 12); //new Point(0, 0), new Point (4,4));
    }
    public MazeGenerator(int theMinPaths, int theMaxPaths, int theSize) {
        //avoid cycles
        Random ran = new Random();
        int paths = theMinPaths;
        paths += ran.nextInt(theMaxPaths - theMinPaths);
        //go straight 40%
        //go left 30%
        //go right 30%
        List<Room> traveledRooms= new ArrayList<Room>();
        Maze builder = new Maze(theSize);
        for (int i = 0; i < paths; i++) {
            createPath(builder);
        }
    }
    private void createPath(Maze theMaze, int theRow, int theColumn) {
        // TODO Auto-generated method stub
        Room start = theMaze.getMyEntrance();
        Room end = theMaze.getMyExit();
        int row = theRow;
        int col = theColumn;
        Random ran = new Random();
        int pick = ran.nextInt(4);
        while (theMaze.getRoom(theRow, theColumn)) {
        
    }

}
