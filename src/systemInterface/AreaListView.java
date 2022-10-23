package systemInterface;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import systemClass.Area;
import systemEnums.MessagesType;
import systemLogic.FindObjects;
import systemLogic.TablesInfo;
import systemServices.AreaService;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class AreaListView extends JDialog {

	private static final long serialVersionUID = -4108300476534595872L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnNuevarea;
	private JButton btnModificarrea;
	private JButton btnEliminarrea;
	private JLabel lblNewLabel;
	private JTextField findArea;
	private DefaultTableModel date;
	private JTable table;

	@SuppressWarnings("unchecked")
	ArrayList<Area> areasList = (ArrayList<Area>) AreaService.getAreas();

	private int selected = -1;

	public static void main(String[] args) {
		try {
			AreaListView dialog = new AreaListView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public AreaListView() throws SQLException {
		setModal(true);
		setBackground(Color.WHITE);
		setBounds(100, 100, 822, 505);
		setTitle("Gesti\u00F3n de \u00E1reas");
		setResizable(false);
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AreaListView.class.getResource("/imgs/logo.png")));
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
		scrollPane.setBounds(26, 74, 533, 368);
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
					btnModificarrea.setEnabled(true);
					btnEliminarrea.setEnabled(true);
				}else{
					btnModificarrea.setEnabled(false);
					btnEliminarrea.setEnabled(false);
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
				String area_name = JOptionPane.showInputDialog(null, "Nombre del área nueva:", "Nueva área", JOptionPane.QUESTION_MESSAGE);
				if(area_name != null){
					if(!area_name.isEmpty() && !area_name.replaceAll(" ", "").isEmpty()){
						try {
							String result = systemServices.AreaService.newArea(area_name);
							if(result == null){
								JOptionPane.showMessageDialog(null, MessagesType.Acción_completada_satisfactoriamente.toString().replace("_", " "), "Acción completada", JOptionPane.INFORMATION_MESSAGE);
								areasList = (ArrayList<Area>) AreaService.getAreas();
								reloadTable(areasList);
							}else{
								JOptionPane.showMessageDialog(null, MessagesType.El_objeto_que_trata_de_crear_ya_existe.toString().replace("_", " "), "Error", JOptionPane.ERROR_MESSAGE);
								btnNuevarea.doClick();
							}
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, MessagesType.Debe_completar_todos_los_campos_para_avanzar.toString().replace("_", " "), "Error", JOptionPane.ERROR_MESSAGE);
						btnNuevarea.doClick();
					}
				}
			}
		});
		btnNuevarea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnNuevarea.setBackground(new Color(239, 196, 159));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnNuevarea.setBackground(new Color(244, 164, 96));
			}
		});
		btnNuevarea.setIcon(new ImageIcon(AreaListView.class.getResource("/imgs/icons8_Plus_16.png")));
		btnNuevarea.setForeground(Color.WHITE);
		btnNuevarea.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnNuevarea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		btnNuevarea.setBackground(new Color(244, 164, 96));
		btnNuevarea.setBounds(602, 148, 173, 37);
		contentPanel.add(btnNuevarea);

		btnModificarrea = new JButton("Modificar \u00E1rea");
		btnModificarrea.setEnabled(false);
		btnModificarrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = table.getSelectedRow();
				int id_area = (Integer) table.getValueAt(selected, 0);		
				String name_area = JOptionPane.showInputDialog("Nombre del área nueva:", (String) table.getValueAt(selected, 1));
				if(name_area != null){
					if(!name_area.isEmpty() && !name_area.replaceAll(" ", "").isEmpty()){
						try {
							String result = systemServices.AreaService.updateArea(id_area, name_area);
							if(result == null){
								JOptionPane.showMessageDialog(null, MessagesType.Acción_completada_satisfactoriamente.toString().replace("_", " "), "Acción completada", JOptionPane.INFORMATION_MESSAGE);
								areasList = (ArrayList<Area>) AreaService.getAreas();
								reloadTable(areasList);
							}else{
								JOptionPane.showMessageDialog(null, MessagesType.El_objeto_que_trata_de_crear_ya_existe.toString().replace("_", " "), "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, MessagesType.Debe_completar_todos_los_campos_para_avanzar.toString().replace("_", " "), "Error", JOptionPane.ERROR_MESSAGE);
						btnModificarrea.doClick();
					}
				}
			}
		});
		btnModificarrea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnModificarrea.isEnabled()){
					btnModificarrea.setBackground(new Color(239, 196, 159));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnModificarrea.setBackground(new Color(244, 164, 96));
			}
		});
		btnModificarrea.setIcon(new ImageIcon(AreaListView.class.getResource("/imgs/icons8_Pencil_16.png")));
		btnModificarrea.setForeground(Color.WHITE);
		btnModificarrea.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnModificarrea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		btnModificarrea.setBackground(new Color(244, 164, 96));
		btnModificarrea.setBounds(602, 227, 173, 37);
		contentPanel.add(btnModificarrea);

		btnEliminarrea = new JButton("Eliminar \u00E1rea");
		btnEliminarrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = table.getSelectedRow();
				int delete = JOptionPane.showConfirmDialog(null, "¿"+ MessagesType.Seguro_que_desea_realizar_esta_operación.toString().replace("_", " ") +"?", "Eliminar área", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				int id_area = (Integer) table.getValueAt(selected, 0);
				if(delete == 0){
					Object users = null;
					try {
						users = systemServices.UserService.getUsersArea(id_area);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(users.getClass().isArray()){
						JOptionPane.showMessageDialog(null, MessagesType.No_puede_eliminar_un_área_si_exisisten_usuarios_en_ella.toString().replace("_", " "), "Error", JOptionPane.ERROR_MESSAGE);
					}else{
						try {
							String result = systemServices.AreaService.deleteArea(id_area);
							if(result == null){
								JOptionPane.showMessageDialog(null, MessagesType.Acción_completada_satisfactoriamente.toString().replace("_", " "), "Acción completada", JOptionPane.INFORMATION_MESSAGE);
								reloadTable((ArrayList<Area>) AreaService.getAreas());
							}else{
								JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnEliminarrea.setEnabled(false);
		btnEliminarrea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnEliminarrea.isEnabled()){
					btnEliminarrea.setBackground(new Color(239, 196, 159));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnEliminarrea.setBackground(new Color(244, 164, 96));
			}
		});
		btnEliminarrea.setIcon(new ImageIcon(AreaListView.class.getResource("/imgs/icons8_Trash_16.png")));
		btnEliminarrea.setForeground(Color.WHITE);
		btnEliminarrea.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnEliminarrea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		btnEliminarrea.setBackground(new Color(244, 164, 96));
		btnEliminarrea.setBounds(602, 308, 173, 37);
		contentPanel.add(btnEliminarrea);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AreaListView.class.getResource("/imgs/search.png")));
		lblNewLabel.setBounds(32, 16, 48, 48);
		contentPanel.add(lblNewLabel);

		findArea = new JTextField();
		findArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(findArea.getText().equals("Buscar \u00E1rea por su nombre")){
					if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
						arg0.consume();
						getToolkit().beep();
					}else{
						findArea.setText("");
						findArea.setForeground(Color.BLACK);
						try {
							reloadTable(FindObjects.findArea(areasList, findArea.getText() + arg0.getKeyChar()));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else if(findArea.getText().length() == 1 && arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
					findArea.setText("Buscar \u00E1rea por su nombre");
					findArea.setForeground(Color.LIGHT_GRAY);
					arg0.consume();
					findArea.setCaretPosition(0);
					try {
						reloadTable(areasList);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
					try {
						reloadTable(FindObjects.findArea(areasList, findArea.getText().substring(0, findArea.getText().length()-1)));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						reloadTable(FindObjects.findArea(areasList, findArea.getText() + arg0.getKeyChar()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		findArea.setForeground(Color.LIGHT_GRAY);
		findArea.setText("Buscar \u00E1rea por su nombre");
		findArea.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		findArea.setColumns(10);
		findArea.setBounds(83, 26, 397, 28);
		contentPanel.add(findArea);

		reloadTable(areasList);
	}

	public void reloadTable(ArrayList<Area> areas) throws SQLException{
		TablesInfo.getAreas(date, table, areas);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
	}
}
