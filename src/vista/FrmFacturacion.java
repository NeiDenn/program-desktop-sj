package vista;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import modelo.DetalleVenta;
import utils.MySQLConexion;

public class FrmFacturacion extends JInternalFrame {
	private JLabel lblFPantalla;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JTable tblProductos;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnAnadirProductos;
	private JComboBox cboCliente;
	private JComboBox cboProducto;
	private JTextField txtBuscarCliente;
	private JTextField txtCantidad;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JButton btnCalcularCambio;
	private JTextField txtSubTotal;
	private JTextField txtDescuento;
	private JTextField txtIgv;
	private JTextField txtTotalPagar;
	private JTextField txtEfectivo;
	private JTextField txtCambio;
	private JButton btnRegistrarVentas;
	private JLabel lblNewLabel_11;
	private JButton btnBuscarCliente;
	
	/*Para_la_tabla*/
	DefaultTableModel modelo = new DefaultTableModel();
	
	// lista para el detalle venta de los productos
	ArrayList<DetalleVenta> listaProductos = new ArrayList<>();
	private DetalleVenta producto;
	
	private int idProducto = 0;
	private String nombre = "";
	private int cantidadProductoBBDD = 0;
	private double precioUnitario = 0.0;
	private int porcentajeIgv = 0;
	
	private int cantidad = 0; // cantidad de productos que se va a comprar
	private double subtotal = 0.0;
	private double descuento = 0.0;
	private double igv = 0.0;
	private double totalPagar = 0.0;
	
	/*-----------------------------*/
	// variable para calculos globales
	private double subtotalGeneral = 0.0;	
	private double descuentoGeneral = 0.0;	
	private double igvGeneral = 0.0;	
	private double totalPagarGeneral = 0.0;		
	/*-----------------------------*/

	private int auxIdDetalle = 1;
	private JPanel panel_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmFacturacion frame = new FrmFacturacion();
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
	public FrmFacturacion() {
		setFrameIcon(new ImageIcon(FrmFacturacion.class.getResource("/img/facturar01.png")));
		setTitle("Facturar- Nueva Factura");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 964, 545);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Facturacion De Venta");
		lblNewLabel.setForeground(SystemColor.controlLtHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(314, 10, 326, 53);
		getContentPane().add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 282, 932, 224);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 912, 204);
		panel.add(scrollPane);
		
		/*-------------------------*/
		/*-------------------------*/
		
		tblProductos = new JTable();
		tblProductos.addMouseListener(new MouseAdapter() {
			
			int idArrayList = 0; 
			
			@Override
			public void mouseClicked(MouseEvent e) {
				/*Evento de la tabla tblProductos*/
				int filaPoint = tblProductos.rowAtPoint(e.getPoint());
				int columnaPoint = 0;
				
				if (filaPoint > -1) {
					idArrayList = (int) modelo.getValueAt(filaPoint, columnaPoint);
				}
				
				int opcion = JOptionPane.showConfirmDialog(null, "Eliminar Producto?");
				// opciones para eliminar
				
				switch (opcion) {
				case 0:
					listaProductos.remove(idArrayList -1);
					calcularTotalPagar();
					listaTablaProductos();
					break;
				case 1:
					break;
				default:
					break;
				}
			}
		});
		/*-----------------------*/
		tblProductos.setModel(modelo);
		
		modelo.addColumn("N°");
		modelo.addColumn("Producto");
		modelo.addColumn("Cantidad");
		modelo.addColumn("P-Unitario");
		modelo.addColumn("Sub-Total");
		modelo.addColumn("Descuento");
		modelo.addColumn("IGV");
		modelo.addColumn("Total Pagar");
		modelo.addColumn("OPCION");
		/*-----------------------*/
		scrollPane.setViewportView(tblProductos);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBounds(10, 73, 401, 199);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Cliente");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 23, 65, 20);
		panel_1.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Producto");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 78, 78, 20);
		panel_1.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Cantidad");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(10, 145, 87, 20);
		panel_1.add(lblNewLabel_3);
		
		btnAnadirProductos = new JButton("A\u00F1adir Producto");
		btnAnadirProductos.setIcon(new ImageIcon(FrmFacturacion.class.getResource("/img/32anadir.png")));
		btnAnadirProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++
				 * BTN_Añadir_productos
				 * ++++++++++++++++
				 * */
				anadirProductos();
			}
		});
		btnAnadirProductos.setBackground(new Color(204, 255, 153));
		btnAnadirProductos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAnadirProductos.setBounds(198, 136, 193, 39);
		panel_1.add(btnAnadirProductos);
		
		cboCliente = new JComboBox();
		cboCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboCliente.setBounds(85, 23, 170, 23);
		panel_1.add(cboCliente);
		
		cboProducto = new JComboBox();
		cboProducto.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboProducto.setBounds(85, 78, 170, 23);
		panel_1.add(cboProducto);
		
		txtBuscarCliente = new JTextField();
		txtBuscarCliente.setForeground(Color.BLUE);
		txtBuscarCliente.setFont(new Font("Arial", Font.BOLD, 13));
		txtBuscarCliente.setBounds(255, 23, 135, 23);
		panel_1.add(txtBuscarCliente);
		txtBuscarCliente.setColumns(10);
		
		txtCantidad = new JTextField();
		txtCantidad.setFont(new Font("Arial", Font.PLAIN, 13));
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(86, 145, 102, 22);
		panel_1.add(txtCantidad);
		
		btnBuscarCliente = new JButton("Buscar Cliente");
		btnBuscarCliente.setIcon(new ImageIcon(FrmFacturacion.class.getResource("/img/16search.png")));
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++
				 * Btn_Buscar_Cliente
				 * ++++++++++++++++++++
				 * */
				buscarCliente();
			}
		});
		btnBuscarCliente.setBackground(SystemColor.activeCaption);
		btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarCliente.setBounds(255, 44, 134, 28);
		panel_1.add(btnBuscarCliente);
		
		panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.controlHighlight);
		panel_2.setBounds(541, 73, 401, 199);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		lblNewLabel_5 = new JLabel("SubTotal");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(10, 24, 65, 20);
		panel_2.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Descuento");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(10, 74, 80, 20);
		panel_2.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("IGV");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_7.setBounds(10, 119, 65, 20);
		panel_2.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("TotalPagar");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(200, 24, 80, 20);
		panel_2.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Efectivo");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_9.setBounds(200, 74, 65, 20);
		panel_2.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("Cambio");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_10.setBounds(200, 119, 65, 20);
		panel_2.add(lblNewLabel_10);
		
		btnCalcularCambio = new JButton("Calcular Cambio");
		btnCalcularCambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*btn calcular cambio*/
				if (!txtEfectivo.getText().isEmpty()) {
					// validamos que el usuario no ingrese caractees de texto
					boolean validacion = validarDouble(txtEfectivo.getText());
					if (validacion == true) {
						// validar que el efectivo sea mayor a 0
						double efec = Double.parseDouble(txtEfectivo.getText().trim());
						double top = Double.parseDouble(txtTotalPagar.getText().trim());
						
						if (efec < top) {							
							JOptionPane.showMessageDialog(null, "El cantidad de efectivo ingresado no es suficiente!");						
						} else {		
							double cambio = (efec - top);
							double cambi = (double) Math.round(cambio * 100d)/100;
							String camb = String.valueOf(cambi);
							txtCambio.setText(camb); 
						}
						
					} else {						
						JOptionPane.showMessageDialog(null, "No se permite caracteres de texto!");						
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ingrese dinero en efectivo para calcular cambio!");
				}
			}
		});
		btnCalcularCambio.setIcon(new ImageIcon(FrmFacturacion.class.getResource("/img/24cambio.png")));
		btnCalcularCambio.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCalcularCambio.setBackground(SystemColor.activeCaption);
		btnCalcularCambio.setBounds(84, 156, 235, 33);
		panel_2.add(btnCalcularCambio);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setForeground(SystemColor.activeCaptionText);
		txtSubTotal.setEditable(false);
		txtSubTotal.setFont(new Font("Arial", Font.BOLD, 12));
		txtSubTotal.setColumns(10);
		txtSubTotal.setBounds(85, 24, 102, 22);
		panel_2.add(txtSubTotal);
		
		txtDescuento = new JTextField();
		txtDescuento.setEditable(false);
		txtDescuento.setFont(new Font("Arial", Font.BOLD, 12));
		txtDescuento.setColumns(10);
		txtDescuento.setBounds(100, 74, 87, 22);
		panel_2.add(txtDescuento);
		
		txtIgv = new JTextField();
		txtIgv.setEditable(false);
		txtIgv.setFont(new Font("Arial", Font.BOLD, 12));
		txtIgv.setColumns(10);
		txtIgv.setBounds(85, 119, 102, 22);
		panel_2.add(txtIgv);
		
		txtTotalPagar = new JTextField();
		txtTotalPagar.setForeground(Color.RED);
		txtTotalPagar.setEditable(false);
		txtTotalPagar.setFont(new Font("Arial", Font.BOLD, 14));
		txtTotalPagar.setColumns(10);
		txtTotalPagar.setBounds(290, 24, 87, 22);
		panel_2.add(txtTotalPagar);
		
		txtEfectivo = new JTextField();
		txtEfectivo.setForeground(Color.BLUE);
		txtEfectivo.setFont(new Font("Arial", Font.BOLD, 14));
		txtEfectivo.setColumns(10);
		txtEfectivo.setBounds(275, 74, 102, 22);
		panel_2.add(txtEfectivo);
		
		txtCambio = new JTextField();
		txtCambio.setForeground(Color.RED);
		txtCambio.setEditable(false);
		txtCambio.setFont(new Font("Arial", Font.BOLD, 14));
		txtCambio.setColumns(10);
		txtCambio.setBounds(275, 119, 102, 22);
		panel_2.add(txtCambio);
		
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(95, 158, 160));
		panel_3.setBounds(421, 95, 110, 147);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		btnRegistrarVentas = new JButton("");
		btnRegistrarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*GENERAR PDF DE VENTA REGISTRADA*/
				
				String nomArchivo = "reportes/Venta.pdf";
				
				try {
					
					Document documento = new Document();
					PdfWriter.getInstance(documento, new FileOutputStream(nomArchivo));

					// titulo
					Paragraph titulo = new Paragraph();
					titulo.setAlignment(Paragraph.ALIGN_CENTER);
					titulo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.BLACK));
					titulo.add("Minimarket San Jorge \n");

					// logo
					Image logo = Image.getInstance("src/img/256logo.png");
					logo.scaleToFit(50, 50);
					logo.setAlignment(Chunk.ALIGN_CENTER);
					
					// informacion
					Paragraph informacion = new Paragraph();
					informacion.setAlignment(Paragraph.ALIGN_LEFT);
					informacion.add("\n");
					informacion.add("Empresa  : " + "San Jorge \n");
					informacion.add("Direccion : " + "Los Angeles Calle Rodriguez Mendoza 1034 \n");
					informacion.add("Telefono  : " + "914 934 344 \n");
					informacion.add("Correo     : " + "SanJorge@gmail.com \n\n");

					// fecha
					Paragraph factura = new Paragraph();
					factura.setAlignment(Paragraph.ALIGN_RIGHT);
					factura.add("Factura : " + "0001 \n");
					factura.add("Fecha 	 : " + "2022/07/02 \n");
					
					// cliente
					Paragraph cliente = new Paragraph();
					cliente.setFont(FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.BLUE));
					cliente.add("-------------------------------------");
					cliente.add("\n");
					cliente.add("Cliente: " + cboCliente.getSelectedItem().toString() + "\n");
					cliente.add("-------------------------------------");
					cliente.add("\n\n");
					
					// abrir documento
					documento.open();

					// instanciar
					documento.add(titulo);
					documento.add(logo);
					documento.add(factura);
					documento.add(informacion);
					documento.add(cliente);

					// crear columnas de las tablas
					PdfPTable tabla = new PdfPTable(8);
					tabla.setWidthPercentage(100);
					tabla.addCell("N°");
					tabla.addCell("producto");
					tabla.addCell("cantidad");
					tabla.addCell("Precio Unitario");
					tabla.addCell("sub total");
					tabla.addCell("descuento");
					tabla.addCell("igv");
					tabla.addCell("total a pagar");

					for (DetalleVenta ventas : listaProductos) {
						tabla.addCell(ventas.getIdDetalleVenta() + "");
						tabla.addCell(ventas.getNombre());
						tabla.addCell(ventas.getCantidad() + "");
						tabla.addCell(ventas.getPrecioUnitario() + "");
						tabla.addCell(ventas.getSubTotal() + "");
						tabla.addCell(ventas.getDescuento() + "");
						tabla.addCell(ventas.getIgv() + "");
						tabla.addCell(ventas.getTotalPagar() + "");
					}
					documento.add(tabla);
					
					Paragraph total = new Paragraph();
					total.setFont(FontFactory.getFont("Arial", 11, Font.BOLD, BaseColor.RED));
					total.setAlignment(Paragraph.ALIGN_RIGHT);
					total.add("\n\n");
					total.add("------------------------------------------------------ \n");
					total.add("TOTAL GENERAL A PAGAR:  "+ "S/ "+ totalPagarGeneral + "\n");
					total.add("------------------------------------------------------ \n\n\n\n");
					documento.add(total);
					
					Paragraph fin = new Paragraph();
					fin.setFont(FontFactory.getFont("Arial", 15, Font.BOLD, BaseColor.BLACK));
					fin.setAlignment(Paragraph.ALIGN_CENTER);
					fin.add("Gracias Por Su Compra.");
					documento.add(fin);

					// cerrar_el_documento
					documento.close();

					// mensaje_de_documento_creado_exitosamente
					JOptionPane.showMessageDialog(null, "Reporte Creado Exitosamente");

					// visualizar_el_documento_despues_de_crearse
					Desktop.getDesktop().open(new File(nomArchivo));
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE DE PDF CLIENTES! " + e2.getMessage());
				}
				
			} // fin del btn registrar ventas en un PDF
		});
		btnRegistrarVentas.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnRegistrarVentas.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRegistrarVentas.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRegistrarVentas.setBackground(SystemColor.inactiveCaption);
		btnRegistrarVentas.setIcon(new ImageIcon(FrmFacturacion.class.getResource("/img/impresora01.png")));
		btnRegistrarVentas.setBounds(10, 38, 90, 73);
		panel_3.add(btnRegistrarVentas);
		
		lblNewLabel_11 = new JLabel(" Registrar Venta");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_11.setBounds(10, 111, 100, 26);
		panel_3.add(lblNewLabel_11);
		
		lblFPantalla = new JLabel("");
		lblFPantalla.setIcon(new ImageIcon(FrmFacturacion.class.getResource("/img/carga04.jpg")));
		lblFPantalla.setBounds(0, 0, 952, 516);
		getContentPane().add(lblFPantalla);
		
		/*
		 * ++++++++++++++++++++++++++++++++++++++++++++
		 * Metodo_de_cargar_CBO_Automaticamente
		 * ++++++++++++++++++++++++++++++++++++++++++++
		 * */
		cargarCboClientes();
		cargarCboProductos();
		
		// deshabilitar
		txtEfectivo.setEnabled(false);
		btnCalcularCambio.setEnabled(false);
		// iniciarlizarlo con 0
		txtSubTotal.setText("S/ 0.0");
		txtIgv.setText("S/ 0.0");
		txtDescuento.setText("S/ 0.0");
		txtTotalPagar.setText("S/ 0.0");
		
		panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.inactiveCaption);
		panel_4.setBounds(200, 4, 191, 60);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

	} // fin constructor
	
	/*
	 * +++++++++++++++++++++++++++
	 * Metodo_para_cargar_clientes
	 * +++++++++++++++++++++++++++
	 * */
	void cargarCboClientes(){
		try {
			Connection con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_cliente ";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery(sql);
			
			cboCliente.removeAllItems();
			cboCliente.addItem("Seleccione Cliente");

			while (rs.next()) {
				cboCliente.addItem(rs.getString("nombre") + " " + rs.getString("apellido"));	
			}

			con.close();
		} catch (SQLException e) {
			System.out.println("Error al cargar clientes: " + e);
		}
	} // fin_del_metodo_cargarCboClientes
	
	/*
	 * +++++++++++++++++++++++++++
	 * Metodo_para_cargar_productos
	 * +++++++++++++++++++++++++++
	 * */
	void cargarCboProductos(){
		try {
			Connection con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_producto ";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery(sql);
			
			cboProducto.removeAllItems();
			cboProducto.addItem("Seleccione Producto");

			while (rs.next()) {
				cboProducto.addItem(rs.getString("nombre"));	
			}

			con.close();
		} catch (SQLException e) {
			System.out.println("Error al cargar producto: " + e);
		}
	} // fin_del_metodo_cargarCboProductos
	
	/*
	 * +++++++++++++++++++
	 * Btn_Buscar_Cliente
	 * ++++++++++++++++++++
	 * */
	void buscarCliente() {
		
		String clienteBuscar = txtBuscarCliente.getText().trim();
		
		try {
			Connection con = MySQLConexion.getConexion();
			
			String sql = "select nombre, apellido from tb_cliente where dni = '"+ clienteBuscar +"' ";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery(sql);

			if (rs.next()) {
				cboCliente.setSelectedItem(rs.getString("nombre") + " " + rs.getString("apellido"));
			} else {
				cboCliente.setSelectedItem("Seleccione Cliente");
				JOptionPane.showMessageDialog(null, "Dni incorrecto o no existe!");
			}
			
			txtBuscarCliente.setText("");

			con.close();
		} catch (SQLException e) {
			System.out.println("Error en buscar al cliente: " + e);
		}
	}
	

	
	/*
	 * +++++++++++++++++++++++
	 * Metodo_añadir_productos
	 * +++++++++++++++++++++++
	 * */
	void anadirProductos(){	
		String combo = cboProducto.getSelectedItem().toString();
		
		// validar que seleccione un productos
		if (combo.equalsIgnoreCase("Seleccione Producto")) {
			JOptionPane.showMessageDialog(null, "Seleccione Un Producto");
		} else {
			// validar que ingrese una cantidad
			if (!txtCantidad.getText().isEmpty()) {
				// validar que no ingrese caracteres de texto
				boolean validacion = validar(txtCantidad.getText());
				if (validacion == true) {
					// validar que la cantidad sea mayor a 0
					if (Integer.parseInt(txtCantidad.getText()) > 0) {
						cantidad = Integer.parseInt(txtCantidad.getText());
						// ejecutar el metodo para obtener datos del producto
						datosDelProducto();
						// validar que la cantidad de productos seleccionados no sea mayor al stock de la base de datos
						if (cantidad <= cantidadProductoBBDD) {
							
							subtotal = precioUnitario * cantidad;
							
							// total a pagar
							totalPagar = subtotal + igv + descuento;
							
							// redondear decimales
							subtotal = (double) Math.round(subtotal * 100) / 100;
							igv = (double) Math.round(igv * 100) / 100;
							descuento = (double) Math.round(descuento * 100) / 100;
							totalPagar = (double) Math.round(totalPagar * 100) / 100;
							
							// crear un nuevo producto
							producto = new DetalleVenta(auxIdDetalle, // detalle venta
									1,
									idProducto,
									nombre,
									Integer.parseInt(txtCantidad.getText()),
									precioUnitario,
									subtotal,
									descuento,
									igv,
									totalPagar,
									1// estado
							);
							
							// anadir a la lista
							listaProductos.add(producto);
							JOptionPane.showMessageDialog(null, "Producto Agregado");
							// que se autouncremente uno en uno
							auxIdDetalle++;
							txtCantidad.setText("");
							// volver a cargar cbo productos
							cargarCboProductos();
							calcularTotalPagar();
							// habilitar los txt y el btn
							txtEfectivo.setEnabled(true);
							btnCalcularCambio.setEnabled(true);
							
						} else {
							JOptionPane.showMessageDialog(null, "La cantidad es mayor al stock!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "No se permite 0 o números negativos!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "No se permite caracteres de texto!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Ingrese Una Cantidad");
			}
		}
		// llamar al metodo
		listaTablaProductos();
		
	} //metodo_anadir_productos
	
	// metodo validar
	private boolean validar(String valor) {
		try {
			int num = Integer.parseInt(valor);
			return true;
		} catch (Exception e) {
			return false;
		}
	} // fin del metodo validar
	
	// metodo validar 2
		private boolean validarDouble(String valor) {
			try {
				double num = Double.parseDouble(valor);
				return true;
			} catch (Exception e) {
				return false;
			}
		} // fin del metodo validar 2
	
	// metodo para mostrar los datos del producto seleccionado
	void datosDelProducto() {
		try {
			Connection con = MySQLConexion.getConexion();
			
			String sql = "select * from tb_producto where nombre = '"+ cboProducto.getSelectedItem() +"' ";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery(sql);

			while (rs.next()) {
				idProducto = rs.getInt("idProducto");
				nombre = rs.getString("nombre");
				cantidadProductoBBDD = rs.getInt("cantidad");
				precioUnitario = rs.getDouble("precio");
				porcentajeIgv = rs.getInt("igv");
				calcularIGV(precioUnitario, porcentajeIgv); // calcular y retorna el igv
			}
			
		} catch (Exception e) {
			System.out.println("Error al obtener datos del producto!");
		}
	} // fin del metodo mostrar los datos del producto seleccionado
	
	// metodo para calcular IGV
	private double calcularIGV(double precio, int porcentajeIgv) {
		int pIgv = porcentajeIgv;
		
		switch (pIgv) {
		case 0:
			igv = 0.0;
			break;
		case 12:
			igv = (precio * cantidad) * 0.12;
			break;
		case 14:
			igv = (precio * cantidad) * 0.14;
			break;
		default:
			break;
		}
		return igv;
	} // fin del metodo calcular igv
	
	// metodo para presentear informacin en la tabla
	private void listaTablaProductos() {
		modelo.setRowCount(listaProductos.size());
		for (int i = 0; i < listaProductos.size(); i++) {
			modelo.setValueAt(i + 1, i, 0);
			modelo.setValueAt(listaProductos.get(i).getNombre(), i, 1);
			modelo.setValueAt(listaProductos.get(i).getCantidad(), i, 2);
			modelo.setValueAt(listaProductos.get(i).getPrecioUnitario(), i, 3);
			modelo.setValueAt(listaProductos.get(i).getSubTotal(), i, 4);
			modelo.setValueAt(listaProductos.get(i).getDescuento(), i, 5);
			modelo.setValueAt(listaProductos.get(i).getIgv(), i, 6);
			modelo.setValueAt(listaProductos.get(i).getTotalPagar(), i, 7);
			modelo.setValueAt("Eliminar", i, 8); // ----------------------
		}
		// añadir al jatble
		tblProductos.setModel(modelo);
		
	} // fin del metodo mostrar inforamcion en la tabla
	
	// metodo de calculos finales
	void calcularTotalPagar() {
		subtotalGeneral = 0;
		descuentoGeneral = 0;
		igvGeneral = 0;
		totalPagarGeneral = 0;
		
		/**************************************************/
		/**************************************************/
		for(DetalleVenta elemento : listaProductos) {
			subtotalGeneral += elemento.getSubTotal();
			descuentoGeneral += elemento.getDescuento();
			igvGeneral += elemento.getIgv();
			totalPagarGeneral += elemento.getTotalPagar();  
		}
		/**************************************************/
		/**************************************************/
		// redondear decimales
		subtotalGeneral = (double) Math.round(subtotalGeneral * 100)/100;
		igvGeneral = (double) Math.round(igvGeneral * 100)/100;
		descuentoGeneral = (double) Math.round(descuentoGeneral * 100)/100;
		totalPagarGeneral = (double) Math.round(totalPagarGeneral * 100)/100;
		
		// enviar a la campos de la vista
		txtSubTotal.setText(String.valueOf("S/ " + subtotalGeneral));
		txtIgv.setText(String.valueOf("S/ " + igvGeneral));
		txtDescuento.setText(String.valueOf("S/ " + descuentoGeneral));
		txtTotalPagar.setText(String.valueOf(totalPagarGeneral));
		
	} // fin del metodo total a paga los productos agregados
} // fin_codigo
