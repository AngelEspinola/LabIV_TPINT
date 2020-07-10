package Entidades;

public class Localidad {
	private int Id;
	private int IdProvincia;
	private String Nombre;
	
	//Constructs
	public Localidad(int id, int idProvincia, String nombre) {
		Id 			= id;
		IdProvincia = idProvincia;
		Nombre 		= nombre;
	}
	public Localidad(int id) {
		this.Id = id;
	}
	public Localidad() {
	}
	
	//Getters and Setters
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	public int getIdProvincia() {
		return IdProvincia;
	}
	public void setIdProvincia(int id) {
		IdProvincia = id;
	}
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	public String toString() {
		return getNombre();
	}
	
}
