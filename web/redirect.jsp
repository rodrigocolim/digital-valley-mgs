<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.
--%>
<%@page import="br.ufc.russas.n2s.darwin.beans.SelecaoBeans"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ArrayList<SelecaoBeans> novasSelecoes = new ArrayList<>();
    novasSelecoes.add(new SelecaoBeans(1, "Seleção para Bolsa de Iniciação à Docência - 2017.2", "O Diretor do Campus da UFC em Russas no uso de suas atribuições legais e estatutárias, em consonância com a Resolução N° 08/CEPE, de 26 de abril de 2013, torna público o processo de seleção de monitores de iniciação à docência-PID, seguindo as orientações estabelecidas neste Edital e no Edital n° 35/2016 da Pró-Reitoria de Graduação - PROGRAD.", null, null, null, 1, 0, "asdasasdas", "asdasdasd", null, null, null, null, null, null));
    request.getSession().setAttribute("novasSelecoes", novasSelecoes);
    request.getSession().setAttribute("selecao", new SelecaoBeans(1, "Seleção para Bolsa de Iniciação à Docência - 2017.2", "O Diretor do Campus da UFC em Russas no uso de suas atribuições legais e estatutárias, em consonância com a Resolução N° 08/CEPE, de 26 de abril de 2013, torna público o processo de seleção de monitores de iniciação à docência-PID, seguindo as orientações estabelecidas neste Edital e no Edital n° 35/2016 da Pró-Reitoria de Graduação - PROGRAD.", null, null, null, 1, 0, "asdasasdas", "asdasdasd", null, null, null, null, null, null));
    ArrayList<EtapaBeans> etapas = new ArrayList<>();
    etapas.add(new EtapaBeans(2, "Provas", null, "Querias estares in cahsah, mahs estois aquis con fomis y sonis", null, null, null, null, null, null, null));
    etapas.add(new EtapaBeans(3, "Entrevista", null, "Querias estares in cahsah, mahs estois aquis con fomis", null, null, null, null, null, null, null));
    EtapaBeans inscricao = new EtapaBeans(1, "Inscricao", null, "Querias estares in cahsah, mahs estois aquis con sedis y fomis y sonis", null, null, null, null, null, null, null);
    request.getSession().setAttribute("selecao", new SelecaoBeans(1, "Seleção para Bolsa de Iniciação à Docência - 2017.2", "O Diretor do Campus da UFC em Russas no uso de suas atribuições legais e estatutárias, em consonância com a Resolução N° 08/CEPE, de 26 de abril de 2013, torna público o processo de seleção de monitores de iniciação à docência-PID, seguindo as orientações estabelecidas neste Edital e no Edital n° 35/2016 da Pró-Reitoria de Graduação - PROGRAD.", null, inscricao, etapas, 1, 0, "asdasasdas", "asdasdasd", null, null, null, null, null, null));
%>
<% response.sendRedirect("index.html"); %>
