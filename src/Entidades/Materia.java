package Entidades;

public class Materia {
	private int Id;
	private String Nombre;
	public Materia(int id, String nombre) {
		super();
		Id = id;
		Nombre = nombre;
	}
	public Materia() {
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