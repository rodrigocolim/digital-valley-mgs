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
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/design.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/timeline.css" />
    </head>
    <body>
        <c:import url="elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
        <c:set var="permissoes" value="${sessionScope.usuarioDarwin.permissoes}"></c:set>
        <div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-right">
            <c:import url="elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
                <div class="col-sm-8">
                    <nav class="breadcrumb">
                        <span class="breadcrumb-item">Você está em:</span> 
                        <a class="breadcrumb-item" href="/Darwin">Início</a>
                        <a class="breadcrumb-item active" href="${selecao.codSelecao}">${selecao.titulo}</a>
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
                <!-- Mensagem de primeiro acesso após o cadastro da seleção -->
                <c:if test="${(empty selecao.etapas) and (fn:contains(permissoes, 'RESPONSAVEL') || fn:contains(permissoes, 'ADMINISTRADOR'))}">
                    <div class="jumbotron jumbotron-fluid" style="padding-top: 40px; padding-bottom: 30px; ">
                        <div class="container">
                            <h1 style="font-size: 20px; font-weight: bold;">Cadastre a primeira etapa da sua seleção!</h1><br>
                            <p style="font-size: 15px;">Para iniciar sua seleção é necessário o cadastro da etapa de inscrição. Você deseja cadastrar a etapa de inscrição? &nbsp;
                                <a href="/Darwin/cadastrarEtapa/${selecao.codSelecao}"> Cadastrar etapa de inscrição </a>
                            </p>
                        </div>
                    </div>
                </c:if>
                <!-- Mensagem de primeiro acesso após o cadastro da seleção -->
                <c:if test="${(not empty selecao.etapas) and ((fn:contains(permissoes, 'RESPONSAVEL') || fn:contains(permissoes, 'ADMINISTRADOR')))}">
                    <div class="jumbotron jumbotron-fluid" style="padding-top: 40px; padding-bottom: 30px; ">
                        <div class="container">
                            <h1 style="font-size: 20px; font-weight: bold;">Divulgue sua seleção!</h1><br>
                            <p style="font-size: 15px;">Para permitir que os outros usuários tenham acesso a sua seleção, você precisa divulga-lá. Antes disso, verifique se as configurações da sua seleção estão de acordo com o edital. Você deseja divulgar a seleção? &nbsp;
                                <a href="#"> Divulgar a seleção </a>
                            </p>
                        </div>
                    </div>
                </c:if>
                    <h1 class="text-uppercase">${selecao.titulo}</h1>
                    <p>
                        ${selecao.descricao}
                    </p>
                    <br/>
                    <c:if test="${not empty selecao.etapas}">
                        <ul class="timeline">
                    <c:set var="i" value="0"></c:set>
                    <c:forEach var="etapa" begin="0" items="${selecao.etapas}">
                        <c:set var="estado" value="${etapa.estado.estado}"></c:set>
                        <li class="${i%2 != 0? 'timeline-inverted': ''}">
                            <div class="timeline-badge ${estado == 1 ? 'insert_invitation': estado == 2 ? 'warning': estado == 3  ? 'success': ''}">
                                <i class="material-icons">${estado == 1 ? 'insert_invitation': estado == 2 ? 'timelapse': estado == 3  ? 'done_all': ''}</i>
                            </div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h2 class="timeline-title text-uppercase">${etapa.titulo}</h2>
                                    <p>
                                        <small class="text-muted">
                                            <i class="glyphicon glyphicon-time"></i>                                             
                                            <fmt:parseDate value="${etapa.periodo.inicio}" pattern="yyyy-MM-dd" var="parseDataInicio" type="date" />
                                            <fmt:parseDate value="${etapa.periodo.termino}" pattern="yyyy-MM-dd" var="parseDataTermino" type="date" />
                                            <fmt:formatDate value="${parseDataInicio}"  pattern="dd/MMMM/yyyy" var="dataInicio" type="date"/>
                                            <fmt:formatDate value="${parseDataTermino}"  pattern="dd/MMMM/yyyy" var="dataTermino" type="date"/>
                                            <b>${fn:replace(dataInicio, "/", " de ")}</b> 
                                            até 
                                            <b>${fn:replace(dataTermino, "/", " de ")}</b>
                                        </small>
                                    </p>
                                </div>
                                <div class="timeline-body">
                                    <p>${etapa.descricao}</p>
                                    <c:if test="${(not empty etapa.documentacaoExigida) and (estado == 2) and (fn:contains(permissoes, 'PARTICIPANTE'))}">
                                        <hr>
                                        <a href="/Darwin/participarEtapa/${etapa.codEtapa}" class="btn btn-primary btn-sm active" role="button" aria-pressed="true">Enviar documentação</a>
                                    </c:if>
                                </div>
                            </div>
                        </li>
                    <c:set var="i" value="${i + 1}"></c:set>
                    </c:forEach>

                    <c:if test="${fn:contains(permissoes, 'RESPONSAVEL') and (selecao.estado == 'ESPERA')}">  
                        <li>
                            <a href="/Darwin/cadastrarEtapa/${selecao.codSelecao}" class="timeline-badge primary" >
                                <i class="material-icons">add</i>
                            </a>
                        </li>                        
                    </c:if>
                    <c:if test="${fn:contains(permissoes, 'PARTICIPANTE')}">  
                        <li class="">
                            <div class="timeline-badge primary">
                                <i class="material-icons">flag</i>
                            </div>
                        </li>                        
                    </c:if>
                        
                    </ul>
                </c:if>
            </div>
            <div class="col-sm-2 sidebar-offcanvas">
                <h2><center>ADITIVOS E ANEXOS</center></h2>
                <div class="card" style="width: 20rem;">
                    <div class="card-header">
                        Featured
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item">Vestibulum at eros</li>
                    </ul>
                </div>
            </div>
        </div>

        <c:import url="elements/rodape.jsp" charEncoding="UTF-8"></c:import>  
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>      
        <script>
            $("#navEtapas").addClass(function( index, currentClass ) {
                var addedClass;

                if (screen.width <= 575) {
                  addedClass = "flex-column";
                }

                return addedClass;
            });
            $("#timeline").removeClass(function( index, currentClass ) {
                var addedClass;

                if (screen.width <= 575) {
                  addedClass = "timeline";
                }

                return addedClass;
            });
        </script>
    </body>
</html>
