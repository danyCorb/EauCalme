package InterfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MainPackage.EauCalmeMain;

public class FinDePartiePan extends JPanel{
	
	private JButton 
		retour=new JButton("Retour au menu");
	private JLabel text=new JLabel("");
	
	private Box vBox=Box.createVerticalBox();
		
	public FinDePartiePan(){
		this.add(vBox);
		vBox.add(text);
		vBox.add(retour);
		
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.setMenuPanel();
			}
		});
		
		
	}
	
	public void setScore(int moi,int adv){
		if(moi>adv){
			text.setText("Vous avez gangé avec "+moi+" point contre "+adv) ;
		}
		else{
			 text.setText("Vous avez perdu avec "+moi+" point contre "+adv);
		}
	}
	
}