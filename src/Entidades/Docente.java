package Entidades;

public class Docente extends Integrante{
	
	public Docente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Docente(int id, String legajo, String dni, String nombre, String apellido, String nac, String calle, String numero, Localidad localidad,
			Provincia provincia, String email, String telefono) {
		super(id, legajo, dni, nombre, apellido, nac, calle, numero, localidad, provincia, email, telefono);
	}
}
