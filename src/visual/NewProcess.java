package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTabbedPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

import services.ProcessConfigService;
import services.ProcessService;
import services.UserService;
import utils.QuestionType;

import java.awt.ComponentOrientation;
import java.io.File;
import java.io.IOException;

import contentClass.Process;
import contentClass.ProcessConfig;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

import logic.ChargeFileProcess;
import logic.Convert;
import contentClass.User;

public class NewProcess extends JDialog {

	private static final long serialVersionUID = 5991563486566874624L;
	private JTabbedPane tabbedPane;
	private JTextField nameProcess;
	private JTextField imageField;
	private JButton findImage;
	private JTextField anmName;
	private JTextField drlName;
	private JButton findAnm;
	private JButton findDrl;
	private JPanel config;
	private JButton btnSiguiente;
	private JPanel process;
	private JPanel users;

	private int area;

	String imgRoute = null;
	String anmRoute = null;
	String drlRoute = null;

	ArrayList<String> usersNA = new ArrayList<>();
	ArrayList<String> usersA = new ArrayList<>();

	private JButton btnEliminarImagen;
	private JButton button;
	private JButton btnAnterior;
	private JLabel lblEntrenamientoDeVariables;
	private JLabel lblCantidadDeIntentos_1;
	private JLabel lblTipoDePregunta;
	private final JSeparator separator = new JSeparator();
	private JLabel lblEntrenamientoDeRecomendaciones;
	private JLabel label_1;
	private JLabel label_4;
	private JSpinner cantRec;
	private JSpinner cantCause;
	private JSpinner cantVar;
	private JComboBox<?> typeVar;
	private JComboBox<?> typeCause;
	private JComboBox<?> typeRec;
	private JRadioButton consVar;
	private JRadioButton consCause;
	private JRadioButton consRec;
	private JButton button_1;
	private JButton btnTerminar;
	private JButton nosendaut;
	private JButton sendAut;
	private JLabel lblCantidadDeIntentos;
	private JSpinner total;
	private JRadioButton rdbtnTodosLosOperarios;
	private JLabel autNoLabel;
	private JLabel autLabel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JList<String> aut;
	private JList<String> noAut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewProcess dialog = new NewProcess(-1, 1);
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
	@SuppressWarnings({ "deprecation" })
	public NewProcess(final int option, final int area) throws SQLException {
		this.area = area;
		usersNA = fillUser();
		getContentPane().setBackground(Color.WHITE);
		if(option == -1){
			setTitle("Nuevo Proceso");
		}else{
			setTitle("Modificar Proceso");
		}
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewUser.class.getResource("/img/Captura de pantalla (133).png")));
		setResizable(false);
		setBounds(100, 100, 691, 526);
		getContentPane().setLayout(new BorderLayout());

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setEnabled(false);
		tabbedPane.setFont(new Font("Arial", Font.BOLD, 16));
		tabbedPane.setBounds(384, 380, -383, -350);
		tabbedPane.setBorder(null);

		process = new JPanel();
		process.setBorder(null);
		process.setBackground(Color.WHITE);
		tabbedPane.addTab("Proceso", process);
		process.setLayout(null);

		JLabel lblNombreDelProceso = new JLabel("Nombre del Proceso:");
		lblNombreDelProceso.setBounds(15, 32, 179, 20);
		lblNombreDelProceso.setFont(new Font("Arial", Font.BOLD, 16));
		process.add(lblNombreDelProceso);

		nameProcess = new JTextField();
		nameProcess.addKeyListener(new KeyAdapter() {
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
		nameProcess.setBounds(181, 30, 405, 26);
		nameProcess.setFont(new Font("Arial", Font.PLAIN, 16));
		nameProcess.setColumns(10);
		process.add(nameProcess);

		JLabel lblFechaDeCreacin = new JLabel("Fecha de Creaci\u00F3n:");
		lblFechaDeCreacin.setBounds(15, 76, 162, 20);
		lblFechaDeCreacin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFechaDeCreacin.setFont(new Font("Arial", Font.BOLD, 16));
		process.add(lblFechaDeCreacin);

		Timestamp time = new Timestamp(Calendar.getInstance().getTime().getTime());
		String date = time.toLocaleString();

		JLabel lblNombreDelProceso_1 = new JLabel(date);
		lblNombreDelProceso_1.setBounds(306, 76, 179, 20);
		lblNombreDelProceso_1.setEnabled(false);
		lblNombreDelProceso_1.setFont(new Font("Arial", Font.BOLD, 16));
		process.add(lblNombreDelProceso_1);

		JLabel lblImagenDelProceso = new JLabel("Imagen del Proceso:");
		lblImagenDelProceso.setBounds(15, 126, 162, 20);
		lblImagenDelProceso.setHorizontalAlignment(SwingConstants.TRAILING);
		lblImagenDelProceso.setFont(new Font("Arial", Font.BOLD, 16));
		process.add(lblImagenDelProceso);

		imageField = new JTextField();
		imageField.setBackground(Color.WHITE);
		imageField.setEditable(false);
		imageField.setBounds(181, 124, 405, 26);
		imageField.setFont(new Font("Arial", Font.PLAIN, 16));
		imageField.setColumns(10);
		process.add(imageField);

		findImage = new JButton();
		findImage.setBounds(599, 112, 53, 42);
		findImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				findImage.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				findImage.setBackground(new Color(255, 255, 201));
			}
		});
		findImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter( new FileNameExtensionFilter("Archivo de Imagen","jpg","png") );
				int result = chooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION){
					imageField.setText("");
					imageField.setText(chooser.getSelectedFile().getName());
					imgRoute = chooser.getSelectedFile().getAbsolutePath();
					btnEliminarImagen.setVisible(true);
				}
			}		
		});
		findImage.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Download_16.png")));
		findImage.setFont(new Font("Segoe UI", Font.BOLD, 17));
		findImage.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		findImage.setBackground(new Color(255, 255, 201));
		process.add(findImage);

		JLabel lblFicheroanm = new JLabel("Fichero ANM (.anm):");
		lblFicheroanm.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFicheroanm.setFont(new Font("Arial", Font.BOLD, 16));
		lblFicheroanm.setBounds(15, 183, 162, 20);
		process.add(lblFicheroanm);

		anmName = new JTextField();
		anmName.setBackground(Color.WHITE);
		anmName.setEditable(false);
		anmName.setFont(new Font("Arial", Font.PLAIN, 16));
		anmName.setColumns(10);
		anmName.setBounds(181, 180, 405, 26);
		process.add(anmName);

		findAnm = new JButton();
		findAnm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				findAnm.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				findAnm.setBackground(new Color(255, 255, 201));
			}
		});
		findAnm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter( new FileNameExtensionFilter("Archivo .anm","ANM") );
				int result = chooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION){
					anmName.setText("");
					anmName.setText(chooser.getSelectedFile().getName());
					anmRoute = chooser.getSelectedFile().getAbsolutePath();	            
				}				
			}
		});
		findAnm.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Download_16.png")));
		findAnm.setFont(new Font("Segoe UI", Font.BOLD, 17));
		findAnm.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		findAnm.setBackground(new Color(255, 255, 201));
		findAnm.setBounds(599, 169, 53, 42);
		process.add(findAnm);

		JLabel lblFicheroDeReglas = new JLabel("Fichero DRL (.drl):");
		lblFicheroDeReglas.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFicheroDeReglas.setFont(new Font("Arial", Font.BOLD, 16));
		lblFicheroDeReglas.setBounds(15, 241, 162, 20);
		process.add(lblFicheroDeReglas);

		drlName = new JTextField();
		drlName.setBackground(Color.WHITE);
		drlName.setEditable(false);
		drlName.setFont(new Font("Arial", Font.PLAIN, 16));
		drlName.setColumns(10);
		drlName.setBounds(181, 238, 405, 26);
		process.add(drlName);

		findDrl = new JButton();
		findDrl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				findDrl.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				findDrl.setBackground(new Color(255, 255, 201));
			}
		});
		findDrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter( new FileNameExtensionFilter("Archivo .drl","DRL") );
				int result = chooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION){
					drlName.setText("");
					drlName.setText(chooser.getSelectedFile().getName());
					drlRoute = chooser.getSelectedFile().getAbsolutePath();	            
				}				
			}
		});
		findDrl.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Download_16.png")));
		findDrl.setFont(new Font("Segoe UI", Font.BOLD, 17));
		findDrl.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		findDrl.setBackground(new Color(255, 255, 201));
		findDrl.setBounds(599, 227, 53, 42);
		process.add(findDrl);

		config = new JPanel();
		config.setBorder(null);
		config.setBackground(Color.WHITE);
		tabbedPane.addTab("Entrenamiento", config);
		config.setLayout(null);

		button = new JButton("Siguiente");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedComponent(users);
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				button.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				button.setBackground(new Color(255, 255, 201));
			}
		});
		button.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Right_16.png")));
		button.setHorizontalTextPosition(SwingConstants.LEFT);
		button.setFont(new Font("Segoe UI", Font.BOLD, 17));
		button.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		button.setBackground(new Color(255, 255, 201));
		button.setBounds(498, 399, 155, 38);
		config.add(button);

		btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedComponent(process);
			}
		});
		btnAnterior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnAnterior.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnAnterior.setBackground(new Color(255, 255, 201));
			}
		});
		btnAnterior.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Long_Arrow_Left_16.png")));
		btnAnterior.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnAnterior.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnAnterior.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		btnAnterior.setBackground(new Color(255, 255, 201));
		btnAnterior.setBounds(15, 399, 155, 38);
		config.add(btnAnterior);

		lblEntrenamientoDeVariables = new JLabel("Entrenamiento de Variables");
		lblEntrenamientoDeVariables.setFont(new Font("Arial", Font.BOLD, 18));
		lblEntrenamientoDeVariables.setBounds(215, 16, 249, 20);
		config.add(lblEntrenamientoDeVariables);

		lblCantidadDeIntentos_1 = new JLabel("Cantidad de Intentos Aprobados:");
		lblCantidadDeIntentos_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblCantidadDeIntentos_1.setBounds(15, 52, 253, 20);
		config.add(lblCantidadDeIntentos_1);

		lblTipoDePregunta = new JLabel("Forma de Pregunta:");
		lblTipoDePregunta.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTipoDePregunta.setFont(new Font("Arial", Font.BOLD, 16));
		lblTipoDePregunta.setBounds(15, 88, 253, 20);
		config.add(lblTipoDePregunta);
		separator.setBounds(0, 122, 680, 41);
		config.add(separator);

		JLabel lblEntrenamientoDeCausas = new JLabel("Entrenamiento de Causas");
		lblEntrenamientoDeCausas.setFont(new Font("Arial", Font.BOLD, 18));
		lblEntrenamientoDeCausas.setBounds(215, 143, 249, 20);
		config.add(lblEntrenamientoDeCausas);

		JLabel label_2 = new JLabel("Cantidad de Intentos Aprobados:");
		label_2.setFont(new Font("Arial", Font.BOLD, 16));
		label_2.setBounds(15, 179, 253, 20);
		config.add(label_2);

		JLabel label_3 = new JLabel("Forma de Pregunta:");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setFont(new Font("Arial", Font.BOLD, 16));
		label_3.setBounds(15, 215, 253, 20);
		config.add(label_3);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 251, 680, 41);
		config.add(separator_1);

		lblEntrenamientoDeRecomendaciones = new JLabel("Entrenamiento de Recomendaciones");
		lblEntrenamientoDeRecomendaciones.setFont(new Font("Arial", Font.BOLD, 18));
		lblEntrenamientoDeRecomendaciones.setBounds(176, 261, 334, 20);
		config.add(lblEntrenamientoDeRecomendaciones);

		label_1 = new JLabel("Cantidad de Intentos Aprobados:");
		label_1.setFont(new Font("Arial", Font.BOLD, 16));
		label_1.setBounds(15, 297, 253, 20);
		config.add(label_1);

		label_4 = new JLabel("Forma de Pregunta:");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setFont(new Font("Arial", Font.BOLD, 16));
		label_4.setBounds(15, 333, 253, 20);
		config.add(label_4);

		cantVar = new JSpinner(new SpinnerNumberModel(5, 1, 20, 1));
		cantVar.setFont(new Font("Arial", Font.PLAIN, 16));
		cantVar.setBounds(276, 50, 59, 26);
		config.add(cantVar);

		cantCause = new JSpinner(new SpinnerNumberModel(5, 1, 20, 1));
		cantCause.setFont(new Font("Arial", Font.PLAIN, 16));
		cantCause.setBounds(276, 176, 59, 26);
		config.add(cantCause);

		cantRec = new JSpinner(new SpinnerNumberModel(5, 1, 20, 1));
		cantRec.setFont(new Font("Arial", Font.PLAIN, 16));
		cantRec.setBounds(276, 294, 59, 26);
		config.add(cantRec);

		typeVar = new JComboBox<Object>(QuestionType.values());
		typeVar.setSelectedIndex(2);
		typeVar.setFont(new Font("Arial", Font.BOLD, 16));
		typeVar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		typeVar.setBackground(Color.WHITE);
		typeVar.setBounds(276, 85, 205, 26);
		config.add(typeVar);

		typeCause = new JComboBox<Object>(QuestionType.values());
		typeCause.setSelectedIndex(3);
		typeCause.setFont(new Font("Arial", Font.BOLD, 16));
		typeCause.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		typeCause.setBackground(Color.WHITE);
		typeCause.setBounds(276, 212, 205, 26);
		config.add(typeCause);

		typeRec = new JComboBox<Object>(QuestionType.values());
		typeRec.setSelectedIndex(3);
		typeRec.setFont(new Font("Arial", Font.BOLD, 16));
		typeRec.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		typeRec.setBackground(Color.WHITE);
		typeRec.setBounds(276, 330, 205, 26);
		config.add(typeRec);

		consVar = new JRadioButton("Consecutivo");
		consVar.setFont(new Font("Arial", Font.BOLD, 17));
		consVar.setBackground(Color.WHITE);
		consVar.setBounds(514, 48, 155, 29);
		config.add(consVar);

		consCause = new JRadioButton("Consecutivo");
		consCause.setFont(new Font("Arial", Font.BOLD, 17));
		consCause.setBackground(Color.WHITE);
		consCause.setBounds(514, 175, 155, 29);
		config.add(consCause);

		consRec = new JRadioButton("Consecutivo");
		consRec.setFont(new Font("Arial", Font.BOLD, 17));
		consRec.setBackground(Color.WHITE);
		consRec.setBounds(514, 293, 155, 29);
		config.add(consRec);

		users = new JPanel();
		users.setBorder(null);
		users.setBackground(Color.WHITE);
		tabbedPane.addTab("Usuarios", users);
		users.setLayout(null);

		button_1 = new JButton("Anterior");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedComponent(config);
			}
		});
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				button_1.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				button_1.setBackground(new Color(255, 255, 201));
			}
		});
		button_1.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Long_Arrow_Left_16.png")));
		button_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		button_1.setFont(new Font("Segoe UI", Font.BOLD, 17));
		button_1.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		button_1.setBackground(new Color(255, 255, 201));
		button_1.setBounds(15, 399, 155, 38);
		users.add(button_1);

		btnTerminar = new JButton("Aceptar");
		btnTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!rdbtnTodosLosOperarios.isSelected() && usersA.isEmpty()){
					JOptionPane.showMessageDialog(null, "Debe autorizar a al menos un usuario", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					Process proc = null;
					try {
						if(imageField.getText().isEmpty()){
							proc = new Process(nameProcess.getText(), new Timestamp(Calendar.getInstance().getTime().getTime()), null,
									Convert.toBytes(new File(anmRoute)),
									Convert.toBytes(new File(drlRoute)), area);
						}else{
							proc = new Process(nameProcess.getText(), new Timestamp(Calendar.getInstance().getTime().getTime()), Convert.toBytes(new File(imgRoute)),
									Convert.toBytes(new File(anmRoute)),
									Convert.toBytes(new File(drlRoute)), area);
						}
						ProcessService.createProcess(proc);

						ProcessConfig config = new ProcessConfig(ProcessService.findByName(nameProcess.getText()).getId_process(), (int) total.getValue(), (int) cantVar.getValue(), consVar.isSelected(),
								QuestionType.valueOf(typeVar.getSelectedItem().toString()), (int) cantCause.getValue(),
								consCause.isSelected(), QuestionType.valueOf(typeCause.getSelectedItem().toString()), (int) cantRec.getValue(), consRec.isSelected(), QuestionType.valueOf(typeRec.getSelectedItem().toString()));
						ProcessConfigService.createProcessConfig(config);

						int proces = ProcessService.findByName(nameProcess.getText()).getId_process();
						
						if(rdbtnTodosLosOperarios.isSelected()){
							ArrayList<User> u = UserService.getOp(area);

							for(int i=0; i<u.size(); i++){
								ProcessConfigService.createUserRelate(proces, u.get(i).getId_user());
							}
						}else{
							//ProcessConfigService.deleteAllRelations(proces);

							for(int i=0; i<usersA.size(); i++){
								User u = UserService.findByName(usersA.get(i), area);
								ProcessConfigService.createUserRelate(proces, u.getId_user());
							}
						}			
						//JOptionPane.showMessageDialog(null, "Cargando ", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
						
						ChargeFileProcess.chargeAnmBD(proces);
						ChargeFileProcess.chargeDrlBD(proces);
						
						JOptionPane.showMessageDialog(null, "Nuevo Proceso añadido satisfactoriamente", "Acción Completada", JOptionPane.INFORMATION_MESSAGE);
						ProcessManagement.reloadTable();
						dispose();
					} catch (IOException | SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnTerminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnTerminar.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnTerminar.setBackground(new Color(255, 255, 201));
			}
		});
		btnTerminar.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Checkmark_16.png")));
		btnTerminar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnTerminar.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnTerminar.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		btnTerminar.setBackground(new Color(255, 255, 201));
		btnTerminar.setBounds(498, 399, 155, 38);
		users.add(btnTerminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 157, 252, 213);
		users.add(scrollPane);

		noAut = new JList<String>(new AbstractListModel<String>(){	
			private static final long serialVersionUID = 599156348656687464L;
			
			public int getSize() {
				return usersNA.size(); 
			}

			public String getElementAt(int i) {
				return usersNA.get(i);
			}

		});
		noAut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(noAut.isSelectionEmpty()){
					sendAut.setEnabled(false);
				}else{
					sendAut.setEnabled(true);
				}
			}
		});
		noAut.setEnabled(false);
		scrollPane.setViewportView(noAut);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(382, 157, 258, 213);
		users.add(scrollPane_1);

		aut = new JList<String>(new AbstractListModel<String>(){		
			private static final long serialVersionUID = 59915636566874624L;
			
			public int getSize() {
				return usersA.size(); 
			}

			public String getElementAt(int i) {
				return usersA.get(i);
			}

		});
		aut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(aut.isSelectionEmpty()){
					nosendaut.setEnabled(false);
				}else{
					nosendaut.setEnabled(true);
				}
			}
		});
		aut.setEnabled(false);
		scrollPane_1.setViewportView(aut);

		autNoLabel = new JLabel("Usuarios no Autorizados");
		autNoLabel.setEnabled(false);
		autNoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		autNoLabel.setFont(new Font("Arial", Font.BOLD, 18));
		autNoLabel.setBounds(28, 132, 252, 20);
		users.add(autNoLabel);

		autLabel = new JLabel("Usuarios Autorizados");
		autLabel.setEnabled(false);
		autLabel.setHorizontalAlignment(SwingConstants.CENTER);
		autLabel.setFont(new Font("Arial", Font.BOLD, 18));
		autLabel.setBounds(382, 132, 258, 20);
		users.add(autLabel);

		nosendaut = new JButton();
		nosendaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizar(1);
			}
		});
		nosendaut.setEnabled(false);
		nosendaut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(nosendaut.isEnabled())
					nosendaut.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(nosendaut.isEnabled())
					nosendaut.setBackground(new Color(255, 255, 201));
			}
		});
		nosendaut.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Long_Arrow_Left_16.png")));
		nosendaut.setHorizontalTextPosition(SwingConstants.RIGHT);
		nosendaut.setFont(new Font("Segoe UI", Font.BOLD, 17));
		nosendaut.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		nosendaut.setBackground(new Color(255, 255, 201));
		nosendaut.setBounds(309, 268, 47, 38);
		users.add(nosendaut);

		sendAut = new JButton();
		sendAut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizar(0);
			}
		});
		sendAut.setEnabled(false);
		sendAut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(sendAut.isEnabled())
					sendAut.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(sendAut.isEnabled())
					sendAut.setBackground(new Color(255, 255, 201));
			}
		});
		sendAut.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Right_16.png")));
		sendAut.setHorizontalTextPosition(SwingConstants.RIGHT);
		sendAut.setFont(new Font("Segoe UI", Font.BOLD, 17));
		sendAut.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		sendAut.setBackground(new Color(255, 255, 201));
		sendAut.setBounds(309, 214, 47, 38);
		users.add(sendAut);

		lblCantidadDeIntentos = new JLabel("Cantidad de Intentos Totales permitidos por Entrenamiento:");
		lblCantidadDeIntentos.setFont(new Font("Arial", Font.BOLD, 16));
		lblCantidadDeIntentos.setBounds(36, 33, 455, 20);
		users.add(lblCantidadDeIntentos);

		total = new JSpinner(new SpinnerNumberModel(10, 1, 50, 1));
		total.setFont(new Font("Arial", Font.PLAIN, 16));
		total.setBounds(496, 31, 59, 26);
		users.add(total);

		rdbtnTodosLosOperarios = new JRadioButton("Todos los Operarios tienen acceso a este Entrenamiento:");
		rdbtnTodosLosOperarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnTodosLosOperarios.isSelected()){
					noAut.setEnabled(false);
					aut.setEnabled(false);
					autLabel.setEnabled(false);
					autNoLabel.setEnabled(false);
				}else{
					noAut.setEnabled(true);
					aut.setEnabled(true);
					autLabel.setEnabled(true);
					autNoLabel.setEnabled(true);
				}
			}
		});
		rdbtnTodosLosOperarios.setHorizontalTextPosition(SwingConstants.LEFT);
		rdbtnTodosLosOperarios.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnTodosLosOperarios.setSelected(true);
		rdbtnTodosLosOperarios.setFont(new Font("Arial", Font.BOLD, 17));
		rdbtnTodosLosOperarios.setBackground(Color.WHITE);
		rdbtnTodosLosOperarios.setBounds(36, 77, 490, 29);
		users.add(rdbtnTodosLosOperarios);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnSiguiente.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnSiguiente.setBackground(new Color(255, 255, 201));
			}
		});
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nameProcess.getText().isEmpty() || nameProcess.getText().replaceAll(" ", "").isEmpty()
						|| anmName.getText().isEmpty() || drlName.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Debe completar el nombre y los ficheros.", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					tabbedPane.setSelectedComponent(config);
				}
			}
		});
		btnSiguiente.setHorizontalTextPosition(SwingConstants.LEFT);
		btnSiguiente.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Right_16.png")));
		btnSiguiente.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnSiguiente.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		btnSiguiente.setBackground(new Color(255, 255, 201));
		btnSiguiente.setBounds(498, 399, 155, 38);
		process.add(btnSiguiente);

		btnEliminarImagen = new JButton("Eliminar Imagen");
		btnEliminarImagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnEliminarImagen.setBackground(new Color(255, 206, 126));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnEliminarImagen.setBackground(new Color(255, 255, 201));
			}
		});
		btnEliminarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				imageField.setText("");
				imgRoute = null;
				btnEliminarImagen.setVisible(false);
			}
		});
		btnEliminarImagen.setVisible(false);
		btnEliminarImagen.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Trash_16.png")));
		btnEliminarImagen.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnEliminarImagen.setBorder(new LineBorder(new Color(255, 186, 74), 2));
		btnEliminarImagen.setBackground(new Color(255, 255, 201));
		btnEliminarImagen.setBounds(460, 309, 192, 38);
		process.add(btnEliminarImagen);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(NewProcess.class.getResource("/img/Grupo 4.png")));
		lblNewLabel.setBounds(97, 277, 322, 160);
		process.add(lblNewLabel);

		getContentPane().add(tabbedPane);
	}

	public ArrayList<String> fillUser() throws SQLException{
		ArrayList<String> names = new ArrayList<>();
		ArrayList<User> user = UserService.getOp(area);

		for(int i=0; i<user.size(); i++){
			names.add(user.get(i).getName_user());
		}

		return names;
	}

	public void actualizar(int action){
		if(action == 0){
			int position = noAut.getSelectedIndex();
			usersA.add(usersNA.get(position));
			usersNA.remove(position);
			aut.setModel(new AbstractListModel<String>(){
				private static final long serialVersionUID = 1563486566874624L;
				
				public int getSize() {
					return usersA.size(); 
				}

				public String getElementAt(int i) {
					return usersA.get(i);
				}

			});
			noAut.setModel(new AbstractListModel<String>(){	
				private static final long serialVersionUID = 51563486566874624L;
				
				public int getSize() {
					return usersNA.size(); 
				}

				public String getElementAt(int i) {
					return usersNA.get(i);
				}

			});
			aut.clearSelection();
			noAut.clearSelection();
			sendAut.setEnabled(false);
			nosendaut.setEnabled(false);
			nosendaut.setBackground(new Color(255, 255, 201));
			sendAut.setBackground(new Color(255, 255, 201));
		}else{
			int position = aut.getSelectedIndex();
			usersNA.add(usersA.get(position));
			usersA.remove(position);
			noAut.setModel(new AbstractListModel<String>(){	
				private static final long serialVersionUID = 599186566874624L;
				
				public int getSize() {
					return usersNA.size(); 
				}

				public String getElementAt(int i) {
					return usersNA.get(i);
				}

			});
			aut.setModel(new AbstractListModel<String>(){	
				private static final long serialVersionUID = 599156348674624L;
				
				public int getSize() {
					return usersA.size(); 
				}

				public String getElementAt(int i) {
					return usersA.get(i);
				}

			});
			aut.clearSelection();
			noAut.clearSelection();
			sendAut.setEnabled(false);
			nosendaut.setEnabled(false);
			nosendaut.setBackground(new Color(255, 255, 201));
			sendAut.setBackground(new Color(255, 255, 201));
		}
	}
}
