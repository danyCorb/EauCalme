package Jeu;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import DAO.MainDAO;
import Data.DataMain;
import Data.DataPionSelectione;
import Data.Pion;
import MainPackage.EauCalmeMain;

public class Jeu {

    public static void selectedCase(int posX, int posY){
        int caseX = DataMain.getInstance().getDataPionSelectione().getX();
        int caseY = DataMain.getInstance().getDataPionSelectione().getY();
        if(Jeu.checkIsTrayCase(posX, posY) && !DataMain.getInstance().getDataTrolleSelection().isSelectionMode())
        {
        	if (caseX == posX && caseY == posY){
                DataMain.getInstance().getDataPionSelectione().unselectPion();
            } else if (DataMain.getInstance().getDataPionSelectione().selectedPion()) {
            	Jeu.deplacerPiece(DataMain.getInstance().getDataPionSelectione(), posX, posY);
            	DataMain.getInstance().getDataPionSelectione().unselectPion();
            }else{
            	if(DataMain.getInstance().getDataPlateau().getPionInCase(posX, posY)!=Pion.Vide){
            		DataMain.getInstance().getDataPionSelectione().setX(posX);
                    DataMain.getInstance().getDataPionSelectione().setY(posY);
                    DataMain.getInstance().getDataPionSelectione().setCaseDispoDeplacement(Jeu.getCaseValidePourDeplacement(posX, posY));
                    DataMain.getInstance().getDataPionSelectione().setCaseDeplacementSpecial(Jeu.getCaseValidePourDeplacementSpecial(posX, posY));
            	}
                
            }
        }else if(Jeu.checkIsTrayCase(posX, posY) && DataMain.getInstance().getDataTrolleSelection().isSelectionMode()){
        	if(DataMain.getInstance().getDataPlateau().getPionInCase(posX, posY)==Pion.Nain){
        		boolean dejaAjoute=false;
        		for(int j=0;j<DataMain.getInstance().getDataTrolleSelection().getListeCase().size();j++)
        			if(DataMain.getInstance().getDataTrolleSelection().getListeCase().get(j)[0]==posX && DataMain.getInstance().getDataTrolleSelection().getListeCase().get(j)[1]==posY)
        					dejaAjoute=true;
        			
        		if(!dejaAjoute)
        			DataMain.getInstance().getDataTrolleSelection().addCase(posX, posY);
        		else
        			DataMain.getInstance().getDataTrolleSelection().removeCase(posX, posY);
        	}
        }
    }

    
    
    
    public static boolean checkIsTrayCase(int x,int y){
    	if(DataMain.getInstance().getDataPlateau().getPionInCase(x, y)!=Pion.Null && DataMain.getInstance().getDataPlateau().getPionInCase(x, y)!=Pion.Rocher)
    		return true;
    	return false;
    }
    
    public static int[][] getCaseValidePourDeplacement(int x,int y){
    	Pion p=DataMain.getInstance().getDataPlateau().getPionInCase(x, y);
    	List<int[]> caseDispo=new ArrayList<>();
    	
    	
    	/*
    	 * Trolle deplacement simple
    	 */
    	if(p==Pion.Trolle){
    		for(int j=0;j<3;j++){
    			for(int k=0;k<3;k++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x-1+j, y-1+k)==Pion.Vide){
    					caseDispo.add(new int[]{x-1+j,y-1+k});
    				}
    			}
    		}
    	}
    	else if(p==Pion.Nain){  // Nain deplacement simple
    		
    		// Ouest
    		int nbNainLigne;
    		
    		for(int j=1;j<14;j++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j, y)==Pion.Vide)
    				caseDispo.add(new int[]{x-j,y});
    			else
    				break;
    		}
    		
    		// Nord
    		for(int j=1;j<14;j++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x, y-j)==Pion.Vide)
    				caseDispo.add(new int[]{x,y-j});
    			else
    				break;
    		}
    		
    		// Est
    		for(int j=1;j<14;j++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j, y)==Pion.Vide)
    				caseDispo.add(new int[]{x+j,y});
    			else
    				break;
    		}
    		
    		// Sud
    		for(int j=1;j<14;j++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x, y+j)==Pion.Vide)
    				caseDispo.add(new int[]{x,y+j});
    			else
    				break;
    		}
    		
    		// Nord-Ouest
    		for(int j=1;j<14;j++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j, y-j)==Pion.Vide)
    				caseDispo.add(new int[]{x-j,y-j});
    			else
    				break;
    		}
    		
    		// Nord-Est
    		for(int j=1;j<14;j++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j, y-j)==Pion.Vide)
    				caseDispo.add(new int[]{x+j,y-j});
    			else
    				break;
    		}
    		
    		// Sud-Est
    		for(int j=1;j<14;j++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j, y+j)==Pion.Vide)
    				caseDispo.add(new int[]{x+j,y+j});
    			else
    				break;
    		}
    		
    		// Sud-Ouest
    		for(int j=1;j<14;j++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j, y+j)==Pion.Vide)
    				caseDispo.add(new int[]{x-j,y+j});
    			else
    				break;
    		}
    		
    	}
    	
    	
    	
    	int ret[][]=new int[2][];
    	ret[0]=new int[caseDispo.size()];
    	ret[1]=new int[caseDispo.size()];
    	
    	for(int j=0;j<caseDispo.size();j++){
    		ret[0][j]=caseDispo.get(j)[0];
    		ret[1][j]=caseDispo.get(j)[1];
    	}
    	
    	
		return ret;
    	
    }
    
    
    public static int[][] getCaseValidePourDeplacementSpecial(int x,int y){
    	Pion p=DataMain.getInstance().getDataPlateau().getPionInCase(x, y);
    	List<int[]> caseDispo=new ArrayList<>();
    	int nbPiece=0;
    	
    	if(p==Pion.Trolle){
    		
    		//Nord to Sud
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x,y-j)!=Pion.Trolle){
    				break;
    			}
    		}
    		if(nbPiece>0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x,y+j)!=Pion.Vide){
    					break;
    				}
    			}
    			if(j>=nbPiece+1){
    				for(int k=-1;k<2;k++){
    					for(int l=-1;l<2;l++){
    						if(DataMain.getInstance().getDataPlateau().getPionInCase(x+k,y+nbPiece+l)==Pion.Nain){
    							caseDispo.add(new int[]{x,y+nbPiece});
    						}
    					}
    				}
    			}
    		}
    		
    		//Sud to Nord
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x,y+j)!=Pion.Trolle){
    				break;
    			}
    		}
    		if(nbPiece>0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x,y-j)!=Pion.Vide){
    					break;
    				}
    			}
    			if(j>=nbPiece+1){
    				for(int k=-1;k<2;k++){
    					for(int l=-1;l<2;l++){
    						if(DataMain.getInstance().getDataPlateau().getPionInCase(x+k,y-nbPiece+l)==Pion.Nain){
    							caseDispo.add(new int[]{x,y-nbPiece});
    						}
    					}
    				}
    			}
    		}
    		
    		//Ouest to Est
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y)!=Pion.Trolle){
    				break;
    			}
    		}
    		if(nbPiece>0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y)!=Pion.Vide){
    					break;
    				}
    			}
    			if(j>=nbPiece+1){
    				for(int k=-1;k<2;k++){
    					for(int l=-1;l<2;l++){
    						if(DataMain.getInstance().getDataPlateau().getPionInCase(x+k+nbPiece,y+l)==Pion.Nain){
    							caseDispo.add(new int[]{x+nbPiece,y});
    						}
    					}
    				}
    			}
    		}
    		
    		//Est to Ouest
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y)!=Pion.Trolle){
    				break;
    			}
    		}
    		if(nbPiece>0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y)!=Pion.Vide){
    					break;
    				}
    			}
    			if(j>=nbPiece+1){
    				for(int k=-1;k<2;k++){
    					for(int l=-1;l<2;l++){
    						if(DataMain.getInstance().getDataPlateau().getPionInCase(x+k-nbPiece,y+l)==Pion.Nain){
    							caseDispo.add(new int[]{x-nbPiece,y});
    						}
    					}
    				}
    			}
    		}
    		
    		//Nord-Ouest to Sud-Est
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y-j)!=Pion.Trolle){
    				break;
    			}
    		}
    		if(nbPiece>0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y+j)!=Pion.Vide){
    					break;
    				}
    			}
    			if(j>=nbPiece+1){
    				for(int k=-1;k<2;k++){
    					for(int l=-1;l<2;l++){
    						if(DataMain.getInstance().getDataPlateau().getPionInCase(x+k+nbPiece,y+l+nbPiece)==Pion.Nain){
    							caseDispo.add(new int[]{x+nbPiece,y+nbPiece});
    						}
    					}
    				}
    			}
    		}
    		
    		//Sud-Ouest to Nord-Est
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y+j)!=Pion.Trolle){
    				break;
    			}
    		}
    		if(nbPiece>0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y-j)!=Pion.Vide){
    					break;
    				}
    			}
    			if(j>=nbPiece+1){
    				for(int k=-1;k<2;k++){
    					for(int l=-1;l<2;l++){
    						if(DataMain.getInstance().getDataPlateau().getPionInCase(x+k+nbPiece,y+l-nbPiece)==Pion.Nain){
    							caseDispo.add(new int[]{x+nbPiece,y-nbPiece});
    						}
    					}
    				}
    			}
    		}
    		
    		//Nord-Est to Sud-Ouest
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y-j)!=Pion.Trolle){
    				break;
    			}
    		}
    		if(nbPiece>0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y+j)!=Pion.Vide){
    					break;
    				}
    			}
    			if(j>=nbPiece+1){
    				for(int k=-1;k<2;k++){
    					for(int l=-1;l<2;l++){
    						if(DataMain.getInstance().getDataPlateau().getPionInCase(x+k-nbPiece,y+l+nbPiece)==Pion.Nain){
    							caseDispo.add(new int[]{x-nbPiece,y+nbPiece});
    						}
    					}
    				}
    			}
    		}
    		
    		//Sud-Est to Nord-Ouest
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y+j)!=Pion.Trolle){
    				break;
    			}
    		}
    		if(nbPiece>0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y-j)!=Pion.Vide){
    					break;
    				}
    			}
    			if(j>=nbPiece+1){
    				for(int k=-1;k<2;k++){
    					for(int l=-1;l<2;l++){
    						if(DataMain.getInstance().getDataPlateau().getPionInCase(x+k-nbPiece,y+l-nbPiece)==Pion.Nain){
    							caseDispo.add(new int[]{x-nbPiece,y-nbPiece});
    						}
    					}
    				}
    			}
    		}
    		
    	}
    	else if(p==Pion.Nain){
    		
    		//Nord to Sud
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x,y-j)!=Pion.Nain){
    				break;
    			}
    		}
    		if(nbPiece>=0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x,y+j)!=Pion.Vide){
    					break;
    				}
    			}
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x,y+j)== Pion.Trolle){
    				caseDispo.add(new int[]{x,y+j});		
    			}
    		}
    		
    		//Sud to Nord
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x,y+j)!=Pion.Nain){
    				break;
    			}
    		}
    		if(nbPiece>=0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x,y-j)!=Pion.Vide){
    					break;
    				}
    			}
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x,y-j)== Pion.Trolle){
    				caseDispo.add(new int[]{x,y-j});		
    			}
    		}
    		
    		//Ouest to Est
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y)!=Pion.Nain){
    				break;
    			}
    		}
    		if(nbPiece>=0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y)!=Pion.Vide){
    					break;
    				}
    			}
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y)== Pion.Trolle){
    				caseDispo.add(new int[]{x+j,y});		
    			}
    		}
    		
    		//Est to Ouest
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y)!=Pion.Nain){
    				break;
    			}
    		}
    		if(nbPiece>=0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y)!=Pion.Vide){
    					break;
    				}
    			}
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y)== Pion.Trolle){
    				caseDispo.add(new int[]{x-j,y});		
    			}
    		}
    		
    		//Sud-Ouest to Nord-Est
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y+j)!=Pion.Nain){
    				break;
    			}
    		}
    		if(nbPiece>=0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y-j)!=Pion.Vide){
    					break;
    				}
    			}
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y-j)== Pion.Trolle){
    				caseDispo.add(new int[]{x+j,y-j});		
    			}
    		}
    		
    		//Nord-Ouest to Sud-Est
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y-j)!=Pion.Nain){
    				break;
    			}
    		}
    		if(nbPiece>=0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y+j)!=Pion.Vide){
    					break;
    				}
    			}
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y+j)== Pion.Trolle){
    				caseDispo.add(new int[]{x+j,y+j});		
    			}
    		}
    		
    		//Nord-Est to Sud-Ouest
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y-j)!=Pion.Nain){
    				break;
    			}
    		}
    		if(nbPiece>=0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y+j)!=Pion.Vide){
    					break;
    				}
    			}
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y+j)== Pion.Trolle){
    				caseDispo.add(new int[]{x-j,y+j});		
    			}
    		}
    		
    		//Sud-Est to Nord-Ouest
    		nbPiece=0;
    		for(int j=1;j<15;j++,nbPiece++){
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x+j,y+j)!=Pion.Nain){
    				break;
    			}
    		}
    		if(nbPiece>=0){
    			int j;
    			for(j=1;j<nbPiece+1;j++){
    				if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y-j)!=Pion.Vide){
    					break;
    				}
    			}
    			if(DataMain.getInstance().getDataPlateau().getPionInCase(x-j,y-j)== Pion.Trolle){
    				caseDispo.add(new int[]{x-j,y-j});		
    			}
    		}
    	}
    	
    	int ret[][]=new int[caseDispo.size()][];
    	for(int j=0;j<caseDispo.size();j++)
    		ret[j]=new int[]{caseDispo.get(j)[0],caseDispo.get(j)[1]};
    	
		return ret;
    }
    
    
    public static void deplacerPiece(DataPionSelectione dps, int newCaseX,int newCaseY){
    	int j,k;
    	if(dps.getCaseDispoDeplacement().length>0)
	    	for(j=0;j<dps.getCaseDispoDeplacement()[0].length;j++){
	    		if(dps.getCaseDispoDeplacement()[0][j]==newCaseX && dps.getCaseDispoDeplacement()[1][j]==newCaseY){
	    			DataMain.getInstance().getDataPlateau().bougerPiece(dps.getX(), dps.getY(), newCaseX, newCaseY);
	    			if(DataMain.getInstance().getDataPlateau().getPionInCase( newCaseX, newCaseY)==Pion.Trolle){
	    				for(int l=0;l<3;l++){
	    					for(int m=0;m<3;m++){
	    						if(DataMain.getInstance().getDataPlateau().getPionInCase( newCaseX+l-1, newCaseY+m-1)==Pion.Nain){
	    							DataMain.getInstance().getDataTrolleSelection().startNewTrollePrise(newCaseX, newCaseY);
	    						}
	    					}
	    				}
	    			}
	    		}
	    	}
    	
    	if(dps.getCaseDeplacementSpecial().length>0)
	    	for(j=0;j<dps.getCaseDeplacementSpecial().length;j++){
	    		if(dps.getCaseDeplacementSpecial()[j][0]==newCaseX && dps.getCaseDeplacementSpecial()[j][1]==newCaseY){
	    			DataMain.getInstance().getDataPlateau().bougerPiece(dps.getX(), dps.getY(), newCaseX, newCaseY);
	    			if(DataMain.getInstance().getDataPlateau().getPionInCase(newCaseX,newCaseY)==Pion.Trolle){
	    				DataMain.getInstance().getDataTrolleSelection().startNewTrollePrise(newCaseX, newCaseY);
	    			}
	    			
	    		}
	    	}
    }
    
    public static void endTrollSelection(){
    	for(int j=0;j<DataMain.getInstance().getDataTrolleSelection().getListeCase().size();j++){
    		DataMain.getInstance().getDataPlateau().killNain(DataMain.getInstance().getDataTrolleSelection().getListeCase().get(j)[0],DataMain.getInstance().getDataTrolleSelection().getListeCase().get(j)[1]);
    	}
    	DataMain.getInstance().getDataTrolleSelection().endTrollePrise();
    }
    
    public static void ProposerAbandon(){
    	EauCalmeMain.setAbandontPan();
    }
    
}
