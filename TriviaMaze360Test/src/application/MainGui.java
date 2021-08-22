package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;


/**
 * This class launches the program and loads the necessary FXML file and style sheet.
 * 
 * @author masonh6@uw.edu
 */
public class MainGui extends Application { 
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//loads the starting FXML file
			Parent root = FXMLLoader.load(getClass().getResource("play.fxml"));
			Scene scene = new Scene(root);
			
			//loads the style sheet used for the starting menu
			scene.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
			
			//sets the title and scene
			primaryStage.setScene(scene);
			primaryStage.setTitle("The Best Trivia Maze Of All Time");
			
			//sets the program icon
			Image image = new Image("file:questionIcon.png");
			primaryStage.getIcons().add(image);
			
			//prevents the game window from being resizable
			primaryStage.setResizable(false);
			
			//allows the program to be visible
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Launches the GUI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}