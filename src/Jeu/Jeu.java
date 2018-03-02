package Jeu;

import Data.DataMain;
import Data.DataPionSelectione;

import javax.xml.crypto.Data;

public class Jeu {

    public static void selectedCase(int posX, int posY){
        int caseX = DataMain.getInstance().getDataPionSelectione().getX();
        int caseY = DataMain.getInstance().getDataPionSelectione().getY();

        if (caseX == posX && caseY == posY){
            DataMain.getInstance().getDataPionSelectione().unselectPion();
        } else if (DataMain.getInstance().getDataPionSelectione().selectedPion()) {

        }else{
            DataMain.getInstance().getDataPionSelectione().setX(posX);
            DataMain.getInstance().getDataPionSelectione().setY(posY);
        }
    }

    public static void deplacerPion(int posXCase2, int posYCase2){

    }

    public static boolean rightMove(int caseX, int caseY, int caseX2, int caseY2){
        Data.Pion stylePion = DataMain.getInstance().getDataPlateau()
    }

}
