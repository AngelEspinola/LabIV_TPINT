package Dao;
import java.util.List;

import Entidades.Provincia;

public interface ProvinciaDao {
	public List<Provincia> readAll();
	public Provincia readOne(int ID);
}