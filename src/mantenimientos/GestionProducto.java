package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.ProductoInterface;
import modelo.Categoria;
import modelo.Producto;
import utils.MySQLConexion;

public class GestionProducto implements ProductoInterface{

	@Override
	public boolean guardar(Producto objeto) {
		
		boolean respuesta = false;
		
		
		// ------------------------
		// plantilla

		Connection con = null;
		PreparedStatement pst = null;
		// se elimino esta parte, porque esto no es un listado

		try {
			con = MySQLConexion.getConexion();

			// OJO; los espacios son muy importantes
			String sql = "insert into tb_producto values(?,?,?,?,?,?,?,?) ";

			pst = con.prepareStatement(sql);

			pst.setInt(1, 0);
			pst.setString(2, objeto.getNombre());
			pst.setInt(3, objeto.getCantidad());
			pst.setDouble(4, objeto.getPrecio());
			pst.setString(5, objeto.getDescripcion());
			pst.setInt(6, objeto.getPorcentajeIgv());
			pst.setInt(7, objeto.getIdCategoria());
			pst.setInt(8, objeto.getEstado());

			if (pst.executeUpdate() > 0) {
				respuesta = true;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al guardar producto \n" + e.getMessage());
		} finally {
			// cerrar nuestra conexion o base de datos
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar : " + e.getMessage());
			}
		}
		// -----------------------------
		
		
		return respuesta ;
	}

	/*
	 * ++++++++++++++++
	 * SiExisteProducto
	 * ++++++++++++++++
	 * */
	@Override
	public boolean existeProducto(String producto) {
		
		boolean respuesta = false;

		//
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();

			// OJO; los espacios son muy importantes
			String sql = "select nombre from tb_producto where nombre = '" + producto + "' ";

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			
			while (rs.next()) {
				respuesta = true;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al consultar producto \n" + e.getMessage());
		} finally {
			// cerrar nuestra conexion o base de datos
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar : " + e.getMessage());
			}
		}
		//
		
		return respuesta;
	}

	/*
	 * ++++++++++++++++++++++++++++
	 * Llenado de combo categoria
	 * ++++++++++++++++++++++++++++
	 * */
	@Override
	public ArrayList<Categoria> listado() {
		
		ArrayList<Categoria> listac = null;
		
		// plantilla
		Connection con = null;
		PreparedStatement pst = null;
		// variable que nos servira para almacenar el resultado de la consulta (SOLO EN
		// CONSULTAS Y LISTADOS)
		ResultSet rs = null; // (RS)

		try {
			con = MySQLConexion.getConexion();
			String sql = "select * from tb_categoria ";
			pst = con.prepareStatement(sql);

			// ejecuta la sentencia y guarda el resultado
			rs = pst.executeQuery(); // (RS)

			// pasar el ResultSet al listado
			listac = new ArrayList<Categoria>();

			while (rs.next()) { // lee la fila del rs, hasta que no haya datos
				Categoria c = new Categoria();
				c.setIdCategoria(rs.getInt(1));
				c.setDescripcion(rs.getString(2));
				c.setEstado(rs.getInt(3));
				listac.add(c);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR en ListadoCombo\n" + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar : " + e.getMessage());
			}
		}
		//
		
		return listac;
	}

	/**
     * ++++++++++++++++++++++++++++++++++
     * metodo para actualizar un producto
     * ++++++++++++++++++++++++++++++++++
     */
	@Override
	public boolean actualizarProducto(Producto objeto, int idProducto) {
		
		boolean respuesta = false;
		
		//
		//
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = MySQLConexion.getConexion();

			String sql = "update tb_producto set nombre=?, cantidad = ?, precio = ?, descripcion= ?, igv = ?, idCategoria = ?, estado = ? where idProducto ='" + idProducto + "' ";
			pst = con.prepareStatement(sql);
			
			pst.setString(1, objeto.getNombre());
            pst.setInt(2, objeto.getCantidad());
            pst.setDouble(3, objeto.getPrecio());
            pst.setString(4, objeto.getDescripcion());
            pst.setInt(5, objeto.getPorcentajeIgv());
            pst.setInt(6, objeto.getIdCategoria());
            pst.setInt(7, objeto.getEstado());
			
            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }
            
		} catch (Exception e) {
			// mostrar el mensaje del posible error
			JOptionPane.showMessageDialog(null, "ERROR al ACTUALIZAR PRODUCTO" + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar : " + e.getMessage());
			}
		}
		//
		//
		
		return respuesta ;
	}

	
	/**
     *+++++++++++++++++++++++++++++++++
     * metodo para eliminar un producto
     * ++++++++++++++++++++++++++++++++
     */
	@Override
	public boolean eliminarProducto(int idProducto) {
		
		boolean respuesta = false;
		
		//
		Connection cn = null;
		PreparedStatement pst = null;

        try {
        	cn = MySQLConexion.getConexion();
        	
        	String sql = "delete from tb_producto where idProducto ='" + idProducto + "' ";
        	
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.executeUpdate();
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e);
        }
		//
		
		return respuesta ;
	}

	@Override
	public boolean actualizarStockProducto(Producto producto, int idProducto) {
		
		boolean respuesta = false;
		
		// plantilla
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = MySQLConexion.getConexion();

			String sql = "update tb_producto set cantidad=? where idProducto ='" + idProducto + "' ";
			pst = con.prepareStatement(sql);

			pst.setInt(1, producto.getCantidad());


			if (pst.executeUpdate() > 0) {
				respuesta = true;
			}

		} catch (Exception e) {
			// mostrar el mensaje del posible error
			JOptionPane.showMessageDialog(null, "ERROR al ACTUALIZAR STOCK PRODUCTO" + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar : " + e.getMessage());
			}
		}
		//
		
		return respuesta;
	}

}
