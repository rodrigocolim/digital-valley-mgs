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
        <link rel="stylesheet" href="../../css/adaptaBootstrap.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
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
                          <li><a href="#" title="configurações"><span class="glyphicon glyphicon-cog"></span> Configurações</a></li>
                      <li><a href="#" title="sair do sistema"><span class="glyphicon glyphicon-log-out"></span> Sair</a></li>
                    </ul>
                </div>
            </div>
        </nav>
      
        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">
                    <ul class="nav nav-pills nav-stacked text-left">
                        <li class="active"><a href="#"><span>Início</span></a></li>
                        <li><a href="#">Minhas seleções</a></li>
                        <li>
                          <a data-toggle="collapse" href="#collapse1"><span class="col-sm-12" style="margin-left: -15px;">Assistência estudantil</span> <span class="glyphicon glyphicon-chevron-down text-right"></span></a>
                          <ul id="collapse1" class="panel-collapse collapse">
                            <li><a href="#">Bolsa de Iniciação Acadêmica</a></li>
                            <li><a href="#">Auxílio Moradia</a></li>
                            <li><a href="#">Auxílio Emergêncial</a></li>
                            <li><a href="#">Insenção do RU</a></li>
                          </ul>
                        </li>
                        <li>
                          <a data-toggle="collapse" href="#collapse2"><span class="col-sm-12" style="margin-left: -15px;">Concursos para servidores</span> <span class="glyphicon glyphicon-chevron-down text-right"></span></a>
                          <ul id="collapse2" class="panel-collapse collapse">
                            <li><a href="#">Seleção para Professor Substituto</a></li>
                            <li><a href="#">Concurso para Professor Efetivo</a></li>
                            <li><a href="#">Concurso para Técnicos-Administrativos</a></li>
                          </ul>
                        </li>
                        <li><a href="http://www.jquery2dotnet.com">Bolsas</a></li>
                        <li><a href="http://www.jquery2dotnet.com">Outras seleções</a></li>

                        <li><a href="http://www.jquery2dotnet.com">Notícias</a></li>
                    </ul>
                </div>
                <!-- Menu lateral esquerdo -->

                <!-- Menu central -->
            <div class="col-sm-8 text-left">

            <div class="container">
                <h2>Início</h2>
                <ul class="nav nav-tabs">
                  <li class="active"><a data-toggle="tab" href="#novasSelecoes">Novas seleções</a></li>
                  <li><a data-toggle="tab" href="#inscricoesAbertas">Inscrições abertas</a></li>
                  <li><a data-toggle="tab" href="#emAndamento">Seleções em andamento</a></li>
                  <li><a data-toggle="tab" href="#encerrados">Seleções encerradas</a></li>
                </ul>

                <div class="tab-content">
                    <div id="novasSelecoes" class="tab-pane fade in active">
                        <br>
                        <c:if test="${not empty sessionScope.novasSelecoes}">
                            <c:forEach var="selecao" varStatus="" items="${sessionScope.novasSelecoes}">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <div class="col-sm-6">
                                            <strong>
                                                <c:out value="${selecao.titulo}"></c:out>
                                            </strong>
                                        </div>
                                        <div class="text-right">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                            ${selecao.etapaAtual.titulo} - <b>${selecao.etapaAtual.dataInicio}</b> à <b>${selecao.etapaAtual.dataTermino}</b>
                                        </div>
                                    </div>
                                    <div class="panel-body">
                                    <c:out value="${selecao.descricao}"></c:out>
                                        <a href="selecao.html?id=${selecao.id}">Ver mais...</a>
                                    </div>
                                    <div class="panel-footer text-right">
                                        <button type="button" class="btn btn-link">
                                            <span class="glyphicon glyphicon-save-file"></span> Ver edital</button>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>

                <!-- Inscrições Abertas -->
                <div id="inscricoesAbertas" class="tab-pane fade">
                  <br>
                  <div class="panel panel-default">
                    <div class="panel-heading">
                      <div class="col-sm-4"><strong>Auxílio Moradia - 2017.1</strong></div>
                      <div class="text-right">
                        <span class="glyphicon glyphicon-calendar"></span>
                        Inscrições - <b>17/09/2017</b> à <b>28/09/2017</b></div>
                    </div>
                    <div class="panel-body"> O Programa Auxílio Moradia tem por objetivo viabilizar a permanência de estudantes matriculados nos Cursos de Graduação dos Campi da Universidade Federal do Ceará (UFC) em Sobral, Cariri e Quixadá, em comprovada situação de vulnerabilidade econômica, assegurando-lhes auxílio institucional para complementação de despesas com moradia e alimentação durante todo o período do curso ou enquanto persistir a mesma situação. <a href="#">Ver mais...</a></div>
                    <div class="panel-footer text-right">
                      <button type="button" class="btn btn-link"><span class="glyphicon glyphicon-save-file"></span> Ver edital</button>
                      <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span> Inscrever-se</button>
                    </div>
                  </div>  
                </div>
                <!-- Inscrições Abertas -->

                <!-- Em andamento -->
                <div id="emAndamento" class="tab-pane fade">
                  <br>
                  <div class="panel panel-default">
                    <div class="panel-heading">
                      <div class="col-sm-4"><strong>Auxílio Moradia - 2017.1</strong></div>
                      <div class="text-right">
                        <span class="glyphicon glyphicon-calendar"></span>
                        Análise de documentação - <b>17/09/2017</b> à <b>28/09/2017</b></div>
                    </div>
                    <div class="panel-body"> O Programa Auxílio Moradia tem por objetivo viabilizar a permanência de estudantes matriculados nos Cursos de Graduação dos Campi da Universidade Federal do Ceará (UFC) em Sobral, Cariri e Quixadá, em comprovada situação de vulnerabilidade econômica, assegurando-lhes auxílio institucional para complementação de despesas com moradia e alimentação durante todo o período do curso ou enquanto persistir a mesma situação. <a href="#">Ver mais...</a></div>
                    <div class="panel-footer text-right">
                      <button type="button" class="btn btn-link"><span class="glyphicon glyphicon-save-file"></span> Ver edital</button>
                      <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span> Acompanhar</button>
                    </div>
                  </div>
                </div>
                <!-- Em andamento -->

                <!-- Encerrados -->
                <div id="encerrados" class="tab-pane fade">
                  <br>
                  <h3>Encerrados</h3>
                  <p>Não possuem concursos encerrados!</p>
                </div>
                <!-- Encerrados -->
              </div>
            </div>
        </div>
        <!-- Menu central -->

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
