package systemInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Font;

import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class CenterView {

	private JFrame frmSecproit;
	
	int userID;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CenterView window = new CenterView(1);
					window.frmSecproit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CenterView(int id) {
		initialize(id);
	}

	private void initialize(int id) {
		userID = id;
		frmSecproit = new JFrame();
		frmSecproit.getContentPane().setBackground(Color.WHITE);
		frmSecproit.setTitle("SECPROIT");
		frmSecproit.setIconImage(Toolkit.getDefaultToolkit().getImage(CenterView.class.getResource("/imgs/logo.png")));
		frmSecproit.setResizable(false);
		frmSecproit.setBackground(Color.WHITE);
		frmSecproit.setForeground(Color.BLACK);
		frmSecproit.setBounds(100, 100, 1064, 596);
		frmSecproit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(244, 164, 96));
		frmSecproit.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Usuario");
		mnNewMenu.setBackground(new Color(244, 164, 96));
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu.setForeground(Color.WHITE);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCambiarContrasea = new JMenuItem("Cambiar contrase\u00F1a");
		mntmCambiarContrasea.setIcon(new ImageIcon(CenterView.class.getResource("/imgs/changePassword.png")));
		mntmCambiarContrasea.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mntmCambiarContrasea.setBackground(new Color(244, 164, 96));
		mntmCambiarContrasea.setForeground(Color.WHITE);
		mnNewMenu.add(mntmCambiarContrasea);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Informaci\u00F3n personal");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mntmNewMenuItem.setIcon(new ImageIcon(CenterView.class.getResource("/imgs/personalInformation.png")));
		mntmNewMenuItem.setBackground(new Color(244, 164, 96));
		mntmNewMenuItem.setForeground(Color.WHITE);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmCerrarSesin = new JMenuItem("Cerrar sesi\u00F3n");
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginView log = new LoginView();
				log.setLocationRelativeTo(frmSecproit);
				log.setVisible(true);
				CenterView.this.frmSecproit.setVisible(false);
			}
		});
		mntmCerrarSesin.setIcon(new ImageIcon(CenterView.class.getResource("/imgs/exit.png")));
		mntmCerrarSesin.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mntmCerrarSesin.setBackground(new Color(244, 164, 96));
		mntmCerrarSesin.setForeground(Color.WHITE);
		mnNewMenu.add(mntmCerrarSesin);
		
		JMenu mnAdministrador = new JMenu("Administrador");
		mnAdministrador.setBackground(new Color(244, 164, 96));
		mnAdministrador.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnAdministrador.setForeground(Color.WHITE);
		menuBar.add(mnAdministrador);
		
		JMenuItem mntmGestinDereas = new JMenuItem("Gesti\u00F3n de \u00E1reas");
		mntmGestinDereas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AreaListView areaView = null;
				try {
					areaView = new AreaListView();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				areaView.setLocationRelativeTo(frmSecproit);
				areaView.setVisible(true);
			}
		});
		mntmGestinDereas.setIcon(new ImageIcon(CenterView.class.getResource("/imgs/icons8_Unit_16.png")));
		mntmGestinDereas.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mntmGestinDereas.setBackground(new Color(244, 164, 96));
		mntmGestinDereas.setForeground(Color.WHITE);
		mnAdministrador.add(mntmGestinDereas);
		
		JMenuItem mntmGestinDeUsuarios = new JMenuItem("Gesti\u00F3n de usuarios");
		mntmGestinDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserListView u = null;
				try {
					u = new UserListView();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				u.setLocationRelativeTo(frmSecproit);
				u.setVisible(true);
			}
		});
		mntmGestinDeUsuarios.setIcon(new ImageIcon(CenterView.class.getResource("/imgs/icons8_User_Groups_16.png")));
		mntmGestinDeUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mntmGestinDeUsuarios.setBackground(new Color(244, 164, 96));
		mntmGestinDeUsuarios.setForeground(Color.WHITE);
		mnAdministrador.add(mntmGestinDeUsuarios);
		
		JMenu mnJefeDerea = new JMenu("Jefe de \u00E1rea");
		mnJefeDerea.setBackground(new Color(244, 164, 96));
		mnJefeDerea.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnJefeDerea.setForeground(Color.WHITE);
		menuBar.add(mnJefeDerea);
		
		JMenuItem mntmGestinDeProcesos = new JMenuItem("Gesti\u00F3n de procesos");
		mntmGestinDeProcesos.setIcon(new ImageIcon(CenterView.class.getResource("/imgs/icons8_Compose_16.png")));
		mntmGestinDeProcesos.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mntmGestinDeProcesos.setBackground(new Color(244, 164, 96));
		mntmGestinDeProcesos .setForeground(Color.WHITE);
		mnJefeDerea.add(mntmGestinDeProcesos);
		
		JMenu mnOperario = new JMenu("Operario");
		mnOperario.setBackground(new Color(244, 164, 96));
		mnOperario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnOperario.setForeground(Color.WHITE);
		menuBar.add(mnOperario);
		
		JMenuItem mntmRealizarEntrenamiento = new JMenuItem("Realizar entrenamiento");
		mntmRealizarEntrenamiento.setIcon(new ImageIcon(CenterView.class.getResource("/imgs/icons8_Pass_Fail_16.png")));
		mntmRealizarEntrenamiento.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mntmRealizarEntrenamiento.setBackground(new Color(244, 164, 96));
		mntmRealizarEntrenamiento.setForeground(Color.WHITE);
		mnOperario.add(mntmRealizarEntrenamiento);
	}
	
	public JFrame getFrame() {
		return frmSecproit;
	}

}
