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
		return plateau[x][y];
	}
	
	
	public void writeInitPlateau(){
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
	

}
