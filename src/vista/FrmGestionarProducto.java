package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
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
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import mantenimientos.GestionCategoria;
import mantenimientos.GestionProducto;
import modelo.Categoria;
import modelo.Producto;
import utils.MySQLConexion;
import java.awt.Color;

public class FrmGestionarProducto extends JInternalFrame {
	private JLabel lblNewLabel;
	private JLabel lblF01;
	private JPanel panel;
	private JTable tblProducto;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JLabel lblDescripcion;
	// OJO private
	public JTextField txtDescripcion;
	/*
	 * +++++++++++++++++++++++++++++++++++++++
	 * Declarar una veriable global idProducto
	 * +++++++++++++++++++++++++++++++++++++++
	 * */
	private int idProducto;
	int obtenerIdCategoriaCombo = 0;
	
	/*
	 * +++++++++++++++
	 * Tabla = modelo
	 * +++++++++++++++
	 * */
	DefaultTableModel modelo = new DefaultTableModel();
	
	private JButton btnLimpiarCampos;
	private JPanel panel_2;
	private JLabel lblNombre;
	private JLabel lblCantidad;
	private JLabel lblPrecio;
	private JLabel lblCategoria;
	private JLabel lblEstado;
	private JTextField txtNombre;
	private JTextField txtCantidad;
	private JTextField txtPrecio;
	private JComboBox cboIgv;
	private JComboBox cboCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGestionarProducto frame = new FrmGestionarProducto();
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
	public FrmGestionarProducto() {
		setFrameIcon(new ImageIcon(FrmGestionarProducto.class.getResource("/img/producto.png")));
		setClosable(true);
		setIconifiable(true);
		setTitle("Producto - Gestionar Producto");
		setBounds(100, 100, 1041, 475);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Administracion De Productos");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(447, 10, 373, 32);
		getContentPane().add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaptionBorder);
		panel.setBounds(339, 52, 671, 312);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Evento Equivocado
			}
		});
		scrollPane.setBounds(10, 10, 651, 292);
		panel.add(scrollPane);
		
		// datos de la tablaCategoria
		tblProducto = new JTable();
		tblProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++ 
				 * Evento_de Mouse en_la_tablatblProducto
				 * ++++++++++++++++++++++++++++++++++++++++
				 */
				int fila_point = tblProducto.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    idProducto = (int) modelo.getValueAt(fila_point, columna_point);
                    enviarDatosProductoSeleccionado(idProducto);
                }
			}
		});
		tblProducto.addKeyListener(new KeyAdapter() {
			// Evento Equivocado
		});
		tblProducto.setModel(modelo);
		scrollPane.setViewportView(tblProducto);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.scrollbar);
		panel_1.setBounds(10, 34, 319, 382);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Consolas", Font.BOLD, 15));
		lblDescripcion.setBounds(20, 203, 115, 25);
		panel_1.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDescripcion.setBounds(119, 200, 179, 25);
		panel_1.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Consolas", Font.BOLD, 15));
		lblNombre.setBounds(20, 55, 95, 25);
		panel_1.add(lblNombre);
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Consolas", Font.BOLD, 15));
		lblCantidad.setBounds(20, 105, 95, 25);
		panel_1.add(lblCantidad);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Consolas", Font.BOLD, 15));
		lblPrecio.setBounds(20, 154, 115, 25);
		panel_1.add(lblPrecio);
		
		lblCategoria = new JLabel("IGV");
		lblCategoria.setFont(new Font("Consolas", Font.BOLD, 15));
		lblCategoria.setBounds(20, 250, 115, 25);
		panel_1.add(lblCategoria);
		
		lblEstado = new JLabel("Categoria");
		lblEstado.setFont(new Font("Consolas", Font.BOLD, 15));
		lblEstado.setBounds(20, 302, 115, 25);
		panel_1.add(lblEstado);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombre.setColumns(10);
		txtNombre.setBounds(119, 52, 179, 25);
		panel_1.add(txtNombre);
		
		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(119, 102, 179, 25);
		panel_1.add(txtCantidad);
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(119, 151, 179, 25);
		panel_1.add(txtPrecio);
		
		cboIgv = new JComboBox();
		cboIgv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboIgv.setModel(new DefaultComboBoxModel(new String[] {"Seleccione IGV", "No tiene IGV", "12%", "14%"}));
		cboIgv.setBounds(119, 249, 179, 24);
		panel_1.add(cboIgv);
		
		cboCategoria = new JComboBox();
		cboCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboCategoria.setBounds(119, 301, 179, 24);
		panel_1.add(cboCategoria);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.scrollbar);
		panel_2.setBounds(370, 374, 640, 62);
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
				GestionProducto controlProducto = new GestionProducto();
		        if (idProducto == 0) {
		            JOptionPane.showMessageDialog(null, "Seleccione un Producto!");
		        } else {
		            if (!controlProducto.eliminarProducto(idProducto)) {
		                JOptionPane.showMessageDialog(null, "Producto Eliminado!");
		                //Limpiar();
		                //limpiarDatosTablaProducto();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar producto!");
		            }
		        }
				
			} // end eliminar
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnEliminar.setBackground(SystemColor.activeCaption);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(28, 10, 175, 40);
		panel_2.add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*+++++++++++
				 * ACTUALIZAR
				 * ++++++++++
				 * */
				Producto producto = new Producto();
				GestionProducto controlProducto = new GestionProducto();
				String igv = "";
				String categoria = "";
				igv = cboIgv.getSelectedItem().toString().trim();
				categoria = cboCategoria.getSelectedItem().toString().trim();

				// validar campos
				if (txtNombre.getText().isEmpty() || txtCantidad.getText().isEmpty()
						|| txtPrecio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos");
				} else {
					if (igv.equalsIgnoreCase("Seleccione IGV")) {
						JOptionPane.showMessageDialog(null, "Seleccione IGV");
					} else {
						if (categoria.equalsIgnoreCase("Seleccione Categoria")) {
							JOptionPane.showMessageDialog(null, "Seleccione Categoria");
						} else {
							try {
								producto.setNombre(txtNombre.getText().trim());
								producto.setCantidad(Integer.parseInt(txtCantidad.getText().trim()));
								String precioTXT = "";
								double Precio = 0.0;
								precioTXT = txtPrecio.getText().trim();
								boolean aux = false;
								/*
								 * Si el usuario ingresa , (coma) como punto decimal, lo transformamos a punto
								 * (.)
								 */
								for (int i = 0; i < precioTXT.length(); i++) {
									if (precioTXT.charAt(i) == ',') {
										String precioNuevo = precioTXT.replace(",", ".");
										Precio = Double.parseDouble(precioNuevo);
										aux = true;
									}
								}
								// evaluar la condicion
								if (aux == true) {
									producto.setPrecio(Precio);
								} else {
									Precio = Double.parseDouble(precioTXT);
									producto.setPrecio(Precio);
								}

								producto.setDescripcion(txtDescripcion.getText().trim());
								// Porcentaje IVA
								if (igv.equalsIgnoreCase("No tiene IGV")) {
									producto.setPorcentajeIgv(0);
								} else if (igv.equalsIgnoreCase("12%")) {
									producto.setPorcentajeIgv(12);
								} else if (igv.equalsIgnoreCase("14%")) {
									producto.setPorcentajeIgv(14);
								}

								// idcategoria - cargar metodo que obtiene el id de categoria
								IdCategoria();
								producto.setIdCategoria(obtenerIdCategoriaCombo);
								producto.setEstado(1);

								if (controlProducto.actualizarProducto(producto, idProducto)) {
									JOptionPane.showMessageDialog(null, "Registro Actualizado");
									//Limpiar();
									//limpiarDatosTablaProducto();
								} else {
									JOptionPane.showMessageDialog(null, "Error al Actualizar");
								}
							} catch (HeadlessException | NumberFormatException e5) {
								System.out.println("Error en: " + e5);
							}
						}
					}
				}
								
			} // end actualizar
		});
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnActualizar.setBackground(new Color(34, 139, 34));
		
		btnLimpiarCampos = new JButton("Limpiar Campos");
		btnLimpiarCampos.setBounds(436, 15, 179, 33);
		panel_2.add(btnLimpiarCampos);
		btnLimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++
				 *  mostrar datos despues del proceso hecho
				 * ++++++++++++++++++++++++++++++++++++++++
				 * */
				Limpiar();
			
				
			}
		});
		btnLimpiarCampos.setForeground(new Color(0, 0, 139));
		btnLimpiarCampos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLimpiarCampos.setBackground(SystemColor.activeCaptionBorder);
		
		lblF01 = new JLabel("");
		lblF01.setIcon(new ImageIcon(FrmGestionarProducto.class.getResource("/img/f03.jpg")));
		lblF01.setBounds(0, 0, 1029, 465);
		getContentPane().add(lblF01);
		
		//**************************************************************************
		
		/*
		 * +++++++++++++++++++++++++++++++++++++
		 * metodo
		 * Cargar combo categoria en tblProducto
		 * +++++++++++++++++++++++++++++++++++++
		 * */
		cargarComboCategoria();
		
		/*
		 * ++++++++++++++++++++++
		 * metodo
		 * Cargar tabla productos
		 * ++++++++++++++++++++++
		 * */
		CargarTablaProductos();
		
		
		//**************************************************************************
	}
	
	/*
	 * ++++++++++++++++++++++++++++++++++++
	 *  Limpiar datos de la tabla productos
	 * ++++++++++++++++++++++++++++++++++++
	 * */
	void limpiarDatosTablaProducto() {
   	 int fila = tblProducto.getRowCount();
			for (int i = fila-1; i>=0; i--) {
				modelo.removeRow(i);
			}
    }

	/*
     * ++++++++++++++++++++++++++++++++++++
     * Metodo que envia datos seleccionados
     * ++++++++++++++++++++++++++++++++++++
     */
     void enviarDatosProductoSeleccionado(int idProducto) {
    	String sql = "select * from tb_producto where idProducto = '" + idProducto + "' ";
        try {
            Connection con;
            con = MySQLConexion.getConexion();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            if (rs.next()) {
            	txtNombre.setText(rs.getString("nombre"));
                txtCantidad.setText(rs.getString("cantidad"));
                txtPrecio.setText(rs.getString("precio"));
                txtDescripcion.setText(rs.getString("descripcion"));
                int pigv = rs.getInt("igv");
                switch (pigv) {
                    case 0:
                        cboIgv.setSelectedItem("No tiene IGV");
                        break;
                    case 12:
                        cboIgv.setSelectedItem("12%");
                        break;
                    case 14:
                        cboIgv.setSelectedItem("14%");
                        break;
                    default:
                        cboIgv.setSelectedItem("Seleccione IGV");
                        break;
                }
                int idCate = rs.getInt("idCategoria");
                cboCategoria.setSelectedItem(relacionarCategoria(idCate));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar categoria: " + e);
        }
    }
     
    /*
	 * +++++++++++++++++++++++++++++++++++++
	 * Cargar combo categoria en tblProducto
	 * +++++++++++++++++++++++++++++++++++++
	 * */
     void cargarComboCategoria() {

    	// instanciar
 		GestionProducto gp = new GestionProducto();
 		ArrayList<Categoria> listac = gp.listado();
 		
 		// 0 = seleccione categoria
 		cboCategoria.addItem("Seleccione Categoria");
 		
 		// > 1 se muestra en el combo los datos de descripcion categoria
 		for (Categoria descripcion : listac) {
 			// solo traer al combito la (descripcion) de la categoria
 			cboCategoria.addItem(descripcion.getDescripcion());
 		}
     }
 

     /*
      * +++++++++++++++++++++++++++++++++++++++++++++++++++
      * metodo para mostrar todos los productos registrados
      * +++++++++++++++++++++++++++++++++++++++++++++++++++
      */
     String descripcionCategoria = "";
     double precio = 0.0;
     int pigv = 0;
     double IGV = 0;
     
     private void CargarTablaProductos() {
         String sql = "select p.idProducto, p.nombre, p.cantidad, p.precio, p.descripcion, p.igv, c.descripcion, p.estado from tb_producto As p, tb_categoria As c where p.idCategoria = c.idCategoria ";
         try {
        	 Connection con;
        	 con = MySQLConexion.getConexion();
        	 Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql);

             modelo.addColumn("IdProducto");
             modelo.addColumn("Nombre");
             modelo.addColumn("Cantidad");
             modelo.addColumn("Precio");
             modelo.addColumn("Descripcion");
             modelo.addColumn("IGV");
             modelo.addColumn("Categoria");
             //modelo.addColumn("Estado");

             while (rs.next()) {
                 precio = rs.getDouble("precio");
                 pigv = rs.getInt("igv");

                 Object fila[] = new Object[8];
                 for (int i = 0; i < 8; i++) {

                     if (i == 5) {
                         this.calcularIgv(precio, pigv);
                         fila[i] = IGV;
                         rs.getObject(i + 1);
                     } else {
                         fila[i] = rs.getObject(i + 1);
                     }
                 }
                 modelo.addRow(fila);
             }
             con.close();
         } catch (SQLException e) {
             System.out.println("Error al llenar la tabla productos: " + e);
         }
     }
     
     /*
      * ++++++++++++++++++++++++
      * Metodo para calcular Igv
      * ++++++++++++++++++++++++
      */
     private double calcularIgv(double precio, int igv) {
         int p_igv = igv;
         switch (p_igv) {
             case 0:
                 IGV = 0.0;
                 break;
             case 12:
                 IGV = precio * 0.12;
                 break;
             case 14:
                 IGV = precio * 0.14;
                 break;
             default:
                 break;
         }
         //redondear decimales
         IGV = (double) Math.round(IGV * 100) / 100;
         return IGV;
     }
     
     /*
      * +++++++++++++++++++++++++++++++++
      * Metodo para relacionar categorias
      * +++++++++++++++++++++++++++++++++
      */
     private String relacionarCategoria(int idCategoria) {

         String sql = "select descripcion from tb_categoria where idCategoria = '" + idCategoria + "'";
         try {
             Connection cn;
             cn = MySQLConexion.getConexion();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql);
             while (rs.next()) {
                 descripcionCategoria = rs.getString("descripcion");
             }
             cn.close();

         } catch (SQLException e) {
             System.out.println("Error al obtener el id de la categoria!");
         }
         return descripcionCategoria;
     }
     
     /**
     *+++++++++++++++++++++++++++++++++
     * Metodo para obtener id categoria
     *+++++++++++++++++++++++++++++++++
     */
	 private int IdCategoria() {
		String sql = "select * from tb_categoria where descripcion = '" + cboCategoria.getSelectedItem() +"' ";
			try {
				Connection cn;
				cn = MySQLConexion.getConexion();
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					obtenerIdCategoriaCombo = rs.getInt("idCategoria");
				}
			} catch (SQLException e) {
				System.out.println("Error al obener id categoria");
			}
			return obtenerIdCategoriaCombo;
	}
	 
	 /*
	  * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	  * metodo para limpiar los campos de la ventana gestionar producto
	  * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	   */
		void Limpiar() {
			txtNombre.setText("");
			txtCantidad.setText("");
			txtPrecio.setText("");
			txtDescripcion.setText("");
			cboIgv.setSelectedItem("Seleccione IGV");
			cboCategoria.setSelectedItem("Seleccione Categoria");
		}
     
} // fin

