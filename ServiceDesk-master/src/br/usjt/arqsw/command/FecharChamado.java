package br.usjt.arqsw.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.arqsw.service.ChamadoService;

/**
 * Classe que irá realizar o fechamento do chamado
 * 
 * @author Alessandro Lima Da Silva R.A 201522705
 *
 */
public class FecharChamado implements Command {

	/**
	 * Método implementado pela Interface Command responsável por orientar o
	 * controlador qual classe chamar para executar a resquest informada
	 */
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Variável que irá guardar a mensagem da request para o usuário
		String mensagem = null;

		// Services
		ChamadoService service = new ChamadoService();

		// Recebe os id da Fila selecionados da CheckBox e salva em um vetor
		String pdIdFila[] = request.getParameterValues("id");

		// Converte o id para inteiro e realiza a alteração de status do chamado
		int id = 0;
		if (pdIdFila != null && pdIdFila.length > 0) {
			try {
				for (int i = 0; i < pdIdFila.length; i++) {
					id = Integer.parseInt(pdIdFila[i]);
					service.fecharChamado(id);
				}

				mensagem = "sucesso";

			} catch (NumberFormatException | SQLException e) {

			}
		}

		// Seta os objetos para a request
		request.setAttribute("mensagem", mensagem);

		// Dispacha os dados para a jsp
		RequestDispatcher view = null;
		view = request.getRequestDispatcher("action.do?command=ListarFilas");
		view.forward(request, response);

	}

}
