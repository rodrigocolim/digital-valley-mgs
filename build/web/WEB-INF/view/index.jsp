<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <meta name="description" content="">
        <meta name="author" content="n2s">
        <link rel="icon" href="favicon.ico">
        <title>Darwin - Sistema de Gerenciamento de Seleções</title>
        
        
        
        <!-- Bootstrap JS / CSS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/design.css" />
    </head>

    <body>
        <nav class="navbar navbar-inverse">
            <c:import charEncoding="UTF-8" url="elements/menu-superior.jsp"></c:import>
        </nav>
        <div class="container-fluid text-center">    
            <div class="row content">
                
                <!-- Menu lateral esquerdo -->
                <div class="col-sm-2 sidenav ">
                    <c:import charEncoding="UTF-8" url="elements/menu-lateral.jsp"></c:import>
                </div>
                <!-- Menu lateral esquerdo -->

                <!-- Menu central -->
                <div class="col-sm-8 text-left">
                    <div class="container">
                        <h2>Início</h2>
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#novasSelecoes" data-toggle="tab" title="novas seleções">Novas seleções</a></li>
                            <li><a href="#inscricoesAbertas" data-toggle="tab" title="seleções com inscrições abertas">Inscrições abertas</a></li>
                            <li><a href="#emAndamento" data-toggle="tab" title="seleções em andamento">Em andamento</a></li>
                            <li><a href="#encerradas" data-toggle="tab" title="seleções encerradas">Encerradas</a></li>
                        </ul>

                        <div class="tab-content">
                            <!-- Novas seleções -->
                            <div id="novasSelecoes" class="tab-pane fade in active">
                                <c:if test="${empty novasSelecoes}">
                                    <h3>Novas Seleções</h3>
                                    <p>Não existem novas seleções no momento!</p>                            
                                </c:if>
                                    
                                <c:if test="${not empty novasSelecoes}">
                                    <c:forEach var="selecao" step="1" items="${novasSelecoes}">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <div class="col-sm-6" style="text-transform: uppercase;">
                                                    <strong>
                                                        <c:out value="${selecao.titulo}"></c:out>
                                                    </strong>
                                                </div>
                                                <div class="text-right" style="text-transform: uppercase;">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                    ${selecao.inscricao.titulo} - <b> ${selecao.inscricao.periodo.dataInicio}</b> à <b>${selecao.inscricao.periodo.dataTermino}</b>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <c:out value="${selecao.descricao}"></c:out>
                                            </div>
                                            <div class="panel-footer text-right">
                                                <a href="selecao?codSelecao=${selecao.codSelecao}" type="button" class="btn btn-link" >
                                                    <span class="glyphicon glyphicon-info-sign"></span> Mais informações
                                                </a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <ul class="pagination">
                                        <li><a href="#">1</a></li>
                                        <li class="active"><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                    </ul>                                     
                                </c:if>
                            </div>
                            <!-- Novas seleções -->
                            
                            <!-- Inscrições abertas -->
                            <div id="inscricoesAbertas" class="tab-pane fade">
                                <c:if test="${empty inscricoesAbertas}">
                                    <h3>Inscrições abertas</h3>
                                    <p>Não existem seleções com inscrições abertas no momento!</p>                            
                                </c:if>

                                <c:if test="${not empty inscricoesAbertas}">
                                    <c:forEach var="selecao" varStatus="" items="${inscricoesAbertas}">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <div class="col-sm-6">
                                                    <strong>
                                                        <c:out value="${selecao.titulo}"></c:out>
                                                    </strong>
                                                </div>
                                                <div class="text-right">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                    ${selecao.etapas} - <b>${selecao.etapas}</b> à <b>${selecao.etapas}</b>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <c:out value="${selecao.descricao}"></c:out>
                                            </div>
                                            <div class="panel-footer text-right">
                                                <a href="selecao.html?cod=${selecao.codSelecao}" type="button" class="btn btn-link" >
                                                    <span class="glyphicon glyphicon-info-sign"></span> Mais informações
                                                </a>
                                                <a href="inscricao.html?cod=${selecao.codSelecao}" type="button" class="btn btn-link" >
                                                    <span class="glyphicon glyphicon-pencil"></span> Inscrever-se
                                                </a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>                        
                            </div>
                            <!-- Inscrições abertas -->

                            <!-- Em andamento -->
                            <div id="emAndamento" class="tab-pane fade">
                                <c:if test="${empty emAndamento}">
                                    <h3>Seleções em andamento</h3>
                                    <p>Não existem seleções em andamento!</p>                            
                                </c:if>
                                <c:if test="${not empty emAndamento}">
                                    <c:forEach var="selecao" varStatus="" items="${emAndamento}">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <div class="col-sm-6">
                                                    <strong>
                                                        <c:out value="${selecao.titulo}"></c:out>
                                                        </strong>
                                                    </div>
                                                    <div class="text-right">
                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                    ${selecao.etapas} - <b>${selecao.etapas}</b> à <b>${selecao.etapas}</b>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <c:out value="${selecao.descricao}"></c:out>
                                                </div>
                                                <div class="panel-footer text-right">
                                                    <a href="selecao.html?cod=${selecao.codSelecao}" type="button" class="btn btn-link" >
                                                    <span class="glyphicon glyphicon-info-sign"></span> Mais informações
                                                </a>
                                                <a href="inscricao.html?cod=${selecao.codSelecao}" type="button" class="btn btn-link" >
                                                    <span class="glyphicon glyphicon-pencil"></span> Inscrever-se
                                                </a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                    
                            </div>
                            <!-- Em andamento -->

                            <!-- Encerradas -->
                            <div id="encerradas" class="tab-pane fade">
                                <c:if test="${empty encerradas}">
                                    <h3>Seleções encerradas</h3>
                                    <p>Não possuem seleções encerradas!</p>
                                </c:if>
                                <c:if test="${not empty encerradas}">
                                    <c:forEach var="selecao" varStatus="" items="${encerradas}">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <div class="col-sm-6">
                                                    <strong>
                                                        <c:out value="${selecao.titulo}"></c:out>
                                                        </strong>
                                                    </div>
                                                    <div class="text-right">
                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                    ${selecao.etapas} - <b>${selecao.etapas}</b> à <b>${selecao.etapas}</b>
                                                </div>
                                            </div>
                                            <div class="panel-body">
                                                <c:out value="${selecao.descricao}"></c:out>
                                                </div>
                                                <div class="panel-footer text-right">
                                                    <a href="selecao.html?cod=${selecao.codSelecao}" type="button" class="btn btn-link" >
                                                    <span class="glyphicon glyphicon-info-sign"></span> Mais informações
                                                </a>
                                                <a href="inscricao.html?cod=${selecao.codSelecao}" type="button" class="btn btn-link" >
                                                    <span class="glyphicon glyphicon-pencil"></span> Inscrever-se
                                                </a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>                         
                            </div>
                            <!-- Encerrados -->
                        </div>
                    </div>
                </div>
                <!-- Menu central -->
            </div>
        </div>
    

        <!-- Rodapé -->
        <footer class="container-fluid text-center">
            <c:import charEncoding="UTF-8" url="elements/rodape.jsp"></c:import>
        </footer>
        <!-- Rodapé -->

    </body>
</html>
