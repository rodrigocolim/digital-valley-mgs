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
                </nav>

                <h1>Cadastrar Seleção</h1>
                <br>
                <nav class="nav nav-pills" id="myTab" role="tablist">
                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Informações da Seleção</a>
                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Edital e Anexos</a>
                </nav>
                <br>
                
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                        <div class="form-group">
                            <form method="POST" action="cadastrarSelecao">
                                <label for="tituloInput">Titulo*</label>
                                <input type="text" name="titulo" value="${selecao.titulo}" class="form-control" id="tituloInput" aria-describedby="tituloHelp" placeholder="Digite um titulo para a seleção" required>
                                <small id="tituloHelp" class="form-text text-muted">Exemplo: Iniciação à Docência - 2018.1</small>
                                <div class="invalid-feedback">
                                    O titulo da seleção é inválido
                                </div>
                                <br>

                                <label for="descricaoInput">Descrição*</label>
                                <textarea class="form-control" name="descricao" id="descricaoInput" placeholder="Digite uma breve descrição sobre a seleção" required>${selecao.descricao}</textarea>
                                <div class="invalid-feedback">
                                    A descrição da seleção está inválida
                                </div>
                                <br>

                                <label for="categoriaInput">Categoria*</label>
                                <select type="text" name="categoria" value="${selecao.categoria}"class="form-control" id="categoriaInput" required>
                                    <option selected="selected">- Selecione a categoria da seleção -</option>
                                    <option>Assistência Estudantil</option>
                                </select>
                                <br>

                                <label for="preRequisitosInput">Pré Requisitos</label>
                                <textarea name="descricaoPreRequisitos" class="form-control" id="preRequisitosInput" placeholder="Digite uma breve descrição sobre os pré requisitos para participar da seleção">${selecao.descricaoPreRequisitos}</textarea>
                                <br>

                                <label for="areaDeConcentracaoInput">Área de Concentração</label>
                                <input type="text" name="areaDeConcentracao" value="${selecao.areaDeConcentracao}"class="form-control" id="areaDeConcentracaoInput" aria-describedby="tituloHelp" placeholder="Digite o nome da área de concentração" required>
                                <small id="tituloHelp" class="form-text text-muted">Exemplo: Computação, Engenharia Mecânica, LINCE</small>
                                <div class="invalid-feedback">
                                    A área de concentração da seleção é inválida
                                </div>
                                <br>

                                <label for="isVagasLimitadasInput">
                                    <input type="checkbox" class="" id="isVagasLimitadasInput">
                                    Definir o número de vagas
                                </label>
                                <br>

                                <label for="vagasRemuneradasInput">Número de vagas remuneradas</label>
                                <input type="number" name="vagasRemuneradas" class="form-control col-sm-2 disabled" id="descricaoInput" value="0">
                                <div class="invalid-feedback">
                                    O número de vagas remuneradas é inválido
                                </div>
                                <br>
                                
                                <label for="vagasVoluntariasInput">Número de vagas voluntárias</label>
                                <input type="number" name="vagasVoluntarias" class="form-control col-sm-2 disabled" id="descricaoInput" value="0" required>
                                <div class="invalid-feedback">
                                    O número de vagas voluntárias é inválido
                                </div>

                                <button type="button" class="btn btn-secondary" data-toggle="button" aria-pressed="false" autocomplete="off">
                                    Cancelar
                                </button>
                                <input type="submit" value="Salvar e Continuar" class="btn btn-primary">
                            </form>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                        <label class="custom-file">
                            <input type="file" accept="application/pdf" placeholder="Anexe o edital da seleção" id="file" class="custom-file-input">
                            <span class="custom-file-control"> @ </span>
                        </label>

                    </div>
                    <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">...</div>
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
    
    $(document).ready(function() {

        $('input:file').change(function() {
            var arq = this.files[0];
            alert(arq.type);
            if(arq.type === "application/pdf"){
                alert("OK");
            }else{
                alert("Erro");
            }
        });

    });
    </script>
</body>
</html>
