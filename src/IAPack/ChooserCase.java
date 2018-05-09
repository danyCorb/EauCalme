package IAPack;

import java.util.ArrayList;
import java.util.List;

import Data.DataMain;
import Data.Pion;
import Jeu.Jeu;

/**
 * Permet de choisir les actions a effectuer en les calculent
 * @author Dany CORBINEAU
 *
 */

public class ChooserCase {
	
	/*
	 * Définition des ceuils et coefficient
	 */
	private final int seuils=0;
	
	
	public Coefficient coefNain,coefTroll;
	
	
	
	
	public ChooserCase(){
		coefNain=new Coefficient("NainCoef.txt");
		coefTroll=new Coefficient("TrollCoef.txt");
		
		coefNain.loadCoef();
		coefTroll.loadCoef();
	}
	
	/**
	 * 
	 * @param jeu
	 * @return 2 case [1][x;y] et [2][x2;y2]
	 */
	public int[][] jouerUneCase(Jeu jeu){
		System.out.println("Selection de la case en cours");
		List<int[]> l= jeu.getListCaseDisponibles();
		Pion pionActuel=getPionActuel();
		
		List<ClassementValue> valeurs=new ArrayList<>(); // int[6] (x,y,x2,y2,nsp/sp,val)
		
		// récupération des valeurs de déplacement
		if(l!=null){
			for(int j=0;j<l.size();++j){
				int deplacement[][]=jeu.getCaseValidePourDeplacement(l.get(j)[0], l.get(j)[1]);
				int[][] deplacementSpec=jeu.getCaseValidePourDeplacementSpecial(l.get(j)[0], l.get(j)[1]);
				
				if(deplacement!=null){
					for(int k=0;k<deplacement[0].length;++k){
						valeurs.add(new ClassementValue(
								l.get(j)[0],
								l.get(j)[1],
								deplacement[0][k],
								deplacement[1][k],
								0,
								getDeplacementScore(l.get(j)[0], l.get(j)[1], deplacement[0][k], deplacement[1][k], 0,pionActuel)
						));
					}
				}
				
				if(deplacementSpec!=null && deplacementSpec.length>0){
					for(int k=0;k<deplacementSpec.length;++k){
						valeurs.add(new ClassementValue(
								l.get(j)[0],
								l.get(j)[1],
								deplacementSpec[k][0],
								deplacementSpec[k][1],
								1,
								getDeplacementScore(l.get(j)[0], l.get(j)[1], deplacementSpec[k][0], deplacementSpec[k][1], 1,pionActuel)
						));
					}
				}
			}
		}
		
		// récupération de la meilleure valeur
		int idMax=0;
		for(int j=0;j<valeurs.size();++j){
			if(valeurs.get(j).getValue()>valeurs.get(idMax).getValue()){
				idMax=j;
			}
		}
		System.out.println("case selectionne "+valeurs.get(idMax).getX()+" "+valeurs.get(idMax).getY()+" "+valeurs.get(idMax).getX2()+" "+valeurs.get(idMax).getY2());
		return new int[][]{
			{(int) valeurs.get(idMax).getX(),(int) valeurs.get(idMax).getY()},
			{(int) valeurs.get(idMax).getX2(),(int) valeurs.get(idMax).getY2()}
		};
	}
	
	public boolean getChoixAccepterAbandonAdv(){
		Pion pionActuel=getPionActuel();
		DataMain dm=DataMain.getInstance();
		int pn=dm.getDataPlateau().getPointNain();
		int pt=dm.getDataPlateau().getPointTrolle();
		int manche=dm.getDataPartie().getManche();
		boolean jaiCommence=dm.getDataPartie().isiStart();
		int pMoi=0;
		int pAdv=0;
		
		if( jaiCommence && manche == 1 ){
			pMoi=pn;
			pAdv=pt;
		}
		else if( !jaiCommence && manche == 1 ){
			pMoi=pt;
			pAdv=pn;
		}
		else if( jaiCommence && manche == 2 ){
			pMoi=pt;
			pAdv=pn;
		}
		else if( !jaiCommence && manche == 2 ){
			pMoi=pn;
			pAdv=pt;
		}
		
		if(manche==2){
			pMoi+=dm.getDataPartie().getScorePartie1()[0];
			pAdv+=dm.getDataPartie().getScorePartie1()[1];
		}
		
		if(pionActuel==Pion.Nain)
			return ( (pMoi-pAdv)>coefNain.getScoreLimitAbandon() ) ? true : false;
		return ( (pMoi-pAdv)>coefTroll.getScoreLimitAbandon() ) ? true : false;
	}
	
	
	private double getDeplacementScore(int x1,int y1,int x2,int y2,int spe, Pion pionActuel){
		double rapprochementEnemy = rapprochementVersEnemy(x1,y1,x2,y2,pionActuel);
		double rapprochementAllie = rapprochementVersAllie(x1,y1,x2,y2,pionActuel);
		double rapprocheDuCentre = rapprocherDuCentre(x1,y1,x2,y2);
		double nainOuTrollPris = getNbPiecePris(x2,y2,pionActuel);
		
		double score=0;
		
		if(pionActuel==Pion.Trolle){
			score=rapprochementEnemy*coefTroll.getCoefRapprochementEnemy() + 
					rapprochementAllie*coefTroll.getCoefRapprochementAllie() +
					rapprocheDuCentre*coefTroll.getCoefRapprocheDuCentre() + 
					nainOuTrollPris*coefTroll.getCoefNainOuTrollPris();
		}else{
			score=rapprochementEnemy*coefNain.getCoefRapprochementEnemy() + 
					rapprochementAllie*coefNain.getCoefRapprochementAllie() +
					rapprocheDuCentre*coefNain.getCoefRapprocheDuCentre() + 
					nainOuTrollPris*coefNain.getCoefNainOuTrollPris();
		}
		
		return score;
	}
	
	
	public boolean proposerAbandont(){
		Pion p=getPionActuel();
		
		DataMain dm=DataMain.getInstance();
		int pn=dm.getDataPlateau().getPointNain();
		int pt=dm.getDataPlateau().getPointTrolle();
		int manche=dm.getDataPartie().getManche();
		boolean jaiCommence=dm.getDataPartie().isiStart();
		int pMoi=0;
		int pAdv=0;
		
		if(dm.getDataPartie().getCoupActuel()<5)
			return false;
		
		if( jaiCommence && manche == 1 ){
			pMoi=pn;
			pAdv=pt;
		}
		else if( !jaiCommence && manche == 1 ){
			pMoi=pt;
			pAdv=pn;
		}
		else if( jaiCommence && manche == 2 ){
			pMoi=pt;
			pAdv=pn;
		}
		else if( !jaiCommence && manche == 2 ){
			pMoi=pn;
			pAdv=pt;
		}
		
		if(manche==2){
			pMoi+=dm.getDataPartie().getScorePartie1()[0];
			pAdv+=dm.getDataPartie().getScorePartie1()[1];
		}
		
		if(p==Pion.Nain)
			return ( (pMoi-pAdv)>coefNain.getScoreLimitAbandon() ) ? true : false;
		return ( (pMoi-pAdv)>coefTroll.getScoreLimitAbandon() ) ? true : false;
	}
	
	
	private double getNbPiecePris(int x,int y,Pion p){
		int nbP=0;
		
		if(p==Pion.Trolle){
			for(int j=x-1;j<x+2;++j){
				for(int k=y-1;k<y+2;++k){
					if( x>=0 && x<15 && y>=0 && y<=15 ){
						if(DataMain.getInstance().getDataPlateau().getPionInCase(j, y)==Pion.Nain){
							++nbP;
						}
					}
				}
			}
		}
		else{
			if(DataMain.getInstance().getDataPlateau().getPionInCase(x, y)==Pion.Trolle){
				++nbP;
			}
		}
		
		return nbP;
	}
	
	private double rapprocherDuCentre(int x,int y,int x2,int y2){
		int cx=8;
		int cy=cx;
		double d1 = Math.sqrt( Math.pow( (x)-(cx) , 2) + Math.pow( (y)-(cy) , 2) );
		double d2 = Math.sqrt( Math.pow( (x2)-(cx) , 2) + Math.pow( (y2)-(cy) , 2) );
		return d1-d2;
	}
	
	private double rapprochementVersEnemy(int x,int y,int x2,int y2,Pion pionActuel){
		double distanceAv=0, distanceAp=0;
		int nbEnnemyAv=0, nbEnnemyAp=0;
		
		Pion plateauAv[][]=DataMain.getInstance().getDataPlateau().getPlateauDump();
		Pion plateauAp[][]=DataMain.getInstance().getDataPlateau().getPlateauDump();
		plateauAp[x2][y2]=plateauAp[x][y];
		plateauAp[x][y]=Pion.Vide;
		
		
		// calc Av
		for(int j=0;j<plateauAv.length;++j){
			for(int k=0;k<plateauAv[j].length;++k){
				if(j!=x && k!=y){
					if(  ( pionActuel==Pion.Trolle && plateauAv[j][k]==Pion.Nain )  || ( pionActuel==Pion.Nain && plateauAv[j][k]==Pion.Trolle ) ){
						distanceAv+= Math.sqrt( Math.pow( (x)-(j) , 2) + Math.pow( (y)-(k) , 2) );
						++nbEnnemyAv;
					}
				}
			}
		}
		
		// calc Ap
		for(int j=0;j<plateauAp.length;++j){
			for(int k=0;k<plateauAp[j].length;++k){
				if(j!=x2 && k!=y2){
					if(  ( pionActuel==Pion.Trolle && plateauAp[j][k]==Pion.Nain )  || ( pionActuel==Pion.Nain && plateauAp[j][k]==Pion.Trolle ) ){
						distanceAp+= Math.sqrt( Math.pow( (x2)-(j) , 2) + Math.pow( (y2)-(k) , 2) );
						++nbEnnemyAp;
					}
				}
			}
		}
		return (distanceAv/((int)nbEnnemyAv))-(distanceAp/((int)nbEnnemyAp));
	}
	
	
	private double rapprochementVersAllie(int x,int y,int x2,int y2,Pion pionActuel){
		double distanceAv=0, distanceAp=0;
		int nbAllieAv=0, nbAllieAp=0;
		
		Pion plateauAv[][]=DataMain.getInstance().getDataPlateau().getPlateauDump();
		Pion plateauAp[][]=DataMain.getInstance().getDataPlateau().getPlateauDump();
		plateauAp[x2][y2]=plateauAp[x][y];
		plateauAp[x][y]=Pion.Vide;
		
		
		// calc Av
		for(int j=0;j<plateauAv.length;++j){
			for(int k=0;k<plateauAv[j].length;++k){
				if(j!=x && k!=y){
					if(  ( plateauAv[j][k]==pionActuel ) ){
						distanceAv+= Math.sqrt( Math.pow( (x)-(j) , 2) + Math.pow( (y)-(k) , 2) );
						++nbAllieAv;
					}
				}
			}
		}
		
		// calc Ap
		for(int j=0;j<plateauAp.length;++j){
			for(int k=0;k<plateauAp[j].length;++k){
				if(j!=x2 && k!=y2){
					if(  ( pionActuel==Pion.Trolle && plateauAp[j][k]==Pion.Nain )  || ( pionActuel==Pion.Nain && plateauAp[j][k]==Pion.Trolle ) ){
						distanceAp+= Math.sqrt( Math.pow( (x2)-(j) , 2) + Math.pow( (y2)-(k) , 2) );
						++nbAllieAp;
					}
				}
			}
		}
		return (distanceAv/((int)nbAllieAv))-(distanceAp/((int)nbAllieAp));
	}
	
	
	private Pion getPionActuel(){
		DataMain dm=DataMain.getInstance();
		if( (dm.getDataPartie().isiStart() && dm.getDataPartie().getManche() == 1)  ||  (!dm.getDataPartie().isiStart() && dm.getDataPartie().getManche() == 2) ){
			return Pion.Nain;
		}
		else{
			return Pion.Trolle;
		}
	}
	
	
}
