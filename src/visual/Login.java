package visual;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import utils.Rol;
import contentClass.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import logic.CheckLogin;

public class Login extends JDialog {
	private static final long serialVersionUID = 4536391610683767780L;
	private JTextField user;
	private JPasswordField passwordField;
	private JButton acept;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Login() {
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/Captura de pantalla (133).png")));
		setTitle("Sistema de Entrenamiento SECPROIT");
		setResizable(false);
		setBounds(100, 100, 412, 351);
		((JComponent) getContentPane()).setBorder(new LineBorder(Color.LIGHT_GRAY));
		getContentPane().setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
		lblUsuario.setBounds(25, 149, 95, 20);
		getContentPane().add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Arial", Font.BOLD, 16));
		lblContrasea.setBounds(25, 189, 95, 20);
		getContentPane().add(lblContrasea);

		user = new JTextField();
		user.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();

				if (Character.isWhitespace(key))
				{
					e.consume();
					getToolkit().beep();
				}
			}
		});
		user.setFont(new Font("Arial", Font.PLAIN, 16));
		user.setColumns(10);
		user.setBounds(125, 147, 195, 26);
		getContentPane().add(user);

		acept = new JButton("Aceptar");
		acept.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(CheckLogin.noEmpty(user.getText(), passwordField.getText())){
					try {
						User uss = CheckLogin.checkPass(user.getText(), passwordField.getText());
						if(uss != null){
							if(uss.getRol() == Rol.ADMINISTRADOR){
								HomePage home = new HomePage(uss);
								home.getFrmSistemaDeEntrenamiento().setVisible(true);
								Login.this.setVisible(false);
							}

						}else{
							JOptionPane.showMessageDialog(null, "Error en las credenciales", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "No pueden haber campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		acept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				acept.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				acept.setBackground(new Color(255, 255, 201));
			}
		});
		acept.setIcon(new ImageIcon(Login.class.getResource("/img/icons8_Checkmark_16.png")));
		acept.setFont(new Font("Segoe UI", Font.BOLD, 17));
		acept.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		acept.setBackground(new Color(255, 255, 201));
		acept.setBounds(125, 247, 155, 38);
		getContentPane().add(acept);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/img/Captura de pantalla (131).png")));
		lblNewLabel_1.setBounds(153, 16, 104, 107);
		getContentPane().add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordField.setBounds(126, 187, 194, 26);
		getContentPane().add(passwordField);
	}
}
