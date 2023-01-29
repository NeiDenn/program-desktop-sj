package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import mantenimientos.GestionUsuario;
import modelo.Usuario;
import utils.MySQLConexion;

public class FrmGestionarUsuario extends JInternalFrame {
	private JLabel lblNewLabel;
	private JLabel lblF01;
	private JPanel panel;
	private JTable tblUsuarios;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JLabel lblTelefono;
	// OJO private
	public JTextField txtContrasena;
	/*
	 * ++++++++++++++++++++++++++++++++++++++
	 * Declarar una variable global idCliente
	 * ++++++++++++++++++++++++++++++++++++++
	 * */
	private int idUsuario;
	
	/*
	 * +++++++++++++++
	 * Tabla_=_modelo
	 * +++++++++++++++
	 * */
	DefaultTableModel modelo = new DefaultTableModel();
	
	private JButton btnLimpiarCampos;
	private JPanel panel_2;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblDni;
	private JLabel lblDireccion;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGestionarUsuario frame = new FrmGestionarUsuario();
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
	public FrmGestionarUsuario() {
		setFrameIcon(new ImageIcon(FrmGestionarUsuario.class.getResource("/img/gestionarusuario01.png")));
		setClosable(true);
		setIconifiable(true);
		setTitle("Usuario - Gestionar Usuarios");
		setBounds(100, 100, 1041, 475);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Administraci\u00F3n De Usuarios");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(433, 10, 365, 32);
		getContentPane().add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(339, 52, 680, 312);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Evento Equivocado
			}
		});
		scrollPane.setBounds(10, 10, 660, 292);
		panel.add(scrollPane);
		
		// datos de la tablaCategoria
		tblUsuarios = new JTable();
		tblUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++ 
				 * Evento_de Mouse en_la_tablatblProducto
				 * ++++++++++++++++++++++++++++++++++++++++
				 */
				int fila_point = tblUsuarios.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    idUsuario = (int) modelo.getValueAt(fila_point, columna_point);
                    enviarDatosUsuarioSeleccionado(idUsuario);
                }
			}
		});
		tblUsuarios.addKeyListener(new KeyAdapter() {
			// Evento Equivocado
		});
		tblUsuarios.setModel(modelo);
		scrollPane.setViewportView(tblUsuarios);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(10, 34, 319, 382);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblTelefono = new JLabel("Contrase\u00F1a");
		lblTelefono.setFont(new Font("Consolas", Font.BOLD, 15));
		lblTelefono.setBounds(20, 203, 115, 25);
		panel_1.add(lblTelefono);
		
		txtContrasena = new JTextField();
		txtContrasena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtContrasena.setBounds(119, 200, 179, 25);
		panel_1.add(txtContrasena);
		txtContrasena.setColumns(10);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNombre.setBounds(20, 55, 95, 25);
		panel_1.add(lblNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Consolas", Font.BOLD, 15));
		lblApellido.setBounds(20, 105, 95, 25);
		panel_1.add(lblApellido);
		
		lblDni = new JLabel("Usuario");
		lblDni.setFont(new Font("Consolas", Font.BOLD, 15));
		lblDni.setBounds(20, 154, 115, 25);
		panel_1.add(lblDni);
		
		lblDireccion = new JLabel("Telefono");
		lblDireccion.setFont(new Font("Consolas", Font.BOLD, 15));
		lblDireccion.setBounds(20, 250, 115, 25);
		panel_1.add(lblDireccion);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombre.setColumns(10);
		txtNombre.setBounds(119, 52, 179, 25);
		panel_1.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtApellido.setColumns(10);
		txtApellido.setBounds(119, 102, 179, 25);
		panel_1.add(txtApellido);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(119, 151, 179, 25);
		panel_1.add(txtUsuario);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(119, 247, 179, 25);
		panel_1.add(txtTelefono);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setBounds(359, 374, 640, 62);
		getContentPane().add(panel_2);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(234, 10, 175, 40);
		panel_2.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * +++++++++ 
				 * ELIMINAR 
				 * ++++++++
				 */
				// instanciar
				GestionUsuario gestionusuario = new GestionUsuario();
				if (idUsuario == 0) {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario!");
				} else {
					if (!gestionusuario.eliminarUsuario(idUsuario)) {
						JOptionPane.showMessageDialog(null, "Usuario Eliminado Correctamente");
						idUsuario = 0;
					} else {
						JOptionPane.showMessageDialog(null, "Error al eliminar usuario!");
					}
				}
			} // end_eliminar
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEliminar.setBackground(new Color(70, 130, 180));
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(28, 10, 175, 40);
		panel_2.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * +++++++++++
				 * ACTUALIZAR
				 * ++++++++++
				 * */
				Usuario usuario = new Usuario();
				GestionUsuario gestionusuario = new GestionUsuario();

				if (idUsuario == 0) {
					JOptionPane.showMessageDialog(null, "Seleccione Un Usuario!");
				} else {
					if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()
							|| txtUsuario.getText().isEmpty() || txtContrasena.getText().isEmpty()
							|| txtTelefono.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Completa todos los campos vacios!");

					} else {
						// enviar todos los datos
						usuario.setNombre(txtNombre.getText().trim());
						usuario.setApellido(txtApellido.getText().trim());
						usuario.setUsuario(txtUsuario.getText().trim());
						usuario.setContrasena(txtContrasena.getText().trim());
						usuario.setTelefono(txtTelefono.getText().trim());
						usuario.setEstado(1);

						if (gestionusuario.actualizarUsuario(usuario, idUsuario)) {
							JOptionPane.showMessageDialog(null, "Usuario Actualizado Correctamente");
							
							idUsuario = 0;
						} else {
							JOptionPane.showMessageDialog(null, "Error al Actualizar Usuario!");
						}
					}
				}
			} // end_actualizar
		});
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnActualizar.setBackground(new Color(34, 139, 34));
		
		btnLimpiarCampos = new JButton("Limpiar Campos");
		btnLimpiarCampos.setBounds(444, 14, 175, 33);
		panel_2.add(btnLimpiarCampos);
		btnLimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++
				 *  clear
				 * ++++++++++++++++++++++++++++++++++++++++
				 * */
				Limpiar();	
			}
		});
		btnLimpiarCampos.setForeground(new Color(0, 0, 139));
		btnLimpiarCampos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLimpiarCampos.setBackground(SystemColor.activeCaptionBorder);
		
		lblF01 = new JLabel("");
		lblF01.setIcon(new ImageIcon(FrmGestionarUsuario.class.getResource("/img/f03.jpg")));
		lblF01.setBounds(0, 0, 1029, 446);
		getContentPane().add(lblF01);
		
		// metodos_en el constructor
		//**************************************************************************
		
		/*
		 * +++++++++++++++++++++++++++++
		 * metodo_Cargar tabla_usuarios
		 * +++++++++++++++++++++++++++++
		 * */
		cargarTablaUsuarios();
		
		//**************************************************************************
	}

	/*
     * ++++++++++++++++++++++++++++++++++++
     * Metodo que envia datos seleccionados
     * cuando el mouse le da click a la tbl
     * ++++++++++++++++++++++++++++++++++++
     */
     void enviarDatosUsuarioSeleccionado(int idUsuario) {
    	String sql = "select * from tb_usuario where idUsuario = '" + idUsuario + "' ";
        try {
            Connection con;
            con = MySQLConexion.getConexion();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            
            if (rs.next()) {
            	txtNombre.setText(rs.getString("nombre"));
            	txtApellido.setText(rs.getString("apellido"));
            	txtUsuario.setText(rs.getString("usuario"));
            	txtContrasena.setText(rs.getString("password"));
            	txtTelefono.setText(rs.getString("telefono"));
            }
            
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar usuario: " + e);
        }
    }

 
     /*
      * ++++++++++++++++++++++++++++++++++++++++++++++++++
      * mostrar los datos de los usuarios en la tabla
      * ++++++++++++++++++++++++++++++++++++++++++++++++++
      */
     private void cargarTablaUsuarios() {
         String sql = "select * from tb_usuario ";
         try {
        	 Connection con;
        	 con = MySQLConexion.getConexion();
        	 Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql);

             modelo.addColumn("IdUsuario");
             modelo.addColumn("Nombre");
             modelo.addColumn("Apellido");
             modelo.addColumn("Usuario");
             modelo.addColumn("Contraseña");
             modelo.addColumn("Telefono");
             modelo.addColumn("Estado");

             while (rs.next()) {

                 Object fila[] = new Object[7];
					for (int i = 0; i < 7; i++) {
						fila[i] = rs.getObject(i + 1);
					}
                 modelo.addRow(fila);
             }
             
             con.close();
         } catch (SQLException e) {
             System.out.println("Error al llenar la tabla usuarios: " + e);
         }
     }
     
	 
	 /*
	  * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	  * metodo para limpiar los campos de la ventana gestionar usuario
	  * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	   */
		void Limpiar() {
			txtNombre.setText("");
			txtApellido.setText("");
			txtUsuario.setText("");
			txtContrasena.setText("");
			txtTelefono.setText("");
		}
} // fin

