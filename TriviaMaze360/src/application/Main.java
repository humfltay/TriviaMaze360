package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;


public class Main extends Application { 
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			
			Parent root = FXMLLoader.load(getClass().getResource("play.fxml"));
			Scene scene = new Scene(root);
			
			//might want to create a new default style sheet
			scene.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Gui Demo 2");
			
			Image image = new Image("file:questionIcon.png");
			primaryStage.getIcons().add(image);
			primaryStage.setResizable(false);
			
			
			//removes the boarder- might want to implement a custom one with full screen option
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}