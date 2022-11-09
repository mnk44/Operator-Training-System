package interfaces;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;

import services.AreaService;
import classes.Area;
import classes.User;

public class PersonalInfPanel extends JPanel {

	private static final long serialVersionUID = -7100469393657619289L;

	public PersonalInfPanel(User user) throws SQLException {
		setBorder(null);
		setBackground(Color.WHITE);
		setBounds(100, 100, 746, 418);
		setLayout(null);
		
		JLabel phto = new JLabel();
		if(user.getUser_sex() == 0){
			phto.setIcon(new ImageIcon(PersonalInfPanel.class.getResource("/images/male.png")));
		}else{
			phto.setIcon(new ImageIcon(PersonalInfPanel.class.getResource("/images/female.png")));
		}
		phto.setBounds(15, 16, 100, 100);
		add(phto);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 113, 19));
		lblNombre.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblNombre.setBounds(130, 29, 100, 37);
		add(lblNombre);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(255, 113, 19));
		lblUsuario.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblUsuario.setBounds(130, 67, 100, 37);
		add(lblUsuario);
		
		JLabel lblCaridadDeLas = new JLabel(user.getUser_name());
		lblCaridadDeLas.setHorizontalAlignment(SwingConstants.LEFT);
		lblCaridadDeLas.setForeground(new Color(99, 68, 55));
		lblCaridadDeLas.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblCaridadDeLas.setBounds(228, 29, 503, 37);
		add(lblCaridadDeLas);
		
		JLabel lblMmontoto = new JLabel(user.getUser_nick());
		lblMmontoto.setHorizontalAlignment(SwingConstants.LEFT);
		lblMmontoto.setForeground(new Color(99, 68, 55));
		lblMmontoto.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 19));
		lblMmontoto.setBounds(238, 67, 493, 37);
		add(lblMmontoto);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(111, 120, 588, 2);
		add(separator);
		
		JLabel lblCarnetDeIdentidad = new JLabel("Carnet de identidad:");
		lblCarnetDeIdentidad.setForeground(new Color(255, 113, 19));
		lblCarnetDeIdentidad.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblCarnetDeIdentidad.setBounds(25, 136, 236, 37);
		add(lblCarnetDeIdentidad);
		
		JLabel lblNivelEscolar = new JLabel("Nivel escolar:");
		lblNivelEscolar.setForeground(new Color(255, 113, 19));
		lblNivelEscolar.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblNivelEscolar.setBounds(97, 179, 164, 37);
		add(lblNivelEscolar);
		
		JLabel lblRol = new JLabel("Rol del usuario:");
		lblRol.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRol.setForeground(new Color(255, 113, 19));
		lblRol.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblRol.setBounds(25, 221, 236, 37);
		add(lblRol);
		
		JLabel lblAosDeExperiencia = new JLabel("A\u00F1os de experiencia:");
		lblAosDeExperiencia.setForeground(new Color(255, 113, 19));
		lblAosDeExperiencia.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblAosDeExperiencia.setBounds(25, 264, 236, 37);
		add(lblAosDeExperiencia);
		
		JLabel lblAosComoJefe = new JLabel("A\u00F1os como jefe:");
		lblAosComoJefe.setForeground(new Color(255, 113, 19));
		lblAosComoJefe.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblAosComoJefe.setBounds(81, 309, 180, 37);
		add(lblAosComoJefe);
		
		JLabel lblreaLaboral = new JLabel("\u00C1rea laboral:");
		lblreaLaboral.setForeground(new Color(255, 113, 19));
		lblreaLaboral.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblreaLaboral.setBounds(101, 362, 160, 37);
		add(lblreaLaboral);
		
		JLabel label = new JLabel(user.getUser_card());
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(new Color(99, 68, 55));
		label.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		label.setBounds(276, 138, 186, 37);
		add(label);
		
		JLabel lblUniversitario = new JLabel(user.getUser_level());
		lblUniversitario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUniversitario.setForeground(new Color(99, 68, 55));
		lblUniversitario.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblUniversitario.setBounds(273, 179, 251, 37);
		add(lblUniversitario);
		
		JLabel lblAdministrador = new JLabel();
		if(user.getUser_rol() == 1){
			lblAdministrador.setText("Administrador");
		}else if(user.getUser_rol() == 2){
			lblAdministrador.setText("Jefe de área");
		}else{
			lblAdministrador.setText("Operario");
		}
		lblAdministrador.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdministrador.setForeground(new Color(99, 68, 55));
		lblAdministrador.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		lblAdministrador.setBounds(273, 221, 189, 37);
		add(lblAdministrador);
		
		JLabel label_3 = new JLabel(Integer.toString(user.getUser_experience()));
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setForeground(new Color(99, 68, 55));
		label_3.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		label_3.setBounds(273, 264, 64, 37);
		add(label_3);
		
		JLabel lblnoEsJefe = new JLabel();
		if(user.getUser_rol() == 2){
			lblnoEsJefe.setText(Integer.toString(user.getUser_boss()));
		}else{
			lblnoEsJefe.setText("(no es jefe)");
			lblnoEsJefe.setFont(new Font("Copperplate Gothic Light", Font.ITALIC, 18));
		}
		lblnoEsJefe.setHorizontalAlignment(SwingConstants.LEFT);
		lblnoEsJefe.setForeground(new Color(99, 68, 55));
		lblnoEsJefe.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblnoEsJefe.setBounds(269, 310, 164, 37);
		add(lblnoEsJefe);
		
		JLabel lblProcesosDeLa = new JLabel(((Area) AreaService.searchId(user.getUser_area())).getName_area());
		lblProcesosDeLa.setHorizontalAlignment(SwingConstants.LEFT);
		lblProcesosDeLa.setForeground(new Color(99, 68, 55));
		lblProcesosDeLa.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		lblProcesosDeLa.setBounds(269, 362, 462, 37);
		add(lblProcesosDeLa);
	}
}
