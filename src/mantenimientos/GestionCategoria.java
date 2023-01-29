package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.CategoriaInterface;
import modelo.Categoria;
import utils.MySQLConexion;
import vista.FrmGestionarCategoria;

public class GestionCategoria implements CategoriaInterface {

	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++++
	 * Insertar datos de categoria a la base de datos
	 * ++++++++++++++++++++++++++++++++++++++++++++++
	 * */
	@Override
	public boolean guardar(Categoria objeto) {
		
		boolean respuesta = false;
		
		// ------------------------
		// plantilla

		Connection con = null;
		PreparedStatement pst = null;
		// se elimino esta parte, porque esto no es un listado

		try {
			con = MySQLConexion.getConexion();

			// OJO; los espacios son muy importantes
			String sql = "insert into tb_categoria values(?,?,?) ";

			pst = con.prepareStatement(sql);
			
			pst.setInt(1, 0);
			pst.setString(2, objeto.getDescripcion());
			pst.setInt(3, objeto.getEstado());
			
			if (pst.executeUpdate() > 0) {
				respuesta = true;
			}
			

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al guardar Categoria\n" + e.getMessage());
		} finally {
			// cerrar nuestra conexion o base de datos
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar : " + e.getMessage());
			}
		}
		// -----------------------------
		
		return respuesta;
	}
	
	
	/*
	 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * Codigo para que la categoria de productos no se repitan
	 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * */
	public boolean existeCategoria(String categoria) {
		boolean respuesta = false;

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();

			// OJO; los espacios son muy importantes
			String sql = "select descripcion from tb_categoria where descripcion = '" + categoria + "' ";

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			
			while (rs.next()) {
				respuesta = true;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al guardar Categoria\n" + e.getMessage());
		} finally {
			// cerrar nuestra conexion o base de datos
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar : " + e.getMessage());
			}
		}
		return respuesta;
	}


	/*
	 * ++++++++++++++++++++++++++++++
	 * Listado de la clase Categoria
	 * ++++++++++++++++++++++++++++++
	 * */
	@Override
	public ArrayList<Categoria> listado() {
		
		ArrayList<Categoria> lista = null;
		
		// plantilla
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		Connection con = null;
		PreparedStatement pst = null;
		// variable que nos servira para almacenar el resultado de la consulta (SOLO EN
		// CONSULTAS Y LISTADOS)
		ResultSet rs = null; // (RS)

		try {
			con = MySQLConexion.getConexion();

			String sql = "select idCategoria, descripcion, estado from tb_categoria ";

			pst = con.prepareStatement(sql);

			// ejecuta la sentencia y guarda el resultado
			rs = pst.executeQuery(); // (RS)

			// pasar el ResultSet al listado
			lista = new ArrayList<Categoria>();

			while (rs.next()) {
				Categoria c = new Categoria();
				c.setIdCategoria(rs.getInt(1));
				c.setDescripcion(rs.getString(2));
				c.setEstado(rs.getInt(3));
				lista.add(c);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR en LISTADO de GestionCategoria" + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar : " + e.getMessage());
			}
		}
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		return lista ;
	}
	
	
	/*
	 * +++++++++++++++++++++++++++++++
	 *  Mostrar los datos con un click
	 * +++++++++++++++++++++++++++++++ 
	 * */
	@Override
	public void enviarDatosCategoriaSeleccionada(int idCategoria) {
		

		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		Connection con = null;
		PreparedStatement pst = null;
		// CONSULTAS Y LISTADOS)
		ResultSet rs = null; // (RS)

		try {
			con = MySQLConexion.getConexion();

			String sql = "select * from tb_categoria where idCategoria = '"+idCategoria+"' ";

			pst = con.prepareStatement(sql);

			// ejecuta la sentencia y guarda el resultado
			rs = pst.executeQuery(); // (RS)
			
			if(rs.next()) {
				FrmGestionarCategoria fgc = new FrmGestionarCategoria();
				fgc.txtDescripcion.setText(rs.getString("descripcion"));
			}
			

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en Seleccionar Categoria" + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar : " + e.getMessage());
			}
		}
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	}
	
	
	/*
	 * ++++++++++++++++++++
	 * Actualizar_Categoria
	 * ++++++++++++++++++++
	 * */
	@Override
	public boolean actualizar(Categoria objeto, int idCategoria) {
		
		boolean respuesta = false;
		
		//
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = MySQLConexion.getConexion();

			String sql = "update tb_categoria set descripcion=? where idCategoria ='" + idCategoria + "' ";
			pst = con.prepareStatement(sql);
			// of INT a STRING
			pst.setString(1, objeto.getDescripcion());
			
			if (pst.executeUpdate() > 0) {
                respuesta = true;
            }

			// =04
		} catch (Exception e) {
			// mostrar el mensaje del posible error
			JOptionPane.showMessageDialog(null, "ERROR al ACTUALIZAR" + e.getMessage());
		} finally {
			// cerrar nuestra conexion o base de datos
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar : " + e.getMessage());
			}
		}
		//
		
		return respuesta ;
	}


	/*
	 * ++++++++++++++++++
	 * Eliminar_Categoria
	 * ++++++++++++++++++
	 * */
	@Override
	public boolean eliminar(int idCategoria) {
		
		boolean respuesta = false;
		
		//
		Connection cn = null;
		//PreparedStatement pst = null;

        try {
        	cn = MySQLConexion.getConexion();
        	
        	String sql = "delete from tb_categoria where idCategoria = '" + idCategoria + "' ";
        	
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.executeUpdate();
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar categoria: " + e);
        }
		//
		
		return respuesta;
	}

} // fin
