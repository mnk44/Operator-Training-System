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
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import contentClass.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class HomePage {

	private JFrame frmSistemaDeEntrenamiento;

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
	private void initialize(User uss) {
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
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cambiar Contrase\u00F1a");
		mntmNewMenuItem.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_Lock_16.png")));
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmNewMenuItem.setFont(new Font("Arial", Font.BOLD, 19));
		mntmNewMenuItem.setBackground(new Color(255, 186, 74));
		mntmNewMenuItem.setForeground(Color.WHITE);
		mnPersonal.add(mntmNewMenuItem);
		
		JMenuItem mntmRestablecerContrasea = new JMenuItem("Reiniciar Contrase\u00F1a");
		mntmRestablecerContrasea.setBorder(new LineBorder(Color.WHITE));
		mntmRestablecerContrasea.setBorderPainted(true);
		mntmRestablecerContrasea.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_Password_Reset_16.png")));
		mntmRestablecerContrasea.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmRestablecerContrasea.setForeground(Color.WHITE);
		mntmRestablecerContrasea.setFont(new Font("Arial", Font.BOLD, 19));
		mntmRestablecerContrasea.setBackground(new Color(255, 186, 74));
		mnPersonal.add(mntmRestablecerContrasea);
		
		JMenuItem mntmSalirDelSisitema = new JMenuItem("Cerrar Sesi\u00F3n");
		mntmSalirDelSisitema.setHorizontalAlignment(SwingConstants.LEFT);
		mntmSalirDelSisitema.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_Exit_16.png")));
		mntmSalirDelSisitema.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmSalirDelSisitema.setForeground(Color.WHITE);
		mntmSalirDelSisitema.setFont(new Font("Arial", Font.BOLD, 19));
		mntmSalirDelSisitema.setBackground(new Color(255, 186, 74));
		mnPersonal.add(mntmSalirDelSisitema);
		
		JMenu mnAdministrador = new JMenu("Administrador");
		mnAdministrador.setBorder(null);
		mnAdministrador.setForeground(Color.WHITE);
		mnAdministrador.setFont(new Font("Arial", Font.BOLD, 20));
		menuBar.add(mnAdministrador);
		
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
		mnAdministrador.add(mntmGestinDereas);
		
		JMenuItem mntmGestinDeUsuarios = new JMenuItem("Gesti\u00F3n de Usuarios");
		mntmGestinDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					UserMangement user = new UserMangement();
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
		mnAdministrador.add(mntmGestinDeUsuarios);
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setForeground(Color.WHITE);
		mnReportes.setFont(new Font("Arial", Font.BOLD, 20));
		mnReportes.setBorder(null);
		menuBar.add(mnReportes);
		
		JMenuItem mntmOperacionesDelSistema = new JMenuItem("Operaciones Realizadas");
		mntmOperacionesDelSistema.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmOperacionesDelSistema.setIcon(new ImageIcon(HomePage.class.getResource("/img/icons8_Purchase_Order_16.png")));
		mntmOperacionesDelSistema.setForeground(Color.WHITE);
		mntmOperacionesDelSistema.setFont(new Font("Arial", Font.BOLD, 19));
		mntmOperacionesDelSistema.setBackground(new Color(255, 186, 74));
		mnReportes.add(mntmOperacionesDelSistema);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(HomePage.class.getResource("/img/undraw_dev_focus_re_6iwt.png")));
		lblNewLabel.setBounds(166, 102, 575, 385);
		getFrmSistemaDeEntrenamiento().getContentPane().add(lblNewLabel);
		getFrmSistemaDeEntrenamiento().setResizable(false);
		getFrmSistemaDeEntrenamiento().setTitle("Sistema de Entrenamiento SECPROIT");
		getFrmSistemaDeEntrenamiento().setIconImage(Toolkit.getDefaultToolkit().getImage(HomePage.class.getResource("/img/Captura de pantalla (133).png")));
		getFrmSistemaDeEntrenamiento().setBounds(100, 100, 866, 614);
		getFrmSistemaDeEntrenamiento().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JFrame getFrmSistemaDeEntrenamiento() {
		return frmSistemaDeEntrenamiento;
	}

	public void setFrmSistemaDeEntrenamiento(JFrame frmSistemaDeEntrenamiento) {
		this.frmSistemaDeEntrenamiento = frmSistemaDeEntrenamiento;
	}
}
