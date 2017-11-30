<%-- 
    Document   : menu-superior
    Created on : 06/10/2017, 10:29:03
    Author     : Alex Felipe
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page import="br.ufc.russas.n2s.darwin.model.EnumPermissoes"%>
<%@page import="br.ufc.russas.n2s.darwin.model.EnumPermissoes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.ufc.russas.n2s.darwin.beans.UsuarioBeans"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">
        <img src="https://png.icons8.com/natural-food/color/160" width="30" height="30" class="d-inline-block align-top" alt="">
        Darwin
    </a>  
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <c:set var="permissoes" value="${sessionScope.usuarioDarwin.permissoes}"></c:set>
            <a class="nav-item nav-link" href="/Darwin">Início <span class="sr-only">(current)</span></a>
            <a class="nav-item nav-link" href="/Darwin/minhasSelecoes">Minhas seleções</a>
            <c:if test="${fn:contains(permissoes, 'RESPONSAVEL')}">
            <div class="btn-group">
                <a href="#" type="button" class="nav-item nav-link " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Opções
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="/Darwin/cadastrarSelecao">Cadastrar Seleção</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Acessar permissões</a>
                </div>
            </div>
            </c:if>
            <a class="nav-item nav-link" href="/Darwin/sair">Sair</a>
        </div>
    </div>
</nav>