package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import services.ProcessService;
import classes.ProcessConfiguration;
import classes.User;
import classes.Process;
import extras.DataTable;
import extras.Search;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class ProcessManagementPanel extends JPanel {
	
	private static JTable table;
	private static DefaultTableModel date;
	
	int selected = -1;

	private static final long serialVersionUID = -7006963620558751080L;
	private JTextField searchField;
	private JButton btnNuevoProceso;
	private static JButton btnModificarProceso;
	private static JButton btnEliminarProceso;
//	private JButton btnArchivoanm;
//	private JButton btnArchivodrl;
 
	public ProcessManagementPanel(final ArrayList<User> op, final User user_active, final ArrayList<Process> process, final ArrayList<ProcessConfiguration> con) throws SQLException {
		setBorder(null);
		setBackground(Color.WHITE);
		setBounds(100, 100, 765, 433);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(28, 104, 490, 313);
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
					btnModificarProceso.setEnabled(true);
					btnModificarProceso.setBackground(new Color(255, 113, 19));
					btnEliminarProceso.setEnabled(true);
					btnEliminarProceso.setBackground(new Color(255, 113, 19));
				}else{
					btnModificarProceso.setEnabled(false);
					btnModificarProceso.setBackground(new Color(248, 159, 101));
					btnEliminarProceso.setEnabled(false);
					btnEliminarProceso.setBackground(new Color(248, 159, 101));
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
		
		JLabel lblGestinDeProcesos = new JLabel("Gesti\u00F3n de procesos:");
		lblGestinDeProcesos.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestinDeProcesos.setForeground(new Color(255, 113, 19));
		lblGestinDeProcesos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblGestinDeProcesos.setBounds(28, 79, 490, 23);
		add(lblGestinDeProcesos);
		
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(ProcessManagementPanel.class.getResource("/images/search.png")));
		label.setBounds(45, 0, 48, 88);
		add(label);
		
		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(searchField.getText().length()==1 && arg0.getKeyChar()==KeyEvent.VK_BACK_SPACE){
					try {
						reload(process);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(arg0.getKeyChar()==KeyEvent.VK_BACK_SPACE){
					ArrayList<Process> s = null;
					try {
						s = Search.searchProcess(process, searchField.getText().substring(0,searchField.getText().length()-1));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						reload(s);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					ArrayList<Process> s = null;
					try {
						s = Search.searchProcess(process, (searchField.getText()+ arg0.getKeyChar()));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						reload(s);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		searchField.setToolTipText("Buscar usuario");
		searchField.setFont(new Font("Corbel", Font.PLAIN, 20));
		searchField.setColumns(10);
		searchField.setBounds(100, 27, 363, 29);
		add(searchField);
		
		btnNuevoProceso = new JButton("Nuevo proceso");
		btnNuevoProceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProcessView p = null;
				try {
					p = new ProcessView(op, user_active, null, null);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		btnNuevoProceso.setBounds(533, 116, 212, 37);
		add(btnNuevoProceso);
		
		btnModificarProceso = new JButton("Modificar proceso");
		btnModificarProceso.setEnabled(false);
		btnModificarProceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = table.getSelectedRow();
				boolean x = true;
				ProcessConfiguration c = null;
				Process y = null;
				for (int i = 0; i < process.size() && x; i++) {
					if((int) table.getValueAt(selected, 0) == process.get(i).getProcess_id()){
						y = process.get(i);
						x = false;
					}
				}
				for (int j = 0; j < con.size() && !x; j++){
					if(con.get(j).getProcess_id() == y.getProcess_id()){
						c = con.get(j);
						x = true;
					}
				}
				ProcessView uv = null;
				try {
					uv = new ProcessView(op, user_active, y, c);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				uv.setLocationRelativeTo(ProcessManagementPanel.this);
				uv.setVisible(true);
			}
		});
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
		btnModificarProceso.setBounds(533, 169, 212, 37);
		add(btnModificarProceso);
		
		btnEliminarProceso = new JButton("Eliminar proceso");
		btnEliminarProceso.setEnabled(false);
		btnEliminarProceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = table.getSelectedRow();
					try {
						String result = ProcessService.deleteProcess(process.get(selected).getProcess_id(), user_active.getUser_nick(), process.get(selected).getProcess_name(), new Timestamp(Calendar.getInstance().getTime().getTime()));
						if(result == null){
							JOptionPane.showMessageDialog(null, "Acci�n completada satisfactoriamente", "Acci�n completada", JOptionPane.INFORMATION_MESSAGE);						
							process.remove(selected);
							reload(process);
							searchField.setText("");
						}else{
							JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
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
		btnEliminarProceso.setBounds(533, 222, 212, 37);
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
		
		reload(process);
	}
	
	public static void reload(ArrayList<Process> process) throws SQLException{
		DataTable.fillProcess(date, table, process);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		btnModificarProceso.setEnabled(false);
		btnEliminarProceso.setEnabled(false);
		btnModificarProceso.setBackground(new Color(248, 159, 101));
		btnEliminarProceso.setBackground(new Color(248, 159, 101));
	}
}
