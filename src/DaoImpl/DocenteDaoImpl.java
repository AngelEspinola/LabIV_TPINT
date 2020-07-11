package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.DocenteDao;
import Entidades.Docente;
import Entidades.Provincia;
import Entidades.Localidad;

public class DocenteDaoImpl implements DocenteDao{
	private static final String insert = "INSERT INTO BDTPINT.docentes (legajo, dni, nombre, apellido, nacimiento, calle, numero, id_localidad, id_provincia, email, telefono) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	private static final String delete = "DELETE FROM BDTPINT.docentes WHERE Dni = ?";
	private static final String modify = "UPDATE BDTPINT.docentes SET legajo=?, dni=?, nombre=?, apellido=?, nacimiento=?, calle=?, numero=?, id_localidad=?, id_provincia=?, email=?, telefono=? Where dni=?";
	private static final String baja   = "UPDATE BDTPINT.docentes SET estado=? WHERE legajo=? AND dni=?";
	private static final String readall= "SELECT * FROM BDTPINT.docentes WHERE estado=1";
	private static final String readone= "SELECT * FROM BDTPINT.docentes WHERE estado=1 AND legajo=? AND dni=?";
	private static final String read   = "SELECT * FROM bdTPInt.Docentes WHERE DNI=? AND estado=1";
	private static final String readID = "SELECT * FROM bdTPInt.Docentes WHERE ID=? AND estado=1";
	
	public boolean insert(Docente docente)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, docente.getLegajo());
			statement.setString(2, docente.getDni());
			statement.setString(3, docente.getNombre());
			statement.setString(4, docente.getApellido());
			statement.setString(5, docente.getNacimiento());
			statement.setString(6, docente.getCalle());
			statement.setString(7, docente.getNumero());
			statement.setInt(8, docente.getLocalidad().getId());
			statement.setInt(9, docente.getProvincia().getId());
			statement.setString(10, docente.getEmail());
			statement.setString(11, docente.getTelefono());
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
	
	public boolean delete(Docente docenteAEliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, docenteAEliminar.getDni());
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
	
	public boolean modify(Docente docenteAModificar, String DNI)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isModificado = false;
		try 
		{
			statement = conexion.prepareStatement(modify);
			statement.setString(1, docenteAModificar.getLegajo());
			statement.setString(2, docenteAModificar.getDni());
			statement.setString(3, docenteAModificar.getNombre());
			statement.setString(4, docenteAModificar.getApellido());
			statement.setString(5, docenteAModificar.getNacimiento());
			statement.setString(6, docenteAModificar.getCalle());
			statement.setString(7, docenteAModificar.getNumero());
			statement.setInt(8, docenteAModificar.getLocalidad().getId());
			statement.setInt(9, docenteAModificar.getProvincia().getId());
			statement.setString(10, docenteAModificar.getEmail());
			statement.setString(11, docenteAModificar.getTelefono());
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
	
	public boolean baja(Docente docenteDeBaja)
	{
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement statement;
		boolean isModificado = false;
		try 
		{
			statement = conexion.prepareStatement(baja);
			statement.setBoolean(1, docenteDeBaja.getEstado());
			statement.setString(2, docenteDeBaja.getLegajo());
			statement.setString(3, docenteDeBaja.getDni());
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
	
	public ArrayList<Docente> readAll()
	{
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Docente> docentes = new ArrayList<Docente>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				docentes.add(getDocente(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return docentes;
	}
	
	public Docente read(String docenteDNI)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Docente docente = new Docente();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(read);
			statement.setString(1, docenteDNI);
			resultSet = statement.executeQuery();
			if(resultSet.next())
			{
				docente = getDocente(resultSet);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return docente;
	}
	
	public Docente readID(int ID)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Docente docente = new Docente();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readID);
			statement.setInt(1, ID);
			resultSet = statement.executeQuery();
			if(resultSet.next())
			{
				docente = getDocente(resultSet);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return docente;
	}
	
	public Docente buscarDocente(Docente d)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Docente docente = null;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readone);
			statement.setString(1, d.getLegajo());
			statement.setString(2, d.getDni());
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				docente = getDocente(resultSet);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return docente;
	}
	
	private Docente getDocente(ResultSet resultSet) throws SQLException
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
		return new Docente(id, legajo, DNI, nombre, apellido, nacimiento, calle, numero, localidad, provincia, email, telefono);
	}
}
