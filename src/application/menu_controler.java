package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class menu_controler implements Initializable{

	@FXML
	private AnchorPane menu_window;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		
	}
	
	public void loadTrain(ActionEvent event ) {
		try {
			AnchorPane train = FXMLLoader.load(getClass().getResource("test_fenetre.fxml"));
			menu_window.getChildren().setAll(train);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
