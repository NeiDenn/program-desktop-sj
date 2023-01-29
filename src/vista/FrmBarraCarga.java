package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilo.HiloBarra;

import javax.swing.JProgressBar;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FrmBarraCarga extends JFrame {

	private JPanel contentPane;
	/*
	 * ++++++++++++++++++++++++++++++
	 * Que sean publicas y estaticas
	 * ++++++++++++++++++++++++++++++
	 * */
	public static JProgressBar prbCarga;
	public static JLabel lblMensajes02;
	private JLabel lblNewLabel;
	public static JLabel lblMensajes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBarraCarga frame = new FrmBarraCarga();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmBarraCarga() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmBarraCarga.class.getResource("/img/128logo.png")));
		setTitle("EL SISTEMA ESTA CARGANDO...");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		prbCarga = new JProgressBar();
		prbCarga.setFont(new Font("Tahoma", Font.BOLD, 13));
		prbCarga.setBounds(10, 119, 876, 90);
		prbCarga.setStringPainted(true);
		prbCarga.setForeground(SystemColor.textHighlight);
		contentPane.add(prbCarga);
		
		lblMensajes02 = new JLabel("El sistema San Jorge est\u00E1 iniciando, espere un momento.");
		lblMensajes02.setForeground(Color.WHITE);
		lblMensajes02.setBounds(95, 20, 707, 42);
		lblMensajes02.setFont(new Font("Constantia", Font.BOLD, 26));
		contentPane.add(lblMensajes02);
		
		lblMensajes = new JLabel("");
		lblMensajes.setForeground(Color.WHITE);
		lblMensajes.setFont(new Font("Constantia", Font.BOLD, 25));
		lblMensajes.setBounds(326, 67, 259, 42);
		contentPane.add(lblMensajes);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmBarraCarga.class.getResource("/img/carga04.jpg")));
		lblNewLabel.setBounds(0, 0, 896, 237);
		contentPane.add(lblNewLabel);
		
		/*
		 * ++++++++++++++++++++++++++++++++++++++++++
		 * Activar el HiloBarra despues del Logueo
		 * ++++++++++++++++++++++++++++++++++++++++++
		 * */
		inicaBarra();
		
		
	}
	
	
	/*
	 * ++++++++++++++++++++++++++++++++++++
	 * Instaciar el HiloBarra
	 * ++++++++++++++++++++++++++++++++++++
	 * */
	void inicaBarra() {
		HiloBarra h = new HiloBarra(this);
		h.start();
	}
}
