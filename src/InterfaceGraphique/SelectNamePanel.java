package InterfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Data.DataMain;
import MainPackage.EauCalmeMain;

public class SelectNamePanel extends JPanel{
	private JTextField jtf=new JTextField();
	private JButton jb=new JButton("Valider");
	private JLabel jl=new JLabel("Votre nom:");
	private Box vBox=Box.createVerticalBox();
	
	public SelectNamePanel(){
		this.add(vBox);
		vBox.add(jl);
		vBox.add(jtf);
		vBox.add(jb);
		
		jtf.setText(DataMain.getInstance().getDataPlayer().getNom());
		
		jb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(jtf.getText().compareTo("")!=0){
					DataMain.getInstance().getDataPlayer().setNom(jtf.getText());
					DataMain.getInstance().getDataPlayer().saveName();
					EauCalmeMain.setMenuPanel();
				}
			}
		});
		
	}
}
