<!-- Import java-->
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- Import JSTL-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Spring Famework -->
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    


<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>BiblioVirtua - Administrador Disciplina</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" />
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/estilo.css" />" />
    
<!-- Fontes do Google -->
<link href='http://fonts.googleapis.com/css?family=Anton' rel='stylesheet' type='text/css'>
    
<!-- Javascript do bootstrap -->
<script src="resources/js/jquery-1.11.2.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<!-- Estilos do Alertfy -->
<link rel="stylesheet" href="<c:url value="/resources/jquery/alertifyjs/alertify.min.css"/>" />
<link rel="stylesheet" href="<c:url value="/resources/jquery/alertifyjs/css/themes/default.min.css"/>" /> 
<script src="<c:url value="/resources/jquery/alertifyjs/alertify.min.js" />"></script>

<!-- Importação do arquivo Ajax -->
<script type="text/javascript" charset="utf-8"  src="<c:url value="/resources/jquery/ajax/ajax_administradordisciplina.js" />"></script> 

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery/datatables/media/css/jquery.dataTables.css"/>">
  
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="<c:url value="/resources/jquery/datatables/media/js/jquery.dataTables.js"/>"></script>
<script>
    $(document).ready(function() {
       $.extend( $.fn.dataTable.defaults, {
            searching: false,
            ordering:  true,
            info: false
       } );
    $('#tabeladisciplina').dataTable( {
        "paging":         true,  

        "language": {
            "emptyTable":     "Não foram encontrados registros para esta busca",
            "lengthMenu": "Exibir _MENU_ registros",
            "zeroRecords": "Não foram encontrados registros para esse filtro.",
            "info": "Mostrando pagina _PAGE_ de _PAGES_<br><br>",
            "infoEmpty": "Mostrando pagina 0 de 0 registros encontrados.",
            "infoFiltered": "(filtrado de _MAX_ gravações)<br><br>",
            "search":         "Consultar"
        };

    } );    
} );    
    
</script>

<!-- Jquery Validator Form --> 
<script src="<c:url value="/resources/jquery/validator/jquery.validate.js" />"></script>
<script src="<c:url value="/resources/jquery/validator/views/administradordisciplina.js" />"></script>
    
</head>
    
<body class="background-sistema">
    <div class="container-fluid">
        
        <!-- MENSAGENS ALERTIFY-->
        <c:if test="${not empty param.error}">
            <script>
                alertify.error("${param.error}");
            </script>
        </c:if>
        
        <c:if test="${not empty erro}">
            <script>
                alertify.error("${erro}");
            </script>
        </c:if>
        
        <c:if test="${not empty sucesso}">
            <script>
                alertify.success("${sucesso}");
            </script>
        </c:if>
             
        <!-- CABEÇALHO -->
        <header class="row">
            <jsp:include page="/resources/template/admin/menu.jsp">
                <jsp:param name="active" value="administradordisciplina" />
            </jsp:include>
        </header>
        
        <!-- CONTEÚDO -->
        <div class="row">
            <div role="main">
                <div class="col-md-12 col-md-offset-0">
		      
                    <!-- BREADCRUMB -->
                    <ul class="breadcrumb">
                       <li><a href="admin">Home</a></li>
                      <li class="active">Administrador Disciplina</li>
                    </ul>
                    
                    <form action="pesquisadisciplina" method="POST">
                        <label>
                            Consultar por: &nbsp; 
                        </label>
                        
                        <label class="radio-inline">
                            <input type="radio" name="opcaopesquisa" id="op_pesq_cod" value="codigo" checked>Código
                        </label>
                        
                        <label class="radio-inline">
                            <input type="radio" name="opcaopesquisa" id="op_pesq_nome" value="nome">Nome
                        </label>
                             
                        
                        <div class="input-group">
                          <input type="number" class="form-control" name="codigoDisciplina" id="search_cod_disciplina" placeholder="Código da disciplina">
                          <input type="text" class="form-control" style="display:none" name="nomeDisciplina" id="search_nome_disciplina" placeholder="Nome da disciplina">
                          <div class="input-group-btn">
                              <button type="submit" class="btn btn-info"><i class="glyphicon glyphicon-search"></i></button>
                            </div>
                        </div>
                    </form>
                    
                    <br>
                    
                    <table id="tabeladisciplina" class="table table-responsive table-bordered table-hover table-striped">
                        <thead>
                            <tr class="table-info">
                                <th>Código</th>
                                <th>Nome</th>
                                <th>Curso</th>
                                <th>Editar</th>
                                <th>Excluir</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="disciplina" items="${listadisciplinas}" >
                            <tr class="active">
                                <td class="text-center" name="tb_cod_disciplina">${disciplina.codigoDisciplina}</td>
                                
                                <td name="tb_nome_disciplina">${disciplina.nomeDisciplina}</td>
                                      
                                <td name="tb_curso_disciplina" id="${disciplina.curso.codigoCurso}">${disciplina.curso.nomeCurso}</td>
                                
                                <td>
                                    <button type="submit" class="btn btn-primary btn-sm btn-editar"data-toggle="modal" data-target="#modalEditaDisciplina" name="editardisciplina">
                                        <span class="glyphicon glyphicon-ok"></span>
                                      
                                    </button>
                                </td> 
                                
                                <td>
                                    <button type="submit" class="btn btn-danger btn-sm btn-remover" name="excluirdisciplina">
                                        <span class="glyphicon glyphicon-remove"></span>
                                     
                                    </button>
                                </td>
                                  
                            </tr>
                           </c:forEach> 
                        </tbody>
                    </table>
                
                    <div class="col-md-offset-9">
                        <br>
                        <button type="submit" class="btn" data-toggle="modal" data-target="#modalAdicionaDisciplina"/>
                        <span class="glyphicon glyphicon-plus"></span> &nbsp;
                        <strong>Adicionar Disciplina</strong>
                        </button>
                    </div>
                    
                      <br>
                <br>  
                
            </div>
        </div>  
    </div>
        <!-- RODAPÉ  
        <footer class="row">
            <c:import url="/resources/template/admin/rodape.jsp"></c:import> 
        </footer>-->
    
        <!-- MODALS -->
        <c:import url="/resources/template/admin/modal/disciplina/adicionadisciplina.jsp"></c:import>
        <c:import url="/resources/template/admin/modal/disciplina/editadisciplina.jsp"></c:import> 
        
    <!--FIM DIV CONTAINER-->
    </div>
</body>
</html>