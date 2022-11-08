package systemInterface;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import systemClass.User;
import systemLogic.Encrypting;
import systemServices.UserService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class ChangePassView extends JDialog {

	private static final long serialVersionUID = 265464301609530943L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField old_pass;
	private JPasswordField new_pass;
	private JButton accept_button;
	private JLabel view_actual;
	private JLabel view_old;

	User user = null;
	int error = 0;
	
	public static void main(String[] args) {
		try {
			ChangePassView dialog = new ChangePassView(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ChangePassView(User uss) {
		this.user = uss;
		setTitle("Cambiar contrase\u00F1a");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChangePassView.class.getResource("/images/change-pass-color.png")));
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 529, 249);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label_old = new JLabel("Contrase\u00F1a anterior:");
			label_old.setForeground(Color.BLACK);
			label_old.setFont(new Font("Segoe UI", Font.BOLD, 19));
			label_old.setBounds(32, 27, 183, 37);
			contentPanel.add(label_old);
		}
		{
			JLabel label_new = new JLabel("Contrase\u00F1a nueva:");
			label_new.setForeground(Color.BLACK);
			label_new.setFont(new Font("Segoe UI", Font.BOLD, 19));
			label_new.setBounds(50, 80, 165, 37);
			contentPanel.add(label_new);
		}
		
		old_pass = new JPasswordField();
		old_pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();

				if(KeyEvent.VK_ENTER == key){
					old_pass.setFocusable(false);
					old_pass.setFocusable(true);
				}
			}
		});
		old_pass.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		old_pass.setEchoChar('*');
		old_pass.setBounds(226, 30, 241, 29);
		contentPanel.add(old_pass);
		
		new_pass = new JPasswordField();
		new_pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();

				if(KeyEvent.VK_ENTER == key){
					accept_button.doClick();
				}
			}
		});
		new_pass.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		new_pass.setEchoChar('*');
		new_pass.setBounds(226, 83, 241, 29);
		contentPanel.add(new_pass);
		
		view_old = new JLabel();
		view_old.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				old_pass.setEchoChar((char)0);
				view_old.setIcon(new ImageIcon(ChangePassView.class.getResource("/images/dont-view.png")));
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				old_pass.setEchoChar('*');
				view_old.setIcon(new ImageIcon(ChangePassView.class.getResource("/images/view.png")));
			}
		});
		view_old.setIcon(new ImageIcon(ChangePassView.class.getResource("/images/view.png")));
		view_old.setBounds(472, 27, 43, 37);
		contentPanel.add(view_old);
		
		view_actual = new JLabel();
		view_actual.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				new_pass.setEchoChar((char)0);
				view_actual.setIcon(new ImageIcon(ChangePassView.class.getResource("/images/dont-view.png")));
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				new_pass.setEchoChar('*');
				view_actual.setIcon(new ImageIcon(ChangePassView.class.getResource("/images/view.png")));
			}
		});
		view_actual.setIcon(new ImageIcon(ChangePassView.class.getResource("/images/view.png")));
		view_actual.setBounds(472, 80, 43, 37);
		contentPanel.add(view_actual);
		
		accept_button = new JButton("Aceptar");
		accept_button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(new_pass.getText().isEmpty() || old_pass.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Se deben completar todos los campos para continuar", "Error", JOptionPane.ERROR_MESSAGE);
					error++;
				} else{
					try {
						if(Encrypting.getEncript(old_pass.getText()).equals(user.getUser_password())){
							String result = UserService.changePassword(user.getUser_id(), Encrypting.getEncript(new_pass.getText()), user.getUser_id(), "Cambio de contraseña", "", new Timestamp(Calendar.getInstance().getTime().getTime()));
							if(result == null){
								JOptionPane.showMessageDialog(null, "Acción completada satisfactoriamente", "Acción completada", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}else{
								JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(null, "Error en la contraseña anterior", "Error", JOptionPane.ERROR_MESSAGE);
							error++;
						}
					} catch (UnsupportedEncodingException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(error > 5){
					JOptionPane.showMessageDialog(null, "Demasiados intentos fallidos, contacte con un administrador", "Error", JOptionPane.ERROR_MESSAGE);
					CenterView.failValue();
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
		accept_button.setForeground(Color.WHITE);
		accept_button.setFont(new Font("Segoe UI", Font.BOLD, 18));
		accept_button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(74, 154, 190)));
		accept_button.setBackground(new Color(74, 154, 190));
		accept_button.setBounds(184, 145, 153, 37);
		contentPanel.add(accept_button);
	}
}
