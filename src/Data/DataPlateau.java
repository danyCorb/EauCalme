package Data;

public class DataPlateau {
	Pion plateau[][];
	String plateauPion[][];
	
	public DataPlateau(){
		plateau= new Pion[15][];
		for(int j=0;j<15;j++)
			plateau[j]=new Pion[15];
		
		plateauPion= new String[15][];
		for(int j=0;j<15;j++)
			plateauPion[j]=new String[15];
		
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
		
		plateauPion=new String[][]{
			{"","","","","","D26","D25","","D24","D23","","","","",""},
			{"","","","","D27","","","","","",   "D22","","","",""},
			{"","","","D28","","","","","","","",   "D21","","",""},
			{"","","D29","","","","","","","","","",   "D20","",""},
			{"","D30","","","","","","","","","","","",   "D19",""},
			{"D31","","","","","","","","","","","","","",   "D18"},
			{"D32","","","","","T8","T7","T6","","","","","","",   "D17"},
			{"","","","","","","T1","","T5","","","","","",         ""},
			{"D1","","","","","","T2","T3","T4","","","","","",    "D16"},
			{"D2","","","","","","","","","","","","","",    "D15"},
			{"","D3","","","","","","","","","","","",    "D14",""},
			{"","","D4","","","","","","","","","",    "D13","",""},
			{"","","","D5","","","","","","","",    "D12","","",""},
			{"","","","","D6","","","","","",    "D11","","","",""},
			{"","","","","","D7","D8","","D9","D10","","","","",""},
		};
	}
	
	public void bougerPiece(int x,int y,int x2,int y2){
		plateau[x2][y2]=plateau[x][y];
		plateau[x][y]=Pion.Vide;
		
		plateauPion[x2][y2]=plateauPion[x][y];
		plateauPion[x][y]="";
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
	
	public int[] getPionByName(String name){
		int[] ret=null;
		
		for(int j=0;j<plateauPion.length  &&  ret==null;j++){
			for(int k=0;k<plateauPion[j].length && ret == null;k++){
				if(plateauPion[j][k].compareTo(name)==0){
					ret=new int[]{j,k};
				}
			}
		}
		
		
		return ret;
	}
	
	public void killNain(int x,int y){
		if(getPionInCase(x, y)==Pion.Nain)
			plateau[x][y]=Pion.Vide;
	}
	
	public String getPionOnCase(int x,int y){
		return plateauPion[x][y];
	}
	
	public void killNainById(String id){
		
		for(int j=0;j<15;j++)
			for(int k=0;k<15;k++)
			{
				if(plateauPion[j][k].compareTo(id)==0){
					killNain(j, k);
					plateauPion[j][k]="";
				}
			}
		
		
	}
	

}
