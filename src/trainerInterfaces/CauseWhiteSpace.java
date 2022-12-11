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

import javax.swing.JComboBox;
import trainerLogic.FillTrain;
import trainerLogic.QuestionCause;
import classes.Process;
import classes.ProcessConfiguration;
import classes.Training;
import classes.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CauseWhiteSpace {

	public JFrame frmEtapa;
	private JLabel label;

	Timer timer;
	int sec;
	int min;

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
	private JTextPane var2;
	private JTextPane var3;
	private JTextPane var4;
	private JTextPane var5;

	QuestionCause questions;
	ArrayList<JTextPane> view = new ArrayList<>();
	ArrayList<String> trueAnswers = new ArrayList<>();
	@SuppressWarnings("rawtypes")
	ArrayList<JComboBox> viewAnswers = new ArrayList<>();

	public CauseWhiteSpace(Process p, int timeFinal, User operator, Training train, ProcessConfiguration cantInte) {
		questions = FillTrain.fillQuestionCause(p);
		initialize(timeFinal, operator, train, cantInte);
	}

	@SuppressWarnings({ "rawtypes" })
	private void initialize(final int timeFinal, final User operator, final Training train, final ProcessConfiguration cantInt) {
		frmEtapa = new JFrame();
		frmEtapa.setTitle("Etapa #2: Causas");
		frmEtapa.setResizable(false);
		frmEtapa.setIconImage(Toolkit.getDefaultToolkit().getImage(CauseWhiteSpace.class.getResource("/images/icons8_Quiz_32.png")));
		frmEtapa.getContentPane().setBackground(Color.WHITE);
		frmEtapa.getContentPane().setLayout(null);
		frmEtapa.setBackground(Color.WHITE);
		frmEtapa.setBounds(100, 100, 745, 621);
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
		panel.setBounds(25, 59, 699, 446);
		frmEtapa.getContentPane().add(panel);
		panel.setLayout(null);

		txtpnSeleccioneLasVariables = new JTextPane();
		txtpnSeleccioneLasVariables.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtpnSeleccioneLasVariables.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtpnSeleccioneLasVariables.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		txtpnSeleccioneLasVariables.setForeground(new Color(255, 113, 19));
		txtpnSeleccioneLasVariables.setText("Complete los espacios en blanco de manera que se obtenga una variable y su causa:");
		txtpnSeleccioneLasVariables.setEditable(false);
		txtpnSeleccioneLasVariables.setBounds(15, 0, 684, 58);
		panel.add(txtpnSeleccioneLasVariables);

		var1 = new JTextPane();
		var1.setBorder(null);
		var1.setForeground(new Color(99, 68, 55));
		var1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var1.setEditable(false);
		var1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var1.setBounds(15, 61, 669, 29);
		panel.add(var1);
		view.add(var1);

		v1 = new JComboBox();
		v1.setFont(new Font("Corbel", Font.PLAIN, 20));
		v1.setBackground(Color.WHITE);
		v1.setBounds(15, 94, 669, 26);
		panel.add(v1);
		viewAnswers.add(v1);

		v2 = new JComboBox();
		v2.setFont(new Font("Corbel", Font.PLAIN, 20));
		v2.setBackground(Color.WHITE);
		v2.setBounds(15, 176, 669, 26);
		panel.add(v2);
		viewAnswers.add(v2);

		var2 = new JTextPane();
		var2.setBorder(null);
		var2.setForeground(new Color(99, 68, 55));
		var2.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var2.setEditable(false);
		var2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var2.setBounds(15, 143, 669, 29);
		panel.add(var2);
		view.add(var2);

		v3 = new JComboBox();
		v3.setFont(new Font("Corbel", Font.PLAIN, 20));
		v3.setBackground(Color.WHITE);
		v3.setBounds(15, 256, 669, 26);
		panel.add(v3);
		viewAnswers.add(v3);

		var3 = new JTextPane();
		var3.setBorder(null);
		var3.setForeground(new Color(99, 68, 55));
		var3.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var3.setEditable(false);
		var3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var3.setBounds(15, 223, 669, 29);
		panel.add(var3);
		view.add(var3);

		var4 = new JTextPane();
		var4.setBorder(null);
		var4.setForeground(new Color(99, 68, 55));
		var4.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var4.setEditable(false);
		var4.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var4.setBounds(15, 298, 669, 29);
		panel.add(var4);
		view.add(var4);

		var5 = new JTextPane();
		var5.setBorder(null);
		var5.setForeground(new Color(99, 68, 55));
		var5.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		var5.setEditable(false);
		var5.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		var5.setBounds(15, 376, 669, 29);
		panel.add(var5);
		view.add(var5);

		v4 = new JComboBox();
		v4.setBounds(15, 334, 669, 26);
		panel.add(v4);
		v4.setFont(new Font("Corbel", Font.PLAIN, 20));
		v4.setBackground(Color.WHITE);
		viewAnswers.add(v4);

		v5 = new JComboBox();
		v5.setBounds(15, 408, 669, 26);
		panel.add(v5);
		v5.setFont(new Font("Corbel", Font.PLAIN, 20));
		v5.setBackground(Color.WHITE);
		viewAnswers.add(v5);

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
						if((viewAnswers.get(i).getSelectedIndex() - 1) == questions.getAnswer().get(i)){
							cant = cant + 1;
							viewAnswers.get(i).setForeground(Color.GREEN);
						}else{
							viewAnswers.get(i).setForeground(Color.RED);
						}
					}

					String tiempo = min + "." + sec;
					double t = Double.parseDouble(tiempo);

					ResultView rs = new ResultView(cant, 5, t, timeFinal, frmEtapa, operator, train, "causa", cantInt);
					rs.setLocationRelativeTo(frmEtapa);
					rs.setVisible(true);
				}
			}
		});
		finish.setIcon(new ImageIcon(CauseWhiteSpace.class.getResource("/images/icons8_Finish_Flag_16.png")));
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
		finish.setBounds(247, 530, 236, 35);
		frmEtapa.getContentPane().add(finish);

		label_1 = new JLabel();
		label_1.setIcon(new ImageIcon(CauseWhiteSpace.class.getResource("/images/icons8_Time_32.png")));
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

	@SuppressWarnings("unchecked")
	private void chargeData(){
		viewAnswers.get(0).addItem(" ");
		viewAnswers.get(1).addItem(" ");
		viewAnswers.get(2).addItem(" ");
		viewAnswers.get(3).addItem(" ");
		viewAnswers.get(4).addItem(" ");
		for(int i=0; i<5; i++){
			view.get(i).setText((i+1) + ". " + questions.getVariables().get(i));
			System.out.println(questions.getAnswer().get(i));
		}
		for(int i=0; i<questions.getCauses().size(); i++){
			viewAnswers.get(0).addItem(questions.getCauses().get(i));
			viewAnswers.get(1).addItem(questions.getCauses().get(i));
			viewAnswers.get(2).addItem(questions.getCauses().get(i));
			viewAnswers.get(3).addItem(questions.getCauses().get(i));
			viewAnswers.get(4).addItem(questions.getCauses().get(i));
		}
	}
}
