package Dao;

import java.util.List;

import Entidades.Docente;

public interface DocenteDao {
	public boolean insert(Docente docente);
	public boolean modify(Docente docenteAModificar, String DNI);
	public boolean delete(Docente docenteAEliminar);
	public boolean baja(Docente docenteBaja);
	public List<Docente> readAll();
	public Docente buscarDocente(Docente docente);
	public Docente read(String docenteDNI);
}