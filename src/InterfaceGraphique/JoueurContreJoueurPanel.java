package InterfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Data.DataMain;
import Entite.Joueur;
import Jeu.Jeu;
import MainPackage.EauCalmeMain;

public class JoueurContreJoueurPanel extends JPanel{
	private JTextField jtf1=new JTextField();
	private JTextField jtf2=new JTextField();
	

	private JButton jb=new JButton("Valider");
	private JLabel jl=new JLabel("URL:");
	private JLabel jl2=new JLabel("Port intern:");
	private Box vBox=Box.createVerticalBox();
	
	public JoueurContreJoueurPanel(){
		this.add(vBox);
		vBox.add(jl);
		vBox.add(jtf1);
		vBox.add(jl2);
		vBox.add(jtf2);
		vBox.add(jb);
		
		
		jb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(jtf1.getText().compareTo("")!=0 && jtf2.getText().compareTo("")!=0){
					DataMain.getInstance().getDataPartie().setAdversaire(new Joueur(0,"Joueur "+jtf1.getText()) );
					EauCalmeMain.startCommunication(jtf1.getText(),Integer.parseInt(jtf2.getText()),new Jeu());
				}
			}
		});
		
	}
}
