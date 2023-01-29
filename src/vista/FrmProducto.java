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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import mantenimientos.GestionProducto;
import modelo.Categoria;
import modelo.Producto;
import utils.MySQLConexion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmProducto extends JInternalFrame {

	
	/*
	 * +++++++++++++++++++++++++++++
	 * Declarar una variable global
	 * +++++++++++++++++++++++++++++
	 * */
	int obtenerIdCategoriaCombo = 0;
	
	private JLabel lblWallpaper03;
	private JLabel lblNewLabel;
	private JLabel lblNDNombres;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField txtNombre;
	private JTextField txtCantidad;
	private JTextField txtPrecio;
	private JTextField txtDescripcion;
	private JComboBox cboIgv;
	private JComboBox cboCategoria;
	private JButton btnGuardar;
	private JButton btnLimpiar;
	private JLabel lblNewLabel_7;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmProducto frame = new FrmProducto();
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
	public FrmProducto() {
		setFrameIcon(new ImageIcon(FrmProducto.class.getResource("/img/nuevo01.png")));
		setTitle("San Jorge - Nuevo Producto");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 735, 418);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Agregar Nuevo Producto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(212, 23, 356, 36);
		getContentPane().add(lblNewLabel);
		
		lblNDNombres = new JLabel("Nombres");
		lblNDNombres.setForeground(Color.WHITE);
		lblNDNombres.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNDNombres.setBounds(117, 78, 84, 28);
		getContentPane().add(lblNDNombres);
		
		lblNewLabel_2 = new JLabel("Cantidad");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(117, 124, 84, 28);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Precio");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(117, 171, 84, 32);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Descripcion");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(117, 213, 101, 28);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("IGV");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(117, 253, 84, 33);
		getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Categoria");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(117, 296, 84, 28);
		getContentPane().add(lblNewLabel_6);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * ++++++++++++++++++++++++++
				 * No_permite_datos_numericos
				 * ++++++++++++++++++++++++++				 * 
				 * */
				if (!Character.isAlphabetic(e.getKeyChar()))
					JOptionPane.showMessageDialog(null, "No se permite datos numericos!");
				if (!Character.isAlphabetic(e.getKeyChar()))
					e.consume();
				if (txtNombre.getText().length() >= 100) // no permite mas de 100 letras
					e.consume();
			}
		});
		txtNombre.setToolTipText("No se permiten datos numericos!");
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNombre.setBounds(250, 79, 164, 28);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++
				 * No_permite_caracteres_de_texto
				 * ++++++++++++++++++++++++++++++			 * 
				 * */
				if (!Character.isDigit(e.getKeyChar()))
					JOptionPane.showMessageDialog(null, "No se permite caracteres de texto!");
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
				if (txtCantidad.getText().length() >= 4) // no permite mas de 4 numeros de cantidad
					e.consume();
			}
		});
		txtCantidad.setToolTipText("No se permiten caracteres de texto!");
		txtCantidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(250, 125, 164, 29);
		getContentPane().add(txtCantidad);
		
		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++
				 * No_permite_caracteres_de_texto
				 * ++++++++++++++++++++++++++++++			 * 
				 * */
				/*
				if (!Character.isDigit(e.getKeyChar()))
					JOptionPane.showMessageDialog(null, "No se permite caracteres de texto!");
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
				if (txtPrecio.getText().length() >= 10) // no permite mas de 10 numeros de cantidad
					e.consume();
				*/
			}
		});
		txtPrecio.setToolTipText("No se permiten caracteres de texto!");
		txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(250, 171, 164, 28);
		getContentPane().add(txtPrecio);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(250, 214, 164, 28);
		getContentPane().add(txtDescripcion);
		
		cboIgv = new JComboBox();
		cboIgv.setModel(new DefaultComboBoxModel(new String[] {"Seleccione IGV", "No tiene IGV", "12%", "14%"}));
		cboIgv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboIgv.setBounds(250, 256, 164, 28);
		getContentPane().add(cboIgv);
		
		cboCategoria = new JComboBox();
		cboCategoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboCategoria.setBounds(250, 297, 164, 28);
		getContentPane().add(cboCategoria);
		
		btnGuardar = new JButton("Guardar Producto");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*
			 * BOTON GUARDAR PRODUCTOS
			 * */
			guardarProductos();
			
			}
		});
		btnGuardar.setBackground(SystemColor.activeCaption);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGuardar.setBounds(467, 117, 194, 56);
		getContentPane().add(btnGuardar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * +++++++++++++++++++++++++++++++++++++
				 * Limpiar los espacios de los txt y cbo
				 * de nuevos productos
				 * +++++++++++++++++++++++++++++++++++++
				 * */
				txtNombre.setText("");
				txtDescripcion.setText("");
				txtCantidad.setText("");
				txtPrecio.setText("");
				cboIgv.setSelectedIndex(0);
				cboCategoria.setSelectedIndex(0);
			}
		});
		btnLimpiar.setBackground(SystemColor.inactiveCaption);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLimpiar.setBounds(467, 208, 194, 56);
		getContentPane().add(btnLimpiar);
		
		lblWallpaper03 = new JLabel("");
		lblWallpaper03.setIcon(new ImageIcon(FrmProducto.class.getResource("/img/fondo03w.jpg")));
		lblWallpaper03.setBounds(0, 0, 728, 390);
		getContentPane().add(lblWallpaper03);
		
		lblNewLabel_7 = new JLabel("by_NDMM_7978_2022");
		lblNewLabel_7.setBounds(71, 46, 45, 13);
		getContentPane().add(lblNewLabel_7);

		/*
		 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		 * El combo automaticamente se rellena por medio de la base de datos tb_categoria
		 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		 * */
		llenaComboCategoria();
	}
	
	/*
	 * ++++++++++++++++++++++++++++++++++++++
	 * Llena combo Categoria de NuevoProducto
	 * ++++++++++++++++++++++++++++++++++++++
	 * */
	void llenaComboCategoria() {
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
	 * +++++++++++++++++
	 * Guardar productos
	 * +++++++++++++++++
	 * */
	void guardarProductos() {
		// instanciar
		GestionProducto gp = new GestionProducto();
		Producto producto = new Producto();
		
		String igv = "";
		String categoria = "";
		
		igv = cboIgv.getSelectedItem().toString().trim();
		categoria = cboCategoria.getSelectedItem().toString().trim();
		
		// validando
		if (txtNombre.getText().equals("") || txtCantidad.getText().equals("") || txtPrecio.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Complete los campos vacios!");
			txtNombre.requestFocus();
		} else {
			// consulta para ver si el producto ya existe
			if(!gp.existeProducto(txtNombre.getText().trim())) {
				if (igv.equalsIgnoreCase("Seleccione IGV")) {
					JOptionPane.showMessageDialog(null, "Seleccione IGV!");
				} else {
					if (categoria.equalsIgnoreCase("Seleccione Categoria")) {
						JOptionPane.showMessageDialog(null, "Seleccione Categoria!");
					} else {
						// guardar el producto
						try {
							
							producto.setNombre(txtNombre.getText().trim());
							producto.setCantidad(Integer.parseInt(txtCantidad.getText().trim()));
							String precioTXT = "";
							double precio = 0.0;
							precioTXT = txtPrecio.getText().trim();
							boolean aux = false;
							
							/*Si el usuario ingresa coma como punto decimal,
							 * lo transformamos a punto
							 * */
							for (int i = 0; i < precioTXT.length(); i++) {
								if (precioTXT.charAt(i) == ',') {
									String precioNuevo = precioTXT.replaceAll(",", ".");
									precio = Double.parseDouble(precioNuevo);
									aux = true;
								}
							}
							
							// evaluar la condicion
							if (aux == true) {
								producto.setPrecio(precio);
							} else {
								precio = Double.parseDouble(precioTXT);
								producto.setPrecio(precio);
							}
							
							// descripcion
							producto.setDescripcion(txtDescripcion.getText().trim());
							
							// Porcenta IGV
							if (igv.equalsIgnoreCase("No tiene IGV")) {
								producto.setPorcentajeIgv(0);
							} else if (igv.equalsIgnoreCase("12%")) {
								producto.setPorcentajeIgv(12);
							} else if (igv.equalsIgnoreCase("14%")) {
								producto.setPorcentajeIgv(14);
							}
							
							//idCategoria - cargarMetodo
							IdCategoria();
							producto.setIdCategoria(obtenerIdCategoriaCombo);
							producto.setEstado(1);
							
							if (gp.guardar(producto)) {
								JOptionPane.showMessageDialog(null, "Producto Guardado Correctamente");
							} else {
								JOptionPane.showMessageDialog(null, "Error al Guardar Producto");
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Los datos ingresados estan incorrectos!! (int), (String)!");
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "El producto ya está registrado en la base de datos!");
			}
		}
	}
	
	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++
	 * Cargar metodo que obtiene el id de categoria
	 * ++++++++++++++++++++++++++++++++++++++++++++
	 * */
	 private int IdCategoria() {
	        String sql = "select * from tb_categoria where descripcion = '" + this.cboCategoria.getSelectedItem() + "'";
	        Statement st;
	        try {
	            Connection con;
	            con = MySQLConexion.getConexion();
	            st = con.createStatement();
	            ResultSet rs = st.executeQuery(sql);
	            while (rs.next()) {
	                obtenerIdCategoriaCombo = rs.getInt("idCategoria");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al obener id categoria");
	        }
	        return obtenerIdCategoriaCombo;
	    }
} // fin
