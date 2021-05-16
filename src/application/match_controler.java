package application;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Arrays;

import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class match_controler {
	
	public GameManager manager = new GameManager();
	public static int piece = 1;
	public static String piece_img = "croix";
	public static boolean is2p = false;
	public boolean aiturn = false;
	public static int[] layers = new int[]{ 9, 128, 9 };
	public static MultiLayerPerceptron net = new MultiLayerPerceptron(layers, 0.1, new SigmoidalTransferFunction());
    @FXML
    private AnchorPane menu_window;

    @FXML
    private Rectangle match_select_j2;

    @FXML
    private ImageView ecran_match_11;

    @FXML
    private ImageView ecran_match_12;

    @FXML
    private ImageView ecran_match_13;

    @FXML
    private ImageView ecran_match_21;

    @FXML
    private ImageView ecran_match_22;

    @FXML
    private ImageView ecran_match_23;

    @FXML
    private ImageView ecran_match_31;

    @FXML
    private ImageView ecran_match_32;

    @FXML
    private ImageView ecran_match_33;

    @FXML
    private ImageView match_j1_icon;

    @FXML
    private ImageView match_j2_icon;

    @FXML
    private Rectangle match_select_j1;

    @FXML
    private ImageView ecran_match_111;
    
    @FXML
    private Button ecran_match_go_home;
    
    @FXML
    private Button ecran_match_replay;

    @FXML
    void CheckState() {//v�rifie l'etat du board
    	if(manager.CheckWin(piece)==true) {
    		System.out.println("les "+piece_img+" ont gagn�,trop bien");
    		ecran_match_11.setDisable(true);
    		ecran_match_12.setDisable(true);
    		ecran_match_13.setDisable(true);
    		ecran_match_21.setDisable(true);
    		ecran_match_22.setDisable(true);
    		ecran_match_23.setDisable(true);
    		ecran_match_31.setDisable(true);
    		ecran_match_32.setDisable(true);
    		ecran_match_33.setDisable(true);
    		ecran_match_go_home.setVisible(true);
    		ecran_match_replay.setVisible(true);
    		aiturn=false;
    		
    	}else if(manager.isTie()==true) {
    		System.out.println("c'est une �galit� Thierry");
    		ecran_match_11.setDisable(true);
    		ecran_match_12.setDisable(true);
    		ecran_match_13.setDisable(true);
    		ecran_match_21.setDisable(true);
    		ecran_match_22.setDisable(true);
    		ecran_match_23.setDisable(true);
    		ecran_match_31.setDisable(true);
    		ecran_match_32.setDisable(true);
    		ecran_match_33.setDisable(true);
    		ecran_match_go_home.setVisible(true);
    		ecran_match_replay.setVisible(true);
    		aiturn=false;
    	}
    	manager.debugDisplay();
    	if(is2p==false && aiturn==true){//contre l'ia,calcul next move
    		double [] next = net.forwardPropagation(manager.getAIGameState());
    		aiturn=false;
    		double max=-1.0;
    		int vert=-1;
    		int hori=-1;
    		int cas=-1;//case cibler par l'ia
    		int aipiece=0;
    		String piece_img_ai;
    		if(piece==1) {
    			aipiece=2;
    			piece_img_ai="rond";
    		}else {
    			aipiece=1;
    			piece_img_ai="croix";
    		}
    		while(!manager.MakeMove(vert,hori,aipiece)) {
    			for(int i=0;i<next.length;i++) {
    			if(next[i]>max) {
    				max=next[i];
    				cas=i;
    			}
    			
    		}
    		next[cas]=0.0;
    		max=0;
    		switch(cas) {
    		case 0:vert=0;hori=0;break;
    		case 1:vert=0;hori=1;break;
    		case 2:vert=0;hori=2;break;
    		case 3:vert=1;hori=0;break;
    		case 4:vert=1;hori=1;break;
    		case 5:vert=1;hori=2;break;
    		case 6:vert=2;hori=0;break;
    		case 7:vert=2;hori=1;break;
    		case 8:vert=2;hori=2;break;
    		}
    		System.out.println("robot chooses:"+String.valueOf(cas));
    		}
    		//le move a �t� fait mais il faut trouver quelle case a �t� jouer
    		try {
    		    //Robot robot = new java.awt.Robot();
    		    //ImageView target = null;
    		    //TurnSwap();//fast changement de tour pour que l'IA soit la piece oppos�
    		    switch(cas) {
        		case 0:ecran_match_11.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img_ai+".png")));break;
        		case 1:ecran_match_12.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img_ai+".png")));break;
        		case 2:ecran_match_13.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img_ai+".png")));break;
        		case 3:ecran_match_21.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img_ai+".png")));break;
        		case 4:ecran_match_22.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img_ai+".png")));break;
        		case 5:ecran_match_23.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img_ai+".png")));break;
        		case 6:ecran_match_31.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img_ai+".png")));break;
        		case 7:ecran_match_32.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img_ai+".png")));break;
        		case 8:ecran_match_33.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img_ai+".png")));break;
    		    }
    		    PlayerSwap();
    		    CheckState();
    		    //System.out.println("robot has clicked on:"+String.valueOf((int)target.getX()+" and " +String.valueOf((int)target.getY())));
    		    //robot.mouseMove((int) originalLocation.getX(), (int)originalLocation.getY());
    		} catch (Exception e) {
    		    e.printStackTrace();
    		}
    	}
    }
    void TurnSwap() {//change de tour
    	if(piece==1) {
    		piece=2;
    		piece_img="rond";
    	}else {
    		piece=1;
    		piece_img="croix";
    	}
    }
    
    void PlayerSwap() {
    	if(match_select_j1.isVisible()) {
    		match_select_j1.setVisible(false);
    		match_select_j2.setVisible(true);
    	}
    	else {
    		match_select_j1.setVisible(true);
    		match_select_j2.setVisible(false);
    	}
    }
    
    static void LoadModel() {//charge le model d'IA
    	net.load(("./resources/train_dev_test/model.ai"));
    }
    @FXML
    void ecran_match_11_play(MouseEvent event) {
    	//System.out.println(path);
    	//ecran_match_11.setImage(new Image("C:\\Users\\Admin\\Desktop\\Projet_Interface\\interface-graphique-et-ergonomie-projet\\bin\\application\\images\\ecran_match_croix.png"));
    	if(manager.MakeMove(0,0,piece)==true) {
    		ecran_match_11.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}else {
    			aiturn=true;
    		}
    		PlayerSwap();
    	}
    }

    @FXML
    void ecran_match_12_play(MouseEvent event) {
    	if(manager.MakeMove(0,1,piece)==true) {
    		ecran_match_12.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}else {
    			aiturn=true;
    		}
    		PlayerSwap();
    		
    	}
    }

    @FXML
    void ecran_match_13_play(MouseEvent event) {
    	if(manager.MakeMove(0,2,piece)==true) {
    		ecran_match_13.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}else {
    			aiturn=true;
    		}
    		PlayerSwap();
    	}
    }

    @FXML
    void ecran_match_21_play(MouseEvent event) {
    	if(manager.MakeMove(1,0,piece)==true) {
    		ecran_match_21.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}else {
    			aiturn=true;
    		}
    		PlayerSwap();
    	}
    }

    @FXML
    void ecran_match_22_play(MouseEvent event) {
    	if(manager.MakeMove(1,1,piece)==true) {
    		ecran_match_22.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}else {
    			aiturn=true;
    		}
    		PlayerSwap();
    	}
    }

    @FXML
    void ecran_match_23_play(MouseEvent event) {
    	if(manager.MakeMove(1,2,piece)==true) {
    		ecran_match_23.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}else {
    			aiturn=true;
    		}
    		PlayerSwap();
    	}
    }

    @FXML
    void ecran_match_31_play(MouseEvent event) {
    	if(manager.MakeMove(2,0,piece)==true) {
    		ecran_match_31.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}else {
    			aiturn=true;
    		}
    		PlayerSwap();
    	}
    }

    @FXML
    void ecran_match_32_play(MouseEvent event) {
    	if(manager.MakeMove(2,1,piece)==true) {
    		ecran_match_32.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}else {
    			aiturn=true;
    		}
    		PlayerSwap();
    	}
    }

    @FXML
    void ecran_match_33_play(MouseEvent event) {
    	if(manager.MakeMove(2,2,piece)==true) {
    		ecran_match_33.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}else {
    			aiturn=true;
    		}
    		PlayerSwap();
    	}
    }

    @FXML
    void ecran_match_save(MouseEvent event) {

    }
    
    @FXML
    void ecran_match_replay(MouseEvent event) {
    	
		ecran_match_11.setDisable(false);
		ecran_match_12.setDisable(false);
		ecran_match_13.setDisable(false);
		ecran_match_21.setDisable(false);
		ecran_match_22.setDisable(false);
		ecran_match_23.setDisable(false);
		ecran_match_31.setDisable(false);
		ecran_match_32.setDisable(false);
		ecran_match_33.setDisable(false);
		ecran_match_11.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_blanc.png")));
		ecran_match_12.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_blanc.png")));
		ecran_match_13.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_blanc.png")));
		ecran_match_21.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_blanc.png")));
		ecran_match_22.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_blanc.png")));
		ecran_match_23.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_blanc.png")));
		ecran_match_31.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_blanc.png")));
		ecran_match_32.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_blanc.png")));
		ecran_match_33.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_blanc.png")));
		match_select_j1.setVisible(true);
		match_select_j2.setVisible(false);
		ecran_match_go_home.setVisible(false);
		ecran_match_replay.setVisible(false);
		piece = 1;
		piece_img = "croix";
		manager = new GameManager();
    }
    
    @FXML
    void ecran_match_go_home(MouseEvent event) {
		try {
			AnchorPane window = FXMLLoader.load(getClass().getResource("ecran_menu_home.fxml"));
			menu_window.getChildren().setAll(window);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
