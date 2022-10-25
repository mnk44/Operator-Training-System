package systemInterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;

public class TracesSystemView extends JDialog {

	private static final long serialVersionUID = -1484207799427822513L;
	private final JPanel contentPanel = new JPanel();

	public static void main(String[] args) {
		try {
			TracesSystemView dialog = new TracesSystemView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TracesSystemView() {
		setTitle("Control de acciones");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TracesSystemView.class.getResource("/imgs/logo.png")));
		setModal(true);
		setBounds(100, 100, 915, 625);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

}
