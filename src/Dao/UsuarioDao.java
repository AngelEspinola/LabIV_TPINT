package Dao;

import Entidades.Usuario;

public interface UsuarioDao {
	public Usuario buscarUsuario(String name, String pass);
}
