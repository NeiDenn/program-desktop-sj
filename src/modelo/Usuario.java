package modelo;

public class Usuario {
	
	// 
	private int  idUsuario;
	private String nombre;
	private String  apellido;
	private String  usuario;
	private String contrasena;
	private String  telefono;
	private int  estado;
	
	// constructor
	public Usuario() {
		this.idUsuario = 0;
		this.nombre = "";
		this.apellido = "";
		this.usuario = "";
		this.contrasena = "";
		this.telefono = "";
		this.estado = 0;
	}
	
	

	public Usuario(int idUsuario, String nombre, String apellido, String usuario, String contrasena, String telefono, int estado) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.telefono = telefono;
		this.estado = estado;
	}

	


	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", usuario="
				+ usuario + ", contrasena=" + contrasena + ", telefono=" + telefono + ", estado=" + estado + "]";
	}



	// get y set
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	

}
