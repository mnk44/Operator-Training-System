package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import classes.Area;
import classes.User;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import extras.DataTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserManagementPanel extends JPanel {

	private static final long serialVersionUID = 7029954910417287576L;
	
	private static JTable table;
	private static DefaultTableModel date;
	
	static ArrayList<Area> areasList = null;
	static User user = null;

	int selected = -1;
	static JTextField searchFile;
	private JButton btnNuevoUsuario;
	private static JButton btnModificarUsuario;
	private static JButton btnReiniciarClave;
	private static JButton btnInactivarUsuario;

	public UserManagementPanel(final User urr, final ArrayList<User> usersList, final ArrayList<Area> al) throws SQLException {
		areasList = al;
		user = urr;
		
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setBounds(100, 100, 838, 433);
		setLayout(null);
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(UserManagementPanel.class.getResource("/images/search.png")));
		label.setBounds(54, 0, 48, 76);
		add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(25, 104, 571, 274);
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

				if (selected != -1) {
					btnReiniciarClave.setEnabled(true);
					btnModificarUsuario.setEnabled(true);
					btnReiniciarClave.setBackground(new Color(255, 113, 19));
					btnModificarUsuario.setBackground(new Color(255, 113, 19));
					btnInactivarUsuario.setEnabled(true);
					btnInactivarUsuario.setBackground(new Color(255, 113, 19));
				}else{
					btnModificarUsuario.setEnabled(false);
					btnReiniciarClave.setEnabled(false);
					btnModificarUsuario.setBackground(new Color(248, 159, 101));
					btnReiniciarClave.setBackground(new Color(248, 159, 101));
					btnInactivarUsuario.setEnabled(false);
					btnInactivarUsuario.setBackground(new Color(248, 159, 101));
				}
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
		
		btnNuevoUsuario = new JButton("Nuevo usuario");
		btnNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserView uv = new UserView(areasList, usersList, user);
				uv.setLocationRelativeTo(UserManagementPanel.this);
				uv.setVisible(true);
			}
		});
		btnNuevoUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNuevoUsuario.setBackground(new Color(248, 159, 101));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNuevoUsuario.setBackground(new Color(255, 113, 19));
			}
		});
		btnNuevoUsuario.setIcon(new ImageIcon(UserManagementPanel.class.getResource("/images/icons8_Add_User_Male_16.png")));
		btnNuevoUsuario.setForeground(Color.WHITE);
		btnNuevoUsuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnNuevoUsuario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		btnNuevoUsuario.setBackground(new Color(255, 113, 19));
		btnNuevoUsuario.setBounds(611, 119, 199, 37);
		add(btnNuevoUsuario);
		
		btnModificarUsuario = new JButton("Modificar usuario");
		btnModificarUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnModificarUsuario.isEnabled()){
					btnModificarUsuario.setBackground(new Color(248, 159, 101));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(btnModificarUsuario.isEnabled()){
					btnModificarUsuario.setBackground(new Color(255, 113, 19));
				}
			}
		});
		btnModificarUsuario.setIcon(new ImageIcon(UserManagementPanel.class.getResource("/images/update.png")));
		btnModificarUsuario.setForeground(Color.WHITE);
		btnModificarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnModificarUsuario.setEnabled(false);
		btnModificarUsuario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		btnModificarUsuario.setBackground(new Color(248, 159, 101));
		btnModificarUsuario.setBounds(611, 185, 199, 37);
		add(btnModificarUsuario);
		
		btnReiniciarClave = new JButton("Restablecer clave");
		btnReiniciarClave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnReiniciarClave.isEnabled()){
					btnReiniciarClave.setBackground(new Color(248, 159, 101));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(btnReiniciarClave.isEnabled()){
					btnReiniciarClave.setBackground(new Color(255, 113, 19));
				}
			}
		});
		btnReiniciarClave.setIcon(new ImageIcon(UserManagementPanel.class.getResource("/images/change-pass.png")));
		btnReiniciarClave.setForeground(Color.WHITE);
		btnReiniciarClave.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnReiniciarClave.setEnabled(false);
		btnReiniciarClave.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		btnReiniciarClave.setBackground(new Color(248, 159, 101));
		btnReiniciarClave.setBounds(611, 249, 199, 37);
		add(btnReiniciarClave);
		
		btnInactivarUsuario = new JButton("Inactivar usuario");
		btnInactivarUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnInactivarUsuario.isEnabled()){
					btnInactivarUsuario.setBackground(new Color(248, 159, 101));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(btnInactivarUsuario.isEnabled()){
					btnInactivarUsuario.setBackground(new Color(255, 113, 19));
				}
			}
		});
		btnInactivarUsuario.setIcon(new ImageIcon(UserManagementPanel.class.getResource("/images/icons8_Delete_User_Male_16.png")));
		btnInactivarUsuario.setForeground(Color.WHITE);
		btnInactivarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnInactivarUsuario.setEnabled(false);
		btnInactivarUsuario.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		btnInactivarUsuario.setBackground(new Color(248, 159, 101));
		btnInactivarUsuario.setBounds(611, 311, 199, 37);
		add(btnInactivarUsuario);
		
		JLabel lblGestinDeUsuarios = new JLabel("Gesti\u00F3n de usuarios:");
		lblGestinDeUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestinDeUsuarios.setForeground(new Color(255, 113, 19));
		lblGestinDeUsuarios.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblGestinDeUsuarios.setBounds(25, 69, 571, 37);
		add(lblGestinDeUsuarios);
		
		searchFile = new JTextField();
		searchFile.setToolTipText("Buscar usuario");
		searchFile.setFont(new Font("Corbel", Font.PLAIN, 20));
		searchFile.setColumns(10);
		searchFile.setBounds(105, 26, 436, 29);
		add(searchFile);
		
		reload(usersList);
	}

	public static void reload(ArrayList<User> users) throws SQLException{
		DataTable.fillUsers(date, table, users, areasList, user.getUser_id());
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		table.getColumnModel().getColumn(4).setCellRenderer(tcr);
		table.getColumnModel().getColumn(0).setMaxWidth(45);
		table.getColumnModel().getColumn(4).setMaxWidth(60);
		btnInactivarUsuario.setEnabled(false);
		btnInactivarUsuario.setBackground(new Color(248, 159, 101));
		btnModificarUsuario.setEnabled(false);
		btnModificarUsuario.setBackground(new Color(248, 159, 101));
		btnReiniciarClave.setEnabled(false);
		btnReiniciarClave.setBackground(new Color(248, 159, 101));
	}
}
