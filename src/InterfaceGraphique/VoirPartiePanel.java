package InterfaceGraphique;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import MainPackage.EauCalmeMain;

public class VoirPartiePanel extends JPanel{
	private JButton retour=new JButton("Retour");
	private JScrollPane jsp;
	private JPanel panelIntern=new JPanel();
	private Box vBox=Box.createVerticalBox();
	
	
	public VoirPartiePanel() {
		jsp=new JScrollPane(panelIntern);
		this.setLayout(new BorderLayout());
		this.add(retour, BorderLayout.NORTH);
		this.add(jsp,BorderLayout.CENTER);
		panelIntern.add(vBox);
		
		for(int j=0;j<50;j++){
			vBox.add(ajouterPartie("Dany Contre IA : victoire IA 10/21 Le 07/03/2018"));
		}
		
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.setMenuPanel();
			}
		});
	}
	
	private Box ajouterPartie(String text){
		Box b=Box.createHorizontalBox();
		b.add(new JLabel(text));
		b.add(new JButton("Jouer"));
		return b;
	}

}
