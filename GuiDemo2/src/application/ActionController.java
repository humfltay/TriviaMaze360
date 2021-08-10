package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ActionController {
	
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
	 * String to hold the path to a music file.
	 */
	String path = "mario3.mp3";
	
	/**
	 * Media object to hold the music.
	 */
	Media media = new Media(new File(path).toURI().toString());
	
	/**
	 * MediaPlayer to be able to play the music.
	 */
	MediaPlayer mp = new MediaPlayer(media);
	
	/*
	public static MediaPlayer getMp() {
		return mp;
	}
	*/
	
	/**
	 * The pane that most of the elements in MainGame.fxml sit on top of.
	 */
	@FXML
    private AnchorPane anchor;

    /**
     * Switches the scene back to the play screen.
     */
    @FXML
    private Button playBtn;

    /**
     * Switches the scene to the settings screen.
     */
    @FXML
    private Button SetttingsBtn;
    
    /**
     * Displays the player's name.
     */
    @FXML
    public Label playerNameLabel;

    /**
     * Displays the player's score.
     */
    @FXML
    private Label playerScoreLabel;
    
    /**
     * All dPad directions are invisible buttons overlaid on top of dPad.png.
     * 
     * Denotes movement to the right.
     */
    @FXML
    private Button dPadRight;

    /**
     * Denotes upwards movement.
     */
    @FXML
    private Button dPadUp;

    /**
     * Denotes movement to the left.
     */
    @FXML
    private Button dPadLeft;

    /**
     * Denotes downwards movement.
     */
    @FXML
    private Button dPadDown;
    
    /**
     * Reports the last button that has been pressed.
     * Used for testing purposes.
     */
    @FXML
    private Label lastPressedLabel;
    
    /**
     * @param event when dPadUp is pressed
     */
    @FXML
    void up(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadUp");
    }
    
    /**
     * @param event when dPadDown is pressed
     */
    @FXML
    void down(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadDown");
    }

    /**
     * @param event when dPadLeft is pressed
     */
    @FXML
    void left(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadLeft");
    }

    /**
     * @param event when dPadRight is pressed
     */
    @FXML
    void right(ActionEvent event) {
    	lastPressedLabel.setText("Last Pressed: dPadRight");
    }
    
    /**
     * @param event when the "play" button is pressed.
     */
    @FXML
    void switchToPlay(ActionEvent event) {
    	//pretty sure ima get rid of this because pressing this should return to the play screen
    	//and if the program is reading this controller then it is already on the play screen
    }

    /**
     * Switches the scene to the settings screen.
     * 
     * @param event when the "settings" button is pressed
     * @throws IOException
     */
    @FXML
    void switchToSettings(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("settings.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

	/**
	 * In javafx the initialize method runs after all of the fxml elements have been loaded.
	 * This method will always be called after the scene fully loads.
	 */
	@FXML
	public void initialize() {
		//sets player name label
		playerNameLabel.setText(SceneController.getPlayerName() + ":");
		
		//plays music
		mp.play();
	}
	
}
