package interfaces;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
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

import services.TraceService;
import assistants.TablesInfo;
import classes.Trace;

import com.toedter.calendar.JDateChooser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class TracesSystemView extends JDialog {

	private static final long serialVersionUID = -1484207799427822513L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnExportar;
	private static JTable table;
	private static DefaultTableModel date;
	
	@SuppressWarnings("unchecked")
	ArrayList<Trace> tracesList = (ArrayList<Trace>) TraceService.getTraces();
	private JDateChooser dateChooser;
	String fecha;
	private JButton btnExportarPdf;
	private JButton btnExportarImagen;

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
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setForeground(new Color(47, 46, 65));
		lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblFecha.setBounds(593, 16, 73, 48);
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
		
		btnExportar = new JButton("Exportar excel");
		btnExportar.setIcon(new ImageIcon(TracesSystemView.class.getResource("/imgs/icons8_Microsoft_Excel_16.png")));
		btnExportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnExportar.setBackground(new Color(239, 196, 159));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnExportar.setBackground(new Color(244, 164, 96));
			}
		});
		btnExportar.setForeground(Color.WHITE);
		btnExportar.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnExportar.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		btnExportar.setBackground(new Color(244, 164, 96));
		btnExportar.setBounds(670, 532, 185, 37);
		contentPanel.add(btnExportar);
		
		dateChooser = new JDateChooser();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				String completo = dateChooser.getSpinner().getValue().toString();
				int pos = completo.indexOf(" ");
				fecha = completo.substring(pos + 1);
				completo = fecha;
				pos = completo.indexOf(" ");
				int otherPos = pos;
				completo = completo.substring(pos + 1);
				pos = completo.indexOf(" ");
				otherPos = otherPos + pos;
				completo = completo.substring(pos + 1);
				fecha = fecha.substring(0, otherPos + 1);
				pos = completo.indexOf("T");
				completo = completo.substring(pos + 1);
				fecha = fecha + completo;
				fecha = fecha.substring(4,6) + " " + fecha.substring(0,3) + " " + fecha.substring(7);
				try {
					reloadTable(tracesList, fecha);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		dateChooser.getSpinner().setFont(new Font("Segoe UI", Font.PLAIN, 18));
		dateChooser.setBounds(670, 30, 211, 26);
		contentPanel.add(dateChooser);
		
		btnExportarPdf = new JButton("Exportar PDF");
		btnExportarPdf.setIcon(new ImageIcon(TracesSystemView.class.getResource("/imgs/icons8_PDF_16.png")));
		btnExportarPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnExportarPdf.setBackground(new Color(239, 196, 159));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnExportarPdf.setBackground(new Color(244, 164, 96));
			}
		});
		btnExportarPdf.setForeground(Color.WHITE);
		btnExportarPdf.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnExportarPdf.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		btnExportarPdf.setBackground(new Color(244, 164, 96));
		btnExportarPdf.setBounds(354, 532, 194, 37);
		contentPanel.add(btnExportarPdf);
		
		btnExportarImagen = new JButton("Exportar imagen");
		btnExportarImagen.setIcon(new ImageIcon(TracesSystemView.class.getResource("/imgs/icons8_Image_File_16.png")));
		btnExportarImagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnExportarImagen.setBackground(new Color(239, 196, 159));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnExportarImagen.setBackground(new Color(244, 164, 96));
			}
		});
		btnExportarImagen.setForeground(Color.WHITE);
		btnExportarImagen.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnExportarImagen.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(244, 164, 96)));
		btnExportarImagen.setBackground(new Color(244, 164, 96));
		btnExportarImagen.setBounds(48, 532, 194, 37);
		contentPanel.add(btnExportarImagen);
	}
	
	public static void reloadTable(ArrayList<Trace> trace, String fecha) throws SQLException{
		TablesInfo.getTraces(date, table, trace, fecha);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		table.getColumnModel().getColumn(0).setMaxWidth(130);
		table.getColumnModel().getColumn(2).setMaxWidth(130);
	}
}
