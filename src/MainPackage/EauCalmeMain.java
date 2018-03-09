package MainPackage;
import java.io.IOException;

import DAO.MainDAO;
import Data.DataMain;
import Events.KeyBoardEvent;
import Events.MouseEvent;
import Events.WindowEvent;
import InterfaceGraphique.AbandonPan;
import InterfaceGraphique.DrawingPan;
import InterfaceGraphique.EnAttentePanel;
import InterfaceGraphique.Fenetre;
import InterfaceGraphique.JoueurContreIAPanel;
import InterfaceGraphique.MenuPanel;
import InterfaceGraphique.SelectNamePanel;
import InterfaceGraphique.VoirPartiePanel;
import InterfaceGraphique.VoirScorePanel;
import Reseau.APIClient;
import Reseau.APIServeur;

public class EauCalmeMain {
	
	private static Fenetre f=Fenetre.getInstance();
	private static DataMain dm=DataMain.getInstance();
	private static MainDAO mdao=MainDAO.getInstance();
	
	private static WindowEvent we=new WindowEvent(f);
	private static MouseEvent me=new MouseEvent();
	private static KeyBoardEvent kbe=new KeyBoardEvent();
	
	
	private static DrawingPan dp=new DrawingPan();
	private static SelectNamePanel snp=new SelectNamePanel();
	private static MenuPanel mp=new MenuPanel();
	private static JoueurContreIAPanel jcIAp=new JoueurContreIAPanel();
	public static AbandonPan ap=new AbandonPan();
	private static EnAttentePanel eap=new EnAttentePanel();
	private static VoirScorePanel vsp=new VoirScorePanel();
	private static VoirPartiePanel vpp=new VoirPartiePanel();
	
	private static APIServeur serveur;
	private static APIClient client;
	

	public static void main(String[] args) {
		
		f.setContentPane(snp);
		
		
		f.addWindowListener(we);
		dp.addMouseListener(me);
		dp.addMouseMotionListener(me);
		dp.addKeyListener(kbe);
		
		
		f.setVisible(true);
		
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
		f.setContentPane(vsp);
		f.setVisible(true);
	}
	
	public static void setVoirPartiePanel(){
		f.setContentPane(vpp);
		f.setVisible(true);
	}
	
	public static void startCommunication(String url,int port){
		try {
			serveur=new APIServeur(port);
			client=new APIClient(url);
			EauCalmeMain.setGamePanel();
			DataMain.getInstance().getFileRequeteGet().offer("stack trace test request");
		} catch (IOException e) {
			e.printStackTrace();
			EauCalmeMain.setMenuPanel();
		}
	}
	
	

}
