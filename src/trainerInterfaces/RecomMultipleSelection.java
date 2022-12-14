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
import java.util.ArrayList;
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
import trainerLogic.QuestionRec;
import classes.Process;
import classes.ProcessConfiguration;
import classes.Training;
import classes.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class RecomMultipleSelection {

	public JFrame frmEtapa;
	private JLabel label;

	Timer timer;
	int sec;
	int min;

	private JButton finish;
	private JLabel label_1;
	private JTextPane txtpnSeleccioneLasVariables;
	private JTextPane var1;
	private JTextPane var2;
	private JTextPane var3;
	private JTextPane var4;
	private JTextPane var5;

	QuestionRec questions;
	ArrayList<JTextPane> view = new ArrayList<>();
	ArrayList<JCheckBox> viewAnswers = new ArrayList<>();
	private JCheckBox checkBox;
	private JCheckBox checkBox_1;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;

	public RecomMultipleSelection(Process p, int timeFinal, User operator, Training train, ProcessConfiguration cantInte) {
		questions = FillTrain.fillQuestionRec(p);
		initialize(timeFinal, operator, train, cantInte);
	}

	private void initialize(final int timeFinal, final User operator, final Training train, final ProcessConfiguration cantInt) {
		frmEtapa = new JFrame();
		frmEtapa.setTitle("Etapa #3: Recomendaciones");
		frmEtapa.setResizable(false);
		frmEtapa.setIconImage(Toolkit.getDefaultToolkit().getImage(RecomMultipleSelection.class.getResource("/images/icons8_Quiz_32.png")));
		frmEtapa.getContentPane().setBackground(Color.WHITE);
		frmEtapa.getContentPane().setLayout(null);
		frmEtapa.setBackground(Color.WHITE);
		frmEtapa.setBounds(100, 100, 745, 593);
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
		panel.setBounds(25, 59, 699, 430);
		frmEtapa.getContentPane().add(panel);
		panel.setLayout(null);

		txtpnSeleccioneLasVariables = new JTextPane();
		txtpnSeleccioneLasVariables.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtpnSeleccioneLasVariables.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtpnSeleccioneLasVariables.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		txtpnSeleccioneLasVariables.setForeground(new Color(255, 113, 19));
		txtpnSeleccioneLasVariables.setText("Seleccione verdadero o falso si la recomendaci\u00F3n corresponde a la causa:");
		txtpnSeleccioneLasVariables.setEditable(false);
		txtpnSeleccioneLasVariables.setBounds(15, 0, 649, 58);
		panel.add(txtpnSeleccioneLasVariables);

		var1 = new JTextPane();
		var1.setBorder(null);
		var1.setForeground(new Color(99, 68, 55));
		var1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var1.setEditable(false);
		var1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var1.setBounds(68, 61, 616, 71);
		panel.add(var1);
		view.add(var1);

		var2 = new JTextPane();
		var2.setBorder(null);
		var2.setForeground(new Color(99, 68, 55));
		var2.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var2.setEditable(false);
		var2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var2.setBounds(68, 135, 616, 71);
		panel.add(var2);
		view.add(var2);

		var3 = new JTextPane();
		var3.setBorder(null);
		var3.setForeground(new Color(99, 68, 55));
		var3.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var3.setEditable(false);
		var3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var3.setBounds(68, 207, 616, 71);
		panel.add(var3);
		view.add(var3);

		var4 = new JTextPane();
		var4.setBorder(null);
		var4.setForeground(new Color(99, 68, 55));
		var4.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var4.setEditable(false);
		var4.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var4.setBounds(68, 280, 616, 71);
		panel.add(var4);
		view.add(var4);

		var5 = new JTextPane();
		var5.setBounds(68, 358, 616, 64);
		panel.add(var5);
		var5.setBorder(null);
		var5.setForeground(new Color(99, 68, 55));
		var5.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var5.setEditable(false);
		var5.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		view.add(var5);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setBounds(25, 78, 32, 29);
		panel.add(chckbxNewCheckBox);
		viewAnswers.add(chckbxNewCheckBox);
		
		checkBox = new JCheckBox("");
		checkBox.setBackground(Color.WHITE);
		checkBox.setBounds(25, 155, 32, 29);
		panel.add(checkBox);
		viewAnswers.add(checkBox);
		
		checkBox_1 = new JCheckBox("");
		checkBox_1.setBackground(Color.WHITE);
		checkBox_1.setBounds(25, 232, 32, 29);
		panel.add(checkBox_1);
		viewAnswers.add(checkBox_1);
		
		checkBox_2 = new JCheckBox("");
		checkBox_2.setBackground(Color.WHITE);
		checkBox_2.setBounds(25, 295, 32, 29);
		panel.add(checkBox_2);
		viewAnswers.add(checkBox_2);
		
		checkBox_3 = new JCheckBox("");
		checkBox_3.setBackground(Color.WHITE);
		checkBox_3.setBounds(25, 370, 32, 29);
		panel.add(checkBox_3);
		viewAnswers.add(checkBox_3);

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

					for(int i=0; i<5; i++){
						if(questions.getAnswer().get(i) == 1){
							if(viewAnswers.get(i).isSelected()){
								cant = cant + 1;
								view.get(i).setForeground(Color.GREEN);
							}else{
								view.get(i).setForeground(Color.RED);
							}
						}else{
							if(viewAnswers.get(i).isSelected()){
								view.get(i).setForeground(Color.RED);
							}else{
								cant = cant + 1;
								view.get(i).setForeground(Color.GREEN);
							}
						}
					}

					String tiempo = min + "." + sec;
					double t = Double.parseDouble(tiempo);

					ResultView rs = new ResultView(cant, 5, t, timeFinal, frmEtapa, operator, train, "recomendaciones", cantInt);
					rs.setLocationRelativeTo(frmEtapa);
					rs.setVisible(true);
				}
			}
		});
		finish.setIcon(new ImageIcon(RecomMultipleSelection.class.getResource("/images/icons8_Finish_Flag_16.png")));
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
		finish.setBounds(240, 505, 236, 35);
		frmEtapa.getContentPane().add(finish);

		label_1 = new JLabel();
		label_1.setIcon(new ImageIcon(RecomMultipleSelection.class.getResource("/images/icons8_Time_32.png")));
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
		for(int i=0; i<5; i++){
			view.get(i).setText(questions.getCauses().get(i) + " y su recomendación " + questions.getRecomendations().get(i));
		}
	}
}
