package application;

import java.util.HashMap;

public class GameManager {
	
	private int[][] gameState;

	public GameManager() {
			gameState = new int[3][3];//Une hashmap<Numero de case,Status de la case>
			for(int i =0;i<3;i++) {
				for(int j=0;j<3;j++) {
					gameState[i][j]=0;
				}
			}

	}
	//0=empty
	//1=cross
	//2=circle
	public boolean MakeMove(int vertical,int horizontal,int piece) {//retourne vrai et execute le move si il est legal,faux sinon
		if(vertical==-1){return false;}//init de l'ia
		if(gameState[vertical][horizontal]==0) {
			gameState[vertical][horizontal]=piece;
			return true;
		}else {
		return false;
		}
	}
	public Boolean isTie() {//retourne vrai si c'est egalit�,faux sinon
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(gameState[i][j]==0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void debugDisplay() {//print le board dans la console
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(gameState[i][j]);
			}
			System.out.println("");
		}
	}
	
	public double[] getAIGameState() {//sort une version du board compatible avec l'IA
		double[] output = new double[9];
		int k=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				switch(gameState[i][j]) {
					case 0:output[k]=0.0;break;
					case 1:output[k]=-1.0;break;
					case 2:output[k]=1.0;break;
					default:output[k]=0.0;break;
				}
			}
		}
		return output;
	}
	public Boolean CheckWin(int player_piece) {//retourne false si pas de gagnant,true si un gagnant
		for(int i =0;i<3;i++) {//check horizontalement
			int score=0; 
			for(int j = 0; j < 3;j++) {
				if(gameState[i][j]==player_piece) {
					score +=1;
				}
				if(score==3){
					return true;
				}
			}
		}
		
		for(int i =0;i<3;i++) {//check verticalement
			int score=0; 
			for(int j = 0; j < 3;j++) {
				if(gameState[j][i]==player_piece) {
					score +=1;
				}
				if(score==3){
					return true;
				}
			}
		}
		//check les diagonales
		if(gameState[0][0]==player_piece) {
			if(gameState[1][1]==player_piece) {
				if(gameState[2][2]==player_piece) {
					return true;
				}
			}
		} 
		if(gameState[2][0]==player_piece) {
			if(gameState[1][1]==player_piece) {
				if(gameState[0][2]==player_piece) {
					return true;
				}
			}
		}
		return false;
	}

}
