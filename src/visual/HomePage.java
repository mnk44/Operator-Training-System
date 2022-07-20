package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import services.UserService;
import utils.Rol;
import contentClass.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HomePage {

	private JFrame frmSistemaDeEntrenamiento;
	private JMenuItem changePassword;
	private JMenuItem resetPassword;
	private JMenuItem logout;
	private JMenuItem seeInfo;
	private JLabel lblNewLabel;
	private JMenu admin;
	private JMenu boss;
	private JMenuItem adminReports;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage(null);
					window.getFrmSistemaDeEntrenamiento().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomePage(User uss) {
		initialize(uss);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final User uss) {
		setFrmSistemaDeEntrenamiento(new JFrame());
		getFrmSistemaDeEntrenamiento().getContentPane().setBackground(Color.WHITE);
		((JComponent) getFrmSistemaDeEntrenamiento().getContentPane()).setBorder(new LineBorder(Color.LIGHT_GRAY));
		getFrmSistemaDeEntrenamiento().getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setBackground(new Color(255, 186, 74));
		menuBar.setBounds(0, 0, 860, 31);
		getFrmSistemaDeEntrenamiento().getContentPane().add(menuBar);

		JMenu mnPersonal = new JMenu("Usuario");
		mnPersonal.setBorder(null);
		mnPersonal.setFont(new Font("Arial", Font.BOLD, 20));
		mnPersonal.setForeground(Color.WHITE);
		menuBar.add(mnPersonal);

		seeInfo = new JMenuItem("Informaci\u00F3n Personal");
		seeInfo.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_User_Menu_Male_16.png")));
		seeInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		seeInfo.setForeground(Color.WHITE);
		seeInfo.setFont(new Font("Arial", Font.BOLD, 19));
		seeInfo.setBackground(new Color(255, 186, 74));
		mnPersonal.add(seeInfo);

		changePassword = new JMenuItem("Cambiar Contrase\u00F1a");
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pass = JOptionPane.showInputDialog(null, "Escriba la nueva contraseña:", "Nueva Clave", JOptionPane.INFORMATION_MESSAGE);
				if(pass != null){
					if(!pass.isEmpty()){
						int segure = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cambiar su contraseña?", "Cambiar Clave", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
						if(segure == 0){
							try {
								String error = UserService.changePassword(uss.getId_user(), pass);
								if(error == null){
									JOptionPane.showMessageDialog(null, "Contraseña cambiada con éxito", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
								}else{
									JOptionPane.showMessageDialog(null, "No se pudo completar la acción", "Error", JOptionPane.ERROR_MESSAGE);
								}
							} catch (SQLException e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}else{
						JOptionPane.showMessageDialog(null, "La contraseña no puede ser vacía", "Error", JOptionPane.ERROR_MESSAGE);
						changePassword.doClick();
					}
				}
			}
		});
		changePassword.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_Lock_16.png")));
		changePassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		changePassword.setFont(new Font("Arial", Font.BOLD, 19));
		changePassword.setBackground(new Color(255, 186, 74));
		changePassword.setForeground(Color.WHITE);
		mnPersonal.add(changePassword);

		resetPassword = new JMenuItem("Reiniciar Contrase\u00F1a");
		resetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int segure = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea restablecer su contraseña?", "Restablecer Clave", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if(segure == 0){
					try {
						String error = UserService.changePassword(uss.getId_user(), "se" + uss.getIdentity_card() + "*");
						if(error == null){
							JOptionPane.showMessageDialog(null, "Contraseña cambiada con éxito", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
						}else{
							JOptionPane.showMessageDialog(null, "No se pudo completar la acción", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		resetPassword.setBorder(null);
		resetPassword.setBorderPainted(true);
		resetPassword.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_Password_Reset_16.png")));
		resetPassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		resetPassword.setForeground(Color.WHITE);
		resetPassword.setFont(new Font("Arial", Font.BOLD, 19));
		resetPassword.setBackground(new Color(255, 186, 74));
		mnPersonal.add(resetPassword);

		logout = new JMenuItem("Cerrar Sesi\u00F3n");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int segure = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea cerrar la sesión?", "Cerrar Sesión", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(segure == 0){
					HomePage.this.getFrmSistemaDeEntrenamiento().setVisible(false);
					Login login = new Login();
					login.setVisible(true);
				}
			}
		});
		logout.setHorizontalAlignment(SwingConstants.LEFT);
		logout.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_Exit_16.png")));
		logout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		logout.setForeground(Color.WHITE);
		logout.setFont(new Font("Arial", Font.BOLD, 19));
		logout.setBackground(new Color(255, 186, 74));
		mnPersonal.add(logout);

		if(uss.getRol().equals(Rol.ADMINISTRADOR)){
			admin = new JMenu("Administrador");
			admin.setBorder(null);
			admin.setForeground(Color.WHITE);
			admin.setFont(new Font("Arial", Font.BOLD, 20));
			menuBar.add(admin);

			JMenuItem mntmGestinDereas = new JMenuItem("Gesti\u00F3n de \u00C1reas");
			mntmGestinDereas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						AreaManagement area = new AreaManagement();
						area.setVisible(true);
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			});
			mntmGestinDereas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
			mntmGestinDereas.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_Layers_16.png")));
			mntmGestinDereas.setForeground(Color.WHITE);
			mntmGestinDereas.setFont(new Font("Arial", Font.BOLD, 19));
			mntmGestinDereas.setBackground(new Color(255, 186, 74));
			admin.add(mntmGestinDereas);

			JMenuItem mntmGestinDeUsuarios = new JMenuItem("Gesti\u00F3n de Usuarios");
			mntmGestinDeUsuarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						UserMangement user = new UserMangement(uss.getId_user());
						user.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			mntmGestinDeUsuarios.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_Conference_16.png")));
			mntmGestinDeUsuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
			mntmGestinDeUsuarios.setForeground(Color.WHITE);
			mntmGestinDeUsuarios.setFont(new Font("Arial", Font.BOLD, 19));
			mntmGestinDeUsuarios.setBackground(new Color(255, 186, 74));
			admin.add(mntmGestinDeUsuarios);
			
		}else if(uss.getRol().equals(Rol.JEFE_DE_AREA)){
			boss = new JMenu("Jefe de \u00C1rea");
			boss.setBorder(null);
			boss.setForeground(Color.WHITE);
			boss.setFont(new Font("Arial", Font.BOLD, 20));
			menuBar.add(boss);

			JMenuItem process = new JMenuItem("Gesti\u00F3n de Procesos");
			process.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						ProcessManagement process = new ProcessManagement(uss.getArea());
						process.setVisible(true);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			process.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_Test_Tube_16.png")));
			process.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
			process.setForeground(Color.WHITE);
			process.setFont(new Font("Arial", Font.BOLD, 19));
			process.setBackground(new Color(255, 186, 74));
			boss.add(process);
			
		}else if(uss.getRol().equals(Rol.OPERARIO)){
			boss = new JMenu("Operario");
			boss.setBorder(null);
			boss.setForeground(Color.WHITE);
			boss.setFont(new Font("Arial", Font.BOLD, 20));
			menuBar.add(boss);

			JMenuItem entr = new JMenuItem("Realizar Entrenamiento");
			entr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					EntManagement ent = null;
					try {
						ent = new EntManagement(uss.getId_user());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ent.setVisible(true);
				}
			});
			entr.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_Test_16.png")));
			entr.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
			entr.setForeground(Color.WHITE);
			entr.setFont(new Font("Arial", Font.BOLD, 19));
			entr.setBackground(new Color(255, 186, 74));
			boss.add(entr);
		}

		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setForeground(Color.WHITE);
		mnReportes.setFont(new Font("Arial", Font.BOLD, 20));
		mnReportes.setBorder(null);
		menuBar.add(mnReportes);

		if(uss.getRol().equals(Rol.ADMINISTRADOR)){
			adminReports = new JMenuItem("Operaciones Realizadas");
			adminReports.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
			adminReports.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_Purchase_Order_16.png")));
			adminReports.setForeground(Color.WHITE);
			adminReports.setFont(new Font("Arial", Font.BOLD, 19));
			adminReports.setBackground(new Color(255, 186, 74));
			mnReportes.add(adminReports);
		}

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(166, 102, 578, 385);
		if(uss.getRol().equals(Rol.ADMINISTRADOR)){
			lblNewLabel.setIcon(new ImageIcon(HomePage.class.getResource("/img/undraw_dev_focus_re_6iwt.png")));
		}else if(uss.getRol().equals(Rol.JEFE_DE_AREA)){
			lblNewLabel.setIcon(new ImageIcon(HomePage.class.getResource("/img/jefe.png")));
		}else if(uss.getRol().equals(Rol.OPERARIO)){
			lblNewLabel.setBounds(70, 102, 764, 385);
			lblNewLabel.setIcon(new ImageIcon(HomePage.class.getResource("/img/Componente 2 – 1.png")));
		}
		getFrmSistemaDeEntrenamiento().getContentPane().add(lblNewLabel);
		getFrmSistemaDeEntrenamiento().setResizable(false);
		getFrmSistemaDeEntrenamiento().setTitle("Sistema de Entrenamiento SECPROIT");
		getFrmSistemaDeEntrenamiento().setIconImage(Toolkit.getDefaultToolkit().getImage(HomePage.class.getResource("/img/Captura de pantalla (133).png")));
		getFrmSistemaDeEntrenamiento().setBounds(100, 100, 866, 614);
		getFrmSistemaDeEntrenamiento().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public JFrame getFrmSistemaDeEntrenamiento() {
		return frmSistemaDeEntrenamiento;
	}

	public void setFrmSistemaDeEntrenamiento(JFrame frmSistemaDeEntrenamiento) {
		this.frmSistemaDeEntrenamiento = frmSistemaDeEntrenamiento;
		frmSistemaDeEntrenamiento.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int segure = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir del Sistema?", "Salir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(segure == 0){
					System.exit(0);
				}
			}
		});
	}
}
