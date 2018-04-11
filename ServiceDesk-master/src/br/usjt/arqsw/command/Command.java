package br.usjt.arqsw.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface que implementa o método "executar"
 * 
 * @author Alessandro Lima Da Silva R.A 201522705
 *
 */
public interface Command {
	/**
	 * Assinatura de Método
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
