package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JToggleButton;

public class NewUser extends JDialog {

	private static final long serialVersionUID = 4793501210293845117L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JToggleButton active;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewUser dialog = new NewUser(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewUser(int option) {
		if(option == 0){
			setTitle("Nuevo Usuario");
		}else{
			setTitle("Modificar Usuario");
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewUser.class.getResource("/img/Captura de pantalla (133).png")));
		setResizable(false);
		setBounds(100, 100, 691, 555);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setBounds(195, 30, 365, 26);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre(s) y Apellidos:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(15, 33, 205, 20);
		contentPanel.add(lblNewLabel);
		
		active = new JToggleButton("Activo");
		active.setBackground(new Color(255, 255, 201));
		active.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		active.setFont(new Font("Segoe UI", Font.BOLD, 17));
		active.setBounds(577, 25, 83, 33);
		contentPanel.add(active);
	}
}
