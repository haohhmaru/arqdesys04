package br.usjt.arqsw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.arqsw.command.Command;

/**
 * Servlet Controlador da App, que ir� fazer o relacionamento entre a View e o
 * Model necess�rios
 * 
 * @author Alessandro Lima Da Silva R.A 201522705
 *
 */
@WebServlet("/action.do")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Realiza o controle de trafego, orientado o controlador a qual classe ir�
	 * chamar para executar a requisi��o
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doExecute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			Command comando = (Command) Class.forName("br.usjt.arqsw.command." + request.getParameter("command"))
					.newInstance();
			comando.executar(request, response);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

	/**
	 * For�a a chamada do m�todo doExecute
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doExecute(request, response);
	}

	/**
	 * For�a a chamada do m�todo doExecute
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doExecute(request, response);
	}

}
