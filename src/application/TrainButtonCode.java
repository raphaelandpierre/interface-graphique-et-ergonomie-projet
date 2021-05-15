package application;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;

import ai.Coup;
import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import ai.Test;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;

public class TrainButtonCode implements Initializable{
	
@FXML
private Button train_button;

@FXML
private TextArea progresstext;

@FXML
private ProgressBar progressBar;

/*
public void testTrain(ActionEvent event) {
    
	Thread threadAffichageTrain = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Test.test(9,progresstext,progressBar);
		}
	
	});
	threadAffichageTrain.start();
}
*/



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

			
		} 
		catch (Exception e) {
			System.out.println("Test.test()");
			e.printStackTrace();
			System.exit(-1);
		}

		
		return null;
	}
	
};



@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}
	
}
