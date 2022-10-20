package systemInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;

public class CenterView {

	private JFrame frmSecproit;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CenterView window = new CenterView();
					window.frmSecproit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CenterView() {
		initialize();
	}

	private void initialize() {
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
		menuBar.setForeground(new Color(244, 164, 96));
		frmSecproit.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Usuario");
		mnNewMenu.setBackground(new Color(244, 164, 96));
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNewMenu);
		
		JMenu mnAdministrador = new JMenu("Administrador");
		mnAdministrador.setBackground(new Color(244, 164, 96));
		mnAdministrador.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnAdministrador);
		
		JMenu mnJefeDerea = new JMenu("Jefe de \u00E1rea");
		mnJefeDerea.setBackground(new Color(244, 164, 96));
		mnJefeDerea.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnJefeDerea);
		
		JMenu mnOperario = new JMenu("Operario");
		mnOperario.setBackground(new Color(244, 164, 96));
		mnOperario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnOperario);
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setBackground(new Color(244, 164, 96));
		mnReportes.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnReportes);
		
		JMenu mnNewMenu_1 = new JMenu("Ayuda");
		mnNewMenu_1.setBackground(new Color(244, 164, 96));
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNewMenu_1);
	}

}
