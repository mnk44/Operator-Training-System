package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class ProcessManagement extends JFrame {

	private static final long serialVersionUID = -488723709801092457L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel date;
	private JButton newProcess;
	private JButton updateProcess;
	private JButton deleteProcess;
	private int selected = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProcessManagement frame = new ProcessManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public ProcessManagement() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProcessManagement.class.getResource("/img/Captura de pantalla (133).png")));
		setAutoRequestFocus(false);
		setTitle("Gesti\u00F3n de Procesos");
		setResizable(false);
		setBounds(100, 100, 691, 555);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		newProcess = new JButton("Nuevo Proceso");
		newProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				newProcess.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				newProcess.setBackground(new Color(255, 255, 201));
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(15, 73, 405, 412);
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
		newProcess.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		newProcess.setBackground(new Color(255, 255, 201));
		newProcess.setIcon(new ImageIcon(ProcessManagement.class.getResource("/img/icons8_Plus_Math_16.png")));
		newProcess.setFont(new Font("Segoe UI", Font.BOLD, 17));
		newProcess.setBounds(460, 100, 182, 38);
		contentPane.add(newProcess);

		updateProcess = new JButton("Modificar Proceso");
		updateProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(updateProcess.isEnabled()){
					updateProcess.setBackground(new Color(255, 206, 126));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(updateProcess.isEnabled()){
					updateProcess.setBackground(new Color(255, 255, 201));
				}
			}
		});
		updateProcess.setEnabled(false);
		updateProcess.setIcon(new ImageIcon(ProcessManagement.class.getResource("/img/icons8_Pencil_16.png")));
		updateProcess.setFont(new Font("Segoe UI", Font.BOLD, 17));
		updateProcess.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		updateProcess.setBackground(new Color(255, 255, 201));
		updateProcess.setBounds(460, 171, 182, 38);
		contentPane.add(updateProcess);

		deleteProcess = new JButton("Eliminar Proceso");
		deleteProcess.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(deleteProcess.isEnabled()){
					deleteProcess.setBackground(new Color(255, 206, 126));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(deleteProcess.isEnabled()){
					deleteProcess.setBackground(new Color(255, 255, 201));
				}
			}
		});
		deleteProcess.setEnabled(false);
		deleteProcess.setBackground(new Color(255, 255, 201));
		deleteProcess.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		deleteProcess.setFont(new Font("Segoe UI", Font.BOLD, 17));
		deleteProcess.setIcon(new ImageIcon(ProcessManagement.class.getResource("/img/icons8_Trash_16.png")));
		deleteProcess.setBounds(460, 244, 182, 38);
		contentPane.add(deleteProcess);

		JLabel image = new JLabel("");
		image.setIcon(new ImageIcon(ProcessManagement.class.getResource("/img/undraw_working_remotely_re_6b3a.png")));
		image.setBounds(427, 298, 243, 201);
		contentPane.add(image);

		
	}

//	public void reloadTable() throws SQLException{
//		FillTables.fillArea(date, table);
//		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
//		tcr.setHorizontalAlignment(SwingConstants.CENTER);
//		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
//		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
//		table.getColumnModel().getColumn(0).setPreferredWidth(15);
//	}

}
