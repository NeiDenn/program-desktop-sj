package interfaces;

import modelo.Usuario;

public interface UsuarioInterface {
	
	/*
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * Interface para iniciar sesion en el logueo, llamando
	 * a la clase de Usuario
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * */
	public boolean UsuarioLogueo (Usuario objeto);
	
	/*
	 * ++++++++++++++++++++++
	 * Metodo_guardar_usuario
	 * ++++++++++++++++++++++
	 * */
	public boolean guardarUsuario(Usuario objeto);
	
	/*
	 * ++++++++++++++++++++++
	 * Metodo_existe_usuario
	 * ++++++++++++++++++++++
	 * */
	public boolean existeUsuario(String usuario);
	
	/*
	 * +++++++++++++++++++++++++
	 * Metodo_actualizar_usuario
	 * +++++++++++++++++++++++++
	 * */
	public boolean actualizarUsuario(Usuario objeto, int idUsuario);
	
	/*
	 * +++++++++++++++++++++++
	 * Metodo_eliminar_usuario
	 * +++++++++++++++++++++++
	 * */
	public boolean eliminarUsuario(int idUsuario);
	
}
