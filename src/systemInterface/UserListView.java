package systemInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import systemClass.User;
import systemEnums.AccionTrace;
import systemEnums.SystemClass;
import systemLogic.FindObjects;
import systemLogic.TablesInfo;
import systemServices.TraceService;
import systemServices.UserService;

public class UserListView extends JDialog {

	private static final long serialVersionUID = 7774743997604860561L;
	private final JPanel contentPanel = new JPanel();
	private JButton newUser;
	private static JButton updateUser;
	private static JButton sleepUser;
	private JLabel lblNewLabel;
	private static JTextField findUser;
	private static DefaultTableModel date;
	private static JTable table;

	private int selected = -1;
	static int user = -1;
	
	@SuppressWarnings("unchecked")
	ArrayList<User> userList = (ArrayList<User>) UserService.getUsers();

	public static void main(String[] args) {
		try {
			UserListView dialog = new UserListView(1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserListView(int userId) throws SQLException {
		user = userId;
		setModal(true);
		setBackground(Color.WHITE);
		setBounds(100, 100, 1051, 616);
		setTitle("Gesti\u00F3n de usuarios");
		setResizable(false);
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserListView.class.getResource("/imgs/logo.png")));
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(26, 74, 776, 480);
		contentPanel.add(scrollPane);

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
					updateUser.setEnabled(true);
					sleepUser.setEnabled(true);
					if(table.getValueAt(selected, 5).equals("x")){
						sleepUser.setText("Inactivar usuario");
						sleepUser.setIcon(new ImageIcon(UserListView.class.getResource("/imgs/icons8_Sleeping_in_Bed_16.png")));
					}else{
						sleepUser.setText("Activar usuario");
						sleepUser.setIcon(new ImageIcon(UserListView.class.getResource("/imgs/icons8_Insomnia_16.png")));
					}
				}else{
					updateUser.setEnabled(false);
					sleepUser.setEnabled(false);
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

		newUser = new JButton("Nuevo usuario");
		newUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewUserView view = null;
				try {
					view = new NewUserView(-1, user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.setLocationRelativeTo(UserListView.this);
				view.setVisible(true);
			}
		});
		newUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				newUser.setBackground(new Color(239, 196, 159));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				newUser.setBackground(new Color(244, 164, 96));
			}
		});
		newUser.setIcon(new ImageIcon(AreaListView.class.getResource("/imgs/icons8_Plus_16.png")));
		newUser.setForeground(Color.WHITE);
		newUser.setFont(new Font("Segoe UI", Font.BOLD, 18));
		newUser.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		newUser.setBackground(new Color(244, 164, 96));
		newUser.setBounds(817, 115, 202, 37);
		contentPanel.add(newUser);

		updateUser = new JButton("Modificar usuario");
		updateUser.setEnabled(false);
		updateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewUserView view = null;
				try {
					view = new NewUserView((int) table.getValueAt(selected, 0), user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				view.setLocationRelativeTo(UserListView.this);
				view.setVisible(true);
			}
		});
		updateUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(updateUser.isEnabled()){
					updateUser.setBackground(new Color(239, 196, 159));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				updateUser.setBackground(new Color(244, 164, 96));
			}
		});
		updateUser.setIcon(new ImageIcon(AreaListView.class.getResource("/imgs/icons8_Pencil_16.png")));
		updateUser.setForeground(Color.WHITE);
		updateUser.setFont(new Font("Segoe UI", Font.BOLD, 18));
		updateUser.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		updateUser.setBackground(new Color(244, 164, 96));
		updateUser.setBounds(817, 197, 202, 37);
		contentPanel.add(updateUser);

		sleepUser = new JButton("Inactivar usuario");
		sleepUser.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				if(sleepUser.getText().equals("Inactivar usuario")){
					try {
						UserService.sleepUser((int) table.getValueAt(selected, 0));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String result2 = null;
					try {
						result2 = TraceService.newTrace(user, AccionTrace.desactivó_al, SystemClass.usuario, (String)table.getValueAt(selected, 1), new Timestamp(Calendar.getInstance().getTime().getTime()));
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if(result2 != null){
						JOptionPane.showMessageDialog(null, result2, "Error", JOptionPane.ERROR_MESSAGE);
					}
					try {
						userList = (ArrayList<User>) UserService.getUsers();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						reloadTable(userList);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						UserService.awakeUser((int) table.getValueAt(selected, 0));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String result2 = null;
					try {
						result2 = TraceService.newTrace(user, AccionTrace.activó_al, SystemClass.usuario, (String)table.getValueAt(selected, 1), new Timestamp(Calendar.getInstance().getTime().getTime()));
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if(result2 != null){
						JOptionPane.showMessageDialog(null, result2, "Error", JOptionPane.ERROR_MESSAGE);
					}
					try {
						userList = (ArrayList<User>) UserService.getUsers();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						reloadTable(userList);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		sleepUser.setEnabled(false);
		sleepUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(sleepUser.isEnabled()){
					sleepUser.setBackground(new Color(239, 196, 159));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				sleepUser.setBackground(new Color(244, 164, 96));
			}
		});
		sleepUser.setIcon(new ImageIcon(UserListView.class.getResource("/imgs/icons8_Sleeping_in_Bed_16.png")));
		sleepUser.setForeground(Color.WHITE);
		sleepUser.setFont(new Font("Segoe UI", Font.BOLD, 18));
		sleepUser.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		sleepUser.setBackground(new Color(244, 164, 96));
		sleepUser.setBounds(817, 287, 202, 37);
		contentPanel.add(sleepUser);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AreaListView.class.getResource("/imgs/search.png")));
		lblNewLabel.setBounds(32, 16, 48, 48);
		contentPanel.add(lblNewLabel);

		findUser = new JTextField();
		findUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(findUser.getText().equals("Buscar usuario")){
					findUser.setCaretPosition(0);
				}
			}
		});
		findUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(findUser.getText().equals("Buscar usuario")){
					if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
						arg0.consume();
						getToolkit().beep();
					}else{
						findUser.setText("");
						findUser.setForeground(Color.BLACK);
						try {
							reloadTable(FindObjects.findUsers(userList, findUser.getText() + arg0.getKeyChar()));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else if(findUser.getText().length() == 1 && arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
					findUser.setText("Buscar usuario");
					findUser.setForeground(Color.LIGHT_GRAY);
					arg0.consume();
					findUser.setCaretPosition(0);
					try {
						reloadTable(userList);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
					try {
						reloadTable(FindObjects.findUsers(userList, findUser.getText().substring(0, findUser.getText().length()-1)));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						reloadTable(FindObjects.findUsers(userList, findUser.getText() + arg0.getKeyChar()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		findUser.setForeground(Color.LIGHT_GRAY);
		findUser.setText("Buscar usuario");
		findUser.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		findUser.setColumns(10);
		findUser.setBounds(83, 26, 499, 28);
		contentPanel.add(findUser);

		reloadTable(userList);
	}

	public static void reloadTable(ArrayList<User> users) throws SQLException{
		TablesInfo.getUsers(date, table, users, user);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		table.getColumnModel().getColumn(4).setCellRenderer(tcr);
		table.getColumnModel().getColumn(5).setCellRenderer(tcr);
		table.getColumnModel().getColumn(0).setMaxWidth(45);
		table.getColumnModel().getColumn(4).setMaxWidth(55);
		table.getColumnModel().getColumn(5).setMaxWidth(60);
		updateUser.setEnabled(false);
		sleepUser.setEnabled(false);
		if(findUser.getText().equals("Buscar usuario")){
			findUser.setCaretPosition(0);
		}
	}
}

