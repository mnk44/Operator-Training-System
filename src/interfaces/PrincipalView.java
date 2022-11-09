package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.ImageIcon;

import classes.User;
import extras.Encrypting;
import services.UserService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JPanel;

import java.awt.event.WindowStateListener;

public class PrincipalView {

	JFrame frame;
	private JMenuItem changePass;
	
	User user_active = null;
	
	JPanel panel = new JPanel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalView window = new PrincipalView(null);
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PrincipalView(User uss) {
		user_active = uss;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg0) {
				int x = (frame.getWidth()-panel.getWidth())/2;
				if(frame.getExtendedState() == JFrame.MAXIMIZED_BOTH){
					int y = (frame.getHeight()- panel.getHeight() - frame.getInsets().top - frame.getInsets().bottom)/4;
			    	panel.setLocation(x, y);
				}else if(frame.getExtendedState() == JFrame.NORMAL){
			    	panel.setLocation(x, 0);
				}
			}
		});
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/images/logo.png")));
		frame.setBackground(new Color(173, 216, 230));
		frame.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 14));
		frame.setForeground(Color.BLACK);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Sistema de entrenamiento SECPROIT");
		frame.setBounds(100, 100, 901, 514);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "¿Desea salir del sistema?", "Salir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option == 0){
					System.exit(0);
				}
			}
		});

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		menuBar.setBackground(new Color(245, 102, 23));
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Usuario");
		mnNewMenu.setForeground(new Color(255, 255, 255));
		mnNewMenu.setFont(new Font("Dubai", Font.BOLD, 20));
		menuBar.add(mnNewMenu);

		changePass = new JMenuItem("Cambiar la contrase\u00F1a");
		changePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String new_pass = JOptionPane.showInputDialog(null, "Escriba la nueva contraseña:");
				if(new_pass != null){
					while(new_pass.isEmpty()){
						JOptionPane.showMessageDialog(null, "Debe escribir una nueva contraseña", "Error", JOptionPane.ERROR_MESSAGE);
						new_pass = JOptionPane.showInputDialog(null, "Escriba la nueva contraseña:");
					}
					String result = null;
					try {
						result = UserService.changePassword(user_active.getUser_id(), Encrypting.getEncript(new_pass), user_active.getUser_nick(), user_active.getUser_nick(), new Timestamp(Calendar.getInstance().getTime().getTime()));
					} catch (UnsupportedEncodingException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					}
					if(result != null){
						JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Acción completada", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		changePass.setIcon(new ImageIcon(PrincipalView.class.getResource("/images/change-pass.png")));
		changePass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		changePass.setForeground(Color.WHITE);
		changePass.setBackground(new Color(255, 113, 19));
		changePass.setFont(new Font("Dubai", Font.BOLD, 19));
		mnNewMenu.add(changePass);
		
		JMenuItem mntmInformacinPersonal = new JMenuItem("Informaci\u00F3n personal");
		mntmInformacinPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					panel = new PersonalInfPanel(user_active);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
				}
				frame.getContentPane().add(panel);
				int x = (frame.getWidth()-panel.getWidth())/2;
				if(frame.getExtendedState() == JFrame.MAXIMIZED_BOTH){
					int y = (frame.getHeight()- panel.getHeight() - frame.getInsets().top - frame.getInsets().bottom)/4;
			    	panel.setLocation(x, y);
				}else if(frame.getExtendedState() == JFrame.NORMAL){
			    	panel.setLocation(x, 0);
				}
			}
		});
		mntmInformacinPersonal.setIcon(new ImageIcon(PrincipalView.class.getResource("/images/personal-info.png")));
		mntmInformacinPersonal.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		mntmInformacinPersonal.setForeground(Color.WHITE);
		mntmInformacinPersonal.setFont(new Font("Dubai", Font.BOLD, 19));
		mntmInformacinPersonal.setBackground(new Color(255, 113, 19));
		mnNewMenu.add(mntmInformacinPersonal);
		
		JMenuItem mntmCerrarSesin = new JMenuItem("Cerrar sesi\u00F3n");
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginView log = new LoginView();
				log.setLocationRelativeTo(frame);
				log.setVisible(true);
				frame.setVisible(false);
			}
		});
		mntmCerrarSesin.setIcon(new ImageIcon(PrincipalView.class.getResource("/images/exit.png")));
		mntmCerrarSesin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
		mntmCerrarSesin.setForeground(Color.WHITE);
		mntmCerrarSesin.setFont(new Font("Dubai", Font.BOLD, 19));
		mntmCerrarSesin.setBackground(new Color(255, 113, 19));
		mnNewMenu.add(mntmCerrarSesin);
	}
}
