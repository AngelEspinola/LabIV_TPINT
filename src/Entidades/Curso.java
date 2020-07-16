package Entidades;
import java.util.List;
public class Curso {
	private int ID;
	private Materia Materia;
	private int 	Cuatrimestre;
	private int 	Anio; 
	private Docente Docente;
	private List<Alumno> Alumnos;
	private int   Aprobados;
	private int   Desaprobados;
	private float PorcentajeAprobados;
	private float PorcentajeDesaprobados;
	private float PromedioParciales;
	private float PromedioTotal;
	
	public Curso(Materia materia, int cuatrimestre, int anio, Entidades.Docente docente, List<Entidades.Alumno> alumnos) {
		super();
		Materia = materia;
		Cuatrimestre = cuatrimestre;
		Anio 	= anio;
		Docente = docente;
		Alumnos = alumnos;
		Aprobados = 0;
		Desaprobados = 0;
		PorcentajeAprobados = 0;
		PorcentajeDesaprobados = 0;
		PromedioParciales = 0;
		PromedioTotal = 0;
	}
	

	public Curso(Materia materia, int cuatrimestre, int anio, Entidades.Docente docente, int aprobados, int desaprobados, float porcentajeAprobados, float porcentajeDesaprobados,
			float promedioParciales, float promedioTotal) {
		super();
		Materia				= materia;
		Cuatrimestre		= cuatrimestre;
		Anio				= anio;
		Docente				= docente;
		Aprobados			= aprobados;
		Desaprobados		= desaprobados;
		PorcentajeAprobados	= porcentajeAprobados;
		PorcentajeDesaprobados= porcentajeDesaprobados;
		PromedioParciales	= promedioParciales;
		PromedioTotal		= promedioTotal;
		
	}
	public int getAprobados() {
		return Aprobados;
	}
	
	public void setAprobados(int aprobados) {
		Aprobados = aprobados;
	}
	
	public int getDesaprobados() {
		return Desaprobados;
	}
	
	public void setDesaprobados(int desaprobados) {
		Desaprobados = desaprobados;
	}
	
	public float getPorcentajeAprobados() {
		return PorcentajeAprobados;
	}
	
	public void setPorcentajeAprobados(float porcentajeAprobados) {
		PorcentajeAprobados = porcentajeAprobados;
	}
	
	public float getPorcentajeDesaprobados() {
		return PorcentajeDesaprobados;
	}
	
	public void setPorcentajeDesaprobados(float porcentajeDesaprobados) {
		PorcentajeDesaprobados = porcentajeDesaprobados;
	}
	
	public float getPromedioParciales() {
		return PromedioParciales;
	}
	
	public void setPromedioParciales(float promedioParciales) {
		PromedioParciales = promedioParciales;
	}
	
	public float getPromedioTotal() {
		return PromedioTotal;
	}
	
	public void setPromedioTotal(float promedioTotal) {
		PromedioTotal = promedioTotal;
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
	public int getAnio() {
		return Anio;
	}
	public void setAnio(int anio) {
		Anio = anio;
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