package br.com.bibliovirtua.controller;

import br.com.bibliovirtua.modelo.Perfil;
import br.com.bibliovirtua.modelo.Usuario;
import br.com.bibliovirtua.repositorio.UsuarioRepositorio;
import br.com.bibliovirtua.servicos.ServicoEmail;
import br.com.bibliovirtua.util.Criptografia;
import br.com.bibliovirtua.util.UtilSistema;
import org.apache.commons.collections.FastArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 */

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private ServicoEmail servicoEmail;

	/**
	 * @return ModelAndView 
	 */
	@RequestMapping(value = "/administradorusuario")
	public ModelAndView gerenciarUsuario() {
		ModelAndView mv = new ModelAndView("/admin/administradorusuario");
		mv.addObject("listausuarios", usuarioRepositorio.findAll());
		return mv;
	}
	/**
	 * @param usuario 
	 *            
	 * @return ModelAndView  
	 */
	@RequestMapping(value = "/adicionausuario", method = RequestMethod.POST)
	public ModelAndView adicionarUsuario(@ModelAttribute Usuario usuario) {
		ModelAndView mv = new ModelAndView("forward:/administradorusuario");

		if(usuarioRepositorio.exists(usuario.getMatricula())){
			String mensagem = "Ja existe um usuario cadastrado com esse CPF.";
			mv.addObject("erro", mensagem);
		}else{
			usuario.setSenha(Criptografia.bCryptEncoder(usuario.getSenha()));
			usuarioRepositorio.save(usuario);
			String mensagem = "Operaçao realizada com sucesso.";
			mv.addObject("sucesso", mensagem);
		}
		return mv;
	}

	/** 
	 * @param usuario
	 * @return ModelAndView  
	 */
        
	@RequestMapping(value = "/adicionausuariologin", method = RequestMethod.POST)
	public ModelAndView adicionarUsuarioPublico(@ModelAttribute Usuario usuario) {
		ModelAndView mv = new ModelAndView("forward:/login");
		usuario.setSenha(Criptografia.bCryptEncoder(usuario.getSenha()));
		if (usuarioRepositorio.exists(usuario.getMatricula())) {
			String mensagem = "Ja existe um usuario cadastrado com essa matricula.";
			mv.addObject("erro", mensagem);
		} else {
			Integer quantidadeDigitos = Long
					.toString(usuario.getMatricula()).length();
			if (quantidadeDigitos < 7) {
				usuario.setPerfil(Perfil.ALUNO);
			} else {
				usuario.setPerfil(Perfil.PROFESSOR);
			}

			if (usuarioRepositorio.save(usuario) != null) {
				String mensagem = "Operaçao realizada com sucesso! FaÃ§a Login!";
				mv.addObject("sucesso", mensagem);
			} else {
				String mensagem = "Ocorreu um erro ao cadastrar o usuario.";
				mv.addObject("erro", mensagem);
			}
		}
		return mv;
	}

	/**
	 * @param usuario 
	 * @return ModelAndView  
	 */
	@RequestMapping(value = "/editausuario", method = RequestMethod.POST)
	public ModelAndView editarUsuario(@ModelAttribute Usuario usuario) {
		ModelAndView mv = new ModelAndView("forward:/administradorusuario");
		Usuario usuarioEditado = usuarioRepositorio.findOneByMatricula(usuario.getMatricula());
		usuario.setSenha(usuarioEditado.getSenha());
		if (usuarioRepositorio.save(usuario) != null) {
			String mensagem = " Usuario editado com Sucesso!";
			mv.addObject("sucesso", mensagem);
		} else {
			String mensagem = " Erro ao editar usuario!";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}

	/**
	
	 * @param usuario 
         * @return ModelAndView 
	 */
	@RequestMapping(value = "/alterasenha", method = RequestMethod.POST)
	public ModelAndView alterarSenha(@ModelAttribute Usuario usuario) {
		ModelAndView mv = new ModelAndView("forward:/administradorusuario");
		Usuario usuarioNovo = usuarioRepositorio.findOneByMatricula(usuario.getMatricula());
		usuarioNovo.setSenha(Criptografia.bCryptEncoder(usuario.getSenha()));
		if (usuarioRepositorio.save(usuarioNovo) != null) {
			String mensagem = " Senha alterada com sucesso!";
			mv.addObject("sucesso", mensagem);
		} else {
			String mensagem = " Erro ao alterar senha do usuario!";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}

	/**
	 * @param usuario - Modelo usuario
	 * @return ModelAndView 
	 */
	@RequestMapping(value = "/alterasenhamenu", method = RequestMethod.POST)
	public ModelAndView alteraSenhaPerfil(@ModelAttribute Usuario usuario) {
		ModelAndView mv;
		Usuario usuarioNovo = usuarioRepositorio.findOneByMatricula(usuario.getMatricula());
		usuarioNovo.setSenha(Criptografia.bCryptEncoder(usuario.getSenha()));
		if (usuarioRepositorio.save(usuarioNovo) != null) {
			String mensagem = " Senha alterada com sucesso!";
			mv = new ModelAndView("forward:/admin");
			mv.addObject("sucesso", mensagem);
		} else {
			mv = new ModelAndView("forward:/admin");
			String mensagem = " Erro ao alterar senha do usuario!";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}

	/**
	 * 
	 * @param usuario 
	 *            
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/removeusuario", method = RequestMethod.POST)
	public ModelAndView removerUsuario(@ModelAttribute Usuario usuario) {
		ModelAndView mv = new ModelAndView("forward:/administradorusuario");
		usuarioRepositorio.delete(usuario);
		return mv;
	}

	/**
	 * @param usuario 
	 * @param opcaopesquisa 
	 *       
	 * @return ModelAndView 
	 */
	@RequestMapping(value = "/pesquisausuario", method = RequestMethod.POST)
	public ModelAndView buscarUsuario(@ModelAttribute Usuario usuario,
			@RequestParam String opcaopesquisa) {
		ModelAndView mv = new ModelAndView("/admin/administradorusuario");
		List<Usuario> list = new FastArrayList();

		opcaopesquisa = opcaopesquisa.toLowerCase();

		if (opcaopesquisa.equals("matricula")) {
			Usuario usuarioBanco = usuarioRepositorio.findOneByMatricula(usuario.getMatricula());
			if(usuarioBanco != null){
				mv.addObject("listausuarios",list);
			}
		} else if (opcaopesquisa.equals("nome")) {
			mv.addObject("listausuarios", usuarioRepositorio.findByNome(usuario.getNome()));
		}
		return mv;
	}

	@RequestMapping(value = "/solicitarsenha", method = RequestMethod.POST)
	public ModelAndView solicitarsenha(@RequestParam String email, HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/publico/solicitarsenha");

		boolean sucesso = true;

		Usuario usuario = usuarioRepositorio.findByEmail(email);

		if(usuario == null){
			sucesso = false;
			mv.addObject("retorno",sucesso);
		}else{
			String token = UtilSistema.gerarToken();
			usuario.setTokenRedefinicaoSenha(token);
			usuarioRepositorio.save(usuario);

			mv.addObject("retorno",sucesso);
			String assunto = "Redefiniçao de senha - bibliovirtua";
			String texto = "Ola "+usuario.getNome()+
			  			   "<br/>"+
			 			   "Voce solicitou a redefiniçao de senha no sistema BiblioVirtua, caso " +
					       " não seja voce que solicitou, ignore esta mensagem." +
					       "<br/>"+
			               "<br/>"+
					       "Link:"+request.getRequestURL().toString().split("solicitarsenha")[0].concat("validartoken/").concat(token);

			ServicoEmail.enviar(assunto,texto,email);

		}

		mv.addObject("email",email);
		return mv;

	}

	@RequestMapping(value = "/validartoken/{token}", method = RequestMethod.GET)
	public ModelAndView validarToken(@PathVariable String token, HttpServletRequest request){
		ModelAndView mv;

		Usuario usuario = usuarioRepositorio.findByTokenRedefinicaoSenha(token);

		if(usuario == null){
			mv = new ModelAndView("redirect:/");
			mv.addObject("error", "O token de redefiniçao de senha e invalido!");
			return mv;
		}else{
			mv = new ModelAndView("/publico/redefinirsenha");
			mv.addObject("usuario",usuario);
		}

		return mv;

	}

	@RequestMapping(value = "/redefinirsenha", method = RequestMethod.POST)
	public ModelAndView validarToken(@ModelAttribute Usuario usuario){
		ModelAndView mv = new ModelAndView("/publico/redefinirsenha");

		Usuario usuarioNovo = usuarioRepositorio.findOneByMatricula(usuario.getMatricula());

		usuarioNovo.setSenha(Criptografia.bCryptEncoder(usuario.getSenha()));
		usuarioNovo.setTokenRedefinicaoSenha(null);

		usuarioRepositorio.save(usuarioNovo);

		Boolean senhaalterada = true;

		mv.addObject("senhaalterada", senhaalterada);

		return mv;

	}

}
