package interfaces;

import java.awt.Color;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import java.awt.Toolkit;

import javax.swing.JComboBox;

import java.awt.ComponentOrientation;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import services.ProcessService;
import classes.Process;
import classes.ProcessConfiguration;
import classes.User;
import extras.Convert;

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
	private JSpinner time;
	@SuppressWarnings("rawtypes")
	private JComboBox causes;
	@SuppressWarnings("rawtypes")
	private JComboBox recomendations;
	private JPanel panel_2;
	private JLabel lblAutorizados;
	private JButton left;
	private JButton right;

	String imageRoute = null;
	String anmRoute = null;
	String drlRoute = null;

	ArrayList<String> autorized = new ArrayList<>();
	ArrayList<String> inautorized = new ArrayList<>();
	private JList<String> list1;
	private JList<String> list_2;
	private JRadioButton allOpers;
	private JSpinner questions;
	private JSpinner aproved;
	@SuppressWarnings("rawtypes")
	private JComboBox variables;

	public static void main(String[] args) {
		try {
			ProcessView dialog = new ProcessView(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ProcessView(final ArrayList<User> op, final User user_active) {
		for(int i=0; i<op.size(); i++){
			inautorized.add(op.get(i).getUser_nick());
		}

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
		imgen_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(img_name.getText().isEmpty()){
					JFileChooser chooser = new JFileChooser();
					chooser.setAcceptAllFileFilterUsed(false);
					chooser.setFileFilter( new FileNameExtensionFilter("Archivo de tipo imagen","jpg","png"));
					int result = chooser.showOpenDialog(null);

					if (result == JFileChooser.APPROVE_OPTION){
						img_name.setText(chooser.getSelectedFile().getName());
						imageRoute = chooser.getSelectedFile().getAbsolutePath();
						imgen_button.setIcon(new ImageIcon(NewProcessView.class.getResource("/images/trash.png")));
					}
				}else{
					img_name.setText("");
					imageRoute = null;
					imgen_button.setIcon(new ImageIcon(NewProcessView.class.getResource("/images/folder.png")));
				}
			}
		});
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
		anm_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(var_name.getText().isEmpty()){
					JFileChooser chooser = new JFileChooser();
					chooser.setAcceptAllFileFilterUsed(false);
					chooser.setFileFilter( new FileNameExtensionFilter("Archivo de tipo .anm","ANM"));
					int result = chooser.showOpenDialog(null);

					if (result == JFileChooser.APPROVE_OPTION){
						var_name.setText(chooser.getSelectedFile().getName());
						anmRoute = chooser.getSelectedFile().getAbsolutePath();
						anm_button.setIcon(new ImageIcon(NewProcessView.class.getResource("/images/trash.png")));
					}
				}else{
					var_name.setText("");
					anmRoute = null;
					anm_button.setIcon(new ImageIcon(NewProcessView.class.getResource("/images/folder.png")));
				}
			}
		});
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
		drl_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rules_name.getText().isEmpty()){
					JFileChooser chooser = new JFileChooser();
					chooser.setAcceptAllFileFilterUsed(false);
					chooser.setFileFilter( new FileNameExtensionFilter("Archivo de tipo .drl","DRL"));
					int result = chooser.showOpenDialog(null);

					if (result == JFileChooser.APPROVE_OPTION){
						rules_name.setText(chooser.getSelectedFile().getName());
						drlRoute = chooser.getSelectedFile().getAbsolutePath();
						drl_button.setIcon(new ImageIcon(NewProcessView.class.getResource("/images/trash.png")));
					}
				}else{
					rules_name.setText("");
					drlRoute = null;
					drl_button.setIcon(new ImageIcon(NewProcessView.class.getResource("/images/folder.png")));
				}
			}
		});
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

		variables = new JComboBox();
		variables.setModel(new DefaultComboBoxModel(new String[] {"Enlazar", "Espacios en blanco", "Selecci\u00F3n m\u00FAltiple", "Verdadero o falso"}));
		variables.setSelectedIndex(3);
		variables.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		variables.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		variables.setBackground(Color.WHITE);
		variables.setBounds(242, 118, 287, 26);
		panel_1.add(variables);

		questions = new JSpinner();
		questions.setModel(new SpinnerNumberModel(3, 3, 20, 1));
		questions.setFont(new Font("Corbel", Font.PLAIN, 21));
		questions.setBounds(295, 25, 64, 26);
		panel_1.add(questions);

		aproved = new JSpinner();
		aproved.setModel(new SpinnerNumberModel(1, 1, 20, 1));
		aproved.setFont(new Font("Corbel", Font.PLAIN, 21));
		aproved.setBounds(295, 68, 64, 26);
		panel_1.add(aproved);

		spinner_2 = new JSpinner();
		spinner_2.setFont(new Font("Corbel", Font.PLAIN, 21));
		spinner_2.setBounds(295, 25, 64, 26);
		panel_1.add(spinner_2);

		time = new JSpinner();
		time.setToolTipText("Minutos");
		time.setModel(new SpinnerNumberModel(10, 3, 60, 1));
		time.setFont(new Font("Corbel", Font.PLAIN, 21));
		time.setBounds(519, 26, 64, 26);
		panel_1.add(time);

		causes = new JComboBox();
		causes.setModel(new DefaultComboBoxModel(new String[] {"Enlazar", "Espacios en blanco", "Selecci\u00F3n m\u00FAltiple", "Verdadero o falso"}));
		causes.setSelectedIndex(1);
		causes.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		causes.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		causes.setBackground(Color.WHITE);
		causes.setBounds(242, 162, 287, 26);
		panel_1.add(causes);

		recomendations = new JComboBox();
		recomendations.setModel(new DefaultComboBoxModel(new String[] {"Enlazar", "Espacios en blanco", "Selecci\u00F3n m\u00FAltiple", "Verdadero o falso"}));
		recomendations.setSelectedIndex(0);
		recomendations.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 20));
		recomendations.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		recomendations.setBackground(Color.WHITE);
		recomendations.setBounds(244, 208, 287, 26);
		panel_1.add(recomendations);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Usuarios", new ImageIcon(ProcessView.class.getResource("/images/icons8_User_Groups_16.png")), panel_2, null);
		panel_2.setLayout(null);

		allOpers = new JRadioButton("Todos los operarios tienen acceso:  ");
		allOpers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(allOpers.isSelected()){
					list1.setEnabled(false);
					list_2.setEnabled(false);
				}else{
					list1.setEnabled(true);
					list_2.setEnabled(true);
				}
			}
		});
		allOpers.setSelected(true);
		allOpers.setHorizontalTextPosition(SwingConstants.LEFT);
		allOpers.setHorizontalAlignment(SwingConstants.CENTER);
		allOpers.setForeground(new Color(255, 113, 19));
		allOpers.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		allOpers.setBackground(Color.WHITE);
		allOpers.setBounds(11, 12, 585, 29);
		panel_2.add(allOpers);

		list1 = new JList<String>(new AbstractListModel<String>(){	
			private static final long serialVersionUID = 599156348656687464L;
			public int getSize() {
				return inautorized.size(); 
			}
			public String getElementAt(int i) {
				return inautorized.get(i);
			}
		});
		list1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(list1.isSelectionEmpty()){
					right.setEnabled(false);
					right.setBackground(new Color(248, 159, 101));
				}else{
					right.setEnabled(true);
					right.setBackground(new Color(255, 113, 19));
				}
			}
		});
		list1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		list1.setEnabled(false);
		list1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list1.setBounds(21, 74, 237, 173);
		panel_2.add(list1);

		JLabel lblNoAutorizados = new JLabel("No autorizados:");
		lblNoAutorizados.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNoAutorizados.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoAutorizados.setForeground(new Color(99, 68, 55));
		lblNoAutorizados.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblNoAutorizados.setBounds(21, 33, 237, 38);
		panel_2.add(lblNoAutorizados);

		right = new JButton();
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizar(0);
			}
		});
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
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizar(1);
			}
		});
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

		list_2 = new JList<String>(new AbstractListModel<String>(){		
			private static final long serialVersionUID = 59915636566874624L;
			public int getSize() {
				return autorized.size(); 
			}
			public String getElementAt(int i) {
				return autorized.get(i);
			}
		});
		list_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(list_2.isSelectionEmpty()){
					left.setEnabled(false);
					left.setBackground(new Color(248, 159, 101));
				}else{
					left.setEnabled(true);
					left.setBackground(new Color(255, 113, 19));
				}
			}
		});
		list_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		list_2.setEnabled(false);
		list_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list_2.setBounds(338, 74, 237, 173);
		panel_2.add(list_2);

		lblAutorizados = new JLabel("Autorizados:");
		lblAutorizados.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAutorizados.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutorizados.setForeground(new Color(99, 68, 55));
		lblAutorizados.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		lblAutorizados.setBounds(338, 33, 237, 38);
		panel_2.add(lblAutorizados);

		button = new JButton("Aceptar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//validations
				if(anmRoute == null || drlRoute == null){
					JOptionPane.showMessageDialog(null, "Debe rellenar los ficheros del proceso", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(process_name.getText().replace(" ", "").isEmpty()){
					JOptionPane.showMessageDialog(null, "El nombre del proceso debe ser un nombre válido", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(!allOpers.isSelected() && autorized.isEmpty()){
					JOptionPane.showMessageDialog(null, "Deben existir usuarios capaces de realizar el entrenamiento", "Error", JOptionPane.ERROR_MESSAGE);
				}else if((int)questions.getValue()<=(int)aproved.getValue()){
					if((int)questions.getValue()!=(int)aproved.getValue()){
						JOptionPane.showMessageDialog(null, "La cantidad de preguntas aprobadas no puede ser mayor que la cantidad de preguntas en total", "Error", JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Debe existir un margen de error en las preguntas, aumente la cantidad de preguntas/n o disminuya la cantidad de aprobados", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				//save
				Process proc = null;
				if(imageRoute != null){
					try {
						proc = new Process(process_name.getText(), user_active.getUser_area(), Convert.toBytes(new File(imageRoute)), Convert.toBytes(new File(anmRoute)), Convert.toBytes(new File(drlRoute)));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					try {
						proc = new Process(process_name.getText(), user_active.getUser_area(), null, Convert.toBytes(new File(anmRoute)), Convert.toBytes(new File(drlRoute)));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				ProcessConfiguration conf = new ProcessConfiguration((int)time.getValue(), (int)questions.getValue(), (int)aproved.getValue(), (String)variables.getSelectedItem(), (String)causes.getSelectedItem(), (String)recomendations.getSelectedItem());
			    try {
					Object result = ProcessService.newProcess(proc, user_active.getUser_nick(), new Timestamp(Calendar.getInstance().getTime().getTime()));
					if(!result.toString().contains("e")){
						int id_process = (int)result;
						String result2 = null;
						if(allOpers.isSelected()){
							 result2 = ProcessService.insertUsers(op, id_process, conf);
						}else{
							ArrayList<User> aux = new ArrayList<>();
							for(int k=0; k<autorized.size(); k++){
								boolean found = true;
								for(int i=0; i<op.size() && found; i++){
									if(autorized.get(k).equals(op.get(i).getUser_nick())){
										aux.add(op.get(i));
										found = false;
									}
								}
							}
							result2 = ProcessService.insertUsers(aux, id_process, conf);
						}
						if(result2 != null){
							JOptionPane.showMessageDialog(null, result2, "Error", JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
				}
			    //anm y drl
			}
		});
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

	public void actualizar(int action){
		if(action == 0){
			int position = list1.getSelectedIndex();
			autorized.add(inautorized.get(position));
			inautorized.remove(position);
			list_2.setModel(new AbstractListModel<String>(){
				private static final long serialVersionUID = 1563486566874624L;
				public int getSize() {
					return autorized.size(); 
				}
				public String getElementAt(int i) {
					return autorized.get(i);
				}
			});
			list1.setModel(new AbstractListModel<String>(){	
				private static final long serialVersionUID = 51563486566874624L;
				public int getSize() {
					return inautorized.size(); 
				}
				public String getElementAt(int i) {
					return inautorized.get(i);
				}
			});
		}else{
			int position = list_2.getSelectedIndex();
			inautorized.add(autorized.get(position));
			autorized.remove(position);
			list1.setModel(new AbstractListModel<String>(){	
				private static final long serialVersionUID = 599186566874624L;
				public int getSize() {
					return inautorized.size(); 
				}
				public String getElementAt(int i) {
					return inautorized.get(i);
				}
			});
			list_2.setModel(new AbstractListModel<String>(){	
				private static final long serialVersionUID = 599156348674624L;
				public int getSize() {
					return autorized.size(); 
				}
				public String getElementAt(int i) {
					return autorized.get(i);
				}
			});
		}
		list_2.clearSelection();
		list1.clearSelection();
		right.setEnabled(false);
		left.setEnabled(false);
		left.setBackground(new Color(248, 159, 101));
		right.setBackground(new Color(248, 159, 101));
	}
}
