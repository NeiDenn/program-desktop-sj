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

import mantenimientos.GestionCliente;
import modelo.Cliente;
import utils.MySQLConexion;
import java.awt.event.KeyEvent;

public class FrmGestionarCliente extends JInternalFrame {
	private JLabel lblNewLabel;
	private JLabel lblF01;
	private JPanel panel;
	private JTable tblCliente;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JLabel lblTelefono;
	// OJO private
	public JTextField txtTelefono;
	/*
	 * ++++++++++++++++++++++++++++++++++++++
	 * Declarar una variable global idCliente
	 * ++++++++++++++++++++++++++++++++++++++
	 * */
	private int idCliente;
	
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
	private JTextField txtDni;
	private JTextField txtDireccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGestionarCliente frame = new FrmGestionarCliente();
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
	public FrmGestionarCliente() {
		setFrameIcon(new ImageIcon(FrmGestionarCliente.class.getResource("/img/gestionar01.png")));
		setClosable(true);
		setIconifiable(true);
		setTitle("Cliente - Gestionar Cliente");
		setBounds(100, 100, 1041, 475);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Administracion De Clientes");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(475, 10, 353, 32);
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
		tblCliente = new JTable();
		tblCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++ 
				 * Evento_de Mouse en_la_tablatblProducto
				 * ++++++++++++++++++++++++++++++++++++++++
				 */
				int fila_point = tblCliente.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    idCliente = (int) modelo.getValueAt(fila_point, columna_point);
                    enviarDatosClientesSeleccionado(idCliente);
                }
			}
		});
		tblCliente.addKeyListener(new KeyAdapter() {
			// Evento Equivocado
		});
		tblCliente.setModel(modelo);
		scrollPane.setViewportView(tblCliente);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(10, 42, 319, 351);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Consolas", Font.BOLD, 15));
		lblTelefono.setBounds(20, 203, 115, 25);
		panel_1.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// null
			}
		});
		txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTelefono.setBounds(119, 200, 179, 25);
		panel_1.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNombre.setBounds(20, 55, 95, 25);
		panel_1.add(lblNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Consolas", Font.BOLD, 15));
		lblApellido.setBounds(20, 105, 95, 25);
		panel_1.add(lblApellido);
		
		lblDni = new JLabel("Dni");
		lblDni.setFont(new Font("Consolas", Font.BOLD, 15));
		lblDni.setBounds(20, 154, 115, 25);
		panel_1.add(lblDni);
		
		lblDireccion = new JLabel("Direccion");
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
		
		txtDni = new JTextField();
		txtDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// null
			}
		});
		txtDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDni.setColumns(10);
		txtDni.setBounds(119, 151, 179, 25);
		panel_1.add(txtDni);
		
		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(119, 247, 190, 25);
		panel_1.add(txtDireccion);
		
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
				/*+++++++++
				 * ELIMINAR
				 * ++++++++
				 * */
				GestionCliente gestionCliente = new GestionCliente();
		        if (idCliente == 0) {
		            JOptionPane.showMessageDialog(null, "Seleccione un cliente!");
		        } else {
		            if (!gestionCliente.eliminarCliente(idCliente)) {
		                JOptionPane.showMessageDialog(null, "Cliente Eliminado Correctamente!");
		                //
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar cliente!");
		                Limpiar();
		            }
		        }
			} // end_eliminar
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(70, 130, 180));
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(28, 10, 175, 40);
		panel_2.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*+++++++++++
				 * ACTUALIZAR
				 * ++++++++++
				 * */
				if (txtNombre.getText().isEmpty() && txtApellido.getText().isEmpty() && txtDni.getText().isEmpty()
						&& txtTelefono.getText().isEmpty() && txtDireccion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione un cliente!");
				} else {
					// instanciar
					Cliente cliente = new Cliente();
					GestionCliente gestionCliente = new GestionCliente();

					cliente.setNombre(txtNombre.getText().trim());
					cliente.setApellido(txtApellido.getText().trim());
					cliente.setDni(txtDni.getText().trim());
					cliente.setTelefono(txtTelefono.getText().trim());
					cliente.setDireccion(txtDireccion.getText().trim());

					if (gestionCliente.actualizarCliente(cliente, idCliente)) {
						JOptionPane.showMessageDialog(null, "Datos del cliente actualizados correctamente!");
						//
					} else {
						JOptionPane.showMessageDialog(null, "Error al actualizar al cliente!");
					}
				}					
			} // end_actualizar
		});
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnActualizar.setBackground(new Color(34, 139, 34));
		
		btnLimpiarCampos = new JButton("Limpiar Campos");
		btnLimpiarCampos.setBounds(444, 14, 154, 33);
		panel_2.add(btnLimpiarCampos);
		btnLimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++
				 *  mostrar_datos despues del proceso hecho
				 * ++++++++++++++++++++++++++++++++++++++++
				 * */
				Limpiar();
			
				
			}
		});
		btnLimpiarCampos.setForeground(new Color(0, 0, 139));
		btnLimpiarCampos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLimpiarCampos.setBackground(SystemColor.activeCaptionBorder);
		
		lblF01 = new JLabel("");
		lblF01.setIcon(new ImageIcon(FrmGestionarCliente.class.getResource("/img/f01.jpg")));
		lblF01.setBounds(0, 0, 1029, 446);
		getContentPane().add(lblF01);
		
		// metodos_en el constructor
		//**************************************************************************
		
		/*
		 * +++++++++++++++++++++++++++++
		 * metodo_Cargar tabla_productos
		 * +++++++++++++++++++++++++++++
		 * */
		cargarTablaClientes();
		
		//**************************************************************************
	}
	
	/*
	 * ++++++++++++++++++++++++++++++++++++
	 *  Limpiar datos de la tabla productos
	 * ++++++++++++++++++++++++++++++++++++
	 * */
	void limpiarDatosTablaProducto() {
   	 int fila = tblCliente.getRowCount();
			for (int i = fila-1; i>=0; i--) {
				modelo.removeRow(i);
			}
    }

	/*
     * ++++++++++++++++++++++++++++++++++++
     * Metodo que envia datos seleccionados
     * ++++++++++++++++++++++++++++++++++++
     */
     void enviarDatosClientesSeleccionado(int idCliente) {
    	String sql = "select * from tb_cliente where idCliente = '" + idCliente + "' ";
        try {
            Connection con;
            con = MySQLConexion.getConexion();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            
            if (rs.next()) {
            	txtNombre.setText(rs.getString("nombre"));
            	txtApellido.setText(rs.getString("apellido"));
            	txtDni.setText(rs.getString("dni"));
            	txtTelefono.setText(rs.getString("telefono"));
            	txtDireccion.setText(rs.getString("direccion"));
            }
            
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar cliente: " + e);
        }
    }

 

     /*
      * ++++++++++++++++++++++++++++++++++++++++++++++++++
      * cargaTablaClientes();
      * metodo_para_mostrar_todos_los_clientes_registrados
      * ++++++++++++++++++++++++++++++++++++++++++++++++++
      */
     private void cargarTablaClientes() {
         String sql = "select * from tb_cliente ";
         try {
        	 Connection con;
        	 con = MySQLConexion.getConexion();
        	 Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql);

             modelo.addColumn("IdCliente");
             modelo.addColumn("Nombre");
             modelo.addColumn("Apellido");
             modelo.addColumn("Dni");
             modelo.addColumn("Telefono");
             modelo.addColumn("Direccion");
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
             System.out.println("Error al llenar la tabla clientes: " + e);
         }
     }
     
	 
	 /*
	  * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	  * metodo para limpiar los campos de la ventana gestionar cliente
	  * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	   */
		void Limpiar() {
			txtNombre.setText("");
			txtApellido.setText("");
			txtDni.setText("");
			txtTelefono.setText("");
			txtDireccion.setText("");
		}
} // fin

