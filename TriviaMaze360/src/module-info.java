module TriviaMaze360 {
    
	requires javafx.controls;
	requires java.desktop;
	requires java.sql;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;
    requires javafx.base;
	opens application to javafx.graphics, javafx.fxml;

}
