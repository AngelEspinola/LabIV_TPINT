package Entidades;

public class Integrante {
	private int			Id;
	private String 		Legajo;     
	private String	    Dni;
	private String	 	Nombre;
	private String 		Apellido;
	private String 		Nacimiento;
	private String 		Calle;
	private String 		Numero;
	private Localidad 	Localidad;
	private Provincia 	Provincia;
	private String 		Email;
	private String 		Telefono;
	private boolean 	Estado;
	
	//private static int cont = 1;
	
	public Integrante(int id, String legajo, String dni, String nombre, String apellido, String nac, String calle, String numero, Localidad localidad,
			Provincia provincia, String email, String telefono) {
		Id			= id;
		Legajo		= legajo;
		Dni 		= dni;
		Nombre 		= nombre;
		Apellido 	= apellido;
		Nacimiento	= nac;
		Calle 		= calle;
		Numero 		= numero;
		Localidad 	= localidad;
		Provincia 	= provincia;
		Email       = email;
		Telefono 	= telefono;
		Estado 		= true;
	}
 
	public Integrante ()
	{
		//this.Legajo = cont++;
		Dni 		= "";
		Nombre 		= "";
		Apellido 	= "";
		Nacimiento	= "";
		Calle 		= "";
		Numero 		= "";
		Email       = "";
		Telefono 	= "";
		this.Estado = true;
	}
	public int getID() {
		return Id;
	}

	public void setID(int id) {
		this.Id = id;
	}
	public String getLegajo() {
		return Legajo;
	}

	public void setLegajo(String legajo) {
		Legajo = legajo;
	}
	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		this.Dni = dni;
	}

	public String getCalle() {
		return Calle;
	}

	public void setCalle(String calle) {
		Calle = calle;
	}
	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		this.Apellido = apellido;
	}

	public String getNacimiento() {
		return Nacimiento;
	}

	public void setNacimiento(String nacimiento) {
		Nacimiento = nacimiento;
	}

	public Localidad getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(Localidad localidad) {
		Localidad = localidad;
	}

	public Provincia getProvincia() {
		return Provincia;
	}

	public void setProvincia(Provincia provincia) {
		Provincia = provincia;
	}

	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	
	public boolean getEstado() {
		return Estado;
	}
	
	public void setEstado(boolean estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return getApellido() + ", " + getNombre() + ". ";
	}
}
