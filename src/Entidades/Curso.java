package Entidades;
import java.util.List;
public class Curso {
	private int ID;
	private Materia Materia;
	private int 	Cuatrimestre;
	private int 	A�o; 
	private Docente Docente;
	private List<Alumno> Alumnos;
	
	public Curso(Materia materia, int cuatrimestre, int a�o, Entidades.Docente docente, List<Entidades.Alumno> alumnos) {
		super();
		Materia = materia;
		Cuatrimestre = cuatrimestre;
		A�o 	= a�o;
		Docente = docente;
		Alumnos = alumnos;
	}
	
	public Curso ()
	{
		
	}
	
	public int getID()
	{
		return ID;
	}
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
	public Materia getMateria() {
		return Materia;
	}

	public void setMateria(Materia materia) {
		Materia = materia;
	}
	public int getCuatrimestre() {
		return Cuatrimestre;
	}
	public void setCuatrimestre(int cuatrimestre) {
		Cuatrimestre = cuatrimestre;
	}
	public int getA�o() {
		return A�o;
	}
	public void setA�o(int a�o) {
		A�o = a�o;
	}
	public Docente getDocente() {
		return Docente;
	}
	public void setDocente(Docente docente) {
		Docente = docente;
	}
	public List<Alumno> getAlumnos() {
		return Alumnos;
	}
	public void setAlumnos(List<Alumno> alumnos) {
		Alumnos = alumnos;
	}
}