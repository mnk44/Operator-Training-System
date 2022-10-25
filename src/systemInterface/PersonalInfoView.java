package systemInterface;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import systemClass.Area;
import systemClass.User;
import systemEnums.RolesTypes;
import systemServices.AreaService;
import systemServices.UserService;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class PersonalInfoView extends JDialog {

	private static final long serialVersionUID = -3937454384208615485L;
	private final JPanel contentPanel = new JPanel();
	
	User user = null;
	private JButton button;
	private JLabel nameUser;
	private JLabel nick;
	private JLabel label;
	private JLabel label_1;

	public static void main(String[] args) {
		try {
			PersonalInfoView dialog = new PersonalInfoView(4);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PersonalInfoView(int user_id) throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PersonalInfoView.class.getResource("/imgs/logo.png")));
		setTitle("Informaci\u00F3n personal");
		setResizable(false);
		setModal(true);
		user = (User) UserService.findId(user_id);
		setBounds(100, 100, 592, 597);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		button = new JButton("Aceptar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
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
		button.setBounds(211, 504, 153, 37);
		contentPanel.add(button);
		
		nameUser = new JLabel(user.getUser_name());
		nameUser.setHorizontalAlignment(SwingConstants.LEFT);
		nameUser.setForeground(new Color(47, 46, 65));
		nameUser.setFont(new Font("Segoe UI", Font.BOLD, 22));
		nameUser.setBounds(147, 46, 424, 37);
		contentPanel.add(nameUser);
		
		nick = new JLabel(user.getUser_nick());
		nick.setHorizontalAlignment(SwingConstants.CENTER);
		nick.setForeground(new Color(47, 46, 65));
		nick.setFont(new Font("Segoe UI", Font.ITALIC, 22));
		nick.setBounds(147, 80, 233, 37);
		contentPanel.add(nick);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(PersonalInfoView.class.getResource("/imgs/User.png")));
		label.setBounds(25, 27, 107, 117);
		contentPanel.add(label);
		
		label_1 = new JLabel(user.getUser_rol().toString().replace("_", " "));
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(new Color(47, 46, 65));
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		label_1.setBounds(267, 269, 286, 37);
		contentPanel.add(label_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(50, 145, 503, 2);
		contentPanel.add(separator);
		
		JLabel lblRolQueDesempea = new JLabel("Rol que desempe\u00F1a:");
		lblRolQueDesempea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRolQueDesempea.setForeground(new Color(47, 46, 65));
		lblRolQueDesempea.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblRolQueDesempea.setBounds(44, 263, 214, 48);
		contentPanel.add(lblRolQueDesempea);
		
		JLabel label_2 = new JLabel("Carnet de identidad:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(new Color(47, 46, 65));
		label_2.setFont(new Font("Segoe UI", Font.BOLD, 22));
		label_2.setBounds(25, 160, 233, 48);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("Nivel escolar:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setForeground(new Color(47, 46, 65));
		label_3.setFont(new Font("Segoe UI", Font.BOLD, 22));
		label_3.setBounds(35, 212, 223, 48);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel(user.getUser_card());
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setForeground(new Color(47, 46, 65));
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		label_4.setBounds(267, 166, 286, 37);
		contentPanel.add(label_4);
		
		JLabel label_5 = new JLabel(user.getUser_academic().toString().replace("_", " "));
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setForeground(new Color(47, 46, 65));
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		label_5.setBounds(267, 218, 286, 37);
		contentPanel.add(label_5);
		
		JLabel lblAosDeExperiencia = new JLabel("A\u00F1os de experiencia:");
		lblAosDeExperiencia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAosDeExperiencia.setForeground(new Color(47, 46, 65));
		lblAosDeExperiencia.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblAosDeExperiencia.setBounds(25, 322, 233, 48);
		contentPanel.add(lblAosDeExperiencia);
		
		JLabel label_6 = new JLabel(Integer.toString(user.getUser_experience()));
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setForeground(new Color(47, 46, 65));
		label_6.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		label_6.setBounds(267, 328, 286, 37);
		contentPanel.add(label_6);
		
		JLabel lblrea = new JLabel("\u00C1rea:");
		lblrea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblrea.setForeground(new Color(47, 46, 65));
		lblrea.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblrea.setBounds(31, 375, 227, 48);
		contentPanel.add(lblrea);
		
		JLabel label_7 = new JLabel(((Area) AreaService.findId(user.getUser_area())).getArea_name());
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setForeground(new Color(47, 46, 65));
		label_7.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		label_7.setBounds(267, 381, 286, 37);
		contentPanel.add(label_7);
		
		if(user.getUser_rol().equals(RolesTypes.Jefe_de_área)){
			JLabel lblAosComoJefe = new JLabel("A\u00F1os como jefe:");
			lblAosComoJefe.setHorizontalAlignment(SwingConstants.RIGHT);
			lblAosComoJefe.setForeground(new Color(47, 46, 65));
			lblAosComoJefe.setFont(new Font("Segoe UI", Font.BOLD, 22));
			lblAosComoJefe.setBounds(25, 423, 233, 48);
			contentPanel.add(lblAosComoJefe);
			
			JLabel label_8 = new JLabel(Integer.toString(user.getUser_position_years()));
			label_8.setHorizontalAlignment(SwingConstants.LEFT);
			label_8.setForeground(new Color(47, 46, 65));
			label_8.setFont(new Font("Segoe UI", Font.PLAIN, 22));
			label_8.setBounds(267, 429, 286, 37);
			contentPanel.add(label_8);
		}
	}
}
