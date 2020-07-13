package Entidades;

public class Usuario {
	private int ID;
	private int ID_docente;
	private String Name;
	private String Pass;
	private String Dni;
	private int Rol;
	
	public Usuario(int id, int id_docente, String name, String pass, String dni, int rol) {
		super();
		ID = id;
		ID_docente = id_docente;
		Name = name;
		Pass = pass;
		Dni = dni;
		setRol(rol);
	}
	
	public Usuario()
	{
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}

	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public int getRol() {
		return Rol;
	}

	public void setRol(int rol) {
		Rol = rol;
	}
	public String getRolDescripcion() {
		return getRol()==1?"Administrador":"Docente";
	}

	public int getID_docente() {
		return ID_docente;
	}

	public void setID_docente(int iD_docente) {
		ID_docente = iD_docente;
	}
}
