package InterfaceGraphique;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import Events.KeyBoardEvent;
import Events.MouseEvent;

public class Fenetre extends JFrame{
	private DrawingThread dt;
	private DrawingPan dp;
	
	private static Fenetre INSTANCE =new Fenetre();
	public static Fenetre getInstance()
    {   return INSTANCE;
    }
	
	
	private Fenetre(){
		this.setTitle("Eau Calme THUD");
	    this.setSize(400, 500);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    
	    /*
	     * Set the writing panel
	     */
	    this.setContentPane((dp=new DrawingPan()));
	}
	
	
	/**
	 * Launch the main loop drawing
	 */
	public void LaunchDrawing(){
		this.setVisible(true);
		dt=new DrawingThread();
		dt.start();
	}
	
	private class DrawingThread extends Thread {
		public void run(){
			while(true){
				dp.repaint();
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
		dt.stop();
		this.dispose();
		System.exit(0);
	}
	
	
	public void addMouseListener(MouseEvent ml){
		dp.addMouseListener(ml);
		dp.addMouseMotionListener(ml);
	}
	public void addKeyListener(KeyBoardEvent ml){
		super.addKeyListener(ml);
	}
	
}
