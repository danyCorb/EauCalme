package Jeu;

import Data.DataPionSelectione;

public interface JeuListener {
	 public void selectedCase(int posX, int posY);
	 public boolean checkIsTrayCase(int x,int y);
	 public int[][] getCaseValidePourDeplacement(int x,int y);
	 public int[][] getCaseValidePourDeplacementSpecial(int x,int y);
	 public int deplacerPiece(DataPionSelectione dps, int newCaseX,int newCaseY);
	 public void endTrollSelection();
	 public void ProposerAbandon();
	 public boolean startJeu();
	 public boolean getTour();
	 public void validerEnvoiDemandeAbandont();
	 
	 public void startManche2();
	 
	 boolean electionOrdreDeJeu();
}
