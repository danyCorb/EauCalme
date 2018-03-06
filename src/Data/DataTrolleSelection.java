package Data;

import java.util.ArrayList;
import java.util.List;

public class DataTrolleSelection {
	private boolean isSelectionMode;
	private List<int[]> listeCase;
	private int caseX,caseY;
	
	public DataTrolleSelection(){
		isSelectionMode=false;
		listeCase=new ArrayList<>();
		caseX=0;
		caseY=0;
	}
	
	public void startNewTrollePrise(int x,int y){
		caseX=x; caseY=y;
		listeCase.clear();
		isSelectionMode=true;
	}
	
	public void endTrollePrise(){
		isSelectionMode=false;
		listeCase.clear();
		caseX=0;
		caseY=0;
	}
	
	public void addCase(int x,int y){
		boolean existe=false;
		int j;
		for(j=0;j<listeCase.size();j++){
			if(listeCase.get(j)[0]==x && listeCase.get(j)[1]==y){
				existe=true;
				break;
			}
		}
		if(!existe)
			listeCase.add(new int[]{x,y});
	}
	public void removeCase(int x,int y){
		int j;
		for(j=0;j<listeCase.size();j++){
			if(listeCase.get(j)[0]==x && listeCase.get(j)[1]==y){
				listeCase.remove(j);
				break;
			}
		}
	}

	public boolean isSelectionMode() {
		return isSelectionMode;
	}

	public List<int[]> getListeCase() {
		return listeCase;
	}

	public int getCaseX() {
		return caseX;
	}

	public int getCaseY() {
		return caseY;
	}
	
	

}
