package InterfaceGraphique;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Data.DataMain;
import MainPackage.EauCalmeMain;

public class JoueurContreIAPanel extends JPanel{
	private JTextField jtf1=new JTextField(),
						jtf2=new JTextField(),
						jtf3=new JTextField(),
						jtf4=new JTextField();
	
	private JLabel point=new JLabel("."),
			point1=new JLabel("."),
			point2=new JLabel(".");
	private JButton jb=new JButton("Valider");
	private JLabel jl=new JLabel("IP:");
	private Box vBox=Box.createVerticalBox();
	private Box hBox=Box.createHorizontalBox();
	
	public JoueurContreIAPanel(){
		this.add(vBox);
		vBox.add(jl);
		vBox.add(hBox);
		vBox.add(jb);
		
		jtf1.setPreferredSize(new Dimension(70, 25));
		jtf2.setPreferredSize(new Dimension(70, 25));
		jtf3.setPreferredSize(new Dimension(70, 25));
		jtf4.setPreferredSize(new Dimension(70, 25));
		
		hBox.add(jtf1);
		hBox.add(point);
		hBox.add(jtf2);
		hBox.add(point1);
		hBox.add(jtf3);
		hBox.add(point2);
		hBox.add(jtf4);
		
		jb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(jtf1.getText().compareTo("")!=0 && jtf2.getText().compareTo("")!=0 && jtf3.getText().compareTo("")!=0 && jtf4.getText().compareTo("")!=0){
					EauCalmeMain.setEnAttente();
					try {
						EauCalmeMain.startClient(jtf1.getText()+"."+jtf3.getText()+"."+jtf3.getText()+"."+jtf4.getText());
						EauCalmeMain.setGamePanel();
					} catch (UnknownHostException e) {
						EauCalmeMain.setMenuPanel();
					} catch (IOException e) {
						EauCalmeMain.setMenuPanel();
					}
					
				}
			}
		});
		
	}
}
