package br.usjt.arqsw.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Usuario;
import br.usjt.arqsw.service.UsuarioService;

/**
 * @authorAlessandro Lima Da Silva R.A 201522705
 * 
 */

@Controller
public class ManterUsuarioController {
	private UsuarioService usuarioService;

	@Autowired
	public ManterUsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@RequestMapping("/login")
	public String logar(@Valid Usuario usuario, BindingResult result, Model model, HttpSession httpSession)
			throws IOException {
		if (result.hasFieldErrors()) {
			System.out.println("Deu erro " + result.toString());
			return "UsuarioLogin";
		}

		Usuario usuarioValidado = usuarioService.validar(usuario);

		if (usuarioValidado == null) {
			model.addAttribute("erro", "Usuário ou senha inválidos. Digite-os novamente.");
			return "UsuarioLogin";
		}

		httpSession.setAttribute("usuarioLogado", usuarioValidado);

		return "index";
	}

}
