package visual;

import java.awt.BorderLayout;

import javafx.scene.control.Alert.AlertType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import contentClass.Area;
import services.AreaService;
import utils.Rol;
import utils.SchoolLevel;

import java.awt.ComponentOrientation;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NewUser extends JDialog {

	private static final long serialVersionUID = 4793501210293845117L;
	private final JPanel contentPanel = new JPanel();
	private JTextField name_user;
	private JTextField nick_name;
	private JTextField identity_card;
	@SuppressWarnings("rawtypes")
	private JComboBox school_level;
	@SuppressWarnings("rawtypes")
	private JComboBox rol;
	private JButton acept;
	private JButton cancel;
	private JLabel importantLabel;
	private JSpinner position_years;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewUser dialog = new NewUser(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NewUser(int option) throws SQLException {
		setAlwaysOnTop(true);
		if(option == 0){
			setTitle("Nuevo Usuario");
		}else{
			setTitle("Modificar Usuario");
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewUser.class.getResource("/img/Captura de pantalla (133).png")));
		setResizable(false);
		setBounds(100, 100, 691, 526);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		name_user = new JTextField();
		name_user.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean capital = key >= 65 && key <= 90;
				boolean lower = key >= 97 && key <= 122;
				boolean space = key == 32;

				if (!(capital || lower || space))
				{
					e.consume();
					if(key != KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();
					}
				}
			}
		});
		name_user.setBounds(195, 30, 365, 26);
		name_user.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPanel.add(name_user);
		name_user.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre(s) y Apellidos:");
		lblNewLabel.setBounds(15, 33, 205, 20);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		contentPanel.add(lblNewLabel);

		JRadioButton active = new JRadioButton("Activo");
		active.setBounds(587, 28, 87, 29);
		active.setSelected(true);
		active.setFont(new Font("Arial", Font.BOLD, 17));
		active.setBackground(Color.WHITE);
		contentPanel.add(active);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setBounds(35, 108, 155, 20);
		lblNombreDeUsuario.setFont(new Font("Arial", Font.BOLD, 16));
		contentPanel.add(lblNombreDeUsuario);

		JLabel lblCarnetDeIdentidad = new JLabel("Carnet de Identidad:");
		lblCarnetDeIdentidad.setBounds(35, 150, 155, 20);
		lblCarnetDeIdentidad.setFont(new Font("Arial", Font.BOLD, 16));
		contentPanel.add(lblCarnetDeIdentidad);

		nick_name = new JTextField();
		nick_name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();

				if (Character.isWhitespace(key))
				{
					e.consume();
					getToolkit().beep();
				}
			}
		});
		nick_name.setBounds(195, 106, 182, 26);
		nick_name.setFont(new Font("Arial", Font.PLAIN, 16));
		nick_name.setColumns(10);
		contentPanel.add(nick_name);

		identity_card = new JTextField();
		identity_card.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numbers = key >= 48 && key <= 57;
				if (!numbers)
				{
					e.consume();
					getToolkit().beep();
				}
				if (identity_card.getText().trim().length() < 11) {
					identity_card.setForeground(Color.RED);
				}else if(identity_card.getText().trim().length() == 11){
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(identity_card.getText().trim().length() == 11){
					identity_card.setForeground(Color.BLACK);
				}
			}
		});
		identity_card.setBounds(195, 148, 182, 26);
		identity_card.setFont(new Font("Arial", Font.PLAIN, 16));
		identity_card.setColumns(10);
		contentPanel.add(identity_card);

		JLabel lblNivelEscolar = new JLabel("Nivel Escolar:");
		lblNivelEscolar.setBounds(84, 193, 106, 20);
		lblNivelEscolar.setFont(new Font("Arial", Font.BOLD, 16));
		contentPanel.add(lblNivelEscolar);

		school_level = new JComboBox(SchoolLevel.values());
		school_level.setBounds(195, 190, 182, 26);
		school_level.setSelectedIndex(3);
		school_level.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		school_level.setFont(new Font("Arial", Font.BOLD, 16));
		school_level.setBackground(Color.WHITE);
		contentPanel.add(school_level);

		JLabel lblreaLaboral = new JLabel("\u00C1rea Laboral:");
		lblreaLaboral.setBounds(84, 277, 106, 20);
		lblreaLaboral.setFont(new Font("Arial", Font.BOLD, 16));
		contentPanel.add(lblreaLaboral);

		JComboBox area = new JComboBox();
		area.setBounds(195, 274, 205, 26);
		ArrayList<Area> areas = AreaService.getAreas();
		for(int i=0; i < areas.size(); i++){
			area.addItem(areas.get(i).getName_area());
		}
		area.setFont(new Font("Arial", Font.BOLD, 16));
		area.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		area.setBackground(Color.WHITE);
		contentPanel.add(area);

		JLabel lblRolDelUsuario = new JLabel("Rol del Usuario:");
		lblRolDelUsuario.setBounds(67, 235, 123, 20);
		lblRolDelUsuario.setFont(new Font("Arial", Font.BOLD, 16));
		contentPanel.add(lblRolDelUsuario);

		rol = new JComboBox(Rol.values());
		rol.setBounds(195, 232, 182, 26);
		rol.setSelectedIndex(2);
		rol.setFont(new Font("Arial", Font.BOLD, 16));
		rol.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		rol.setBackground(Color.WHITE);
		contentPanel.add(rol);

		JLabel lblExperienciaEnEl = new JLabel("A\u00F1os de Experiencia Laboral:");
		lblExperienciaEnEl.setBounds(26, 358, 230, 20);
		lblExperienciaEnEl.setFont(new Font("Arial", Font.BOLD, 16));
		contentPanel.add(lblExperienciaEnEl);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBounds(0, 326, 685, 9);
		contentPanel.add(separator_1);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(NewUser.class.getResource("/img/undraw_add_user_re_5oib.png")));
		lblNewLabel_1.setBounds(404, 100, 270, 210);
		contentPanel.add(lblNewLabel_1);

		acept = new JButton("Aceptar");
		acept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		acept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				acept.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				acept.setBackground(new Color(255, 255, 201));
			}
		});
		acept.setIcon(new ImageIcon(NewUser.class.getResource("/img/icons8_Checkmark_16.png")));
		acept.setFont(new Font("Segoe UI", Font.BOLD, 17));
		acept.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		acept.setBackground(new Color(255, 255, 201));
		acept.setBounds(33, 423, 155, 38);
		contentPanel.add(acept);

		cancel = new JButton("Cancelar");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				cancel.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				cancel.setBackground(new Color(255, 255, 201));
			}
		});
		cancel.setIcon(new ImageIcon(NewUser.class.getResource("/img/icons8_Delete_16.png")));
		cancel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		cancel.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		cancel.setBackground(new Color(255, 255, 201));
		cancel.setBounds(492, 423, 155, 38);
		contentPanel.add(cancel);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(0, 79, 685, 9);
		contentPanel.add(separator);

		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Arial", Font.PLAIN, 16));
		spinner.setBounds(255, 355, 59, 26);
		contentPanel.add(spinner);

		importantLabel = new JLabel("A\u00F1os como Jefe:");
		importantLabel.setEnabled(false);
		importantLabel.setFont(new Font("Arial", Font.BOLD, 16));
		importantLabel.setBounds(412, 358, 129, 20);
		contentPanel.add(importantLabel);

		position_years = new JSpinner();
		position_years.setEnabled(false);
		position_years.setFont(new Font("Arial", Font.PLAIN, 16));
		position_years.setBounds(545, 356, 59, 26);
		contentPanel.add(position_years);

		rol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rol.getSelectedIndex() != 1){
					importantLabel.setEnabled(false);
					position_years.setEnabled(false);
				}else{
					importantLabel.setEnabled(true);
					position_years.setEnabled(true);
				}
			}
		});
	}
}
