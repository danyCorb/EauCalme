package MainPackage;
import java.io.IOException;

import DAO.MainDAO;
import Data.DataMain;
import Entite.Joueur;
import Entite.Partie;
import Events.KeyBoardEvent;
import Events.KeyBoardEventShowGame;
import Events.MouseEvent;
import Events.WindowEvent;
import IAPack.IA;
import InterfaceGraphique.AbandonPan;
import InterfaceGraphique.DrawingPan;
import InterfaceGraphique.DrawingPanViewGame;
import InterfaceGraphique.EnAttentePanel;
import InterfaceGraphique.Fenetre;
import InterfaceGraphique.FinDePartiePan;
import InterfaceGraphique.JoueurContreJoueurPanel;
import InterfaceGraphique.MenuPanel;
import InterfaceGraphique.SelectNamePanel;
import InterfaceGraphique.ValiderAbandonPan;
import InterfaceGraphique.VoirPartiePanel;
import InterfaceGraphique.VoirScorePanel;
import Jeu.Jeu;
import Jeu.JeuListener;
import Jeu.JeuViewGame;
import Reseau.APIClient;
import Reseau.APIServeur;

public class EauCalmeMain {
	
	private static Fenetre f=Fenetre.getInstance();
	private static DataMain dm=DataMain.getInstance();
	private static MainDAO mdao=MainDAO.getInstance();
	
	private static WindowEvent we=new WindowEvent(f);
	private static MouseEvent me=new MouseEvent();
	private static KeyBoardEvent kbe=new KeyBoardEvent();
	private static KeyBoardEventShowGame kbesg=new KeyBoardEventShowGame();
	
	private static DrawingPan dp=new DrawingPan();
	private static SelectNamePanel snp=new SelectNamePanel();
	private static MenuPanel mp=new MenuPanel();
	private static JoueurContreJoueurPanel jcIAp=new JoueurContreJoueurPanel();
	public static AbandonPan ap=new AbandonPan();
	private static EnAttentePanel eap=new EnAttentePanel();
	private static VoirScorePanel vsp=new VoirScorePanel();
	private static VoirPartiePanel vpp=new VoirPartiePanel();
	private static ValiderAbandonPan vap=new ValiderAbandonPan();
	private static FinDePartiePan fpp=new FinDePartiePan();
	public static Configuration configuration;
	public static IA ia=new IA();
	private static DrawingPanViewGame dpvg=new DrawingPanViewGame();
	

	
	public static JeuViewGame jvg=new JeuViewGame();
	public static APIServeur serveur;
	public static APIClient client;
	
	
	public static boolean isConnected=false;
	
	
	public static JeuListener jl=new Jeu();

	public static void main(String[] args) {
		
		
		if(args.length>0){
			configuration=new Configuration(args[0]);
		}
		else{
			configuration=new Configuration("");
		}
		
		if(!configuration.isHaveConfig()){
			f.setContentPane(snp);
		}
		else{
			// start IA vs IA
			DataMain.getInstance().getDataPartie().setAdversaire(new Joueur(0,"Joueur IA") );
			EauCalmeMain.startCommunication(configuration.getURLCOnnexion(), configuration.getPortIntern(), new Jeu());
		}
		
		
		f.addWindowListener(we);
		dp.addMouseListener(me);
		dp.addMouseMotionListener(me);
		dp.addKeyListener(kbe);
		
		dpvg.addKeyListener(kbesg);
		
		
		
		f.setVisible(true);
		
		if(configuration.isHaveConfig()){
			// ia launch
			long time=System.currentTimeMillis();
			while(!isConnected && System.currentTimeMillis()-time < 30*1000 ){
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(isConnected)
				ia.startIA();
		}
		
		
	}
	
	public static void setMenuPanel(){
		f.setContentPane(mp);
		f.setVisible(true);
	}
	
	public static void setGamePanel(){
		f.setContentPane(dp);
		f.setVisible(true);
		DataMain.getInstance().getDataFenetre().setW(f.getContentPane().getWidth());
		DataMain.getInstance().getDataFenetre().setH(f.getContentPane().getHeight());
		f.LaunchDrawing();
	}
	public static void setChoseIpPanel(){
		f.setContentPane(jcIAp);
		f.setVisible(true);
	}
	
	public static void setAbandontPan(){
		f.setContentPane(ap);
		f.setVisible(true);
	}
	
	public static void returnToGame(){
		f.setContentPane(dp);
		f.setVisible(true);
		DataMain.getInstance().getDataFenetre().setW(f.getContentPane().getWidth());
		DataMain.getInstance().getDataFenetre().setH(f.getContentPane().getHeight());
	}
	
	
	public static void setEnAttente(){
		f.setContentPane(eap);
		f.setVisible(true);
		f.repaint();
	}
	
	public static void setScorePanel(){
		vsp.reloadPanel();
		f.setContentPane(vsp);
		f.setVisible(true);
	}
	
	
	public static void setVoirPartiePanel(){
		vpp.reloadPanel();
		f.setContentPane(vpp);
		f.setVisible(true);
	}
	
	public static void setValiderAbandonPanel(int trolle,int nain,int mancheAv[]){
		vap.setScore(trolle,nain, mancheAv);
		f.setContentPane(vap);
		f.setVisible(true);
	}
	
	public static void setFinDePartiePan(int scoreMoi,int scoreAdv){
		EauCalmeMain.isConnected=false;
		fpp.setScore(scoreMoi, scoreAdv);
		f.setContentPane(fpp);
		f.setVisible(true);
	}
	
	public static void startCommunication(String url,int port,JeuListener jeu){
		try {
			serveur=new APIServeur(port);
			client=new APIClient(url);
			EauCalmeMain.setGamePanel();
			
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			isConnected=true;
			
			jl=jeu;
			if(!jl.startJeu()){ // echec de connexion
				EauCalmeMain.setMenuPanel();
				isConnected=false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			EauCalmeMain.setMenuPanel();
			isConnected=false;
		}
	}
	
	public static void startViewGame(Partie p){
		// load data
		DataMain.getInstance().getDataViewGame().loadPartie(p);
		
		
		// set drawing panel
		f.setContentPane(dpvg);
		f.setVisible(true);
		DataMain.getInstance().getDataFenetre().setW(f.getContentPane().getWidth());
		DataMain.getInstance().getDataFenetre().setH(f.getContentPane().getHeight());
		f.LaunchDrawing();
		
		// set event and panel
		
		// launch view
		jvg.startJeuViewGame();
	}
	
	public static boolean checkAdvProposeAbandont(){
		return (f.getContentPane() instanceof ValiderAbandonPan);
	}
	
	public static boolean checkIsAIA(){
		return ia.isIARun();
	}
	
	public static boolean getChoiceIaAbandon(){
		return ia.getValideAbandonAdv();
	}
	

}
