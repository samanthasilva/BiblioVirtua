package br.com.bibliovirtua.repositorio;

import br.com.bibliovirtua.modelo.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Samantha Silva em 09/04/2017
 */
@Repository
public interface CursoRepositorio extends CrudRepository<Curso, Integer>  {

    List<Curso> findByNomeCurso(String nome);

}
