package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import classes.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ProcessManagementPanel extends JPanel {
	
	private static JTable table;
	private static DefaultTableModel date;
	
	int selected = -1;

	private static final long serialVersionUID = -7006963620558751080L;
	private JTextField textField;
	private JButton btnNuevoProceso;
	private JButton btnModificarProceso;
	private JButton btnEliminarProceso;
//	private JButton btnArchivoanm;
//	private JButton btnArchivodrl;
 
	public ProcessManagementPanel(final ArrayList<User> op, final User user_active) {
		setBorder(null);
		setBackground(Color.WHITE);
		setBounds(100, 100, 838, 433);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(35, 104, 561, 313);
		add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(selected == table.getSelectedRow()){
					table.clearSelection();
					selected = -1;
				}else{
					selected = table.getSelectedRow();
				}

//				if (selected != -1) {
//					btnReiniciarClave.setEnabled(true);
//					btnModificarUsuario.setEnabled(true);
//					btnReiniciarClave.setBackground(new Color(255, 113, 19));
//					btnModificarUsuario.setBackground(new Color(255, 113, 19));
//					btnInactivarUsuario.setEnabled(true);
//					btnInactivarUsuario.setBackground(new Color(255, 113, 19));
//					if(usersList.get(selected).isUser_active()){
//						btnInactivarUsuario.setText("Inactivar usuario");
//						btnInactivarUsuario.setIcon(new ImageIcon(UserManagementPanel.class.getResource("/images/icons8_Delete_User_Male_16.png")));
//					}else{
//						btnInactivarUsuario.setText("Activar usuario");
//						btnInactivarUsuario.setIcon(new ImageIcon(UserManagementPanel.class.getResource("/images/icons8_Checked_User_Male_16.png")));
//					}
//				}else{
//					btnModificarUsuario.setEnabled(false);
//					btnReiniciarClave.setEnabled(false);
//					btnModificarUsuario.setBackground(new Color(248, 159, 101));
//					btnReiniciarClave.setBackground(new Color(248, 159, 101));
//					btnInactivarUsuario.setEnabled(false);
//					btnInactivarUsuario.setBackground(new Color(248, 159, 101));
//				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(24);
		table.setIntercellSpacing(new Dimension(2, 2));
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		table.setFillsViewportHeight(true);
		table.setBorder(null);
		table.setBackground(Color.WHITE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoCreateRowSorter(true);
		table.setIntercellSpacing(new Dimension(2, 2));
		scrollPane.setColumnHeaderView(table);		
		scrollPane.setViewportView(table);
		
		JLabel lblGestinDeProcesos = new JLabel("Gesti\u00F3n de procesos:");
		lblGestinDeProcesos.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestinDeProcesos.setForeground(new Color(255, 113, 19));
		lblGestinDeProcesos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblGestinDeProcesos.setBounds(25, 79, 571, 23);
		add(lblGestinDeProcesos);
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(ProcessManagementPanel.class.getResource("/images/search.png")));
		label.setBounds(58, 0, 48, 76);
		add(label);
		
		textField = new JTextField();
		textField.setToolTipText("Buscar usuario");
		textField.setFont(new Font("Corbel", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(115, 26, 426, 29);
		add(textField);
		
		btnNuevoProceso = new JButton("Nuevo proceso");
		btnNuevoProceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProcessView p = new ProcessView(op, user_active);
				p.setLocationRelativeTo(ProcessManagementPanel.this);
				p.setVisible(true);
			}
		});
		btnNuevoProceso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNuevoProceso.setBackground(new Color(248, 159, 101));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNuevoProceso.setBackground(new Color(255, 113, 19));
			}
		});
		btnNuevoProceso.setIcon(new ImageIcon(ProcessManagementPanel.class.getResource("/images/new.png")));
		btnNuevoProceso.setForeground(Color.WHITE);
		btnNuevoProceso.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnNuevoProceso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		btnNuevoProceso.setBackground(new Color(255, 113, 19));
		btnNuevoProceso.setBounds(611, 116, 212, 37);
		add(btnNuevoProceso);
		
		btnModificarProceso = new JButton("Modificar proceso");
		btnModificarProceso.setEnabled(false);
		btnModificarProceso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnModificarProceso.isEnabled()){
					btnModificarProceso.setBackground(new Color(248, 159, 101));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(btnModificarProceso.isEnabled()){
					btnModificarProceso.setBackground(new Color(255, 113, 19));
				}
			}
		});
		btnModificarProceso.setIcon(new ImageIcon(ProcessManagementPanel.class.getResource("/images/update.png")));
		btnModificarProceso.setForeground(Color.WHITE);
		btnModificarProceso.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnModificarProceso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		btnModificarProceso.setBackground(new Color(248, 159, 101));
		btnModificarProceso.setBounds(611, 169, 212, 37);
		add(btnModificarProceso);
		
		btnEliminarProceso = new JButton("Eliminar proceso");
		btnEliminarProceso.setEnabled(false);
		btnEliminarProceso.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnEliminarProceso.isEnabled()){
					btnEliminarProceso.setBackground(new Color(248, 159, 101));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(btnEliminarProceso.isEnabled()){
					btnEliminarProceso.setBackground(new Color(255, 113, 19));
				}
			}
		});
		btnEliminarProceso.setIcon(new ImageIcon(ProcessManagementPanel.class.getResource("/images/delete.png")));
		btnEliminarProceso.setForeground(Color.WHITE);
		btnEliminarProceso.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnEliminarProceso.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		btnEliminarProceso.setBackground(new Color(248, 159, 101));
		btnEliminarProceso.setBounds(611, 222, 212, 37);
		add(btnEliminarProceso);
		
//		btnArchivoanm = new JButton("Archivo .anm");
//		btnArchivoanm.setEnabled(false);
//		btnArchivoanm.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//				if(btnArchivoanm.isEnabled()){
//					btnArchivoanm.setBackground(new Color(248, 159, 101));
//				}
//			}
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//				if(btnArchivoanm.isEnabled()){
//					btnArchivoanm.setBackground(new Color(255, 113, 19));
//				}
//			}
//		});
//		btnArchivoanm.setIcon(new ImageIcon(ProcessManagementPanel.class.getResource("/images/icons8_File_16.png")));
//		btnArchivoanm.setForeground(Color.WHITE);
//		btnArchivoanm.setFont(new Font("Segoe UI", Font.BOLD, 18));
//		btnArchivoanm.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
//		btnArchivoanm.setBackground(new Color(248, 159, 101));
//		btnArchivoanm.setBounds(611, 309, 212, 37);
//		add(btnArchivoanm);
//		
//		btnArchivodrl = new JButton("Archivo .drl");
//		btnArchivodrl.setEnabled(false);
//		btnArchivodrl.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent arg0) {
//				if(btnArchivodrl.isEnabled()){
//					btnArchivodrl.setBackground(new Color(248, 159, 101));
//				}
//			}
//			@Override
//			public void mouseExited(MouseEvent arg0) {
//				if(btnArchivodrl.isEnabled()){
//					btnArchivodrl.setBackground(new Color(255, 113, 19));
//				}
//			}
//		});
//		btnArchivodrl.setIcon(new ImageIcon(ProcessManagementPanel.class.getResource("/images/icons8_File_16.png")));
//		btnArchivodrl.setForeground(Color.WHITE);
//		btnArchivodrl.setFont(new Font("Segoe UI", Font.BOLD, 18));
//		btnArchivodrl.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
//		btnArchivodrl.setBackground(new Color(248, 159, 101));
//		btnArchivodrl.setBounds(611, 361, 212, 37);
//		add(btnArchivodrl);
//		
//		JSeparator separator = new JSeparator();
//		separator.setBounds(611, 284, 212, 2);
//		add(separator);
	}
}
