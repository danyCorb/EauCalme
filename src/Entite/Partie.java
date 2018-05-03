package Entite;

public class Partie {
	private Joueur j1,j2;
	private boolean j1Commence;
	private int scoreJ1,scoreJ2;
	private long date_joue;
	private int id;
	
	public Partie(){}
	public Partie(int id,Joueur j1,Joueur j2, boolean j1Commence,int scoreJ1,int scoreJ2,long date_joue){
		this.id=id;
		this.j1=j1;
		this.j2=j2;
		this.j1Commence=j1Commence;
		this.scoreJ1=scoreJ1;
		this.scoreJ2=scoreJ2;
		this.date_joue=date_joue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Joueur getJ1() {
		return j1;
	}
	public void setJ1(Joueur j1) {
		this.j1 = j1;
	}
	public Joueur getJ2() {
		return j2;
	}
	public void setJ2(Joueur j2) {
		this.j2 = j2;
	}
	public boolean isJ1Commence() {
		return j1Commence;
	}
	public void setJ1Commence(boolean j1Commence) {
		this.j1Commence = j1Commence;
	}
	public int getScoreJ1() {
		return scoreJ1;
	}
	public void setScoreJ1(int scoreJ1) {
		this.scoreJ1 = scoreJ1;
	}
	public int getScoreJ2() {
		return scoreJ2;
	}
	public void setScoreJ2(int scoreJ2) {
		this.scoreJ2 = scoreJ2;
	}
	public long getDate_joue() {
		return date_joue;
	}
	public void setDate_joue(long date_joue) {
		this.date_joue = date_joue;
	}
	
	
	
	
}
