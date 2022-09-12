package systemInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;

public class Login {

	private JFrame frmSistemaDeEntrenamiento;
	private JTextField userName;
	private JButton okButton;
	private JPasswordField userPass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmSistemaDeEntrenamiento.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		initialize();
	}

	private void initialize() {
		frmSistemaDeEntrenamiento = new JFrame();
		frmSistemaDeEntrenamiento.setBackground(new Color(173, 216, 230));
		frmSistemaDeEntrenamiento.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		frmSistemaDeEntrenamiento.setForeground(Color.BLACK);
		frmSistemaDeEntrenamiento.getContentPane().setBackground(Color.WHITE);
		frmSistemaDeEntrenamiento.setAlwaysOnTop(true);
		frmSistemaDeEntrenamiento.setTitle("Sistema de Entrenamiento SECPROIT");
		frmSistemaDeEntrenamiento.setResizable(false);
		frmSistemaDeEntrenamiento.setBounds(100, 100, 1080, 676);
		frmSistemaDeEntrenamiento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeEntrenamiento.getContentPane().setLayout(null);
		
		okButton = new JButton("Aceptar");
		okButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		okButton.setForeground(new Color(255, 255, 255));
		okButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		okButton.setBackground(new Color(244, 164, 96));
		okButton.setBounds(455, 487, 153, 37);
		frmSistemaDeEntrenamiento.getContentPane().add(okButton);
		
		userName = new JTextField();
		userName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		userName.setBounds(414, 370, 279, 29);
		frmSistemaDeEntrenamiento.getContentPane().add(userName);
		userName.setColumns(10);
		
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon(Login.class.getResource("/imgs/login.png")));
		image.setBounds(317, 16, 427, 312);
		frmSistemaDeEntrenamiento.getContentPane().add(image);
		
		JLabel user = new JLabel("Usuario:");
		user.setForeground(new Color(47, 46, 65));
		user.setFont(new Font("Segoe UI", Font.BOLD, 22));
		user.setBounds(324, 366, 93, 37);
		frmSistemaDeEntrenamiento.getContentPane().add(user);
		
		JLabel pass = new JLabel("Contrase\u00F1a:");
		pass.setForeground(new Color(47, 46, 65));
		pass.setFont(new Font("Segoe UI", Font.BOLD, 22));
		pass.setBounds(286, 419, 123, 37);
		frmSistemaDeEntrenamiento.getContentPane().add(pass);
		
		userPass = new JPasswordField();
		userPass.setEchoChar('*');
		userPass.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		userPass.setBounds(414, 421, 279, 29);
		frmSistemaDeEntrenamiento.getContentPane().add(userPass);
	}
}
