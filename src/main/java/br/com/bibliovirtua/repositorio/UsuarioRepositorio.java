package br.com.bibliovirtua.repositorio;

import br.com.bibliovirtua.modelo.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Samantha Silva em 09/04/2017
 */

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u WHERE u.matricula = ?")
	public Usuario findOneByMatricula(Integer matricula);

	List<Usuario> findByNome(String nome);

	Usuario findByEmail(String email);

	Usuario findByTokenRedefinicaoSenha(String token);
	
}
