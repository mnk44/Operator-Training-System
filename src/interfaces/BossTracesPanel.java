package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import extras.DataTable;

public class BossTracesPanel extends JPanel {

	private static final long serialVersionUID = -3453890857506965873L;
	
	private static JTable table;
	private static DefaultTableModel date;
	JLabel lblGestinDeProcesos;
	JScrollPane scrollPane;
	int selected = -1;
	
	private JPanel panel;

	public BossTracesPanel(int area) throws SQLException {
		setBorder(null);
		setBackground(Color.WHITE);
		setBounds(100, 100, 779, 359);
	    setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 779, 359);
		add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 38, 710, 305);
		panel.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);

		table = new JTable();
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
		
		lblGestinDeProcesos = new JLabel("Resultados de los operarios:");
		lblGestinDeProcesos.setBounds(75, 13, 620, 23);
		panel.add(lblGestinDeProcesos);
		lblGestinDeProcesos.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestinDeProcesos.setForeground(new Color(255, 113, 19));
		lblGestinDeProcesos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));

		reload(area);
	}
	
	public static void reload(int area) throws SQLException{
		DataTable.fillTracesOp(date, table, area);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(3).setCellRenderer(tcr);
	}
}
