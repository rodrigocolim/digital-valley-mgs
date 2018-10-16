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
         <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		  <script>
			  $( function() {
			    $( "#sortable" ).sortable();
			    $( "#sortable" ).disableSelection();
			  } );
		  </script>
    </head>
    <body>
        <c:import url="elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
       <div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-right">
                <c:import url="elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
                <div class="col-sm-8">
                    <nav class="breadcrumb">
                        <span class="breadcrumb-item">Você está em:</span> 
                        <a class="breadcrumb-item" href="/Darwin">Início</a>
                        <a class="breadcrumb-item" href="/Darwin/selecao/${selecao.codSelecao}">${selecao.titulo}</a>
                        <a class="breadcrumb-item active" href="#">Resultado</a>
                    </nav>
	                <c:if test="${not empty mensagem}">
	                    <div class="alert alert-${status} alert-dismissible fade show" role="alert">
	                        ${mensagem}
	                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	                            <span aria-hidden="true">&times;</span>
	                        </button>
	                    </div>
	                </c:if>
               
                    <h1>Cálculo do Resultado</h1>
                    <p>*A seguir são apresentadas apenas as etapas que foram definidas com nota no metodo de avaliação no momento do cadastro.</p>
                
                   <br>
                    <div>
                
                         <form method="POST" action="/Darwin/resultadoSelecao/salvar/${selecao.codSelecao}" modelAttribute="resultadoSelecaoForm">
	                        <table class="table table-bordered">
								<tr>
									<th>No. </th>
									<th>Titulo da Etapa</th>
									<th>Peso</th>
									<th>Prioridade</th>
									<th>Utilização Para Desempate</th>
								</tr>
								<c:forEach items="${resultadoSelecaoForm.etapas}" var="etapas" varStatus="status">
									<input type="hidden" name="etapas[${status.index}].codEtapa" value="${etapas.codEtapa}" />
									<tr>
										<td align="center">${status.count}</td>
										<td>${etapas.titulo}</td> 
										<td><input class="form-control" name="etapas[${status.index}].pesoNota" type="number" min="0" max="10" value="${etapas.pesoNota}"/></td>
										<td><input class="form-control" name="etapas[${status.index}].posicaoCriterioDesempate" type="number" min="0" value="${etapas.posicaoCriterioDesempate}"/></td>
										<td><input class="form-control" name="etapas[${status.index}].criterioDesempate" type="checkbox" ${etapas.criterioDesempate eq true ? 'checked' : '' } /></td>
									</tr>
								</c:forEach>
							</table>	
							<br/>
							<input class="btn btn-primary float-right" type="submit" value="Salvar" />
	                        
                         </form>
                   
					</div>
					</div>
					
        <script>
			
			  $(function() {
				  var anterior = 0;
				    $( "#sortable" ).sortable({
				        update: function(event, ui) { 
				            console.log('update: '+ui.item.index())
				        },
				        start: function(event, ui) { 
				            console.log('start: ' + ui.item.index())
				            anterior = ui.item.index();
				        }
				    });
				    $( "#sortable" ).disableSelection();
				});
		  </script>
 		</div>
 		</div>
 		</div>
 		</div>
        <c:import url="elements/rodape.jsp" charEncoding="UTF-8"></c:import>  
        <!--  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
       --> <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/resources/js/script.js" ></script>
        <script src="https://raw.githubusercontent.com/furf/jquery-ui-touch-punch/master/jquery.ui.touch-punch.min.js" ></script>
    </body>
</html>