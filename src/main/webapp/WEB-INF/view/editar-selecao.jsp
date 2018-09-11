<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8"/>
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
                <nav aria-label="breadcrumb" role="navigation">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">Você está em: </li>
                        <li class="breadcrumb-item" aria-current="page"><a href="/Darwin">Início</a></li>
                        <li class="breadcrumb-item" aria-current="page"><a href="/Darwin/selecao/${selecao.codSelecao}">${selecao.titulo}</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Editar Seleção</li>
                    </ol>
                </nav>
            <c:if test="${not empty mensagem}">
                <div class="alert alert-${status} alert-dismissible fade show" role="alert">
                    ${mensagem}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>   
                <h1>Editar Seleção</h1>
                <p>Selecione apenas os campos que você deseja editar</p>
                <br>
                
                <div class="form-group">
                    <form method="POST" action="" accept-charset="UTF-8" enctype="multipart/form-data" id="needs-validation" novalidate> 
                        <label for="tituloInput"> <input type="checkbox" onclick="habilitaEdicao('tituloInput')"> Titulo*</label>
                        <input type="text" name="titulo" value="${selecao.titulo}" class="form-control" id="tituloInput" aria-describedby="tituloHelp" placeholder="Digite um titulo para a seleção" readonly="true" required>
                        <small id="tituloHelp" class="form-text text-muted">Exemplo: Iniciação à Docência - 2018.1</small>
                        <div class="invalid-feedback">
                            
                        </div>
                        <br>

                        <label for="descricaoInput"> <input type="checkbox" onclick="habilitaEdicao('descricaoInput')"> Descrição*</label>
                        <textarea class="form-control" name="descricao" id="descricaoInput" placeholder="Digite uma breve descrição sobre a seleção" readonly="true" required>${selecao.descricao}</textarea>
                        <div class="invalid-feedback">
                            
                        </div>
                        <br>

                        <label for="preRequisitosInput"> <input type="checkbox" onclick="habilitaEdicao('preRequisitosInput')"> Pré Requisitos</label>
                        <textarea name="descricaoPreRequisitos" class="form-control" id="preRequisitosInput" placeholder="Digite uma breve descrição sobre os pré requisitos para participar da seleção" readonly="true">${selecao.descricaoPreRequisitos}</textarea>
                        <br>

                        <label for="categoriaInput"> <input type="checkbox" onclick="habilitaEdicao('categoriaInput')"> Categoria*</label>
                        <select type="text" name="categoria" class="form-control custom-select" id="categoriaInput" readonly="true" required>
                            <option selected="${selecao.categoria eq 'Assistência Estudantil' ? 'selected' : ''}">Assistência Estudantil</option>
                            <option selected="${selecao.categoria eq 'Bolsas para Discentes' ? 'selected' : ''}">Seleções para Discentes</option>
                            <option selected="${selecao.categoria eq 'Cargos de Docente' ? 'selected' : ''}">Cargos de Docente</option>
                            <option selected="${selecao.categoria eq 'Cargos de Técnicos Admin' ? 'selected' : ''}">Cargos de Técnicos Admin</option>
                            <option selected="${selecao.categoria eq 'Professores Substitutos' ? 'selected' : ''}">Professores Substitutos</option>
                        </select>
                        <br>

                        <br>
                        <label for="areaDeConcentracaoInput"> <input type="checkbox" onclick="habilitaEdicao('areaDeConcentracaoInput')">  Área de Concentração</label>
                        <input type="text" name="areaDeConcentracao" value="${selecao.areaDeConcentracao}" class="form-control" id="areaDeConcentracaoInput" aria-describedby="tituloHelp" placeholder="Digite o nome da área de concentração" readonly="true">
                        <small id="tituloHelp" class="form-text text-muted">Exemplo: Computação, Engenharia Mecânica, LINCE</small>
                        <div class="invalid-feedback">
                            
                        </div>
                        <br>

                        <div class="card">
                            <div class="card-header col-auto">
                                
                                <label class="custom-control custom-checkbox mb-2 mr-sm-2 mb-sm-0" for="isVagasLimitadasInput">
                                    <input type="checkbox" class="custom-control-input" id="isVagasLimitadasInput" onclick="habilitaCampoVagas()"/>
                                    <span class="custom-control-indicator"></span>
                                    <span class="custom-control-description" style="margin-top: 4px;">Definir o número de vagas</span>
                                </label>
                            </div>
                            
                            <div class="card-body">
                                <label for="vagasRemuneradasInput">Número de vagas remuneradas</label>
                                <input type="number" name="vagasRemuneradas" value="${selecao.vagasRemuneradas}" class="form-control col-sm-2 disabled" id="vagasRemuneradasInput" value="0" min="0" max="100" disabled>
                                <div class="invalid-feedback" >
                                    
                                </div>
                                <br>

                                <label for="vagasVoluntariasInput">Número de vagas voluntárias</label>
                                <input type="number" name="vagasVoluntarias" value="${selecao.vagasVoluntarias}" class="form-control col-sm-2 disabled"  id="vagasVoluntariasInput" value="0" min="0" max="100" disabled>
                                <div class="invalid-feedback">
                                    
                                </div>
                            </div>
                        </div>
						<br>
                        <div class="card">
                            <div class="card-header col-auto">
                                <span class="custom-control-description" style="margin-top: 4px;">Documentos da seleção</span>
                            </div>

                            
                            <div class="card-body">

                                <br>
                                <label for="editalInput">Substituir link do edital</label>
                                <input type="text" name="file" class="form-control" id="editalInput" aria-describedby="editalHelp" placeholder="Adicione o link atual para o edital da seleção"  accept="application/pdf">
                                <small id="tituloHelp" class="form-text text-muted"> Ex. http://www.campusrussas.ufc.br/editais-e-selecoes.php</small>
                                <div class="invalid-feedback">
                                </div>
                                <br>
                                
                                <label for="anexoInput">Anexos</label>
                                <div class="form-row" style="margin-left: 2px;">
                                    <input type="text" class="form-control col-md-4" id="nomeAnexoInput" placeholder=" Digite o titulo do anexo da seleção">&nbsp; &nbsp;
                                    <input type="text" class="form-control col-md-6" id="linkAnexoInput" placeholder=" Adicione aqui o link para acesso deste anexo">&nbsp;
                                    &nbsp;
                                    <input type="button" class="btn btn-secondary btn-sm " onclick="adicionaAnexo()" value="Adicionar">                            
                                </div>

                                <br>
                                <ul class="list-group col-md-8 " id="listaAnexos">
                                </ul>
                                <br>

                                <label for="aditivosInput">Aditivos</label>
                                <div class="form-row">
                                    <input type="text" class="form-control col-md-4" id="nomeAditivoInput" placeholder=" Digite o titulo do aditivo da seleção">&nbsp; &nbsp;
                                    <input type="text" class="form-control col-md-6" id="linkAditivoInput" placeholder=" Adicione aqui o link para acesso deste aditivo">&nbsp;
                                    &nbsp;
                                    <input type="button" class="btn btn-secondary btn-sm " onclick="adicionaAditivo()" value="Adicionar">                            
                                </div>

                                <br>
                                <ul class="list-group col-md-8 " id="listaAditivos">
                                </ul>
                                <br>
                            </div>
                        </div>
                        <br>
                        <div class="card">
                            <div class="card-header col-auto">
                                <label for="ResponsaveisInput">Responsáveis</label>
                            </div>
                            <div class="card-body">
			                        <div class="form-row">
			                            <select id="responsavelInput" class="form-control col-md-8" style="margin-left: 3px">
			                                <option value="" selected="selected" disabled="disabled">Selecione os responsáveis por esta seleção</option>
			                                <c:forEach items="${usuarios}" var="usuario">
			                                     <option id="responsavelOption-${usuario.nome}" value="${usuario.codUsuario}-${usuario.nome}">${usuario.nome}</option>
			                                 </c:forEach>
			                            </select>
			                            &nbsp;&nbsp;
			                            <input type="button" class="btn btn-secondary btn-sm " onclick="adicionaResponsavel()" value="Adicionar">                            
			                        </div>
			                        <br>
			                        <ul class="list-group col-md-8" id="listaResponsaveis">
			                        	<c:forEach var="responsavel" items="${responsaveis}">
			                                	<li class="list-group-item">
			                                     <input type="hidden" name="codResponsaveis" value="${responsavel.codUsuario}-${responsavel.nome}" style="display: none;"/>
			                                     ${responsavel.nome}
			                                     <button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeResponsaveis('${responsavel.nome}');">clear</button>
			                                 </li>    
			                             </c:forEach>
			                        </ul>
                        			<br>
                        	</div>
                        </div>
                        <br>
                        <a href="/Darwin/selecao/${selecao.codSelecao}" type="button" id="enviar" class="btn btn-secondary">
                            Cancelar
                        </a>
                        <input type="button"  class="btn btn-primary" value="Salvar" data-toggle="modal" data-target="#confirmarSelecao" >
                        
                        <!-- Modal -->
                        <div class="modal fade" id="confirmarSelecao" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="modalLabel">Confirmar edição da seleção</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p>Você deseja confirmar as atualizações realizadas na seleção?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Cancelar</button>
                                        <button type="submit" class="btn btn-primary btn-sm">Confirmar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                    </form>
                </div>
            </div>
        </div>
    </div>

    <c:import url="elements/rodape.jsp" charEncoding="UTF-8"></c:import>  
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/js/script.js" ></script>
  	<script>
      var listaResponsaveis = ${responsaveis};
      var codResponsaveis = [];
      var numResponsaveis = 0;
      
      $(document).ready(function() { 
      	for (i=0;i < listaResponsaveis.length;i++) {
      		var nomeResponsavel = listaResponsaveis[i].nome;
      		document.getElementById("responsavelOption-"+nomeResponsavel+"").disabled = "disabled";
      		codResponsaveis[i] = listaResponsaveis[i].nome.substring(0, responsavelInput.indexOf("-"));
      		numResponsaveis++;
      	}
      });
      
      function adicionaResponsavel(){
    	  alert("reiureryewur");
        var responsavelInput = document.getElementById("responsavelInput").value;
        var nomeResponsavel= responsavelInput.substring(responsavelInput.indexOf("-") + 1, responsavelInput.lenght);
        var codResponsavel = responsavelInput.substring(0, responsavelInput.indexOf("-"));
        if(nomeResponsavel !== ""){
            listaResponsaveis[numResponsaveis] = nomeResponsavel;
            codResponsaveis[numResponsaveis] = codResponsavel;
            document.getElementById("responsavelOption-"+codResponsavel+"").disabled = "disabled";
            numResponsaveis++;
        }
        document.getElementById("responsavelInput").value = "";
        atualizaResponsaveis();
        
      }
      function atualizaResponsaveis(){
          var list = document.getElementById("listaResponsaveis");
          list.innerHTML = "";
          for(i = 0;i < listaResponsaveis.length;i++){
            if(listaResponsaveis[i] !== ""){
                list.innerHTML += '<li class="list-group-item"><input type="hidden" name="codResponsaveis" value="'+codResponsaveis[i]+'" style="display: none;"> '+ listaResponsaveis[i] +'<button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeResponsaveis(\''+codResponsaveis[i]+'\')">clear</button></li>';
            }
          }
      }
      function removeResponsaveis(codResponsavel){
    	  alert("reiureryewur");
          for(i = 0;i < listaResponsaveis.length;i++){
              if(codResponsaveis[i] === codResponsavel){
                  document.getElementById("responsavelOption-"+codResponsavel+"").disabled = "";
                  listaResponsaveis[i] = "";
                  codResponsaveis[i] = "";
                  
              }
          }
          atualizaResponsaveis();
      }

    function habilitaCampoVagas(){
	if(! document.getElementById('isVagasLimitadasInput').checked){
		document.getElementById('vagasRemuneradasInput').disabled = true;
                document.getElementById('vagasVoluntariasInput').disabled = true;
	} else {
		document.getElementById('vagasRemuneradasInput').disabled = false;
                document.getElementById('vagasVoluntariasInput').disabled = false;
	}
    }
    
    function habilitaEdicao(id){
        var input = $("#"+id);
        if(document.getElementById(id).getAttribute('readonly')){
            input.removeAttr('readonly');
        }else{
            input.attr('readonly',true);
        }
    }


      var listaNomeAnexo = [];
      var listaLinkAnexo = [];
      var numAnexo = 0;
      function adicionaAnexo(){
        var nomeAnexo = document.getElementById("nomeAnexoInput").value;
        var linkAnexo = document.getElementById("linkAnexoInput").value;
        if(nomeAnexo !== "" && linkAnexo !== ""){
            listaNomeAnexo[numAnexo] = nomeAnexo;
            listaLinkAnexo[numAnexo] = linkAnexo;
            numAnexo++;
        }
        document.getElementById("nomeAnexoInput").value = "";
        document.getElementById("linkAnexoInput").value = "";
        atualizaAnexo();
      }
      function atualizaAnexo(){
          var list = document.getElementById("listaAnexos");
          list.innerHTML = "";
          for(i = 0;i < listaNomeAnexo.length;i++){
            if(listaNomeAnexo[i] !== ""){
                list.innerHTML += '<li class="list-group-item" ><input type="hidden" name="listaNomeAnexo" value="'+listaNomeAnexo[i]+'" style="display: none;"> <input type="hidden" name="listaLinkAnexo" value="'+listaLinkAnexo[i]+'" style="display: none;"> <a href="'+listaLinkAnexo[i]+'" target="_blank">'+ listaNomeAnexo[i] +'</a><button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeAnexo(\''+listaNomeAnexo[i]+'\')">clear</button></li>';
            }
          }
      }
      function removeAnexo(nome){
          for(i = 0;i < listaNomeAnexo.length;i++){
              if(listaNomeAnexo[i] === nome){
                  listaNomeAnexo[i] = "";
                  listaLinkAnexo[i] = "";
              }
          }
          atualizaAnexo();
      }
      


      var listaNomeAditivo = [];
      var listaLinkAditivo = [];
      var numAditivo = 0;
      function adicionaAditivo(){
        var nomeAditivo = document.getElementById("nomeAditivoInput").value;
        var linkAditivo = document.getElementById("linkAditivoInput").value;
        if(nomeAditivo !== "" && linkAditivo !== ""){
            listaNomeAditivo[numAditivo] = nomeAditivo;
            listaLinkAditivo[numAditivo] = linkAditivo;
            numAditivo++;
        }
        document.getElementById("nomeAditivoInput").value = "";
        document.getElementById("linkAditivoInput").value = "";
        atualizaAditivo();
      }
      function atualizaAditivo(){
          var list = document.getElementById("listaAditivos");
          list.innerHTML = "";
          for(i = 0;i < listaNomeAditivo.length;i++){
            if(listaNomeAditivo[i] !== ""){
                list.innerHTML += '<li class="list-group-item" ><input type="hidden" name="listaNomeAditivo" value="'+listaNomeAditivo[i]+'" style="display: none;"> <input type="hidden" name="listaLinkAditivo" value="'+listaLinkAditivo[i]+'" style="display: none;"> <a href="'+listaLinkAditivo[i]+'" target="_blank">'+ listaNomeAditivo[i] +'</a><button type="button" class="btn btn-light btn-sm material-icons float-right" style="font-size: 15px;" onclick="removeAditivo(\''+listaNomeAditivo[i]+'\')">clear</button></li>';
            }
          }
      }
      function removeAditivo(nome){
          for(i = 0;i < listaNomeAditivo.length;i++){
              if(listaNomeAditivo[i] === nome){
                  listaNomeAditivo[i] = "";
                  listaLinkAditivo[i] = "";
              }
          }
          atualizaAditivo();
      }
      </script>
      
</body>
</html>
