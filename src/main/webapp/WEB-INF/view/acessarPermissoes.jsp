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
                        <a class="breadcrumb-item active" href="#">Acessar Permissões</a>
                    </nav>
                <c:if test="${not empty mensagem}">
                    <div class="alert alert-${status} alert-dismissible fade show" role="alert">
                        ${mensagem}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>                           
                    <h1>Acessar Permissões</h1>
                    <br>
                    <div class="form-group">
                        <form method="POST" action="acessarPermissoes" accept-charset="UTF-8" enctype="multipart/form-data" id="needs-validation" novalidate>
                            <label for="usuarioInput">Usuário</label>
                            <select id="usuarioInput" class="form-control col-md-8">
                                    <option value="" selected="selected" disabled="disabled">Selecione o usuário desejado</option>
                                <c:forEach items="${usuariosDarwin}" var="usuario">
                                    <option id="usuarioOption-${usuario.nome}" value="${usuario.codUsuario}-${usuario.nome}">${usuario.nome}</option>
                                </c:forEach>
                            </select>
                            <div class="form-row">
                                <select id="usuarioInput" class="form-control col-md-8">
                                    <option value="" selected="selected" disabled="disabled">Selecione a permissão desejada</option>
                                <c:forEach items="${usuariosDarwin.permissoes}" var="usuario">
                                    <option id="usuarioOption-${usuario.nome}" value="${permissao}">${usuario.permissoes.nivel}</option>
                                </c:forEach>
                                </select>
                                &nbsp;&nbsp;
                                <input type="button" class="btn btn-secondary btn-sm " onclick="adicionaAvaliador()" value="Adicionar">                            
                            </div>
                            <br>
                            <ul class="list-group col-md-8 " id="listaAvaliadores">
                                <c:forEach var="permissoes" items="${usuario.permissoes}">
                                    <li class="list-group-item">
                                        <input type="hidden" name="codUsuario" value="${permissoes}" style="display: none;"/>
                                        ${permissoes.nivel}
                                        <button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeAvaliador('${permissoes.nivel}')">clear</button>
                                    </li>
                                </c:forEach>
                            </ul>                      
                            <input type="submit" class="btn btn-primary" value="Salvar">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    <script>
      var listaAvaliadores = [];
      var codAvaliadores = [];
      var numAvaliadores = 0;
      function adicionaAvaliador(){
        var avaliadorInput = document.getElementById("avaliadorInput").value;
        var nomeAvaliador = avaliadorInput.substring(avaliadorInput.indexOf("-") + 1, avaliadorInput.lenght);
        var codAvaliador = avaliadorInput.substring(0, avaliadorInput.indexOf("-"));
        if(nomeAvaliador !== ""){
            listaAvaliadores[numAvaliadores] = nomeAvaliador;
            codAvaliadores[numAvaliadores] = codAvaliador;
            document.getElementById("avaliadorOption-"+nomeAvaliador+"").disabled = "disabled";
            numAvaliadores++;
        }
        document.getElementById("avaliadorInput").value = "";
        atualizaAvaliadores();
        
      }
      function atualizaAvaliadores(){
          var list = document.getElementById("listaAvaliadores");
          list.innerHTML = "";
          for(i = 0;i < listaAvaliadores.length;i++){
            if(listaAvaliadores[i] !== ""){
                list.innerHTML += '<li class="list-group-item"><input type="hidden" name="codAvaliadores" value="'+codAvaliadores[i]+'" style="display: none;"> '+ listaAvaliadores[i] +'<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeAvaliador(\''+listaAvaliadores[i]+'\')">clear</button></li>';
            }
          }
      }
      function removeAvaliador(codAvaliador){
          for(i = 0;i < listaAvaliadores.length;i++){
              if(listaAvaliadores[i] === codAvaliador){
                  document.getElementById("avaliadorOption-"+codAvaliador+"").disabled = "";
                  listaAvaliadores[i] = "";
                  codAvaliadores[i] = "";
                  
              }
          }
          atualizaAvaliadores();
      }
      
    </script>
    </body>
</html>