package br.com.bibliovirtua.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.com.bibliovirtua.repositorio.CursoRepositorio;
import br.com.bibliovirtua.repositorio.DisciplinaRepositorio;
import br.com.bibliovirtua.repositorio.LivroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.bibliovirtua.modelo.Curso;
import br.com.bibliovirtua.modelo.Disciplina;



@Controller
public class SugeridoController {

	@Autowired
	private CursoRepositorio cursoRepositorio;

	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;

	@Autowired
	private LivroRepositorio livroRepositorio;

	
	/**
	 * @return ModelAndView 
	 */
        
	@RequestMapping(value= "/buscar")
	public ModelAndView buscarSugerido(){
		ModelAndView mv = new ModelAndView("/admin/pesquisa");
		mv.addObject("listacursos",cursoRepositorio.findAll());
		return mv;		
	}
	
	/**  @param disciplina 
	 * @return ModelAndView -
	 */
	@RequestMapping(value= "/resultado", method= RequestMethod.POST)
	public ModelAndView gerarResultado(@ModelAttribute Disciplina disciplina){
		ModelAndView mv = new ModelAndView("/admin/pesquisaresultado");
		mv.addObject("listalivros", livroRepositorio.findByDisciplinas_CodigoDisciplina(disciplina.getCodigoDisciplina()));
		mv.addObject("disciplina", disciplinaRepositorio.findOne(disciplina.getCodigoDisciplina()));
		return mv;
	}

	/** 
	 * @param curso 
	 * @param response 
	 * @throws IOException
	 */
	@RequestMapping(value= "/buscardisciplinas", method= RequestMethod.POST)
	public void buscarDisciplinasDoCurso(@ModelAttribute Curso curso, HttpServletResponse response ) throws IOException{
		PrintWriter out = response.getWriter();

		List<Disciplina> listaDisciplinas = disciplinaRepositorio.findByCurso_CodigoCurso(curso.getCodigoCurso());

		StringBuilder sb = new StringBuilder("");
		
		for(int i = 0; i < listaDisciplinas.size(); i++){
                    StringBuilder append = sb.append(listaDisciplinas.get(i).getCodigoDisciplina()).append("-").append(listaDisciplinas.get(i).getNomeDisciplina()).append(":");
		}
		out.write(sb.toString());
	}

	
}
