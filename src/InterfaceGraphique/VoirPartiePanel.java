package InterfaceGraphique;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DAO.PartieDAO;
import Entite.Partie;
import MainPackage.EauCalmeMain;

public class VoirPartiePanel extends JPanel{
	private JButton retour=new JButton("Retour");
	private JScrollPane jsp;
	private JPanel panelIntern=new JPanel();
	private Box vBox=Box.createVerticalBox();
	private List<Partie> lp;
	
	
	public VoirPartiePanel() {
		jsp=new JScrollPane(panelIntern);
		this.setLayout(new BorderLayout());
		this.add(retour, BorderLayout.NORTH);
		this.add(jsp,BorderLayout.CENTER);
		panelIntern.add(vBox);
		
		lp=new ArrayList<>();
		
		reloadPanel();
		
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EauCalmeMain.setMenuPanel();
			}
		});
	}
	
	private Box ajouterPartie(String text,int idListPartie){
		Box b=Box.createHorizontalBox();
		JButton jb=new JButton("Jouer");
		jb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EauCalmeMain.startViewGame(lp.get(idListPartie));
			}
		});
		
		b.add(new JLabel(text));
		b.add(jb);
		return b;
	}
	
	
	public void reloadPanel(){
		vBox.removeAll();
		List<String> texts=PartieDAO.getPartieForView();
		
		lp=PartieDAO.getListPartie();
		
		for(int j=0;j<texts.size();j++){
			vBox.add(ajouterPartie(texts.get(j),j));
		}
	}
	
	
	
	
}
