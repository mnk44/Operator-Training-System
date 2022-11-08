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

	private JButton accept_button;
	private JTextField user_name;
	private JPasswordField user_pass;
	private JLabel view_pass;
	
	private int error = 0;

	public LoginView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/images/mini-logo.png")));
		setBackground(new Color(173, 216, 230));
		setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		setForeground(Color.BLACK);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setTitle("Sistema de entrenamiento SECPROIT");
		setResizable(false);
		setBounds(100, 100, 693, 631);
		
		accept_button = new JButton("Aceptar");
		accept_button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(user_name.getText().isEmpty() || user_pass.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Se deben completar todos los campos para continuar", "Error", JOptionPane.ERROR_MESSAGE);
					error++;
				}else{
					try {
						User user = (User) UserService.findNick(user_name.getText());
						if(user != null){
							if(user.getUser_password().equals(Encrypting.getEncript(user_pass.getText()))){
								if(!user.isUser_active()){
									JOptionPane.showMessageDialog(null, "Usuario desactivado", "Error", JOptionPane.ERROR_MESSAGE);
									error++;
								}else{
									CenterView center = new CenterView(user);
									LoginView.this.setVisible(false);
									center.getFrame().setLocationRelativeTo(null);
									center.getFrame().setVisible(true);
								}
							}else{
								JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
								error++;
							}
						}else{
							JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
							error++;
						}
					} catch (HeadlessException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (UnsupportedEncodingException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(error > 5){
					JOptionPane.showMessageDialog(null, "Demasiados intentos fallidos", "Error", JOptionPane.ERROR_MESSAGE);
					dispose();
				}
			}
		});
		accept_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				accept_button.setBackground(new Color(184, 225, 243));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				accept_button.setBackground(new Color(74, 154, 190));
			}
		});
		accept_button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(74, 154, 190)));
		accept_button.setForeground(new Color(255, 255, 255));
		accept_button.setFont(new Font("Segoe UI", Font.BOLD, 18));
		accept_button.setBackground(new Color(74, 154, 190));
		accept_button.setBounds(264, 526, 153, 37);
		getContentPane().add(accept_button);
		
		user_name = new JTextField();
		user_name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();

				if(KeyEvent.VK_ENTER == key){
					user_name.setFocusable(false);
					user_name.setFocusable(true);
				}else if(Character.isWhitespace(key)){
					e.consume();
					getToolkit().beep();
				}
			}
		});
		user_name.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		user_name.setBounds(228, 400, 275, 29);
		getContentPane().add(user_name);
		user_name.setColumns(10);
		
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(LoginView.class.getResource("/images/logo-inicial.png")));
		logo.setBounds(136, 0, 428, 312);
		getContentPane().add(logo);
		
		JLabel user_label = new JLabel("Usuario:");
		user_label.setForeground(Color.BLACK);
		user_label.setFont(new Font("Segoe UI", Font.BOLD, 22));
		user_label.setBounds(136, 396, 85, 37);
		getContentPane().add(user_label);
		
		JLabel pass_label = new JLabel("Contrase\u00F1a:");
		pass_label.setForeground(Color.BLACK);
		pass_label.setFont(new Font("Segoe UI", Font.BOLD, 22));
		pass_label.setBounds(98, 445, 133, 37);
		getContentPane().add(pass_label);
		
		user_pass = new JPasswordField();
		user_pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(KeyEvent.VK_ENTER == arg0.getKeyChar()){
					accept_button.doClick();
				}
			}
		});
		user_pass.setEchoChar('*');
		user_pass.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		user_pass.setBounds(228, 449, 275, 29);
		getContentPane().add(user_pass);
		
		JLabel title = new JLabel("Sistema Experto para el Control de Procesos Qu\u00EDmicos");
		title.setForeground(new Color(243,193,67));
		title.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(0, 316, 687, 45);
		getContentPane().add(title);
		
		view_pass = new JLabel();
		view_pass.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				user_pass.setEchoChar((char)0);
				view_pass.setIcon(new ImageIcon(LoginView.class.getResource("/images/dont-view.png")));
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				user_pass.setEchoChar('*');
				view_pass.setIcon(new ImageIcon(LoginView.class.getResource("/images/view.png")));
			}
		});
		view_pass.setIcon(new ImageIcon(LoginView.class.getResource("/images/view.png")));
		view_pass.setBounds(511, 445, 37, 37);
		getContentPane().add(view_pass);
	}
}
