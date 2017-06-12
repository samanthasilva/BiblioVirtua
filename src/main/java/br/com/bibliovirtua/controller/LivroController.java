package br.com.bibliovirtua.controller;

import javax.servlet.http.HttpServletRequest;

import br.com.bibliovirtua.repositorio.DisciplinaRepositorio;
import br.com.bibliovirtua.repositorio.LivroRepositorio;
import org.apache.commons.collections.FastArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.bibliovirtua.modelo.Disciplina;
import br.com.bibliovirtua.modelo.Livro;

import java.util.List;
import java.util.Objects;

/**
 * Classe responsavel por implementar Servlets de controle que estÃ£o
 * relacionados a Livro.
 * 
 * @author Samantha Silva
 *
 */

@Controller
public class LivroController {

	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;

	@Autowired
	private LivroRepositorio livroRepositorio;

	/**
	 * @return ModelAndView 
	 */
	@RequestMapping(value = "/administradorlivro")
	public ModelAndView gerenciarLivros() {
		ModelAndView mv = new ModelAndView("/admin/administradorlivro");
		mv.addObject("listalivros", livroRepositorio.findAll());
		mv.addObject("listadisciplinas", disciplinaRepositorio.findAll());
		return mv;
	}

	/**
	 * 
	 * @param livro  
     * @param disciplina  
     * @param request  
   * @return ModelAndView  
	 */
	@RequestMapping(value = "/adicionalivro", method = RequestMethod.POST)
	public ModelAndView adicionarLivro(@ModelAttribute Livro livro, @ModelAttribute Disciplina disciplina,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("forward:/administradorlivro");


		Livro livroBanco = livroRepositorio.findByIsbn(livro.getIsbn());

		if(livroBanco != null){
				String mensagem = "O Livro ja esta cadastrado.";
				mv.addObject("erro", mensagem);
				return mv;
		}

		if (livroRepositorio.save(livro) != null) {
			String mensagem = "Operaçao realizada com sucesso.";
			mv.addObject("sucesso", mensagem);
		} else {
			String mensagem = "Ocorreu um erro ao cadastrar livro.";
			mv.addObject("erro", mensagem);
		}

		return mv;
	}

	/**
	 * @param livro  
	 * 
	 * @return ModelAndView  
	 */
	@RequestMapping(value = "/editarlivro", method = RequestMethod.POST)
	public ModelAndView editarLivro(@ModelAttribute Livro livro) {
		ModelAndView mv = new ModelAndView("forward:/administradorlivro");

		Livro livroBanco = livroRepositorio.findByIsbn(livro.getIsbn());
		if(!livroBanco.getDisciplinas().isEmpty()){
			livro.setDisciplinas(livroBanco.getDisciplinas());
		}

		if (livroRepositorio.save(livro) != null) {
			String mensagem = "Operaçao realizada com sucesso.";
			mv.addObject("sucesso", mensagem);
		} else {
			String mensagem = "Ocorreu um erro ao editar o livro.";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}

	/**
	 * @param livro - Modelo livro
	
	 * @return ModelAndView 
	 */
	@RequestMapping(value = "/removelivro", method = RequestMethod.POST)
	public ModelAndView removerLivro(@ModelAttribute Livro livro) {
		ModelAndView mv = new ModelAndView("forward:/administradorlivro");
		livroRepositorio.deleteByIsbn(livro.getIsbn());
		return mv;
	}

	/**

	 * @param disciplina 
	 *            
	 * @param livro 
	 *            
	 * @param opcaopesquisa 
	 *            
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/filtrolivro", method = RequestMethod.POST)
	public ModelAndView filtrarLivro(
			@ModelAttribute Disciplina disciplina, @ModelAttribute Livro livro,
			@RequestParam String opcaopesquisa) {
		ModelAndView mv = new ModelAndView("/admin/administradorlivro");
		opcaopesquisa = opcaopesquisa.toLowerCase();
		List<Livro> listaLivros = new FastArrayList();
		if (opcaopesquisa.equals("isbn")) {

			if(livro.getIsbn() != null){
				Livro livroBanco = livroRepositorio.findByIsbn(livro.getIsbn());
				if(livroBanco != null){
					listaLivros.add(livroBanco);
				}
			}

			mv.addObject("listalivros", listaLivros);

		} else if (opcaopesquisa.equals("titulo")) {
			mv.addObject("listalivros", livroRepositorio.findByTitulo(livro.getTitulo()));
		}
		return mv;
	}

	@RequestMapping(value = "/administradorvinculo")
	public ModelAndView gerenciarVinculos(){
		ModelAndView mv = new ModelAndView("/admin/administradorvinculo");
		mv.addObject("listalivros",livroRepositorio.findAll());
		mv.addObject("listadisciplinas",disciplinaRepositorio.findAll());
		return mv;
	}

	@RequestMapping (value = "/salvarvinculo", method= RequestMethod.POST)
	public ModelAndView salvarVinculo(@ModelAttribute Livro livro, @ModelAttribute Disciplina disciplina){
		ModelAndView mv =  new ModelAndView("forward:/administradorvinculo");
		String mensagem;
		Livro livroBanco = livroRepositorio.findByIsbn(livro.getIsbn());

		if(livroBanco.getDisciplinas().contains(disciplina)){
			mensagem = "Ja existe um vinculo com essa disciplina.";
			mv.addObject("erro",mensagem);
			return mv;
		}

		
		if(livroBanco.getDisciplinas().isEmpty()){
			List<Disciplina> listaDisciplinas = new FastArrayList();
			listaDisciplinas.add(disciplina);
			livroBanco.setDisciplinas(listaDisciplinas);
		}else{
			livroBanco.getDisciplinas().add(disciplina);
		}


		if(livroRepositorio.save(livroBanco).getId() != null){
			mensagem = "Operaçao realizada com sucesso.";
			mv.addObject("sucesso",mensagem);
		}else{
			mensagem = "Ocorreu um erro ao tentar vincular livro";
			mv.addObject("erro",mensagem);
		}

		return mv;

	};

	@RequestMapping(value= "/removervinculo", method= RequestMethod.POST)
	public ModelAndView removerVinculo(@ModelAttribute Livro livro, @ModelAttribute Disciplina disciplina){

		ModelAndView mv =  new ModelAndView("forward:/administradorvinculo");
		String mensagem;
		Livro livroBanco = livroRepositorio.findByIsbn(livro.getIsbn());

            boolean removeIf = livroBanco.getDisciplinas()
                    .removeIf(p -> Objects.equals(p.getCodigoDisciplina(), disciplina.getCodigoDisciplina()));

		if (livroRepositorio.save(livroBanco).getId() != null) {
			mensagem = "Operaçao realizada com suceso.";
			mv.addObject("sucesso",mensagem);
		}else{
			mensagem = "Ocorreu um problema ao tentar remover o vinculo.";
			mv.addObject("erro",mensagem);
		}

		return mv;
	};

	@RequestMapping(value= "/buscarvinculos", method= RequestMethod.POST)
	public ModelAndView buscarVinculos(@ModelAttribute Livro livro){
		ModelAndView mv =  new ModelAndView("/admin/administradorvinculo");
		List<Livro> listaLivros = new FastArrayList();
		listaLivros.add(livroRepositorio.findByIsbn(livro.getIsbn()));
		mv.addObject("listalivros",listaLivros);
		mv.addObject("listadisciplinas",disciplinaRepositorio.findAll());
		return mv;
	}

}
