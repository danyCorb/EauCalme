package Data;

import Entite.Joueur;
import Entite.Partie;

public class DataPartie {
	private boolean iStart;
	private int manche;
	private boolean monTour;
	private boolean jeProposeAbandon;
	private int valideAbandon;
	private int scorePartie1[];
	private Partie partie;
	private Joueur adversaire;
	private int coupActuel=0;
	private int nbDemandeAbandonAdverse;
	private int nbDemandeAbandonMoi;
	
	public Joueur getAdversaire() {
		return adversaire;
	}

	public void setAdversaire(Joueur adversaire) {
		this.adversaire = adversaire;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public DataPartie(){
		iStart=false;
		manche=1;
		monTour=false;
		jeProposeAbandon=false;
		valideAbandon=0;
		scorePartie1=new int[2];
		coupActuel=0;
		nbDemandeAbandonAdverse=0;
		nbDemandeAbandonMoi=0;
	}

	public void initNewGame(){
		iStart=false;
		manche=1;
		jeProposeAbandon=false;
		nbDemandeAbandonAdverse=0;
		nbDemandeAbandonMoi=0;
	}
	
	public boolean isiStart() {
		return iStart;
	}

	public void setiStart(boolean iStart) {
		this.iStart = iStart;
	}

	public int getManche() {
		return manche;
	}

	public void setManche(int manche) {
		this.manche = manche;
	}

	public boolean isMonTour() {
		return monTour;
	}

	public void setMonTour(boolean monTour) {
		coupActuel++;
		this.monTour = monTour;
	}

	public boolean isJeProposeAbandon() {
		return jeProposeAbandon;
	}

	public void setJeProposeAbandon(boolean jeProposeAbandon) {
		this.jeProposeAbandon = jeProposeAbandon;
	}

	public int getValideAbandon() {
		return valideAbandon;
	}

	public void setValideAbandon(int valideAbandon) {
		this.valideAbandon = valideAbandon;
	}

	public int[] getScorePartie1() {
		return scorePartie1;
	}

	public void setScorePartie1(int[] scorePartie1) {
		this.scorePartie1 = scorePartie1;
	}
	
	public void finDeMonTour(){
		this.setMonTour(false);
	}
	
	public void finDuTourDeAdversaire(){
		this.setMonTour(true);
	}
	public void proposerAbandont(){
		this.jeProposeAbandon=true;
	}
	
	public void initialiserPremierTour(boolean jeJouePremier){
		iStart=jeJouePremier;
		monTour=jeJouePremier;
		coupActuel=0;
		nbDemandeAbandonAdverse=0;
		nbDemandeAbandonMoi=0;
	}
	
	public void changerTour(){
		manche=2;
		nbDemandeAbandonAdverse=0;
		nbDemandeAbandonMoi=0;
		monTour=!isiStart();
	}

	public int getCoupActuel() {
		return coupActuel;
	}

	public void setCoupActuel(int coupActuel) {
		this.coupActuel = coupActuel;
	}

	public int getNbDemandeAbandonAdverse() {
		return nbDemandeAbandonAdverse;
	}

	public void setNbDemandeAbandonAdverse(int nbDemandeAbandonAdverse) {
		this.nbDemandeAbandonAdverse = nbDemandeAbandonAdverse;
	}

	public int getNbDemandeAbandonMoi() {
		return nbDemandeAbandonMoi;
	}

	public void setNbDemandeAbandonMoi(int nbDemandeAbandonMoi) {
		this.nbDemandeAbandonMoi = nbDemandeAbandonMoi;
	}
	
	
}
