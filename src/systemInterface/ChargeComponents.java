package systemInterface;

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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import systemClass.Area;
import systemClass.User;
import systemEnums.RolesTypes;
import systemLogic.FindObjects;
import systemServices.AreaService;

public class ChargeComponents {
	static int selected = -1;
	
	public static void personalInfo(final JPanel personal_infor_panel, User user_conected) throws SQLException{		
		JLabel photo = new JLabel();
		photo.setIcon(new ImageIcon(CenterView.class.getResource("/images/user-photo.png")));
		photo.setBounds(15, 44, 110, 110);
		personal_infor_panel.add(photo);

		JLabel user_name = new JLabel(user_conected.getUser_name());
		user_name.setHorizontalAlignment(SwingConstants.LEFT);
		user_name.setForeground(new Color(47, 46, 65));
		user_name.setFont(new Font("Segoe UI", Font.BOLD, 22));
		user_name.setBounds(130, 62, 432, 37);
		personal_infor_panel.add(user_name);
		
		JLabel user_nick = new JLabel(user_conected.getUser_nick());
		user_nick.setHorizontalAlignment(SwingConstants.CENTER);
		user_nick.setForeground(new Color(47, 46, 65));
		user_nick.setFont(new Font("Segoe UI", Font.ITALIC, 22));
		user_nick.setBounds(130, 99, 233, 37);
		personal_infor_panel.add(user_nick);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(118, 152, 440, 2);
		personal_infor_panel.add(separator);
		
		JLabel card_label = new JLabel("Carnet de identidad:");
		card_label.setHorizontalAlignment(SwingConstants.RIGHT);
		card_label.setForeground(new Color(47, 46, 65));
		card_label.setFont(new Font("Segoe UI", Font.BOLD, 22));
		card_label.setBounds(25, 170, 233, 48);
		personal_infor_panel.add(card_label);
		
		JLabel schoolar_label = new JLabel("Nivel escolar:");
		schoolar_label.setHorizontalAlignment(SwingConstants.RIGHT);
		schoolar_label.setForeground(new Color(47, 46, 65));
		schoolar_label.setFont(new Font("Segoe UI", Font.BOLD, 22));
		schoolar_label.setBounds(35, 222, 223, 48);
		personal_infor_panel.add(schoolar_label);
		
		JLabel rol_label = new JLabel("Rol:");
		rol_label.setHorizontalAlignment(SwingConstants.RIGHT);
		rol_label.setForeground(new Color(47, 46, 65));
		rol_label.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rol_label.setBounds(44, 270, 214, 48);
		personal_infor_panel.add(rol_label);
		
		JLabel experience_label = new JLabel("A\u00F1os de experiencia:");
		experience_label.setHorizontalAlignment(SwingConstants.RIGHT);
		experience_label.setForeground(new Color(47, 46, 65));
		experience_label.setFont(new Font("Segoe UI", Font.BOLD, 22));
		experience_label.setBounds(25, 321, 233, 48);
		personal_infor_panel.add(experience_label);
		
		JLabel boss_label = new JLabel("A\u00F1os de jefe:");
		boss_label.setHorizontalAlignment(SwingConstants.RIGHT);
		boss_label.setForeground(new Color(47, 46, 65));
		boss_label.setFont(new Font("Segoe UI", Font.BOLD, 22));
		boss_label.setBounds(91, 368, 167, 48);
		personal_infor_panel.add(boss_label);
		
		JLabel area_label = new JLabel("\u00C1rea:");
		area_label.setHorizontalAlignment(SwingConstants.RIGHT);
		area_label.setForeground(new Color(47, 46, 65));
		area_label.setFont(new Font("Segoe UI", Font.BOLD, 22));
		area_label.setBounds(167, 419, 91, 48);
		personal_infor_panel.add(area_label);
		
		JLabel title = new JLabel("Informaci\u00F3n personal del usuario:");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(new Color(243,193,67));
		title.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		title.setBounds(36, 0, 504, 48);
		personal_infor_panel.add(title);
		
		JLabel card = new JLabel(user_conected.getUser_card());
		card.setHorizontalAlignment(SwingConstants.LEFT);
		card.setForeground(Color.GRAY);
		card.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		card.setBounds(264, 176, 294, 37);
		personal_infor_panel.add(card);
		
		JLabel schoolar = new JLabel(user_conected.getUser_academic().toString().replace("_", " "));
		schoolar.setHorizontalAlignment(SwingConstants.LEFT);
		schoolar.setForeground(Color.GRAY);
		schoolar.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		schoolar.setBounds(264, 228, 294, 37);
		personal_infor_panel.add(schoolar);
		
		JLabel rol = new JLabel(user_conected.getUser_rol().toString().replace("_", " "));
		rol.setHorizontalAlignment(SwingConstants.LEFT);
		rol.setForeground(Color.GRAY);
		rol.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		rol.setBounds(264, 276, 298, 37);
		personal_infor_panel.add(rol);
		
		JLabel area = new JLabel(((Area) AreaService.findId(user_conected.getUser_area())).getArea_name());
		area.setHorizontalAlignment(SwingConstants.LEFT);
		area.setForeground(Color.GRAY);
		area.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		area.setBounds(264, 425, 294, 37);
		personal_infor_panel.add(area);
		
		JLabel boss = new JLabel();
		if(user_conected.getUser_rol().equals(RolesTypes.Jefe_de_área)){
			boss.setText(Integer.toString(user_conected.getUser_position_years()));
		}else{
			boss.setText("-");
		}
		boss.setHorizontalAlignment(SwingConstants.LEFT);
		boss.setForeground(Color.GRAY);
		boss.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		boss.setBounds(267, 374, 295, 37);
		personal_infor_panel.add(boss);
		
		JLabel experience = new JLabel(Integer.toString(user_conected.getUser_experience()));
		experience.setHorizontalAlignment(SwingConstants.LEFT);
		experience.setForeground(Color.GRAY);
		experience.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		experience.setBounds(267, 327, 295, 37);
		personal_infor_panel.add(experience);
		
		final JButton close = new JButton("Limpiar vista");
		close.setIcon(new ImageIcon(CenterView.class.getResource("/images/clear.png")));
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				personal_infor_panel.setVisible(false);
			}
		});
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				close.setBackground(new Color(214, 230, 157));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				close.setBackground(new Color(164, 195, 55));
			}
		});
		close.setForeground(Color.WHITE);
		close.setFont(new Font("Segoe UI", Font.BOLD, 18));
		close.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(164, 195, 55)));
		close.setBackground(new Color(164, 195, 55));
		close.setBounds(184, 499, 214, 37);
		personal_infor_panel.add(close);
	}

	public static void areaManagement(final JPanel area_manage,final ArrayList<Area> areasList, final User user_conected) throws SQLException{	
		final JTable table = new JTable();			
		final JTextField find_field = new JTextField("Buscar");
		final JButton update_area = new JButton("Modificar \u00E1rea");
		final JButton delete_area = new JButton("Eliminar \u00E1rea");
		
		JLabel title = new JLabel("Gesti\u00F3n de \u00E1reas:");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(new Color(243,193,67));
		title.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		title.setBounds(0, 5, 913, 31);
		area_manage.add(title);		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(161, 111, 575, 409);
		area_manage.add(scrollPane);
		
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
					delete_area.setEnabled(true);
					update_area.setEnabled(true);
				}else{
					update_area.setEnabled(false);
					delete_area.setEnabled(false);
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
		
		final JButton close = new JButton("Limpiar vista");
		close.setIcon(new ImageIcon(CenterView.class.getResource("/images/clear.png")));
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				area_manage.setVisible(false);
			}
		});
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				close.setBackground(new Color(214, 230, 157));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				close.setBackground(new Color(164, 195, 55));
			}
		});
		close.setForeground(Color.WHITE);
		close.setFont(new Font("Segoe UI", Font.BOLD, 18));
		close.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(164, 195, 55)));
		close.setBackground(new Color(164, 195, 55));
		close.setBounds(767, 464, 214, 37);
		area_manage.add(close);

		JLabel photo = new JLabel("");
		photo.setIcon(new ImageIcon(CenterView.class.getResource("/images/search.png")));
		photo.setBounds(191, 45, 48, 50);
		area_manage.add(photo);

		find_field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(find_field.getText().equals("Buscar")){
					find_field.setCaretPosition(0);
				}
			}
		});
		find_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(find_field.getText().equals("Buscar")){
					if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
						arg0.consume();
						Toolkit.getDefaultToolkit().beep();
					}else{
						find_field.setText("");
						find_field.setForeground(Color.BLACK);
						try {
							CenterView.areaTable(FindObjects.findArea(areasList, find_field.getText() + arg0.getKeyChar()), table, update_area, delete_area, find_field);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else if(find_field.getText().length() == 1 && arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
					find_field.setText("Buscar");
					find_field.setForeground(Color.LIGHT_GRAY);
					arg0.consume();
					find_field.setCaretPosition(0);
					try {
						CenterView.areaTable(areasList, table, update_area, delete_area, find_field);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
					try {
						CenterView.areaTable(FindObjects.findArea(areasList, find_field.getText().substring(0, find_field.getText().length()-1)), table, update_area, delete_area, find_field);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						CenterView.areaTable(FindObjects.findArea(areasList, find_field.getText() + arg0.getKeyChar()), table, update_area, delete_area, find_field);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		find_field.setText("Buscar");
		find_field.setForeground(Color.LIGHT_GRAY);
		find_field.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		find_field.setColumns(10);
		find_field.setBounds(240, 52, 456, 31);
		find_field.setCaretPosition(0);
		area_manage.add(find_field);

		final JButton new_area = new JButton("Nueva \u00E1rea");
		new_area.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String area_name = JOptionPane.showInputDialog(null, "Nuevo nombre del área:", "Nueva área", JOptionPane.QUESTION_MESSAGE);
				if(area_name != null){
					if(!area_name.isEmpty() && !area_name.replaceAll(" ", "").isEmpty()){
						try {
							Object result = systemServices.AreaService.newArea(area_name,user_conected.getUser_id(), "Agregar área nueva", area_name, new Timestamp(Calendar.getInstance().getTime().getTime()));
							if(result == null){
								JOptionPane.showMessageDialog(null, "Acción completada satisfactoriamente", "Acción completada", JOptionPane.INFORMATION_MESSAGE);						
								areasList.add((Area)systemServices.AreaService.findName(area_name));
								CenterView.areaTable(areasList, table, update_area, delete_area, find_field);
							}else{
								JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
								new_area.doClick();
							}
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Debes completar todos los campos para continuar", "Error", JOptionPane.ERROR_MESSAGE);
						new_area.doClick();
					}
				}
			}
		});
		new_area.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				new_area.setBackground(new Color(184, 225, 243));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				new_area.setBackground(new Color(74, 154, 190));
			}
		});
		new_area.setIcon(new ImageIcon(CenterView.class.getResource("/images/new.png")));
		new_area.setForeground(Color.WHITE);
		new_area.setFont(new Font("Segoe UI", Font.BOLD, 18));
		new_area.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(74, 154, 190)));
		new_area.setBackground(new Color(74, 154, 190));
		new_area.setBounds(767, 139, 214, 37);
		area_manage.add(new_area);

		update_area.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected = table.getSelectedRow();
				int id_area = (Integer) table.getValueAt(selected, 0);		
				String name_area = JOptionPane.showInputDialog("Nuevo nombre del área:", (String) table.getValueAt(selected, 1));
				if(name_area != null){
					if(!name_area.isEmpty() && !name_area.replaceAll(" ", "").isEmpty()){
						try {
							String result = systemServices.AreaService.updateArea(id_area, name_area, user_conected.getUser_id(), "Modificar área", name_area, new Timestamp(Calendar.getInstance().getTime().getTime()));
							if(result == null){
								JOptionPane.showMessageDialog(null, "Acción completada satisfactoriamente", "Acción completada", JOptionPane.INFORMATION_MESSAGE);
								areasList.get(table.getSelectedRow()).setArea_name(name_area);
								CenterView.areaTable(areasList, table, update_area, delete_area, find_field);
							}else{
								JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Debes completar todos los campos para continuar", "Error", JOptionPane.ERROR_MESSAGE);
						update_area.doClick();
					}
				}
			}
		});
		update_area.setEnabled(false);
		update_area.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				update_area.setBackground(new Color(250, 225, 161));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				update_area.setBackground(new Color(243,193,67));
			}
		});
		update_area.setIcon(new ImageIcon(CenterView.class.getResource("/images/update.png")));
		update_area.setForeground(Color.WHITE);
		update_area.setFont(new Font("Segoe UI", Font.BOLD, 18));
		update_area.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(243,193,67)));
		update_area.setBackground(new Color(243,193,67));
		update_area.setBounds(767, 198, 214, 37);
		area_manage.add(update_area);

		delete_area.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el área?", "Eliminar área", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				selected = table.getSelectedRow();
				int id_area = (Integer) table.getValueAt(selected, 0);
				if(option == 0){
					String result = null;
					try {
						result = systemServices.AreaService.deleteArea(id_area, user_conected.getUser_id(), "Eliminar área", (String) table.getValueAt(selected, 1), new Timestamp(Calendar.getInstance().getTime().getTime()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(result == null){
						JOptionPane.showMessageDialog(null, "Acción completada satisfactoriamente", "Acción completada", JOptionPane.INFORMATION_MESSAGE);
						areasList.remove(table.getSelectedRow());
						try {
							CenterView.areaTable(areasList, table, update_area, delete_area, find_field);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		delete_area.setEnabled(false);
		delete_area.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				delete_area.setBackground(new Color(240, 152, 154));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				delete_area.setBackground(new Color(233, 81, 84));
			}
		});
		delete_area.setIcon(new ImageIcon(CenterView.class.getResource("/images/delete.png")));
		delete_area.setForeground(Color.WHITE);
		delete_area.setFont(new Font("Segoe UI", Font.BOLD, 18));
		delete_area.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(233, 81, 84)));
		delete_area.setBackground(new Color(233, 81, 84));
		delete_area.setBounds(767, 261, 214, 37);
		area_manage.add(delete_area);
		
		CenterView.areaTable(areasList, table, update_area, delete_area, find_field);
	}
}
