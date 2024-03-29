package interfaces;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import services.TrainingService;
import trainerInterfaces.CauseEnlace;
import trainerInterfaces.CauseMultipleSelection;
import trainerInterfaces.CauseTrueFalse;
import trainerInterfaces.CauseWhiteSpace;
import trainerInterfaces.RecomMultipleSelection;
import trainerInterfaces.RecomTrueFalse;
import trainerInterfaces.VariableEnlace;
import trainerInterfaces.VariableMultipleSelection;
import trainerInterfaces.VariableTrueFalse;
import trainerInterfaces.VariableWhiteSpace;
import classes.ProcessConfiguration;
import classes.Training;
import classes.Process;
import classes.User;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TrainingView extends JPanel{

	private static final long serialVersionUID = 2160031059373289103L;
	private JLabel process_name;
	private JLabel lblVariables;
	private JLabel lblEtapacausas;
	private JLabel lblEtaparecomendaciones;
	private JLabel lblIntentos;
	private JLabel lblPuntosObtenidos;
	private JLabel lblTotal;
	private JLabel lblNewLabel;
	private JButton btnComenzarPrueba;
	private JPanel panel;
	private JLabel var_int;
	private JLabel total1;
	private JLabel total2;
	private JLabel total3;
	private JLabel cause_int;
	private JLabel rec_int;
	private JLabel var;
	private JLabel cause;
	private JLabel rec;
	private JLabel prom_var;
	private JLabel prom_cause;
	private JLabel prom_rec;
	private JLabel lblCantidadDeIntentos;
	private JLabel label;

	DecimalFormat format = new DecimalFormat("#.00");

	public TrainingView(String process, final ProcessConfiguration conf, final Training trainn, final User operator) {
		setBorder(null);
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(100, 100, 779, 359);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 779, 343);
		add(panel);
		panel.setLayout(null);

		JLabel lblNombreDelProceso = new JLabel("Nombre del proceso:");
		lblNombreDelProceso.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreDelProceso.setForeground(new Color(255, 113, 19));
		lblNombreDelProceso.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 22));
		lblNombreDelProceso.setBounds(15, 16, 319, 49);
		panel.add(lblNombreDelProceso);

		process_name = new JLabel(process);
		process_name.setHorizontalAlignment(SwingConstants.LEFT);
		process_name.setForeground(new Color(99, 68, 55));
		process_name.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 22));
		process_name.setBounds(349, 16, 417, 49);
		panel.add(process_name);

		lblVariables = new JLabel("Etapa #1:");
		lblVariables.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVariables.setForeground(new Color(99, 68, 55));
		lblVariables.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
		lblVariables.setBounds(289, 122, 127, 49);
		panel.add(lblVariables);

		lblEtapacausas = new JLabel("Etapa #2:");
		lblEtapacausas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEtapacausas.setForeground(new Color(99, 68, 55));
		lblEtapacausas.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
		lblEtapacausas.setBounds(289, 166, 127, 49);
		panel.add(lblEtapacausas);

		lblEtaparecomendaciones = new JLabel("Etapa #3:");
		lblEtaparecomendaciones.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEtaparecomendaciones.setForeground(new Color(99, 68, 55));
		lblEtaparecomendaciones.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 20));
		lblEtaparecomendaciones.setBounds(289, 213, 127, 49);
		panel.add(lblEtaparecomendaciones);

		lblIntentos = new JLabel("Intentos");
		lblIntentos.setBorder(new LineBorder(new Color(255, 113, 19)));
		lblIntentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntentos.setForeground(new Color(99, 68, 55));
		lblIntentos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		lblIntentos.setBounds(428, 81, 105, 34);
		panel.add(lblIntentos);

		lblPuntosObtenidos = new JLabel("Nota");
		lblPuntosObtenidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntosObtenidos.setBorder(new LineBorder(new Color(255, 113, 19)));
		lblPuntosObtenidos.setForeground(new Color(99, 68, 55));
		lblPuntosObtenidos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		lblPuntosObtenidos.setBounds(644, 81, 105, 34);
		panel.add(lblPuntosObtenidos);

		lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBorder(new LineBorder(new Color(255, 113, 19)));
		lblTotal.setForeground(new Color(99, 68, 55));
		lblTotal.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		lblTotal.setBounds(536, 81, 105, 34);
		panel.add(lblTotal);

		lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(TrainingView.class.getResource("/images/entrenamiento.png")));
		lblNewLabel.setBounds(25, 69, 215, 220);
		panel.add(lblNewLabel);

		btnComenzarPrueba = new JButton("Iniciar evaluaci\u00F3n");
		btnComenzarPrueba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(trainn == null){
					Object train = null;
					try {
						train = TrainingService.newTraining(conf.getProcess_id(), operator.getUser_id(), operator.getUser_nick(), new Timestamp(Calendar.getInstance().getTime().getTime()));
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.INFORMATION_MESSAGE);
					}
					if(!train.toString().contains("e")){
						Process p = findProcess(conf.getProcess_id());
						Training t = new Training((int)train, conf.getProcess_id(), operator.getUser_id(), 0, -1, -1, -1, -1, 0);
						if(conf.getType_var().equals("Verdadero o falso")){
							VariableTrueFalse var = new VariableTrueFalse(p, conf.getTime_limit(), operator, t, conf);
							var.frmEtapa.setLocationRelativeTo(null);
							var.frmEtapa.setVisible(true);
							PrincipalView.close();
						}else if(conf.getType_var().equals("Espacios en blanco")){
							VariableWhiteSpace var = new VariableWhiteSpace(p, conf.getTime_limit(), operator, t, conf);
							var.frmEtapa.setLocationRelativeTo(null);
							var.frmEtapa.setVisible(true);
							PrincipalView.close();
						}else if(conf.getType_var().equals("Enlazar")){
							VariableEnlace var = new VariableEnlace(p, conf.getTime_limit(), operator, t, conf);
							var.frmEtapa.setLocationRelativeTo(null);
							var.frmEtapa.setVisible(true);
							PrincipalView.close();
						}else{
							VariableMultipleSelection var = new VariableMultipleSelection(p, conf.getTime_limit(), operator, t, conf);
							var.frmEtapa.setLocationRelativeTo(null);
							var.frmEtapa.setVisible(true);
							PrincipalView.close();
						}
					}else{
						JOptionPane.showMessageDialog(null, train, "Error", JOptionPane.INFORMATION_MESSAGE);
					}
				}else{
					Process p = findProcess(conf.getProcess_id());
					if(var.isVisible()){
						if(conf.getType_var().equals("Verdadero o falso")){
							VariableTrueFalse var = new VariableTrueFalse(p, conf.getTime_limit(), operator, trainn, conf);
							var.frmEtapa.setLocationRelativeTo(null);
							var.frmEtapa.setVisible(true);
							PrincipalView.close();
						}else if(conf.getType_var().equals("Espacios en blanco")){
							VariableWhiteSpace var = new VariableWhiteSpace(p, conf.getTime_limit(), operator, trainn, conf);
							var.frmEtapa.setLocationRelativeTo(null);
							var.frmEtapa.setVisible(true);
							PrincipalView.close();
						}else if(conf.getType_var().equals("Enlazar")){
							VariableEnlace var = new VariableEnlace(p, conf.getTime_limit(), operator, trainn, conf);
							var.frmEtapa.setLocationRelativeTo(null);
							var.frmEtapa.setVisible(true);
							PrincipalView.close();
						}else{
							VariableMultipleSelection var = new VariableMultipleSelection(p, conf.getTime_limit(), operator, trainn, conf);
							var.frmEtapa.setLocationRelativeTo(null);
							var.frmEtapa.setVisible(true);
							PrincipalView.close();
						}
					}else if(cause.isVisible()){
						if(conf.getType_cause().equals("Espacios en blanco")){
							CauseWhiteSpace cw = new CauseWhiteSpace(p, conf.getTime_limit(), operator, trainn, conf);
							cw.frmEtapa.setLocationRelativeTo(null);
							cw.frmEtapa.setVisible(true);
							PrincipalView.close();
						}else if(conf.getType_cause().equals("Verdadero o falso")){
							CauseTrueFalse cw = new CauseTrueFalse(p, conf.getTime_limit(), operator, trainn, conf);
							cw.frmEtapa.setLocationRelativeTo(null);
							cw.frmEtapa.setVisible(true);
							PrincipalView.close();
						}else if(conf.getType_cause().equals("Enlazar")){
							CauseEnlace cw = new CauseEnlace(p, conf.getTime_limit(), operator, trainn, conf);
							cw.frmEtapa.setLocationRelativeTo(null);
							cw.frmEtapa.setVisible(true);
							PrincipalView.close();
						}else{
							CauseMultipleSelection cw = new CauseMultipleSelection(p, conf.getTime_limit(), operator, trainn, conf);
							cw.frmEtapa.setLocationRelativeTo(null);
							cw.frmEtapa.setVisible(true);
							PrincipalView.close();
						}
					}else{
						if(conf.getType_rec().equals("Verdadero o falso")){
							RecomTrueFalse cw = new RecomTrueFalse(p, conf.getTime_limit(), operator, trainn, conf);
							cw.frmEtapa.setLocationRelativeTo(null);
							cw.frmEtapa.setVisible(true);
							PrincipalView.close();
						}else if(conf.getType_rec().equals("Selecci�n m�ltiple")){
							RecomMultipleSelection cw = new RecomMultipleSelection(p, conf.getTime_limit(), operator, trainn, conf);
							cw.frmEtapa.setLocationRelativeTo(null);
							cw.frmEtapa.setVisible(true);
							PrincipalView.close();
						}
					}
				}
			}
		});
		btnComenzarPrueba.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnComenzarPrueba.setBackground(new Color(248, 159, 101));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnComenzarPrueba.setBackground(new Color(255, 113, 19));
			}
		});
		btnComenzarPrueba.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Play_Property_32.png")));
		btnComenzarPrueba.setHorizontalTextPosition(SwingConstants.LEFT);
		btnComenzarPrueba.setForeground(Color.WHITE);
		btnComenzarPrueba.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnComenzarPrueba.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 113, 19)));
		btnComenzarPrueba.setBackground(new Color(255, 113, 19));
		btnComenzarPrueba.setBounds(484, 272, 236, 37);
		panel.add(btnComenzarPrueba);

		var_int = new JLabel();
		var_int.setHorizontalAlignment(SwingConstants.CENTER);
		var_int.setForeground(Color.BLACK);
		var_int.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		var_int.setBounds(428, 122, 105, 49);
		panel.add(var_int);

		total1 = new JLabel();
		total1.setHorizontalAlignment(SwingConstants.CENTER);
		total1.setForeground(Color.BLACK);
		total1.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		total1.setBounds(536, 122, 105, 49);
		panel.add(total1);

		total2 = new JLabel();
		total2.setHorizontalAlignment(SwingConstants.CENTER);
		total2.setForeground(Color.BLACK);
		total2.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		total2.setBounds(536, 167, 105, 49);
		panel.add(total2);

		total3 = new JLabel();
		total3.setHorizontalAlignment(SwingConstants.CENTER);
		total3.setForeground(Color.BLACK);
		total3.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		total3.setBounds(536, 214, 105, 49);
		panel.add(total3);

		cause_int = new JLabel();
		cause_int.setHorizontalAlignment(SwingConstants.CENTER);
		cause_int.setForeground(Color.BLACK);
		cause_int.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		cause_int.setBounds(431, 166, 102, 49);
		panel.add(cause_int);

		rec_int = new JLabel();
		rec_int.setHorizontalAlignment(SwingConstants.CENTER);
		rec_int.setForeground(Color.BLACK);
		rec_int.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		rec_int.setBounds(431, 213, 102, 49);
		panel.add(rec_int);

		var = new JLabel();
		var.setHorizontalAlignment(SwingConstants.RIGHT);
		var.setIcon(new ImageIcon(TrainingView.class.getResource("/images/right-orange.png")));
		var.setBounds(267, 130, 32, 32);
		panel.add(var);

		cause = new JLabel();
		cause.setVisible(false);
		cause.setIcon(new ImageIcon(TrainingView.class.getResource("/images/right-orange.png")));
		cause.setHorizontalAlignment(SwingConstants.RIGHT);
		cause.setBounds(267, 174, 32, 32);
		panel.add(cause);

		rec = new JLabel();
		rec.setVisible(false);
		rec.setIcon(new ImageIcon(TrainingView.class.getResource("/images/right-orange.png")));
		rec.setHorizontalAlignment(SwingConstants.RIGHT);
		rec.setBounds(267, 221, 32, 32);
		panel.add(rec);

		prom_var = new JLabel();
		prom_var.setHorizontalAlignment(SwingConstants.CENTER);
		prom_var.setForeground(Color.BLACK);
		prom_var.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		prom_var.setBounds(644, 122, 105, 49);
		panel.add(prom_var);

		prom_cause = new JLabel();
		prom_cause.setHorizontalAlignment(SwingConstants.CENTER);
		prom_cause.setForeground(Color.BLACK);
		prom_cause.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		prom_cause.setBounds(644, 166, 105, 49);
		panel.add(prom_cause);

		prom_rec = new JLabel();
		prom_rec.setHorizontalAlignment(SwingConstants.CENTER);
		prom_rec.setForeground(Color.BLACK);
		prom_rec.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 18));
		prom_rec.setBounds(644, 207, 105, 49);
		panel.add(prom_rec);

		lblCantidadDeIntentos = new JLabel("Cantidad de intentos a aprobar:");
		lblCantidadDeIntentos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidadDeIntentos.setForeground(new Color(255, 113, 19));
		lblCantidadDeIntentos.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
		lblCantidadDeIntentos.setBounds(0, 293, 285, 34);
		panel.add(lblCantidadDeIntentos);

		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(new Color(99, 68, 55));
		label.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
		label.setBounds(289, 293, 71, 34);
		panel.add(label);

		//fill data
		if(trainn != null){
			if(trainn.getVar_note() == -1){
				total1.setText(Integer.toString(conf.getCant_questions()));
				var_int.setText(Integer.toString(trainn.getCant_try()));
				cause_int.setText("-");
				rec_int.setText("-");
				prom_var.setText("-");
				prom_cause.setText("-");
				prom_rec.setText("-");
				total2.setText("-");
				total3.setText("-");
				label.setText(Integer.toString(conf.getCant_aprov()-trainn.getCant_aprov()));
			}else if(trainn.getCause_note() == -1){
				total1.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Checkmark_32.png")));
				total1.setText("");
				var.setVisible(false);
				cause.setVisible(true);
				var_int.setText("");
				var_int.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Checkmark_32.png")));
				cause_int.setText(Integer.toString(trainn.getCant_try()));
				rec_int.setText("-");
				prom_var.setText(format.format(trainn.getVar_note()));
				prom_cause.setText("-");
				prom_rec.setText("-");
				total2.setText(Integer.toString(conf.getCant_questions()));
				total3.setText("-");
				label.setText(Integer.toString(conf.getCant_aprov()-trainn.getCant_aprov()));
			}else if(trainn.getRec_note() == -1){
				var.setVisible(false);
				rec.setVisible(true);
				var_int.setText("");
				var_int.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Checkmark_32.png")));
				cause_int.setText("");
				cause_int.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Checkmark_32.png")));
				total1.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Checkmark_32.png")));
				total1.setText("");
				total2.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Checkmark_32.png")));
				total2.setText("");
				total3.setText(Integer.toString(conf.getCant_questions()));
				rec_int.setText(Integer.toString(trainn.getCant_try()));
				prom_var.setText(format.format(trainn.getVar_note()));
				prom_cause.setText(format.format(trainn.getCause_note()));
				prom_rec.setText("-");
				label.setText(Integer.toString(conf.getCant_aprov()-trainn.getCant_aprov()));
			}else{
				var.setVisible(false);
				var_int.setText("");
				var_int.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Checkmark_32.png")));
				cause_int.setText("");
				cause_int.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Checkmark_32.png")));
				rec_int.setText("");
				rec_int.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Checkmark_32.png")));
				total1.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Checkmark_32.png")));
				total1.setText("");
				total2.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Checkmark_32.png")));
				total2.setText("");
				total3.setIcon(new ImageIcon(TrainingView.class.getResource("/images/icons8_Checkmark_32.png")));
				total3.setText("");
				prom_var.setText(format.format(trainn.getVar_note()));
				prom_cause.setText(format.format(trainn.getCause_note()));
				prom_rec.setText(format.format(trainn.getRec_note()));
				lblCantidadDeIntentos.setText("Nota general:");
				label.setText(format.format(trainn.getGeneral_note()));
				btnComenzarPrueba.setVisible(false);
			}
		}else{
			total1.setText(Integer.toString(conf.getCant_questions()));
			total2.setText("-");
			total3.setText("-");
			var_int.setText("-");
			cause_int.setText("-");
			rec_int.setText("-");
			prom_var.setText("-");
			prom_cause.setText("-");
			prom_rec.setText("-");
			label.setText(Integer.toString(conf.getCant_aprov()));
		}
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	private Process findProcess(int id){
		Process process = null;
		ArrayList<Process> p = PrincipalView.processList;
		int i=0;

		while(process == null && i<p.size()){
			if(p.get(i).getProcess_id() == id){
				process = p.get(i);
			}
			i = i + 1;
		}

		return process;
	}
}
