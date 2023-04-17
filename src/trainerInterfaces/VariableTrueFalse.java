package trainerInterfaces;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

import java.awt.ComponentOrientation;
import java.awt.Cursor;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import trainerLogic.FillTrain;
import trainerLogic.QuestionsVar;
import classes.Process;
import classes.ProcessConfiguration;
import classes.Training;
import classes.User;
import extras.Convert;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VariableTrueFalse {

	public JFrame frmEtapa;
	private JLabel label;

	Timer timer;
	int sec;
	int min;

	private ImageCharge img = null;
	private JButton finish;
	private JLabel label_1;
	private JTextPane txtpnSeleccioneLasVariables;
	private JTextPane var1;
	@SuppressWarnings("rawtypes")
	private JComboBox v1;
	@SuppressWarnings("rawtypes")
	private JComboBox v2;
	@SuppressWarnings("rawtypes")
	private JComboBox v3;
	@SuppressWarnings("rawtypes")
	private JComboBox v4;
	@SuppressWarnings("rawtypes")
	private JComboBox v5;
	@SuppressWarnings("rawtypes")
	private JComboBox v6;
	@SuppressWarnings("rawtypes")
	private JComboBox v7;
	@SuppressWarnings("rawtypes")
	private JComboBox v8;
	@SuppressWarnings("rawtypes")
	private JComboBox v9;
	@SuppressWarnings("rawtypes")
	private JComboBox v10;
	private JTextPane var2;
	private JTextPane var3;
	private JTextPane var4;
	private JTextPane var5;
	private JTextPane var6;
	private JTextPane var7;
	private JTextPane var8;
	private JTextPane var9;
	private JTextPane var10;

	QuestionsVar questions;
	ArrayList<Integer> numbers = new ArrayList<>();
	ArrayList<JTextPane> view = new ArrayList<>();
	ArrayList<String> trueAnswers = new ArrayList<>();
	@SuppressWarnings("rawtypes")
	ArrayList<JComboBox> viewAnswers = new ArrayList<>();

	public VariableTrueFalse(Process p, int timeFinal, User operator, Training train, ProcessConfiguration cantInte) {
		questions = FillTrain.fillQuestionVar(p);
		if(p.getProcess_img() != null){
			try {
				img = new ImageCharge(Convert.toObject(p.getProcess_img()));
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			img.setVisible(true);
		}
		initialize(timeFinal, operator, train, cantInte);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize(final int timeFinal, final User operator, final Training train, final ProcessConfiguration cantInt) {
		frmEtapa = new JFrame();
		frmEtapa.setTitle("Etapa #1: Variables");
		frmEtapa.setResizable(false);
		frmEtapa.setIconImage(Toolkit.getDefaultToolkit().getImage(VariableTrueFalse.class.getResource("/images/icons8_Quiz_32.png")));
		frmEtapa.getContentPane().setBackground(Color.WHITE);
		frmEtapa.getContentPane().setLayout(null);
		frmEtapa.setBackground(Color.WHITE);
		frmEtapa.setBounds(100, 100, 745, 594);
		frmEtapa.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmEtapa.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				JOptionPane.showMessageDialog(null, "Si sale se dará por terminado el entrenamiento", "Salir", JOptionPane.WARNING_MESSAGE);
				finish.doClick();
			}
		});

		label = new JLabel();
		label.setBorder(new LineBorder(new Color(255, 113, 19)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 26));
		label.setBounds(610, 16, 99, 40);
		frmEtapa.getContentPane().add(label);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(25, 59, 699, 431);
		frmEtapa.getContentPane().add(panel);
		panel.setLayout(null);

		txtpnSeleccioneLasVariables = new JTextPane();
		txtpnSeleccioneLasVariables.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtpnSeleccioneLasVariables.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtpnSeleccioneLasVariables.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		txtpnSeleccioneLasVariables.setForeground(new Color(255, 113, 19));
		txtpnSeleccioneLasVariables.setText("Seleccione verdadero o falso si la varible se encuentra fuera de rango:");
		txtpnSeleccioneLasVariables.setEditable(false);
		txtpnSeleccioneLasVariables.setBounds(0, 0, 664, 58);
		panel.add(txtpnSeleccioneLasVariables);

		var1 = new JTextPane();
		var1.setForeground(new Color(99, 68, 55));
		var1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var1.setEditable(false);
		var1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var1.setBounds(80, 61, 619, 29);
		panel.add(var1);
		view.add(var1);

		v1 = new JComboBox();
		v1.setFont(new Font("Corbel", Font.PLAIN, 20));
		v1.setBackground(Color.WHITE);
		v1.setModel(new DefaultComboBoxModel(new String[] {"", "V", "F"}));
		v1.setBounds(25, 64, 48, 26);
		panel.add(v1);
		viewAnswers.add(v1);

		v2 = new JComboBox();
		v2.setModel(new DefaultComboBoxModel(new String[] {"", "V", "F"}));
		v2.setFont(new Font("Corbel", Font.PLAIN, 20));
		v2.setBackground(Color.WHITE);
		v2.setBounds(25, 101, 48, 26);
		panel.add(v2);
		viewAnswers.add(v2);

		var2 = new JTextPane();
		var2.setForeground(new Color(99, 68, 55));
		var2.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var2.setEditable(false);
		var2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var2.setBounds(80, 98, 619, 29);
		panel.add(var2);
		view.add(var2);

		v3 = new JComboBox();
		v3.setModel(new DefaultComboBoxModel(new String[] {"", "V", "F"}));
		v3.setFont(new Font("Corbel", Font.PLAIN, 20));
		v3.setBackground(Color.WHITE);
		v3.setBounds(25, 138, 48, 26);
		panel.add(v3);
		viewAnswers.add(v3);

		v4 = new JComboBox();
		v4.setModel(new DefaultComboBoxModel(new String[] {"", "V", "F"}));
		v4.setFont(new Font("Corbel", Font.PLAIN, 20));
		v4.setBackground(Color.WHITE);
		v4.setBounds(25, 174, 48, 26);
		panel.add(v4);
		viewAnswers.add(v4);

		v5 = new JComboBox();
		v5.setModel(new DefaultComboBoxModel(new String[] {"", "V", "F"}));
		v5.setFont(new Font("Corbel", Font.PLAIN, 20));
		v5.setBackground(Color.WHITE);
		v5.setBounds(25, 211, 48, 26);
		panel.add(v5);
		viewAnswers.add(v5);

		v6 = new JComboBox();
		v6.setModel(new DefaultComboBoxModel(new String[] {"", "V", "F"}));
		v6.setFont(new Font("Corbel", Font.PLAIN, 20));
		v6.setBackground(Color.WHITE);
		v6.setBounds(25, 247, 48, 26);
		panel.add(v6);
		viewAnswers.add(v6);

		v7 = new JComboBox();
		v7.setModel(new DefaultComboBoxModel(new String[] {"", "V", "F"}));
		v7.setFont(new Font("Corbel", Font.PLAIN, 20));
		v7.setBackground(Color.WHITE);
		v7.setBounds(25, 286, 48, 26);
		panel.add(v7);
		viewAnswers.add(v7);

		v8 = new JComboBox();
		v8.setModel(new DefaultComboBoxModel(new String[] {"", "V", "F"}));
		v8.setFont(new Font("Corbel", Font.PLAIN, 20));
		v8.setBackground(Color.WHITE);
		v8.setBounds(25, 324, 48, 26);
		panel.add(v8);
		viewAnswers.add(v8);

		v9 = new JComboBox();
		v9.setModel(new DefaultComboBoxModel(new String[] {"", "V", "F"}));
		v9.setFont(new Font("Corbel", Font.PLAIN, 20));
		v9.setBackground(Color.WHITE);
		v9.setBounds(25, 361, 48, 26);
		panel.add(v9);
		viewAnswers.add(v9);

		v10 = new JComboBox();
		v10.setModel(new DefaultComboBoxModel(new String[] {"", "V", "F"}));
		v10.setFont(new Font("Corbel", Font.PLAIN, 20));
		v10.setBackground(Color.WHITE);
		v10.setBounds(25, 397, 48, 26);
		panel.add(v10);
		viewAnswers.add(v10);

		var3 = new JTextPane();
		var3.setForeground(new Color(99, 68, 55));
		var3.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var3.setEditable(false);
		var3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var3.setBounds(80, 138, 619, 29);
		panel.add(var3);
		view.add(var3);

		var4 = new JTextPane();
		var4.setForeground(new Color(99, 68, 55));
		var4.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var4.setEditable(false);
		var4.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var4.setBounds(80, 174, 619, 29);
		panel.add(var4);
		view.add(var4);

		var5 = new JTextPane();
		var5.setForeground(new Color(99, 68, 55));
		var5.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var5.setEditable(false);
		var5.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var5.setBounds(80, 211, 619, 29);
		panel.add(var5);
		view.add(var5);

		var6 = new JTextPane();
		var6.setForeground(new Color(99, 68, 55));
		var6.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var6.setEditable(false);
		var6.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var6.setBounds(80, 247, 619, 29);
		panel.add(var6);
		view.add(var6);

		var7 = new JTextPane();
		var7.setForeground(new Color(99, 68, 55));
		var7.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var7.setEditable(false);
		var7.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var7.setBounds(80, 286, 619, 29);
		panel.add(var7);
		view.add(var7);

		var8 = new JTextPane();
		var8.setForeground(new Color(99, 68, 55));
		var8.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var8.setEditable(false);
		var8.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var8.setBounds(80, 324, 619, 29);
		panel.add(var8);
		view.add(var8);

		var9 = new JTextPane();
		var9.setForeground(new Color(99, 68, 55));
		var9.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var9.setEditable(false);
		var9.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var9.setBounds(80, 361, 619, 29);
		panel.add(var9);
		view.add(var9);

		var10 = new JTextPane();
		var10.setForeground(new Color(99, 68, 55));
		var10.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var10.setEditable(false);
		var10.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var10.setBounds(80, 397, 619, 29);
		panel.add(var10);
		view.add(var10);

		finish = new JButton("Terminar evaluaci\u00F3n");
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = 0;
				if(sec != 0 && min != 0){
					option = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea terminar el entrenamiento?", "Terminar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				}
				if(option == 0){
					int cant = 0;
					timer.cancel();

					for(int i=0; i<10; i++){
						if(viewAnswers.get(i).getSelectedItem().equals(trueAnswers.get(i))){
							cant = cant + 1;
							viewAnswers.get(i).setForeground(Color.GREEN);
						}else{
							viewAnswers.get(i).setForeground(Color.RED);
						}
					}

					String tiempo = min + "." + sec;
					double t = Double.parseDouble(tiempo);
					
					ResultView rs = new ResultView(cant, 10, t, timeFinal, frmEtapa, operator, train, "variable", cantInt, img);
					rs.setLocationRelativeTo(frmEtapa);
					rs.setVisible(true);
				}
			}
		});
		finish.setIcon(new ImageIcon(VariableTrueFalse.class.getResource("/images/icons8_Finish_Flag_16.png")));
		finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				finish.setBackground(new Color(248, 159, 101));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				finish.setBackground(new Color(255, 113, 19));
			}
		});
		finish.setHorizontalTextPosition(SwingConstants.LEFT);
		finish.setForeground(Color.WHITE);
		finish.setFont(new Font("Segoe UI", Font.BOLD, 18));
		finish.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		finish.setBackground(new Color(255, 113, 19));
		finish.setBounds(248, 506, 236, 35);
		frmEtapa.getContentPane().add(finish);

		label_1 = new JLabel();
		label_1.setIcon(new ImageIcon(VariableTrueFalse.class.getResource("/images/icons8_Time_32.png")));
		label_1.setBounds(577, 16, 32, 36);
		frmEtapa.getContentPane().add(label_1);

		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int minutes = timeFinal - 1;
			int seconds = 59;

			public void run() {
				min = minutes;
				sec = seconds;
				if(Integer.toString(minutes).length() < 2){
					if(Integer.toString(seconds).length() < 2){
						label.setText("0" + minutes + ":" + "0" + seconds);
					}else{
						label.setText("0" + minutes + ":" + seconds);
					}
					if(minutes < 1){
						label.setForeground(Color.RED);
					}
				}else{
					if(Integer.toString(seconds).length() < 2){
						label.setText(minutes + ":" + "0" + seconds);
					}else{
						label.setText(minutes + ":" + seconds);
					}
				}
				seconds = seconds - 1;
				if (seconds < 0) {
					minutes = minutes - 1;
					if(minutes < 0){
						timer.cancel();
						frmEtapa.getToolkit().beep();
						JOptionPane.showMessageDialog(null, "Se ha terminado el tiempo", "¡Tiempo!", JOptionPane.INFORMATION_MESSAGE);
						finish.doClick();
					}else{
						seconds = 59;
					}
				}
			}
		}, 0, 1000);

		chargeData();
	}

	private void chargeData(){
		for(int i=0; i<10; i++){
			numbers.add(i);
		}
		Collections.shuffle(numbers);

		for(int i=0; i<10; i++){
			if(questions.getVariables().get(i).getVar_type().equals("Continua")){
				view.get(i).setText(questions.getStates().get(numbers.get(i)) + ", " + questions.getVariables().get(numbers.get(i)).getVar_name());
			}else{
				view.get(i).setText(questions.getStates().get(numbers.get(i)) + ", " + questions.getVariables().get(numbers.get(i)).getVar_name());
			}

			if(questions.getAnswer().contains(numbers.get(i))){
				trueAnswers.add("V");
			}else{
				trueAnswers.add("F");
			}
		}
	}
}
