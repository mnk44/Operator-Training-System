package systemInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class BossView {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BossView window = new BossView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BossView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(BossView.class.getResource("/imgs/logo.png")));
		frame.setBackground(new Color(173, 216, 230));
		frame.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		frame.setForeground(Color.BLACK);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBounds(0, 0, 1074, 31);
		menuBar.setBackground(new Color(244, 164, 96));
		frame.getContentPane().add(menuBar);
		
		JMenu mnUsuario = new JMenu("Usuario");
		mnUsuario.setForeground(Color.WHITE);
		mnUsuario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnUsuario);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Informaci\u00F3n personal");
		mntmNewMenuItem.setBackground(new Color(244, 164, 96));
		mntmNewMenuItem.setIcon(new ImageIcon(BossView.class.getResource("/imgs/personalInformation.png")));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mntmNewMenuItem.setForeground(Color.WHITE);
		mnUsuario.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cambiar contrase\u00F1a");
		mntmNewMenuItem_1.setBackground(new Color(244, 164, 96));
		mntmNewMenuItem_1.setIcon(new ImageIcon(BossView.class.getResource("/imgs/changePassword.png")));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mntmNewMenuItem_1.setForeground(Color.WHITE);
		mnUsuario.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Cerrar sesi\u00F3n");
		mntmNewMenuItem_2.setBackground(new Color(244, 164, 96));
		mntmNewMenuItem_2.setIcon(new ImageIcon(BossView.class.getResource("/imgs/exit.png")));
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mntmNewMenuItem_2.setForeground(Color.WHITE);
		mnUsuario.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu = new JMenu("Jefe de \u00C1rea");
		mnNewMenu.setForeground(Color.WHITE);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Gesti\u00F3n de procesos");
		mntmNewMenuItem_3.setIcon(new ImageIcon(BossView.class.getResource("/imgs/editList.png")));
		mntmNewMenuItem_3.setBackground(new Color(244, 164, 96));
		mntmNewMenuItem_3.setForeground(Color.WHITE);
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmListadoDeProcesos = new JMenuItem("Listado de procesos");
		mntmListadoDeProcesos.setIcon(new ImageIcon(BossView.class.getResource("/imgs/list.png")));
		mntmListadoDeProcesos.setBackground(new Color(244, 164, 96));
		mntmListadoDeProcesos.setForeground(Color.WHITE);
		mntmListadoDeProcesos.setFont(new Font("Segoe UI", Font.BOLD, 19));
		mnNewMenu.add(mntmListadoDeProcesos);
		
		frame.setTitle("Sistema de Entrenamiento SECPROIT");
		frame.setResizable(false);
		frame.setBounds(100, 100, 1080, 676);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
