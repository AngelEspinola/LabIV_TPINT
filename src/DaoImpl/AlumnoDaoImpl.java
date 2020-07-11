package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.AlumnoDao;
import Entidades.Alumno;
import Entidades.Provincia;
import Entidades.Localidad;

public class AlumnoDaoImpl implements AlumnoDao{
	private static final String insert = "INSERT INTO BDTPINT.alumnos (legajo, dni, nombre, apellido, nacimiento, calle, numero, id_localidad, id_provincia, email, telefono) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	private static final String delete = "DELETE FROM BDTPINT.alumnos WHERE Dni = ?";
	private static final String modify = "UPDATE BDTPINT.alumnos SET legajo=?, dni=?, nombre=?, apellido=?, nacimiento=?, calle=?, numero=?, id_localidad=?, id_provincia=?, email=?, telefono=? Where dni=?";
	private static final String baja   = "UPDATE BDTPINT.alumnos SET estado=? WHERE legajo=? AND dni=?";
	private static final String readall = "SELECT * FROM BDTPINT.alumnos WHERE estado=1";
	private static final String readone = "SELECT * FROM BDTPINT.alumnos WHERE estado=1 AND legajo=? AND dni=?";
	
	public boolean insert(Alumno alumno)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, alumno.getLegajo());
			statement.setString(2, alumno.getDni());
			statement.setString(3, alumno.getNombre());
			statement.setString(4, alumno.getApellido());
			statement.setString(5, alumno.getNacimiento());
			statement.setString(6, alumno.getCalle());
			statement.setString(7, alumno.getNumero());
			statement.setInt(8, alumno.getLocalidad().getId());
			statement.setInt(9, alumno.getProvincia().getId());
			statement.setString(10, alumno.getEmail());
			statement.setString(11, alumno.getTelefono());
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
	
	public boolean delete(Alumno alumnoAEliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, alumnoAEliminar.getDni());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}
	
	public boolean modify(Alumno alumnoAModificar, String DNI)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isModificado = false;
		try 
		{
			statement = conexion.prepareStatement(modify);
			statement.setString(1, alumnoAModificar.getLegajo());
			statement.setString(2, alumnoAModificar.getDni());
			statement.setString(3, alumnoAModificar.getNombre());
			statement.setString(4, alumnoAModificar.getApellido());
			statement.setString(5, alumnoAModificar.getNacimiento());
			statement.setString(6, alumnoAModificar.getCalle());
			statement.setString(7, alumnoAModificar.getNumero());
			statement.setInt(8, alumnoAModificar.getLocalidad().getId());
			statement.setInt(9, alumnoAModificar.getProvincia().getId());
			statement.setString(10, alumnoAModificar.getEmail());
			statement.setString(11, alumnoAModificar.getTelefono());
			statement.setString(12,DNI);
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isModificado = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isModificado;
	}
	
	public boolean baja(Alumno alumnoDeBaja)
	{
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement statement;
		boolean isModificado = false;
		try 
		{
			statement = conexion.prepareStatement(baja);
			statement.setBoolean(1, alumnoDeBaja.getEstado());
			statement.setString(2, alumnoDeBaja.getLegajo());
			statement.setString(3, alumnoDeBaja.getDni());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isModificado = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isModificado;
	}
	
	public ArrayList<Alumno> readAll()
	{
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				alumnos.add(getAlumno(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return alumnos;
	}
	
	public Alumno buscarAlumno(Alumno d)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Alumno alumno = null;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readone);
			statement.setString(1, d.getLegajo());
			statement.setString(2, d.getDni());
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				alumno = getAlumno(resultSet);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return alumno;
	}
	
	private Alumno getAlumno(ResultSet resultSet) throws SQLException
	{
		ProvinciaDaoImpl PDAO = new ProvinciaDaoImpl();
		LocalidadDaoImpl LDAO = new LocalidadDaoImpl();
		
		int 	  id 		= resultSet.getInt("ID");
		String 	  legajo 	= resultSet.getString("legajo");
		String    DNI 	    = resultSet.getString("dni");
		String    nombre 	= resultSet.getString("nombre");
		String    apellido  = resultSet.getString("apellido");
		String    nacimiento= resultSet.getString("nacimiento");
		String 	  calle		= resultSet.getString("calle");
		String 	  numero	= resultSet.getString("numero");
		Localidad localidad = LDAO.readOne(resultSet.getInt("id_localidad"));
		Provincia provincia = PDAO.readOne(resultSet.getInt("id_provincia"));
		String 	  email 	= resultSet.getString("email");
		String    telefono  = resultSet.getString("telefono");
		return new Alumno(id, legajo, DNI, nombre, apellido, nacimiento, calle, numero, localidad, provincia, email, telefono);
	}
}
