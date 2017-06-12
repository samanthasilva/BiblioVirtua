package br.com.bibliovirtua.repositorio;

import br.com.bibliovirtua.modelo.Disciplina;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Samantha Silva em 09/04/2017
 */
@Repository
public interface DisciplinaRepositorio extends CrudRepository<Disciplina, Integer>  {

    List<Disciplina> findByNomeDisciplina(String nome);

    List<Disciplina> findByCurso_CodigoCurso(Integer codigoCurso);

}
