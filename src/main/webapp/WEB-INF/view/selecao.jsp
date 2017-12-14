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
                <c:if test="${(empty selecao.inscricao) and (fn:contains(permissoes, 'RESPONSAVEL') || fn:contains(permissoes, 'ADMINISTRADOR'))}">
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
                <c:if test="${(not empty selecao.etapas) and ((fn:contains(permissoes, 'RESPONSAVEL') || fn:contains(permissoes, 'ADMINISTRADOR'))) and not selecao.divulgada}">
                    <div class="jumbotron jumbotron-fluid" style="padding-top: 40px; padding-bottom: 30px; ">
                        <div class="container">
                            <h1 style="font-size: 20px; font-weight: bold;">Divulgue sua seleção!</h1><br>
                            <p style="font-size: 15px;">Para permitir que os outros usuários tenham acesso a sua seleção, você precisa divulga-lá. Antes disso, verifique se as configurações da sua seleção estão de acordo com o edital. Você deseja divulgar a seleção? &nbsp;
                                <a href="/Darwin/editarSelecao/divulga/${selecao.codSelecao}"> Divulgar a seleção </a>
                            </p>
                        </div>
                    </div>
                </c:if>
                <div class="row" style="padding-left: 12px;">
                    <h1 class="text-uppercase">${selecao.titulo}</h1>
                <c:if test="${fn:contains(selecao.responsaveis, usuarioDarwin)}">
                    <a href="/Darwin/editarSelecao/${selecao.codSelecao}" class="btn btn-primary btn-sm btn-icon" style="position: relative;margin-left: 50px;margin-bottom: 8px;">
                        <i class="material-icons">edit</i>
                        <span>Editar</span>
                    </a>                    
                </c:if>
                </div>
                <div class="card text-center">
                    <div class="tab-content card-body" id="pills-tabContent">
                        <div class="tab-pane fade show active text-justify" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                            ${selecao.descricao}
                            <form method="GET" action="/Darwin/visualizarEdital">
                                <input type="hidden" value="${selecao.codSelecao}" name="selecao">
                                <button type="submit" class="btn btn-primary btn-sm btn-icon" style="position: relative;">
                                    <i class="material-icons">picture_as_pdf</i> 
                                    <span>Visualizar edital</span>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
                <br/>
                    <c:if test="${(not empty selecao.etapas) or (not empty selecao.inscricao)}">
                        <ul class="timeline">
                            <c:if test="${not empty selecao.inscricao}">
                            <c:set var="estadoInscricao" value="${selecao.inscricao.estado.estado}"></c:set>
                            <li class="${i%2 != 0? 'timeline-inverted': ''}">
                            <div class="timeline-badge ${estadoInscricao == 1 ? 'insert_invitation': estadoInscricao == 2 ? 'warning': estadoInscricao == 3  ? 'success': 'danger'}">
                                <i class="material-icons">${estadoInscricao == 1 ? 'insert_invitation': estadoInscricao == 2 ? 'timelapse': estadoInscricao == 3  ? 'done_all': 'warning'}</i>
                            </div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h2 class="timeline-title text-uppercase">${selecao.inscricao.titulo}</h2>
                                    <p>
                                        <small class="text-muted">
                                            <i class="glyphicon glyphicon-time"></i>                                             
                                            <fmt:parseDate value="${selecao.inscricao.periodo.inicio}" pattern="yyyy-MM-dd" var="parseDataInicio" type="date" />
                                            <fmt:parseDate value="${selecao.inscricao.periodo.termino}" pattern="yyyy-MM-dd" var="parseDataTermino" type="date" />
                                            <fmt:formatDate value="${parseDataInicio}"  pattern="dd/MMMM/yyyy" var="dataInicio" type="date"/>
                                            <fmt:formatDate value="${parseDataTermino}"  pattern="dd/MMMM/yyyy" var="dataTermino" type="date"/>
                                            <b>${fn:replace(dataInicio, "/", " de ")}</b> 
                                            até 
                                            <b>${fn:replace(dataTermino, "/", " de ")}</b>
                                        </small>
                                    </p>
                                </div>
                                <div class="timeline-body">
                                    <p>${selecao.inscricao.descricao}</p>
                                    <c:if test="${(estadoInscricao == 2) and (fn:contains(permissoes, 'PARTICIPANTE'))}">
                                        <hr>
                                        <a href="/Darwin/participarEtapa/inscricao/${selecao.inscricao.codEtapa}" class="btn btn-primary btn-sm active" role="button" aria-pressed="true">Participar</a>
                                    </c:if>
                                </div>
                            </div>
                        </li>
                            </c:if>
                    <c:set var="i" value="1"></c:set>
                    <c:forEach var="etapa" begin="0" items="${selecao.etapas}">
                        <c:set var="estado" value="${etapa.estado.estado}"></c:set>
                        <li class="${i%2 != 0? 'timeline-inverted': ''}">
                            <div class="timeline-badge ${estado == 1 ? 'insert_invitation': estado == 2 ? 'warning': estado == 3  ? 'success': 'danger'}">
                                <i class="material-icons">${estado == 1 ? 'insert_invitation': estado == 2 ? 'timelapse': estado == 3  ? 'done_all': 'warning'}</i>
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
                                <div class="timeline-body" >
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
                    <c:if test="${(fn:contains(permissoes, 'PARTICIPANTE'))}">  
                        <li class="">
                            <div class="timeline-badge primary">
                                <i class="material-icons">flag</i>
                            </div>
                        </li>                        
                    </c:if>
                        
                    </ul>
                </c:if>
                <br>
            </div>
            <div class="col-sm-2 sidebar-offcanvas">
                <c:if test="${not empty selecao.aditivos}">
                <h2 style="font-size: 15px; font-weight: bold;margin-top: 5px;" class="text-center">ANEXOS E ADITIVOS</h2>
                <ul class="list-group">
                    <c:forEach var="aditivo" items="${selecao.aditivos}">
                    <li class="list-group-item disabled">
                        <fmt:parseDate value="${aditivo.data}" pattern="yyyy-MM-dd" var="parseData" type="date" />
                        <fmt:formatDate value="${parseData}"  pattern="dd/MM/yyyy" var="dataAditivo" type="date"/>
                        <a href="/Darwin/visualizarAditivo/${aditivo.titulo}">(${dataAditivo}) ${aditivo.titulo}</a>
                    </li>
                    </c:forEach>
                </ul>
                </c:if>
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
