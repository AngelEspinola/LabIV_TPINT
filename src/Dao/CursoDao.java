package Dao;

import java.util.List;

import Entidades.Curso;

public interface CursoDao {
	public boolean insert(Curso curso);
	public boolean modify(Curso curso, int ID);
	public boolean delete(Curso curso);
	public boolean baja(String ID);
	public Curso read(Curso curso);
	public List<Curso> readAll();
}
