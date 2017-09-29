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
                      <li><a href="logout.jsp" title="sair do sistema"><span class="glyphicon glyphicon-log-out"></span> Sair</a></li>
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
