package Jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.StyleSheet.ListPainter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Data.DataMain;
import Data.DataPionSelectione;
import Data.Pion;
import MainPackage.EauCalmeMain;
import Reseau.JSonGenerator;

public class Jeu implements JeuListener  {
	private boolean start;
	private int manche;
	private boolean monTour=false;
	private boolean jeProposeAbandon=false;
	private int trolleMouvement[][]=null;
	private static long tempsAttenteEntreCoup=30*1000;
	
	public int valideAbandon=0;
	public int scorePartie1[]=null;
	
	public Jeu(){
		DataMain.getInstance().getDataPionSelectione().unselectPion();
		DataMain.getInstance().getDataPlateau().writeInitPlateau();
		start=false;
		manche=1;


		JSonGenerator.setShot(0);
		this.jeProposeAbandon=false;
		
	}

    public void selectedCase(int posX, int posY){
    	if(EauCalmeMain.jl.getTour()){
	        int caseX = DataMain.getInstance().getDataPionSelectione().getX();
	        int caseY = DataMain.getInstance().getDataPionSelectione().getY();
	        if(this.checkIsTrayCase(posX, posY) && !DataMain.getInstance().getDataTrolleSelection().isSelectionMode())
	        {
	        	if (caseX == posX && caseY == posY){
	                DataMain.getInstance().getDataPionSelectione().unselectPion();
	            } else if (DataMain.getInstance().getDataPionSelectione().selectedPion()) {
	            	if(DataMain.getInstance().getDataPionSelectione().estUneCasePourDeplacement(posX, posY)){
		            	try {
		            		String nainCase=null;
		            		List<int[]> listPrise=null;
		            		// Envoi information
		            		if(DataMain.getInstance().getDataPlateau().getPionInCase(posX, posY)==Pion.Trolle){
		            			listPrise=new ArrayList<>();
		            			listPrise.add(new int[]{posX,posY});
		            			nainCase=DataMain.getInstance().getDataPlateau().getPionOnCase(posX, posY);
		            		}
		            		
		            		this.deplacerPiece(DataMain.getInstance().getDataPionSelectione(), posX, posY);
		            		
		            		if(!DataMain.getInstance().getDataTrolleSelection().isSelectionMode())
		            		{
		            			DataMain.getInstance().getFileRequeteGet().offer(JSonGenerator.genererDeplacement(caseX, caseY, posX, posY, listPrise,jeProposeAbandon,nainCase));
								// deplacement
		            			
		            			
								monTour=false;
								
								// reception information
								this.coupAdverce();
								
		            		}
		            		else
		            		{
		            			trolleMouvement=new int[][]{{caseX,caseY},{posX,posY}};
		            		}
		            		
		            		DataMain.getInstance().getDataPionSelectione().unselectPion();
		            		
						} catch (IOException e) {
							e.printStackTrace();
						}
	            	}
	            	else
	            		DataMain.getInstance().getDataPionSelectione().unselectPion();
	            	
	            	
	            }else{
	            	if(DataMain.getInstance().getDataPlateau().getPionInCase(posX, posY)!=Pion.Vide){
	            		Pion pionActuel=DataMain.getInstance().getDataPlateau().getPionInCase(posX, posY);
	            		if( (start&&manche==1&&pionActuel==Pion.Nain) || (start&&manche==2&&pionActuel==Pion.Trolle) || 
	                			(!start&&manche==1&&pionActuel==Pion.Trolle) || (!start&&manche==2&&pionActuel==Pion.Nain)	){
		            		DataMain.getInstance().getDataPionSelectione().setX(posX);
		                    DataMain.getInstance().getDataPionSelectione().setY(posY);
		                    DataMain.getInstance().getDataPionSelectione().setCaseDispoDeplacement(this.getCaseValidePourDeplacement(posX, posY));
		                    DataMain.getInstance().getDataPionSelectione().setCaseDeplacementSpecial(this.getCaseValidePourDeplacementSpecial(posX, posY));
	            		}
	            	}
	                
	            }
	        }else if(this.checkIsTrayCase(posX, posY) && DataMain.getInstance().getDataTrolleSelection().isSelectionMode()){
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
    }

    
    
    
    public boolean checkIsTrayCase(int x,int y){
    	if(DataMain.getInstance().getDataPlateau().getPionInCase(x, y)!=Pion.Null && DataMain.getInstance().getDataPlateau().getPionInCase(x, y)!=Pion.Rocher)
    		return true;
    	return false;
    }
    
    public int[][] getCaseValidePourDeplacement(int x,int y){
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
    
    
    public int[][] getCaseValidePourDeplacementSpecial(int x,int y){
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
    
    
    public void deplacerPiece(DataPionSelectione dps, int newCaseX,int newCaseY){
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
    
    public void endTrollSelection(){
    	// envoi des données
    	try {
			DataMain.getInstance().getFileRequeteGet().offer(JSonGenerator.genererDeplacement(trolleMouvement[0][0], trolleMouvement[0][1], trolleMouvement[1][0], trolleMouvement[1][1], DataMain.getInstance().getDataTrolleSelection().getListeCase(),jeProposeAbandon,null));
			for(int j=0;j<DataMain.getInstance().getDataTrolleSelection().getListeCase().size();j++){
	    		DataMain.getInstance().getDataPlateau().killNain(DataMain.getInstance().getDataTrolleSelection().getListeCase().get(j)[0],DataMain.getInstance().getDataTrolleSelection().getListeCase().get(j)[1]);
	    	}
	    	DataMain.getInstance().getDataTrolleSelection().endTrollePrise();
	    	
	    	monTour=false;
	    	
	    	this.coupAdverce();
	    	
    	} catch (IOException e) {
		}
    }
    
    public void ProposerAbandon(){
    	EauCalmeMain.setAbandontPan();
    }
    
    public void validerEnvoiDemandeAbandont(){
    	this.jeProposeAbandon=true;
    }



	@Override
	public boolean startJeu() {
		
		if(!electionOrdreDeJeu()){
			return false;
		}
		
		return true;
	}




	@Override
	public boolean electionOrdreDeJeu(){
		double rand=Math.random()*2000.0;
		String value = null;
		boolean connected=false;
		long timeStart=System.currentTimeMillis();
		
		DataMain.getInstance().getFileRequeteGet().offer(rand+"");
		
		while(System.currentTimeMillis()-timeStart<10000 && !connected)
		{
			try {
				value = EauCalmeMain.client.sendGet();
				connected=true;
			} catch (Exception e) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
				}
				//e.printStackTrace();
			}
			
		}
		if(connected){
			if(Double.parseDouble(value) > rand){
				System.out.println("L'autre commence "+Double.parseDouble(value));
				start=false;
				monTour=false;

				this.coupAdverce();
			}
			else{
				System.out.println("Je commence "+Double.parseDouble(value));
				start=true;
				monTour=true;
			}
		}
		return connected;
	}
	
	@Override
	public boolean getTour(){
		return this.monTour;
	}
	
	
	private void coupAdverce() {
		Thread thread = new Thread(){
		    public void run(){
		    	String coup=attendreCoupAutreJoueur((int) Jeu.tempsAttenteEntreCoup);
				JSONParser parser = new JSONParser();
				
				Object obj;
				
				// test si on a surrender la mache precedante
				valideAbandon=1;
				
				try {
					obj = parser.parse(coup);
					boolean demandeAbandon=(Boolean) ((JSONObject) obj).get("surrender");
					
					if(demandeAbandon&& jeProposeAbandon){
						startManche2();
						valideAbandon=0;
					}
					else if( !demandeAbandon && jeProposeAbandon){
						jeProposeAbandon=false;
					}
					else if(demandeAbandon){
						valideAbandon=0;
						EauCalmeMain.setValiderAbandonPanel(DataMain.getInstance().getDataPlateau().getPointTrolle(), DataMain.getInstance().getDataPlateau().getPointNain(), scorePartie1);
						while(valideAbandon==0){
							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					
					if(valideAbandon==1){ // pas validation de l'abandont
						valideAbandon=0;
						String caseD=(String) ((JSONObject) obj).get("slot_1");
						String caseA=(String) ((JSONObject) obj).get("slot_2");
						JSONArray tableauPrise=(JSONArray) ((JSONObject) obj).get("slot_eat");
						
						int posX= (caseD.toCharArray()[0]-'A');
						int posY=(Integer.parseInt(caseD.substring(1))-1);
						
						System.out.println("Mouvement: "+ (caseD.toCharArray()[0]-'A')+" " + (Integer.parseInt(caseD.substring(1))-1) +" To "+(caseA.toCharArray()[0]-'A')+" " + (Integer.parseInt(caseA.substring(1))-1));
						
						DataMain.getInstance().getDataPionSelectione().setX(posX);
	                    DataMain.getInstance().getDataPionSelectione().setY(posY);
	                    DataMain.getInstance().getDataPionSelectione().setCaseDispoDeplacement(getCaseValidePourDeplacement(posX, posY));
	                    DataMain.getInstance().getDataPionSelectione().setCaseDeplacementSpecial(getCaseValidePourDeplacementSpecial(posX, posY));
						
	                    deplacerPiece(DataMain.getInstance().getDataPionSelectione(), (caseA.toCharArray()[0]-'A'), (Integer.parseInt(caseA.substring(1))-1));
	                    
	                    if(tableauPrise.size()>0){
	                    	for(int j=0;j<tableauPrise.size();j++)
	                    	{
	                    		String nain=(String) tableauPrise.get(j);
	                    		System.out.println("Prise de :"+nain);
	                    		DataMain.getInstance().getDataPlateau().killNainById(nain);
	                    	}
	                    }
						
	                    DataMain.getInstance().getDataPionSelectione().unselectPion();
	                    DataMain.getInstance().getDataTrolleSelection().endTrollePrise();
						monTour=true;
					}
					else if(valideAbandon!=0){
						try {
							DataMain.getInstance().getFileRequeteGet().offer(JSonGenerator.genererDeplacement(0, 0, 0, 0, null,true,null));
							startManche2();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
		    }
		  };

		  thread.start();
	}
	
	private String attendreCoupAutreJoueur(int delay){
		long timeStart=System.currentTimeMillis();
		while(System.currentTimeMillis()-timeStart<delay)
		{
			try {
				return EauCalmeMain.client.sendGet();
			} catch (Exception e) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
				}
			}
		}
		return null;
	}

	@Override
	public void startManche2() {
		if(this.manche==1){
			/*
			 * Score
			 */
			if(this.start==true)
				this.scorePartie1=new int[]{DataMain.getInstance().getDataPlateau().getPointNain(),DataMain.getInstance().getDataPlateau().getPointTrolle()};
			else
				this.scorePartie1=new int[]{DataMain.getInstance().getDataPlateau().getPointTrolle(),DataMain.getInstance().getDataPlateau().getPointNain()};
			
			/*
			 * Réinitialisation
			 */
			DataMain.getInstance().getDataPionSelectione().unselectPion();
			DataMain.getInstance().getDataPlateau().writeInitPlateau();
			JSonGenerator.setShot(0);
			this.jeProposeAbandon=false;
			
			/*
			 * Changer ordre
			 */
			this.manche=2;
			this.monTour = !this.start;
			
			if(!this.monTour)
				this.coupAdverce();
			
		}
		else{
			int scoreMoi=scorePartie1[0];
			int scoreAdv=scorePartie1[1];
			if(this.start){
				scoreMoi+=DataMain.getInstance().getDataPlateau().getPointTrolle();
				scoreAdv+=DataMain.getInstance().getDataPlateau().getPointNain();
			}else{
				scoreMoi+=DataMain.getInstance().getDataPlateau().getPointNain();
				scoreAdv+=DataMain.getInstance().getDataPlateau().getPointTrolle();
			}
			EauCalmeMain.setFinDePartiePan(scoreMoi, scoreAdv);
		}
	}
	
    
}
