package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import interfaces.UsuarioInterface;
import modelo.Usuario;
import utils.MySQLConexion;

public class GestionUsuario implements UsuarioInterface {

	/*
	 * UsuarioLogueo
	 * */
	@Override
	public boolean UsuarioLogueo(Usuario objeto) {
		
		boolean respuesta = false;
		
		// ------------------------
		// plantilla
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null; 

		try {
			con = MySQLConexion.getConexion();
		
			// OJO; los espacios son muy importantes
			 String sql = "select u.usuario, u.password "
			 		+ "from tb_usuario u "
			 		+ "where u.usuario = '"+objeto.getUsuario()+"' and u.password = '"+objeto.getContrasena()+"' ";

			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery(); 

			while (rs.next()) {
				respuesta = true;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en IniciarSesion\n" + e.getMessage());
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
	 * +++++++++++++++
	 * Guardar_Usuario
	 * +++++++++++++++
	 * */
	@Override
	public boolean guardarUsuario(Usuario objeto) {
		
		boolean respuesta = false;
		
		//
		Connection con = null;
		PreparedStatement pst = null;
		// se elimino esta parte, porque esto no es un listado

		try {
			con = MySQLConexion.getConexion();

			// OJO; los espacios son muy importantes
			String sql = "insert into tb_usuario values(?,?,?,?,?,?,?) ";

			pst = con.prepareStatement(sql);

			pst.setInt(1, 0);
			pst.setString(2, objeto.getNombre());
			pst.setString(3, objeto.getApellido());
			pst.setString(4, objeto.getUsuario());
			pst.setString(5, objeto.getContrasena());
			pst.setString(6, objeto.getTelefono());
			pst.setInt(7, objeto.getEstado());

			if (pst.executeUpdate() > 0) {
				respuesta = true;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al guardar usuario \n" + e.getMessage());
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

	/*
	 * +++++++++++++++++++++
	 * Metodo_existe_usuario
	 * +++++++++++++++++++++
	 * */
	@Override
	public boolean existeUsuario(String usuario) {
		
		boolean respuesta = false;

		//
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();

			String sql = "select usuario from tb_usuario where usuario = '" + usuario + "' ";

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			
			while (rs.next()) {
				respuesta = true;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al consultar usuario \n" + e.getMessage());
		} finally {
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
	 * Actualizar_Usuario
	 * ++++++++++++++++++
	 * */
	@Override
	public boolean actualizarUsuario(Usuario objeto, int idUsuario) {
		
		boolean respuesta = false;
		
		//
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = MySQLConexion.getConexion();

			String sql = "update tb_usuario set nombre= ?, apellido = ?, usuario = ?, password= ?, telefono = ?, estado = ? where idUsuario ='" + idUsuario + "' ";
			pst = con.prepareStatement(sql);
			
			pst.setString(1, objeto.getNombre());
            pst.setString(2, objeto.getApellido());
            pst.setString(3, objeto.getUsuario());
            pst.setString(4, objeto.getContrasena());
            pst.setString(5, objeto.getTelefono());
            pst.setInt(6, objeto.getEstado());
			
            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }
            
		} catch (Exception e) {
			// mostrar el mensaje del posible error
			JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR USUARIO" + e.getMessage());
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

	/*
	 * ++++++++++++++++
	 * Eliminar_Usuario
	 * ++++++++++++++++
	 * */
	@Override
	public boolean eliminarUsuario(int idUsuario) {
		
		boolean respuesta = false;
		
		//
		Connection cn = null;
		//PreparedStatement pst = null; 

        try {
        	cn = MySQLConexion.getConexion();
        	
        	String sql = "delete from tb_usuario where idUsuario ='" + idUsuario + "' ";
        	
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.executeUpdate();
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e);
        }
		//
		
		return respuesta;
	}

}
