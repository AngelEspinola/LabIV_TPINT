package Dao;

import java.sql.SQLException;
import java.util.List;

import Entidades.Curso;

public interface CursoDao {
	public boolean insert(Curso curso);
	public boolean modify(Curso curso);
	public boolean delete(Curso curso);
	public boolean baja(String ID);
	public Boolean read(Curso curso) throws SQLException;
	public Boolean read(Curso curso, int ID) throws SQLException;
	public List<Curso> readAll();
	public List<Curso> readAll(int ID);
	public List<Curso> readFilterDate(int i, int f);
	public List<Curso> readFilter(int i, int f, int m);
}
