package br.usjt.arqsw.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.arqsw.model.Fila;
import br.usjt.arqsw.service.FilaService;
import br.usjt.arqsw.model.Chamado;
import br.usjt.arqsw.service.ChamadoService;

/**
 * Classe que irá exibir uma lista de chamados
 * 
 * @author Alessandro Lima Da Silva R.A 201522705
 *
 */
public class ListarChamados implements Command {

	/**
	 * Método implementado pela Interface Command responsável por orientar o
	 * controlador a qual classe chamar para executar a resquest informada
	 */
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Models
		Fila fila = new Fila();
		ArrayList<Chamado> chamados = new ArrayList<Chamado>();

		// Services
		ChamadoService service = new ChamadoService();
		FilaService service2 = new FilaService();

		// Recebe o id da Fila através do formulário da request
		String pIdFila = request.getParameter("id");

		// Converte o id de String para inteiro
		int id = 0;
		try {
			id = Integer.parseInt(pIdFila);
		} catch (NumberFormatException e) {

		}

		try {
			// Carrega o objeto fila
			fila = service2.carregar(id);
			// Carrega a lista de chamados
			chamados = service.listarChamados(fila);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Atribui os objetos para a request
		request.setAttribute("fila", fila);
		request.setAttribute("chamados", chamados);

		// Dispacha os dados para a jsp
		RequestDispatcher view = null;
		view = request.getRequestDispatcher("listarChamados.jsp");
		view.forward(request, response);

	}

}
