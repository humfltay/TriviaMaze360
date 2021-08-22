package application;

import java.io.File;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Creates a popUp that allows the user to make a new save or overrite an old one.
 * 
 * @author masonh6@uw.edu
 */
public class SaveTool {
	
	//name of the file to be saved
    private static String myResult = "";
    
	public static String popUp(String windowTitle, String message) throws IOException {
		Stage window = new Stage();
		Image image = new Image("file:questionPopUp.png");
		window.getIcons().add(image);
		window.setResizable(false);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(windowTitle);
		//set size?
		window.setMinWidth(500);
		Label label = new Label(message);
		
		ListView<String> listview = new ListView<String>();
		
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
		
		listview.getItems().add("---Save game as a new file---");
		
		Label nl = new Label("Create a name for your save file");
		
		TextField tf = new TextField();
		
		Button saveBtn = new Button("Save");
		
		
		saveBtn.setOnAction(e -> {
			if(listview.getSelectionModel().getSelectedItem() == null) {
				try {
					notificationBox.popUp("Please Select A Save Option", "You must select a file to overwite or choose to save as a new file");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (listview.getSelectionModel().getSelectedItem() == "---Save game as a new file---"){
				String fileName = tf.getText();
				myResult = fileName + ".sav";
				//save the fileName appended to the path to the saved games folder
			} else {
				//remove this file
				String fileToRemove = (String) listview.getSelectionModel().getSelectedItem();
				//String fileName = tf.getText();
				myResult = fileToRemove;
				//save the fileName appended to the path to the saved games folder
			}
			window.close();
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, listview, nl, tf, saveBtn);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		
		window.setScene(scene);
		window.showAndWait();
		return myResult;
	}
	
}
