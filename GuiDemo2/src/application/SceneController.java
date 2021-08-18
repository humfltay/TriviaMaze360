package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;

public class SceneController {
	
	/**
	 * A String to store the player name.
	 */
	public static String playerName;
	
	/**
	 * A Stage to contain a scene.
	 */
	private Stage stage;
	
	/**
	 * A Scene to contain a pane or another element.
	 */
	private Scene scene;
	
	/**
	 * A root element to be attached to different FXML files.
	 */
	private Parent root;
	
	@FXML
	private AnchorPane backPane;
	
	@FXML
	private AnchorPane frontPane;
	
	Image otWars = new Image("file:otWars.jpg", true);
	
	Image stellaris = new Image("file:stellaris.jpg", true);
	
	Image dino = new Image("file:dino.jpg", true);
	
	Image bridge = new Image("file:waBridge.jpg", true);
	
	Image forest = new Image("file:waForest.jpg", true);
	
	Image lake = new Image("file:waLake.jpg", true);
	
	Image link = new Image("file:zeldaPaper.jpg", true);
	
	Image m3Level = new Image("file:m3Paper.png", true);
	
	/**present in play.fxml
	 * Changes the scene to NewGame.fxml which allows the player to enter a name
	 * and begin the game.
	 */
	@FXML
    private Button newGameBtn;
	
	/**present in play.fxml
	 * Changes the scene to LoadGame.fxml which allows the player to see the files
	 * in the SavedGames folder a select one to load.
	 */
	@FXML
    private Button LoadGameBtn;
	
	/**present in NewGame.fxml
	 * Allows the player to enter their name.
	 */
	@FXML
    private TextField nameField;
	
	/**present in NewGame.fxml
	 * Saves the player name and switches the scene to MainGame.fxml beginning the game.
	 */
	@FXML
    private Button confirmBtn;
	
	/**present in LaodGame.fxml
	 * Grabs the file the user has selected in the ListView.
	 */
	@FXML
    private Button chooseFile;

    /**present in LoadGame.fxml
     * Displays the files in the SavedGames folder for the user to choose from.
     */
    @FXML
    private ListView<String> listview;
	
	/**
	 * Switches the scene to play.fxml; the screen the program launches on.
	 * 
	 * @param event when the "play" button is pressed
	 * @throws IOException in case there is a problem loading the fxml
	 */
	public void switchToPlay(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("play.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
    /**
     * Switches the scene to NewGame.fxml.
     * 
     * @param event when newGameBtn is pressed
     * @throws IOException
     */
    @FXML
    void newGame(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("NewGame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    /**
     * Switches the scene to MainGame.fxml and saves the name entered by the player.
     * 
     * @param event when confirmBtn is pressed
     * @throws IOException
     */
    @FXML
    void startGame(ActionEvent event) throws IOException {
    	
    	if(nameField.getText().equals("") || nameField.getText().equals(null)) {
    		notificationBox.popUp("You Assign A Name to Play", "Please enter a name for your character");
    	} else {
    		//grabs the name entered by the player
        	playerName = nameField.getText();
        	
        	//switches to the scene that contains the main game play area
        	//this scene will also uses the ActionController class
        	root = FXMLLoader.load(getClass().getResource("MainGame.fxml"));
    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    		scene = new Scene(root);
    		stage.setScene(scene);
    		stage.show();
    	}
    }
    
    /**
     * Allows the name entered by the player to be accessible from ActionController.java.
     * 
     * @return playerName
     */
    public static String getPlayerName() {
    	return playerName;
    }

    /**
     * Switches the scene to LoadGame.fxml.
     * 
     * @param event when LoadGameBtn is pressed
     * @throws IOException
     */
    @FXML
    void LoadGame(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("LoadGame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    /**
     * Puts the files in the SavedGames folder into an array and populates the
     * list view with the files names.
     * 
     * @param event when the "Show Loadable Saves Files" button is pressed
     */
    @FXML
    void populateFileList(ActionEvent event) {
    	
    	//creates an array of the files in the SavedGames folder
    	File folder = new File("SavedGames");
		File[] listOfFiles = folder.listFiles();
		
		//clears any previous entries to the list view
		listview.getItems().clear();
		
		//traversed the array and populates the ListView
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    //System.out.println("File " + listOfFiles[i].getName());
			  listview.getItems().add(listOfFiles[i].getName());
		  } 
		}
    }
    
    /**
     * When the users presses the "choose file" button the path of the file that 
     * is focused in the ListView is saved in a string.
     * 
     * @param event when the "choose file" button is pressed
     */
    @FXML
    void selectFile(ActionEvent event) {
    	
    	//gets file path by appending the name of the selected file in the ListView to SavedGames folder
    	//currently untested
    	String fileNameAndPath = "SavedGames/" + listview.getFocusModel().getFocusedItem();
    	File toLoad = new File(fileNameAndPath);
    	
    	//prints the file path of the selected item in the ListView which we can use to load the save
    	System.out.println(toLoad.getAbsolutePath());
    }
    
    @FXML
    public void initialize() {
    	//uncomment the line below to see how elements are null until after the initialize method is ran
    	//listview.getItems().add("hello world");
    	//this is becuase it doesnt exist in all the fxml files that belong to this controller class
    	
    	//array of images to use as backgrounds
    	Image[] allBg = new Image[8];
    	allBg[0] = otWars;
    	allBg[1] = stellaris;
    	allBg[2] = dino;
    	allBg[3] = bridge;
    	allBg[4] = forest;
    	allBg[5] = lake;
    	allBg[6] = link;
    	allBg[7] = m3Level;
    	
    	//randomize the background image on each launch
    	randomizeBg(allBg);
    }
    
    void randomizeBg(Image[] iArray) {
    	//generate random number to pick from the available images
    	Random r = new Random();
    	int randomNum = r.nextInt(8);
    	
    	//set background image to whatever was randomly chosen
    	BackgroundImage myBi = new BackgroundImage(iArray[randomNum], BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
    			BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, false, false, true, true));
    	backPane.setBackground(new Background(myBi));
    }
}
