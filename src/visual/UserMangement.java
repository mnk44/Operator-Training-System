package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import logic.FillTables;

import java.awt.Font;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import services.UserService;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class UserMangement extends JFrame {

	private static final long serialVersionUID = -3283975538421886447L;
	private JPanel contentPane;
	private static JTable table;
	private static DefaultTableModel date;
	private JButton newUser;
	private static JButton updateUser;
	private static JButton sleepUser;
	private int selected = -1;
	private JLabel image;
	private static int id_user = -1;
	private static JButton resetPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMangement frame = new UserMangement(id_user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	@SuppressWarnings("static-access")
	public UserMangement(final int id) throws SQLException {
		id_user = id;
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserMangement.class.getResource("/img/Captura de pantalla (133).png")));
		setAutoRequestFocus(false);
		setTitle("Gesti\u00F3n de Usuarios");
		setResizable(false);
		setBounds(100, 100, 691, 555);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(15, 73, 644, 254);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(selected == table.getSelectedRow()){
					table.clearSelection();
					selected = -1;
				}else{
					selected = table.getSelectedRow();
					if(table.getValueAt(selected, 4) == "INACTIVO"){
						sleepUser.setText("Activar Usuario");
					}else{
						sleepUser.setText("Desactivar Usuario");
					}
				}
				
				if (selected != -1) {
					updateUser.setEnabled(true);
					resetPass.setEnabled(true);
					sleepUser.setEnabled(true);
				}else{
					updateUser.setEnabled(false);
					resetPass.setEnabled(false);
					sleepUser.setEnabled(false);
				}
			}
		});
		table.setBorder(null);
		table.setFillsViewportHeight(true);
		table.setBackground(Color.WHITE);
		table.setRowHeight(24);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		table.setIntercellSpacing(new Dimension(2, 2));
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoCreateRowSorter(true);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(this.table);

		newUser = new JButton("Nuevo Usuario");
		newUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					NewUser newUserView = new NewUser(-1);
					newUserView.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		newUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				newUser.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				newUser.setBackground(new Color(255, 255, 201));
			}
		});
		newUser.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		newUser.setBackground(new Color(255, 255, 201));
		newUser.setIcon(new ImageIcon(UserMangement.class.getResource("/img/icons8_Plus_Math_16.png")));
		newUser.setFont(new Font("Segoe UI", Font.BOLD, 17));
		newUser.setBounds(25, 373, 187, 38);
		contentPane.add(newUser);

		updateUser = new JButton("Modificar Usuario");
		updateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					NewUser newUserView = new NewUser((Integer) table.getValueAt(table.getSelectedRow(), 0));
					newUserView.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		updateUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(updateUser.isEnabled()){
					updateUser.setBackground(new Color(255, 206, 126));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(updateUser.isEnabled()){
					updateUser.setBackground(new Color(255, 255, 201));
				}
			}
		});
		updateUser.setEnabled(false);
		updateUser.setIcon(new ImageIcon(UserMangement.class.getResource("/img/icons8_Pencil_16.png")));
		updateUser.setFont(new Font("Segoe UI", Font.BOLD, 17));
		updateUser.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		updateUser.setBackground(new Color(255, 255, 201));
		updateUser.setBounds(230, 373, 187, 38);
		contentPane.add(updateUser);

		sleepUser = new JButton("Desactivar Usuario");
		sleepUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sleepUser.getText()=="Desactivar Usuario"){
					try {
						UserService.sleepUser((Integer) table.getValueAt(table.getSelectedRow(), 0));
						reloadTable();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else{
					try {
						UserService.awakeUser((Integer) table.getValueAt(table.getSelectedRow(), 0));
						reloadTable();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		sleepUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(sleepUser.isEnabled()){
					sleepUser.setBackground(new Color(255, 206, 126));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(sleepUser.isEnabled()){
					sleepUser.setBackground(new Color(255, 255, 201));
				}
			}
		});
		sleepUser.setEnabled(false);
		sleepUser.setBackground(new Color(255, 255, 201));
		sleepUser.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		sleepUser.setFont(new Font("Segoe UI", Font.BOLD, 17));
		sleepUser.setIcon(new ImageIcon(UserMangement.class.getResource("/img/icons8_Sleep_16.png")));
		sleepUser.setBounds(25, 437, 187, 38);
		contentPane.add(sleepUser);
		
		image = new JLabel("");
		image.setIcon(new ImageIcon(UserMangement.class.getResource("/img/undraw_meet_the_team_re_4h08.png")));
		image.setBounds(429, 342, 222, 157);
		contentPane.add(image);
		
		resetPass = new JButton("Restablecer Clave");
		resetPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = table.getSelectedRow();
				int segure = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea restablecer la contraseña?", "Restablecer Clave", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				int id_user = (Integer) table.getValueAt(selected, 0);
				if(segure == 0){
					try {
						String error = UserService.changePassword(id_user, "se" + UserService.findById(id_user).getIdentity_card() + "*");
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
		resetPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(resetPass.isEnabled()){
					resetPass.setBackground(new Color(255, 206, 126));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(resetPass.isEnabled()){
					resetPass.setBackground(new Color(255, 255, 201));
				}
			}
		});
		resetPass.setIcon(new ImageIcon(UserMangement.class.getResource("/img/pass.png")));
		resetPass.setFont(new Font("Segoe UI", Font.BOLD, 17));
		resetPass.setEnabled(false);
		resetPass.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		resetPass.setBackground(new Color(255, 255, 201));
		resetPass.setBounds(230, 437, 187, 38);
		contentPane.add(resetPass);
		
		reloadTable();
	}
	
	public static void reloadTable() throws SQLException{
		FillTables.fillUser(date, table, id_user);
		updateUser.setEnabled(false);
		resetPass.setEnabled(false);
		sleepUser.setEnabled(false);
		resetPass.setBackground(new Color(255, 255, 201));
		sleepUser.setBackground(new Color(255, 255, 201));
		updateUser.setBackground(new Color(255, 255, 201));
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		table.getColumnModel().getColumn(4).setCellRenderer(tcr);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(1).setMaxWidth(200);
	}
}
