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
    </head>
    <body>
    <c:import url="elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
    <div class="container-fluid">
        <div class="row row-offcanvas row-offcanvas-right">
            <c:import url="elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
            <div class="col-sm-8">
                <nav class="breadcrumb">
                    <span class="breadcrumb-item">Você está em:</span> 
                    <a class="breadcrumb-item active" href="indexController">Início</a>
                    <a class="breadcrumb-item active" href="cadastrarSelecao">Cadastrar Seleção</a>
                    <a class="breadcrumb-item active" href="cadastrarEtapas">Cadastrar Etapa</a>
                </nav>

                <h1>Cadastrar Etapa</h1>
                <br>
                <p>Atenção: Os campos abaixo (*) são de preenchimento obrigatório</p>
                <nav class="nav nav-pills" id="myTab" role="tablist">
                    <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Informações Básicas</a>
                </nav>
                <br>
                
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
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
                                        <label class="custom-control" for="periodoInput">Período </label>
                                    </div>
                                    <div class="card-body">
                                        <div class="container">
                                            <div class='sm-md-5'>
                                                <label for="dataInicioInput">Data Início*</label>
                                                <div class="form-group">
                                                    <div class='input-group date' id='datetimepicker6'>
                                                        <input type='text' name="dataInicio" value="${etapa.periodo.dataInicio}" class="form-control" id="dataInicioInput"/>
                                                        <span class="input-group-addon">
                                                            <i class="material-icons">date_range</i>
                                                        </span>
                                                        <div class="invalid-feedback">
                                                            Insira uma Data de Início válida
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class='sm-md-5'>
                                                <label for="dataTerminoInput">Data Término*</label>
                                                <div class="form-group">
                                                    <div class='input-group date' id='datetimepicker7'>
                                                        <input type='text' name="dataTermino" value="${etapa.periodo.dataTermino}" class="form-control" id="dataTerminoInput"/>
                                                        <span class="input-group-addon">
                                                            <i class="material-icons">date_range</i>
                                                        </span>
                                                        <div class="invalid-feedback">
                                                            Insira uma Data de Término válida
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                
                                <label for="documentacaoExigidaInput">Documentação Exigida</label>
                                <textarea class="form-control" name="descricao" id="descricaoInput" placeholder="Digite uma breve descrição sobre os documentos que são exigidos para esta etapa" required>${etapa.documentacaoExigida}</textarea>
                                
                                <br>
                                <!-- Colocar avaliadores quando a ligação com o Guardião for realizada -->
                                <label for="AvaliadoresInput">Avaliadores*</label>
                                <small id="avaliadoresHelp" class="form-text text-muted">Selecione os avaliadores dessa etapa</small>
                                <div class="col-xs-6">
                                    <div class="well" style="max-height: 300px;overflow: auto;">
                                        <ul class="list-group checked-list-box">
                                            <li class="list-group-item">
                                                <label class="custom-control custom-checkbox" for="avaliadoresInput">
                                                    <input type="checkbox" class="custom-control-input" id="avaliadoresInput">
                                                    <span class="custom-control-indicator"></span>
                                                    <span class="custom-control-description align-bottom">Alex</span>
                                                </label>
                                            </li>
                                            
                                        </ul>
                                    </div>
                                </div>
                                
                                <br>
                                
                                <label for="criterioDeAvaliacaoInput">Critério de Avaliação*</label>
                                <select type="text" name="criterioDeAvaliacao" value="${etapa.criterioDeAvaliacao}"class="form-control" id="categoriaInput" required>
                                    <option selected="selected">- Selecione o critério de avaliação dessa etapa -</option>
                                    <option>Nota</option>
                                    <option>Deferido ou Indeferido</option>
                                    <option>Manual</option>
                                </select>
                                <br>
                                
                                <button type="button" class="btn btn-secondary" data-toggle="button" aria-pressed="false" autocomplete="off">
                                    Cancelar
                                </button>
                                <button type="button" class="btn btn-secondary" data-toggle="button" aria-pressed="false" autocomplete="off">
                                    Adicionar nova etapa
                                </button>
                                <input type="submit" value="Salvar e Continuar" class="btn btn-primary">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:import url="elements/rodape.jsp" charEncoding="UTF-8"></c:import>  
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
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
    
    });
    </script>
    <script type="text/javascript">
    $(function () {
        $('#dataInicioInput').datetimepicker();
        $('#dataTerminoInput').datetimepicker({
            useCurrent: false //Important! See issue #1075
        });
        $("#datetimepicker6").on("dp.change", function (e) {
            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker7").on("dp.change", function (e) {
            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
        });
    });
</script>
</body>
</html>


