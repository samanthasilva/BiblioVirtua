package br.com.bibliovirtua.controller;

import br.com.bibliovirtua.modelo.Livro;
import br.com.bibliovirtua.repositorio.CursoRepositorio;
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

import br.com.bibliovirtua.modelo.Curso;
import br.com.bibliovirtua.modelo.Disciplina;

import java.util.List;



@Controller
public class DisciplinaController {

	@Autowired
	private LivroRepositorio livroRepositorio;

	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;

	@Autowired
	private CursoRepositorio cursoRepositorio;


	/**
	 * 
	 * @return ModelAndView 
	 */
	@RequestMapping(value = "/administradordisciplina")
	public ModelAndView gerenciarDisciplina() {
		ModelAndView mv = new ModelAndView("/admin/administradordisciplina");
		mv.addObject("listadisciplinas", disciplinaRepositorio.findAll());
		mv.addObject("listacursos", cursoRepositorio.findAll());
		return mv;
	}

	/**
	 * Metodo Servlet - Insert nova disciplina.
	 * 
	 * @param curso 
	 *   
	 * @param disciplina  
	 *            
	 * @return ModelAndView  
	 */
	@RequestMapping(value = "/adicionadisciplina", method = RequestMethod.POST)
	public ModelAndView adicionarDisciplina(
			@ModelAttribute Disciplina disciplina, @ModelAttribute Curso curso) {
		ModelAndView mv = new ModelAndView("forward:/administradordisciplina");

		disciplina.setCurso(curso);

		if (disciplinaRepositorio.save(disciplina) != null) {
			String mensagem = " Disciplina adicionada com Sucesso!";
			mv.addObject("sucesso", mensagem);
		} else {
			String mensagem = " Erro ao cadastrar disciplina!";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}

	/**
	 * Metodo Servlet - Update disciplina.
	 * 
	 * @param disciplina - Model disciplina
         * @param curso
	 * 
	 * @return ModelAndView  
	 */
	@RequestMapping(value = "/editadisciplina", method = RequestMethod.POST)
	public ModelAndView editarDisciplina(@ModelAttribute Disciplina disciplina,
			@ModelAttribute Curso curso) {
		ModelAndView mv = new ModelAndView("forward:/administradordisciplina");

		disciplina.setCurso(curso);

		if (disciplinaRepositorio.save(disciplina) != null) {
			String mensagem = " Disciplina editada com Sucesso!";
			mv.addObject("sucesso", mensagem);
		} else {
			String mensagem = "Ocorreu um erro ao editar  disciplina!";
			mv.addObject("erro", mensagem);
		}
		return mv;
	}

	/**
	 * Metodo Servlet  - Delete disciplina.
	 * 
	 * @param disciplina 
	 *            
	 * @return ModelAndView 
	 */
	@RequestMapping(value = "/removedisciplina", method = RequestMethod.POST)
	public ModelAndView removerDisciplina(@ModelAttribute Disciplina disciplina) {
		ModelAndView mv = new ModelAndView("forward:/administradordisciplina");

		List<Livro> listaLivros = livroRepositorio.findByDisciplinas_CodigoDisciplina(disciplina.getCodigoDisciplina());

		for(Livro livro : listaLivros){

			livro.getDisciplinas()
					.removeIf(p -> p.getCodigoDisciplina() == disciplina.getCodigoDisciplina());
			livroRepositorio.save(livro);
		}

		disciplinaRepositorio.delete(disciplina);
		return mv;
	}

	/**
	 * @param disciplina 
	 *            
	 * @param opcaopesquisa 
	 *            
	 * @return ModelAndView 
	 */
	@RequestMapping(value = "/pesquisadisciplina", method = RequestMethod.POST)
	public ModelAndView pesquisarDisciplina(
			@ModelAttribute Disciplina disciplina,
			@RequestParam String opcaopesquisa) {
		ModelAndView mv = new ModelAndView("/admin/administradordisciplina");
		List<Disciplina> listaDisciplina = new FastArrayList();

		opcaopesquisa = opcaopesquisa.toLowerCase();
		if (opcaopesquisa.equals("codigo")) {

			if(disciplina.getCodigoDisciplina() != null) {
				Disciplina disciplinaBanco = disciplinaRepositorio.findOne(disciplina.getCodigoDisciplina());

				if(disciplinaBanco != null){
					listaDisciplina.add(disciplinaBanco);
				}
			}

			mv.addObject("listadisciplinas",listaDisciplina);
		} else if (opcaopesquisa.equals("nome")) {
			mv.addObject("listadisciplinas",
					disciplinaRepositorio.findByNomeDisciplina(	disciplina.getNomeDisciplina()));
		}
		mv.addObject("listacursos", cursoRepositorio.findAll());
		return mv;
	}

}
