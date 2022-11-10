package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.ComponentOrientation;
import javax.swing.border.MatteBorder;

public class UserView extends JDialog {

	private static final long serialVersionUID = 8521115610247925177L;
	private JTextField name;
	private JTextField card;
	private JComboBox sex;
	private JComboBox level;
	private JButton button;

	public static void main(String[] args) {
		try {
			UserView dialog = new UserView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserView() {
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserView.class.getResource("/images/icons8_Add_User_Male_32.png")));
		setTitle("Nuevo usuario");
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
		lblNombreCompleto.setBounds(15, 16, 220, 43);
		panel.add(lblNombreCompleto);
		
		name = new JTextField();
		name.setFont(new Font("Corbel", Font.PLAIN, 20));
		name.setColumns(10);
		name.setBounds(237, 22, 370, 29);
		panel.add(name);
		
		JLabel lblCarnetDeIdentidad = new JLabel("Carnet de identidad:");
		lblCarnetDeIdentidad.setForeground(new Color(255, 113, 19));
		lblCarnetDeIdentidad.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblCarnetDeIdentidad.setBounds(15, 75, 237, 50);
		panel.add(lblCarnetDeIdentidad);
		
		card = new JTextField();
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Habilidades", new ImageIcon(UserView.class.getResource("/images/icons8_Worker_16.png")), panel_2, null);
		
		button = new JButton("Aceptar");
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI", Font.BOLD, 18));
		button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		button.setBackground(new Color(255, 113, 19));
		button.setBounds(233, 320, 153, 37);
		getContentPane().add(button);
	}
}
