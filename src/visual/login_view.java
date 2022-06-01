package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class login_view extends JDialog {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			login_view dialog = new login_view();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public login_view() {		
		setBounds(100, 100, 720, 519);
		getContentPane().setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(191, 53, 146, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}
