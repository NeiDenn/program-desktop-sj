package vista;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;

import modelo.Cliente;
import utils.MySQLConexion;

public class FrmGenerarReporteProductos extends JInternalFrame {
	private JButton btnSiGenerarReporteCliente;
	private JButton btnNoGenerarReporteCliente;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGenerarReporteProductos frame = new FrmGenerarReporteProductos();
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
	public FrmGenerarReporteProductos() {
		setIconifiable(true);
		setTitle("Reportes - Reporte Productos");
		setFrameIcon(new ImageIcon(FrmGenerarReporteProductos.class.getResource("/img/reporteproductos.png")));
		setBounds(100, 100, 450, 214);
		getContentPane().setLayout(null);
		
		btnSiGenerarReporteCliente = new JButton("SI");
		btnSiGenerarReporteCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++
				 * SI GENERAR REPORTES CLIENTES;
				 * ++++++++++++++++++++++++++++
				 * */
				generarReporteClientes();
			}
		});
		btnSiGenerarReporteCliente.setBackground(SystemColor.textHighlight);
		btnSiGenerarReporteCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSiGenerarReporteCliente.setBounds(78, 107, 129, 35);
		getContentPane().add(btnSiGenerarReporteCliente);
		
		btnNoGenerarReporteCliente = new JButton("NO");
		btnNoGenerarReporteCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++
				 * NO GENERAR REPORTES CLIENTES;
				 * entonces_cerramos_la_ventana
				 * ++++++++++++++++++++++++++++
				 * */
				dispose();
			}
		});
		btnNoGenerarReporteCliente.setBackground(SystemColor.activeCaption);
		btnNoGenerarReporteCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNoGenerarReporteCliente.setBounds(231, 107, 129, 35);
		getContentPane().add(btnNoGenerarReporteCliente);
		
		lblNewLabel_2 = new JLabel("Productos Registrados?");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Constantia", Font.BOLD, 21));
		lblNewLabel_2.setBounds(104, 62, 256, 35);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_1 = new JLabel("Desea Generar Reporte PDF De");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Constantia", Font.BOLD, 21));
		lblNewLabel_1.setBounds(72, 17, 323, 35);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmGenerarReporteProductos.class.getResource("/img/f01.jpg")));
		lblNewLabel.setBounds(0, 0, 438, 185);
		getContentPane().add(lblNewLabel);

	}
	
	// variable global de_cliente
	Cliente cliente = new Cliente();
	private JLabel lblNewLabel_2;
	
	/*
	 * +++++++++++++++++++
	 * Metodo_del_btn_(SI)
	 * +++++++++++++++++++
	 * */
	void generarReporteClientes() {
		
		Document documento = new Document();
		
		try {
			String nomArchivo = "reportes/Productos.pdf";
			PdfWriter.getInstance(documento, new FileOutputStream(nomArchivo));
			
			Paragraph titulo = new Paragraph();
			titulo.setAlignment(Paragraph.ALIGN_CENTER);
			titulo.setFont(FontFactory.getFont("Tahoma", 13, Font.BOLD, BaseColor.BLACK));
			titulo.add("Minimarket San Jorge \n");

			// informacion
			Paragraph informacion = new Paragraph();
			informacion.setAlignment(Paragraph.ALIGN_LEFT);
			informacion.add("\n");
			informacion.add("Empresa  : " + "San Jorge \n");
			informacion.add("Direccion : " + "Los Angeles Calle Rodriguez Mendoza 1034 \n");
			informacion.add("Telefono  : " + "914 934 344 \n");
			informacion.add("Correo     : " + "SanJorge@gmail.com \n\n\n");
			
			// logo
			Image logo = Image.getInstance("src/img/256logo.png");
			logo.scaleToFit(50, 50);
			logo.setAlignment(Chunk.ALIGN_CENTER);
			
			// factura
			Paragraph factura = new Paragraph();
			factura.setAlignment(Paragraph.ALIGN_RIGHT);
			factura.add("Factura : " + "0001 \n");
			factura.add("Fecha 	 : " + "2022-07-02 \n\n");
			
			// parrafo
			Paragraph parrafo = new Paragraph();
			parrafo.setAlignment(Paragraph.ALIGN_CENTER);
			parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
			parrafo.add("PRODUCTOS REGISTRADOS \n\n\n");
			
			documento.open();
			
			// agregar_los_formatos_de_arriba
			//_ojo_en_orden
			documento.add(titulo);
			documento.add(logo);
			documento.add(factura);
			documento.add(informacion);
			documento.add(parrafo);
			
			// creando_las_cabezeras_de_la_tabla
			PdfPTable tabla = new PdfPTable(7);
			tabla.setWidthPercentage(100);
			tabla.addCell("ID PRODUCTO");
			tabla.addCell("NOMBRE");
			tabla.addCell("CANTIDAD");
			tabla.addCell("PRECIO");
			tabla.addCell("DESCRIPCION");
			tabla.addCell("IGV");
			tabla.addCell("ID CATEGORIA");
			//tabla.addCell("ESTADO");
			try {
				Connection con;
	            con = MySQLConexion.getConexion();
	            PreparedStatement pst = con.prepareStatement("select * from tb_producto ");
	            
	            ResultSet rs = pst.executeQuery();
	            
	            if(rs.next()) {
	            	
	            	// lista_a_todos_los_clientes_que_encuentre
	            	do {
						tabla.addCell(rs.getString(1));
						tabla.addCell(rs.getString(2));
						tabla.addCell(rs.getString(3));
						tabla.addCell(rs.getString(4));
						tabla.addCell(rs.getString(5));
						tabla.addCell(rs.getString(6));
						tabla.addCell(rs.getString(7));
						//tabla.addCell(rs.getString(8));
					} while (rs.next());
	            	
	            	documento.add(tabla);
	            }
	            
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "ERROR EN LA CONEXION A LA BD!");
			}
			// cerrar_el_documento
			documento.close();
			
			// mensaje_de_documento_creado_exitosamente_el_reporte_pdfClientes
			JOptionPane.showMessageDialog(null, "Reporte Creado Exitosamente");
			
			//  visualizar_el_documento_despues_de_crearse
			Desktop.getDesktop().open(new File(nomArchivo));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR EN EL REPORTE DE PDF PRODUCTOS!");			
		}
	}
	
} // end_codigo
