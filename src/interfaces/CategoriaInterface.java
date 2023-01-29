package interfaces;

import java.util.ArrayList;

import modelo.Categoria;

public interface CategoriaInterface {

	/*
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 * Interface guardar_llamando a la_clase_Categoria
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 * */
	public boolean guardar(Categoria objeto);
	
	/*
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 * Interface listado_de_la_clase_categoria
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 * */
	public ArrayList<Categoria> listado();
	
	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++++++
	 * Mostrar_con_un click la_descripcion del_producto 
	 * ++++++++++++++++++++++++++++++++++++++++++++++++
	 * */
	public void enviarDatosCategoriaSeleccionada(int idCategoria);
	
	/*
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 * Actualizar_Categoria
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 * */
	public boolean actualizar(Categoria objeto, int idCategoria);
	
	/*
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 * Eliminar_Categoria
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 * */
	public boolean eliminar(int idCategoria);
}
