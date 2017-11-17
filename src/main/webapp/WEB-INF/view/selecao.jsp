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
        <div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-right">
            <c:import url="elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
                <div class="col-sm-8">
                    <nav class="breadcrumb">
                        <span class="breadcrumb-item">Você está em:</span> 
                        <a class="breadcrumb-item" href="/Darwin">Início</a>
                        <a class="breadcrumb-item active" href="${selecao.codSelecao}">${selecao.titulo}</a>
                    </nav>
                    <h1>${selecao.titulo}</h1>
                    <p class="text-justify">
                        ${selecao.descricao}
                    </p>
                    <br/>
                    <p><b>Descrição dos Pré-Requisitos:</b> ${selecao.descricaoPreRequisitos}</p>
                    <p><b>Vagas:</b> ${selecao.vagasRemuneradas} remuneradas e ${selecao.vagasVoluntarias} voluntárias.</p>
                    <p><b>Categoria:</b> ${selecao.categoria}</p>
                    <p><b>Área de Concentração:</b> ${selecao.areaDeConcentracao}</p>      
                    <p><b>Edital:</b></p>
                    <br/>
                    
                    <!-- 
                    <nav class="timeline timeline-horizontal" id="navEtapas" role="tablist" > 
                    <c:forEach var="etapa" items="${selecao.etapas}">
                        <a class="nav-item nav-link" id="navtab-etapa-${etapa.codEtapa}" data-toggle="tab" href="#nav-etapa-${etapa.codEtapa}" role="tab" aria-controls="nav-etapa-${etapa.codEtapa}" aria-selected="false">${etapa.titulo}</a>  
                    </c:forEach>
                        
                        <a class="nav-link  btn-outline-dark" href="/Darwin/cadastrarEtapa/${selecao.codSelecao}">Adicionar etapa</a>
                    </nav>
                    
                    <div class="tab-content" id="nav-tabContent">
                    <c:forEach var="etapa" items="${selecao.etapas}">
                        <div class="tab-pane fade card" id="nav-etapa-${etapa.codEtapa}" role="tabpanel" aria-labelledby="nav-etapa-${etapa.codEtapa}">
                            <div class="card-body">
                                <h2 class="card-title">
                                    ${etapa.titulo} - 
                                    <b>${etapa.periodo.dataInicio}</b> 
                                    até 
                                    <b>${etapa.periodo.dataTermino}</b>
                                </h2>
                                <p class="card-text text-justify">
                                    ${etapa.descricao}
                                </p>
                                <br/>
                                <p><b>Avaliadores:</b> -
                                <p><b>Documentação Exigida:</b> ${etapa.documentacaoExigida}</p>
                                <p><b>Critério de Avaliação:</b> ${etapa.criterioDeAvaliacao}</p> 
                            </div>
                        </div>

                    </c:forEach>
                    </div>
                    -->
                    <ul class="timeline timeline-vertical" id="timeline">
                    <c:forEach var="etapa" items="${selecao.etapas}">
                        <li class="timeline-item">
                        <c:if test="${not empty PARTICIPANTE}">
                            <div class="timeline-badge success">
                                <i class="material-icons">check</i>
                            </div>
                        </c:if>
                        <c:if test="${(not empty RESPONSAVEL) or (not empty AVALIADOR) }">
                            <div class="timeline-badge">
                                <i class="material-icons"></i>
                            </div>
                        </c:if>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h2 class="timeline-title">${etapa.titulo}</h2>
                                    <p>
                                        <small class="text-muted">
                                            <b>${etapa.periodo.dataInicio}</b> 
                                            até 
                                            <b>${etapa.periodo.dataTermino}</b>
                                        </small>
                                    </p>
                                </div>
                                <div class="timeline-body">
                                    <p class="text-justify">${etapa.descricao}</p><br>
                                        <c:if test="${not empty RESPONSAVEL}">
                                            <input type="button" class="btn btn-sm btn-primary" value="Editar">
                                        </c:if>
                                        <c:if test="${not empty AVALIADOR}">
                                            <input type="button" class="btn btn-sm btn-primary" value="Avaliar">
                                        </c:if>
                                        <c:if test="${not empty PARTICIPANTE}">
                                            <a href="/Darwin/participarEtapa/${etapa.codEtapa}" class="btn btn-sm btn-primary">Participar</a>
                                        </c:if>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                    <c:if test="${not empty RESPONSAVEL}">
                        <li class="timeline-item">
                            <a href="/Darwin/cadastrarEtapa/${selecao.codSelecao}" class="timeline-badge" style="background-color: #007bff;">
                                <i class="material-icons">add</i>
                            </a>
                        </li>                        
                    </c:if>

                    </ul>
                </div>            
            </div>
        </div>
            <br>
        <c:import url="elements/menu-lateral-direito.jsp" charEncoding="UTF-8"></c:import>
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
