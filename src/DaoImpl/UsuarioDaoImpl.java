package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.UsuarioDao;
import Entidades.Usuario;

public class UsuarioDaoImpl implements UsuarioDao{
	private static final String readone = "SELECT * FROM BDTPINT.usuarios WHERE estado=1 AND usuario=? AND clave=?";
	
	public Usuario buscarUsuario(String name, String pass)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Usuario usuario = null;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readone);
			statement.setString(1, name);
			statement.setString(2, pass);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				usuario = getUsuario(resultSet);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return usuario;
	}
	
	private Usuario getUsuario(ResultSet resultSet) throws SQLException
	{
		//int 	   id = resultSet.getInt("ID");
		int idDocente = resultSet.getInt("id_docente");
		String usuario= resultSet.getString("usuario");
		String clave  = resultSet.getString("clave");
		//String estado = resultSet.getString("estado");
		int    idRol  = resultSet.getInt("id_rol");
		return new Usuario(idDocente,usuario,clave,clave,idRol);
	}	
}