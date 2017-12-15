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
                        <form method="POST" action="" accept-charset="UTF-8" enctype="multipart/form-data" id="needs-validation" class="select-usuario" novalidate>
                            <label for="usuarioInput">Usuário</label>
                            <select id="usuarioInput" class="form-control col-md-8" name="usuario">
                                    <option value="" selected="selected" disabled="disabled">Selecione o usuário desejado</option>
                                <c:forEach items="${usuarios}" var="usuario">
                                    <option id="usuarioOption-${usuario.nome}" value="${usuario.codUsuario}">${usuario.nome}</option>
                                </c:forEach>
                            </select>
                        </form>
                            <c:if test="${not empty usuarioSelecionado}">
                            <form method="POST" action="/atualiza" accept-charset="UTF-8" enctype="multipart/form-data" id="needs-validation" novalidate>
                            <div class="form-row">
                                <select id="PermissaoInput" class="form-control col-md-8">
                                    <option value="" selected="selected" disabled="disabled">Selecione a permissão desejada</option>
                                    <option id="PermissaoOption-1" value="1" ${fn:contains(usuarioSelecionado.permissoes, 'PARTICIPANTE') ? "disabled='disabled'" : ""} >Participante</option>
                                    <option id="PermissaoOption-2" value="2" ${fn:contains(usuarioSelecionado.permissoes, 'AVALIADOR') ? "disabled='disabled'" : ""} >Avaliador</option>
                                    <option id="PermissaoOption-3" value="3" ${fn:contains(usuarioSelecionado.permissoes, 'RESPONSAVEL') ? "disabled='disabled'" : ""}>Responsavel</option>
                                    <option id="PermissaoOption-4" value="4" ${fn:contains(usuarioSelecionado.permissoes, 'ADMINISTRADOR') ? "disabled='disabled'" : ""}>Administrador</option>
                                </select>
                                &nbsp;&nbsp;
                                <input type="button" class="btn btn-secondary btn-sm " onclick="adicionaPermissao()" value="Adicionar">                            
                            </div>
                                
                            <br>
                            <ul class="list-group col-md-8 " id="listaPermissoes">
                                <c:forEach var="permissoes" items="${usuarioSelecionado.permissoes}">
                                    <li class="list-group-item">
                                        <input type="hidden" name="codUsuario" value="${permissoes}" style="display: none;"/>
                                        ${permissoes.nivel}
                                        <button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removePermissao('${permissoes.nivel}')">clear</button>
                                    </li>
                                </c:forEach>
                            </ul>                      
                            <input type="submit" class="btn btn-primary" value="Salvar">
                        </form>
                        </c:if>        
                    </div>
                </div>
            </div>
        </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>
      var listaPermissoes = [];
      var codPermissoes = [];
      var numPermissoes = 0;
      function adicionaPermissao(){
        var PermissaoInput = document.getElementById("PermissaoInput").value;
        var nomePermissao = PermissaoInput.substring(PermissaoInput.indexOf("-") + 1, PermissaoInput.lenght);
        var codPermissao = PermissaoInput.substring(0, PermissaoInput.indexOf("-"));
        if(nomePermissao !== ""){
            listaPermissoes[numPermissoes] = nomePermissao;
            codPermissoes[numPermissoes] = codPermissao;
            document.getElementById("PermissaoOption-"+codPermissao+"").disabled = "disabled";
            numPermissoes++;
        }
        document.getElementById("PermissaoInput").value = "";
        atualizaPermissoes();
        
      }
      function atualizaPermissoes(){
          var list = document.getElementById("listaPermissoes");
          list.innerHTML = "";
          for(i = 0;i < listaPermissoes.length;i++){
            if(listaPermissoes[i] !== ""){
                list.innerHTML += '<li class="list-group-item"><input type="hidden" name="codPermissoes" value="'+codPermissoes[i]+'" style="display: none;"> '+ listaPermissoes[i] +'<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removePermissao(\''+listaPermissoes[i]+'\')">clear</button></li>';
            }
          }
      }
      function removePermissao(codPermissao){
          for(i = 0;i < listaPermissoes.length;i++){
              alert("Oie");
              if(listaPermissoes[i] === codPermissao){
                  document.getElementById("PermissaoOption-"+codPermissao+"").disabled = "";
                  listaPermissoes[i] = "";
                  codPermissoes[i] = "";
                  
              }
          }
          atualizaPermissoes();
      }

        $(document).ready(function() {
            $("#usuarioInput").change(function(){
                $(".select-usuario").submit();
            });
        });
    </script>
    </body>
</html>