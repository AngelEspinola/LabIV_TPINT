
package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.LocalidadDao;
import Entidades.Localidad;

public class LocalidadDaoImpl implements LocalidadDao{
	private static final String readALL = "SELECT * FROM BDTPINT.localidades";
	private static final String readall = "SELECT * FROM BDTPINT.localidades WHERE id_provincia=?";
	private static final String readone = "SELECT * FROM BDTPINT.localidades WHERE ID=?";
	
	
	public ArrayList<Localidad> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Localidad> Localidades = new ArrayList<Localidad>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readALL);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Localidades.add(getLocalidad(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Localidades;
	}
	public ArrayList<Localidad> readAll(int idProvincia)
	{
		Conexion conexion = Conexion.getConexion();
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Localidad> localidades = new ArrayList<Localidad>();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setInt(1, idProvincia);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				localidades.add(getLocalidad(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return localidades;
	}
	public Localidad readOne(int ID)
	{
		Conexion conexion = Conexion.getConexion();
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Localidad l = new Localidad();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readone);
			statement.setInt(1, ID);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				l = getLocalidad(resultSet);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return l;
	}
	
	private Localidad getLocalidad(ResultSet resultSet) throws SQLException
	{
		int id 			= resultSet.getInt("ID");
		int idProvincia = resultSet.getInt("id_provincia");
		String nombre 	= resultSet.getString("nombre");
		return new Localidad(id, idProvincia, nombre);
	}
}
