package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilo.HiloReloj;

public class FrmMenu extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnUsuario;
	private JMenu mnProducto;
	private JMenu mnCliente;
	private JMenu mnCategoria;
	private JMenu mnFacturar;
	private JMenu mnReportes;
	private JMenuItem mntmNuevoUsuario;
	private JMenuItem mntmGestionarUsuarios;
	private JMenuItem mntmNuevoProducto;
	private JMenuItem mntmActualizarStock;
	private JMenuItem mntmGestionarProducto;
	private JMenuItem mntmGestionarClientes;
	private JMenuItem mntmNuevoCliente;
	private JMenuItem mntmGestionarCategoria;
	private JMenuItem mntmNuevaCategoria;
	private JMenuItem mntmNuevaVenta;
	private JMenu mnSalir;
	private JMenuItem mntmReporteCategorias;
	private JMenuItem mntmReporteClientes;
	private JMenuItem mntmReporteProductos;
	private JMenuItem mntmCerrar;
	private JDesktopPane escritorio;
	public static JLabel lblReloj;
	private JLabel lblCentro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMenu frame = new FrmMenu();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					//frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmMenu() {
		setBackground(new Color(255, 250, 240));
		setForeground(Color.BLACK);
		setFont(new Font("Consolas", Font.BOLD, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmMenu.class.getResource("/img/512logo.png")));
		/*
		 * +++++++++++++++++++++++++++++++++++++++
		 * El nombre se trae de FrmLogin > Usuario
		 * +++++++++++++++++++++++++++++++++++++++
		 * */
		setTitle("Bienvenido: " + FrmLogin.usuario.getUsuario().toString().trim()); // No funciona como deberia funcionar!!!!!
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 750);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnUsuario = new JMenu("Usuario");
		mnUsuario.setBackground(SystemColor.inactiveCaption);
		mnUsuario.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/usuario03min.png")));
		mnUsuario.setFont(new Font("Arial", Font.BOLD, 14));
		mnUsuario.setPreferredSize(new Dimension(164, 40));
		menuBar.add(mnUsuario);
		
		mntmNuevoUsuario = new JMenuItem("Nuevo Usuario");
		mntmNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++
				 * Abrir JInternalFrame FrmUsuario
				 * ++++++++++++++++++++++++++++++++++++
				 * */
				FrmUsuario frmu = new FrmUsuario();
				frmu.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(frmu);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				frmu.setLocation((escritorio.getWidth() - frmu.getWidth()) / 2, (escritorio.getHeight() - frmu.getHeight()) / 2 );
			}
		});
		mntmNuevoUsuario.setPreferredSize(new Dimension(150, 40));
		mntmNuevoUsuario.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/newuser01.png")));
		mnUsuario.add(mntmNuevoUsuario);
		
		mntmGestionarUsuarios = new JMenuItem("Gestionar Usuarios");
		mntmGestionarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * +++++++++++++++++++++++++++++++++++++
				 * Abrir_JInternalFrame GestionarUsuario
				 * +++++++++++++++++++++++++++++++++++++
				 * */
				FrmGestionarUsuario fgu = new FrmGestionarUsuario();
				fgu.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(fgu);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				fgu.setLocation((escritorio.getWidth() - fgu.getWidth()) / 2, (escritorio.getHeight() - fgu.getHeight()) / 2 );
			}
		});
		mntmGestionarUsuarios.setPreferredSize(new Dimension(150, 40));
		mntmGestionarUsuarios.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/gestionarusuario01.png")));
		mnUsuario.add(mntmGestionarUsuarios);
		
		mnCategoria = new JMenu("Categoria");
		mnCategoria.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/ca01.png")));
		mnCategoria.setBackground(SystemColor.inactiveCaption);
		mnCategoria.setFont(new Font("Arial", Font.BOLD, 14));
		mnCategoria.setPreferredSize(new Dimension(164, 40));
		menuBar.add(mnCategoria);
		
		mntmNuevaCategoria = new JMenuItem("Nueva Categoria");
		mntmNuevaCategoria.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/agre01.png")));
		mntmNuevaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * +++++++++++++++++++++++++++++++++++++++++++++++++
				 * Llamar_ventana JInternalFrame_NuevaCategoria
				 * +++++++++++++++++++++++++++++++++++++++++++++++++
				 * */
				FrmNuevaCategoria nc = new FrmNuevaCategoria();
				nc.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(nc);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				nc.setLocation((escritorio.getWidth() - nc.getWidth()) / 2, (escritorio.getHeight() - nc.getHeight()) / 2 );
				
			}
		});
		mntmNuevaCategoria.setPreferredSize(new Dimension(155, 40));
		mnCategoria.add(mntmNuevaCategoria);
		
		mntmGestionarCategoria = new JMenuItem("Gestionar Categorias");
		mntmGestionarCategoria.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/ca03.png")));
		mntmGestionarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++++++++++
				 * Llamar_ventana JInternalFrame_GestionarCategoria
				 * ++++++++++++++++++++++++++++++++++++++++++++++++
				 * */
				FrmGestionarCategoria gc = new FrmGestionarCategoria();
				gc.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del Menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(gc);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				gc.setLocation((escritorio.getWidth() - gc.getWidth()) / 2, (escritorio.getHeight() - gc.getHeight()) / 2 );
			}
		});
		mntmGestionarCategoria.setPreferredSize(new Dimension(155, 40));
		mnCategoria.add(mntmGestionarCategoria);
		
		mnProducto = new JMenu("Producto");
		mnProducto.setBackground(SystemColor.inactiveCaption);
		mnProducto.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/producto01.png")));
		mnProducto.setFont(new Font("Arial", Font.BOLD, 14));
		mnProducto.setPreferredSize(new Dimension(164, 40));
		menuBar.add(mnProducto);
		
		mntmNuevoProducto = new JMenuItem("Nuevo Producto");
		mntmNuevoProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++
				 * Abrir JInternalFrame FrmProducto
				 * ++++++++++++++++++++++++++++++++++++
				 * */
				FrmProducto fp = new FrmProducto();
				fp.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(fp);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				fp.setLocation((escritorio.getWidth() - fp.getWidth()) / 2, (escritorio.getHeight() - fp.getHeight()) / 2 );
			}
		});
		mntmNuevoProducto.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/nuevo01.png")));
		mntmNuevoProducto.setPreferredSize(new Dimension(150, 40));
		mnProducto.add(mntmNuevoProducto);
		
		mntmGestionarProducto = new JMenuItem("Gestionar Producto");
		mntmGestionarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++
				 * Abrir JInternalFrame FrmGestionarProducto
				 * ++++++++++++++++++++++++++++++++++++
				 * */
				FrmGestionarProducto fgp = new FrmGestionarProducto();
				fgp.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(fgp);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				fgp.setLocation((escritorio.getWidth() - fgp.getWidth()) / 2, (escritorio.getHeight() - fgp.getHeight()) / 2 );
			}
		});
		mntmGestionarProducto.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/producto.png")));
		mntmGestionarProducto.setPreferredSize(new Dimension(150, 40));
		mnProducto.add(mntmGestionarProducto);
		
		mntmActualizarStock = new JMenuItem("Actualizar Stock");
		mntmActualizarStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++++++++
				 * Llamar_ventana JInternalFrame_Actualizar_Stock
				 * ++++++++++++++++++++++++++++++++++++++++++++++
				 * */
				FrmActualizarStock fas = new FrmActualizarStock();
				fas.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(fas);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				fas.setLocation((escritorio.getWidth() - fas.getWidth()) / 2, (escritorio.getHeight() - fas.getHeight()) / 2 );
			}
		});
		mntmActualizarStock.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/actualizarstock01.png")));
		mntmActualizarStock.setPreferredSize(new Dimension(150, 40));
		mnProducto.add(mntmActualizarStock);
		
		mnCliente = new JMenu("Cliente");
		mnCliente.setBackground(SystemColor.inactiveCaption);
		mnCliente.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/cliente01.png")));
		mnCliente.setFont(new Font("Arial", Font.BOLD, 14));
		mnCliente.setPreferredSize(new Dimension(164, 40));
		menuBar.add(mnCliente);
		
		mntmNuevoCliente = new JMenuItem("Nuevo Cliente");
		mntmNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * +++++++++++++++++++++++++++++++++++++++++++++++++
				 * Llamar_ventana JInternalFrame_NuevoCliente
				 * +++++++++++++++++++++++++++++++++++++++++++++++++
				 * */
				FrmCliente frmc = new FrmCliente();
				frmc.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(frmc);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				frmc.setLocation((escritorio.getWidth() - frmc.getWidth()) / 2, (escritorio.getHeight() - frmc.getHeight()) / 2 );
			}
		});
		mntmNuevoCliente.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/nuevo02.png")));
		mntmNuevoCliente.setPreferredSize(new Dimension(150, 40));
		mnCliente.add(mntmNuevoCliente);
		
		mntmGestionarClientes = new JMenuItem("Gestionar Clientes");
		mntmGestionarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * +++++++++++++++++++++++++++++++++++++++++++++++++
				 * Llamar_ventana JInternalFrame_GestionarCliente
				 * +++++++++++++++++++++++++++++++++++++++++++++++++
				 * */
				FrmGestionarCliente fgc = new FrmGestionarCliente();
				fgc.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(fgc);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				fgc.setLocation((escritorio.getWidth() - fgc.getWidth()) / 2, (escritorio.getHeight() - fgc.getHeight()) / 2 );
				
			}
		});
		mntmGestionarClientes.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/gestionar01.png")));
		mntmGestionarClientes.setPreferredSize(new Dimension(150, 40));
		mnCliente.add(mntmGestionarClientes);
		
		mnFacturar = new JMenu("Facturar");
		mnFacturar.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/facturar01.png")));
		mnFacturar.setBackground(SystemColor.inactiveCaption);
		mnFacturar.setFont(new Font("Arial", Font.BOLD, 14));
		mnFacturar.setPreferredSize(new Dimension(164, 40));
		menuBar.add(mnFacturar);
		
		mntmNuevaVenta = new JMenuItem("Nueva Venta");
		mntmNuevaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++
				 * Llamar_ventana JInternalFrame_NuevaVenta_o_Facturacion
				 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++
				 * */
				FrmFacturacion frmf = new FrmFacturacion();
				frmf.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del Menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(frmf);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				frmf.setLocation((escritorio.getWidth() - frmf.getWidth()) / 2, (escritorio.getHeight() - frmf.getHeight()) / 2 );
			}
		});
		mntmNuevaVenta.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/nuevaventa.png")));
		mntmNuevaVenta.setPreferredSize(new Dimension(150, 40));
		mnFacturar.add(mntmNuevaVenta);
		
		mnReportes = new JMenu("Reportes");
		mnReportes.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/report01.png")));
		mnReportes.setBackground(SystemColor.inactiveCaption);
		mnReportes.setFont(new Font("Arial", Font.BOLD, 14));
		mnReportes.setPreferredSize(new Dimension(164, 40));
		menuBar.add(mnReportes);
		
		mntmReporteClientes = new JMenuItem("Reportes Clientes");
		mntmReporteClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * +++++++++++++++++++++++++++++++++++++++++++++
				 * Llamar_ventana JInternalFrame_ReporteClientes
				 * +++++++++++++++++++++++++++++++++++++++++++++
				 * */
				FrmGenerarReporteClientes grc = new FrmGenerarReporteClientes();
				grc.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del Menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(grc);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				grc.setLocation((escritorio.getWidth() - grc.getWidth()) / 2, (escritorio.getHeight() - grc.getHeight()) / 2 );
			}
		});
		mntmReporteClientes.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/reporteclientes.png")));
		mntmReporteClientes.setPreferredSize(new Dimension(150, 40));
		mnReportes.add(mntmReporteClientes);
		
		mntmReporteCategorias = new JMenuItem("Reportes Categorias");
		mntmReporteCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * +++++++++++++++++++++++++++++++++++++++++++++
				 * Llamar_ventana JInternalFrame_ReporteCategorias
				 * +++++++++++++++++++++++++++++++++++++++++++++
				 * */
				FrmGenerarReporteCategorias frmgrc = new FrmGenerarReporteCategorias();
				frmgrc.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del Menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(frmgrc);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				frmgrc.setLocation((escritorio.getWidth() - frmgrc.getWidth()) / 2, (escritorio.getHeight() - frmgrc.getHeight()) / 2 );
			}
		});
		mntmReporteCategorias.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/reportecategorias.png")));
		mntmReporteCategorias.setPreferredSize(new Dimension(150, 40));
		mnReportes.add(mntmReporteCategorias);
		
		mntmReporteProductos = new JMenuItem("Reportes Productos");
		mntmReporteProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * +++++++++++++++++++++++++++++++++++++++++++++
				 * Llamar_ventana JInternalFrame_ReporteProductos
				 * +++++++++++++++++++++++++++++++++++++++++++++
				 * */
				FrmGenerarReporteProductos frmrp = new FrmGenerarReporteProductos();
				frmrp.setVisible(true);
				
				/*
				 * ++++++++++++++++++++++++++++++++
				 * Ubicar en el escritorio del Menu
				 * ++++++++++++++++++++++++++++++++
				 * */
				escritorio.add(frmrp);
				
				/*
				 * ++++++++++++++++++++++++++++++++++++++
				 * Que se ubique al centro del escritorio
				 * ++++++++++++++++++++++++++++++++++++++
				 * */
				frmrp.setLocation((escritorio.getWidth() - frmrp.getWidth()) / 2, (escritorio.getHeight() - frmrp.getHeight()) / 2 );
			}
		});
		mntmReporteProductos.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/reporteproductos.png")));
		mntmReporteProductos.setPreferredSize(new Dimension(150, 40));
		mnReportes.add(mntmReporteProductos);
		
		mnSalir = new JMenu("Salir");
		mnSalir.setBackground(SystemColor.inactiveCaption);
		mnSalir.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/salir01min.png")));
		mnSalir.setFont(new Font("Arial", Font.BOLD, 14));
		mnSalir.setPreferredSize(new Dimension(164, 40));
		menuBar.add(mnSalir);
		
		mntmCerrar = new JMenuItem("Cerrar Sesion");
		mntmCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * ++++++++++++++++++
				 *  Salir del sistema
				 * ++++++++++++++++++
				 * */
				System.exit(0);
			}
		});
		mntmCerrar.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/salir02min.png")));
		mntmCerrar.setPreferredSize(new Dimension(150, 40));
		mnSalir.add(mntmCerrar);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		escritorio = new JDesktopPane();
		escritorio.setBackground(new Color(255, 250, 250));
		contentPane.add(escritorio, BorderLayout.CENTER);
		
		lblReloj = new JLabel("reloj");
		lblReloj.setForeground(new Color(51, 51, 51));
		lblReloj.setBackground(SystemColor.menu);
		lblReloj.setFont(new Font("Arial", Font.BOLD, 15));
		lblReloj.setBounds(1016, 639, 110, 22);
		escritorio.add(lblReloj);
		
		lblCentro = new JLabel("");
		lblCentro.setForeground(SystemColor.desktop);
		lblCentro.setIcon(new ImageIcon(FrmMenu.class.getResource("/img/reloj.jpg")));
		lblCentro.setBounds(1007, 639, 119, 22);
		escritorio.add(lblCentro);
		
		/*metodo_que_inicializan*/
		iniciaReloj();
		
	} // fin del constructor
	
	void iniciaReloj() {
		HiloReloj hr = new HiloReloj();
		hr.start();
	}

} // fin del codigo
