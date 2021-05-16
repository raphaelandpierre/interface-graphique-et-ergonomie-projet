package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class menu_home_controler implements Initializable{


    @FXML
    private AnchorPane menu_window;

    @FXML
    private Button train_menu_button;

    @FXML
    private Text Titre;

    @FXML
    private ImageView menu_home_start_button;
	
    @FXML
    private AnchorPane help_window;

    @FXML
    private Button help_close_button;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		
	}
	
	@FXML
	public void openHelp(MouseEvent event) {
		
		help_window.setVisible(true);
		
	}
	
    @FXML
    void closeHelp(MouseEvent event) {

    	help_window.setVisible(false);
    	
    }
	
	@FXML
	public void loadSavedGame(MouseEvent event ) {
		try {
			AnchorPane window = FXMLLoader.load(getClass().getResource("ecran_jeu.fxml"));
			menu_window.getChildren().setAll(window);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void loadModeSelection(MouseEvent event ) {
		try {
			AnchorPane window = FXMLLoader.load(getClass().getResource("ecran_menu_mode.fxml"));
			menu_window.getChildren().setAll(window);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
