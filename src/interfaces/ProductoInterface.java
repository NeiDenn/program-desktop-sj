package interfaces;

import java.util.ArrayList;

import modelo.Categoria;
import modelo.Producto;

public interface ProductoInterface {

	/*
	 * +++++++++++++++++++++++++++++++++++++++++++
	 * Guardar nuevo producto
	 * +++++++++++++++++++++++++++++++++++++++++++
	 * */
	public boolean guardar(Producto objeto);
	
	/*
	 * +++++++++++++++++++++++++++++++++++++++++++
	 * Metodo; el producto ya esta registrado
	 * +++++++++++++++++++++++++++++++++++++++++++
	 * */
	public boolean existeProducto(String producto);
	
	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++
	 * Metodo para el llenado de combo de Categoria
	 * en la ventana de producto
	 * ++++++++++++++++++++++++++++++++++++++++++++
	 * */
	public ArrayList<Categoria> listado();
	
	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++
	 * Metodo para actualizar producto
	 * ++++++++++++++++++++++++++++++++++++++++++++
	 * */
	public boolean actualizarProducto(Producto objeto, int idProducto);
	
	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++
	 * Metodo para eliminar producto
	 * ++++++++++++++++++++++++++++++++++++++++++++
	 * */
	public boolean eliminarProducto(int idProducto);
	
	/*
	 * +++++++++++++++++++++++++++++++++++++++++++++
	 * Metodo_para_actualizar_el_metodo_del_producto
	 * +++++++++++++++++++++++++++++++++++++++++++++
	 * */
	public boolean actualizarStockProducto(Producto producto, int idProducto);
}
