package InterfaceGraphique;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DAO.PartieDAO;
import MainPackage.EauCalmeMain;

public class VoirScorePanel extends JPanel{
	private JButton retour=new JButton("Retour");
	private JScrollPane jsp;
	private JPanel panelIntern=new JPanel();
	private Box vBox=Box.createVerticalBox();
	
	public VoirScorePanel() {
		jsp=new JScrollPane(panelIntern);
		this.setLayout(new BorderLayout());
		this.add(retour, BorderLayout.NORTH);
		this.add(jsp,BorderLayout.CENTER);
		panelIntern.add(vBox);
		
		
		reloadPanel();
		
		//vBox.add(new JLabel("Dany Contre IA : victoire IA 10/21 Le 07/03/2018"));
		
		retour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.setMenuPanel();
			}
		});
		
	}
	public void reloadPanel(){
		vBox.removeAll();
		List<String> texts=PartieDAO.getPartieForView();
		for(String t : texts){
			vBox.add(new JLabel(t));
			
		}
	}
	
	
}
