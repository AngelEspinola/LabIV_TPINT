package Dao;

import java.util.ArrayList;

import Entidades.AlumnoNotas;

public interface AlumnoNotasDao
{
	public boolean insert(int idCurso, int idAlumno);
	public boolean modify(AlumnoNotas notas);
	public ArrayList<AlumnoNotas> readAll(int idCurso);
}
