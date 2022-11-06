package systemInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;

import systemClass.Area;
import systemClass.User;
import systemEnums.AccionTrace;
import systemEnums.RolesTypes;
import systemEnums.SchoolarLevel;
import systemEnums.SystemClass;
import systemLogic.Encrypting;
import systemServices.AreaService;
import systemServices.UserService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;

public class NewUserView extends JDialog {

	private static final long serialVersionUID = -8141116935542447229L;
	private final JPanel contentPanel = new JPanel();
	private JPanel personalInfo;
	private JPanel userInfo;
	private JButton button;
	private JLabel lblNombreCompletoDel;
	private JTextField nameUser;
	private JLabel lblCarnetDeIdentidad;
	private JTextField card;
	private JLabel lblNivelEscolar;
	private JComboBox<?> school;
	private JLabel lblAosDeExperiencia;
	private JLabel lblAosCumplidosComo;
	private JSpinner jefe;
	private JSpinner experience;
	private JLabel lblNombreDeUsuario;
	private JTextField nick;
	private JLabel lblRolQueDesempea;
	private JComboBox<Object> rol;
	private JLabel lblContrasea;
	private JLabel label;
	private JLabel lblreaDelUsuario;
	private JComboBox<Object> areaSelect;

	String password = null;
	private JRadioButton actve;

	User uss = null;
	boolean rest = false;
	int userId = -1;

	public static void main(String[] args) {
		try {
			NewUserView dialog = new NewUserView(-1, 1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public NewUserView(final int opt, int user) throws SQLException {
		userId = user;
		setModal(true);
		setBackground(Color.WHITE);
		setBounds(100, 100, 884, 591);
		if(opt == -1){
			setTitle("Nuevo usuario");
		}else{
			setTitle("Modificar usuario");
		}
		setResizable(false);
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AreaListView.class.getResource("/imgs/logo.png")));
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 882, 481);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tabbedPane.setBorder(null);
		contentPanel.add(tabbedPane);

		userInfo = new JPanel();
		userInfo.setBackground(Color.WHITE);
		userInfo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		userInfo.setEnabled(false);
		userInfo.setBorder(null);
		tabbedPane.addTab("Información de usuario", new ImageIcon(NewProcessView.class.getResource("/imgs/icons8_Male_User_16.png")), userInfo, null);
		userInfo.setLayout(null);

		lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDeUsuario.setForeground(new Color(47, 46, 65));
		lblNombreDeUsuario.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNombreDeUsuario.setBounds(15, 33, 333, 48);
		userInfo.add(lblNombreDeUsuario);

		nick = new JTextField();
		nick.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(nick.getText().equals("Ejemplo: alopez")){
					nick.setCaretPosition(0);
				}
			}
		});
		nick.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(nick.getText().equals("Ejemplo: alopez")){
					if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
						arg0.consume();
						getToolkit().beep();
					}else{
						nick.setText("");
						nick.setForeground(Color.BLACK);
					}
				}else if(nick.getText().length() == 1 && arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
					nick.setText("Ejemplo: alopez");
					nick.setForeground(Color.LIGHT_GRAY);
					arg0.consume();
					nick.setCaretPosition(0);
				}
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(Character.isWhitespace(arg0.getKeyChar())){
					arg0.consume();
					getToolkit().beep();
					if(nick.getText().isEmpty()){
						nick.setText("Ejemplo: alopez");
						nick.setForeground(Color.LIGHT_GRAY);
						nick.setCaretPosition(0);
					}
				}
			}
		});
		nick.setText("Ejemplo: alopez");
		nick.setForeground(Color.LIGHT_GRAY);
		nick.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		nick.setColumns(10);
		nick.setBounds(353, 43, 341, 29);
		userInfo.add(nick);

		lblRolQueDesempea = new JLabel("Rol que desempe\u00F1a el usuario:");
		lblRolQueDesempea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRolQueDesempea.setForeground(new Color(47, 46, 65));
		lblRolQueDesempea.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblRolQueDesempea.setBounds(15, 161, 333, 48);
		userInfo.add(lblRolQueDesempea);

		lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContrasea.setForeground(new Color(47, 46, 65));
		lblContrasea.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblContrasea.setBounds(15, 97, 333, 48);
		userInfo.add(lblContrasea);

		label = new JLabel();
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(opt == -1){
					if(!nameUser.getText().isEmpty() && card.getForeground() != Color.RED){
						password = nameUser.getText().substring(0, 1).toLowerCase() + card.getText() + "*";
						JOptionPane.showMessageDialog(null, "La contraseña del usuario es: " + password, "Contraseña", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Debe rellenar los datos personales para obtener la contraseña", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					password = nameUser.getText().substring(0, 1).toLowerCase() + card.getText();
					try {
						if(!Encrypting.getEncript(password).equalsIgnoreCase(uss.getUser_password())){
							int change = JOptionPane.showConfirmDialog(null, "La contraseña actual es diferente a la original ¿Desea restablecerla?", "Restablecer contraseña", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
							if(change == 0){
								if(!nameUser.getText().isEmpty() && card.getForeground() != Color.RED){
									JOptionPane.showMessageDialog(null, "La contraseña del usuario es: " + password, "Contraseña", JOptionPane.INFORMATION_MESSAGE);
									rest = true;
								}else{
									JOptionPane.showMessageDialog(null, "Debe rellenar los datos personales para obtener la contraseña", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}
						}else{
							JOptionPane.showMessageDialog(null, "La contraseña del usuario es: " + password, "Contraseña", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		label.setToolTipText("");
		label.setIcon(new ImageIcon(NewUserView.class.getResource("/imgs/icons8_Info_32.png")));
		label.setBounds(353, 97, 42, 48);
		userInfo.add(label);

		lblreaDelUsuario = new JLabel("\u00C1rea del usuario:");
		lblreaDelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblreaDelUsuario.setForeground(new Color(47, 46, 65));
		lblreaDelUsuario.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblreaDelUsuario.setBounds(15, 225, 333, 48);
		userInfo.add(lblreaDelUsuario);

		ArrayList<Area> areas = (ArrayList<Area>) AreaService.getAreas();
		areaSelect = new JComboBox<Object>();
		for(int i=0; i<areas.size(); i++){
			areaSelect.addItem(areas.get(i).getArea_name());
		}
		areaSelect.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		areaSelect.setBounds(353, 235, 360, 29);
		userInfo.add(areaSelect);

		actve = new JRadioButton("Usuario activo:   ");
		actve.setHorizontalTextPosition(SwingConstants.LEFT);
		actve.setHorizontalAlignment(SwingConstants.CENTER);
		actve.setFont(new Font("Segoe UI", Font.BOLD, 22));
		actve.setBackground(Color.WHITE);
		actve.setSelected(true);
		actve.setBounds(124, 303, 341, 29);
		userInfo.add(actve);

		personalInfo = new JPanel();
		personalInfo.setBackground(Color.WHITE);
		personalInfo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		personalInfo.setEnabled(false);
		personalInfo.setBorder(null);
		tabbedPane.addTab("Información personal", new ImageIcon(NewProcessView.class.getResource("/imgs/icons8_User_Menu_Male_16.png")), personalInfo, null);
		tabbedPane.setBackgroundAt(1, Color.WHITE);
		personalInfo.setLayout(null);

		lblNombreCompletoDel = new JLabel("Nombre completo del usuario:");
		lblNombreCompletoDel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreCompletoDel.setForeground(new Color(47, 46, 65));
		lblNombreCompletoDel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNombreCompletoDel.setBounds(28, 35, 333, 40);
		personalInfo.add(lblNombreCompletoDel);

		nameUser = new JTextField();
		nameUser.addKeyListener(new KeyAdapter() {
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
				}else if(Character.isWhitespace(key) && nameUser.getText().replace(" ", "").isEmpty()){
					e.consume();
					getToolkit().beep();
				}
			}
		});
		nameUser.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		nameUser.setColumns(10);
		nameUser.setBounds(365, 41, 466, 29);
		personalInfo.add(nameUser);

		lblCarnetDeIdentidad = new JLabel("Carnet de identidad:");
		lblCarnetDeIdentidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCarnetDeIdentidad.setForeground(new Color(47, 46, 65));
		lblCarnetDeIdentidad.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblCarnetDeIdentidad.setBounds(28, 99, 333, 48);
		personalInfo.add(lblCarnetDeIdentidad);

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
		card.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		card.setColumns(10);
		card.setBounds(365, 109, 212, 29);
		personalInfo.add(card);

		lblNivelEscolar = new JLabel("Nivel escolar:");
		lblNivelEscolar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNivelEscolar.setForeground(new Color(47, 46, 65));
		lblNivelEscolar.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNivelEscolar.setBounds(28, 172, 333, 48);
		personalInfo.add(lblNivelEscolar);

		school = new JComboBox<Object>(SchoolarLevel.values());
		school.setBounds(365, 182, 263, 29);
		school.setSelectedIndex(5);
		school.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		personalInfo.add(school);

		lblAosDeExperiencia = new JLabel("A\u00F1os de experiencia laboral:");
		lblAosDeExperiencia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAosDeExperiencia.setForeground(new Color(47, 46, 65));
		lblAosDeExperiencia.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblAosDeExperiencia.setBounds(28, 249, 333, 48);
		personalInfo.add(lblAosDeExperiencia);

		experience = new JSpinner();
		experience.setModel(new SpinnerNumberModel(0, 0, 35, 1));
		experience.setBounds(365, 259, 64, 29);
		experience.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		personalInfo.add(experience);

		lblAosCumplidosComo = new JLabel("A\u00F1os de experiencia como jefe:");
		lblAosCumplidosComo.setEnabled(false);
		lblAosCumplidosComo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAosCumplidosComo.setForeground(new Color(47, 46, 65));
		lblAosCumplidosComo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblAosCumplidosComo.setBounds(28, 326, 333, 48);
		personalInfo.add(lblAosCumplidosComo);

		jefe = new JSpinner();
		jefe.setEnabled(false);
		jefe.setModel(new SpinnerNumberModel(0, 0, 35, 1));
		jefe.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		jefe.setBounds(365, 336, 64, 29);
		personalInfo.add(jefe);
		tabbedPane.setBackgroundAt(1, Color.WHITE);

		button = new JButton("Aceptar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nameUser.getText().isEmpty() || card.getForeground() == Color.RED || nick.getText().equals("Ejemplo: alopez")){
					JOptionPane.showMessageDialog(null, "Debes completar todos los campos para continuar", "Error", JOptionPane.ERROR_MESSAGE);
				}else if((int)experience.getValue() < (int)jefe.getValue()){
					JOptionPane.showMessageDialog(null, "La cantidad de años de experiencia laboral no pueden ser menor que la cantidad de años como jefe", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(opt == -1){
					String result = "No funciona";
					password = nameUser.getText().substring(0, 1).toLowerCase() + card.getText();
					try {
						result = UserService.newUser(nameUser.getText(), card.getText(), SchoolarLevel.valueOf(school.getSelectedItem().toString()), 
								(int)experience.getValue(), (int)jefe.getValue(), nick.getText(), Encrypting.getEncript(password), actve.isSelected(),
								((Area) AreaService.findName(areaSelect.getSelectedItem().toString())).getArea_id(), RolesTypes.valueOf(rol.getSelectedItem().toString()),
								userId, AccionTrace.creó_el, SystemClass.usuario, nick.getText(), new Timestamp(Calendar.getInstance().getTime().getTime()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(result == null){
						JOptionPane.showMessageDialog(null, "Acción completada satisfactoriamente", "Acción completada", JOptionPane.INFORMATION_MESSAGE);
						try {
							UserListView.reloadTable((ArrayList<User>) UserService.getUsers());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dispose();
					}else{
						JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					User ret = null;
					if(rest){
						try {
							ret = new User(uss.getUser_id(), nameUser.getText(), card.getText(), SchoolarLevel.valueOf(school.getSelectedItem().toString()), 
									(int)experience.getValue(), (int)jefe.getValue(), nick.getText(), Encrypting.getEncript(password), actve.isSelected(),
									((Area) AreaService.findName(areaSelect.getSelectedItem().toString())).getArea_id(), RolesTypes.valueOf(rol.getSelectedItem().toString()));
						} catch (SQLException | UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						try {
							ret = new User(uss.getUser_id(), nameUser.getText(), card.getText(), SchoolarLevel.valueOf(school.getSelectedItem().toString()), 
									(int)experience.getValue(), (int)jefe.getValue(), nick.getText(), uss.getUser_password(), actve.isSelected(),
									((Area) AreaService.findName(areaSelect.getSelectedItem().toString())).getArea_id(), RolesTypes.valueOf(rol.getSelectedItem().toString()));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					String result = "No funciona";
					try {
						result = UserService.updateUser(ret, userId, AccionTrace.modificó_el, SystemClass.usuario, nick.getText(), new Timestamp(Calendar.getInstance().getTime().getTime()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(result == null){
						JOptionPane.showMessageDialog(null, "Acción completada satisfactoriamente", "Acción completada", JOptionPane.INFORMATION_MESSAGE);
						try {
							UserListView.reloadTable((ArrayList<User>) UserService.getUsers());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dispose();
					}else{
						JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				button.setBackground(new Color(239, 196, 159));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				button.setBackground(new Color(244, 164, 96));
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI", Font.BOLD, 18));
		button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		button.setBackground(new Color(244, 164, 96));
		button.setBounds(353, 498, 153, 37);
		contentPanel.add(button);

		rol = new JComboBox<Object>(RolesTypes.values());
		rol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rol.getSelectedIndex() != 1){
					lblAosCumplidosComo.setEnabled(false);
					jefe.setEnabled(false);
					jefe.setValue(0);
				}else{
					lblAosCumplidosComo.setEnabled(true);
					jefe.setEnabled(true);
				}
			}
		});
		rol.setSelectedIndex(2);
		rol.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		rol.setBounds(353, 171, 274, 29);
		userInfo.add(rol);

		if(opt != -1){
			uss = (User) UserService.findId(opt);
			nameUser.setText(uss.getUser_name());
			card.setText(uss.getUser_card());
			school.setSelectedItem(uss.getUser_academic()); 
			experience.setValue(uss.getUser_experience());
			jefe.setValue(uss.getUser_position_years());
			nick.setText(uss.getUser_nick());
			nick.setForeground(Color.BLACK);
			actve.setSelected(uss.isUser_active());
			areaSelect.setSelectedItem(((Area) AreaService.findId(uss.getUser_area())).getArea_name());
			rol.setSelectedItem(uss.getUser_rol());
		}
	}
}
