package interfaces;

import modelo.Cliente;

public interface ClienteInterface {

	/*
	 * ++++++++++++++++
	 * Guardar_Cliente
	 * ++++++++++++++++
	 * */
	public boolean guardarCliente(Cliente objeto);
	
	/*
	 * +++++++++++++++++
	 * Si_existe_Cliente
	 * +++++++++++++++++
	 * */
	public boolean existeCliente(String dni);
	
	/*
	 * ++++++++++++++++++
	 * Actualizar_Cliente
	 * ++++++++++++++++++
	 * */
	public boolean actualizarCliente(Cliente objeto, int idCliente);
	
	/*
	 * ++++++++++++++++
	 * Eliminar_Cliente
	 * ++++++++++++++++
	 * */
	public boolean eliminarCliente(int idCliente);
}
