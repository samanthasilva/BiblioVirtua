<!-- Import java-->
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- Import JSTL-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Spring Famework -->
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
    
<link href='http://fonts.googleapis.com/css?family=Roboto:100,300,400|Open+Sans' rel='stylesheet' type='text/css'>   
    
<nav class="navbar navbar-default">
  <div class="container-fluid">

    <div class="navbar-header">
      <a class="navbar-brand logo" href="#">       </a>
    </div>


    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        
              <ul class="nav navbar-right">
          <li class="dropdown"> 
            <a class="glyphicon glyphicon-education" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
			<div class="form-inline" >
		<div class="form-group form-group-sm">
			    </div>
			  Bem vindo (a) ${nome_usuario_sessao} 
                 <span class="caret"></span>
           </div>            
                          
                <!-- <%= SecurityContextHolder.getContext().getAuthentication().getName() %>-->
           
          </a>
          <ul class="dropdown-menu" role="menu">
            <li><a class="glyphicon glyphicon-wrench" data-toggle="modal" data-target="#modalNovaSenhaMenu" name="novasenha"> 
                Alterar Senha
                </a>
              </li>
    
            <li class="divider"></li>
            <li><a href="<c:url value="/j_spring_security_logout"/>">
                <span class="glyphicon glyphicon-off"></span> Sair
                </a>
              </li>
          </ul>
        </li>
      </ul>
        
        
      <ul class="nav nav-tabs nav-justified">
        <li <c:if test="${param.active == 'home'}"> class="active"</c:if>>
        <a class="glyphicon glyphicon-home"  href="admin">  BiblioVirtua </a>
        </li>
      
        <li <c:if test="${param.active == 'pesquisa'}"> class="active"</c:if>>
                <a class= "glyphicon glyphicon-search" href="buscar">    Consultar  </a></li>
    <sec:authorize access="hasRole('ROLE_ADMIN')">      
        
        <li 
            <c:if test="${param.active == 'administradorcurso' || param.active == 'administradordisciplina' || param.active == 'administradorlivro' ||param.active == 'administradorvinculo' || param.active == 'administradorusuario'}"> class="active"</c:if>
        class="dropdown">
        
          <a class="glyphicon glyphicon-cog" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                  Administração <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li <c:if test="${param.active == 'administradorcurso'}"> class="active"</c:if>>
                <a   class="glyphicon glyphicon-folder-close"    href="administradorcurso">    Cursos   </a>
           </li>
            <li <c:if test="${param.active == 'administradordisciplina'}"> class="active"</c:if>>
                <a class="glyphicon glyphicon-duplicate" href="administradordisciplina">      Disciplinas     </a></li>
            <li 
                <c:choose>
                    <c:when test="${param.active=='administradorvinculo' || param.active=='administradorlivro'}"> class="dropdown-submenu active"    
                </c:when>

                  <c:otherwise>
                      class="dropdown-submenu"
                  </c:otherwise>
                </c:choose>
            >
                <a class="glyphicon glyphicon-book"   tabindex="-1" href="administradorlivro">    Livros   </a>
                <ul class="dropdown-menu">
                <li <c:if test="${param.active == 'administradorvinculo'}"> class="active"</c:if>>
                      <a class="glyphicon glyphicon-transfer" href="administradorvinculo">   Vinculo Livro\Disciplina  </a></li>
                </ul>
              </li>
              
            <li <c:if test="${param.active == 'administradorusuario'}"> class="active"</c:if>>
                <a class="glyphicon glyphicon-user" href="administradorusuario">   Usuários   </a></li>
          </ul>
        </li>
      </sec:authorize>    
 
     <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')">  
        <li class="dropdown">
          <a class="glyphicon glyphicon-list-alt" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                    Relatórios<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
          	<sec:authorize access="hasRole('ROLE_ADMIN')"> 
            <li>
                <a class="glyphicon glyphicon-folder-close"   href="relatorio/cursos" target="_blank">   Cursos   </a>
            </li>
            <li><a class="glyphicon glyphicon-duplicate" href="relatorio/disciplinas" target="_blank">   Disciplinas   </a></li>
             </sec:authorize> 
            <li><a class="glyphicon glyphicon-book"  href="relatorio/livros" target="_blank">   Livros   </a></li>
 			<sec:authorize access="hasRole('ROLE_ADMIN')"> 
            <li><a  class="glyphicon glyphicon-user" href="relatorio/usuarios" target="_blank">   Usuários</a></li>
            </sec:authorize>  
          </ul>
        </li>
      </sec:authorize>   
      </ul>

    </div>
  </div>
</nav>
<c:import url="/resources/template/admin/modal/usuario/alterasenha_menu.jsp"></c:import>   