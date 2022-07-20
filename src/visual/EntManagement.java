package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

import services.ProcessService;
import logic.FillTables;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntManagement extends JFrame {

	private static final long serialVersionUID = -3283975538421886447L;
	private JPanel contentPane;
	private static JTable table;
	private static DefaultTableModel date;
	private JButton btnIrAlEntrenamiento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EntManagement frame = new EntManagement(5);
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
	public EntManagement(final int id_uss) throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserMangement.class.getResource("/img/Captura de pantalla (133).png")));
		setAutoRequestFocus(false);
		setTitle("Entrenamientos Disponibles");
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
		scrollPane.setBounds(15, 73, 644, 367);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int selected = table.getSelectedRow();

				if (selected != -1) {
					btnIrAlEntrenamiento.setEnabled(true);
				}else{
					btnIrAlEntrenamiento.setEnabled(false);
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

		btnIrAlEntrenamiento = new JButton("Ver Entrenamiento");
		btnIrAlEntrenamiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = 0;
				try {
					id = ProcessService.findByName((String) table.getValueAt(table.getSelectedRow(), 0)).getId_process();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					TrainerView t = new TrainerView(id);
					t.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnIrAlEntrenamiento.setEnabled(false);
		btnIrAlEntrenamiento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnIrAlEntrenamiento.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnIrAlEntrenamiento.setBackground(new Color(255, 255, 201));
			}
		});
		btnIrAlEntrenamiento.setIcon(new ImageIcon(EntManagement.class.getResource("/img/icons8_Show_Property_16.png")));
		btnIrAlEntrenamiento.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnIrAlEntrenamiento.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		btnIrAlEntrenamiento.setBackground(new Color(255, 255, 201));
		btnIrAlEntrenamiento.setBounds(238, 456, 212, 38);
		contentPane.add(btnIrAlEntrenamiento);
		
		FillTables.fillEnt(date, table, id_uss);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
	}
}
