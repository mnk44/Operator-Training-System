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

import trainerLogic.FillTrain;
import trainerLogic.QuestionsVar;
import classes.Process;
import classes.ProcessConfiguration;
import classes.Training;
import classes.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

import extras.Convert;

public class VariableMultipleSelection {

	public JFrame frmEtapa;
	private JLabel label;

	Timer timer;
	int sec;
	int min;

	private JButton finish;
	private JLabel label_1;
	private JTextPane txtpnSeleccioneLasVariables;

	QuestionsVar questions;
	ArrayList<Integer> numbers = new ArrayList<>();
	ArrayList<String> trueAnswers = new ArrayList<>();
	ArrayList<JCheckBox> viewAnswers = new ArrayList<>();
	private JCheckBox v1;
	private JCheckBox v2;
	private JCheckBox v3;
	private JCheckBox v4;
	private JCheckBox v5;
	private JCheckBox v6;
	private JCheckBox v7;
	private JCheckBox v8;
	private JCheckBox v9;
	private JCheckBox v10;
	private ImageCharge img = null;

	public VariableMultipleSelection(Process p, int timeFinal, User operator, Training train, ProcessConfiguration cantInte) {
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

	private void initialize(final int timeFinal, final User operator, final Training train, final ProcessConfiguration cantInt) {
		frmEtapa = new JFrame();
		frmEtapa.setTitle("Etapa #1: Variables");
		frmEtapa.setResizable(false);
		frmEtapa.setIconImage(Toolkit.getDefaultToolkit().getImage(VariableMultipleSelection.class.getResource("/images/icons8_Quiz_32.png")));
		frmEtapa.getContentPane().setBackground(Color.WHITE);
		frmEtapa.getContentPane().setLayout(null);
		frmEtapa.setBackground(Color.WHITE);
		frmEtapa.setBounds(100, 100, 745, 594);
		frmEtapa.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmEtapa.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				JOptionPane.showMessageDialog(null, "Si sale se dar� por terminado el entrenamiento", "Salir", JOptionPane.WARNING_MESSAGE);
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
		txtpnSeleccioneLasVariables.setText("Seleccione solo las varibles que se encuentren fuera de rango:");
		txtpnSeleccioneLasVariables.setEditable(false);
		txtpnSeleccioneLasVariables.setBounds(20, 0, 664, 58);
		panel.add(txtpnSeleccioneLasVariables);

		v1 = new JCheckBox();
		v1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v1.setBackground(Color.WHITE);
		v1.setBounds(25, 61, 659, 29);
		panel.add(v1);
		viewAnswers.add(v1);

		v2 = new JCheckBox();
		v2.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v2.setBackground(Color.WHITE);
		v2.setBounds(25, 98, 659, 29);
		panel.add(v2);
		viewAnswers.add(v2);

		v3 = new JCheckBox();
		v3.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v3.setBackground(Color.WHITE);
		v3.setBounds(25, 139, 659, 29);
		panel.add(v3);
		viewAnswers.add(v3);

		v4 = new JCheckBox();
		v4.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v4.setBackground(Color.WHITE);
		v4.setBounds(25, 174, 659, 29);
		panel.add(v4);
		viewAnswers.add(v4);

		v5 = new JCheckBox();
		v5.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v5.setBackground(Color.WHITE);
		v5.setBounds(25, 211, 659, 29);
		panel.add(v5);
		viewAnswers.add(v5);

		v6 = new JCheckBox();
		v6.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v6.setBackground(Color.WHITE);
		v6.setBounds(25, 247, 659, 29);
		panel.add(v6);
		viewAnswers.add(v6);

		v7 = new JCheckBox();
		v7.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v7.setBackground(Color.WHITE);
		v7.setBounds(25, 286, 659, 29);
		panel.add(v7);
		viewAnswers.add(v7);

		v8 = new JCheckBox();
		v8.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v8.setBackground(Color.WHITE);
		v8.setBounds(25, 324, 659, 29);
		panel.add(v8);
		viewAnswers.add(v8);

		v9 = new JCheckBox();
		v9.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v9.setBackground(Color.WHITE);
		v9.setBounds(25, 361, 659, 29);
		panel.add(v9);
		viewAnswers.add(v9);

		v10 = new JCheckBox();
		v10.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v10.setBackground(Color.WHITE);
		v10.setBounds(25, 397, 659, 29);
		panel.add(v10);
		viewAnswers.add(v10);

		finish = new JButton("Terminar evaluaci\u00F3n");
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = 0;
				if(sec != 0 && min != 0){
					option = JOptionPane.showConfirmDialog(null, "�Est� seguro que desea terminar el entrenamiento?", "Terminar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				}
				if(option == 0){
					int cant = 0;
					timer.cancel();

					for(int i=0; i<10; i++){
						if(viewAnswers.get(i).isSelected() && trueAnswers.get(i).equals("V")){
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
		finish.setIcon(new ImageIcon(VariableMultipleSelection.class.getResource("/images/icons8_Finish_Flag_16.png")));
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
		label_1.setIcon(new ImageIcon(VariableMultipleSelection.class.getResource("/images/icons8_Time_32.png")));
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
						JOptionPane.showMessageDialog(null, "Se ha terminado el tiempo", "�Tiempo!", JOptionPane.INFORMATION_MESSAGE);
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
				viewAnswers.get(i).setText(questions.getStates().get(numbers.get(i)) + ", " + questions.getVariables().get(numbers.get(i)).getVar_name());
			}else{
				viewAnswers.get(i).setText(questions.getStates().get(numbers.get(i)) + ", " + questions.getVariables().get(numbers.get(i)).getVar_name());
			}

			if(questions.getAnswer().contains(numbers.get(i))){
				trueAnswers.add("V");
			}else{
				trueAnswers.add("F");
			}
		}
	}
}
