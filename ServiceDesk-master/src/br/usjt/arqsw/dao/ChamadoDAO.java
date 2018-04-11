package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

/**
 * @author Alessandro Lima Da Silva R.A 201522705
 * 
 */

@Repository
public class ChamadoDAO {
	private Connection conn;

	@Autowired
	public ChamadoDAO(DataSource dataSource) throws IOException {
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException {
		String query = "select ID_CHAMADO, DESCRICAO, STATUS, DT_ABERTURA, DT_FECHAMENTO from CHAMADO where ID_FILA = ?;";
		ArrayList<Chamado> lista = new ArrayList<>();
		try (PreparedStatement pst = conn.prepareStatement(query);) {

			pst.setInt(1, fila.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Chamado chamado = new Chamado();
				chamado.setId(rs.getInt("ID_CHAMADO"));
				chamado.setDescricao(rs.getString("DESCRICAO"));
				chamado.setStatus(rs.getString("STATUS"));
				chamado.setDataAbertura(new java.util.Date(rs.getDate("DT_ABERTURA").getTime()));
				chamado.setDataFechamento(
						rs.getDate("DT_FECHAMENTO") != null ? new java.util.Date(rs.getDate("DT_FECHAMENTO").getTime())
								: null);
				chamado.setFila(fila);
				lista.add(chamado);
			}

		} catch (SQLException e) {
			throw new IOException(e);
		}
		return lista;
	}

	public Chamado criarChamado(Chamado chamado) throws IOException {
		String query = "insert into CHAMADO (DESCRICAO, STATUS, DT_ABERTURA, ID_FILA) values(?, ?, ?, ?)";
		try (PreparedStatement pst = conn.prepareStatement(query);) {
			pst.setString(1, chamado.getDescricao());
			pst.setString(2, chamado.getStatus());
			pst.setDate(3, new Date(chamado.getDataAbertura().getTime()));
			pst.setInt(4, chamado.getFila().getId());

			pst.execute();

		} catch (SQLException e) {
			throw new IOException(e);
		}

		return obterUltimoChamado();
	}

	private Chamado obterUltimoChamado() throws IOException {
		String query = "select * from CHAMADO order by ID_CHAMADO desc limit 1";
		Chamado chamado = null;
		try (PreparedStatement pst = conn.prepareStatement(query); ResultSet rs = pst.executeQuery();) {

			if (rs.next()) {
				chamado = new Chamado();
				Fila fila = new Fila();
				chamado.setId(rs.getInt("ID_CHAMADO"));
				chamado.setDescricao(rs.getString("DESCRICAO"));
				chamado.setStatus(rs.getString("STATUS"));
				chamado.setDataAbertura(new java.util.Date(rs.getDate("DT_ABERTURA").getTime()));
				chamado.setDataFechamento(
						rs.getDate("DT_FECHAMENTO") != null ? new java.util.Date(rs.getDate("DT_FECHAMENTO").getTime())
								: null);
				fila.setId(rs.getInt("ID_FILA"));
				chamado.setFila(fila);
			}

		} catch (SQLException e) {
			throw new IOException(e);
		}
		return chamado;
	}

}
