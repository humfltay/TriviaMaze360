package application;

import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Creates a popUp that allows the user to continue or cancel.
 * 
 * @author masonh6@uw.edu
 */
public class ConfirmBox {
	
	/**
	 * The choice selected by the user.
	 */
	static boolean choice;
	
	public static boolean popUp(String windowTitle, String message) throws IOException {
		Stage window = new Stage();
		Image image = new Image("file:questionPopUp.png");
		window.getIcons().add(image);
		window.setResizable(false);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(windowTitle);
		window.setMinWidth(500);
		Label label = new Label(message);
		
		Button continueBtn = new Button("Continue");
		Button cancelBtn = new Button("Cancel");
		
		continueBtn.setOnAction(e -> {
			choice = true;
			window.close();
		});
		
		cancelBtn.setOnAction(e -> {
			choice = false;
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, continueBtn, cancelBtn);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		
		window.setScene(scene);
		window.showAndWait();
		
		return choice;
	}
	
}
