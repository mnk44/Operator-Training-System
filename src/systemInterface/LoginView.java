package systemInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.Toolkit;

public class LoginView extends JDialog {

	private static final long serialVersionUID = -1995912037948383187L;
	private JTextField userName;
	private JButton okButton;
	private JPasswordField userPass;

	public static void main(String[] args) {
		try {
			LoginView dialog = new LoginView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LoginView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/imgs/logo.png")));
		setBackground(new Color(173, 216, 230));
		setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		setForeground(Color.BLACK);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		setAlwaysOnTop(true);
		setTitle("Sistema de Entrenamiento SECPROIT");
		setResizable(false);
		setBounds(100, 100, 1080, 676);
		
		okButton = new JButton("Aceptar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		okButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		okButton.setForeground(new Color(255, 255, 255));
		okButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		okButton.setBackground(new Color(244, 164, 96));
		okButton.setBounds(455, 487, 153, 37);
		getContentPane().add(okButton);
		
		userName = new JTextField();
		userName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		userName.setBounds(414, 370, 279, 29);
		getContentPane().add(userName);
		userName.setColumns(10);
		
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon(LoginView.class.getResource("/imgs/login.png")));
		image.setBounds(317, 16, 427, 312);
		getContentPane().add(image);
		
		JLabel user = new JLabel("Usuario:");
		user.setForeground(new Color(47, 46, 65));
		user.setFont(new Font("Segoe UI", Font.BOLD, 22));
		user.setBounds(324, 366, 93, 37);
		getContentPane().add(user);
		
		JLabel pass = new JLabel("Contrase\u00F1a:");
		pass.setForeground(new Color(47, 46, 65));
		pass.setFont(new Font("Segoe UI", Font.BOLD, 22));
		pass.setBounds(286, 419, 123, 37);
		getContentPane().add(pass);
		
		userPass = new JPasswordField();
		userPass.setEchoChar('*');
		userPass.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		userPass.setBounds(414, 421, 279, 29);
		getContentPane().add(userPass);
	}

}
