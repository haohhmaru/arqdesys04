package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

/**
 * @author 
 * Alessandro Lima Da Silva R.A 201522705
 */

@Service
public class ChamadoService {
	ChamadoDAO dao;

	@Autowired
	public ChamadoService(ChamadoDAO dao) {
		this.dao = dao;
	}

	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException {
		return dao.listarChamados(fila);
	}

	public Chamado criarChamado(Chamado chamado) throws IOException {
		chamado.setStatus(Chamado.ABERTO);
		chamado.setDataAbertura(new Date());
		chamado.setDataFechamento(null);

		Chamado chamadoSalvo = dao.criarChamado(chamado);

		return chamadoSalvo;
	}

}
