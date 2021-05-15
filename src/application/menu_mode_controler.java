package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class menu_mode_controler {

    @FXML
    private AnchorPane menu_window;

    @FXML
    private RadioButton p1Rond;

    @FXML
    private ToggleGroup p1Choice;

    @FXML
    private RadioButton P1Croix;

    @FXML
    void loadSoloMode(MouseEvent event) {

		try {
			AnchorPane window = FXMLLoader.load(getClass().getResource("ecran_menu_solo_mode.fxml"));
			menu_window.getChildren().setAll(window);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void loadDuoMatch(MouseEvent event) {

		try {
			AnchorPane window = FXMLLoader.load(getClass().getResource("ecran_match.fxml"));
			menu_window.getChildren().setAll(window);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

}


