package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import services.AreaService;
import classes.Area;
import classes.User;
import extras.DataTable;
import extras.Search;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AreaManagementPanel extends JPanel {

	private static final long serialVersionUID = 1719454491990485591L;
	private JTextField searchFile;
	private JButton btnNuevarea;
	private JButton btnModificarrea;
	private JButton btnEliminarrea;
	private JTable table;
	private DefaultTableModel date;

	int selected = -1;

	public AreaManagementPanel(final User user, final ArrayList<Area> areas, final ArrayList<User> usersList) throws SQLException {
		setBorder(null);
		setBackground(Color.WHITE);
		setBounds(100, 100, 789, 428);
		setLayout(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(AreaManagementPanel.class.getResource("/images/search.png")));
		lblNewLabel.setBounds(44, 16, 48, 58);
		add(lblNewLabel);

		searchFile = new JTextField();
		searchFile.setToolTipText("Buscar \u00E1rea por su nombre");
		searchFile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(searchFile.getText().length()==1 && arg0.getKeyChar()==KeyEvent.VK_BACK_SPACE){
					try {
						reload(areas);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(arg0.getKeyChar()==KeyEvent.VK_BACK_SPACE){
					ArrayList<Area> s = null;
					try {
						s = Search.searchAreas(areas, searchFile.getText().substring(0,searchFile.getText().length()-1));
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
					ArrayList<Area> s = null;
					try {
						s = Search.searchAreas(areas, (searchFile.getText()+ arg0.getKeyChar()));
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
		searchFile.setFont(new Font("Corbel", Font.PLAIN, 20));
		searchFile.setColumns(10);
		searchFile.setBounds(95, 32, 436, 29);
		add(searchFile);

		JLabel lblGestinDereas = new JLabel("Gesti\u00F3n de \u00E1reas:");
		lblGestinDereas.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestinDereas.setForeground(new Color(255, 113, 19));
		lblGestinDereas.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblGestinDereas.setBounds(38, 77, 517, 37);
		add(lblGestinDereas);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(38, 111, 517, 301);
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
					btnEliminarrea.setEnabled(true);
					btnModificarrea.setEnabled(true);
					btnModificarrea.setBackground(new Color(255, 113, 19));
					btnEliminarrea.setBackground(new Color(255, 113, 19));
				}else{
					btnModificarrea.setEnabled(false);
					btnEliminarrea.setEnabled(false);
					btnModificarrea.setBackground(new Color(248, 159, 101));
					btnEliminarrea.setBackground(new Color(248, 159, 101));
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

		btnNuevarea = new JButton("Nueva \u00E1rea");
		btnNuevarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String area_name = JOptionPane.showInputDialog(null, "Escriba el nombre del área nueva:");
				if(area_name != null){
					if(!area_name.isEmpty() && !area_name.replaceAll(" ", "").isEmpty()){
						try {
							Object result = AreaService.newArea(area_name, user.getUser_nick(), new Timestamp(Calendar.getInstance().getTime().getTime()));
							if(!result.toString().contains("e")){
								JOptionPane.showMessageDialog(null, "Acción completada satisfactoriamente", "Acción completada", JOptionPane.INFORMATION_MESSAGE);						
								areas.add(new Area((int) result, area_name));
								reload(areas);
								searchFile.setText("");
							}else{
								JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Debe escribir un nombre válido", "Error", JOptionPane.ERROR_MESSAGE);
						btnNuevarea.doClick();
					}
				}
			}
		});
		btnNuevarea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNuevarea.setBackground(new Color(248, 159, 101));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNuevarea.setBackground(new Color(255, 113, 19));
			}
		});
		btnNuevarea.setIcon(new ImageIcon(AreaManagementPanel.class.getResource("/images/new.png")));
		btnNuevarea.setForeground(Color.WHITE);
		btnNuevarea.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnNuevarea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		btnNuevarea.setBackground(new Color(255, 113, 19));
		btnNuevarea.setBounds(582, 135, 192, 37);
		add(btnNuevarea);

		btnModificarrea = new JButton("Modificar \u00E1rea");
		btnModificarrea.setEnabled(false);
		btnModificarrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = table.getSelectedRow();
				Area area = areas.get(selected);
				String area_name = JOptionPane.showInputDialog(null, "Escriba el nuevo nombre del área:", (String) table.getValueAt(selected, 1));
				if(area_name != null){
					if(!area_name.isEmpty() && !area_name.replaceAll(" ", "").isEmpty()){
						try {
							String result = AreaService.updateArea(area.getId_area(), area_name, user.getUser_nick(), new Timestamp(Calendar.getInstance().getTime().getTime()));
							if(result == null){
								JOptionPane.showMessageDialog(null, "Acción completada satisfactoriamente", "Acción completada", JOptionPane.INFORMATION_MESSAGE);						
								area.setName_area(area_name);
								areas.set(selected, area);
								reload(areas);
								searchFile.setText("");
							}else{
								JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Debe escribir un nombre válido", "Error", JOptionPane.ERROR_MESSAGE);
						btnNuevarea.doClick();
					}
				}
			}
		});
		btnModificarrea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnModificarrea.isEnabled()){
					btnModificarrea.setBackground(new Color(248, 159, 101));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(btnModificarrea.isEnabled()){
					btnModificarrea.setBackground(new Color(255, 113, 19));
				}
			}
		});
		btnModificarrea.setIcon(new ImageIcon(AreaManagementPanel.class.getResource("/images/update.png")));
		btnModificarrea.setForeground(Color.WHITE);
		btnModificarrea.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnModificarrea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		btnModificarrea.setBackground(new Color(248, 159, 101));
		btnModificarrea.setBounds(582, 209, 192, 37);
		add(btnModificarrea);

		btnEliminarrea = new JButton("Eliminar \u00E1rea");
		btnEliminarrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = table.getSelectedRow();
				boolean exist = false;
				for(int i=0;i<usersList.size() && !exist;i++){
					if(usersList.get(i).getUser_area() == areas.get(selected).getId_area()){
						exist = true;
					}
				}
				if(!exist){
					try {
						String result = AreaService.deleteArea(areas.get(selected).getId_area(), user.getUser_nick(), areas.get(selected).getName_area(), new Timestamp(Calendar.getInstance().getTime().getTime()));
						if(result == null){
							JOptionPane.showMessageDialog(null, "Acción completada satisfactoriamente", "Acción completada", JOptionPane.INFORMATION_MESSAGE);						
							areas.remove(selected);
							reload(areas);
							searchFile.setText("");
						}else{
							JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "No se puede eliminar un área si esta contiene usuarios", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminarrea.setEnabled(false);
		btnEliminarrea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnEliminarrea.isEnabled()){
					btnEliminarrea.setBackground(new Color(248, 159, 101));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(btnEliminarrea.isEnabled()){
					btnEliminarrea.setBackground(new Color(255, 113, 19));
				}
			}
		});
		btnEliminarrea.setIcon(new ImageIcon(AreaManagementPanel.class.getResource("/images/delete.png")));
		btnEliminarrea.setForeground(Color.WHITE);
		btnEliminarrea.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnEliminarrea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		btnEliminarrea.setBackground(new Color(248, 159, 101));
		btnEliminarrea.setBounds(582, 284, 192, 37);
		add(btnEliminarrea);

		reload(areas);
	}

	public void reload(ArrayList<Area> areas) throws SQLException{
		DataTable.fillArea(date, table, areas);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		btnModificarrea.setEnabled(false);
		btnEliminarrea.setEnabled(false);
		btnModificarrea.setBackground(new Color(248, 159, 101));
		btnEliminarrea.setBackground(new Color(248, 159, 101));
	}
}
