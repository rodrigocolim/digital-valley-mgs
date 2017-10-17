<%-- 
    Document   : cadastro-selecao
    Created on : 13/10/2017, 11:10:36
    Author     : Lavínia Matoso
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="">
        <meta name="author" content="n2s">
        <link rel="icon" href="favicon.ico">
        <title>Darwin - Sistema de Gerenciamento de Seleções</title>

        <!-- Bootstrap core CSS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/recursos/css/timeline.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/recursos/css/design.css" />
    </head>
    
    <body>
        <nav class="navbar navbar-inverse">
            <c:import charEncoding="UTF-8" url="elements/menu-superior.jsp"></c:import>
        </nav>
        <div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-right">
                <c:import url="elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
                <div class="col-sm-8">
                    <nav class="breadcrumb">
                        <span class="breadcrumb-item">Você está em:</span> 
                        <a class="breadcrumb-item active" href="cadastro-selecao.jsp">Cadastro de Seleção</a>
                    </nav>
                    
                    <h1>Cadastro de Seleção</h1>
                
                    <!-- Timeline do cadastro -->
                    <nav class="nav nav-pills nav-justified">
                        <a class="nav-link active" href="cadastro-selecao">Informações Básicas</a>
                        <a class="nav-link" href="cadastro-etapas">Etapas</a>
                        <a class="nav-link" href="#">Concluído</a>
                    </nav>
                    <!-- Timeline do cadastro -->
                    
                    <!-- Formulário de cadastro -->
                    <form>
                        <div class="form-group">
                            <label for="titulo">Título</label>
                            <input type="titulo" class="form-control" id="titulo" placeholder="Ex: Bolsa de Iniciação Científica">
                        </div>
                        <div class="form-group">
                            <label for="descricao">Descrição</label>
                            <textarea class="form-control" id="descricao" rows="3"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="descricaoPreRequisitos">Pré-Requisitos</label>
                            <textarea class="form-control" id="descricaoPreRequisitos" rows="5"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="areaDeConcentracao">Área de Concentração</label>
                            <input type="areaDeConcentracao" class="form-control" id="areaDeConcentracao" placeholder="Ex: Computação">
                        </div>
                        <select class="custom-select">
                            <option selected>Categoria</option>
                            <option value="Auxilio Moradia">Auxílio Moradia</option>
                            <option value="Auxilio Emergenicial">Auxílio Emergencial</option>
                            <option value="Bolsa de Iniciacao Academica">Bolsa de Iniciação Acadêmica</option>
                            <option value="Isencao do RU">Isenção do RU</option>
                            <option value="Bolsa de Extensao">Bolsa de Extensão</option>
                            <option value="Iniciacao a Docencia">Iniciação à Docência</option>
                            <option value="PACCE">PACCE</option>
                            <option value="Cargos de Docente">Cargos de Docente</option>
                            <option value="Cargos de Técnicos Admin">Cargos de Técnicos Admin</option>
                            <option value="Professores Substitutos">Professores Substitutos</option>
                        </select>
                    </form>
                    <!-- Formulário de cadastro -->
                    
                </div>
            </div>    
        </div>
        <c:import url="elements/rodape.jsp" charEncoding="UTF-8"></c:import>  
    </body>
</html>
