package MainPackage;
import java.sql.Date;


import DAO.JoueurDAO;
import DAO.MainDAO;
import Data.DataMain;
import Events.KeyBoardEvent;
import Events.MouseEvent;
import Events.WindowEvent;
import InterfaceGraphique.DrawingPan;
import InterfaceGraphique.Fenetre;
import InterfaceGraphique.JoueurContreIAPanel;
import InterfaceGraphique.MenuPanel;
import InterfaceGraphique.SelectNamePanel;

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
	
	

	public static void main(String[] args) {
		
		f.setContentPane(snp);
		
		
		f.addWindowListener(we);
		f.addMouseListener(me);
		f.addKeyListener(kbe);
		
		f.setVisible(true);
		
	}
	
	public static void setMenuPanel(){
		f.setContentPane(mp);
		f.setVisible(true);
	}
	
	public static void setGamePanel(){
		f.setContentPane(dp);
		f.setVisible(true);
		f.LaunchDrawing();
	}
	public static void setChoseIpPanel(){
		f.setContentPane(jcIAp);
		f.setVisible(true);
	}
	

}
