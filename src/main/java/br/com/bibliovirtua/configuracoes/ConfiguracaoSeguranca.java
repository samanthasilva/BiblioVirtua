package br.com.bibliovirtua.configuracoes;

import br.com.bibliovirtua.servicos.ServicoAutenticacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {

	@Autowired
	private ServicoAutenticacao servicoAutenticacao;

    @Autowired
    private HandlerAutenticacao handlerAutenticacao;

	@Autowired
	private ControleFalhaAutenticacao controleFalhaAutenticacao;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(servicoAutenticacao)
			.passwordEncoder(encoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/buscar",
							 "/admin/**").hasAnyRole("ADMIN","ALUNO", "PROFESSOR")

				.antMatchers(
							 "/administradorcurso",
							 "/administradordisciplina",
							 "/administradorlivro",
							 "/administradorusuario",
							 "/administradorvinculo",
						     "/relatorio/cursos",
						     "/relatorio/disciplinas",
							 "/relatorio/usuarios").hasAnyRole("ADMIN")

				.antMatchers("/relatorio/livros",
							 "/professor/**").hasAnyRole("ADMIN", "PROFESSOR")

				.antMatchers("/aluno/**").hasAnyRole("ADMIN", "ALUNO")

				.anyRequest().permitAll()
		.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/j_spring_security_check")
				.defaultSuccessUrl("/admin")
				.failureHandler(controleFalhaAutenticacao)
                .successHandler(handlerAutenticacao)
				.usernameParameter("j_username")
				.passwordParameter("j_password")
				
			.and()
				.logout()
					.logoutUrl("/j_spring_security_logout")
			.and()
				.exceptionHandling().accessDeniedPage("/403");


		http.csrf().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
	}
	
	@Bean
	public BCryptPasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));
	}
	
}
