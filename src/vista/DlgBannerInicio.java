package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilo.HiloContador;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;

public class DlgBannerInicio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JLabel lblContador;
	private JLabel lblBannerVentas;
	private JLabel lblNewLabel;
	private JLabel lblSanJorge;
	private JLabel lblMinimarket;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgBannerInicio dialog = new DlgBannerInicio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			//*********************************
			dialog.setLocationRelativeTo(null);
			//*********************************
			dialog.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgBannerInicio() {
		setTitle("Bienvenido");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgBannerInicio.class.getResource("/img/128logo.png")));
		setBounds(100, 100, 1200, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.textInactiveText);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		ImageIcon ico = new ImageIcon(getClass().getResource("/img/banner01.jpg"));
		
		lblContador = new JLabel("5");
		lblContador.setForeground(Color.WHITE);
		lblContador.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblContador.setBounds(113, 147, 45, 32);
		contentPanel.add(lblContador);
		
		lblNewLabel = new JLabel("Bienvenido Al Sistema De Ventas");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Constantia", Font.BOLD, 50));
		lblNewLabel.setBounds(207, 24, 807, 62);
		contentPanel.add(lblNewLabel);
		
		lblSanJorge = new JLabel("San Jorge");
		lblSanJorge.setForeground(Color.DARK_GRAY);
		lblSanJorge.setFont(new Font("Constantia", Font.BOLD, 46));
		lblSanJorge.setBounds(562, 146, 242, 57);
		contentPanel.add(lblSanJorge);
				
		lblMinimarket = new JLabel("Minimarket  - ");
		lblMinimarket.setForeground(Color.DARK_GRAY);
		lblMinimarket.setFont(new Font("Constantia", Font.BOLD, 46));
		lblMinimarket.setBounds(232, 147, 320, 54);
		contentPanel.add(lblMinimarket);
						
		lblNewLabel_1 = new JLabel("OPEN");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 18));
		lblNewLabel_1.setBounds(155, 538, 56, 27);
		contentPanel.add(lblNewLabel_1);
						
		lblBannerVentas = new JLabel("");
						
		lblBannerVentas.setBounds(0, 0, 1200, 700);
								
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		ImageIcon img = new ImageIcon(ico.getImage().getScaledInstance(lblBannerVentas.getWidth(), lblBannerVentas.getHeight(), Image.SCALE_SMOOTH));
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		lblBannerVentas.setIcon(img);
								
		contentPanel.add(lblBannerVentas);
		
		/*+++++++++++++++++++++++++++++++++++++
		 * iniciar el contador automaticamente
		 * +++++++++++++++++++++++++++++++++++++
		 * */
		iniciarContador();
		
	}
	
	/*+++++++++++++++++++
	 * iniciar contador
	 * ++++++++++++++++++
	 * */
	void iniciarContador() {
		HiloContador h = new HiloContador(this);
		h.start();
	}
}
