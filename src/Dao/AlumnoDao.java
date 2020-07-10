package Dao;

import java.util.ArrayList;
import Entidades.Alumno;

public interface AlumnoDao
{
	public boolean insert(Alumno alumno);
	public boolean modify(Alumno alumnoAModificar, String DNI);
	public boolean delete(Alumno alumnoAEliminar);
	public boolean baja(Alumno alumnoBaja);
	public ArrayList<Alumno> readAll();
	public Alumno buscarAlumno(Alumno alumno);
}
