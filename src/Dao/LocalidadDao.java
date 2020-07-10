package Dao;

import java.util.List;

import Entidades.Localidad;

public interface LocalidadDao {
	public List<Localidad> readAll(int IDProvincia);
	public List<Localidad> readAll();
	public Localidad readOne(int ID);
}
