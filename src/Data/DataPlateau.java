package Data;

public class DataPlateau {
	Pion plateau[][];
	
	public DataPlateau(){
		plateau= new Pion[15][];
		for(int j=0;j<15;j++)
			plateau[j]=new Pion[15];
		
		writeInitPlateau();
	}
	
	public void PrintTable(){
		for(int j=0;j<plateau.length;j++){
			for(int k=0;k<plateau[j].length;k++)
			{
				System.out.print(plateau[j][k]+" ");
			}
			System.out.println("");
		}
	}

	public Pion getPionInCase(int x, int y){
		if(x>=0&&x<15&&y>=0&&y<15)
			return plateau[x][y];
		return Pion.Null;
	}
	
	
	public void writeInitPlateau(){
		/*plateau=new Pion[][]{
			{Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Nain,Pion.Nain,Pion.Vide,Pion.Nain,Pion.Nain,Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null,Pion.Null,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null},
			{Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain},
			{Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Trolle,Pion.Trolle,Pion.Trolle,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain},
			{Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Trolle,Pion.Rocher,Pion.Trolle,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide},
			{Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Trolle,Pion.Trolle,Pion.Trolle,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain},
			{Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain},
			{Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null,Pion.Null,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Nain,Pion.Nain,Pion.Vide,Pion.Nain,Pion.Nain,Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Null}
		};*/
		
		plateau=new Pion[][]{
			{Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Nain,Pion.Nain,Pion.Vide,Pion.Nain,Pion.Nain,Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null,Pion.Null,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null},
			{Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain},
			{Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Trolle,Pion.Trolle,Pion.Trolle,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain},
			{Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Trolle,Pion.Rocher,Pion.Trolle,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide},
			{Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Trolle,Pion.Trolle,Pion.Trolle,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain},
			{Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain},
			{Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Nain,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Vide,Pion.Nain,Pion.Null,Pion.Null,Pion.Null,Pion.Null},
			{Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Nain,Pion.Nain,Pion.Vide,Pion.Nain,Pion.Nain,Pion.Null,Pion.Null,Pion.Null,Pion.Null,Pion.Null}
		};
	}
	
	public void bougerPiece(int x,int y,int x2,int y2){
		plateau[x2][y2]=plateau[x][y];
		plateau[x][y]=Pion.Vide;
	}
	
	public int getPointTrolle(){
		int j,k,score;
		for(j=0,score=0;j<plateau.length;j++){
			for(k=0;k<plateau[j].length;k++){
				if(plateau[j][k]==Pion.Trolle)
					score+=4;
			}
		}
		return score;
	}
	
	public int getPointNain(){
		int j,k,score;
		for(j=0,score=0;j<plateau.length;j++){
			for(k=0;k<plateau[j].length;k++){
				if(plateau[j][k]==Pion.Nain)
					score+=1;
			}
		}
		return score;
	}
	
	public void killNain(int x,int y){
		if(getPionInCase(x, y)==Pion.Nain)
			plateau[x][y]=Pion.Vide;
	}
	

}
