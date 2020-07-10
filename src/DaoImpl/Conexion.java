package DaoImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public static Conexion instancia;
	private String root = "localhost:3306";
	private String bd	= "bdTPINT";
	private String user = "root";
	private String pass = "root";
	private String timeZone = "?useSSL=false&serverTimezone=UTC";
	
	private Connection connection;
	
	private Conexion()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			//Class.forName("com.mysql.cj.jdbc.Driver"); // quitar si no es necesario
			//this.connection = DriverManager.getConnection("jdbc:mysql://"+root+"/"+bd+ "?useSSL=false",user,pass);
			this.connection = DriverManager.getConnection("jdbc:mysql://"+root+"/"+bd+timeZone,user,pass);
			this.connection.setAutoCommit(false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Conexion getConexion()   
	{
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		instancia = null;
	}
}
