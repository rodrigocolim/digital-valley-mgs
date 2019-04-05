<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="n2s">
        <link rel="icon" href="favicon.ico">
        <title>Darwin - Sistema de Gerenciamento de Seleções</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/design.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
        
    </head>
    <body>
        <c:import url="elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
        <div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-right">
                <c:import url="elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
                <c:set var="titulo" value="${fn:replace(categoria, '_', ' ')}"></c:set>
                <c:set var="titulo" value="${fn:replace(titulo, 'Selecoes', 'Seleções')}"></c:set>
                <div class="col-sm-8">
                <nav class="breadcrumb">
                    <span class="breadcrumb-item">Você está em:</span> 
                    
                    <a class="breadcrumb-item ${titulo eq 'Início' ? 'active': ''}" href="/Darwin/">Início</a>
                    <c:if test="${not (titulo eq 'Início')}"> 
                    <a class="breadcrumb-item text-capitalize active" href="#">${categoria}</a>
                    </c:if>
                </nav>
                <c:set var="mensagem" value="${sessionScope.mensagem}"></c:set>
                <c:if test="${not empty mensagem}">
                    <div class="alert alert-${status} alert-dismissible fade show" role="alert">
                        ${mensagem}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <c:set scope="session" var="mensagem" value=""></c:set>
                    <c:set scope="session" var="status" value=""></c:set>
                </c:if>
                <div class="row col-sm-12">
                    <h1 class="text-capitalize" >${titulo}</h1>
                    <c:if test="${not empty estado}"> 
                    <div class="dropdown right" style="right:-13px; position:absolute;">
                        <button class="btn dropdown-toggle btn-sm btn-icon filtro_tela" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="material-icons">filter_list</i>
                            <span>Filtrar</span>
                        </button>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="/Darwin/">Todas as seleções</a>
                            <a class="dropdown-item" href="/Darwin/estado/aberta">Seleções abertas</a>
                            <a class="dropdown-item" href="/Darwin/estado/andamento">Seleções em andamento</a>
                            <a class="dropdown-item" href="/Darwin/estado/finalizada">Seleções finalizadas</a>
                        </div>
                    </div>
                    </c:if>
                </div>
                <form method="POST" action="/Darwin/">
                <div class="center">
				  <div class="input-group mb-3" style="padding-top: 5px;">
				  
					  <input type="text" style="text-align: center;" class="form-control" placeholder="Pesquisar seleções por nome" name="campoBuscaSelecao" aria-describedby="basic-addon2">
					  <div class="input-group-append">
					    <button type="submit" class="btn btn-outline-primary" ><i class="fas fa-search"></i> Pesquisar</button>
					  </div>
				
				</div>
				</div>
				  </form>
                
                <c:if test="${empty selecoes}">
                    <p class="text-muted">Nenhuma seleção cadastrada!</p>
                </c:if>
                <c:set var="pagina" value="${(((not empty param.pag) and (param.pag >= 1)) ? param.pag : 1)}"></c:set>
                <>
                <c:forEach var="selecao" begin="${((pagina - 1) * 5)}" end="${((pagina - 1) * 5) + 4}" items="${selecoes}">
                    <div class="card">
                        <div class="card-body">
                            <div class="row" style="padding-left: 13px;">
                                <h2 class="card-title text-uppercase font-weight-bold">
                                ${selecao.titulo}
                                </h2>
                                <c:if test="${selecao.estado.estado == 0}">
                                    <span class="badge badge-pill badge-danger" style="right: 20px; font-size: 10px;position: absolute;">Em edição</span>
                                </c:if>       
                                <c:if test="${selecao.estado.estado == 1}">
                                    <span class="badge badge-pill badge-warning" style="right: 20px; font-size: 10px;position: absolute;">Em espera</span>
                                </c:if>
                                <c:if test="${selecao.estado.estado == 2}">
                                    <span class="badge badge-pill badge-primary" style="right: 20px; font-size: 10px;position: absolute;">Aberta</span>
                                </c:if>
                                <c:if test="${selecao.estado.estado == 3}">
                                    <span class="badge badge-pill badge-info" style="right: 20px; font-size: 10px;position: absolute;">Em andamento</span>
                                </c:if>
                                <c:if test="${selecao.estado.estado == 4}">
                                    <span class="badge badge-pill badge-dark" style="right: 20px; font-size: 10px;position: absolute;">Finalizada</span>
                                </c:if>
                                                                
                                
                            </div>
                            <h3 class="card-subtitle mb-2 text-muted">
                            <c:set var = "codSelecao" value = "${selecao.codSelecao}"/>
                                <c:out value="${etapasAtuais[codSelecao+0].titulo}" /> - 
                                <b>${etapasAtuais[codSelecao].periodo.dataInicio}</b>
                                até 
                                <b>${etapasAtuais[codSelecao].periodo.dataTermino}</b>
                            </h3>
                            <p class="card-text text-justify">
                                ${fn:substring(selecao.descricao, 0, 400)}
                                <c:if test="${fn:length(selecao.descricao) > 400}">
                                    [...]
                                </c:if>
                            </p>
                            <c:set var = "nomeUrl" value = "${selecao.titulo}"/>
                            <a href="/Darwin/selecao/${selecao.codSelecao}" class="card-link">Mais informações</a>
                        </div>
                    </div>
                </c:forEach>
                <br/>
                <nav aria-label="">
                <c:if test="${titulo eq 'Início'}"><c:set value="" var="categoria"></c:set></c:if>
                <c:if test="${titulo eq 'Minhas seleções'}"><c:set value="minhas_Selecoes" var="categoria"></c:set></c:if>
                
                    <ul class="pagination justify-content-center">
                        <li class="page-item ${pagina <= 1 ? "disabled" : ""}">
                            <a class="page-link" href="/Darwin/${categoria}?pag=${pagina - 1}" tabindex="-1">Anterior</a>
                        </li>
                    <c:forEach var="i" begin="1" end="${(fn:length(selecoes)/5) + (fn:length(selecoes)%5 == 0 ? 0 : 1)}">
                        <li class="page-item ${pagina == i ? "active": ""}"><a class="page-link" href="/Darwin/${categoria}?pag=${i}">${i}</a></li>
                    </c:forEach>
                        <li class="page-item ${pagina >= fn:length(selecoes)/5 ? "disabled" : ""}">
                            <a class="page-link" href="/Darwin/${categoria}?pag=${pagina + 1}">Próximo</a>
                        </li>
                    </ul>
                </nav>
                
                </div>
            </div>
        </div>
        <c:import url="elements/rodape.jsp" charEncoding="UTF-8"></c:import>  
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>      
    </body>
</html>
