package br.com.bibliovirtua.controller;

import br.com.bibliovirtua.modelo.Curso;
import br.com.bibliovirtua.modelo.Disciplina;
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

import java.util.List;
import java.util.Objects;

/**
 * @author Samantha Silva
 */
@Controller
public class CursoController {

    @Autowired
    private CursoRepositorio cursoRepositorio;

    @Autowired
    private DisciplinaRepositorio disciplinaRepositorio;

    @Autowired
    private LivroRepositorio livroRepositorio;

    /**
     * @return ModelAndView
     */
    @RequestMapping(value = "/administradorcurso")
    public ModelAndView gerenciarCurso() {
        ModelAndView mv = new ModelAndView("/admin/administradorcurso");
        mv.addObject("listacursos", cursoRepositorio.findAll());
        return mv;
    }

    /**
     *
     * @param curso 
     * @return ModelAndView  
     */
    @RequestMapping(value = "/adicionacurso", method = RequestMethod.POST)
    public ModelAndView adicionarCurso(@ModelAttribute Curso curso) {
        ModelAndView mv = new ModelAndView("forward:/administradorcurso");
        if (cursoRepositorio.save(curso).getCodigoCurso() != 0) {
            String mensagem = "Registro inserido com sucesso!";
            mv.addObject("sucesso", mensagem);
        } else {
            String mensagem = "Erro ao inserir o registro!";
            mv.addObject("erro", mensagem);
        }
        return mv;
    }

    /**
     Servlet  - update novo curso entre o JSP e o DAO.
     *
     * @param curso - Model curso
     * @return ModelAndView  
     */
    @RequestMapping(value = "/editacurso", method = RequestMethod.POST)
    public ModelAndView editarCurso(@ModelAttribute Curso curso) {
        ModelAndView mv = new ModelAndView("forward:/administradorcurso");
        if (cursoRepositorio.save(curso) != null) {
            String mensagem = "Curso editado com Sucesso!";
            mv.addObject("sucesso", mensagem);
        } else {
            String mensagem = " Erro ao editar curso!";
            mv.addObject("erro", mensagem);
        }
        return mv;
    }

    /**
    Servlet  - delete novo curso entre o JSP e o DAO.
     *
     * @param curso 
     * @return ModelAndView 
     */
    @RequestMapping(value = "/removecurso", method = RequestMethod.POST)
    public ModelAndView removerCurso(@ModelAttribute Curso curso) {
        ModelAndView mv = new ModelAndView("forward:/administradorcurso");

        List<Disciplina> listaDisciplinas = disciplinaRepositorio.findByCurso_CodigoCurso(curso.getCodigoCurso());

        listaDisciplinas.stream().map((disciplina) -> {
            List<Livro> listaLivros = livroRepositorio.findByDisciplinas_CodigoDisciplina(disciplina.getCodigoDisciplina());
            listaLivros.stream().map((livro) -> {
                livro.getDisciplinas()
                        .removeIf(p -> Objects.equals(p.getCodigoDisciplina(), disciplina.getCodigoDisciplina()));
                return livro;
            }).forEachOrdered((livro) -> {
                livroRepositorio.save(livro);
            });
            return disciplina;
        }).forEachOrdered((disciplina) -> {
            disciplinaRepositorio.delete(disciplina);
        });

        cursoRepositorio.delete(curso);

        return mv;
    }

    /**
     *
     * @param curso      
     * @param opcaofiltro 
     * @return ModelAndView 
     */
    @RequestMapping(value = "/filtrocurso", method = RequestMethod.POST)
    public ModelAndView filtrarCurso(@ModelAttribute Curso curso,
                                     @RequestParam String opcaofiltro) {
        ModelAndView mv = new ModelAndView("/admin/administradorcurso");
        List<Curso> lista = new FastArrayList();

        opcaofiltro = opcaofiltro.toLowerCase();
        if (opcaofiltro.equals("codigo")) {

            if(curso.getCodigoCurso() != null){
                lista.add(cursoRepositorio.findOne(curso.getCodigoCurso()));
            }

            mv.addObject("listacursos", lista);

        } else if (opcaofiltro.equals("nome")) {
            mv.addObject("listacursos", cursoRepositorio.findByNomeCurso(curso.getNomeCurso()));
        }
        return mv;
    }

}
