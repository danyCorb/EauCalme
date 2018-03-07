package MainPackage;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EnAttentePanel extends JPanel{
	private Box vBox=Box.createVerticalBox();
	private JLabel jl=new JLabel("En Attente");
	public EnAttentePanel() {
		this.add(vBox);
		vBox.add(jl);
	}
	
	
}
