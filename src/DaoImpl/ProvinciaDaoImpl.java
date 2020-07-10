package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.ProvinciaDao;
import Entidades.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao{
	private static final String readall = "SELECT * FROM bdTPInt.Provincias";
	private static final String readone = "SELECT * FROM bdTPInt.Provincias WHERE ID=?";
	
	public ArrayList<Provincia> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Provincia> Provincias = new ArrayList<Provincia>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Provincias.add(getProvincia(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Provincias;
	}
	
	public Provincia readOne(int ID)
	{
		Conexion conexion = Conexion.getConexion();
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Provincia p = new Provincia();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readone);
			statement.setInt(1, ID);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				p = getProvincia(resultSet);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return p;
	}
	
	private Provincia getProvincia(ResultSet resultSet) throws SQLException
	{
		int 	   id = resultSet.getInt("ID");
		String nombre = resultSet.getString("nombre");
		return new Provincia(id, nombre);
	}
}