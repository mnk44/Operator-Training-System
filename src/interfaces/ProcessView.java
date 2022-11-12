package interfaces;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import java.awt.Toolkit;

import javax.swing.JComboBox;

import java.awt.ComponentOrientation;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.border.LineBorder;

public class ProcessView extends JDialog {

	private static final long serialVersionUID = 7478765787397360968L;
	private JTextField process_name;
	private JTextField img_name;
	private JButton imgen_button;
	private JTextField var_name;
	private JTextField rules_name;
	private JButton anm_button;
	private JButton drl_button;
	private JButton button;
	private JLabel lblTiempoLmite;
	private JLabel lblCantidadDePreguntas;
	private JLabel lblCantidadDePreguntas_1;
	private JLabel lblTipoDePreguntas;
	private JLabel lblPreguntasDeCausas;
	private JLabel lblPreguntasDeRecomendaciones;
	private JSpinner spinner_2;
	private JSpinner spinner_3;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_1;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_2;
	private JPanel panel_2;
	private JLabel lblAutorizados;
	private JButton left;
	private JButton right;

	public static void main(String[] args) {
		try {
			ProcessView dialog = new ProcessView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ProcessView() {
		setTitle("Nuevo proceso");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProcessView.class.getResource("/images/icons8_Add_Node_16.png")));
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 612, 312);
		tabbedPane.setForeground(new Color(99, 68, 55));
		tabbedPane.setFont(new Font("Dubai", Font.PLAIN, 20));
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Proceso", new ImageIcon(ProcessView.class.getResource("/images/prod.png")), panel, null);
		panel.setLayout(null);
		
		JLabel lblNombreDelProceso = new JLabel("Nombre del proceso:");
		lblNombreDelProceso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombreDelProceso.setForeground(new Color(255, 113, 19));
		lblNombreDelProceso.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblNombreDelProceso.setBounds(15, 16, 264, 43);
		panel.add(lblNombreDelProceso);
		
		process_name = new JTextField();
		process_name.setFont(new Font("Corbel", Font.PLAIN, 20));
		process_name.setColumns(10);
		process_name.setBounds(285, 25, 297, 29);
		panel.add(process_name);
		
		JLabel lblImagenDelProceso = new JLabel("Imagen del proceso:");
		lblImagenDelProceso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblImagenDelProceso.setForeground(new Color(255, 113, 19));
		lblImagenDelProceso.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblImagenDelProceso.setBounds(15, 75, 264, 43);
		panel.add(lblImagenDelProceso);
		
		img_name = new JTextField();
		img_name.setFont(new Font("Corbel", Font.PLAIN, 20));
		img_name.setColumns(10);
		img_name.setBounds(285, 84, 233, 29);
		panel.add(img_name);
		
		imgen_button = new JButton();
		imgen_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				imgen_button.setBackground(new Color(240, 209, 188));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				imgen_button.setBackground(Color.WHITE);
			}
		});
		imgen_button.setIcon(new ImageIcon(ProcessView.class.getResource("/images/folder.png")));
		imgen_button.setForeground(Color.WHITE);
		imgen_button.setFont(new Font("Segoe UI", Font.BOLD, 18));
		imgen_button.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 113, 19)));
		imgen_button.setBackground(Color.WHITE);
		imgen_button.setBounds(523, 75, 59, 43);
		panel.add(imgen_button);
		
		var_name = new JTextField();
		var_name.setFont(new Font("Corbel", Font.PLAIN, 20));
		var_name.setColumns(10);
		var_name.setBounds(285, 142, 233, 29);
		panel.add(var_name);
		
		rules_name = new JTextField();
		rules_name.setFont(new Font("Corbel", Font.PLAIN, 20));
		rules_name.setColumns(10);
		rules_name.setBounds(285, 204, 233, 29);
		panel.add(rules_name);
		
		anm_button = new JButton();
		anm_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				anm_button.setBackground(new Color(240, 209, 188));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				anm_button.setBackground(Color.WHITE);
			}
		});
		anm_button.setIcon(new ImageIcon(ProcessView.class.getResource("/images/folder.png")));
		anm_button.setForeground(Color.WHITE);
		anm_button.setFont(new Font("Segoe UI", Font.BOLD, 18));
		anm_button.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 113, 19)));
		anm_button.setBackground(Color.WHITE);
		anm_button.setBounds(523, 134, 59, 43);
		panel.add(anm_button);
		
		drl_button = new JButton();
		drl_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				drl_button.setBackground(new Color(240, 209, 188));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				drl_button.setBackground(Color.WHITE);
			}
		});
		drl_button.setIcon(new ImageIcon(ProcessView.class.getResource("/images/folder.png")));
		drl_button.setForeground(Color.WHITE);
		drl_button.setFont(new Font("Segoe UI", Font.BOLD, 18));
		drl_button.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 113, 19)));
		drl_button.setBackground(Color.WHITE);
		drl_button.setBounds(523, 193, 59, 43);
		panel.add(drl_button);
		
		JLabel lblArchivoDeVariables = new JLabel("Archivo con variables:");
		lblArchivoDeVariables.setHorizontalAlignment(SwingConstants.TRAILING);
		lblArchivoDeVariables.setForeground(new Color(255, 113, 19));
		lblArchivoDeVariables.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblArchivoDeVariables.setBounds(15, 134, 264, 43);
		panel.add(lblArchivoDeVariables);
		
		JLabel lblReglas = new JLabel("Archivo con reglas:");
		lblReglas.setHorizontalAlignment(SwingConstants.TRAILING);
		lblReglas.setForeground(new Color(255, 113, 19));
		lblReglas.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblReglas.setBounds(15, 196, 264, 43);
		panel.add(lblReglas);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Entrenamiento", new ImageIcon(ProcessView.class.getResource("/images/icons8_Quiz_16.png")), panel_1, null);
		panel_1.setLayout(null);
		
		lblTiempoLmite = new JLabel("Tiempo:");
		lblTiempoLmite.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTiempoLmite.setForeground(new Color(255, 113, 19));
		lblTiempoLmite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblTiempoLmite.setBounds(424, 21, 93, 32);
		panel_1.add(lblTiempoLmite);
		
		lblCantidadDePreguntas = new JLabel("Cantidad de preguntas:");
		lblCantidadDePreguntas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidadDePreguntas.setForeground(new Color(255, 113, 19));
		lblCantidadDePreguntas.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblCantidadDePreguntas.setBounds(15, 16, 275, 43);
		panel_1.add(lblCantidadDePreguntas);
		
		lblCantidadDePreguntas_1 = new JLabel("Preguntas aprobadas:");
		lblCantidadDePreguntas_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidadDePreguntas_1.setForeground(new Color(255, 113, 19));
		lblCantidadDePreguntas_1.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblCantidadDePreguntas_1.setBounds(15, 59, 275, 43);
		panel_1.add(lblCantidadDePreguntas_1);
		
		lblTipoDePreguntas = new JLabel("Variables:");
		lblTipoDePreguntas.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTipoDePreguntas.setForeground(new Color(255, 113, 19));
		lblTipoDePreguntas.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblTipoDePreguntas.setBounds(25, 110, 212, 43);
		panel_1.add(lblTipoDePreguntas);
		
		lblPreguntasDeCausas = new JLabel("Causas:");
		lblPreguntasDeCausas.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPreguntasDeCausas.setForeground(new Color(255, 113, 19));
		lblPreguntasDeCausas.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblPreguntasDeCausas.setBounds(58, 154, 180, 43);
		panel_1.add(lblPreguntasDeCausas);
		
		lblPreguntasDeRecomendaciones = new JLabel("Recomendaciones:");
		lblPreguntasDeRecomendaciones.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreguntasDeRecomendaciones.setForeground(new Color(255, 113, 19));
		lblPreguntasDeRecomendaciones.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		lblPreguntasDeRecomendaciones.setBounds(15, 200, 223, 43);
		panel_1.add(lblPreguntasDeRecomendaciones);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Enlazar", "Espacios en blanco", "Selecci\u00F3n m\u00FAltiple", "Verdadero o falso"}));
		comboBox.setSelectedIndex(3);
		comboBox.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		comboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(242, 118, 287, 26);
		panel_1.add(comboBox);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(3, 3, 20, 1));
		spinner.setFont(new Font("Corbel", Font.PLAIN, 21));
		spinner.setBounds(295, 25, 64, 26);
		panel_1.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 20, 1));
		spinner_1.setFont(new Font("Corbel", Font.PLAIN, 21));
		spinner_1.setBounds(295, 68, 64, 26);
		panel_1.add(spinner_1);
		
		spinner_2 = new JSpinner();
		spinner_2.setFont(new Font("Corbel", Font.PLAIN, 21));
		spinner_2.setBounds(295, 25, 64, 26);
		panel_1.add(spinner_2);
		
		spinner_3 = new JSpinner();
		spinner_3.setToolTipText("Minutos");
		spinner_3.setModel(new SpinnerNumberModel(10, 3, 60, 1));
		spinner_3.setFont(new Font("Corbel", Font.PLAIN, 21));
		spinner_3.setBounds(519, 26, 64, 26);
		panel_1.add(spinner_3);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Enlazar", "Espacios en blanco", "Selecci\u00F3n m\u00FAltiple", "Verdadero o falso"}));
		comboBox_1.setSelectedIndex(1);
		comboBox_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		comboBox_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(242, 162, 287, 26);
		panel_1.add(comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Enlazar", "Espacios en blanco", "Selecci\u00F3n m\u00FAltiple", "Verdadero o falso"}));
		comboBox_2.setSelectedIndex(0);
		comboBox_2.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		comboBox_2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setBounds(244, 208, 287, 26);
		panel_1.add(comboBox_2);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Usuarios", new ImageIcon(ProcessView.class.getResource("/images/icons8_User_Groups_16.png")), panel_2, null);
		panel_2.setLayout(null);
		
		JRadioButton rdbtnTodosLosOperarios = new JRadioButton("Todos los operarios tienen acceso:  ");
		rdbtnTodosLosOperarios.setSelected(true);
		rdbtnTodosLosOperarios.setHorizontalTextPosition(SwingConstants.LEFT);
		rdbtnTodosLosOperarios.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnTodosLosOperarios.setForeground(new Color(255, 113, 19));
		rdbtnTodosLosOperarios.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		rdbtnTodosLosOperarios.setBackground(Color.WHITE);
		rdbtnTodosLosOperarios.setBounds(11, 12, 585, 29);
		panel_2.add(rdbtnTodosLosOperarios);
		
		JList<String> list = new JList<String>();
		list.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		list.setEnabled(false);
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list.setBounds(21, 74, 237, 173);
		panel_2.add(list);
		
		JLabel lblNoAutorizados = new JLabel("No autorizados:");
		lblNoAutorizados.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNoAutorizados.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoAutorizados.setForeground(new Color(99, 68, 55));
		lblNoAutorizados.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblNoAutorizados.setBounds(21, 33, 237, 38);
		panel_2.add(lblNoAutorizados);
		
		right = new JButton();
		right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(right.isEnabled()){
					right.setBackground(new Color(248, 159, 101));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(right.isEnabled()){
					right.setBackground(new Color(255, 113, 19));
				}
			}
		});
		right.setEnabled(false);
		right.setIcon(new ImageIcon(ProcessView.class.getResource("/images/icons8_Right_32.png")));
		right.setForeground(Color.WHITE);
		right.setFont(new Font("Segoe UI", Font.BOLD, 18));
		right.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		right.setBackground(new Color(248, 159, 101));
		right.setBounds(273, 119, 50, 49);
		panel_2.add(right);
		
		left = new JButton();
		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(left.isEnabled()){
					left.setBackground(new Color(248, 159, 101));
				}
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(left.isEnabled()){
					left.setBackground(new Color(255, 113, 19));
				}
			}
		});
		left.setEnabled(false);
		left.setIcon(new ImageIcon(ProcessView.class.getResource("/images/icons8_Left_32.png")));
		left.setForeground(Color.WHITE);
		left.setFont(new Font("Segoe UI", Font.BOLD, 18));
		left.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		left.setBackground(new Color(248, 159, 101));
		left.setBounds(273, 184, 50, 49);
		panel_2.add(left);
		
		JList<String> list_1 = new JList<String>();
		list_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		list_1.setEnabled(false);
		list_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list_1.setBounds(338, 74, 237, 173);
		panel_2.add(list_1);
		
		lblAutorizados = new JLabel("Autorizados:");
		lblAutorizados.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAutorizados.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutorizados.setForeground(new Color(99, 68, 55));
		lblAutorizados.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblAutorizados.setBounds(338, 33, 237, 38);
		panel_2.add(lblAutorizados);
		
		button = new JButton("Aceptar");
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
		button.setBounds(221, 328, 153, 37);
		getContentPane().add(button);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 618, 419);
	}
}
