package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
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
			
			//might want to remove this
			scene.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Gui Demo 2");
			//ImageView iv = new ImageView(getClass().getResource("questionIcon.png").toExternalForm());
			//primaryStage.getIcons().add(new Image ("/GuiDemo2/questionIcon.png"));
			
			//Image image = new Image("questionIcon.png");
			//primaryStage.getIcons().add(image);
			primaryStage.setResizable(false);
			//primaryStage.set maybe make custom boarder?
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
