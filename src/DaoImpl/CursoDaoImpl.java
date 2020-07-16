package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.*;
import Entidades.Curso;
import Entidades.Materia;

public class CursoDaoImpl implements CursoDao{
	private static final String insert = "INSERT INTO bdTPInt.cursos (docente, cuatrimestre, anio, materia) VALUES(?, ?, ?, ?)";
	private static final String delete = "DELETE FROM bdTPInt.Docentes WHERE Dni = ?";
	private static final String modify = "UPDATE bdTPInt.cursos SET docente=?, cuatrimestre=?, ani=?, materia=? Where ID=?";
	private static final String baja   = "UPDATE bdTPInt.cursos SET Baja=1 WHERE ID=?";
	private static final String readall = "SELECT * FROM bdTPInt.cursos WHERE Baja=0";
	private static final String readallforID = "SELECT * FROM bdTPInt.cursos WHERE Baja=0 AND docente=?";
	private static final String read = "SELECT * FROM bdTPInt.cursos WHERE materia=? AND cuatrimestre=? AND anio=? AND baja=0";
	private static final String readforID = "SELECT * FROM bdTPInt.cursos WHERE materia=? AND cuatrimestre=? AND anio=? AND baja=0 AND docente=?";
	private static final String readFilter = "SELECT * FROM bdtpint.cursos WHERE anio BETWEEN ? AND ? AND materia=?";
	private static final String readFilterDate = "SELECT * FROM bdtpint.cursos WHERE anio BETWEEN ? AND ?";
	
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
			statement.setInt(3, curso.getAnio());
			statement.setInt(4, curso.getMateria().getId());
			
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
	public boolean modify(Curso curso) {
		// TODO Auto-generated method stub
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean success = false;
		if(curso.getID() != 0)
		{
			try
			{
				statement = conexion.prepareStatement(modify);
				statement.setInt(1, curso.getDocente().getID());
				statement.setInt(2, curso.getCuatrimestre());
				statement.setInt(3, curso.getAnio());
				statement.setInt(4, curso.getMateria().getId());
				statement.setInt(5, curso.getID());
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
			
		}
		
		return success;
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
	public ArrayList<Curso> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Curso nuevoCurso = new Curso();
				if(getCurso(resultSet, nuevoCurso))
				{
					cursos.add(nuevoCurso);
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return cursos;
	}
	@Override
	public ArrayList<Curso> readAll(int ID) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readallforID);
			statement.setInt(1, ID);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Curso nuevoCurso = new Curso();
				if(getCurso(resultSet, nuevoCurso))
				{
					cursos.add(nuevoCurso);
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return cursos;
	}
	@Override
	public Boolean read(Curso curso) throws SQLException {
		// TODO Auto-generated method stub
		Boolean response = false;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement statement = conexion.prepareStatement(read);
		statement.setInt(1, curso.getMateria().getId());
		statement.setInt(2, curso.getCuatrimestre());
		statement.setInt(3, curso.getAnio());
		ResultSet resultSet = statement.executeQuery();
		try
		{	
			if(resultSet.next())
			{
				response = getCurso(resultSet, curso);
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
		finally {
		    //try { resultSet.close(); } catch (Exception e) { /* ignored */ }
		    //try { statement.close(); } catch (Exception e) { /* ignored */ }
		    //try { conexion.close(); } catch (Exception e) { /* ignored */ }
		}
		
		return response;
	}
	
	@Override
	public Boolean read(Curso curso, int ID) throws SQLException {
		// TODO Auto-generated method stub
		Boolean response = false;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement statement = conexion.prepareStatement(readforID);
		statement.setInt(1, curso.getMateria().getId());
		statement.setInt(2, curso.getCuatrimestre());
		statement.setInt(3, curso.getAnio());
		statement.setInt(4, ID);
		ResultSet resultSet = statement.executeQuery();
		try
		{	
			if(resultSet.next())
			{
				response = getCurso(resultSet, curso);
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
		finally {
		    //try { resultSet.close(); } catch (Exception e) { /* ignored */ }
		    //try { statement.close(); } catch (Exception e) { /* ignored */ }
		    //try { conexion.close(); } catch (Exception e) { /* ignored */ }
		}
		
		return response;
	}
	
	public ArrayList<Curso> readFilterDate(int i, int f) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readFilterDate);
			statement.setInt(1, i);
			statement.setInt(2, f);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Curso nuevoCurso = new Curso();
				if(getCurso(resultSet, nuevoCurso))
				{
					cursos.add(nuevoCurso);
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return cursos;
	}
	public ArrayList<Curso> readFilter(int i, int f, int m) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readFilter);
			statement.setInt(1, i);
			statement.setInt(2, f);
			statement.setInt(3, m);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Curso nuevoCurso = new Curso();
				if(getCurso(resultSet, nuevoCurso))
				{
					cursos.add(nuevoCurso);
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return cursos;
	}
	
	private Boolean getCurso(ResultSet result, Curso curso) throws SQLException
	{
		Boolean response = true;
		DocenteDaoImpl docenteDAO = new DocenteDaoImpl();
		MateriaDaoImpl materiaDAO = new MateriaDaoImpl();
		try
		{
			curso.setID(result.getInt("ID"));
			curso.setDocente(docenteDAO.readID(result.getInt("docente")));
			curso.setAnio(result.getInt("anio"));
			curso.setCuatrimestre(result.getInt("cuatrimestre"));
			curso.setMateria(materiaDAO.read(result.getInt("materia")));
			curso.setAprobados(result.getInt("aprobados"));
			curso.setDesaprobados(result.getInt("desaprobados"));
			curso.setPorcentajeAprobados(result.getFloat("porcentajeAprobados"));
			curso.setPorcentajeDesaprobados(result.getFloat("porcentajeDesaprobados"));
			curso.setPromedioParciales(result.getFloat("promedio1"));
			curso.setPromedioTotal(result.getFloat("promedio2"));
		}
		catch(Exception e)
		{
			response = false;
		}
		
		return response;
		
	}
	
}