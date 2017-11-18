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
                        <li class="breadcrumb-item active" aria-current="page">Cadastrar Seleção</li>
                    </ol>
                </nav>

                <h1>Cadastrar Seleção</h1>
                <p>Atenção: Os campos abaixo (*) são de preenchimento obrigatório</p>
                <br>
                
                <div class="form-group">
                  <form method="post" action="cadastrarSelecao"  enctype="multipart/form-data" id="needs-validation" novalidate> 
                        <label for="tituloInput">Titulo*</label>
                        <input type="text" name="titulo" class="form-control" id="tituloInput" aria-describedby="tituloHelp" placeholder="Digite um titulo para a seleção" required>
                        <small id="tituloHelp" class="form-text text-muted">Exemplo: Iniciação à Docência - 2018.1</small>
                        <div class="invalid-feedback">
                            
                        </div>
                        <br>

                        <label for="descricaoInput">Descrição*</label>
                        <textarea class="form-control" name="descricao" id="descricaoInput" placeholder="Digite uma breve descrição sobre a seleção" required></textarea>
                        <div class="invalid-feedback">
                            
                        </div>
                        <br>

                        <label for="preRequisitosInput">Pré Requisitos</label>
                        <textarea name="descricaoPreRequisitos" class="form-control" id="preRequisitosInput" placeholder="Digite uma breve descrição sobre os pré requisitos para participar da seleção">${selecao.descricaoPreRequisitos}</textarea>
                        <br>

                        <label for="categoriaInput">Categoria*</label>
                        <select type="text" name="categoria" class="form-control custom-select" id="categoriaInput" required>
                            <option selected="selected" disabled="disabled">Selecione a categoria da seleção</option>
                            <option>Assistência Estudantil</option>
                            <option>Bolsas para Discentes</option>
                            <option>Cargos de Docente</option>
                            <option>Cargos de Técnicos Admin</option>
                            <option>Professores Substitutos</option>
                        </select>
                        <br>

                        <br>
                        <label for="areaDeConcentracaoInput">Área de Concentração*</label>
                        <input type="text" name="areaDeConcentracao" class="form-control" id="areaDeConcentracaoInput" aria-describedby="tituloHelp" placeholder="Digite o nome da área de concentração" required>
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
                                <input type="number" name="vagasRemuneradas" class="form-control col-sm-2 disabled" id="vagasRemuneradasInput" value="0" min="0" max="100" disabled>
                                <div class="invalid-feedback" >
                                    
                                </div>
                                <br>

                                <label for="vagasVoluntariasInput">Número de vagas voluntárias</label>
                                <input type="number" name="vagasVoluntarias" class="form-control col-sm-2 disabled" id="vagasVoluntariasInput" value="0" min="0" max="100" disabled>
                                <div class="invalid-feedback">
                                    
                                </div>
                            </div>
                        </div>
                        <br>

                        <label for="editalInput">Edital*</label>
                        <input type="file" name="edital" class="form-control" id="editalInput" aria-describedby="editalHelp" placeholder="Anexe o edital da seleção" accept="application/pdf" required>
                        <small id="tituloHelp" class="form-text text-muted">Tipo de arquivo .PDF</small>
                        <div class="invalid-feedback">
                            
                        </div>
                        <br>
                        <a href="/Darwin" type="button" class="btn btn-secondary">
                            Cancelar
                        </a>
                        <input type="submit"  class="btn btn-primary" value="Salvar e Continuar">
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


    function habilitaCampoVagas(){
	if(! document.getElementById('isVagasLimitadasInput').checked){
		document.getElementById('vagasRemuneradasInput').disabled = true;
                document.getElementById('vagasVoluntariasInput').disabled = true;
	} else {
		document.getElementById('vagasRemuneradasInput').disabled = false;
                document.getElementById('vagasVoluntariasInput').disabled = false;
	}
    }

    </script>
    <script type="text/javascript">

    
    var arquivoInput = document.getElementById("arquivoInput");
    var enviar = document.getElementById("enviar");
    enviar.addEventListener("click", function (event) {
      if (arquivoInput.files.length === 0) {
        alert("Nenhum Arquivo Selecionado");
        return;
      }

      if (arquivoInput.files[0].type.indexOf("pdf") !== 0) {
        alert("Este arquivo não é um PDF");
        $(".invalid-feedback").addClass("active");
        return;
      }
    });

    </script>
</body>
</html>
