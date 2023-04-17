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
import trainerLogic.QuestionCause;
import classes.Process;
import classes.ProcessConfiguration;
import classes.Training;
import classes.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

import extras.Convert;

public class CauseMultipleSelection {

	public JFrame frmEtapa;
	private JLabel label;

	Timer timer;
	int sec;
	private ImageCharge img = null;
	int min;

	private JButton finish;
	private JLabel label_1;
	private JTextPane txtpnSeleccioneLasVariables;
	ArrayList<JTextPane> viewQ = new ArrayList<>();
	QuestionCause questions;
	ArrayList<JCheckBox> view = new ArrayList<>();
	private JCheckBox v1;
	private JCheckBox v2;
	private JCheckBox v3;
	private JCheckBox v4;
	private JCheckBox v5;
	private JTextPane textPane;
	private JTextPane textPane_1;
	private JTextPane textPane_2;
	private JTextPane textPane_3;
	private JTextPane textPane_4;

	public CauseMultipleSelection(Process p, int timeFinal, User operator, Training train, ProcessConfiguration cantInte) {
		questions = FillTrain.fillQuestionCause3(p);
		initialize(p, timeFinal, operator, train, cantInte);
	}

	@SuppressWarnings({ })
	private void initialize(Process p, final int timeFinal, final User operator, final Training train, final ProcessConfiguration cantInt) {
		if(p.getProcess_img() != null){
			try {
				img = new ImageCharge(Convert.toObject(p.getProcess_img()));
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			img.setVisible(true);
		}

		
		frmEtapa = new JFrame();
		frmEtapa.setTitle("Etapa #2: Causas");
		frmEtapa.setResizable(false);
		frmEtapa.setIconImage(Toolkit.getDefaultToolkit().getImage(CauseMultipleSelection.class.getResource("/images/icons8_Quiz_32.png")));
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
		txtpnSeleccioneLasVariables.setText("Seleccione solo las expresiones correctas:");
		txtpnSeleccioneLasVariables.setEditable(false);
		txtpnSeleccioneLasVariables.setBounds(104, 0, 507, 27);
		panel.add(txtpnSeleccioneLasVariables);

		v1 = new JCheckBox("");
		v1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v1.setBackground(Color.WHITE);
		v1.setBounds(22, 30, 29, 71);
		panel.add(v1);
		view.add(v1);

		v2 = new JCheckBox("");
		v2.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v2.setBackground(Color.WHITE);
		v2.setBounds(25, 103, 26, 71);
		panel.add(v2);
		view.add(v2);

		v3 = new JCheckBox("");
		v3.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v3.setBackground(Color.WHITE);
		v3.setBounds(22, 182, 29, 71);
		panel.add(v3);
		view.add(v3);

		v4 = new JCheckBox("");
		v4.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v4.setBackground(Color.WHITE);
		v4.setBounds(22, 261, 29, 71);
		panel.add(v4);
		view.add(v4);

		v5 = new JCheckBox("");
		v5.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		v5.setBackground(Color.WHITE);
		v5.setBounds(22, 340, 29, 71);
		panel.add(v5);
		view.add(v5);
		
		textPane = new JTextPane();
		textPane.setForeground(new Color(99, 68, 55));
		textPane.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		textPane.setEditable(false);
		textPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textPane.setBorder(null);
		textPane.setBounds(51, 30, 633, 71);
		panel.add(textPane);
		viewQ.add(textPane);
		
		textPane_1 = new JTextPane();
		textPane_1.setForeground(new Color(99, 68, 55));
		textPane_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		textPane_1.setEditable(false);
		textPane_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textPane_1.setBorder(null);
		textPane_1.setBounds(51, 103, 633, 71);
		panel.add(textPane_1);
		viewQ.add(textPane_1);
		
		textPane_2 = new JTextPane();
		textPane_2.setForeground(new Color(99, 68, 55));
		textPane_2.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		textPane_2.setEditable(false);
		textPane_2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textPane_2.setBorder(null);
		textPane_2.setBounds(51, 182, 633, 71);
		panel.add(textPane_2);
		viewQ.add(textPane_2);
		
		textPane_3 = new JTextPane();
		textPane_3.setForeground(new Color(99, 68, 55));
		textPane_3.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		textPane_3.setEditable(false);
		textPane_3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textPane_3.setBorder(null);
		textPane_3.setBounds(51, 261, 633, 71);
		panel.add(textPane_3);
		viewQ.add(textPane_3);
		
		textPane_4 = new JTextPane();
		textPane_4.setForeground(new Color(99, 68, 55));
		textPane_4.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 18));
		textPane_4.setEditable(false);
		textPane_4.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textPane_4.setBorder(null);
		textPane_4.setBounds(51, 344, 633, 71);
		panel.add(textPane_4);
		viewQ.add(textPane_4);

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
							if(view.get(i).isSelected()){
								cant = cant + 1;
								viewQ.get(i).setForeground(Color.GREEN);
							}else{
								viewQ.get(i).setForeground(Color.RED);
							}
						}else if(view.get(i).isSelected()){
							viewQ.get(i).setForeground(Color.RED);
						}else{
							cant = cant + 1;
							viewQ.get(i).setForeground(Color.GREEN);
						}
					}

					String tiempo = min + "." + sec;
					double t = Double.parseDouble(tiempo);

					ResultView rs = new ResultView(cant, 5, t, timeFinal, frmEtapa, operator, train, "causa", cantInt, img);
					rs.setLocationRelativeTo(frmEtapa);
					rs.setVisible(true);
				}
			}
		});
		finish.setIcon(new ImageIcon(CauseMultipleSelection.class.getResource("/images/icons8_Finish_Flag_16.png")));
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
		label_1.setIcon(new ImageIcon(CauseMultipleSelection.class.getResource("/images/icons8_Time_32.png")));
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
			viewQ.get(i).setText((i+1) + ". " + questions.getVariables().get(i) + " tiene como causa " + questions.getCauses().get(i));
		}
	}
}
