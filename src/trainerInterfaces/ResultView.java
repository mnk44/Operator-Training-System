package trainerInterfaces;

import interfaces.PrincipalView;

import javax.swing.JDialog;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;

import trainerLogic.InsertTrainPart;
import classes.ProcessConfiguration;
import classes.TrainPart;
import classes.Training;
import classes.User;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class ResultView extends JDialog {

	private static final long serialVersionUID = 6980072127901785779L;
	private JLabel aprove;
	private JLabel disaprove;
	private JLabel cantityTotal;
	private JLabel note;
	private JProgressBar progressBar;
	
	Timer timer;
	DecimalFormat format = new DecimalFormat("#.00");
	int porcent;
	double noteF;

	public ResultView(final int cantAprove, final int cantQ, final double time, final int totalTime, final JFrame frame, 
			final User uss, final Training train, final String type, final ProcessConfiguration config, final JDialog dg) {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ResultView.class.getResource("/images/icons8_Grades_32.png")));
		setTitle("Resultado");
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				TrainPart part = new TrainPart(train.getTrain_id(), time, cantAprove, type, noteF);
				try {
					InsertTrainPart.insertPart(part, train, config, uss.getUser_nick());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					new PrincipalView(uss);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
				frame.setVisible(false);
				if(dg != null){
					dg.setVisible(false);
				}
				PrincipalView.frame.setLocationRelativeTo(null);
				PrincipalView.train.doClick();
				PrincipalView.frame.setVisible(true);
			}
		});

		aprove = new JLabel("Aprobado");
		aprove.setVisible(false);
		aprove.setHorizontalAlignment(SwingConstants.CENTER);
		aprove.setForeground(new Color(255, 113, 19));
		aprove.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 34));
		aprove.setBounds(51, 16, 453, 67);
		getContentPane().add(aprove);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBackground(Color.WHITE);
		progressBar.setBounds(51, 122, 453, 24);
		getContentPane().add(progressBar);

		disaprove = new JLabel("Desaprobado");
		disaprove.setVisible(false);
		disaprove.setHorizontalAlignment(SwingConstants.CENTER);
		disaprove.setForeground(new Color(99, 68, 55));
		disaprove.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 34));
		disaprove.setBounds(51, 25, 453, 49);
		getContentPane().add(disaprove);

		cantityTotal = new JLabel();
		cantityTotal.setHorizontalAlignment(SwingConstants.CENTER);
		cantityTotal.setForeground(Color.BLACK);
		cantityTotal.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
		cantityTotal.setBounds(51, 68, 464, 49);
		getContentPane().add(cantityTotal);

		note = new JLabel();
		note.setHorizontalAlignment(SwingConstants.CENTER);
		note.setForeground(Color.BLACK);
		note.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 20));
		note.setBounds(51, 148, 453, 49);
		getContentPane().add(note);
		setBounds(100, 100, 565, 251);
		
		evaluate(cantAprove, cantQ, time, totalTime);
		if(porcent > 50){
			progressBar.setForeground(Color.GREEN);
		}else{
			progressBar.setForeground(Color.RED);
		}
		
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int cont = -20;

			public void run() {
				progressBar.setValue(cont);
				cont = cont + 1;
				if(cont > porcent){
					timer.cancel();
				}
			}
		}, 0, 20);
	}
	
	private void evaluate(int cantAprove, int cantQ, double time, int totalTime){
		cantityTotal.setText("Cantidad de preguntas aceptadas: " + cantAprove);
		
		double apr = ((double)cantAprove)/cantQ;
		double t = (time/totalTime)* 0.1;
		noteF = (apr + t) * 9.09;
		if(!format.format(noteF).startsWith(".")){
			note.setText(format.format(noteF) + " de 10");
		}else{
			note.setText("0" + format.format(noteF) + " de 10");
		}
		
		porcent = (int) (noteF * 10);
		if(porcent > 50){
			aprove.setVisible(true);
		}else{
			disaprove.setVisible(true);
		}
	}
}
