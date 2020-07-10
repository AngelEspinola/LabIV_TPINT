package Entidades;

public class AlumnoNotas {
	private int	IdAlumno;
	private int	IdCurso;
	private String Legajo;
	private String Nombre;
	private String Apellido;
	private int Nota1;
	private int	Nota2;
	private int	Recuperatorio1;
	private int	Recuperatorio2;
	private String Estado;
	public AlumnoNotas(int idAlumno, int idCurso, String legajo, String nombre, String apellido, int nota1, int nota2, int recuperatorio1, int recuperatorio2,
			String estado) {
		super();
		IdAlumno = idAlumno;
		IdCurso  = idCurso;
		Legajo   = legajo;
		Nombre   = nombre;
		Apellido = apellido;
		Nota1 = nota1;
		Nota2 = nota2;
		Recuperatorio1 = recuperatorio1;
		Recuperatorio2 = recuperatorio2;
		Estado = estado;
	}
	public AlumnoNotas()
	{
	}
	public int getIdAlumno() {
		return IdAlumno;
	}
	public void setIdAlumno(int idAlumno) {
		IdAlumno = idAlumno;
	}
	public int getIdCurso() {
		return IdCurso;
	}
	public void setIdCurso(int idCurso) {
		IdCurso = idCurso;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public int getNota1() {
		return Nota1;
	}
	public void setNota1(int nota1) {
		Nota1 = nota1;
	}
	public int getNota2() {
		return Nota2;
	}
	public void setNota2(int nota2) {
		Nota2 = nota2;
	}
	public int getRecuperatorio1() {
		return Recuperatorio1;
	}
	public void setRecuperatorio1(int recuperatorio1) {
		Recuperatorio1 = recuperatorio1;
	}
	public int getRecuperatorio2() {
		return Recuperatorio2;
	}
	public void setRecuperatorio2(int recuperatorio2) {
		Recuperatorio2 = recuperatorio2;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getLegajo() {
		return Legajo;
	}
	public void setLegajo(String legajo) {
		Legajo = legajo;
	}
	public int getEstadoInt() {
		return getEstado() == "Libre"?1:2;
	}
}
