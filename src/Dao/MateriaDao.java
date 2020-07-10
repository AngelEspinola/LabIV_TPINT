package Dao;

import java.util.List;

import Entidades.Materia;

public interface MateriaDao {
	public List<Materia> readAll();
	public Materia read(int ID);
}