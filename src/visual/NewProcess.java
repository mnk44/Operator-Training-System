package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTabbedPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import utils.VariableType;
import contentClass.Variable;
import drools.EvaluateProcessDrool;

public class NewProcess extends JDialog {

	private static final long serialVersionUID = 5991563486566874624L;
	private JTabbedPane tabbedPane;
	private JTextField textField;
	private JTextField imageField;
	private JButton findImage;
	private JTextField anmName;
	private JTextField drlName;
	private JButton findAnm;
	private JButton findDrl;
	
	String imgRoute = null;
	String anmRoute = null;
	String drlRoute = null;
	
	private JButton btnEliminarImagen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewProcess dialog = new NewProcess(-1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewProcess(final int option) {
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
		tabbedPane.setFont(new Font("Arial", Font.BOLD, 16));
		tabbedPane.setBounds(384, 380, -383, -350);
		tabbedPane.setBorder(null);
		
		JPanel process = new JPanel();
		process.setBorder(null);
		process.setBackground(Color.WHITE);
        tabbedPane.addTab("Proceso", process);
        process.setLayout(null);
        
        JLabel lblNombreDelProceso = new JLabel("Nombre del Proceso:");
        lblNombreDelProceso.setBounds(15, 32, 179, 20);
        lblNombreDelProceso.setFont(new Font("Arial", Font.BOLD, 16));
        process.add(lblNombreDelProceso);
        
        textField = new JTextField();
        textField.setBounds(181, 30, 231, 26);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setColumns(10);
        process.add(textField);
        
        JLabel lblFechaDeCreacin = new JLabel("Fecha de Creaci\u00F3n:");
        lblFechaDeCreacin.setBounds(15, 76, 162, 20);
        lblFechaDeCreacin.setHorizontalAlignment(SwingConstants.TRAILING);
        lblFechaDeCreacin.setFont(new Font("Arial", Font.BOLD, 16));
        process.add(lblFechaDeCreacin);
        
        JLabel lblNombreDelProceso_1 = new JLabel();
        lblNombreDelProceso_1.setBounds(213, 76, 179, 20);
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
        
        JButton btnSiguiente = new JButton("Cancelar");
        btnSiguiente.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Delete_16.png")));
        btnSiguiente.setFont(new Font("Segoe UI", Font.BOLD, 17));
        btnSiguiente.setBorder(new LineBorder(new Color(255, 186, 74), 2));
        btnSiguiente.setBackground(new Color(255, 255, 201));
        btnSiguiente.setBounds(493, 399, 155, 38);
        process.add(btnSiguiente);
        
        JButton btnGuardar = new JButton("Aceptar");
        btnGuardar.setIcon(new ImageIcon(NewProcess.class.getResource("/img/icons8_Checkmark_16.png")));
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 17));
        btnGuardar.setBorder(new LineBorder(new Color(255, 186, 74), 2));
        btnGuardar.setBackground(new Color(255, 255, 201));
        btnGuardar.setBounds(26, 399, 155, 38);
        process.add(btnGuardar);
        
        btnEliminarImagen = new JButton("Eliminar Imagen");
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
        btnEliminarImagen.setBounds(462, 22, 192, 38);
        process.add(btnEliminarImagen);
 
        JPanel config = new JPanel();
        config.setBorder(null);
        config.setBackground(Color.WHITE);
        tabbedPane.addTab("Entrenamiento", config);
        
        JPanel users = new JPanel();
        users.setBorder(null);
        users.setBackground(Color.WHITE);
        tabbedPane.addTab("Usuarios", users);
        
        getContentPane().add(tabbedPane);
	}
}
