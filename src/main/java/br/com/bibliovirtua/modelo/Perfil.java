package br.com.bibliovirtua.modelo;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Samantha Silva em 09/04/2017
 */
public enum Perfil implements GrantedAuthority {

    ALUNO, PROFESSOR, ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }


}
