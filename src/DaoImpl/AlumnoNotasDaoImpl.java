package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entidades.AlumnoNotas;
import Dao.AlumnoNotasDao;

public class AlumnoNotasDaoImpl implements AlumnoNotasDao{
	private static final String insert = "INSERT INTO BDTPINT.alumnoxcurso (id_curso, id_alumno) VALUES(?, ?)";
	private static final String modify = "UPDATE BDTPINT.alumnoxcurso SET nota1=?, nota2=?, recuperatorio1=?, recuperatorio2=?, estado=? Where id_curso=? AND id_alumno=?";
	private static final String readall = "SELECT * FROM BDTPINT.alumnonotas WHERE id_curso=?";
	
	public boolean insert(int idCurso, int idAlumno)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, idCurso);
			statement.setInt(2, idAlumno);
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}
	
	public boolean modify(AlumnoNotas notas)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isModificado = false;
		try 
		{
			statement = conexion.prepareStatement(modify);
			statement.setInt(1, notas.getNota1());
			statement.setInt(2, notas.getNota2());
			statement.setInt(3, notas.getRecuperatorio1());
			statement.setInt(4, notas.getRecuperatorio2());
			statement.setInt(5, notas.getEstadoInt()-1);
			statement.setInt(6, notas.getIdCurso());
			statement.setInt(7, notas.getIdAlumno());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isModificado = true;
			}
			//conexion.cerrarConexion();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isModificado;
	}
	
	public ArrayList<AlumnoNotas> readAll(int idCurso)
	{
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<AlumnoNotas> alumnoNotas = new ArrayList<AlumnoNotas>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setInt(1, idCurso);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				alumnoNotas.add(getAlumnoNotas(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return alumnoNotas;
	}
	
	private AlumnoNotas getAlumnoNotas(ResultSet resultSet) throws SQLException
	{
		int    idAlumno = resultSet.getInt("ID");
		String legajo 	= resultSet.getString("legajo");
		String nombre 	= resultSet.getString("nombre");
		String apellido = resultSet.getString("apellido");
		int    idCurso 	= resultSet.getInt("id_curso");
		int    nota1    = resultSet.getInt("nota1");
		int    nota2	= resultSet.getInt("nota2");
		int    recu1	= resultSet.getInt("recuperatorio1");
		int    recu2	= resultSet.getInt("recuperatorio2");
		String estado	= resultSet.getInt("estado")==1?"Regular":"Libre";
		
		return new AlumnoNotas(idAlumno, idCurso, legajo, nombre, apellido, nota1, nota2, recu1, recu2, estado);
	}

}
