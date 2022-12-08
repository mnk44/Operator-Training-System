package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.ImageIcon;

import classes.Area;
import classes.ProcessConfiguration;
import classes.TrainingPrepare;
import classes.User;
import classes.Process;
import extras.Encrypting;
import extras.PrepareProcess;
import services.AreaService;
import services.FileService;
import services.ProcessService;
import services.UserService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JPanel;

import java.awt.event.WindowStateListener;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PrincipalView {

	public static JFrame frame;
	private JMenuItem changePass;

	//datas
	User user_active = null;
	ArrayList<Area> areasList = AreaService.getAreas();
	ArrayList<User> usersList = UserService.getUsers();
	static ArrayList<Process> processList = new ArrayList<>();
	static ArrayList<ProcessConfiguration> configurationList = new ArrayList<>();
	static ArrayList<Integer> ids = new ArrayList<>();
	TrainingPrepare trainer = null;

	JPanel panel = new JPanel();
	private JLabel title;
	private JLabel title1;
	private JLabel title2;
	private JLabel title3;
	private JMenuItem processMana;
	private JMenuItem userMana;
	private JMenuItem areaMana;
	private JMenu options;
	private JMenuItem train;

	public PrincipalView(User uss) throws SQLException {
		user_active = uss;
		initialize();
	}

	private void initialize() throws SQLException {		
		frame = new JFrame();
		frame.setMaximumSize(new Dimension(901, 514));
		frame.setMinimumSize(new Dimension(901, 514));
		frame.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg0) {
				adjustPanel(panel);
			}
		});
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/images/logo.png")));
		frame.setBackground(new Color(173, 216, 230));
		frame.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 14));
		frame.setForeground(Color.BLACK);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);

		title3 = new JLabel("");
		title3.setIcon(new ImageIcon(PrincipalView.class.getResource("/images/quimica.png")));
		title3.setBounds(43, 26, 299, 368);
		frame.getContentPane().add(title3);

		title = new JLabel("SECPROIT");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(new Color(255, 113, 19));
		title.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 32));
		title.setBounds(377, 107, 445, 49);
		frame.getContentPane().add(title);

		title1 = new JLabel("Sistema Experto para el Control");
		title1.setHorizontalAlignment(SwingConstants.CENTER);
		title1.setForeground(new Color(99, 68, 55));
		title1.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 26));
		title1.setBounds(335, 156, 513, 49);
		frame.getContentPane().add(title1);

		title2 = new JLabel("de Procesos Qu\u00EDmicos");
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setForeground(new Color(99, 68, 55));
		title2.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 26));
		title2.setBounds(335, 196, 513, 49);
		frame.getContentPane().add(title2);
		
		
		frame.setTitle("Sistema de entrenamiento SECPROIT");
		frame.setBounds(100, 100, 901, 514);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "¿Desea salir del sistema?", "Salir", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option == 0){
					System.exit(0);
				}
			}
		});

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		menuBar.setBackground(new Color(245, 102, 23));
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Usuario");
		mnNewMenu.setForeground(new Color(255, 255, 255));
		mnNewMenu.setFont(new Font("Dubai", Font.BOLD, 20));
		menuBar.add(mnNewMenu);

		changePass = new JMenuItem("Cambiar la contrase\u00F1a");
		changePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String new_pass = JOptionPane.showInputDialog(null, "Escriba la nueva contraseña:");
				if(new_pass != null){
					while(new_pass.isEmpty()){
						JOptionPane.showMessageDialog(null, "Debe escribir una nueva contraseña", "Error", JOptionPane.ERROR_MESSAGE);
						new_pass = JOptionPane.showInputDialog(null, "Escriba la nueva contraseña:");
					}
					String result = null;
					try {
						result = UserService.changePassword(user_active.getUser_id(), Encrypting.getEncript(new_pass), user_active.getUser_nick(), user_active.getUser_nick(), new Timestamp(Calendar.getInstance().getTime().getTime()));
					} catch (UnsupportedEncodingException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
					}
					if(result != null){
						JOptionPane.showMessageDialog(null, result, "Error", JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Acción completada", "", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		changePass.setIcon(new ImageIcon(PrincipalView.class.getResource("/images/change-pass.png")));
		changePass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		changePass.setForeground(Color.WHITE);
		changePass.setBackground(new Color(255, 113, 19));
		changePass.setFont(new Font("Dubai", Font.BOLD, 19));
		mnNewMenu.add(changePass);

		JMenuItem mntmInformacinPersonal = new JMenuItem("Informaci\u00F3n personal");
		mntmInformacinPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(panel);
				frame.remove(title);
				frame.remove(title1);
				frame.remove(title2);
				frame.remove(title3);
				panel = new JPanel();
				try {
					panel = new PersonalInfPanel(user_active);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
				}
				frame.getContentPane().add(panel);
				adjustPanel(panel);
			}
		});
		mntmInformacinPersonal.setIcon(new ImageIcon(PrincipalView.class.getResource("/images/personal-info.png")));
		mntmInformacinPersonal.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_MASK));
		mntmInformacinPersonal.setForeground(Color.WHITE);
		mntmInformacinPersonal.setFont(new Font("Dubai", Font.BOLD, 19));
		mntmInformacinPersonal.setBackground(new Color(255, 113, 19));
		mnNewMenu.add(mntmInformacinPersonal);

		JMenuItem mntmCerrarSesin = new JMenuItem("Cerrar sesi\u00F3n");
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginView log = new LoginView();
				log.setLocationRelativeTo(frame);
				log.setVisible(true);
				frame.setVisible(false);
			}
		});
		mntmCerrarSesin.setIcon(new ImageIcon(PrincipalView.class.getResource("/images/exit.png")));
		mntmCerrarSesin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
		mntmCerrarSesin.setForeground(Color.WHITE);
		mntmCerrarSesin.setFont(new Font("Dubai", Font.BOLD, 19));
		mntmCerrarSesin.setBackground(new Color(255, 113, 19));
		mnNewMenu.add(mntmCerrarSesin);

		options = new JMenu("Opciones");
		options.setForeground(Color.WHITE);
		options.setFont(new Font("Dubai", Font.BOLD, 20));
		menuBar.add(options);

		areaMana = new JMenuItem("Gesti\u00F3n de \u00E1reas");
		areaMana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(panel);
				frame.remove(title);
				frame.remove(title1);
				frame.remove(title2);
				frame.remove(title3);
				panel = new JPanel();
				try {
					panel = new AreaManagementPanel(user_active, areasList, usersList);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
				}
				frame.getContentPane().add(panel);
				adjustPanel(panel);
			}
		});
		areaMana.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
		areaMana.setIcon(new ImageIcon(PrincipalView.class.getResource("/images/areas.png")));
		areaMana.setForeground(Color.WHITE);
		areaMana.setFont(new Font("Dubai", Font.BOLD, 19));
		areaMana.setBackground(new Color(255, 113, 19));
		options.add(areaMana);

		userMana = new JMenuItem("Gesti\u00F3n de usuarios");
		userMana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(panel);
				frame.remove(title);
				frame.remove(title1);
				frame.remove(title2);
				frame.remove(title3);
				panel = new JPanel();
				try {
					panel = new UserManagementPanel(user_active, usersList, areasList);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.getContentPane().add(panel);
				adjustPanel(panel);
			}
		});
		userMana.setIcon(new ImageIcon(PrincipalView.class.getResource("/images/user_management.png")));
		userMana.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_MASK));
		userMana.setForeground(Color.WHITE);
		userMana.setFont(new Font("Dubai", Font.BOLD, 19));
		userMana.setBackground(new Color(255, 113, 19));
		options.add(userMana);

		processMana = new JMenuItem("Gesti\u00F3n de procesos");
		processMana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(panel);
				frame.remove(title);
				frame.remove(title1);
				frame.remove(title2);
				frame.remove(title3);
				panel = new JPanel();
				ArrayList<User> op = getOperators();
				try {
					panel = new ProcessManagementPanel(op, user_active, processList, configurationList);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.getContentPane().add(panel);
				adjustPanel(panel);
			}
		});
		processMana.setIcon(new ImageIcon(PrincipalView.class.getResource("/images/procesos.png")));
		processMana.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		processMana.setForeground(Color.WHITE);
		processMana.setFont(new Font("Dubai", Font.BOLD, 19));
		processMana.setBackground(new Color(255, 113, 19));
		options.add(processMana);
		
		train = new JMenuItem("Iniciar entrenamiento");
		train.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.remove(panel);
				frame.remove(title);
				frame.remove(title1);
				frame.remove(title2);
				frame.remove(title3);
				title.setVisible(false);
				title1.setVisible(false);
				title2.setVisible(false);
				title3.setVisible(false);
				panel = new JPanel();
				try {
					panel = new GeneralTrainingPanel(trainer, user_active);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.getContentPane().add(panel);
				int x = (frame.getWidth()-panel.getWidth())/2;
				int y = (frame.getHeight()- panel.getHeight() - frame.getInsets().top - frame.getInsets().bottom)/4;
				panel.setLocation(x, y);
			}
		});
		train.setIcon(new ImageIcon(PrincipalView.class.getResource("/images/icons8_Test_Passed_16.png")));
		train.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_MASK));
		train.setForeground(Color.WHITE);
		train.setFont(new Font("Dubai", Font.BOLD, 19));
		train.setBackground(new Color(255, 113, 19));
		options.add(train);

		if(user_active.getUser_rol() == 1){
			processMana.setVisible(false);
			train.setVisible(false);
		}else if(user_active.getUser_rol() == 2){
			//charge info
			ids = FileService.getIds();
			processList = ProcessService.searchArea(user_active.getUser_area());
			configurationList = ProcessService.getProcessConf();
			
			//show functions
			userMana.setVisible(false);
			areaMana.setVisible(false);
			train.setVisible(false);
		}else{
			//charge info
			processList = ProcessService.searchArea(user_active.getUser_area());
			configurationList = ProcessService.getProcessConf();
			
			trainer = PrepareProcess.prepareTraining(user_active, processList, configurationList);	
			
			//hide and shows functions
			userMana.setVisible(false);
			areaMana.setVisible(false);
			processMana.setVisible(false);
		}
	}

	public ArrayList<User> getOperators(){
		ArrayList<User> operators = new ArrayList<>();

		for(int i=0; i<usersList.size(); i++){
			if(usersList.get(i).getUser_rol()==3 && usersList.get(i).getUser_area()==user_active.getUser_area()
					&& usersList.get(i).isUser_active()){
				operators.add(usersList.get(i));
			}
		}

		return operators;
	}

	public static void changeProcess(Process p, int opc){
		if(opc == 1){
			processList.add(p);
		}else{

		}
	}

	public static ArrayList<Process> getProcess(){
		return processList;
	}

	public static void changeConfig(ProcessConfiguration p, int opc){
		if(opc == 1){
			configurationList.add(p);
		}else{

		}
	}
	
	public void adjustPanel(JPanel panel){
		int x = (frame.getWidth()-panel.getWidth())/2;
		if(frame.getExtendedState() == JFrame.MAXIMIZED_BOTH){
			int y = (frame.getHeight()- panel.getHeight() - frame.getInsets().top - frame.getInsets().bottom)/4;
			panel.setLocation(x, y);
		}else if(frame.getExtendedState() == JFrame.NORMAL){
			panel.setLocation(x, 0);
		}
	}
	
	public static void close(){
		frame.setVisible(false);
	}
}
