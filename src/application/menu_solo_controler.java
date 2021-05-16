package application;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import ai.Coup;
import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import ai.Test;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class menu_solo_controler {

	
	public String path = System.getProperty("user.dir");
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
    
    
    AnchorPane window_ai = null;
    
    CountDownLatch latch = new CountDownLatch(1); 
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
			window_ai=FXMLLoader.load(getClass().getResource("ecran_match.fxml"));
			AnchorPane window = FXMLLoader.load(getClass().getResource("ecran_match.fxml"));
			ChangeListener<String> message = new ChangeListener<String>() {

	    		@Override
	    		public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
	    			// TODO Auto-generated method stub
	    			progresstext.setText(arg2);
	    			
	    		}
	    		
	    	};
	    	
	    	progressBar.progressProperty().bind(task.progressProperty());
	    	
	    	task.messageProperty().addListener(message);
	    	
	    	Thread th = new Thread(task);
	        th.setDaemon(true);
	        th.start();
	        task.setOnSucceeded(e ->{
	        	if(p1Rond.isSelected()) {
	        		match_controler.piece=2;
	        		match_controler.piece_img="rond";
	        	}
	        	menu_window.getChildren().setAll(window);
	        });
			//menu_window.getChildren().setAll(window);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
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
    public void testTrain(ActionEvent event) {
    	
    	ChangeListener<String> message = new ChangeListener<String>() {

    		@Override
    		public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
    			// TODO Auto-generated method stub
    			progresstext.setText(arg2);
    			
    		}
    		
    	};
    	
    	progressBar.progressProperty().bind(task.progressProperty());
    	
    	task.messageProperty().addListener(message);
    	
    	Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        task.setOnSucceeded(e ->{
        	
        });
        try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    }

    Task<Void> task = new Task<Void>() {
    	
    	@Override
    	protected Void call() throws Exception {
    		// TODO Auto-generated method stub
    		int size=9;
    	    DecimalFormat df1 = new DecimalFormat("0.000");

    		try {
    			System.out.println();
    			System.out.println("START TRAINING ...");
    			System.out.println();
    			//
    			int[] layers = new int[]{ size, 128, size };
    			//
    			double error = 0.0 ;
    			MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.1, new SigmoidalTransferFunction());
    			double epochs = 10000000 ;

    			System.out.println("---");
    			System.out.println("Load data ...");
    			HashMap<Integer, Coup> mapTrain = Test.loadCoupsFromFile("./resources/train_dev_test/train.txt");
    			HashMap<Integer, Coup> mapDev = Test.loadCoupsFromFile("./resources/train_dev_test/dev.txt");
    			HashMap<Integer, Coup> mapTest = Test.loadCoupsFromFile("./resources/train_dev_test/test.txt");
    			System.out.println("---");
    			//TRAINING ...
    			for(int i = 0; i < epochs; i++){

    				Coup c = null ;
    				while ( c == null )
    					c = mapTrain.get((int)(Math.round(Math.random() * mapTrain.size())));

    				error += net.backPropagate(c.in, c.out);

    				if ( i % 10000 == 0 ) {
    					
    					//progresstext.setText(df1.format((i/epochs*100))+"% de la fin du processus \n" + "Error is "+ (error/(double)i));
    					//progressBar.setProgress(i/epochs);
    					updateMessage(df1.format((i/epochs*100))+"% de la fin du processus \n" + "Error is "+ (error/(double)i));
    				}
    			updateProgress(i,epochs);
    			}
    			error /= epochs ;
    			if ( epochs > 0 )
    				System.out.println("Error is "+error);
    			//
    			updateMessage("Learning completed!");
    			latch.countDown();
    			net.save(("./resources/train_dev_test/model.ai"));
    			
    		} 
    		catch (Exception e) {
    			System.out.println("Test.test()");
    			e.printStackTrace();
    			System.exit(-1);
    		}

    		
    		return null;
    	}
    	
    };

}
