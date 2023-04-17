package trainerInterfaces;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class ImageCharge extends JDialog {


	private static final long serialVersionUID = 5195741109979756134L;

	public static void main(String[] args) {
		try {
			ImageCharge dialog = new ImageCharge(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ImageCharge(Object img) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ImageCharge.class.getResource("/images/icons8_Image_16.png")));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Imagen del Proceso");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel imagelb = new JLabel();
		imagelb.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		imagelb.setBounds(15, 16, 426, 328);
		getContentPane().add(imagelb);
		setResizable(false);
		setBounds(100, 100, 470, 400);
		
		ImageIcon imgIcon = new ImageIcon(img.toString());
        Image imgEscalada = imgIcon.getImage().getScaledInstance(imagelb.getWidth(), imagelb.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
		imagelb.setIcon(iconoEscalado);
	}
}
