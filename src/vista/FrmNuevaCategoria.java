package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import mantenimientos.GestionCategoria;
import modelo.Categoria;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmNuevaCategoria extends JInternalFrame {
	private JLabel lblF03;
	private JLabel lblTitulo;
	private JLabel lblNewLabel;
	private JTextField txtDescripcion;
	private JButton btnGuardar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmNuevaCategoria frame = new FrmNuevaCategoria();
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
	public FrmNuevaCategoria() {
		setFrameIcon(new ImageIcon(FrmNuevaCategoria.class.getResource("/img/agre01.png")));
		setTitle("Categoria - Nueva Categoria");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 666, 341);
		getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Agregar Nueva Categoria");
		lblTitulo.setFont(new Font("Consolas", Font.BOLD, 27));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(173, 34, 355, 33);
		getContentPane().add(lblTitulo);
		
		lblNewLabel = new JLabel("Agrega una categoria:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(114, 113, 209, 19);
		getContentPane().add(lblNewLabel);
		
		txtDescripcion = new JTextField();
		txtDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*
				 * ++++++++++++++++++++++++++
				 * No_permite_datos_numericos
				 * (PARA TXT DESCRIPCION)
				 * ++++++++++++++++++++++++++
				 * */
				if (!Character.isAlphabetic(e.getKeyChar()))
					JOptionPane.showMessageDialog(null, "No se permite datos numericos!");
				if (!Character.isAlphabetic(e.getKeyChar()))
					e.consume();
				if (txtDescripcion.getText().length() >= 150) // no permite mas de 150 letras
					e.consume();
			}
		});
		txtDescripcion.setToolTipText("Ejm. \"Lacteos\"");
		txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDescripcion.setBounds(333, 111, 209, 27);
		getContentPane().add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		btnGuardar = new JButton("Guardar Categoria");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * +++++++++++++++
				 * BOTON GUARDAR
				 * +++++++++++++++
				 * */
				guardarCategoria();
			}
		});
		btnGuardar.setFont(new Font("Arial", Font.BOLD, 18));
		btnGuardar.setBackground(SystemColor.inactiveCaption);
		btnGuardar.setBounds(213, 202, 246, 43);
		getContentPane().add(btnGuardar);
		
		lblF03 = new JLabel("");
		lblF03.setIcon(new ImageIcon(FrmNuevaCategoria.class.getResource("/img/f02.jpg")));
		lblF03.setBounds(0, 0, 654, 313);
		getContentPane().add(lblF03);

	}
	
	/*
	 * +++++++++++++++++++++
	 * DEL BOTON GUARDAR
	 * +++++++++++++++++++++
	 * */
	void guardarCategoria() {
		// instanciar
		Categoria categoria = new Categoria();
		GestionCategoria gc = new GestionCategoria();
		
		// validando
		if (txtDescripcion.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Completa los datos vacios");
		} else {
			// para que no se repita nuestro producto de categoria (! = negacion_)
			if (!gc.existeCategoria(txtDescripcion.getText().trim())) {
				categoria.setDescripcion(txtDescripcion.getText().trim());
				categoria.setEstado(1);
				if (gc.guardar(categoria)) {
					JOptionPane.showMessageDialog(null, "Categoria Guardado Exitosamente");
				} else {
					JOptionPane.showMessageDialog(null, "Error al Guardar!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "La categoria ya esta registrado, intente con otra categoria!");
			}
		}
		
		// Luego de la operacion limpiar el txtDescripcion
		txtDescripcion.setText("");
	}
	
	
	
} // fin
