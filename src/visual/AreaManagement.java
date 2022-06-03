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

import services.AreaService;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AreaManagement extends JFrame {

	private static final long serialVersionUID = -3283975538421886447L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel date;
	private JButton newArea;
	private JButton updateArea;
	private JButton deleteArea;
	private int selected = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AreaManagement frame = new AreaManagement();
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
	public AreaManagement() throws SQLException {
		setAutoRequestFocus(false);
		setTitle("Gesti\u00F3n de \u00C1reas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 499);
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
		scrollPane.setBounds(15, 73, 328, 370);
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
					updateArea.setEnabled(true);
					deleteArea.setEnabled(true);
				}else{
					updateArea.setEnabled(false);
					deleteArea.setEnabled(false);
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

		newArea = new JButton("Nueva \u00C1rea");
		newArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name_area = JOptionPane.showInputDialog(null, "Escriba el nombre del Área nueva:", "Nueva Área", JOptionPane.INFORMATION_MESSAGE);
				if(name_area != null){
					if(!name_area.isEmpty() && !name_area.replaceAll(" ", "").isEmpty()){
						try {
							String result = AreaService.createArea(name_area);
							if(result == null){
								JOptionPane.showMessageDialog(null, "Área creada satisfactoriamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
								reloadTable();
							}else{
								JOptionPane.showMessageDialog(null, "El Área introducida ya existe", "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "El nombre del Área no puede ser vacío", "Error", JOptionPane.ERROR_MESSAGE);
						newArea.doClick();
					}
				}
			}
		});
		newArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				newArea.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				newArea.setBackground(new Color(255, 255, 201));
			}
		});
		newArea.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		newArea.setBackground(new Color(255, 255, 201));
		newArea.setIcon(new ImageIcon(AreaManagement.class.getResource("/img/icons8_Plus_Math_16.png")));
		newArea.setFont(new Font("Segoe UI", Font.BOLD, 17));
		newArea.setBounds(389, 88, 171, 38);
		contentPane.add(newArea);

		updateArea = new JButton("Modificar \u00C1rea");
		updateArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = table.getSelectedRow();
				int update = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea modificar esta Área?", "Modificar Área", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				int id_area = (Integer) table.getValueAt(selected, 0);
				if(update == 0){
					String name_area = JOptionPane.showInputDialog(null, "Escriba el nombre nuevo del Área:", "Modificar Área", JOptionPane.INFORMATION_MESSAGE);
					if(name_area != null){
						if(!name_area.isEmpty() && !name_area.replaceAll(" ", "").isEmpty()){
							try {
								String result = AreaService.updateArea(id_area, name_area);
								if(result == null){
									JOptionPane.showMessageDialog(null, "Área modificada satisfactoriamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
									reloadTable();
								}else{
									JOptionPane.showMessageDialog(null, "El Área introducida ya existe", "Error", JOptionPane.ERROR_MESSAGE);
								}
							} catch (SQLException e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(null, "El nombre del Área no puede ser vacío", "Error", JOptionPane.ERROR_MESSAGE);
							updateArea.doClick();
						}
					}
				}
			}
		});
		updateArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(updateArea.isEnabled()){
					updateArea.setBackground(new Color(255, 206, 126));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(updateArea.isEnabled()){
					updateArea.setBackground(new Color(255, 255, 201));
				}
			}
		});
		updateArea.setEnabled(false);
		updateArea.setIcon(new ImageIcon(AreaManagement.class.getResource("/img/icons8_Pencil_16.png")));
		updateArea.setFont(new Font("Segoe UI", Font.BOLD, 17));
		updateArea.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		updateArea.setBackground(new Color(255, 255, 201));
		updateArea.setBounds(389, 154, 171, 38);
		contentPane.add(updateArea);

		deleteArea = new JButton("Eliminar \u00C1rea");
		deleteArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = table.getSelectedRow();
				int delete = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar esta Área?", "Eliminar Área", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				int id_area = (Integer) table.getValueAt(selected, 0);
				if(delete == 0){
					try {
						String result = AreaService.deleteArea(id_area);
						if(result == null){
							JOptionPane.showMessageDialog(null, "Área eliminada satisfactoriamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
							reloadTable();
						}else{
							JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		deleteArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(deleteArea.isEnabled()){
					deleteArea.setBackground(new Color(255, 206, 126));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(deleteArea.isEnabled()){
					deleteArea.setBackground(new Color(255, 255, 201));
				}
			}
		});
		deleteArea.setEnabled(false);
		deleteArea.setBackground(new Color(255, 255, 201));
		deleteArea.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		deleteArea.setFont(new Font("Segoe UI", Font.BOLD, 17));
		deleteArea.setIcon(new ImageIcon(AreaManagement.class.getResource("/img/icons8_Trash_16.png")));
		deleteArea.setBounds(389, 222, 171, 38);
		contentPane.add(deleteArea);

		JLabel image = new JLabel("");
		image.setIcon(new ImageIcon(AreaManagement.class.getResource("/img/undraw_logistics_x-4-dc.png")));
		image.setBounds(358, 288, 230, 143);
		contentPane.add(image);
		
		reloadTable();
	}
	
	public void reloadTable() throws SQLException{
		FillTables.fillArea(date, table);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
	}
}
