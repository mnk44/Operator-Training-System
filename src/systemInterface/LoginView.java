package systemInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

import systemClass.User;
import systemLogic.Encrypting;
import systemServices.UserService;

public class LoginView extends JDialog {

	private static final long serialVersionUID = 7424831289335760294L;

	public static void main(String[] args) {
		try {
			LoginView dialog = new LoginView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JButton okButton;
	private JTextField userName;
	private JPasswordField userPass;
	private JLabel showPass;

	public LoginView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/imgs/logo.png")));
		setBackground(new Color(173, 216, 230));
		setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		setForeground(Color.BLACK);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setTitle("SECPROIT");
		setResizable(false);
		setBounds(100, 100, 767, 596);
		
		okButton = new JButton("Aceptar");
		okButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(userName.getText().isEmpty() || userPass.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Debes completar todos los campos para continuar", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					try {
						User user = (User) UserService.findNick(userName.getText());
						if(user != null){
							if(!user.isUser_active()){
								JOptionPane.showMessageDialog(null, "Usuario bloqueado", "Error", JOptionPane.ERROR_MESSAGE);
							}else if(user.getUser_password().equals(Encrypting.getEncript(userPass.getText()))){
								CenterView center = new CenterView(user.getUser_id());
								LoginView.this.setVisible(false);
								center.getFrame().setLocationRelativeTo(null);
								center.getFrame().setVisible(true);
							}
						}else{
							JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (HeadlessException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (UnsupportedEncodingException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				okButton.setBackground(new Color(239, 196, 159));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				okButton.setBackground(new Color(244, 164, 96));
			}
		});
		okButton.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		okButton.setForeground(new Color(255, 255, 255));
		okButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		okButton.setBackground(new Color(244, 164, 96));
		okButton.setBounds(294, 469, 153, 37);
		getContentPane().add(okButton);
		
		userName = new JTextField();
		userName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();

				if(KeyEvent.VK_ENTER == key){
					userName.setFocusable(false);
					userName.setFocusable(true);
				}else if(Character.isWhitespace(key)){
					e.consume();
					getToolkit().beep();
				}
			}
		});
		userName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		userName.setBounds(256, 344, 279, 29);
		getContentPane().add(userName);
		userName.setColumns(10);
		
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon(LoginView.class.getResource("/imgs/LoginView.png")));
		image.setBounds(169, 16, 428, 271);
		getContentPane().add(image);
		
		JLabel user = new JLabel("Usuario:");
		user.setForeground(new Color(47, 46, 65));
		user.setFont(new Font("Segoe UI", Font.BOLD, 22));
		user.setBounds(162, 340, 93, 37);
		getContentPane().add(user);
		
		JLabel pass = new JLabel("Contrase\u00F1a:");
		pass.setForeground(new Color(47, 46, 65));
		pass.setFont(new Font("Segoe UI", Font.BOLD, 22));
		pass.setBounds(123, 392, 132, 37);
		getContentPane().add(pass);
		
		userPass = new JPasswordField();
		userPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(KeyEvent.VK_ENTER == arg0.getKeyChar()){
					okButton.doClick();
				}
			}
		});
		userPass.setEchoChar('*');
		userPass.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		userPass.setBounds(256, 396, 279, 29);
		getContentPane().add(userPass);
		
		JLabel lblNewLabel = new JLabel("Sistema Experto para el Control de Procesos Qu\u00EDmicos");
		lblNewLabel.setForeground(new Color(244, 164, 96));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(15, 279, 746, 45);
		getContentPane().add(lblNewLabel);
		
		showPass = new JLabel("");
		showPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				userPass.setEchoChar((char)0);
				showPass.setIcon(new ImageIcon(LoginView.class.getResource("/imgs/icons8_Invisible_32.png")));
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				userPass.setEchoChar('*');
				showPass.setIcon(new ImageIcon(LoginView.class.getResource("/imgs/icons8_Eye_32.png")));
			}
		});
		showPass.setIcon(new ImageIcon(LoginView.class.getResource("/imgs/icons8_Eye_32.png")));
		showPass.setBounds(539, 392, 41, 37);
		getContentPane().add(showPass);
	}
}
