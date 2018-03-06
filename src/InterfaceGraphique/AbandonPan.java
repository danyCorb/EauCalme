package InterfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MainPackage.EauCalmeMain;

public class AbandonPan extends JPanel{
	
	private JButton 
		Oui=new JButton("Oui"),
		Non=new JButton("Non");
	private JLabel text=new JLabel("Voulez-vous abandoner?"),
			scoreT=new JLabel("Trolle : "),scoreN=new JLabel("Nain : ");
	
	private Box vBox=Box.createVerticalBox();
		
	public AbandonPan(){
		this.add(vBox);
		vBox.add(scoreT);
		vBox.add(scoreN);
		vBox.add(text);
		vBox.add(Oui);
		vBox.add(Non);
		
		Non.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.returnToGame();
			}
		});
		
	}
	
	public void setScore(int t,int n){
		scoreT.setText("Trolle : "+t);
		scoreN.setText("Nain : "+n);
	}
	
}
