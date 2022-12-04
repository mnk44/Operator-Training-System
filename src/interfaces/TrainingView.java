package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TrainingView {

	private JFrame frmEntrenamiento;
	private JLabel process_name;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainingView window = new TrainingView("Proceso de la caña");
					window.frmEntrenamiento.setLocationRelativeTo(null);
					window.frmEntrenamiento.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TrainingView(String process) {
		initialize(process);
	}

	private void initialize(String process) {
		frmEntrenamiento = new JFrame();
		frmEntrenamiento.setTitle("Entrenamiento");
		frmEntrenamiento.setIconImage(Toolkit.getDefaultToolkit().getImage(TrainingView.class.getResource("/images/icons8_Quiz_32.png")));
		frmEntrenamiento.getContentPane().setBackground(Color.WHITE);
		frmEntrenamiento.getContentPane().setLayout(null);
		
		JLabel lblNombreDelProceso = new JLabel("Nombre del proceso:");
		lblNombreDelProceso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelProceso.setForeground(new Color(255, 113, 19));
		lblNombreDelProceso.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 22));
		lblNombreDelProceso.setBounds(15, 16, 302, 49);
		frmEntrenamiento.getContentPane().add(lblNombreDelProceso);
		
		process_name = new JLabel(process);
		process_name.setHorizontalAlignment(SwingConstants.LEFT);
		process_name.setForeground(new Color(99, 68, 55));
		process_name.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 22));
		process_name.setBounds(324, 16, 556, 49);
		frmEntrenamiento.getContentPane().add(process_name);
		frmEntrenamiento.setResizable(false);
		frmEntrenamiento.setBounds(100, 100, 901, 514);
		frmEntrenamiento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
