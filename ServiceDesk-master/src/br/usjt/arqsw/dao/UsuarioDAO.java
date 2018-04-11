package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Usuario;
/**
 * @author 
 * Alessandro Lima Da Silva R.A 201522705
 */

@Repository
public class UsuarioDAO {
	private Connection conn;

	@Autowired
	public UsuarioDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	public Usuario validar(Usuario usuario) throws IOException {
		Usuario novoUsuario = null;
		// usuario.setUsername(username);
		// usuario.setPassword(password);

		String query = "select EMAIL_USUARIO, SENHA_USUARIO from USUARIO where EMAIL_USUARIO = ? and SENHA_USUARIO";

		try (PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setString(1, usuario.getUsername());
			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					novoUsuario = new Usuario();
					novoUsuario.setUsername(rs.getString("EMAIL_USUARIO"));
					novoUsuario.setPassword(rs.getString("SENHA_USUARIO"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		return novoUsuario;
	}
}
