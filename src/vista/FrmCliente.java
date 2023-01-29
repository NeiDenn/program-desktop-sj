package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import mantenimientos.GestionCliente;
import modelo.Cliente;
import utils.MySQLConexion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmCliente extends JInternalFrame {
	
	private JLabel lblWallpaper03;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtDni;
	private JTextField txtTelefono;
	private JButton btnGuardar;
	private JButton btnLimpiar;
	private JTextField txtDireccion;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCliente frame = new FrmCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCliente() {
		setFrameIcon(new ImageIcon(FrmCliente.class.getResource("/img/nuevo02.png")));
		setTitle("San Jorge - Nuevo Cliente");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 735, 375);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Agregar Nuevo Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(228, 10, 292, 36);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nombres");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(117, 78, 84, 28);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(117, 124, 84, 28);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Dni");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(117, 172, 84, 32);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Telefono");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(117, 231, 101, 28);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Direccion");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(117, 276, 84, 33);
		getContentPane().add(lblNewLabel_5);
		
		txtNombres = new JTextField();
		txtNombres.setToolTipText("No se permiten datos numericos!");
		txtNombres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				 * Capturar_el_teclado_para_no_permitir CARACTERES DE NUMEROS
				 * -------------
				 * (PARA EL NOMBRE)
				 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				 * */
				if(!Character.isAlphabetic(e.getKeyChar()))
					e.consume();
				if(txtNombres.getText().length() >=29) //no_permite_mas_de_29_letras
					e.consume();
			}
		});
		txtNombres.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombres.setBounds(250, 79, 164, 28);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setToolTipText("No se permiten datos numericos!");
		txtApellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				 * Capturar_el_teclado_para_no_permitir CARACTERES DE NUMEROS
				 * -------------
				 * (PARA LOS APELLIDOS)
				 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				 * */
				if(!Character.isAlphabetic(e.getKeyChar()))
					e.consume();
				if(txtApellidos.getText().length() >=29) //no_permite_mas_de_29_letras
					e.consume();
			}
		});
		txtApellidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(250, 126, 164, 27);
		getContentPane().add(txtApellidos);
		
		txtDni = new JTextField();
		txtDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				 * Capturar_el_teclado_para_no_permitir CARACTERES DE LETRA
				 * -------------
				 * (PARA EL DNI)
				 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				 * */
				if (!Character.isDigit(e.getKeyChar()))
					JOptionPane.showMessageDialog(null, "Solo se aceptan datos NUMERICOS en este campo");
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
				if (txtDni.getText().length() >= 8)
					e.consume();
			}
		});
		txtDni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDni.setColumns(10);
		txtDni.setBounds(250, 175, 164, 28);
		getContentPane().add(txtDni);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				 * Capturar_el_teclado_para_no_permitir CARACTERES DE LETRA
				 * ------------------
				 * (PARA EL TELEFONO)
				 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				 * */
				if (!Character.isDigit(e.getKeyChar()))
					JOptionPane.showMessageDialog(null, "Solo se aceptan datos NUMERICOS en este campo");
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
				if (txtTelefono.getText().length() >= 9)
					e.consume();
			}
		});
		txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(250, 232, 164, 28);
		getContentPane().add(txtTelefono);
		
		btnGuardar = new JButton("Guardar Cliente");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*
			 * +++++++++++++++++++++
			 * BOTON GUARDAR CLIENTE
			 * +++++++++++++++++++++
			 * */
			guardarCliente();
			
			}
		});
		btnGuardar.setBackground(SystemColor.inactiveCaption);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGuardar.setBounds(487, 107, 174, 49);
		getContentPane().add(btnGuardar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Limpiar los espacios de los txt
				 * de nuevo cliente
				 * ++++++++++++++++++++++++++++++++
				 * */
				txtNombres.setText("");
				txtNombres.requestFocus();
				txtApellidos.setText("");
				txtDni.setText("");
				txtTelefono.setText("");
				txtDireccion.setText("");
			}
		});
		btnLimpiar.setBackground(Color.LIGHT_GRAY);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLimpiar.setBounds(487, 195, 174, 43);
		getContentPane().add(btnLimpiar);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(250, 279, 164, 28);
		getContentPane().add(txtDireccion);
		
		lblWallpaper03 = new JLabel("");
		lblWallpaper03.setIcon(new ImageIcon(FrmCliente.class.getResource("/img/f01.jpg")));
		lblWallpaper03.setBounds(0, 0, 723, 346);
		getContentPane().add(lblWallpaper03);
	}
	
	/*
	 * +++++++++++++++
	 * Guardar cliente
	 * +++++++++++++++
	 * */
	void guardarCliente() {
		// instanciar
		Cliente cliente = new Cliente();
		GestionCliente gestioncliente = new GestionCliente();
		
		// validando
		if (!txtNombres.getText().isEmpty() && !txtApellidos.getText().isEmpty() && !txtDni.getText().isEmpty()) {
			
			if (!gestioncliente.existeCliente(txtDni.getText().trim())) {
				
				cliente.setNombre(txtNombres.getText().trim());
				cliente.setApellido(txtApellidos.getText().trim());
				cliente.setDni(txtDni.getText().trim());
				cliente.setTelefono(txtTelefono.getText().trim());
				cliente.setDireccion(txtDireccion.getText().trim());
				cliente.setEstado(1);
				
				if (gestioncliente.guardarCliente(cliente)) {
					JOptionPane.showMessageDialog(null, "Cliente Guardado Correctamente");
				} else {
					JOptionPane.showMessageDialog(null, "Error al Guardar Cliente");					
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "El cliente ya está registrado en la BD, ingrese otro!");	
				txtDni.requestFocus();
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Complete los campos vacios!");
			txtNombres.requestFocus();
		}
	}

} // fin
