package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
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
	private Button mainBtn;
	
	@FXML
	private Button SettingsBtn;
	
	@FXML
	private Button playBtn;
	
	@FXML
	private Button ShowFiles;
	
	@FXML
	public void switchToMain(ActionEvent event) throws IOException {
	    root = FXMLLoader.load(getClass().getResource("MainGame.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
	@FXML
    private TextField nameField;
	
	/**present in NewGame.fxml
	 * Saves the player name and switches the scene to MainGame.fxml begining the game.
	 */
	@FXML
    private Button confirmBtn;
	
	@FXML
    private Button chooseFile;
	
	@FXML
	private AnchorPane anchor;
    @FXML
    private ListView<String> listview;
	
	@FXML
	private Slider vSlider;
	
	
	/*
	@FXML
	public void initialize() {
		vSlider.setValue(ActionController.getMp().getVolume() *100);
	}
	*/
	@FXML
    private Button resetBtn;
	
	
	public void switchToPlay(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("play.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

	public void switchToSettings(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("settings.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		//root.translateYProperty().set(scene.getHeight());
	}
	
	

    @FXML
    void newGame(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("NewGame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    @FXML
    void startGame(ActionEvent event) throws IOException {
    	
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
    
    public static String getPlayerName() {
    	return playerName;
    }

    @FXML
    void LoadGame(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("LoadGame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    @FXML
    void populateFileList(ActionEvent event) {
    	File folder = new File("SavedGames");
		File[] listOfFiles = folder.listFiles();

		listview.getItems().clear();
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    //System.out.println("File " + listOfFiles[i].getName());
			  listview.getItems().add(listOfFiles[i].getName());
		  } 
		}
    }
    
    @FXML
    void openFileSelection(ActionEvent event) {
    	
    	//populates the ListView with files in the saved game folder
    	//placing this code in the loadGame method does not work unfortunately
    	//eventually I will find a way to have this populate without pressing this button
    	File[] files = new File("SavedGames").listFiles();
    	
    	for(File file : files) {
    		if(file.isFile()) {
    			listview.getItems().add(file.getName());
    		}
    	}
    }
    
    @FXML
    void selectFile(ActionEvent event) throws IOException {
    	
    	//gets file path by appending the name of the selected file in the ListView to SavedGames folder
    	//currently untested
    	String fileName = listview.getFocusModel().getFocusedItem();
    	fileName = fileName.substring(0, fileName.length() - 4);
    	playerName = fileName;
    	//File toLoad = new File(fileNameAndPath);
    	
    	//prints the file path of the selected item in the ListView which we can use to load the save
    	//System.out.println(toLoad.getAbsolutePath());  
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGame.fxml"));
    	root = loader.load();
    	ActionController AC = (ActionController) loader.getController();
    	AC.loadGame(fileName);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    	
    }
	
}
