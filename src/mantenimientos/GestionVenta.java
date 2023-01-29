package mantenimientos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import interfaces.VentaInterface;
import modelo.DetalleVenta;
import utils.MySQLConexion;

public class GestionVenta implements VentaInterface{

	/*Realizar_Venta*/
	@Override
	public int realizarVenta(ArrayList<DetalleVenta> det) {
		int rs = 0;
		
		// plantilla
		Connection con = null;
		PreparedStatement pst1 = null;
		
		try {
			con = MySQLConexion.getConexion();
			con.setAutoCommit(false);
			
			String sql1 = "insert into tb_detalle_venta values (?, ?, ?, ?, ?, ?, ?) ";
			
			pst1 = con.prepareStatement(sql1);
			
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		//
		
		return rs ;
	}

	
}
