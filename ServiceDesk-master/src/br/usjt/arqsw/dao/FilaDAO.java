package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Fila;
/**
 * @author 
 * Alessandro Lima Da Silva R.A 201522705
 */

@Repository
public class FilaDAO {
	private Connection conn;

	@Autowired
	public FilaDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	public ArrayList<Fila> listarFilas() throws IOException {
		String query = "select ID_FILA, NM_FILA from FILA";
		ArrayList<Fila> lista = new ArrayList<>();
		try(PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();){
			
			while(rs.next()) {
				Fila fila = new Fila();
				fila.setId(rs.getInt("ID_FILA"));
				fila.setNome(rs.getString("NM_FILA"));
				lista.add(fila);
			}
			
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return lista;
	}

	public Fila carregar(int id) throws IOException {
		String query = "select ID_FILA, NM_FILA from FILA WHERE ID_FILA = ?";
		Fila fila = new Fila();
		try(PreparedStatement pst = conn.prepareStatement(query);){
			
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();			
			
			if(rs.next()) {
				fila.setId(rs.getInt("ID_FILA"));
				fila.setNome(rs.getString("NM_FILA"));
			}
			
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return fila;
	}

}
