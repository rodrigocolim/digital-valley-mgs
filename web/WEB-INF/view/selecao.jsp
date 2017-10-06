<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="">
        <meta name="author" content="n2s">
        <link rel="icon" href="favicon.ico">
        <title>Darwin - Sistema de Gerenciamento de Seleções</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/recursos/css/timeline.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/recursos/css/design.css" />
    </head>

    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="#">Darwin</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">           
                      <li><a href="logout.jsp" title="sair do sistema"><span class="glyphicon glyphicon-log-out"></span> Sair</a></li>
                    </ul>
                </div>
            </div>
        </nav>
      
        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">
                </div>
                <!-- Menu lateral esquerdo -->

                <!-- Menu central -->
                <div class="col-sm-8 text-left">
                    <c:if test="${not empty selecao}">
                        <h2>
                            <c:out value="${selecao.titulo}"></c:out><br>
                        </h2>
                        <p>
                            <c:out value="${selecao.descricao}"></c:out>
                        </p>
                        
                    <div class="col-sm-8 text-left">
                        <div class="container">
                            <ul class="nav nav-tabs menu-selecoes">
                                <li class="active"><a href="#cronograma" data-toggle="tab" title="cronograma">Cronograma</a></li>
                                <li><a href="#preRequisitos" data-toggle="tab" title="pré-requisitos para inscrição">Pré-Requisitos</a></li>
                                <li><a href="#maisInformacoes" data-toggle="tab" title="mais informações">Mais informações</a></li>
                            </ul>

                            <div class="tab-content">
                                <!-- Cronograma -->
                                <div id="cronograma" class="tab-pane fade in active">
                                    <br>
                                    <c:if test="${empty cronograma}">
                                        <h3>Cronograma</h3>
                                        <p>Não existe um cronograma no momento!</p>                            
                                    </c:if>
                                    <ul class="timeline">
                                    <!-- Fase inscrição na timeline -->
                                        <li>
                                            <div class="timeline-badge"><i class="glyphicon glyphicon-pencil"></i></div>
                                            <div class="timeline-panel">
                                                <div class="timeline-heading">
                                                    <h4 class="timeline-title">
                                                        <c:out value="${selecao.inscricao.titulo}"></c:out>
                                                    </h4>
                                                </div>
                                                <div class="timeline-body">
                                                    <p>
                                                        <c:out value="${selecao.inscricao.descricao}"></c:out>
                                                    </p>
                                                </div>
                                            </div>
                                        </li>
                                        <!-- Fase inscrição na timeline -->

                                        <!-- Outras fases na timeline -->
                                        <c:forEach var="etapa" varStatus="" items="${selecao.etapas}"> 
                                            <li>
                                                <div class="timeline-badge danger"><i class="glyphicon glyphicon-eye-open"></i></div>
                                                <div class="timeline-panel">
                                                    <div class="timeline-heading">
                                                        <h4 class="timeline-title">
                                                            <c:out value="${etapa.titulo}"></c:out>
                                                        </h4>
                                                    </div>
                                                    <div class="timeline-body">
                                                        <p>
                                                            <c:out value="${etapa.descricao}"></c:out>
                                                        </p>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                        <!-- Outras fases na timeline -->
                                    </ul>    
                                </div>
                                <div id="preRequisitos" class="tab-pane fade">
                                    <br>
                                    <c:if test="${empty preRequisitos}">
                                        <h3>Pré-Requisitos</h3>
                                        <p>Sem pré-requisitos!</p>                            
                                    </c:if>
                                     <c:if test="${not empty preRequisitos}">
                                        <p><c:out value="${selecao.descricaoPreRequisitos}"></c:out></p>
                                    </c:if>                        
                                </div>
                                <div id="maisInformacoes" class="tab-pane fade">
                                    <br>
                                    <c:if test="${empty maisInformacoes}">
                                        <h3>Mais Informações</h3>
                                        <p>Sem mais informações!</p>                            
                                    </c:if>
                                     <c:if test="${not empty maisInformacoes}">
                                        <p>Vagas Remuneradas: <c:out value="${selecao.vagasRemuneradas}"></c:out></p>
                                        <p>Vagas Voluntárias: <c:out value="${selecao.vagasVoluntarias}"></c:out></p>
                                        <p>Área de Concentração: <c:out value="${selecao.areaDeConcentracao}"></c:out></p>
                                        <p>Categoria: <c:out value="${selecao.categoria}"></c:out></p>
                                    </c:if>                        
                                </div>
                            <!-- Fases da Seleção-->
                                </c:if>
                            </div>
                    <!-- Menu central -->

                        </div>
                    </div>
                </div>
            </div>
        </div>
    

        <!-- Rodapé -->
        <footer class="text-muted">
          <div class="container">

          </div>
        </footer>
        <!-- Rodapé -->

  </body>
</html>
