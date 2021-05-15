package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class match_controler {

    @FXML
    private AnchorPane menu_window;

    @FXML
    private RadioButton p1Rond;

    @FXML
    private ToggleGroup p1Choice;

    @FXML
    private RadioButton P1Croix;

    @FXML
    private ImageView menu_mode_solo_facile_button;

    @FXML
    private TextArea progresstext;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ImageView menu_mode_solo_moyen_button;

    @FXML
    private ImageView menu_mode_solo_difficile_button;

    @FXML
    void loadSoloDifficileMatch(MouseEvent event) {

		try {
			AnchorPane window = FXMLLoader.load(getClass().getResource("ecran_match.fxml"));
			menu_window.getChildren().setAll(window);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void loadSoloFacileMatch(MouseEvent event) {
		try {
			AnchorPane window = FXMLLoader.load(getClass().getResource("ecran_match.fxml"));
			menu_window.getChildren().setAll(window);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void loadSoloMoyenMatch(MouseEvent event) {
		try {
			AnchorPane window = FXMLLoader.load(getClass().getResource("ecran_match.fxml"));
			menu_window.getChildren().setAll(window);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
