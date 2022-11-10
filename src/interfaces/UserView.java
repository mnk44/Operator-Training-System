package interfaces;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.border.MatteBorder;
import javax.swing.JRadioButton;

import classes.Area;
import classes.User;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import services.UserService;

public class UserView extends JDialog {

	private static final long serialVersionUID = 8521115610247925177L;
	private JTextField name;
	private JTextField card;
	@SuppressWarnings("rawtypes")
	private JComboBox sex;
	@SuppressWarnings("rawtypes")
	private JComboBox level;
	private JButton button;
	private JTextField nick;
	private JRadioButton active;
	@SuppressWarnings("rawtypes")
	private JComboBox rol;
	private JLabel pass;
	@SuppressWarnings("rawtypes")
	private JComboBox areasCB;
	private JSpinner boss;
	private JSpinner experience;
	private JLabel lblAosDeExperiencia_1;
	
	User modificated = null;

	public static void main(String[] args) {
		try {
			UserView dialog = new UserView(null, null, null, -1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserView(final ArrayList<Area> areas, final ArrayList<User> users, final User user_act, final int id) {
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 640, 413);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 634, 306);
		tabbedPane.setForeground(new Color(99, 68, 55));
		tabbedPane.setFont(new Font("Dubai", Font.PLAIN, 20));
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Información personal", new ImageIcon(UserView.class.getResource("/images/icons8_User_16.png")), panel, null);
		panel.setLayout(null);
		
		JLabel lblNombreCompleto = new JLabel("Nombre completo:");
		lblNombreCompleto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombreCompleto.setForeground(new Color(255, 113, 19));
		lblNombreCompleto.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblNombreCompleto.setBounds(15, 16, 229, 43);
		panel.add(lblNombreCompleto);
		
		name = new JTextField();
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean capital = key >= 65 && key <= 90;
				boolean lower = key >= 97 && key <= 122;
				boolean space = key == 32;

				if(!(capital || lower || space)){
					if(key != 225 && key != 233 && key != 237 && key != 243 && key != 250 && key != 241){
						e.consume();
						if(key != KeyEvent.VK_BACK_SPACE){
							getToolkit().beep();
						}
					}
				}else if(Character.isWhitespace(key) && name.getText().replace(" ", "").isEmpty()){
					e.consume();
					getToolkit().beep();
				}
			}
		});
		name.setFont(new Font("Corbel", Font.PLAIN, 20));
		name.setColumns(10);
		name.setBounds(255, 22, 352, 29);
		panel.add(name);
		
		JLabel lblCarnetDeIdentidad = new JLabel("Carnet de identidad:");
		lblCarnetDeIdentidad.setForeground(new Color(255, 113, 19));
		lblCarnetDeIdentidad.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblCarnetDeIdentidad.setBounds(15, 75, 237, 50);
		panel.add(lblCarnetDeIdentidad);
		
		card = new JTextField();
		card.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numbers = key >= 48 && key <= 57;
				if (!numbers || Character.isWhitespace(key))
				{
					e.consume();
					if(key != KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();
					}
				}
				if (card.getText().trim().length() < 11) {
					card.setForeground(Color.RED);
				}else if(card.getText().trim().length() == 11){
					e.consume();
					getToolkit().beep();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(card.getText().trim().length() == 11){
					card.setForeground(Color.BLACK);
				}
			}
		});
		card.setFont(new Font("Corbel", Font.PLAIN, 21));
		card.setColumns(10);
		card.setBounds(257, 84, 183, 29);
		panel.add(card);
		
		JLabel lblGnero = new JLabel("G\u00E9nero:");
		lblGnero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGnero.setForeground(new Color(255, 113, 19));
		lblGnero.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblGnero.setBounds(144, 132, 108, 50);
		panel.add(lblGnero);
		
		sex = new JComboBox();
		sex.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		sex.setBackground(Color.WHITE);
		sex.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		sex.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		sex.setBounds(257, 145, 229, 26);
		panel.add(sex);
		
		JLabel lblNivelEscolar = new JLabel("Nivel escolar:");
		lblNivelEscolar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNivelEscolar.setForeground(new Color(255, 113, 19));
		lblNivelEscolar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblNivelEscolar.setBounds(74, 187, 178, 50);
		panel.add(lblNivelEscolar);
		
		level = new JComboBox();
		level.setModel(new DefaultComboBoxModel(new String[] {"Preescolar", "Primaria", "Secundaria", "Preuniversitario", "T\u00E9cnico medio", "Universitario", "Ingeniero", "Licenciado", "M\u00E1ster", "Doctor"}));
		level.setSelectedIndex(5);
		level.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		level.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		level.setBackground(Color.WHITE);
		level.setBounds(257, 200, 229, 26);
		panel.add(level);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Usuario", new ImageIcon(UserView.class.getResource("/images/icons8_User_Menu_Male_16.png")), panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNombreDelUsuario = new JLabel("Nombre del usuario:");
		lblNombreDelUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombreDelUsuario.setForeground(new Color(255, 113, 19));
		lblNombreDelUsuario.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblNombreDelUsuario.setBounds(15, 16, 249, 49);
		panel_1.add(lblNombreDelUsuario);
		
		nick = new JTextField();
		nick.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(Character.isWhitespace(e.getKeyChar())){
					e.consume();
					getToolkit().beep();
				}
			}
		});
		nick.setFont(new Font("Corbel", Font.PLAIN, 20));
		nick.setColumns(10);
		nick.setBounds(273, 25, 315, 29);
		panel_1.add(nick);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.TRAILING);
		lblContrasea.setForeground(new Color(255, 113, 19));
		lblContrasea.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblContrasea.setBounds(88, 72, 176, 43);
		panel_1.add(lblContrasea);
		
		pass = new JLabel("secproit123*");
		pass.setHorizontalAlignment(SwingConstants.LEFT);
		pass.setForeground(new Color(99, 68, 55));
		pass.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		pass.setBounds(272, 72, 330, 43);
		panel_1.add(pass);
		
		JLabel lblRol = new JLabel("Rol del usuario:");
		lblRol.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRol.setForeground(new Color(255, 113, 19));
		lblRol.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblRol.setBounds(80, 124, 184, 43);
		panel_1.add(lblRol);
		
		rol = new JComboBox();
		rol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rol.getSelectedIndex() != 1){
					lblAosDeExperiencia_1.setEnabled(false);
					boss.setEnabled(false);
					boss.setValue(0);
				}else{
					lblAosDeExperiencia_1.setEnabled(true);
					boss.setEnabled(true);
				}
			}
		});
		rol.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Jefe de \u00E1rea", "Operario"}));
		rol.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		rol.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		rol.setBackground(Color.WHITE);
		rol.setBounds(273, 131, 229, 26);
		panel_1.add(rol);
		
		active = new JRadioButton("Activo:  ");
		active.setSelected(true);
		active.setHorizontalTextPosition(SwingConstants.LEFT);
		active.setHorizontalAlignment(SwingConstants.RIGHT);
		active.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		active.setForeground(new Color(255, 113, 19));
		active.setBackground(Color.WHITE);
		active.setBounds(146, 180, 155, 29);
		panel_1.add(active);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Habilidades", new ImageIcon(UserView.class.getResource("/images/icons8_Worker_16.png")), panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblAosDeExperiencia = new JLabel("A\u00F1os de experiencia laboral:");
		lblAosDeExperiencia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAosDeExperiencia.setForeground(new Color(255, 113, 19));
		lblAosDeExperiencia.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblAosDeExperiencia.setBounds(49, 16, 338, 53);
		panel_2.add(lblAosDeExperiencia);
		
		lblAosDeExperiencia_1 = new JLabel("A\u00F1os de experiencia como jefe:");
		lblAosDeExperiencia_1.setEnabled(false);
		lblAosDeExperiencia_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAosDeExperiencia_1.setForeground(new Color(255, 113, 19));
		lblAosDeExperiencia_1.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblAosDeExperiencia_1.setBounds(32, 75, 355, 43);
		panel_2.add(lblAosDeExperiencia_1);
		
		JLabel lblreaLaboral = new JLabel("\u00C1rea laboral:");
		lblreaLaboral.setHorizontalAlignment(SwingConstants.LEFT);
		lblreaLaboral.setForeground(new Color(255, 113, 19));
		lblreaLaboral.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblreaLaboral.setBounds(89, 134, 166, 43);
		panel_2.add(lblreaLaboral);
		
		areasCB = new JComboBox();
		for(int i=0; i<areas.size(); i++){
			areasCB.addItem(areas.get(i).getName_area());
		}
		areasCB.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		areasCB.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		areasCB.setBackground(Color.WHITE);
		areasCB.setBounds(257, 142, 302, 26);
		panel_2.add(areasCB);
		
		boss = new JSpinner();
		boss.setEnabled(false);
		boss.setModel(new SpinnerNumberModel(0, 0, 35, 1));
		boss.setFont(new Font("Corbel", Font.PLAIN, 21));
		boss.setBounds(398, 84, 64, 26);
		panel_2.add(boss);
		
		experience = new JSpinner();
		experience.setModel(new SpinnerNumberModel(0, 0, 40, 1));
		experience.setFont(new Font("Corbel", Font.PLAIN, 21));
		experience.setBounds(398, 30, 64, 26);
		panel_2.add(experience);
		
		button = new JButton("Aceptar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(name.getText().isEmpty() || card.getText().isEmpty() || nick.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Se deben completar todos los campos para continuar", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(card.getForeground().equals(Color.RED)){
					JOptionPane.showMessageDialog(null, "Carnet de identidad incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(name.getText().replace(" ", "").isEmpty()){
					JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
				}else if((int)experience.getValue() < (int)boss.getValue()){
					JOptionPane.showMessageDialog(null, "La cantidad de años como jefe no puede ser mayor a los años de experiencia", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					if(id == -1){
						try {
							User newUser = new User(name.getText(), sex.getSelectedIndex(), card.getText(), (int)experience.getValue(),
									(String)level.getSelectedItem(), (int)boss.getValue(), nick.getText(), pass.getText(), active.isSelected(), 
									areas.get(areasCB.getSelectedIndex()).getId_area(), rol.getSelectedIndex()+1);
							try {
								Object result = UserService.newUser(newUser, user_act.getUser_nick(), new Timestamp(Calendar.getInstance().getTime().getTime()));
								if(!result.toString().contains("e")){
									JOptionPane.showMessageDialog(null, "Acción completada satisfactoriamente", "Acción completada", JOptionPane.INFORMATION_MESSAGE);						
									newUser.setUser_id((int) result);
									users.add(newUser);
									UserManagementPanel.reload(users);
									UserManagementPanel.searchFile.setText("");
									UserView.this.dispose();
								}else{
									JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
								}
							} catch (SQLException e) {
								JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (UnsupportedEncodingException e) {
							JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						try {
							User newUser = new User(name.getText(), sex.getSelectedIndex(), card.getText(), (int)experience.getValue(),
									(String)level.getSelectedItem(), (int)boss.getValue(), nick.getText(), pass.getText(), active.isSelected(), 
									areas.get(areasCB.getSelectedIndex()).getId_area(), rol.getSelectedIndex()+1);
							try {
								String result = UserService.updateUser(newUser, user_act.getUser_nick(), new Timestamp(Calendar.getInstance().getTime().getTime()));
								if(result == null){
									JOptionPane.showMessageDialog(null, "Acción completada satisfactoriamente", "Acción completada", JOptionPane.INFORMATION_MESSAGE);						
									newUser.setUser_id(modificated.getUser_id());
									users.set(id, newUser);
									UserManagementPanel.reload(users);
									UserManagementPanel.searchFile.setText("");
									UserView.this.dispose();
								}else{
									JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
								}
							} catch (SQLException e) {
								JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (UnsupportedEncodingException e) {
							JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				button.setBackground(new Color(248, 159, 101));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				button.setBackground(new Color(255, 113, 19));
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI", Font.BOLD, 18));
		button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		button.setBackground(new Color(255, 113, 19));
		button.setBounds(233, 320, 153, 37);
		getContentPane().add(button);
		
		if(id == -1){
			setIconImage(Toolkit.getDefaultToolkit().getImage(UserView.class.getResource("/images/icons8_Add_User_Male_32.png")));
			setTitle("Nuevo usuario");
		}else{
			modificated = users.get(id);
			setIconImage(Toolkit.getDefaultToolkit().getImage(UserView.class.getResource("/images/icons8_Edit_32.png")));
			setTitle("Modificar usuario");
			fillView(areas);
		}
	}
	
	public void fillView(ArrayList<Area> areas){
		name.setText(modificated.getUser_name());
		sex.setSelectedIndex(modificated.getUser_sex());
		card.setText(modificated.getUser_card());
		experience.setValue(modificated.getUser_experience());
		level.setSelectedItem(modificated.getUser_level());
		boss.setValue(modificated.getUser_boss());
		nick.setText(modificated.getUser_nick());
		active.setSelected(modificated.isUser_active());
		rol.setSelectedIndex(modificated.getUser_rol()-1);
		boolean x = true;
		for(int i=0; i<areas.size() && x; i++){
			if(areas.get(i).getId_area() == modificated.getUser_area()){
				areasCB.setSelectedItem(areas.get(i).getName_area());
				x = false;
			}
		}
	}
}
