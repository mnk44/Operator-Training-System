package systemInterface;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ListSelectionModel;

import systemClass.Trace;
import systemLogic.TablesInfo;
import systemServices.TraceService;
import com.toedter.calendar.JDateChooser;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class TracesSystemView extends JDialog {

	private static final long serialVersionUID = -1484207799427822513L;
	private final JPanel contentPanel = new JPanel();
	private static JTextField txtBuscar;
	private JButton button;
	private static JTable table;
	private static DefaultTableModel date;
	
	@SuppressWarnings("unchecked")
	ArrayList<Trace> tracesList = (ArrayList<Trace>) TraceService.getTraces();

	public static void main(String[] args) {
		try {
			TracesSystemView dialog = new TracesSystemView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TracesSystemView() throws SQLException {
		setTitle("Control de acciones");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TracesSystemView.class.getResource("/imgs/logo.png")));
		setModal(true);
		setBounds(100, 100, 915, 625);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TracesSystemView.class.getResource("/imgs/search.png")));
		label.setBounds(28, 16, 48, 48);
		contentPanel.add(label);
		
		txtBuscar = new JTextField();
		txtBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(txtBuscar.getText().equals("Buscar")){
					txtBuscar.setCaretPosition(0);
				}
			}
		});
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(txtBuscar.getText().equals("Buscar")){
					if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
						arg0.consume();
						getToolkit().beep();
					}else{
						txtBuscar.setText("");
						txtBuscar.setForeground(Color.BLACK);
							//reloadTable(FindObjects.findUsers(userList, findUser.getText() + arg0.getKeyChar()));
					}
				}else if(txtBuscar.getText().length() == 1 && arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
					txtBuscar.setText("Buscar");
					txtBuscar.setForeground(Color.LIGHT_GRAY);
					arg0.consume();
					txtBuscar.setCaretPosition(0);
					try {
						reloadTable(tracesList);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(arg0.getKeyChar() == KeyEvent.VK_BACK_SPACE){
						//reloadTable(FindObjects.findUsers(userList, findUser.getText().substring(0, findUser.getText().length()-1)));
				}else{
						//reloadTable(FindObjects.findUsers(userList, findUser.getText() + arg0.getKeyChar()));
				}
			}
		});
		txtBuscar.setText("Buscar");
		txtBuscar.setForeground(Color.LIGHT_GRAY);
		txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(79, 30, 500, 27);
		contentPanel.add(txtBuscar);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setForeground(new Color(47, 46, 65));
		lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblFecha.setBounds(620, 16, 73, 48);
		contentPanel.add(lblFecha);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(28, 75, 853, 441);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(24);
		table.setIntercellSpacing(new Dimension(2, 2));
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setFillsViewportHeight(true);
		table.setBorder(null);
		table.setBackground(Color.WHITE);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		button = new JButton("Aceptar");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				button.setBackground(new Color(239, 196, 159));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				button.setBackground(new Color(244, 164, 96));
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Segoe UI", Font.BOLD, 18));
		button.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		button.setBackground(new Color(244, 164, 96));
		button.setBounds(377, 532, 153, 37);
		contentPanel.add(button);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getSpinner().setFont(new Font("Segoe UI", Font.PLAIN, 18));
		dateChooser.setBounds(696, 30, 185, 26);
		contentPanel.add(dateChooser);
		
		reloadTable(tracesList);
	}
	
	public static void reloadTable(ArrayList<Trace> trace) throws SQLException{
		TablesInfo.getTraces(date, table, trace);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(0).setMaxWidth(130);
		table.getColumnModel().getColumn(2).setMaxWidth(130);
		if(txtBuscar.getText().equals("Buscar")){
			txtBuscar.setCaretPosition(0);
		}
	}
}
