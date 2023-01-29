package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import interfaces.ClienteInterface;
import modelo.Cliente;
import utils.MySQLConexion;

public class GestionCliente implements ClienteInterface{

	/*
	 * +++++++++++++++++++++
	 * Guardar_nuevo_cliente
	 * +++++++++++++++++++++
	 * */
	@Override
	public boolean guardarCliente(Cliente objeto) {
		
		boolean respuesta = false;
		
		//
		Connection con = null;
		PreparedStatement pst = null;
		// se elimino esta parte, porque esto no es un listado

		try {
			con = MySQLConexion.getConexion();

			// OJO; los espacios son muy importantes
			String sql = "insert into tb_cliente values(?,?,?,?,?,?,?) ";

			pst = con.prepareStatement(sql);

			pst.setInt(1, 0);
			pst.setString(2, objeto.getNombre());
			pst.setString(3, objeto.getApellido());
			pst.setString(4, objeto.getDni());
			pst.setString(5, objeto.getTelefono());
			pst.setString(6, objeto.getDireccion());
			pst.setInt(7, objeto.getEstado());

			if (pst.executeUpdate() > 0) {
				respuesta = true;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al guardar cliente \n" + e.getMessage());
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
	 * +++++++++++++++++
	 * Si_existe_cliente
	 * +++++++++++++++++
	 * */
	@Override
	public boolean existeCliente(String dni) {
		
		boolean respuesta = false;
		
		//
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = MySQLConexion.getConexion();

			// OJO; los espacios son muy importantes
			String sql = "select dni from tb_cliente where dni = '" + dni + "' ";

			pst = con.prepareStatement(sql);
			rs = pst.executeQuery(sql);
			
			while (rs.next()) {
				respuesta = true;
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al consultar cliente \n" + e.getMessage());
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
	 * Actualizar_Cliente
	 * ++++++++++++++++++
	 * */
	@Override
	public boolean actualizarCliente(Cliente objeto, int idCliente) {
		
		boolean respuesta = false;
		
		//
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = MySQLConexion.getConexion();

			String sql = "update tb_cliente set nombre = ?, apellido = ?, dni = ?, telefono= ?, direccion = ?, estado = ? where idCliente ='" + idCliente + "' ";
			pst = con.prepareStatement(sql);
			
			pst.setString(1, objeto.getNombre());
            pst.setString(2, objeto.getApellido());
            pst.setString(3, objeto.getDni());
            pst.setString(4, objeto.getTelefono());
            pst.setString(5, objeto.getDireccion());
            pst.setInt(6, objeto.getEstado());
			
            if (pst.executeUpdate() > 0) {
                respuesta = true;
            }
            
		} catch (Exception e) {
			// mostrar el mensaje del posible error
			JOptionPane.showMessageDialog(null, "ERROR al ACTUALIZAR CLIENTE" + e.getMessage());
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
	 * Eliminar_Cliente
	 * ++++++++++++++++
	 * */
	@Override
	public boolean eliminarCliente(int idCliente) {
		
		boolean respuesta = false;
		
		//
		Connection cn = null;
		PreparedStatement pst = null; //esta de mas; creo

        try {
        	cn = MySQLConexion.getConexion();
        	
        	String sql = "delete from tb_cliente where idCliente = '" + idCliente + "' ";
        	
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.executeUpdate();
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e);
        }
		//
		
		return respuesta ;
	}



}
