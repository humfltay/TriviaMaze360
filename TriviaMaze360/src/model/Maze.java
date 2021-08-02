package model;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Random;

import model.Room;

public class Maze {
    private enum Themes {DEFAULT, POKEMON, SPOOKY, COMEDY, HISTORY};
    
    private Room[][] myRooms;
    private int myMazeSize;
    private Room myEntrance;
    private Room myExit;
    private boolean myWinnable;
    private int myTheme;
    
    public Maze() {
        myRooms = new Room[myMazeSize+2][myMazeSize+2];
        myMazeSize = 4;
        createSimpleMaze();
        myWinnable = true;
    }
    public Maze(int theSize, int theTheme) {
        myRooms = new Room[theSize + 2][myMazeSize + 2];
        myTheme = theTheme;
        buildMaze(theSize);
    }
    public Maze(int theSize) {
        myRooms = new Room[theSize + 2][myMazeSize + 2];
        myTheme = Themes.valueOf("DEFAULT").ordinal();
        buildMaze(theSize);
    }
    //implement Maze constructors and maze generator method.
    private void createSimpleMaze() {
        for (int i = 1; i <= myMazeSize; i++) {
            for (int j = 1; j <= myMazeSize; j++) {
                //int rand = new Random().nextInt(4);
                myRooms[i][j] = new Room(i, j);
                
            }
        }
        myEntrance = new Room(1,1);
        myRooms[1][1] = myEntrance;
        myExit = new Room(4, 4);
        myRooms[4][4] = myExit;
    }
    private void buildMaze(int theSize) {
        //edges set to null currently
        for (int i = 1; i <= theSize; i++) {
            for (int j = 1; j <= theSize; j++) {
                //int rand = new Random().nextInt(4);
                myRooms[i][j] = new Room(i, j);
                
            }
        }
        myEntrance = new Room(1,1);
        myRooms[1][1] = myEntrance;
        myExit = new Room(4, 4);
        myRooms[4][4] = myExit;
    }
    
    private void createRandomMaze() {
        for (int i = 1; i <= myMazeSize; i++) {
            Random num = new Random();
            for (int j = 1; j < myMazeSize; j++) {
                myRooms[i][j] = new Room(i, j);
            }
        }
    }
    
    public Room[][] getMyRooms() {
        return myRooms;
    }
    public int getMyMazeSize() {
        return myMazeSize;
    }
    public Room getMyEntrance() {
        return myEntrance;
    }
    public Room getMyExit() {
        return myExit;
    }
    public boolean isWinnable() {
        return myWinnable;
    }
    
    public void setWinnable() {
         //Needs to be implemented still.
    }
    public Room getRoom(final int theRow, final int theCol) throws IndexOutOfBoundsException {
        if (isValid(theRow, theCol)) {
            return myRooms[theRow][theCol];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    public boolean isValid(final int theRow, final int theCol) {
        return (theRow > 0 && theRow < myMazeSize) && (theCol > 0 && theCol < myMazeSize);
    }
    
    public static void main(final String[] theArgs) {
        System.out.println("It runs.");
    }
    public void goNorth() {
        //getRoom();
        
    }
}
