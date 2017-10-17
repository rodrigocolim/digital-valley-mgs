<%-- 
    Document   : cadastro-selecao
    Created on : 13/10/2017, 11:10:36
    Author     : Lavínia Matoso
--%>

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
                            <label for="exampleFormControlSelect1">Example select</label>
                            <select class="form-control" id="exampleFormControlSelect1">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlSelect2">Example multiple select</label>
                            <select multiple class="form-control" id="exampleFormControlSelect2">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>
                    </form>
                    <!-- Formulário de cadastro -->
                    
                </div>
            </div>    
        </div>
    </body>
</html>
