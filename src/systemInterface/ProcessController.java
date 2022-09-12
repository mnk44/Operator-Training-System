package systemInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Cursor;

public class ProcessController {

	private JFrame frmGestorDeProcesos;
	private JTextField textField;
	private JButton find;
	private JButton newProcess;
	private JButton editProcess;
	private JButton showInfo;
	private JButton deleteInfo;
	private JLabel back;
	private JLabel reload;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcessController window = new ProcessController();
					window.frmGestorDeProcesos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProcessController() {
		initialize();
	}

	private void initialize() {
		frmGestorDeProcesos = new JFrame();
		frmGestorDeProcesos.setBackground(new Color(173, 216, 230));
		frmGestorDeProcesos.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		frmGestorDeProcesos.setForeground(Color.BLACK);
		frmGestorDeProcesos.getContentPane().setBackground(Color.WHITE);
		frmGestorDeProcesos.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(256, 96, 787, 524);
		frmGestorDeProcesos.getContentPane().add(scrollPane);
		
		newProcess = new JButton("Nuevo Proceso");
		newProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				newProcess.setBackground(new Color(255, 229, 204));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				newProcess.setBackground(new Color(244, 164, 96));
			}
		});
		newProcess.setIcon(new ImageIcon(ProcessController.class.getResource("/imgs/icons8_Plus_Math_16.png")));
		newProcess.setForeground(Color.WHITE);
		newProcess.setFont(new Font("Segoe UI", Font.BOLD, 18));
		newProcess.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		newProcess.setBackground(new Color(244, 164, 96));
		newProcess.setBounds(27, 201, 195, 37);
		frmGestorDeProcesos.getContentPane().add(newProcess);
		
		editProcess = new JButton("Editar Proceso");
		editProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				editProcess.setBackground(new Color(255, 229, 204));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				editProcess.setBackground(new Color(244, 164, 96));
			}
		});
		editProcess.setIcon(new ImageIcon(ProcessController.class.getResource("/imgs/icons8_Pencil_16.png")));
		editProcess.setForeground(Color.WHITE);
		editProcess.setFont(new Font("Segoe UI", Font.BOLD, 18));
		editProcess.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		editProcess.setBackground(new Color(244, 164, 96));
		editProcess.setBounds(27, 282, 195, 37);
		frmGestorDeProcesos.getContentPane().add(editProcess);
		
		deleteInfo = new JButton("Eliminar Proceso");
		deleteInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				deleteInfo.setBackground(new Color(255, 229, 204));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				deleteInfo.setBackground(new Color(244, 164, 96));
			}
		});
		deleteInfo.setIcon(new ImageIcon(ProcessController.class.getResource("/imgs/icons8_Delete_16.png")));
		deleteInfo.setForeground(Color.WHITE);
		deleteInfo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		deleteInfo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		deleteInfo.setBackground(new Color(244, 164, 96));
		deleteInfo.setBounds(27, 442, 195, 37);
		frmGestorDeProcesos.getContentPane().add(deleteInfo);
		
		showInfo = new JButton("Mostrar Ficheros");
		showInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				showInfo.setBackground(new Color(255, 229, 204));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				showInfo.setBackground(new Color(244, 164, 96));
			}
		});
		showInfo.setIcon(new ImageIcon(ProcessController.class.getResource("/imgs/icons8_Show_Property_16.png")));
		showInfo.setForeground(Color.WHITE);
		showInfo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		showInfo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		showInfo.setBackground(new Color(244, 164, 96));
		showInfo.setBounds(27, 363, 195, 37);
		frmGestorDeProcesos.getContentPane().add(showInfo);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(377, 47, 388, 29);
		frmGestorDeProcesos.getContentPane().add(textField);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setForeground(new Color(47, 46, 65));
		lblBuscar.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblBuscar.setBounds(297, 43, 93, 37);
		frmGestorDeProcesos.getContentPane().add(lblBuscar);
		
		find = new JButton();
		find.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				find.setBackground(new Color(255, 229, 204));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				find.setBackground(new Color(244, 164, 96));
			}
		});
		find.setIcon(new ImageIcon(ProcessController.class.getResource("/imgs/icons8_Search_16.png")));
		find.setForeground(Color.WHITE);
		find.setFont(new Font("Segoe UI", Font.BOLD, 18));
		find.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		find.setBackground(new Color(244, 164, 96));
		find.setBounds(774, 43, 63, 37);
		frmGestorDeProcesos.getContentPane().add(find);
		
		back = new JLabel("");
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setIcon(new ImageIcon(ProcessController.class.getResource("/imgs/icons8_Left_64.png")));
		back.setBounds(15, 16, 57, 49);
		frmGestorDeProcesos.getContentPane().add(back);
		
		reload = new JLabel("");
		reload.setIcon(new ImageIcon(ProcessController.class.getResource("/imgs/icons8_Synchronize_64.png")));
		reload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		reload.setBounds(990, 30, 69, 64);
		frmGestorDeProcesos.getContentPane().add(reload);
		frmGestorDeProcesos.setAlwaysOnTop(true);
		frmGestorDeProcesos.setTitle("Gestor de Procesos");
		frmGestorDeProcesos.setResizable(false);
		frmGestorDeProcesos.setBounds(100, 100, 1080, 676);
		frmGestorDeProcesos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
