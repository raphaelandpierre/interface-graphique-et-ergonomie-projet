package application;

import java.awt.AWTException;
import java.awt.Robot;

import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import javafx.event.Event;
import javafx.fxml.FXML;
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
    void CheckState() {//vérifie l'etat du board
    	if(manager.CheckWin(piece)==true) {
    		System.out.println("les "+piece_img+"ont gagné,trop bien");
    	}else if(manager.isTie()==true) {
    		System.out.println("c'est une égalité Thierry");
    	}
    	manager.debugDisplay();
    	if(is2p==false){//contre l'ia,calcul next move
    		double [] next = net.forwardPropagation(manager.getAIGameState());
    		double max=-1.0;
    		int vert=-1;
    		int hori=-1;
    		int cas=-1;//case cibler par l'ia
    		if(piece==1) {
    			int aipiece=2;
    		}else {
    			int aipiece=1;
    		}
    		while(!manager.MakeMove(vert,hori,1)) {
    		for(int i=0;i<next.length;i++) {
    			if(next[i]>max) {
    				max=next[i];
    				cas=i;
    			}
    		}
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
    		}
    		//le move a été fait mais il faut trouver quelle case a été jouer
    		try {
    		    Robot robot = new java.awt.Robot();
    		    ImageView target = null;
    		    switch(cas) {
        		case 0:target=ecran_match_11;
        		case 1:target=ecran_match_12;break;
        		case 2:target=ecran_match_13;break;
        		case 3:target=ecran_match_21;break;
        		case 4:target=ecran_match_22;break;
        		case 5:target=ecran_match_23;break;
        		case 6:target=ecran_match_31;break;
        		case 7:target=ecran_match_32;break;
        		case 8:target=ecran_match_33;break;
    		    }
    		    TurnSwap();//fast changement de tour pour que l'IA soit la piece opposé
    		    robot.mouseMove((int)target.getX(), (int)target.getY());
    		    robot.mousePress(16);
    		    robot.mouseRelease(16);
    		    //robot.mouseMove((int) originalLocation.getX(), (int)originalLocation.getY());
    		} catch (AWTException e) {
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
    		}
    	}
    }

    @FXML
    void ecran_match_12_play(MouseEvent event) {
    	if(manager.MakeMove(0,1,piece)==true) {
    		ecran_match_12.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}
    	}
    }

    @FXML
    void ecran_match_13_play(MouseEvent event) {
    	if(manager.MakeMove(0,2,piece)==true) {
    		ecran_match_13.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}
    	}
    }

    @FXML
    void ecran_match_21_play(MouseEvent event) {
    	if(manager.MakeMove(1,0,piece)==true) {
    		ecran_match_21.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}
    	}
    }

    @FXML
    void ecran_match_22_play(MouseEvent event) {
    	if(manager.MakeMove(1,1,piece)==true) {
    		ecran_match_22.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}
    	}
    }

    @FXML
    void ecran_match_23_play(MouseEvent event) {
    	if(manager.MakeMove(1,2,piece)==true) {
    		ecran_match_23.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}
    	}
    }

    @FXML
    void ecran_match_31_play(MouseEvent event) {
    	if(manager.MakeMove(2,0,piece)==true) {
    		ecran_match_31.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}
    	}
    }

    @FXML
    void ecran_match_32_play(MouseEvent event) {
    	if(manager.MakeMove(2,1,piece)==true) {
    		ecran_match_32.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}
    	}
    }

    @FXML
    void ecran_match_33_play(MouseEvent event) {
    	if(manager.MakeMove(2,2,piece)==true) {
    		ecran_match_33.setImage(new Image(getClass().getResourceAsStream("images/ecran_match_"+piece_img+".png")));
    		CheckState();
    		if(is2p==true) {
    			TurnSwap();
    		}
    	}
    }

    @FXML
    void ecran_match_save(MouseEvent event) {

    }
    

}
