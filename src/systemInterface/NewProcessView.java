package systemInterface;

import java.awt.Color;
import java.awt.Dimension;
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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JComboBox;

public class NewProcessView extends JDialog {

	private static final long serialVersionUID = -3463338119647839725L;
	private JTextField processName;
	private JTextField anm;
	private JTextField drl;
	private JTextField image;
	
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
	private JSpinner shots;
	private JSpinner aprovShots;

	public static void main(String[] args) {
		try {
			NewProcessView dialog = new NewProcessView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public NewProcessView() throws SQLException {
		usersNA = fillUser();
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/imgs/logo.png")));
		setBackground(new Color(173, 216, 230));
		setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		setForeground(Color.BLACK);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("Aceptar");
		button.setBounds(478, 583, 153, 37);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI", Font.BOLD, 18));
		button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		button.setBackground(new Color(244, 164, 96));
		getContentPane().add(button);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1074, 567);
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
		lblNombreDelProceso.setBounds(147, 81, 248, 37);
		process.add(lblNombreDelProceso);
		
		processName = new JTextField();
		processName.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		processName.setColumns(10);
		processName.setBounds(399, 85, 452, 29);
		process.add(processName);
		
		JLabel lblImagenDelProceso = new JLabel("*Imagen del proceso:");
		lblImagenDelProceso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImagenDelProceso.setForeground(new Color(47, 46, 65));
		lblImagenDelProceso.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblImagenDelProceso.setBounds(162, 161, 233, 37);
		process.add(lblImagenDelProceso);
		
		JLabel lblFicheroDeExtensin = new JLabel("Fichero de extensi\u00F3n .anm:");
		lblFicheroDeExtensin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFicheroDeExtensin.setForeground(new Color(47, 46, 65));
		lblFicheroDeExtensin.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblFicheroDeExtensin.setBounds(105, 242, 290, 37);
		process.add(lblFicheroDeExtensin);
		
		JLabel lblFicheroDeExtensin_1 = new JLabel("Fichero de extensi\u00F3n .drl:");
		lblFicheroDeExtensin_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFicheroDeExtensin_1.setForeground(new Color(47, 46, 65));
		lblFicheroDeExtensin_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblFicheroDeExtensin_1.setBounds(118, 323, 277, 37);
		process.add(lblFicheroDeExtensin_1);
		
		image = new JTextField();
		image.setBackground(Color.WHITE);
		image.setEditable(false);
		image.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		image.setColumns(10);
		image.setBounds(399, 165, 359, 29);
		process.add(image);
		
		anm = new JTextField();
		anm.setBackground(Color.WHITE);
		anm.setEditable(false);
		anm.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		anm.setColumns(10);
		anm.setBounds(399, 250, 359, 29);
		process.add(anm);
		
		drl = new JTextField();
		drl.setBackground(Color.WHITE);
		drl.setEditable(false);
		drl.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		drl.setColumns(10);
		drl.setBounds(399, 331, 359, 29);
		process.add(drl);
		
		final JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(image.getText().isEmpty()){
					JFileChooser chooser = new JFileChooser();
					chooser.setAcceptAllFileFilterUsed(false);
					chooser.setFileFilter( new FileNameExtensionFilter("Archivo de Imagen","jpg","png"));
					int result = chooser.showOpenDialog(null);

					if (result == JFileChooser.APPROVE_OPTION){
						image.setText(chooser.getSelectedFile().getName());
						imageRoute = chooser.getSelectedFile().getAbsolutePath();
						button_1.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/trash.png")));
					}
				}else{
					image.setText("");
					imageRoute = null;
					button_1.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/openFolder.png")));
				}
			}
		});
		button_1.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/openFolder.png")));
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		button_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(766, 150, 51, 48);
		process.add(button_1);
		
		JButton button_2 = new JButton("");
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
						button_1.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/trash.png")));
					}
				}else{
					anm.setText("");
					anmRoute = null;
					button_1.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/openFolder.png")));
				}
			}
		});
		button_2.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/openFolder.png")));
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		button_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(766, 242, 51, 48);
		process.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
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
		button_3.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/openFolder.png")));
		button_3.setForeground(Color.WHITE);
		button_3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		button_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		button_3.setBackground(Color.WHITE);
		button_3.setBounds(766, 323, 51, 48);
		process.add(button_3);
		
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
		lblUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblUsuarios.setBounds(124, 161, 302, 37);
		config.add(lblUsuarios);
		
		lblUsuariosAutorizados = new JLabel("Usuarios autorizados:");
		lblUsuariosAutorizados.setEnabled(false);
		lblUsuariosAutorizados.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuariosAutorizados.setForeground(new Color(47, 46, 65));
		lblUsuariosAutorizados.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblUsuariosAutorizados.setBounds(631, 161, 302, 37);
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
		noAut.setBounds(124, 214, 302, 296);
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
		aut.setBounds(631, 214, 302, 296);
		config.add(aut);
		
		right = new JButton("");
		right.setEnabled(false);
		right.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/rightArrow.png")));
		right.setForeground(Color.WHITE);
		right.setFont(new Font("Segoe UI", Font.BOLD, 18));
		right.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		right.setBackground(Color.WHITE);
		right.setBounds(502, 288, 51, 48);
		config.add(right);
		
		left = new JButton("");
		left.setEnabled(false);
		left.setIcon(new ImageIcon(NewProcessView.class.getResource("/imgs/leftArrow.png")));
		left.setForeground(Color.WHITE);
		left.setFont(new Font("Segoe UI", Font.BOLD, 18));
		left.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		left.setBackground(Color.WHITE);
		left.setBounds(502, 381, 51, 48);
		config.add(left);
		
		rdbtnTodosLosOperarios = new JRadioButton("Todos los operarios tienen acceso al entrenamiento:");
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
		rdbtnTodosLosOperarios.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnTodosLosOperarios.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnTodosLosOperarios.setBackground(Color.WHITE);
		rdbtnTodosLosOperarios.setBounds(124, 103, 809, 29);
		config.add(rdbtnTodosLosOperarios);
		
		JLabel lblCantidadDeIntentos = new JLabel("Cantidad de veces que se puede realizar el entrenamiento:");
		lblCantidadDeIntentos.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadDeIntentos.setForeground(new Color(47, 46, 65));
		lblCantidadDeIntentos.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblCantidadDeIntentos.setBounds(173, 29, 621, 37);
		config.add(lblCantidadDeIntentos);
		
		shots = new JSpinner();
		shots.setModel(new SpinnerNumberModel(5, 1, 20, 1));
		shots.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		shots.setBounds(786, 35, 67, 26);
		config.add(shots);
		
		JPanel ent = new JPanel();
		ent.setEnabled(false);
		ent.setBackground(Color.WHITE);
		ent.setFont(new Font("Segoe UI", Font.BOLD, 20));
		ent.setBorder(null);
		tabbedPane.addTab("Entrenamiento", new ImageIcon(NewProcessView.class.getResource("/imgs/training.png")), ent, null);
		ent.setLayout(null);
		
		JLabel lblCantidadDeIntentos_1 = new JLabel("Cantidad de intentos que se deben aprobar:");
		lblCantidadDeIntentos_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblCantidadDeIntentos_1.setForeground(new Color(47, 46, 65));
		lblCantidadDeIntentos_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblCantidadDeIntentos_1.setBounds(238, 39, 464, 37);
		ent.add(lblCantidadDeIntentos_1);
		
		aprovShots = new JSpinner();
		aprovShots.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		aprovShots.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		aprovShots.setBounds(707, 45, 67, 26);
		ent.add(aprovShots);
		
		JRadioButton rdbtnSeDebenAprobar = new JRadioButton("Intentos aprobados consecutivamente:");
		rdbtnSeDebenAprobar.setHorizontalTextPosition(SwingConstants.LEFT);
		rdbtnSeDebenAprobar.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSeDebenAprobar.setForeground(new Color(47, 46, 65));
		rdbtnSeDebenAprobar.setFont(new Font("Segoe UI", Font.BOLD, 22));
		rdbtnSeDebenAprobar.setBackground(Color.WHITE);
		rdbtnSeDebenAprobar.setBounds(239, 109, 535, 29);
		ent.add(rdbtnSeDebenAprobar);
		
		JLabel lblTipoDePreguntas = new JLabel("Tipo de preguntas para las variables:");
		lblTipoDePreguntas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDePreguntas.setForeground(new Color(47, 46, 65));
		lblTipoDePreguntas.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTipoDePreguntas.setBounds(73, 206, 464, 37);
		ent.add(lblTipoDePreguntas);
		
		JLabel lblTipoDePreguntas_1 = new JLabel("Tipo de preguntas para las causas:");
		lblTipoDePreguntas_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDePreguntas_1.setForeground(new Color(47, 46, 65));
		lblTipoDePreguntas_1.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTipoDePreguntas_1.setBounds(127, 289, 410, 37);
		ent.add(lblTipoDePreguntas_1);
		
		JLabel lblTipoDePregunta = new JLabel("Tipo de pregunta para las recomendaciones:");
		lblTipoDePregunta.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoDePregunta.setForeground(new Color(47, 46, 65));
		lblTipoDePregunta.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTipoDePregunta.setBounds(73, 368, 464, 37);
		ent.add(lblTipoDePregunta);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		comboBox.setBounds(552, 207, 331, 34);
		ent.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		comboBox_1.setBounds(552, 289, 331, 34);
		ent.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		comboBox_2.setBounds(552, 371, 331, 34);
		ent.add(comboBox_2);
		tabbedPane.setBackgroundAt(2, Color.WHITE);
		setTitle("Nuevo proceso");
		setResizable(false);
		setBounds(100, 100, 1080, 676);
	}
	
	public boolean validation(){
		boolean validate = false;
		
		if(processName.getText().replace(" ", "").isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe escribir el nombre del proceso", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(anm.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe seleccionar un fichero de extensión .anm", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(drl.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Debe seleccionar un fichero de extensión .drl", "Error", JOptionPane.ERROR_MESSAGE);
		}else if((Integer)shots.getValue() < (Integer)aprovShots.getValue()){
			JOptionPane.showMessageDialog(null, "La cantidad de entrenamientos aprobados no puede ser mayor a la cantidad total", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return validate;
	}
	
	public ArrayList<String> fillUser() throws SQLException{
		ArrayList<String> names = new ArrayList<>();
//		ArrayList<User> user = UserService.getOp(area);
//
//		for(int i=0; i<user.size(); i++){
//			names.add(user.get(i).getName_user());
//		}

		return names;
	}
}
