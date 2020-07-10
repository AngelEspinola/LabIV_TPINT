package Entidades;

public class Provincia
{
	private int Id;
	private String Nombre;
	
	public Provincia(int id, String nombre) {
		Id = id;
		Nombre = nombre;
	}
	public Provincia(int id) {
		this.Id = id;
	}
	public Provincia() {
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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