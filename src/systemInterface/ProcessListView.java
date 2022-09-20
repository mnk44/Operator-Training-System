package systemInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import systemLogic.TablesInfo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;

public class ProcessListView {

	private JFrame frame;
	private static JTable table;
	private static DefaultTableModel date;
	private JTextField textField;
	private JButton viewInfo;
	
	int selected = -1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcessListView window = new ProcessListView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProcessListView() throws SQLException {
		initialize();
	}

	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(BossView.class.getResource("/imgs/logo.png")));
		frame.setBackground(new Color(173, 216, 230));
		frame.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		frame.setForeground(Color.BLACK);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		frame.setAlwaysOnTop(true);
		frame.setTitle("Listado de procesos");
		frame.setResizable(false);
		frame.setBounds(100, 100, 1080, 676);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(45, 70, 990, 494);
		frame.getContentPane().add(scrollPane);
		
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
					viewInfo.setEnabled(true);
				}else{
					viewInfo.setEnabled(false);
				}
			}
		});
		table.setGridColor(Color.WHITE);
		table.setRowMargin(1);
		table.setRowHeight(24);
		table.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 20));
		table.getTableHeader().setBackground(new Color(245, 245, 245));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Segoe UI", Font.BOLD, 18));
		table.setFillsViewportHeight(true);
		table.setBorder(null);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JLabel lblBuscar = new JLabel("");
		lblBuscar.setIcon(new ImageIcon(ProcessListView.class.getResource("/imgs/search.png")));
		lblBuscar.setForeground(new Color(47, 46, 65));
		lblBuscar.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblBuscar.setBounds(80, 16, 45, 43);
		frame.getContentPane().add(lblBuscar);
		
		textField = new JTextField();
		textField.setText("Buscar proceso por su nombre");
		textField.setForeground(Color.GRAY);
		textField.setCaretPosition(0);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char letter = arg0.getKeyChar();
				
				if(letter == KeyEvent.VK_BACK_SPACE){
					if(textField.getText().toCharArray().length == 1){
						textField.setText("Buscar proceso por su nombre");
						textField.setForeground(Color.GRAY);
						textField.setCaretPosition(0);
						arg0.consume();
					}
				}else{
					if(textField.getForeground() == Color.GRAY){
						textField.setText("");
					}
					textField.setForeground(Color.BLACK);
				}
			}
		});
		
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBounds(130, 25, 471, 29);
		frame.getContentPane().add(textField);
		
		viewInfo = new JButton("Mostrar informaci\u00F3n");
		viewInfo.setEnabled(false);
		viewInfo.setForeground(Color.WHITE);
		viewInfo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		viewInfo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		viewInfo.setBackground(new Color(244, 164, 96));
		viewInfo.setBounds(414, 583, 256, 37);
		frame.getContentPane().add(viewInfo);
		
		reloadTable();
	}

	public static void reloadTable() throws SQLException{
		TablesInfo.getProcess(date, table);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
	}
}
