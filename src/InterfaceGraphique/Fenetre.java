package InterfaceGraphique;
import javax.swing.JFrame;

import Data.DataMain;
import Events.KeyBoardEvent;
import Events.MouseEvent;

public class Fenetre extends JFrame{
	private DrawingThread dt;
	
	
	private static Fenetre INSTANCE =new Fenetre();
	public static Fenetre getInstance()
    {   return INSTANCE;
    }
	
	
	private Fenetre(){
		this.setTitle("Eau Calme THUD");
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    
	    this.setSize(600, 600);
		DataMain.getInstance().getDataFenetre().setH(600);
		DataMain.getInstance().getDataFenetre().setW(600);

	}
	
	
	/**
	 * Launch the main loop drawing
	 */
	public void LaunchDrawing(){
		dt=new DrawingThread();
		dt.start();
	}
	
	private class DrawingThread extends Thread {
		public void run(){
			while(true){
				getContentPane().repaint();
				try {
					Thread.sleep(1000/30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Close window and end program when red cross is clic
	 * @return 
	 */
	public void closeWindow(){
		if(dt!=null)
			dt.stop();
		this.dispose();
		System.exit(0);
	}
	
	
	public void addMouseListener(MouseEvent ml){
		this.getContentPane().addMouseListener(ml);
		this.getContentPane().addMouseMotionListener(ml);
	}
	public void addKeyListener(KeyBoardEvent ml){
		super.addKeyListener(ml);
	}
	
	
}
