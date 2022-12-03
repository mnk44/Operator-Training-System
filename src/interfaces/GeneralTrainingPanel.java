package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import classes.TrainingPrepare;
import extras.DataTable;

public class GeneralTrainingPanel extends JPanel {

	private static final long serialVersionUID = -3453890857506965873L;
	
	private static JTable table;
	private static DefaultTableModel date;
	private JButton trainning;
	int selected = -1;
	
	static TrainingPrepare train = null;

	public GeneralTrainingPanel(TrainingPrepare t) throws SQLException {
		train = t;
		
		setBorder(null);
		setBackground(Color.WHITE);
		setBounds(100, 100, 657, 433);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(15, 64, 620, 301);
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
					String title = (String) table.getValueAt(selected, 1);
					if(title.equals("Iniciado")){
						trainning.setText("Continuar entrenamiento");
						trainning.setEnabled(true);
						trainning.setBackground(new Color(255, 113, 19));
					}else if(title.equals("No iniciado")){
						trainning.setText("Iniciar entrenamiento");
						trainning.setEnabled(true);
						trainning.setBackground(new Color(255, 113, 19));
					}
				}else{
					trainning.setEnabled(false);
					trainning.setBackground(new Color(248, 159, 101));
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
		
		JLabel lblGestinDeProcesos = new JLabel("Lista de entrenamientos:");
		lblGestinDeProcesos.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestinDeProcesos.setForeground(new Color(255, 113, 19));
		lblGestinDeProcesos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblGestinDeProcesos.setBounds(15, 28, 620, 23);
		add(lblGestinDeProcesos);
		
		trainning = new JButton("Iniciar entrenamiento");
		trainning.setEnabled(false);
		trainning.setHorizontalTextPosition(SwingConstants.LEFT);
		trainning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		trainning.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(trainning.isEnabled()){
					trainning.setBackground(new Color(248, 159, 101));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(trainning.isEnabled()){
					trainning.setBackground(new Color(255, 113, 19));
				}
			}
		});
		trainning.setIcon(new ImageIcon(GeneralTrainingPanel.class.getResource("/images/icons8_Right_32.png")));
		trainning.setForeground(Color.WHITE);
		trainning.setFont(new Font("Segoe UI", Font.BOLD, 18));
		trainning.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		trainning.setBackground(new Color(248, 159, 101));
		trainning.setBounds(186, 380, 272, 37);
		add(trainning);

		reload();
	}
	
	public static void reload() throws SQLException{
		DataTable.fillPTrain(date, table, train);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
	}

}
