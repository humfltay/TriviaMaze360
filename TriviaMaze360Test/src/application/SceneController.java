package application;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Controller class for the initial menu that is shown when the game is launched.
 * Gives the user the option to begin a new game or load a previous one.
 * 
 * @author masonh6@uw.edu
 */
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
	
	/**
	 * AnchorPane to hold a background image.
	 */
	@FXML
	private AnchorPane backPane;
	
	/**
	 * AnchorPane to hold another background image.
	 */
	@FXML
	private AnchorPane frontPane;
	
	/**
	 * This Image object and the ones below hold the possible background images.
	 */
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
     * Notifies the player to place save files in the SavedGames folder.
     */
    @FXML
    private Button chooseFolderBtn;
    
	/**
	 * Switches the scene to play.fxml; the screen the program launches on.
	 * 
	 * @param event when the "play" button is pressed
	 * @throws IOException in case there is a problem loading the FXML
	 */
	public void switchToPlay(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("play.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
    /**
     * Switches the scene to NewGame.fxml where the player can enter their name.
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
    	
    	//requires the player to enter a name before the game can start
    	if(nameField.getText().equals("") || nameField.getText().equals(null)) {
    		notificationBox.popUp("You Must Assign A Name to Play", "Please enter a name for your character");
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
     * is focused in the ListView is saved and the corresponding file is loaded.
     * 
     * @param event when the "choose file" button is pressed
     * @throws IOException 
     */
    @FXML
    void selectFile(ActionEvent event) throws IOException {
    	
        //gets the selected save file from the ListView and sets the player name to the file name
        String fileName = listview.getFocusModel().getFocusedItem();
        fileName = fileName.substring(0, fileName.length() - 4);
        playerName = fileName;
        
        //loads the FXML that contains the main game and loads from the save file  
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGame.fxml"));
        root = loader.load();
        ActionController AC = (ActionController) loader.getController();
        AC.load(fileName);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void chooseFolder(ActionEvent event) throws IOException {
    	notificationBox.popUp("You are unable to select another folder to load from", "If you have a save file that is not in"
    			+ " the SavedGames folder move the file into it \nand press \"show loadable save files\"");
    }
    
    /**
     * Combines all possible background images into an array and passes it
     * to randomizeBg().
     */
    @FXML
    public void initialize() {
    	
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
    
    /**
     * Chooses a random image from the array that has been passed in
     * and sets it as a background for the proper pane.
     * 
     * @param iArray array of background images
     */
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
