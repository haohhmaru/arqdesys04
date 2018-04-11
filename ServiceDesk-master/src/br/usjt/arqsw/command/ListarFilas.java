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

/**
 * Classe que irá exibir uma lista de filas
 * 
 * @author Alessandro Lima Da Silva R.A 201522705
 *
 */
public class ListarFilas implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Models
		ArrayList<Fila> filas = new ArrayList<Fila>();

		// Services
		FilaService service = new FilaService();

		try {
			// Alimenta o array com uma lista de filas vindas do Banco
			filas = service.listarFilas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Atribui o objeto para a request
		request.setAttribute("filas", filas);

		// Dispacha os dados para a jsp
		RequestDispatcher view = null;
		view = request.getRequestDispatcher("listarFilas.jsp");
		view.forward(request, response);

	}

}
