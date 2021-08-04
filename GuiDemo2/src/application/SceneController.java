package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController {
	
	public static String playerName;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
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
	}
	
	@FXML
    private Button newGameBtn;

    @FXML
    void newGame(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("NewGame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    @FXML
    private TextField nameField;

    @FXML
    private Button confirmBtn;
    
    @FXML
    void startGame(ActionEvent event) throws IOException {
    	playerName = nameField.getText();
    	
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
    private Button LoadGameBtn;

    @FXML
    void LoadGame(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("LoadGame.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
	
}
