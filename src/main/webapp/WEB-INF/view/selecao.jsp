<%@page import="br.ufc.russas.n2s.darwin.beans.SelecaoBeans"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="util.Constantes"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="n2s">
        <link rel="icon" href="favicon.ico">
        <title>Darwin - Sistema de Gerenciamento de Seleções</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/design.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/timeline.css" />
        
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
        <style>
        ul {
            display: block;
            list-style-type: disc;
            margin-left: 0;
            margin-right: 0;
            padding-left: 40px;
        }
        </style>
    </head>
    <body>
        <c:import url="elements/menu-superior.jsp" charEncoding="UTF-8"></c:import>
        <c:set var="permissoes" value="${sessionScope.usuarioDarwin.permissoes}"></c:set>
        <div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-right">
            <c:import url="elements/menu-lateral-esquerdo.jsp" charEncoding="UTF-8"></c:import>
                <div class="col-sm-8">
                    <nav class="breadcrumb">
                        <span class="breadcrumb-item">Você está em:</span> 
                        <a class="breadcrumb-item" href="/Darwin/">Início</a>
                        <a class="breadcrumb-item active" href="${selecao.codSelecao}">${selecao.titulo}</a>
                    </nav>
                <c:set var="mensagem" value="${sessionScope.mensagem}"></c:set>
                <c:if test="${not empty mensagem}">
                    <div class="alert alert-${status} alert-dismissible fade show" role="alert">
                        ${mensagem}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <c:set scope="session" var="mensagem" value=""></c:set>
                    <c:set scope="session" var="status" value=""></c:set>
                </c:if>
                <!-- Mensagem de primeiro acesso após o cadastro da seleção -->
                <c:if test="${(empty selecao.inscricao) and (fn:contains(permissoes, 'RESPONSAVEL') or fn:contains(permissoes, 'ADMINISTRADOR'))}">
                    <div class="jumbotron jumbotron-fluid" style="padding-top: 40px; padding-bottom: 30px; ">
                        <div class="container">
                            <h1 style="font-size: 20px; font-weight: bold;">Cadastre a primeira etapa da sua seleção!</h1><br>
                            <p style="font-size: 15px;">Para iniciar sua seleção é necessário o cadastro da etapa de inscrição. Você deseja cadastrar a etapa de inscrição? &nbsp;
                                <a href="/Darwin/cadastrarEtapa/${selecao.codSelecao}"> Cadastrar etapa de inscrição </a>
                            </p>
                        </div>
                    </div>
                </c:if>
                <!-- Mensagem solicitando a divulgação da seleção -->
                <c:if test="${(not empty selecao.inscricao) and ((isResponsavel || fn:contains(permissoes, 'ADMINISTRADOR'))) and (not selecao.divulgada)}">
                    <div class="jumbotron jumbotron-fluid" style="padding-top: 40px; padding-bottom: 30px; ">
                        <div class="container">
                            <h1 style="font-size: 20px; font-weight: bold;">Divulgue sua seleção!</h1><br>
                            <p style="font-size: 15px;">Para permitir que os outros usuários tenham acesso a sua seleção, você precisa divulga-lá. Antes disso, verifique se as configurações da sua seleção estão de acordo com o edital. Você deseja divulgar a seleção?
                                <input type="button" style="font-size: 15px;" class="btn btn-link" value="Divulgar a seleção" data-toggle="modal" data-target="#divulgar" >
                                
                            </p>
                        </div>
                    </div>
                    <!-- Modal divulgar -->
                    <div class="modal fade" id="divulgar" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalLabel">Divulgar seleção</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p>Após divulgar sua seleção todos os outros usuários poderão visualizar e participar dela. Portanto, verifique se todas as configurações da sua seleção estão de acordo com o edital. </p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Cancelar</button>
                                    <a class="btn btn-sm btn-primary" href="/Darwin/editarSelecao/divulga/${selecao.codSelecao}"> Divulgar a seleção</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="row" style="padding-left: 15px;">
                    <h1 class="text-uppercase" style="font-size: 20px;">${selecao.titulo}</h1>
                    <a href="/Darwin/visualizarArquivo?selecao=${selecao.codSelecao}&tipo=edital" target="_blank" class="btn btn-primary btn-sm" style="height: 33px;margin-left: 30px;margin-top: -4px;" >
                        <i class="fas fa-file-pdf"></i><span> Visualizar Edital</span>
                    </a>
                <c:if test="${((isResponsavel) or (fn:contains(permissoes, 'ADMINISTRADOR'))) or (selecao.divulgadoResultado)}">
                    <a href="/Darwin/selecao/${selecao.codSelecao}/resultado" class="btn btn-primary btn-sm" style="height: 33px;margin-left: 30px;margin-top: -4px;">
                        <i class="fas fa-eye"></i> Resultado
                    </a>                    
                </c:if>
                <c:if test="${(isResponsavel) or (fn:contains(permissoes, 'ADMINISTRADOR'))}">
                    
                    <div class="btn-group" role="group">
					    <button id="btnGroupDrop1" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="
   						 height: 33px; margin-left: 30px; margin-top: -4px;"><i class="fas fa-list-ul"></i> Mais opções </button>
					    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1" x-placement="bottom-start" style="position: absolute; transform: translate3d(0px, 38px, 0px); top: 0px; left: 0px; will-change: transform;">
					      <a class="dropdown-item" href="/Darwin/editarSelecao/${selecao.codSelecao}"> <i class="fas fa-edit"></i> Editar seleção                   </a>
					      <a class="dropdown-item" href="/Darwin/selecao/${selecao.codSelecao}/participantes"><i class="fas fa-users"></i> Participantes</a>
					      <a class="dropdown-item" href="/Darwin/resultadoSelecao/${selecao.codSelecao}"><i class="fas fa-cog"></i> Cálculo Resultado</a>
					      <a class="dropdown-item" href="" data-toggle="modal" data-target="#remover"><i class="fas fa-trash-alt"></i> Excluir Selecao</a>
					      
					    </div>
					  </div>
                    
                 </c:if> 
                 <!-- Modal remover-->
                    <div class="modal fade" id="remover" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalLabel">Remover seleção</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p>Tem certeza que deseja excluir esta seleção? Após a confirmação, a ação não poderá ser desfeita! Confirme apenas se tiver absoluta certeza.</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Cancelar</button>
                                    <a class="btn btn-sm btn-primary" href="/Darwin/selecao/${selecao.codSelecao}/remover"> Remover seleção</a>
                                </div>
                            </div>
                        </div>
                    </div>
                
                </div>
                <br>
                <p class="text-justify">
                    ${selecao.descricao}
                </p>
                <p class="text-justify">
                    <hr>
                    <c:if test="${not empty selecao.categoria}">
                    <b>CATEGORIA: </b> ${selecao.categoria}<br/><br/>
                    </c:if>
                    <c:if test="${not empty selecao.areaDeConcentracao}">
                    <b>ÁREA DE CONCENTRAÇÃO: </b> ${selecao.areaDeConcentracao}<br/><br/>
                    </c:if>
                    <c:if test="${not empty selecao.descricaoPreRequisitos}">
                    <b>PRÉ REQUISITOS: </b> ${selecao.descricaoPreRequisitos}<br/><br/>
                    </c:if>
                    <c:if test="${selecao.vagasRemuneradas == 0 and selecao.vagasVoluntarias == 0}">
                    <b>NÚMERO DE VAGAS: </b> Indeterminadas<br/><br/>
                    </c:if>
                    <c:if test="${not (selecao.vagasRemuneradas == 0 and selecao.vagasVoluntarias == 0)}">
                    <b>NÚMERO DE VAGAS: </b> 
                    <ul>
                        <li>REMUNERADAS:  <b>${selecao.vagasRemuneradas}</b></li>
                        <li>VOLUNTÁRIAS:  <b>${selecao.vagasVoluntarias}</b></li>
                    </ul>
                    </c:if>
                    <hr/>
                </p>
                <br/>
                    <c:if test="${(not empty selecao.etapas) or (not empty selecao.inscricao)}">
                        <ul class="timeline">
                            <c:if test="${not empty selecao.inscricao}">
                            <c:set var="estadoInscricao" value="${selecao.inscricao.estado.estado}"></c:set>
                            <li class="${i%2 != 0? 'timeline-inverted': ''}">
                                <div class="timeline-badge ${estadoInscricao == 1 ? 'insert_invitation': estadoInscricao == 2 ? 'warning': estadoInscricao == 3  ? 'success': 'danger'}">
                                    <i class="material-icons">${estadoInscricao == 1 ? 'insert_invitation': estadoInscricao == 2 ? 'timelapse': estadoInscricao == 3  ? 'done_all': 'warning'}</i>
                                </div>
                                <div class="timeline-panel">
                                    <div class="timeline-heading">
                                        <h2 class="timeline-title text-uppercase">${selecao.inscricao.titulo}</h2>
                                        <p>
                                            <small class="text-muted">
                                               <i class="far fa-calendar-alt"></i>                                             
                                                <fmt:parseDate value="${selecao.inscricao.periodo.inicio}" pattern="yyyy-MM-dd" var="parseDataInicio" type="date" />
                                                <fmt:parseDate value="${selecao.inscricao.periodo.termino}" pattern="yyyy-MM-dd" var="parseDataTermino" type="date" />
                                                <fmt:formatDate value="${parseDataInicio}"  pattern="dd/MMMM/yyyy" var="dataInicio" type="date"/>
                                                <fmt:formatDate value="${parseDataTermino}"  pattern="dd/MMMM/yyyy" var="dataTermino" type="date"/>
                                                <b>${fn:replace(dataInicio, "/", " de ")}</b> 
                                                até 
                                                <b>${fn:replace(dataTermino, "/", " de ")}</b>
                                            </small>
                                        </p>
                                    </div>
                                    <div class="timeline-body">
                                        <p>${selecao.inscricao.descricao}</p>
                                        <br>
                                        <c:if test="${not empty selecao.inscricao.documentacaoExigida}">
                                            <b>DOCUMENTAÇÃO EXIGIDA: </b> 
                                            <ul>
                                                <c:forEach var="documento" items="${selecao.inscricao.documentacaoExigida}">
                                                    <li>${documento}</b></li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                        <c:if test="${not empty selecao.inscricao.documentacaoOpcional}">
                                         <br>
                                            <b>DOCUMENTAÇÃO OPCIONAL: </b> 
                                            <ul>
                                                <c:forEach var="documentoOp" items="${selecao.inscricao.documentacaoOpcional}">
                                                    <li>${documentoOp}</b></li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                        <c:if test="${not empty selecao.inscricao.recurso}">
                                            <b>PERÍODO PARA RECURSO: </b> 
                                           		<fmt:parseDate value="${selecao.inscricao.recurso.periodo.inicio}" pattern="yyyy-MM-dd" var="parseDataInicioR" type="date" />
                                                <fmt:parseDate value="${selecao.inscricao.recurso.periodo.termino}" pattern="yyyy-MM-dd" var="parseDataTerminoR" type="date" />
                                                <fmt:formatDate value="${parseDataInicioR}"  pattern="dd/MMMM/yyyy" var="dataInicioRecurso" type="date"/>
                                                <fmt:formatDate value="${parseDataTerminoR}"  pattern="dd/MMMM/yyyy" var="dataTerminoRecurso" type="date"/>
                                                <b>${fn:replace(dataInicioRecurso, "/", " de ")}</b> 
                                                até 
                                                <b>${fn:replace(dataTerminoRecurso, "/", " de ")}</b>
                                        </c:if>
                                                                                
                                        <hr>
                                        <c:if test="${not (isParticipante)}">
	                                        <c:if test="${(estadoInscricao == 2) and (not isResponsavel) and (not fn:contains(permissoes, 'ADMINISTRADOR')) and (not fn:contains(selecao.inscricao.avaliadores, sessionScope.usuarioDarwin))}">
	                                            <a href="/Darwin/participarEtapa/inscricao/${selecao.inscricao.codEtapa}" class="btn btn-primary btn-sm" role="button" aria-pressed="true"><i class="fas fa-user-edit"></i> Inscrever-se</a>
	                                        </c:if>
                                        </c:if>
                                        <c:if test="${(isParticipante)}">
	                                            <button disabled class="btn btn-secondary btn-sm" role="button" aria-pressed="true"><i class="fas fa-user-check"></i> Inscrito</button>
                                        </c:if>
                                        	<jsp:useBean id="now" class="java.util.Date" />
											<fmt:formatDate var="dateAgora" value="${now}" pattern="ddMMyyyy" />
											<fmt:formatDate value="${parseDataInicio}"  pattern="ddMMyyyy" var="Inicio"/>											                                      
                                            <c:if test="${(isResponsavel and (selecao.estado eq 'ESPERA') and ((dateAgora < Inicio) or (not selecao.divulgada))) or (fn:contains(permissoes, 'ADMINISTRADOR') or (isResponsavel)) }">
	                                            <a href="/Darwin/editarEtapa/${selecao.codSelecao}/${selecao.inscricao.codEtapa}" class="btn btn-primary btn-sm" style="height: 30px;">
	                                               <i class="fas fa-edit"></i> Editar etapa
	                                            </a>   
	                                        </c:if>
                                       	
                                        <c:if test="${((estadoInscricao == 2) or (estadoInscricao == 3)) and (fn:contains(permissoes, 'ADMINISTRADOR') or (isResponsavel)) or (fn:contains(selecao.inscricao.avaliadores, sessionScope.usuarioDarwin))}">
                                            <a href="/Darwin/avaliar/inscricao/${selecao.inscricao.codEtapa}" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;">
                                                <i class="fas fa-clipboard-check"></i> Avaliação
                                            </a>
                                        </c:if>
                                       
										<c:if test="${(estadoInscricao == 3) and (!selecao.inscricao.divulgadoResultado) and ((fn:contains(permissoes, 'ADMINISTRADOR')) or (isResponsavel))}">
											<c:set var="pendente" value="false"></c:set>
											<c:forEach var="avaliacao" items="${selecao.inscricao.avaliacoes}">
												<c:if test="${avaliacao.estado eq 'PENDENTE'}">
													<c:set var="pendente" value="true"></c:set>
												</c:if>
											</c:forEach>
											
											<c:if test="${pendente}">
												<a href="" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;" data-toggle="modal" data-target="#divulgaresultados">
													Divulgar Resultado
												</a>
											</c:if>
											<c:if test="${not pendente}">
											<a href="/Darwin/editarEtapa/divulgarResultadoInscricao/${selecao.codSelecao}/${selecao.inscricao.codEtapa}" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;">
												Divulgar Resultado
											</a>
											</c:if>
											<!-- divulgação de resultados -->
						                    <div class="modal fade" id="divulgaresultados" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						                        <div class="modal-dialog" role="document">
						                            <div class="modal-content">
						                                <div class="modal-header">
						                                    <h5 class="modal-title" id="modalLabel">Divulgar resultados</h5>
						                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						                                        <span aria-hidden="true">&times;</span>
						                                    </button>
						                                </div>
						                                <div class="modal-body">
						                                    <p>Esta etapa possui participantes pendentes. Se continuar com a divulgação, estes participantes serão indeferidos automaticamente. Deseja continuar? </p>
						                                </div>
						                                <div class="modal-footer">
						                                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Cancelar</button>
						                                    <a class="btn btn-sm btn-primary" href="/Darwin/editarEtapa/divulgarResultadoInscricao/${selecao.codSelecao}/${selecao.inscricao.codEtapa}"> Continuar</a>
						                                </div>
						                            </div>
						                        </div>
						                    </div>
										</c:if>
                                        <c:if test="${(estadoInscricao == 3) and (selecao.inscricao.divulgadoResultado) and (not empty selecao.inscricao.avaliacoes)}">
	                                        <a href="/Darwin/resultadoEtapa/${selecao.inscricao.codEtapa}" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;">
	                                            <i class="fas fa-eye"></i> Ver Resultado
	                                        </a>
                                        </c:if> 
                                      
                                      
                                    </div>
                                </div>
                            </li>
                          </c:if>
                    <c:set var="i" value="1"></c:set>
                    
                    <c:forEach var="etapa" begin="0" items="${selecao.etapas}">
                        <c:set var="estado" value="${etapa.estado.estado}"></c:set>
                        <li class="${i%2 != 0? 'timeline-inverted': ''}">
                            <div class="timeline-badge ${estado == 1 ? 'insert_invitation': estado == 2 ? 'warning': estado == 3  ? 'success': 'danger'}">
                                <i class="material-icons">${estado == 1 ? 'insert_invitation': estado == 2 ? 'timelapse': estado == 3  ? 'done_all': 'warning'}</i>
                            </div>
                            <div class="timeline-panel">
                                <div class="timeline-heading">
                                    <h2 class="timeline-title text-uppercase">${etapa.titulo}</h2>
                                    <p>
                                        <small class="text-muted">
                                            <i class="far fa-calendar-alt"></i>                                             
                                            <fmt:parseDate value="${etapa.periodo.inicio}" pattern="yyyy-MM-dd" var="parseDataInicio" type="date" />
                                            <fmt:parseDate value="${etapa.periodo.termino}" pattern="yyyy-MM-dd" var="parseDataTermino" type="date" />
                                            <fmt:formatDate value="${parseDataInicio}"  pattern="dd/MMMM/yyyy" var="dataInicio" type="date"/>
                                            <fmt:formatDate value="${parseDataTermino}"  pattern="dd/MMMM/yyyy" var="dataTermino" type="date"/>
                                            <b>${fn:replace(dataInicio, "/", " de ")}</b> 
                                            até 
                                            <b>${fn:replace(dataTermino, "/", " de ")}</b>
                                        </small>
                                    </p>
                                </div>
                                <div class="timeline-body" >
                                	<input name = "etapa_atual" type = "hidden" value = "${etapa.codEtapa}">
                                    <p class="text-justify">${etapa.descricao}</p><br>
                                    <b>ETAPA DE PRÉ-REQUISITO: </b> ${etapa.prerequisito.titulo}<br>
                                    <b>CRITÉRIO DE AVALIAÇÃO: </b> ${etapa.criterioDeAvaliacao}<br>
                                    <c:if test="${not empty etapa.documentacaoExigida}">
                                    <b>DOCUMENTAÇÃO EXIGIDA: </b> 
                                    <ul>
                                        <c:forEach var="documento" items="${etapa.documentacaoExigida}">
                                        <li>${documento}</b></li>
                                        </c:forEach>
                                    </ul>
                                    </c:if>
                                    <c:if test="${not empty etapa.documentacaoOpcional}">
                                         <br>
                                            <b>DOCUMENTAÇÃO OPCIONAL: </b> 
                                            <ul>
                                                <c:forEach var="documentoOp" items="${etapa.documentacaoOpcional}">
                                                    <li>${documentoOp}</b></li>
                                                </c:forEach>
                                            </ul>
                                        </c:if>
                                        <c:if test="${not empty etapa.recurso}">
                                            <b>PERÍODO PARA RECURSO: </b> 
                                           		<fmt:parseDate value="${etapa.recurso.periodo.inicio}" pattern="yyyy-MM-dd" var="parseDataInicio" type="date" />
                                                <fmt:parseDate value="${etapa.recurso.periodo.termino}" pattern="yyyy-MM-dd" var="parseDataTermino" type="date" />
                                                <fmt:formatDate value="${parseDataInicio}"  pattern="dd/MMMM/yyyy" var="dataInicioRecurso" type="date"/>
                                                <fmt:formatDate value="${parseDataTermino}"  pattern="dd/MMMM/yyyy" var="dataTerminoRecurso" type="date"/>
                                                <b>${fn:replace(dataInicioRecurso, "/", " de ")}</b> 
                                                até 
                                                <b>${fn:replace(dataTerminoRecurso, "/", " de ")}</b>
                                        </c:if>
                                    <hr>
                                    <c:if test="${(not empty etapa.documentacaoExigida) and (estado == 2) and (fn:contains(classificados[etapa.codEtapa], sessionScope.usuarioDarwin)) }">
                                        <a href="/Darwin/participarEtapa/${etapa.codEtapa}" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;">
                                            Enviar documentação
                                        </a>
                                    </c:if>
                                 
                                    <c:if test="${((isResponsavel) and ((dateAgora < Inicio) or (not selecao.divulgada))) or (fn:contains(permissoes, 'ADMINISTRADOR'))}">
                                        <a href="/Darwin/editarEtapa/${selecao.codSelecao}/${etapa.codEtapa}" class="btn btn-primary btn-sm" style="height: 30px;">
                                           <i class="fas fa-edit"></i> Editar etapa
                                        </a>   
                                    </c:if>
                                    <c:if test="${(isResponsavel and (selecao.estado eq 'ESPERA')) or (fn:contains(permissoes, 'ADMINISTRADOR')) and (dateAgora < Inicio)  }">
                                        <a href="/Darwin/removerEtapa/${selecao.codSelecao}/${etapa.codEtapa}" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#removerEtapa-${etapa.codEtapa}" style="height: 30px;">
                                           <i class="fas fa-trash-alt"></i> Remover etapa
                                        </a>
                                    </c:if>
                                       <!-- remover etapa -->
					                    <div class="modal fade" id="removerEtapa-${etapa.codEtapa}" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
					                        <div class="modal-dialog" role="document">
					                            <div class="modal-content">
					                                <div class="modal-header">
					                                    <h5 class="modal-title" id="modalLabel">Remover etapa</h5>
					                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					                                        <span aria-hidden="true">&times;</span>
					                                    </button>
					                                </div>
					                                <div class="modal-body">
					                                    <p>Esta etapa será removida. Deseja continuar? </p>
					                                </div>
					                                <div class="modal-footer">
					                                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Cancelar</button>
					                                    <a class="btn btn-sm btn-primary" href="/Darwin/editarEtapa/removerEtapa/${selecao.codSelecao}/${etapa.codEtapa}"> Continuar</a>
					                                </div>
					                            </div>
					                        </div>
					                    </div>
					                   <!-- remover etapa -->
                                    <c:if test="${((estado == 2) or (estado == 3)) and (fn:contains(permissoes, 'ADMINISTRADOR') or (isResponsavel)) or (fn:contains(selecao.inscricao.avaliadores, sessionScope.usuarioDarwin))}">
                                        <a href="/Darwin/avaliar/${etapa.codEtapa}" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;">
                                            <i class="fas fa-clipboard-check"></i> Avaliação
                                        </a>
                                    </c:if>
                                    <c:if test="${(estado == 3) and (!etapa.divulgadoResultado) and ((fn:contains(permissoes, 'ADMINISTRADOR')) or (isResponsavel))}">
											<c:set var="pendente" value="false"></c:set>
											<c:forEach var="avaliacao" items="${etapa.avaliacoes}">
												<c:if test="${avaliacao.estado eq 'PENDENTE'}">
													<c:set var="pendente" value="true"></c:set>
												</c:if>
											</c:forEach>
											<c:if test="${pendente}">
												<a href="" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;" data-toggle="modal" data-target="#divulgaresultadoetapa">
													<i class="fas fa-bullhorn"></i> Divulgar Resultado
												</a>
											</c:if>
											<c:if test="${not pendente}">
												<a href="/Darwin/editarEtapa/divulgarResultadoInscricao/${selecao.codSelecao}/${etapa.codEtapa}" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;">
													<i class="fas fa-bullhorn"></i> Divulgar Resultado
												</a>
											</c:if>
											
											<!-- divulgação de resultados -->
						                    <div class="modal fade" id="divulgaresultadoetapa" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
						                        <div class="modal-dialog" role="document">
						                            <div class="modal-content">
						                                <div class="modal-header">
						                                    <h5 class="modal-title" id="modalLabel">Divulgar resultados</h5>
						                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						                                        <span aria-hidden="true">&times;</span>
						                                    </button>
						                                </div>
						                                <div class="modal-body">
						                                    <p>Esta etapa possui participantes pendentes. Se continuar com a divulgação, estes participantes serão indeferidos automaticamente. Deseja continuar? </p>
						                                </div>
						                                <div class="modal-footer">
						                                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Cancelar</button>
						                                    <a class="btn btn-sm btn-primary" href="/Darwin/editarEtapa/divulgarResultado/${selecao.codSelecao}/${etapa.codEtapa}"> Continuar</a>
						                                </div>
						                            </div>
						                        </div>
						                    </div>
									</c:if>
                                    <c:if test="${(etapa.divulgadoResultado) and ((isResponsavel and (estado == 3)) or (fn:contains(permissoes, 'ADMINISTRADOR') and (estado == 3)) or (fn:contains(permissoes, 'PARTICIPANTE') and (estado == 3)))}">
                                     	<a href="/Darwin/resultadoEtapa/${etapa.codEtapa}" class="btn btn-primary btn-sm active" class="btn btn-primary btn-sm" style="height: 30px;">
                                           <i class="fas fa-eye"></i> Ver Resultado
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                        </li>
                    <c:set var="i" value="${i + 1}"></c:set>
                    </c:forEach>
                    <c:if test="${((not isResponsavel) or (fn:contains(permissoes, 'PARTICIPANTE')))}">  
                        <li class="">
                            <div class="timeline-badge primary">
                                <i class="material-icons">flag</i>
                            </div>
                            <div class="timeline-heading" style="">
                                <br><br><br>
                            </div>
                        </li>                        
                    </c:if>
                    <c:if test="${(isResponsavel and (selecao.estado eq 'ESPERA')) or (fn:contains(permissoes, 'ADMINISTRADOR'))}">  
                        <li>
                            <a href="/Darwin/cadastrarEtapa/${selecao.codSelecao}" class="timeline-badge primary" >
                                <i class="material-icons">add</i>
                            </a>
                        </li>                        
                    </c:if>
                    </ul>
                </c:if>
                <br>
            </div>
            <div class="col-sm-2 sidebar-offcanvas">
                <c:if test="${not empty selecao.aditivos}">
	                <div class="card" >
	                    <div class="card-body">
	                        <h2 style="font-size: 15px; font-weight: bold;" class="text-center">ADITIVOS</h2>
	                    </div>
	                    <ul class="list-group list-group-flush">
	                        <c:forEach var="aditivo" items="${selecao.aditivos}">
	                            <li class="list-group-item disabled">
	                                <fmt:parseDate value="${aditivo.data}" pattern="yyyy-MM-dd" var="parseData" type="date" />
	                                <fmt:formatDate value="${parseData}"  pattern="dd/MM/yyyy" var="dataAditivo" type="date"/>
	                                <a href="/Darwin/visualizarArquivo?selecao=${selecao.codSelecao}&tipo=aditivo&aditivo=${aditivo.codArquivo}" target="_blank">(${dataAditivo}) ${aditivo.titulo}</a>
	                            </li>
	                        </c:forEach>
	                    </ul>
	                </div>
                </c:if>
                <br>
                <c:if test="${not empty selecao.anexos}">
	                <div class="card" >
	                    <div class="card-body">
	                        <h2 style="font-size: 15px; font-weight: bold;" class="text-center">ANEXOS</h2>
	                    </div>
	                    <ul class="list-group list-group-flush">
	                        <c:forEach var="anexo" items="${selecao.anexos}">
	                            <li class="list-group-item disabled">
	                                <fmt:parseDate value="${anexo.data}" pattern="yyyy-MM-dd" var="parseData" type="date" />
	                                <fmt:formatDate value="${parseData}"  pattern="dd/MM/yyyy" var="dataAditivo" type="date"/>
	                                <a href="/Darwin/visualizarArquivo?selecao=${selecao.codSelecao}&tipo=anexo&anexo=${anexo.codArquivo}" target="_blank">(${dataAditivo}) ${anexo.titulo}</a>
	                            </li>
	                        </c:forEach>
	                    </ul>
	                </div>
              	</c:if>
           	 </div>
        </div>
        </div>
        <c:import url="elements/rodape.jsp" charEncoding="UTF-8"></c:import>  
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>      
        <script>
            $("#navEtapas").addClass(function( index, currentClass ) {
                var addedClass;

                if (screen.width <= 575) {
                  addedClass = "flex-column";
                }

                return addedClass;
            });
            $("#timeline").removeClass(function( index, currentClass ) {
                var addedClass;

                if (screen.width <= 575) {
                  addedClass = "timeline";
                }

                return addedClass;
            });
        </script>
    </body>
</html>