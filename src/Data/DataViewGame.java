package Data;

import java.util.ArrayList;
import java.util.List;

import DAO.DeplacementDAO;
import DAO.PositionDAO;
import Entite.Deplacement;
import Entite.Partie;
import Entite.Position;

public class DataViewGame {
	private Partie part;
	private List<Deplacement> dep;
	private List<Position> pos;
	private int tour=0;
	
	
	public DataViewGame(){
		dep=new ArrayList<>();
		pos=new ArrayList<>();
	}
	
	public int loadPartie(Partie p){
		this.part=p;
		dep.clear();
		pos.clear();
		
		dep=DeplacementDAO.getDeplacementFromPartie(part);
		loadPosition();
		
		System.out.println("load OK part:"+p.getId()+" dep:"+ dep.size()+" pos:"+pos.size());
		
		
		return 0;
	}
	
	private void loadPosition(){
		for(Deplacement d : dep){
			if(!checkIfPositionExist(d.getDeplacement_arrive())){
				pos.add(PositionDAO.getPositionById(d.getDeplacement_arrive()));
			}
			if(!checkIfPositionExist(d.getDeplacement_depart())){
				pos.add(PositionDAO.getPositionById(d.getDeplacement_depart()));
			}
		}
	}
	
	private boolean checkIfPositionExist(int id){
		for(Position p: pos){
			if(p!=null && p.getId()==id){
				return true;
			}
		}
		return false;
	}
	
	public int getNbDeplacement(){
		return dep.size();
	}
	
	public Deplacement getDeplacementByNo(int no){
		return dep.get(no);
	}
	
	public Position getPositionById(int id){
		for(Position p : pos){
			if(p!=null && p.getId()==id){
				return p;
			}
		}
		return null;
	}
	
	public void restartTour(){
		this.tour=0;
	}
	
	public boolean isTheEnd(){
		return !(tour<dep.size());
	}
	
	public Deplacement getActualDeplacement(){
		return dep.get(tour);
	}
	public void nextTour(){
		if(tour<dep.size()-1)
			tour++;
	}
	public void retourTour(){
		if(tour>0)
			tour--;
	}

	public int getTour() {
		return tour;
	}
	
	
	
}
