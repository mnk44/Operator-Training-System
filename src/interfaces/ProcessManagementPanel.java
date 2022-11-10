package interfaces;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class ProcessManagementPanel extends JPanel {

	private static final long serialVersionUID = -7006963620558751080L;
 
	public ProcessManagementPanel() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setBounds(100, 100, 838, 433);
		setLayout(null);
	}

}
