package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mantenimientos.GestionUsuario;
import modelo.Usuario;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmUsuario extends JInternalFrame {
	
	private JLabel lblWallpaper03;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtUsuario;
	private JButton btnGuardarUsuario;
	private JButton btnLimpiar;
	private JTextField txtTelefono;
	private JPasswordField txtContrasena;
	private JCheckBox chBoxVerClave;
	private JTextField txtContrasenaVisible;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUsuario frame = new FrmUsuario();
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
	public FrmUsuario() {
		setFrameIcon(new ImageIcon(FrmUsuario.class.getResource("/img/newuser01.png")));
		setTitle("Usuario - Nuevo Usuario");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 703, 375);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Agregar Nuevo Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(189, 10, 346, 36);
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
		
		lblNewLabel_3 = new JLabel("Usuario");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(117, 172, 84, 32);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Contrase\u00F1a");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(112, 226, 101, 28);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Telefono");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(117, 276, 84, 33);
		getContentPane().add(lblNewLabel_5);
		
		txtNombres = new JTextField();
		txtNombres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * ******************************
				 * No_se_permiten_datos_numericos
				 * ******************************
				 * */
				if (!Character.isAlphabetic(e.getKeyChar()))
					JOptionPane.showMessageDialog(null, "No se permite datos numericos!");
				if (!Character.isAlphabetic(e.getKeyChar()))
					e.consume();
				if (txtNombres.getText().length() >= 30) // no permite_mas_de_30_letras
					e.consume();
			}
		});
		txtNombres.setToolTipText("No se permiten datos numericos!");
		txtNombres.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombres.setBounds(250, 79, 182, 28);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * ******************************
				 * No_se_permiten_datos_numericos
				 * ******************************
				 * */
				if (!Character.isAlphabetic(e.getKeyChar()))
					JOptionPane.showMessageDialog(null, "No se permite datos numericos!");
				if (!Character.isAlphabetic(e.getKeyChar()))
					e.consume();
				if (txtApellidos.getText().length() >= 30) // no permite_mas_de_30_letra
					e.consume();
			}
		});
		txtApellidos.setToolTipText("No se permiten datos numericos!");
		txtApellidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(250, 126, 182, 27);
		getContentPane().add(txtApellidos);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(250, 175, 182, 28);
		getContentPane().add(txtUsuario);
		
		btnGuardarUsuario = new JButton("Guardar Usuario");
		btnGuardarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*
			 * +++++++++++++++++++++
			 * BOTON GUARDAR USUARIO
			 * +++++++++++++++++++++
			 * */
				guardarUsuario();
			
			}
		});
		btnGuardarUsuario.setBackground(SystemColor.activeCaption);
		btnGuardarUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGuardarUsuario.setBounds(475, 113, 175, 51);
		getContentPane().add(btnGuardarUsuario);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*
			 * ++++++++++++++++++++++++++++++++
			 * Limpiar_los espacios_de_los_txt
			 * ++++++++++++++++++++++++++++++++
			 * */
				txtNombres.setText("");
				txtNombres.requestFocus();
				txtApellidos.setText("");
				txtUsuario.setText("");
				txtContrasena.setText("");
				txtTelefono.setText("");
				txtContrasenaVisible.setText("");
			}
		});
		btnLimpiar.setBackground(SystemColor.inactiveCaption);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLimpiar.setBounds(475, 192, 175, 45);
		getContentPane().add(btnLimpiar);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * **********************************
				 * No_se_permiten_caracteres_de_texto
				 * **********************************
				 * */
				if (!Character.isDigit(e.getKeyChar()))
					JOptionPane.showMessageDialog(null, "No se permite caracteres de texto!");
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
				if (txtTelefono.getText().length() >= 9) // no permite_mas_de_9_numeros
					e.consume();
			}
		});
		txtTelefono.setToolTipText("No se permiten caracteres de texto!");
		txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(250, 279, 182, 28);
		getContentPane().add(txtTelefono);
		
		txtContrasena = new JPasswordField();
		txtContrasena.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtContrasena.setBounds(251, 227, 181, 28);
		getContentPane().add(txtContrasena);
		
		chBoxVerClave = new JCheckBox("");
		chBoxVerClave.setForeground(Color.BLACK);
		chBoxVerClave.setBackground(Color.BLUE);
		chBoxVerClave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * ++++++++++
				 * Ver_Clave
				 * ++++++++++
				 * */
				if (chBoxVerClave.isSelected() == true) {
					String pass = "";
					char[] passIngresado = txtContrasena.getPassword();
					for (int i = 0; i < passIngresado.length; i++) {
						pass += passIngresado[i];
					}
					txtContrasenaVisible.setText(pass);
					txtContrasena.setVisible(false);
					txtContrasenaVisible.setVisible(true);
				} else {
					String passwordIngresado = txtContrasenaVisible.getText().trim();
					txtContrasena.setText(passwordIngresado);
					txtContrasena.setVisible(true);
					txtContrasenaVisible.setVisible(false);
				}
			}
		});
		chBoxVerClave.setBounds(432, 227, 21, 28);
		getContentPane().add(chBoxVerClave);
		
		txtContrasenaVisible = new JTextField();
		txtContrasenaVisible.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtContrasenaVisible.setBounds(251, 227, 181, 28);
		getContentPane().add(txtContrasenaVisible);
		txtContrasenaVisible.setColumns(10);
		
		lblWallpaper03 = new JLabel("");
		lblWallpaper03.setIcon(new ImageIcon(FrmUsuario.class.getResource("/img/f01.jpg")));
		lblWallpaper03.setBounds(0, 0, 691, 346);
		getContentPane().add(lblWallpaper03);
		
		// metodo mostrar la contraseña
		txtContrasena.setVisible(true);
		txtContrasenaVisible.setVisible(false);
	} // fin constructor
	
	/*
	 * +++++++++++++++
	 * Guardar cliente
	 * +++++++++++++++
	 * */
	void guardarUsuario() {
		if (txtNombres.getText().isEmpty() || txtApellidos.getText().isEmpty() || txtUsuario.getText().isEmpty()
				|| txtContrasena.getText().isEmpty() || txtTelefono.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Completa todos los campos vacios");
		} else {
			// instanciar
			Usuario usuario = new Usuario()	;
			GestionUsuario gestionusuario = new GestionUsuario();
			
			// validacion si el usuario si ya esta registrado en la BD
			if (!gestionusuario.existeUsuario(txtUsuario.getText().trim())) {
				// enviamos datos del usuario
				usuario.setNombre(txtNombres.getText().trim());
				usuario.setApellido(txtApellidos.getText().trim());
				usuario.setUsuario(txtUsuario.getText().trim());
				usuario.setContrasena(txtContrasena.getText().trim());
				usuario.setTelefono(txtTelefono.getText().trim());
				usuario.setEstado(1);

				if (gestionusuario.guardarUsuario(usuario)) {
					JOptionPane.showMessageDialog(null, "Usuario Registrado Correctamente");
				} else {
					JOptionPane.showMessageDialog(null, "Error al registrar Usuario!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "El Usuario ya esta registrado en la BD, ingrese otro.");
			}
		}
	}
} // fin
