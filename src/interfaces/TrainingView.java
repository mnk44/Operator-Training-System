package interfaces;

import java.awt.Color;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class TrainingView extends JPanel{

	private static final long serialVersionUID = 2160031059373289103L;
	private JLabel process_name;
	private JLabel lblVariables;
	private JLabel lblEtapacausas;
	private JLabel lblEtaparecomendaciones;
	private JLabel lblIntentos;
	private JLabel lblPuntosObtenidos;
	private JLabel lblTotal;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblNewLabel;
	private JButton btnComenzarPrueba;
	private JPanel panel;

	public TrainingView(String process) {
		setBorder(null);
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(100, 100, 779, 359);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 779, 343);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreDelProceso = new JLabel("Nombre del proceso:");
		lblNombreDelProceso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelProceso.setForeground(new Color(255, 113, 19));
		lblNombreDelProceso.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 22));
		lblNombreDelProceso.setBounds(15, 16, 319, 49);
		panel.add(lblNombreDelProceso);
		
		process_name = new JLabel(process);
		process_name.setHorizontalAlignment(SwingConstants.LEFT);
		process_name.setForeground(new Color(99, 68, 55));
		process_name.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 22));
		process_name.setBounds(349, 16, 417, 49);
		panel.add(process_name);
		
		lblVariables = new JLabel("Etapa #1:");
		lblVariables.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVariables.setForeground(new Color(99, 68, 55));
		lblVariables.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
		lblVariables.setBounds(289, 122, 127, 49);
		panel.add(lblVariables);
		
		lblEtapacausas = new JLabel("Etapa #2:");
		lblEtapacausas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEtapacausas.setForeground(new Color(99, 68, 55));
		lblEtapacausas.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
		lblEtapacausas.setBounds(289, 166, 127, 49);
		panel.add(lblEtapacausas);
		
		lblEtaparecomendaciones = new JLabel("Etapa #3:");
		lblEtaparecomendaciones.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEtaparecomendaciones.setForeground(new Color(99, 68, 55));
		lblEtaparecomendaciones.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
		lblEtaparecomendaciones.setBounds(289, 213, 127, 49);
		panel.add(lblEtaparecomendaciones);
		
		lblIntentos = new JLabel("Intentos");
		lblIntentos.setBorder(new LineBorder(new Color(255, 113, 19)));
		lblIntentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntentos.setForeground(new Color(99, 68, 55));
		lblIntentos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		lblIntentos.setBounds(428, 81, 105, 34);
		panel.add(lblIntentos);
		
		lblPuntosObtenidos = new JLabel("Promedio");
		lblPuntosObtenidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntosObtenidos.setBorder(new LineBorder(new Color(255, 113, 19)));
		lblPuntosObtenidos.setForeground(new Color(99, 68, 55));
		lblPuntosObtenidos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		lblPuntosObtenidos.setBounds(644, 81, 105, 34);
		panel.add(lblPuntosObtenidos);
		
		lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBorder(new LineBorder(new Color(255, 113, 19)));
		lblTotal.setForeground(new Color(99, 68, 55));
		lblTotal.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		lblTotal.setBounds(536, 81, 105, 34);
		panel.add(lblTotal);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(TrainingView.class.getResource("/images/right-orange.png")));
		label.setBounds(255, 136, 32, 20);
		panel.add(label);
		
		label_1 = new JLabel("");
		label_1.setVisible(false);
		label_1.setIcon(new ImageIcon(TrainingView.class.getResource("/images/right-orange.png")));
		label_1.setBounds(255, 179, 32, 20);
		panel.add(label_1);
		
		label_2 = new JLabel("");
		label_2.setVisible(false);
		label_2.setIcon(new ImageIcon(TrainingView.class.getResource("/images/right-orange.png")));
		label_2.setBounds(255, 228, 32, 20);
		panel.add(label_2);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TrainingView.class.getResource("/images/entrenamiento.png")));
		lblNewLabel.setBounds(25, 69, 215, 220);
		panel.add(lblNewLabel);
		
		btnComenzarPrueba = new JButton("Iniciar evaluaci\u00F3n");
		btnComenzarPrueba.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnComenzarPrueba.setBackground(new Color(248, 159, 101));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnComenzarPrueba.setBackground(new Color(255, 113, 19));
			}
		});
		btnComenzarPrueba.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Play_Property_32.png")));
		btnComenzarPrueba.setHorizontalTextPosition(SwingConstants.LEFT);
		btnComenzarPrueba.setForeground(Color.WHITE);
		btnComenzarPrueba.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnComenzarPrueba.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		btnComenzarPrueba.setBackground(new Color(255, 113, 19));
		btnComenzarPrueba.setBounds(484, 272, 236, 37);
		panel.add(btnComenzarPrueba);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
}
