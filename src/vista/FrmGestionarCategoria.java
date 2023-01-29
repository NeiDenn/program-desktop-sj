package vista;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mantenimientos.GestionCategoria;
import modelo.Categoria;
import utils.MySQLConexion;

public class FrmGestionarCategoria extends JInternalFrame {
	private JLabel lblNewLabel;
	private JLabel lblF01;
	private JPanel panel;
	private JTable tblCategoria;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JLabel lblDescripcion;
	// OJO private
	public JTextField txtDescripcion;
	/*
	 * Declarar una veriable global idCategoria
	 * */
	private int idCategoria;
	
	/*
	 * +++++++++++++++
	 * Tabla = modelo
	 * +++++++++++++++
	 * */
	DefaultTableModel modelo = new DefaultTableModel();
	private JButton btnActualizarTabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGestionarCategoria frame = new FrmGestionarCategoria();
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
	public FrmGestionarCategoria() {
		setFrameIcon(new ImageIcon(FrmGestionarCategoria.class.getResource("/img/ca03.png")));
		setClosable(true);
		setIconifiable(true);
		setTitle("Categoria - Gestionar Categoria");
		setBounds(100, 100, 815, 494);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Administracion De Categorias");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(216, 31, 438, 33);
		getContentPane().add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(246, 96, 551, 312);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Evento Equivocado
			}
		});
		scrollPane.setBounds(10, 10, 531, 292);
		panel.add(scrollPane);
		
		// datos de la tablaCategoria
		tblCategoria = new JTable();
		tblCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++ 
				 * Evento_de Mouse en_la_tablatblCategoria
				 * muestra los datos con un click en el 
				 * txtDescripcion 
				 * ++++++++++++++++++++++++++++++++++++++++
				 */
				int fila_point = tblCategoria.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    idCategoria = (int) modelo.getValueAt(fila_point, columna_point);
                    enviarDatosCategoriaSeleccionada(idCategoria);
                }
			}
		});
		tblCategoria.addKeyListener(new KeyAdapter() {
			// Evento Equivocado
		});
		tblCategoria.setModel(modelo);
		modelo.addColumn("idCategoria");
		modelo.addColumn("Descripcion");
		//modelo.addColumn("Estado");
		scrollPane.setViewportView(tblCategoria);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.scrollbar);
		panel_1.setBounds(14, 96, 222, 312);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*+++++++++++
				 * ACTUALIZAR
				 * ++++++++++
				 * */
				if (!txtDescripcion.getText().isEmpty()) {
		            Categoria categoria = new Categoria();
		            GestionCategoria controlCategoria = new GestionCategoria();

		            categoria.setDescripcion(txtDescripcion.getText().trim());
		            if (controlCategoria.actualizar(categoria, idCategoria)) {
		                JOptionPane.showMessageDialog(null, "Categoria Actualizada");
		                txtDescripcion.setText("");
		                //mostrarDatos();
		                limpiarDatosTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al actualizar Categoria");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Seleccione una categoria");
		        }				
			} // end actualizar
		});
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnActualizar.setBackground(SystemColor.activeCaption);
		btnActualizar.setBounds(32, 28, 154, 40);
		panel_1.add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*+++++++++
				 * ELIMINAR
				 * ++++++++
				 * */
				if (!txtDescripcion.getText().isEmpty()) {
		            Categoria categoria = new Categoria();
		            GestionCategoria controlCategoria = new GestionCategoria();

		            categoria.setDescripcion(txtDescripcion.getText().trim());
		            if (!controlCategoria.eliminar(idCategoria)) {
		                JOptionPane.showMessageDialog(null, "Categoria Eliminada");
		                txtDescripcion.setText("");
		                //mostrarDatos();
		                limpiarDatosTabla();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al Eliminar Categoria");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Seleccione una categoria");
		        }
			} // end eliminar
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.setBackground(SystemColor.inactiveCaption);
		btnEliminar.setBounds(32, 91, 154, 40);
		panel_1.add(btnEliminar);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Consolas", Font.BOLD, 14));
		lblDescripcion.setBounds(66, 175, 115, 25);
		panel_1.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDescripcion.setBounds(22, 199, 179, 25);
		panel_1.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		btnActualizarTabla = new JButton("Actualizar Tabla");
		btnActualizarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++
				 *  
				 * ++++++++++++++++++++++++++++++
				 * */
				mostrarDatos();
				
			}
		});
		btnActualizarTabla.setForeground(SystemColor.textHighlight);
		btnActualizarTabla.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnActualizarTabla.setBackground(SystemColor.activeCaptionBorder);
		btnActualizarTabla.setBounds(32, 234, 154, 33);
		panel_1.add(btnActualizarTabla);
		
		lblF01 = new JLabel("");
		lblF01.setIcon(new ImageIcon(FrmGestionarCategoria.class.getResource("/img/f01.jpg")));
		lblF01.setBounds(0, 0, 803, 465);
		getContentPane().add(lblF01);
		
		/*
		 * +++++++++++++++++++++++++++++++++++++++++++++
		 * Mostrar los datos en la tabla automaticamente
		 * +++++++++++++++++++++++++++++++++++++++++++++
		 * */
		mostrarDatos();
	}
	
	/*
	 * +++++++++++++++++++++++++++++++++++
	 *  Mostrar los datos en la tabla
	 * +++++++++++++++++++++++++++++++++++
	 * */
	void mostrarDatos() {
		// instanciar
		GestionCategoria gc = new GestionCategoria();
		ArrayList<Categoria> lista = gc.listado();
		
		// enviar la lista a la tabla por un Foreach
		for (Categoria c : lista) {
			Object datos[] = {c.getIdCategoria(), c.getDescripcion(), c.getEstado()};
			
			// agregar a la tabla
			modelo.addRow(datos);
		}
	}
	
	/*
     * ++++++++++++++++++++++++++++++++++++
     * Metodo que envia datos seleccionados
     * ++++++++++++++++++++++++++++++++++++
     */
     void enviarDatosCategoriaSeleccionada(int idCategoria) {
    	String sql = "select * from tb_categoria where idCategoria = '" + idCategoria + "'";
        try {
            Connection con;
            con = MySQLConexion.getConexion();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            if (rs.next()) {
                txtDescripcion.setText(rs.getString("descripcion"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al seleccionar categoria: " + e);
        }
    }
     
     void limpiarDatosTabla() {
    	 int fila = tblCategoria.getRowCount();
			for (int i = fila-1; i>=0; i--) {
				modelo.removeRow(i);
			}
     }
} // fin

