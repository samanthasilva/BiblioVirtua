package br.com.bibliovirtua.repositorio;

import br.com.bibliovirtua.modelo.Curso;
import br.com.bibliovirtua.modelo.Disciplina;
import br.com.bibliovirtua.modelo.Livro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Samantha Silva em 09/04/2017
 */
@Repository
public interface LivroRepositorio extends CrudRepository<Livro, Integer>  {

    Livro findByIsbn(String isbn);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByDisciplinas_CodigoDisciplina(Integer codigoDisciplina);

    @Transactional
    Long deleteByIsbn(String isbn);

}
