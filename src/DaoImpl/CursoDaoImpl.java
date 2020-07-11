package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Dao.*;
import Entidades.Curso;

public class CursoDaoImpl implements CursoDao{
	private static final String insert = "INSERT INTO bdTPInt.Cursos (docente, cuatrimestre, año) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM bdTPInt.Docentes WHERE Dni = ?";
	private static final String modify = "UPDATE bdTPInt.Docentes SET DNI=?, Nombre=?, Apellido=?, Nacimiento=?, Direccion=?, Localidad=?, Provincia=?, Email=?, Telefono=? Where Dni=?";
	private static final String baja   = "UPDATE bdTPInt.cursos SET Baja=1 WHERE ID=?";
	private static final String readall = "SELECT * FROM bdTPInt.Docentes WHERE Baja=0";
	private static final String read = "SELECT * FROM bdTPInt.cursos WHERE docente=? AND cuatrimestre=? AND año=? AND baja=0";
	
	public boolean insert(Curso curso) {
		// TODO Auto-generated method stub
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean success = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, curso.getDocente().getID());
			statement.setInt(2, curso.getCuatrimestre());
			statement.setInt(3, curso.getAño());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				success = true;
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
		
		return success;
	}
	@Override
	public boolean modify(Curso curso, int ID) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Curso curso) {
		
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean baja(String ID) {
		// TODO Auto-generated method stub
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean success = false;
		try
		{
			statement = conexion.prepareStatement(baja);
			statement.setInt(1, Integer.parseInt(ID));
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				success = true;
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
		
		return success;
	}
	@Override
	public List<Curso> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Curso read(Curso curso) {
		// TODO Auto-generated method stub
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try
		{
			statement = conexion.prepareStatement(read);
			statement.setInt(1, curso.getDocente().getID());
			statement.setInt(2, curso.getCuatrimestre());
			statement.setInt(3, curso.getAño());
			
			resultSet = statement.executeQuery();
			if(resultSet.next())
			{
				curso = getCurso(resultSet);
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
		
		return curso;
	}
	
	private Curso getCurso(ResultSet result) throws SQLException
	{
		DocenteDaoImpl docenteDAO = new DocenteDaoImpl();
		MateriaDaoImpl materiaDAO = new MateriaDaoImpl();
		Curso nuevoCurso = new Curso();
		
		nuevoCurso.setID(result.getInt("ID"));
		nuevoCurso.setDocente(docenteDAO.readID(result.getInt("docente")));
		nuevoCurso.setAño(result.getInt("año"));
		nuevoCurso.setCuatrimestre(result.getInt("cuatrimestre"));
		nuevoCurso.setMateria(materiaDAO.read(result.getInt("materia")));
		
		return nuevoCurso;
	}
}