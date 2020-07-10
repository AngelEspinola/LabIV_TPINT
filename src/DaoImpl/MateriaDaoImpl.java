package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.MateriaDao;
import Entidades.Materia;

public class MateriaDaoImpl implements MateriaDao{
	private static final String readall = "SELECT * FROM BDTPINT.Materias";
	private static final String read = "SELECT * FROM BDTPINT.Materias WHERE ID = ?";
	
	public ArrayList<Materia> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Materia> Materias = new ArrayList<Materia>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Materias.add(getMateria(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Materias;
	}
	public Materia read(int ID) {
		// TODO Auto-generated method stub
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Materia materia = new Materia();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(read);
			statement.setInt(1, ID);
			resultSet = statement.executeQuery();
			if(resultSet.next())
			{
				materia = getMateria(resultSet);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return materia;
	}
	
	private Materia getMateria(ResultSet resultSet) throws SQLException
	{
		int 	   id = resultSet.getInt("ID");
		String nombre = resultSet.getString("Nombre");
		return new Materia(id, nombre);
	}
}
