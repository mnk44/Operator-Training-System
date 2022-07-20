package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import contentClass.Process;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import services.ProcessService;

import java.awt.Font;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrainerView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Process process = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TrainerView dialog = new TrainerView(9);
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
	public TrainerView(final int p) throws SQLException {
		this.process = ProcessService.findById(p);
		setTitle("Entrenamiento");
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewUser.class.getResource("/img/Captura de pantalla (133).png")));
		setResizable(false);
		setBounds(100, 100, 691, 526);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblProcesoDeEntrenamiento = new JLabel("Proceso de Entrenamiento: " + process.getName_process());
		lblProcesoDeEntrenamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblProcesoDeEntrenamiento.setFont(new Font("Arial", Font.BOLD, 18));
		lblProcesoDeEntrenamiento.setBounds(15, 16, 655, 20);
		contentPanel.add(lblProcesoDeEntrenamiento);
		
		JButton btnEntrenamientoVariables = new JButton("Entrenamiento de Variables");
		btnEntrenamientoVariables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Train t = null;
				try {
					t = new Train(p);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				t.setVisible(true);
				TrainerView.this.setVisible(false);
			}
		});
		btnEntrenamientoVariables.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnEntrenamientoVariables.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		btnEntrenamientoVariables.setBackground(new Color(255, 255, 201));
		btnEntrenamientoVariables.setBounds(60, 71, 579, 80);
		contentPanel.add(btnEntrenamientoVariables);
		
		JButton btnEntrenamientoDeCausas = new JButton("Entrenamiento de Causas");
		btnEntrenamientoDeCausas.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnEntrenamientoDeCausas.setEnabled(false);
		btnEntrenamientoDeCausas.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		btnEntrenamientoDeCausas.setBackground(new Color(255, 255, 201));
		btnEntrenamientoDeCausas.setBounds(60, 219, 579, 80);
		contentPanel.add(btnEntrenamientoDeCausas);
		
		JButton btnEntrenamientoDeRecomendaciones = new JButton("Entrenamiento de Recomendaciones");
		btnEntrenamientoDeRecomendaciones.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnEntrenamientoDeRecomendaciones.setEnabled(false);
		btnEntrenamientoDeRecomendaciones.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		btnEntrenamientoDeRecomendaciones.setBackground(new Color(255, 255, 201));
		btnEntrenamientoDeRecomendaciones.setBounds(60, 364, 579, 80);
		contentPanel.add(btnEntrenamientoDeRecomendaciones);
	}
}
