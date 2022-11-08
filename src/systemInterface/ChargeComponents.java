package systemInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import systemClass.Area;
import systemClass.User;
import systemEnums.RolesTypes;
import systemServices.AreaService;

public class ChargeComponents {
	
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
}
