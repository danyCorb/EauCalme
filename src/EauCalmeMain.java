import Data.DataMain;
import Events.KeyBoardEvent;
import Events.MouseEvent;
import Events.WindowEvent;
import InterfaceGraphique.Fenetre;

public class EauCalmeMain {

	public static void main(String[] args) {
		Fenetre f=Fenetre.getInstance();
		DataMain dm=DataMain.getInstance();
		
		WindowEvent we=new WindowEvent(f);
		MouseEvent me=new MouseEvent();
		KeyBoardEvent kbe=new KeyBoardEvent();
		
		f.addWindowListener(we);
		f.addMouseListener(me);
		f.addKeyListener(kbe);
		
		
		f.LaunchDrawing();
		
	}

}
