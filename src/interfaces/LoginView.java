package interfaces;

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

import classes.User;
import extras.Encrypting;
import services.UserService;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/images/logo.png")));
		setBackground(new Color(173, 216, 230));
		setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		setForeground(Color.BLACK);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setTitle("Sistema de entrenamiento SECPROIT");
		setResizable(false);
		setBounds(100, 100, 901, 484);
		
		JLabel title = new JLabel("Sistema de Experto para el Control de");
		title.setBounds(450, 68, 445, 49);
		getContentPane().add(title);
		title.setForeground(new Color(99, 68, 55));
		title.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel user_label = new JLabel("Usuario:");
		user_label.setBounds(491, 157, 109, 37);
		getContentPane().add(user_label);
		user_label.setForeground(new Color(255, 113, 19));
		user_label.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		
		user_name = new JTextField();
		user_name.setBounds(491, 197, 339, 29);
		getContentPane().add(user_name);
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
		user_name.setFont(new Font("Corbel", Font.PLAIN, 20));
		user_name.setColumns(10);
		
		JLabel pass_label = new JLabel("Contrase\u00F1a:");
		pass_label.setBounds(491, 242, 182, 37);
		getContentPane().add(pass_label);
		pass_label.setForeground(new Color(255, 113, 19));
		pass_label.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		
		user_pass = new JPasswordField();
		user_pass.setBounds(491, 285, 339, 29);
		getContentPane().add(user_pass);
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
		
		view_pass = new JLabel();
		view_pass.setBounds(836, 285, 32, 29);
		getContentPane().add(view_pass);
		view_pass.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				user_pass.setEchoChar((char)0);
				view_pass.setIcon(new ImageIcon(LoginView.class.getResource("/images/icons8_Invisible_32.png")));
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				user_pass.setEchoChar('*');
				view_pass.setIcon(new ImageIcon(LoginView.class.getResource("/images/icons8_Eye_32.png")));
			}
		});
		view_pass.setIcon(new ImageIcon(LoginView.class.getResource("/images/icons8_Eye_32.png")));
		
		accept_button = new JButton("Aceptar");
		accept_button.setBounds(600, 374, 153, 37);
		getContentPane().add(accept_button);
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
					JOptionPane.showMessageDialog(null, "Demasiados intentos fallidos, contacte con un administrador", "Error", JOptionPane.WARNING_MESSAGE);
					dispose();
				}
			}
		});
		accept_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				accept_button.setBackground(new Color(248, 159, 101));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				accept_button.setBackground(new Color(255, 113, 19));
			}
		});
		accept_button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		accept_button.setForeground(new Color(255, 255, 255));
		accept_button.setFont(new Font("Segoe UI", Font.BOLD, 18));
		accept_button.setBackground(new Color(255, 113, 19));
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(LoginView.class.getResource("/images/secproit.png")));
		lblNewLabel.setBounds(0, 0, 450, 450);
		getContentPane().add(lblNewLabel);
		
		JLabel lblSecproit = new JLabel("SECPROIT");
		lblSecproit.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecproit.setForeground(new Color(255, 113, 19));
		lblSecproit.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 30));
		lblSecproit.setBounds(450, 38, 445, 49);
		getContentPane().add(lblSecproit);
		
		JLabel lblProcesosQumicos = new JLabel("Procesos Qu\u00EDmicos");
		lblProcesosQumicos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProcesosQumicos.setForeground(new Color(99, 68, 55));
		lblProcesosQumicos.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblProcesosQumicos.setBounds(450, 92, 445, 49);
		getContentPane().add(lblProcesosQumicos);
	}
}
