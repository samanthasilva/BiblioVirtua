package br.com.bibliovirtua.controller;


import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

@Controller
@Transactional
public class RelatorioController {
	
	private final String CAMINHO_RELATORIO_CURSO = "/reports/RelatorioCursos.jrxml";
	private final String CAMINHO_RELATORIO_DISCIPLINA = "/reports/RelatorioDisciplinas.jrxml";
	private final String CAMINHO_RELATORIO_LIVRO = "/reports/RelatorioLivros.jrxml";
	private final String CAMINHO_RELATORIO_USUARIO = "/reports/RelatorioUsuarios.jrxml";

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private DataSource dataSource;


	/** Servlet responsavel por gerar relatorios
         * @param tipo
	 * @param response
	 * @throws JRException
	 * @throws IOException
    '    * @throws java.sql.SQLException
         * @throws java.sql.SQLException
	 */
        
	@RequestMapping(value = "/relatorio/{tipo}")
	public void gerarRelatorios(@PathVariable String tipo, HttpServletResponse response) throws JRException, IOException, SQLException {

		String PATH = "";

		switch (tipo.toUpperCase()) {
			case "CURSOS":
				PATH = CAMINHO_RELATORIO_CURSO;
				break;
			case "DISCIPLINAS":
				PATH = CAMINHO_RELATORIO_DISCIPLINA;
				break;
			case "LIVROS":
				PATH = CAMINHO_RELATORIO_LIVRO;
				break;
			case "USUARIOS":
				PATH = CAMINHO_RELATORIO_USUARIO;
				break;
		}

		InputStream inputStreamURLCurso = this.getClass().getResourceAsStream(PATH);

		response.setContentType("application/pdf");

                JasperReport jasperReport = JasperCompileManager.compileReport(inputStreamURLCurso);

		JasperPrint print = JasperFillManager.fillReport(jasperReport,new HashMap<>(), dataSource.getConnection());

		JasperExportManager.exportReportToPdfStream(print,response.getOutputStream());

	}

	
}