package Entite;

public class Deplacement {
	private int id;
	private int nb_coup;
	private int deplacement_depart;
	private int deplacement_arrive;
	
	
	public Deplacement(){}
	
	public Deplacement(int id,int depart,int arrive, int nb_coup){
		this.id=id;
		this.deplacement_depart=depart;
		this.deplacement_arrive=arrive;
		this.nb_coup=nb_coup;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNb_coup() {
		return nb_coup;
	}
	public void setNb_coup(int nb_coup) {
		this.nb_coup = nb_coup;
	}
	public int getDeplacement_depart() {
		return deplacement_depart;
	}
	public void setDeplacement_depart(int deplacement_depart) {
		this.deplacement_depart = deplacement_depart;
	}
	public int getDeplacement_arrive() {
		return deplacement_arrive;
	}
	public void setDeplacement_arrive(int deplacement_arrive) {
		this.deplacement_arrive = deplacement_arrive;
	}

}
