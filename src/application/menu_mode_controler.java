package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
    private AnchorPane help_window;

    @FXML
    private Button help_close_button;
	
	@FXML
	public void openHelp(MouseEvent event) {
		
		help_window.setVisible(true);
		
	}
	
    @FXML
    void closeHelp(MouseEvent event) {

    	help_window.setVisible(false);
    	
    }
    
    @FXML
    void loadSoloMode(MouseEvent event) {

		try {
			AnchorPane window = FXMLLoader.load(getClass().getResource("ecran_menu_solo_mode.fxml"));
			if(p1Rond.isArmed()) {
				match_controler.piece=2;
				match_controler.piece_img="rond";
			}
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
			match_controler.is2p=true;
			menu_window.getChildren().setAll(window);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

}


