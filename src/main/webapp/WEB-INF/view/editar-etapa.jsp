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
                    <a class="breadcrumb-item active" href="cadastrarEtapas">Editar Etapa</a>
                </nav>

                <h1>Editar Etapa</h1>
                <p>Atenção: Os campos abaixo (*) são de preenchimento obrigatório</p>
                <br>
                <div class="form-group">
                    <form method="POST" action="editarEtapa">
                        <label for="tituloInput">Titulo*</label>
                        <input type="text" name="titulo" value="${etapa.titulo}" class="form-control" id="tituloInput" aria-describedby="tituloHelp" placeholder="Digite um título para a etapa" required>
                        <div class="invalid-feedback">
                            Digite um título para a seleção
                        </div>
                        
                        <br>
                        <label for="descricaoInput">Descrição*</label>
                        <textarea class="form-control" name="descricao" id="descricaoInput" placeholder="Digite uma breve descrição sobre a etapa" value="${etapa.descricao}" required>${etapa.descricao}</textarea>
                        <div class="invalid-feedback">
                            Digite uma descrição para a seleção
                        </div>

                        <br>
                        <label for="documentacaoExigidaInput">Documentação Exigida</label>
                        <textarea class="form-control" name="descricao" id="descricaoInput" placeholder="Digite uma breve descrição sobre os documentos que são exigidos para esta etapa" value="${etapa.documentacaoExigida}">${etapa.documentacaoExigida}</textarea>

                        <br>
                        <label for="descricaoInput">Período*</label>
                        <div id="sandbox-container">
                            <div class="input-daterange input-group col-lg-6 align-left" style="padding-left: 0px;" id="datepicker">
                                <input type="text" class="form-control text-left" placeholder="Digite a data de início desta etapa" name="dataInicio" value="${etapa.dataInicio}" required/>
                                <span class="input-group-addon">até</span>
                                <input type="text" class="form-control text-left " placeholder="Digite a data de término desta etapa" name="dataTermino" value="${etapa.dataTermino}" required/>
                                <div class="invalid-feedback">
                                    Selecione uma data para Início e Término
                                </div>
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
                        <label for="AvaliadoresInput">Avaliadores*</label>
                        <input type="text" id="nomeAvaliador" class="form-control" onkeyup="getListaAvaliadores()" placeholder="Digite o nome do avaliador" title="Digite o nome do avaliador">
                        <ul id="listaAvaliadores" class="list-group">
                        <c:forEach var="avaliador" items="${avaliadores}">
                            <li class="list-group-item" style="display: none;">
                                <input type="checkbox" value="${avaliador.codUsuario}">
                                <span>${avaliador.codUsuario}</span>
                            </li>
                        </c:forEach>
                        </ul>
                        <small id="avaliadoresHelp" class="form-text text-muted">Selecione os avaliadores dessa etapa</small>
                        <br>

                        <a href="/Darwin/selecao/${selecao.codSelecao}" type="button" class="btn btn-secondary">
                            Cancelar
                        </a>
                        <input type="submit" value="Salvar" class="btn btn-primary">
                    </form>
                </div>
            </div>
        </div>
    </div>

    <c:import url="elements/rodape.jsp" charEncoding="UTF-8"></c:import>  
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js" ></script>
    <script src="${pageContext.request.contextPath}/resources/js/script.js" ></script>
    <script type="text/javascript">
     $('#sandbox-container .input-daterange').datepicker({
        todayHighlight: true
        
     });
    </script>
    <script>
    function getListaAvaliadores() {
        ordenaLista();
        var input, filter, ul, li, a, i;
        input = document.getElementById("nomeAvaliador");
        filter = input.value.toUpperCase();
        ul = document.getElementById("listaAvaliadores");
        li = ul.getElementsByTagName("li");
        for (i = 0; i < li.length; i++) {
            a = li[i].getElementsByTagName("span")[0];
            if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                li[i].getElementsByTagName("span")[0].style.display = "";
                li[i].getElementsByTagName("input")[0].style.display = "";
                li[i].style.display = "";
            } else {
                if(li[i].getElementsByTagName("input")[0].checked === false){
                    li[i].getElementsByTagName("span")[0].style.display = "none";
                    li[i].getElementsByTagName("input")[0].style.display = "none";
                    li[i].style.display = "none";                    
                }

            }
        }
    }
    
    function ordenaLista() {
        var list, i, switching, b, shouldSwitch;
        list = document.getElementById("listaAvaliadores");
        switching = true;
        while (switching) {
          switching = false;
          b = list.getElementsByTagName("li");
          for (i = 0; i < (b.length - 1); i++) {
            shouldSwitch = false;
            if ((b[i + 1].getElementsByTagName("input")[0].checked === true) && (b[i].getElementsByTagName("input")[0].checked === false)) {
              shouldSwitch= true;
              break;
            }
          }
          if (shouldSwitch) {
            b[i].parentNode.insertBefore(b[i + 1], b[i]);
            switching = true;
          }
        }
      }
    </script>
</body>
</html>