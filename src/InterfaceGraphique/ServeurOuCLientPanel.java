package InterfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import MainPackage.EauCalmeMain;

public class ServeurOuCLientPanel extends JPanel{
	private JButton 
	serveur=new JButton("Serveur"),
	client=new JButton("Client"),
	retour=new JButton("Retour");
	
	private Box vBox=Box.createVerticalBox();
	
	public ServeurOuCLientPanel() {
		this.add(vBox);
		vBox.add(serveur);
		vBox.add(client);
		vBox.add(retour);
		
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.setMenuPanel();
			}
		});
		
		serveur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.setEnAttente();
				try {
					EauCalmeMain.startServeur();
					EauCalmeMain.setGamePanel();
				} catch (IOException e1) {
					EauCalmeMain.setMenuPanel();
				}
			}
		});
		
		client.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.setChoseIpPanel();
			}
		});
		
		
	}

}
