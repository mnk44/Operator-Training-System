package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logic.GenerateTrain;

public class Train extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ArrayList<String> var = new ArrayList<>();
	private ArrayList<String> varB = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Train dialog = new Train(9);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public Train(int id_process) throws SQLException {
		ArrayList<String> c = GenerateTrain.getVariables(id_process);
		int pos =0;
		for(int i=0; i<c.size(); i++){
			if(c.get(i)!="&"){
				var.add(c.get(i));
			}else{
				pos = i;
				i=c.size()+3; 
			}
		}
		for(int i=pos; i<c.size(); i++){
			varB.add(c.get(i));
		}
		setTitle("Entrenamiento Variables");
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewUser.class.getResource("/img/Captura de pantalla (133).png")));
		setResizable(false);
		setBounds(100, 100, 691, 526);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 157, 252, 213);
		contentPanel.add(scrollPane);

		JList<String> noAut = new JList<String>(new AbstractListModel<String>(){	
			private static final long serialVersionUID = 599156348656687464L;
			
			public int getSize() {
				return var.size(); 
			}

			public String getElementAt(int i) {
				return var.get(i);
			}

		});
		noAut.setEnabled(false);
		scrollPane.setViewportView(noAut);
	}

}
