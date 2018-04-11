package br.usjt.arqsw.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.arqsw.model.Chamado;
import br.usjt.arqsw.model.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/**
 * Classe que irá realizar a inserção do chamado
 * 
 * @author Alessandro Lima Da Silva R.A 201522705
 *
 */
public class InserirChamado implements Command {

	/**
	 * Método implementado pela Interface Command responsável por orientar o
	 * controlador a qual classe chamar para executar a resquest informada.
	 */
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Variável que irá guardar a mensagem da request para o usuário
		String mensagem = null;

		// Models
		Chamado chamado = new Chamado();
		Fila fila = new Fila();

		// Services
		ChamadoService service = new ChamadoService();
		FilaService service2 = new FilaService();

		// Recebe os parâmetros do formulário enviados pela request
		String pDescricao = request.getParameter("descricao");
		String pIdFila = request.getParameter("id");

		// Converte o id de String para inteiro
		int id = 0;
		try {
			id = Integer.parseInt(pIdFila);
		} catch (NumberFormatException e) {

		}

		try {
			// Carrega a fila
			fila = service2.carregar(id);

			// Seta os valores para o objeto
			chamado.setDescricao(pDescricao);
			chamado.setFila(fila);

			// Executa o método de inserir
			service.inserir(chamado);

			// Seta a mensagem de sucesso para a view
			mensagem = "sucesso";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Seta a mensagem de sucesso para a view
			mensagem = "falha";
		}

		// Atribui os objetos para a view
		request.setAttribute("mensagem", mensagem);

		// Dispacha os dados para a jsp
		RequestDispatcher view = null;
		view = request.getRequestDispatcher("inserirChamado.jsp");
		view.forward(request, response);

	}

}
