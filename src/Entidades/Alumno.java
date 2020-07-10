package Entidades;

public class Alumno extends Integrante{
	
	public Alumno(int id, String legajo, String dni, String nombre, String apellido, String nac, String calle, String numero, Localidad localidad,
			Provincia provincia, String email, String telefono) {
		super(id, legajo, dni, nombre, apellido, nac, calle, numero, localidad, provincia, email, telefono);
	}

	public Alumno ()
	{
	}
}
