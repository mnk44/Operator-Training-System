package interfaces;

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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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

import classes.Process;
import extras.Search;
import extras.DataTable;
import services.ProcessService;

public class ProcessListView extends JDialog {

	private static final long serialVersionUID = 7774743997604860561L;
	private final JPanel contentPanel = new JPanel();
	private JButton newProcess;
	private static JButton updateProcess;
	private static JButton deleteProcess;
	private JLabel lblNewLabel;
	private JTextField findProcess;
	private static DefaultTableModel date;
	private static JTable table;

	private int selected = -1;
	
	@SuppressWarnings("unchecked")
	ArrayList<Process> processList = (ArrayList<Process>) ProcessService.getProcess();

	public static void main(String[] args) {
		try {
			ProcessListView dialog = new ProcessListView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProcessListView() throws SQLException {
		setModal(true);
		setBackground(Color.WHITE);
		setBounds(100, 100, 996, 548);
		setTitle("Gesti\u00F3n de procesos");
		setResizable(false);
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProcessListView.class.getResource("/imgs/logo.png")));
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
		scrollPane.setBounds(26, 74, 708, 402);
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
					updateProcess.setEnabled(true);
					deleteProcess.setEnabled(true);
				}else{
					updateProcess.setEnabled(false);
					deleteProcess.setEnabled(false);
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

		newProcess = new JButton("Nuevo proceso");
		newProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		newProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				newProcess.setBackground(new Color(239, 196, 159));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				newProcess.setBackground(new Color(244, 164, 96));
			}
		});
		newProcess.setIcon(new ImageIcon(ProcessListView.class.getResource("/imgs/icons8_Plus_16.png")));
		newProcess.setForeground(Color.WHITE);
		newProcess.setFont(new Font("Segoe UI", Font.BOLD, 18));
		newProcess.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		newProcess.setBackground(new Color(244, 164, 96));
		newProcess.setBounds(765, 130, 202, 37);
		contentPanel.add(newProcess);

		updateProcess = new JButton("Modificar proceso");
		updateProcess.setEnabled(false);
		updateProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		updateProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(updateProcess.isEnabled()){
					updateProcess.setBackground(new Color(239, 196, 159));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				updateProcess.setBackground(new Color(244, 164, 96));
			}
		});
		updateProcess.setIcon(new ImageIcon(ProcessListView.class.getResource("/imgs/icons8_Pencil_16.png")));
		updateProcess.setForeground(Color.WHITE);
		updateProcess.setFont(new Font("Segoe UI", Font.BOLD, 18));
		updateProcess.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		updateProcess.setBackground(new Color(244, 164, 96));
		updateProcess.setBounds(765, 208, 202, 37);
		contentPanel.add(updateProcess);

		deleteProcess = new JButton("Eliminar proceso");
		deleteProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		deleteProcess.setEnabled(false);
		deleteProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(deleteProcess.isEnabled()){
					deleteProcess.setBackground(new Color(239, 196, 159));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				deleteProcess.setBackground(new Color(244, 164, 96));
			}
		});
		deleteProcess.setIcon(new ImageIcon(ProcessListView.class.getResource("/imgs/icons8_Trash_16.png")));
		deleteProcess.setForeground(Color.WHITE);
		deleteProcess.setFont(new Font("Segoe UI", Font.BOLD, 18));
		deleteProcess.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		deleteProcess.setBackground(new Color(244, 164, 96));
		deleteProcess.setBounds(765, 292, 202, 37);
		contentPanel.add(deleteProcess);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ProcessListView.class.getResource("/imgs/search.png")));
		lblNewLabel.setBounds(32, 16, 48, 48);
		contentPanel.add(lblNewLabel);

		findProcess = new JTextField();
		findProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(findProcess.getText().equals("Buscar proceso por nombre")){
					findProcess.setCaretPosition(0);
				}
			}
		});
		findProcess.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(findProcess.getText().equals("Buscar proceso por nombre")){
					if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
						arg0.consume();
						getToolkit().beep();
					}else{
						findProcess.setText("");
						findProcess.setForeground(Color.BLACK);
						try {
							reloadTable(Search.findProcess(processList, findProcess.getText() + arg0.getKeyChar()));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else if(findProcess.getText().length() == 1 && arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
					findProcess.setText("Buscar proceso por nombre");
					findProcess.setForeground(Color.LIGHT_GRAY);
					arg0.consume();
					findProcess.setCaretPosition(0);
					try {
						reloadTable(processList);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
					try {
						reloadTable(Search.findProcess(processList, findProcess.getText().substring(0, findProcess.getText().length()-1)));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						reloadTable(Search.findProcess(processList, findProcess.getText() + arg0.getKeyChar()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		findProcess.setForeground(Color.LIGHT_GRAY);
		findProcess.setText("Buscar proceso por nombre");
		findProcess.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		findProcess.setColumns(10);
		findProcess.setBounds(83, 26, 499, 28);
		contentPanel.add(findProcess);

		reloadTable(processList);
	}

	public static void reloadTable(ArrayList<Process> processList) throws SQLException{
		DataTable.getProcess(date, table, processList);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(0).setMaxWidth(45);
		updateProcess.setEnabled(false);
		deleteProcess.setEnabled(false);
	}
}

