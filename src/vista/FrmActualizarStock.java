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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import mantenimientos.GestionProducto;
import modelo.Producto;
import utils.MySQLConexion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmActualizarStock extends JInternalFrame {
	private JLabel lblWallpaper05;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JComboBox cboProductos;
	private JTextField txtStockActual;
	private JTextField txtStockNuevo;
	private JButton btnActualizar;
	
	/*
	 * ++++++++++++++++++
	 * Variables_Globales
	 * ++++++++++++++++++
	 * */
	int idProducto = 0;
	int cantidad = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmActualizarStock frame = new FrmActualizarStock();
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
	public FrmActualizarStock() {
		setClosable(true);
		setIconifiable(true);
		setFrameIcon(new ImageIcon(FrmActualizarStock.class.getResource("/img/actualizarstock01.png")));
		setTitle("Actualizar stock de los productos");
		setBounds(100, 100, 603, 408);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Actualizar Stock De Productos");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 25));
		lblNewLabel.setBounds(111, 26, 404, 38);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Producto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(139, 98, 106, 21);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Stock Actual");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(139, 159, 127, 19);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Stock Nuevo");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(139, 227, 127, 19);
		getContentPane().add(lblNewLabel_3);
		
		cboProductos = new JComboBox();
		cboProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++
				 * Metodo_MostrarStockProducto
				 * ++++++++++++++++++++++++++++
				 * */
				mostrarStockProducto();
			}
		});
		cboProductos.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboProductos.setBounds(271, 94, 203, 31);
		getContentPane().add(cboProductos);
		
		txtStockActual = new JTextField();
		txtStockActual.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtStockActual.setEditable(false);
		txtStockActual.setBounds(271, 159, 130, 26);
		getContentPane().add(txtStockActual);
		txtStockActual.setColumns(10);
		
		txtStockNuevo = new JTextField();
		txtStockNuevo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * No_se_permiten_caracteres_de_Texto
				 * */
				if (!Character.isDigit(e.getKeyChar()))
					e.consume();
				if (txtStockNuevo.getText().length() >= 5)
					e.consume();
			}
		});
		txtStockNuevo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtStockNuevo.setColumns(10);
		txtStockNuevo.setBounds(271, 225, 130, 26);
		getContentPane().add(txtStockNuevo);
		
		btnActualizar = new JButton("Actualizar Stock");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++
				 * Actualizar_Stock
				 * ++++++++++++++++
				 * */
				// validacion_seleccion_del_producto
				if (!cboProductos.getSelectedItem().equals("Seleccione Un Producto")) {
					// ValidacionNoseAdmitenCamposVacios
					if (!txtStockNuevo.getText().isEmpty()) {
						// validacion_de_no_permiter_caracterres_de_texto
						boolean validacion = validar(txtStockNuevo.getText().trim());
						if (validacion == true) {
							// validar que la cantidad sea mayor cero (0)
							if (Integer.parseInt(txtStockNuevo.getText()) > 0) {

								Producto producto = new Producto();
								GestionProducto gestionProducto = new GestionProducto();
								// parseo
								int stockActual = Integer.parseInt(txtStockActual.getText().trim());
								int stockNuevo = Integer.parseInt(txtStockNuevo.getText().trim());

								// sumar
								stockNuevo = stockActual + stockNuevo;
								
								producto.setCantidad(stockNuevo);
								if (gestionProducto.actualizarStockProducto(producto, idProducto)) {
									JOptionPane.showMessageDialog(null, "Stock Actualizado Correctamente");
									cboProductos.setSelectedItem("Seleccione Un Producto:");
									txtStockActual.setText("");
									txtStockNuevo.setText("");
									//
									cargarComboProductos();
									//
								} else {
									JOptionPane.showMessageDialog(null, "Error al Actualizar Stock");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Ingrese cantidad mayor a 0");
							}
						} else {
							JOptionPane.showMessageDialog(null, "En la cantidad no se admiten caracteres de texto");
						}
					} else {
						JOptionPane.showMessageDialog(null,"Ingrese una nueva cantidad para sumar el stock del producto");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione Un Producto");
				}
			} // end btnActualizarStock
		});
		btnActualizar.setBackground(SystemColor.activeCaption);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnActualizar.setForeground(SystemColor.desktop);
		btnActualizar.setBounds(198, 293, 203, 38);
		getContentPane().add(btnActualizar);
		
		lblWallpaper05 = new JLabel("");
		lblWallpaper05.setIcon(new ImageIcon(FrmActualizarStock.class.getResource("/img/f03.jpg")));
		lblWallpaper05.setBounds(0, 0, 591, 379);
		getContentPane().add(lblWallpaper05);
		
		/*
		 * +++++++++++++++++++++++++++++
		 * Llamar_al_metodo cboProductos
		 * +++++++++++++++++++++++++++++
		 * */
		cargarComboProductos();

	} // end constructor
	
	/*
	 * +++++++++++++++++++++++++++
	 * Metodo_Cargar_CBO_PRODUCTOS
	 * +++++++++++++++++++++++++++
	 * */
	void cargarComboProductos() {
		//
		String sql = "select * from tb_producto";
		try {
			Connection cn;
			cn = MySQLConexion.getConexion();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			cboProductos.removeAllItems();
			cboProductos.addItem("Seleccione Un Producto");
			
			while (rs.next()) {
				cboProductos.addItem(rs.getString("nombre"));
			}

		} catch (SQLException e) {
			System.out.println("Error al cargar los productos en: " + e);
		}
		//
	}
	
	/*
	 * ++++++++++++++++++++++++
	 * Metodo_Stock_de_Producto
	 * ++++++++++++++++++++++++
	 * */
	void mostrarStockProducto() {
        try {
            Connection cn = MySQLConexion.getConexion();
            String sql = "select * from tb_producto where nombre = '" + cboProductos.getSelectedItem() + "' ";
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                idProducto = rs.getInt("idProducto");
                cantidad = rs.getInt("cantidad");
                txtStockActual.setText(String.valueOf(cantidad));
            } else {
            	// caso contrario se limpie el txt
            	txtStockActual.setText("");
            }         
        } catch (SQLException e) {
            System.out.println("Error al obtener stock del producto en: " + e);
        }
    }
	
	// Metodo_no_admitir_datos_de_texto
	private boolean validar(String valor) {
		int num;
		try {
			num = Integer.parseInt(valor);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
} // fin codigo
