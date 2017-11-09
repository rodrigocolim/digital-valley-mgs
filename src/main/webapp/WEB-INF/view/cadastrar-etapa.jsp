<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="n2s">
        <link rel="icon" href="favicon.ico">
        <title>Darwin - Sistema de Gerenciamento de Seleções</title>


        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/design.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.standalone.css" />
    </head>
    <body>
    <c:import url="elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
    <div class="container-fluid">
        <div class="row row-offcanvas row-offcanvas-right">
            <c:import url="elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
            <div class="col-sm-8">
                <nav class="breadcrumb">
                    <span class="breadcrumb-item">Você está em:</span> 
                    <a class="breadcrumb-item active" href="/Darwin">Início</a>
                    <a class="breadcrumb-item active" href="${selecao.codSelecao}">${selecao.titulo}</a>
                    <a class="breadcrumb-item active" href="cadastrarEtapas">Cadastrar Etapa</a>
                </nav>

                <h1>Cadastrar Etapa</h1>
                <p>Atenção: Os campos abaixo (*) são de preenchimento obrigatório</p>
<<<<<<< HEAD
                <br>
                <nav class="nav nav-pills" id="myTab" role="tablist">
                    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Informações Básicas</a>
                </nav>
=======
>>>>>>> 5da7e835081782be935d0dc37ace59a999fda674
                <br>
                <div class="form-group">
                    <form method="POST" action="cadastrarEtapa">
                        <label for="tituloInput">Titulo*</label>
                        <input type="text" name="titulo" value="${etapa.titulo}" class="form-control" id="tituloInput" aria-describedby="tituloHelp" placeholder="Digite um título para a etapa" required>
                        <small id="tituloHelp" class="form-text text-muted">Exemplo: Inscrição</small>
                        <div class="invalid-feedback">
                            O titulo da etapa é inválido
                        </div>
                        <br>
                        <label for="descricaoInput">Descrição*</label>
                        <textarea class="form-control" name="descricao" id="descricaoInput" placeholder="Digite uma breve descrição sobre a etapa" required>${etapa.descricao}</textarea>
                        <div class="invalid-feedback">
                            A descrição da etapa é inválida
                        </div>
                        <br>
                        <div class="card">
                            <div class="card-header">
                                Período
                            </div>
                            <div class="card-body">
                                <div class="container" id="sandbox-container">
                                <small id="periodoHelp" class="form-text text-muted">Selecione uma data para Início e Término</small>
                                    <div class="input-daterange input-group" id="datepicker">
                                        <input type="text" class="input-sm form-control" name="dataInicio" required/>
                                        <span class="input-group-addon">até</span>
                                        <input type="text" class="input-sm form-control" name="dataTermino" required/>
                                        <div class="invalid-feedback">
                                            Selecione uma data para Início e Término
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>

                        <label for="documentacaoExigidaInput">Documentação Exigida</label>
                        <textarea class="form-control" name="descricao" id="descricaoInput" placeholder="Digite uma breve descrição sobre os documentos que são exigidos para esta etapa">${etapa.documentacaoExigida}</textarea>

                        <br>
                        <!-- Colocar avaliadores quando a ligação com o Guardião for realizada -->
                        <label for="AvaliadoresInput">Avaliadores*</label>
                        <small id="avaliadoresHelp" class="form-text text-muted">Selecione os avaliadores dessa etapa</small>
                        <div class="col-xs-6">
                            <div class="well" style="max-height: 300px;overflow: auto;">
                                <ul class="list-group checked-list-box">
                                    <li class="list-group-item">
                                        <label class="custom-control custom-checkbox" for="avaliadoresInput" required>
                                            <input type="checkbox" class="custom-control-input" id="avaliadoresInput">
                                            <span class="custom-control-indicator"></span>
                                            <span class="custom-control-description align-bottom">Alex</span>
                                        </label>
                                        <div class="invalid-feedback">
                                            Selecione no mínimo um avaliador
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <br>

                        <label for="criterioDeAvaliacaoInput">Critério de Avaliação*</label>
                        <select type="text" name="criterioDeAvaliacao" value="${etapa.criterioDeAvaliacao}"class="form-control" id="categoriaInput" required>
                            <option selected="selected" disabled="disabled">- Selecione o critério de avaliação dessa etapa -</option>
                            <option>Nota</option>
                            <option>Deferido ou Indeferido</option>
                            <option>Manual</option>
                        </select>
                        <div class="invalid-feedback">
                            Escolha um critério de avaliação
                        </div>
                        <br>

                        <button type="button" class="btn btn-secondary" data-toggle="button" aria-pressed="false" autocomplete="off">
                            Cancelar
                        </button>
                        <input type="submit" value="Salvar" class="btn btn-primary">
                    </form>
                </div>
            </div>
        </div>
    </div>

    <c:import url="elements/rodape.jsp" charEncoding="UTF-8"></c:import>  
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js" ></script>
    <script type="text/javascript">
    // Example starter JavaScript for disabling form submissions if there are invalid fields
     $('#sandbox-container .input-daterange').datepicker({
        todayHighlight: true
        
     });
    (function() {
      'use strict';
      window.addEventListener('load', function() {
        var form = document.getElementById('needs-validation');
        form.addEventListener('submit', function(event) {
          if ((form.checkValidity() === false)) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add('was-validated');
        }, false);
      }, false);
    })();
    

    </script>
</body>
</html>

