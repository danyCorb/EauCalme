package InterfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Jeu.Jeu;
import MainPackage.EauCalmeMain;

public class ValiderAbandonPan extends JPanel{
	
	private JButton 
		Oui=new JButton("Valider"),
		Non=new JButton("Refuser");
	private JLabel text=new JLabel("L'adversaire veux abandonner"),
			scoreT=new JLabel("Trolle : "),scoreN=new JLabel("Nain : "),
			scoreAvant=new JLabel();
	
	private Box vBox=Box.createVerticalBox();
		
	public ValiderAbandonPan(){
		this.add(vBox);
		vBox.add(scoreAvant);
		vBox.add(scoreT);
		vBox.add(scoreN);
		vBox.add(text);
		vBox.add(Oui);
		vBox.add(Non);
		
		Non.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((Jeu)EauCalmeMain.jl).valideAbandon=1;
				EauCalmeMain.returnToGame();
			}
		});
		
		Oui.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((Jeu)EauCalmeMain.jl).valideAbandon=2;
				EauCalmeMain.returnToGame();
			}
		});
		
	}
	
	public void setScore(int t,int n,int pointMancheAv[]){
		if(pointMancheAv!=null){
			scoreAvant.setText("Mache 1: "+pointMancheAv[0]+" (Moi) / "+pointMancheAv[1]+"(adversaire)");
		}
		
		scoreT.setText("Trolle : "+t);
		scoreN.setText("Nain : "+n);
	}
	
}