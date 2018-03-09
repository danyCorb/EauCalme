package InterfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import MainPackage.EauCalmeMain;

public class MenuPanel extends JPanel{
	
	private JButton 
		nouvellePartieContreIA=new JButton("Nouvelle partie contre une IA"),
		nouvellePartieContreUnAutreJoueur=new JButton("Nouvelle partie contre un joueur"),
		voirLesScores=new JButton("Voir les scores"),
		voirUnePartie=new JButton("Voir une partie (replay)"),
		joueurAIContreIA=new JButton("Jouer IA contre IA");
	private Box vBox=Box.createVerticalBox();
		
	public MenuPanel(){
		this.add(vBox);
		vBox.add(nouvellePartieContreIA);
		vBox.add(nouvellePartieContreUnAutreJoueur);
		vBox.add(voirLesScores);
		vBox.add(voirUnePartie);
		vBox.add(joueurAIContreIA);
		
		nouvellePartieContreIA.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.setGamePanel();
			}
		});
		
		nouvellePartieContreUnAutreJoueur.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.setChoseIpPanel();
			}
		});
		
		voirLesScores.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.setScorePanel();
			}
		});
		
		voirUnePartie.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.setVoirPartiePanel();
			}
		});
		
		joueurAIContreIA.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.setGamePanel();
			}
		});
		
	}
}
