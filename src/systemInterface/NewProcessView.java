package systemInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.AbstractListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;

import knowledgeBase.LoadFiles;
import systemClass.FactoryProcess;
import systemClass.User;
import systemEnums.QuestionsTypes;
import systemEnums.RolesTypes;
import systemLogic.Convert;
import systemServices.FactoryProcessService;
import systemServices.UserService;

public class NewProcessView extends JDialog {

	private static final long serialVersionUID = -3463338119647839725L;
	private JTextField processName;
	private JTextField anm;
	private JTextField drl;
	private JTextField image;

	User userG = null;
	
	String imageRoute = null;
	String anmRoute = null;
	String drlRoute = null;
	
	ArrayList<String> usersNA = new ArrayList<>();
	ArrayList<String> usersA = new ArrayList<>();
	
	private JRadioButton rdbtnTodosLosOperarios;
	private JLabel lblUsuarios;
	private JLabel lblUsuariosAutorizados;
	private JList<String> noAut;
	private JList<String> aut;
	private JButton right;
	private JButton left;
	private JSpinner cantEnt;
	private JSpinner cantAprov;
	private JComboBox<String> var;
	private JComboBox<String> cause;
	private JComboBox<String> rec;
	private JButton imageButt;
	private JButton button_2;
	private JButton button_1;

	public static void main(String[] args) {
		try {
			NewProcessView dialog = new NewProcessView(4);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NewProcessView(int u) throws SQLException {
		setModal(true);
		userG = (User) UserService.findId(u);
		usersNA = fillUser();
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/imgs/logo.png")));
		setBackground(new Color(173, 216, 230));
		setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		setForeground(Color.BLACK);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("Aceptar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				if(processName.getText().replace(" ", "").isEmpty() || anmRoute.isEmpty() || drlRoute.isEmpty()){
					JOptionPane.showMessageDialog(null, "Debes completar todos los campos para continuar", "Error", JOptionPane.ERROR_MESSAGE);
				}else if((int)cantEnt.getValue() < (int)cantAprov.getValue()){
					JOptionPane.showMessageDialog(null, "La cantidad de intentos debe ser mayor que la cantidad intentos aprobados", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					
					//insert process into database
					FactoryProcess process = null;
					if(image.getText().isEmpty()){
						try {
							process = new FactoryProcess(processName.getText(), userG.getUser_area(), null, Convert.toBytes(new File(anmRoute)), Convert.toBytes(new File(drlRoute)));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						try {
							process = new FactoryProcess(processName.getText(), userG.getUser_area(), Convert.toBytes(new File(imageRoute)), Convert.toBytes(new File(anmRoute)), Convert.toBytes(new File(drlRoute)));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					String result = null;
					try {
						result = FactoryProcessService.newProcess(process);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(result != null){
						JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					//insert configuration into database
					//insert users into database configuration process
					//charge anm file into database
					//charge drl file into database
				}
			}
		});
		button.setBounds(338, 487, 153, 37);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI", Font.BOLD, 18));
		button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		button.setBackground(new Color(244, 164, 96));
		getContentPane().add(button);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 871, 471);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tabbedPane.setBorder(null);
		getContentPane().add(tabbedPane);
		
		JPanel process = new JPanel();
		process.setBackground(Color.WHITE);
		process.setFont(new Font("Segoe UI", Font.BOLD, 20));
		process.setEnabled(false);
		process.setBorder(null);
		tabbedPane.addTab("Proceso", new ImageIcon(NewProcessView.class.getResource("/imgs/process.png")), process, null);
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		process.setLayout(null);
		
		JLabel lblNombreDelProceso = new JLabel("Nombre del proceso:");
		lblNombreDelProceso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelProceso.setForeground(new Color(47, 46, 65));
		lblNombreDelProceso.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblNombreDelProceso.setBounds(26, 33, 248, 37);
		process.add(lblNombreDelProceso);
		
		processName = new JTextField();
		processName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		processName.setColumns(10);
		processName.setBounds(289, 37, 471, 29);
		process.add(processName);
		
		JLabel lblImagenDelProceso = new JLabel("*Imagen del proceso:");
		lblImagenDelProceso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImagenDelProceso.setForeground(new Color(47, 46, 65));
		lblImagenDelProceso.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblImagenDelProceso.setBounds(87, 131, 233, 37);
		process.add(lblImagenDelProceso);
		
		JLabel lblFicheroDeExtensin = new JLabel("Fichero de variables (anm):");
		lblFicheroDeExtensin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFicheroDeExtensin.setForeground(new Color(47, 46, 65));
		lblFicheroDeExtensin.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblFicheroDeExtensin.setBounds(15, 205, 305, 37);
		process.add(lblFicheroDeExtensin);
		
		JLabel lblFicheroDeExtensin_1 = new JLabel("Fichero de reglas (drl):");
		lblFicheroDeExtensin_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFicheroDeExtensin_1.setForeground(new Color(47, 46, 65));
		lblFicheroDeExtensin_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblFicheroDeExtensin_1.setBounds(42, 281, 277, 37);
		process.add(lblFicheroDeExtensin_1);
		
		image = new JTextField();
		image.setBackground(Color.WHITE);
		image.setEditable(false);
		image.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		image.setColumns(10);
		image.setBounds(335, 135, 359, 29);
		process.add(image);
		
		anm = new JTextField();
		lblFicheroDeExtensin.setLabelFor(anm);
		anm.setBackground(Color.WHITE);
		anm.setEditable(false);
		anm.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		anm.setColumns(10);
		anm.setBounds(335, 209, 359, 29);
		process.add(anm);
		
		drl = new JTextField();
		drl.setBackground(Color.WHITE);
		drl.setEditable(false);
		drl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		drl.setColumns(10);
		drl.setBounds(335, 285, 359, 29);
		process.add(drl);
		
		imageButt = new JButton("");
		imageButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(image.getText().isEmpty()){
					JFileChooser chooser = new JFileChooser();
					chooser.setAcceptAllFileFilterUsed(false);
					chooser.setFileFilter( new FileNameExtensionFilter("Archivo de Imagen","jpg","png"));
					int result = chooser.showOpenDialog(null);

					if (result == JFileChooser.APPROVE_OPTION){
						image.setText(chooser.getSelectedFile().getName());
						imageRoute = chooser.getSelectedFile().getAbsolutePath();
						imageButt.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/trash.png")));
					}
				}else{
					image.setText("");
					imageRoute = null;
					imageButt.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/openFolder.png")));
				}
			}
		});
		imageButt.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/openFolder.png")));
		imageButt.setForeground(Color.WHITE);
		imageButt.setFont(new Font("Segoe UI", Font.BOLD, 18));
		imageButt.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		imageButt.setBackground(Color.WHITE);
		imageButt.setBounds(709, 120, 51, 48);
		process.add(imageButt);
		
		button_2 = new JButton();
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(anm.getText().isEmpty()){
					JFileChooser chooser = new JFileChooser();
					chooser.setAcceptAllFileFilterUsed(false);
					chooser.setFileFilter( new FileNameExtensionFilter("Archivo .anm","ANM"));
					int result = chooser.showOpenDialog(null);

					if (result == JFileChooser.APPROVE_OPTION){
						anm.setText(chooser.getSelectedFile().getName());
						anmRoute = chooser.getSelectedFile().getAbsolutePath();
						button_2.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/trash.png")));
					}
				}else{
					anm.setText("");
					anmRoute = null;
					button_2.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/openFolder.png")));
				}
			}
		});
		button_2.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/openFolder.png")));
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		button_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(709, 194, 51, 48);
		process.add(button_2);
		
		button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(drl.getText().isEmpty()){
					JFileChooser chooser = new JFileChooser();
					chooser.setAcceptAllFileFilterUsed(false);
					chooser.setFileFilter( new FileNameExtensionFilter("Archivo .drl","DRL"));
					int result = chooser.showOpenDialog(null);

					if (result == JFileChooser.APPROVE_OPTION){
						drl.setText(chooser.getSelectedFile().getName());
						drlRoute = chooser.getSelectedFile().getAbsolutePath();
						button_1.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/trash.png")));
					}
				}else{
					drl.setText("");
					drlRoute = null;
					button_1.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/openFolder.png")));
				}
			}
		});
		button_1.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/openFolder.png")));
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		button_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(709, 269, 51, 48);
		process.add(button_1);
		
		JPanel config = new JPanel();
		config.setBackground(Color.WHITE);
		config.setFont(new Font("Segoe UI", Font.BOLD, 20));
		config.setEnabled(false);
		config.setBorder(null);
		tabbedPane.addTab("Configuraci\u00F3n", new ImageIcon(NewProcessView.class.getResource("/imgs/config.png")), config, null);
		tabbedPane.setBackgroundAt(1, Color.WHITE);
		config.setLayout(null);
		
		lblUsuarios = new JLabel("Usuarios no autorizados:");
		lblUsuarios.setEnabled(false);
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setForeground(new Color(47, 46, 65));
		lblUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblUsuarios.setBounds(36, 132, 302, 37);
		config.add(lblUsuarios);
		
		lblUsuariosAutorizados = new JLabel("Usuarios autorizados:");
		lblUsuariosAutorizados.setEnabled(false);
		lblUsuariosAutorizados.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuariosAutorizados.setForeground(new Color(47, 46, 65));
		lblUsuariosAutorizados.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblUsuariosAutorizados.setBounds(488, 132, 302, 37);
		config.add(lblUsuariosAutorizados);
		
		noAut = new JList<String>(new AbstractListModel<String>(){	
			private static final long serialVersionUID = 599156348656687464L;
			
			public int getSize() {
				return usersNA.size(); 
			}

			public String getElementAt(int i) {
				return usersNA.get(i);
			}

		});
		noAut.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		noAut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(noAut.isSelectionEmpty()){
					right.setEnabled(false);
				}else{
					right.setEnabled(true);
				}
			}
		});
		noAut.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		noAut.setEnabled(false);
		noAut.setBounds(46, 170, 284, 233);
		config.add(noAut);
		
		aut = new JList<String>(new AbstractListModel<String>(){		
			private static final long serialVersionUID = 59915636566874624L;
			
			public int getSize() {
				return usersA.size(); 
			}

			public String getElementAt(int i) {
				return usersA.get(i);
			}

		});
		aut.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		aut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(aut.isSelectionEmpty()){
					left.setEnabled(false);
				}else{
					left.setEnabled(true);
				}
			}
		});
		aut.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		aut.setEnabled(false);
		aut.setBounds(488, 170, 302, 233);
		config.add(aut);
		
		right = new JButton("");
		right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizar(0);
			}
		});
		right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(right.isEnabled())
					right.setBackground(new Color(239, 196, 159));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(right.isEnabled())
					right.setBackground(Color.WHITE);
			}
		});
		right.setEnabled(false);
		right.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/rightArrow.png")));
		right.setForeground(Color.WHITE);
		right.setFont(new Font("Segoe UI", Font.BOLD, 18));
		right.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		right.setBackground(Color.WHITE);
		right.setBounds(385, 211, 51, 48);
		config.add(right);
		
		left = new JButton("");
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizar(1);
			}
		});
		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(left.isEnabled())
					left.setBackground(new Color(239, 196, 159));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(left.isEnabled())
					left.setBackground(Color.WHITE);
			}
		});
		left.setEnabled(false);
		left.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/leftArrow.png")));
		left.setForeground(Color.WHITE);
		left.setFont(new Font("Segoe UI", Font.BOLD, 18));
		left.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		left.setBackground(Color.WHITE);
		left.setBounds(385, 302, 51, 48);
		config.add(left);
		
		rdbtnTodosLosOperarios = new JRadioButton("Todos los operarios tienen acceso al entrenamiento:    ");
		rdbtnTodosLosOperarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!rdbtnTodosLosOperarios.isSelected()){
					lblUsuarios.setEnabled(true);
					lblUsuariosAutorizados.setEnabled(true);
					aut.setEnabled(true);
					noAut.setEnabled(true);
				}else{
					lblUsuarios.setEnabled(false);
					lblUsuariosAutorizados.setEnabled(false);
					aut.setEnabled(false);
					noAut.setEnabled(false);
				}
			}
		});
		rdbtnTodosLosOperarios.setSelected(true);
		rdbtnTodosLosOperarios.setForeground(new Color(47, 46, 65));
		rdbtnTodosLosOperarios.setHorizontalTextPosition(SwingConstants.LEFT);
		rdbtnTodosLosOperarios.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnTodosLosOperarios.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnTodosLosOperarios.setBackground(Color.WHITE);
		rdbtnTodosLosOperarios.setBounds(99, 78, 615, 29);
		config.add(rdbtnTodosLosOperarios);
		
		JLabel lblCantidadDeIntentos = new JLabel("Cantidad de veces que se puede realizar el entrenamiento:");
		lblCantidadDeIntentos.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadDeIntentos.setForeground(new Color(47, 46, 65));
		lblCantidadDeIntentos.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblCantidadDeIntentos.setBounds(36, 29, 621, 37);
		config.add(lblCantidadDeIntentos);
		
		cantEnt = new JSpinner();
		cantEnt.setModel(new SpinnerNumberModel(5, 1, 20, 1));
		cantEnt.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cantEnt.setBounds(655, 35, 67, 26);
		config.add(cantEnt);
		
		JPanel ent = new JPanel();
		ent.setEnabled(false);
		ent.setBackground(Color.WHITE);
		ent.setFont(new Font("Segoe UI", Font.BOLD, 20));
		ent.setBorder(null);
		tabbedPane.addTab("Entrenamiento", new ImageIcon(NewProcessView.class.getResource("/imgs/icons8_3Quiz_16.png")), ent, null);
		ent.setLayout(null);
		
		JLabel lblCantidadDeIntentos_1 = new JLabel("Cantidad de intentos que se deben aprobar:");
		lblCantidadDeIntentos_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadDeIntentos_1.setForeground(new Color(47, 46, 65));
		lblCantidadDeIntentos_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblCantidadDeIntentos_1.setBounds(94, 47, 464, 37);
		ent.add(lblCantidadDeIntentos_1);
		
		cantAprov = new JSpinner();
		cantAprov.setModel(new SpinnerNumberModel(1, 1, 20, 1));
		cantAprov.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		cantAprov.setBounds(573, 53, 67, 26);
		ent.add(cantAprov);
		
		JRadioButton rdbtnSeDebenAprobar = new JRadioButton("Los intentos deben aprobarse consecutivamente:     ");
		rdbtnSeDebenAprobar.setHorizontalTextPosition(SwingConstants.LEFT);
		rdbtnSeDebenAprobar.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnSeDebenAprobar.setForeground(new Color(47, 46, 65));
		rdbtnSeDebenAprobar.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnSeDebenAprobar.setBackground(Color.WHITE);
		rdbtnSeDebenAprobar.setBounds(41, 107, 625, 29);
		ent.add(rdbtnSeDebenAprobar);
		
		JLabel lblTipoDePreguntas = new JLabel("Pregunta para las variables:");
		lblTipoDePreguntas.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoDePreguntas.setForeground(new Color(47, 46, 65));
		lblTipoDePreguntas.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTipoDePreguntas.setBounds(130, 187, 303, 37);
		ent.add(lblTipoDePreguntas);
		
		JLabel lblTipoDePreguntas_1 = new JLabel("Pregunta para las causas:");
		lblTipoDePreguntas_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoDePreguntas_1.setForeground(new Color(47, 46, 65));
		lblTipoDePreguntas_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTipoDePreguntas_1.setBounds(155, 249, 278, 37);
		ent.add(lblTipoDePreguntas_1);
		
		JLabel lblTipoDePregunta = new JLabel("Pregunta para las recomendaciones:");
		lblTipoDePregunta.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoDePregunta.setForeground(new Color(47, 46, 65));
		lblTipoDePregunta.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTipoDePregunta.setBounds(41, 314, 390, 37);
		ent.add(lblTipoDePregunta);
		
		var = new JComboBox<String>();
		var.addItem(QuestionsTypes.Verdadero_o_falso.toString().replace("_", " "));
		var.addItem(QuestionsTypes.Completar_espacios_en_blanco.toString().replace("_", " "));
		var.addItem(QuestionsTypes.Selección_múltiple.toString().replace("_", " "));
		var.setBackground(Color.WHITE);
		var.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		var.setBounds(431, 188, 358, 34);
		ent.add(var);
		
		cause = new JComboBox<String>();
		cause.addItem(QuestionsTypes.Completar_espacios_en_blanco.toString().replace("_", " "));
		cause.addItem(QuestionsTypes.Verdadero_o_falso.toString().replace("_", " "));
		cause.addItem(QuestionsTypes.Enlazar.toString());
		cause.addItem(QuestionsTypes.Selección_múltiple.toString().replace("_", " "));
		cause.setBackground(Color.WHITE);
		cause.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		cause.setBounds(431, 250, 358, 34);
		ent.add(cause);
		
		rec = new JComboBox<String>();
		rec.addItem(QuestionsTypes.Completar_espacios_en_blanco.toString().replace("_", " "));
		rec.addItem(QuestionsTypes.Verdadero_o_falso.toString().replace("_", " "));
		rec.addItem(QuestionsTypes.Enlazar.toString());
		rec.addItem(QuestionsTypes.Selección_múltiple.toString().replace("_", " "));
		rec.setBackground(Color.WHITE);
		rec.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		rec.setBounds(431, 315, 358, 34);
		ent.add(rec);
		tabbedPane.setBackgroundAt(2, Color.WHITE);
		setTitle("Nuevo proceso");
		setResizable(false);
		setBounds(100, 100, 873, 583);
	}
	
	public boolean validation(){
		boolean validate = false;
		
		if(processName.getText().replace(" ", "").isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe escribir el nombre del proceso", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(anm.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe seleccionar un fichero de extensión .anm", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(drl.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe seleccionar un fichero de extensión .drl", "Error", JOptionPane.ERROR_MESSAGE);
		}else if((Integer)cantEnt.getValue() < (Integer)cantAprov.getValue()){
			JOptionPane.showMessageDialog(null, "La cantidad de entrenamientos aprobados no puede ser mayor a la cantidad total", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return validate;
	}
	
	public ArrayList<String> fillUser() throws SQLException{
		ArrayList<String> names = new ArrayList<>();
		@SuppressWarnings("unchecked")
		ArrayList<User> usersL = (ArrayList<User>) UserService.getUsers();

		for(int i=0; i<usersL.size(); i++){
			if(usersL.get(i).getUser_rol().equals(RolesTypes.Operario) && usersL.get(i).getUser_area() == userG.getUser_area()){
				names.add(usersL.get(i).getUser_name());
			}
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
			right.setEnabled(false);
			left.setEnabled(false);
			left.setBackground(new Color(255, 255, 201));
			right.setBackground(new Color(255, 255, 201));
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
			right.setEnabled(false);
			left.setEnabled(false);
			left.setBackground(new Color(255, 255, 201));
			right.setBackground(new Color(255, 255, 201));
		}
	}
}
