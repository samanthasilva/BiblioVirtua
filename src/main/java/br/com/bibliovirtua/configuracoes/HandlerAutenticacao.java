package br.com.bibliovirtua.configuracoes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bibliovirtua.modelo.Perfil;
import br.com.bibliovirtua.modelo.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import org.springframework.stereotype.Component;


@Component
public class HandlerAutenticacao implements AuthenticationSuccessHandler {

	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication)
										throws IOException, ServletException {

		SecurityContextHolder.getContext().getAuthentication();

		Usuario usuario = (Usuario) authentication.getPrincipal();

		request.getSession().setAttribute("nome_usuario_sessao", usuario.getNome());

		String URL = request.getRequestURL().toString().split("j_spring_security_check")[0];

		if (usuario.getAuthorities().contains(Perfil.ALUNO)) {
			response.sendRedirect(URL.concat("aluno"));
		}else if (usuario.getAuthorities().contains(Perfil.PROFESSOR)){
			response.sendRedirect(URL.concat("professor"));
		}else if  (usuario.getAuthorities().contains(Perfil.ADMIN)){
			response.sendRedirect(URL.concat("admin"));
		}
	}
}