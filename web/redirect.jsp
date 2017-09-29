<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.
--%>
<%@page import="br.ufc.russas.n2s.darwin.beans.Teste"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ArrayList<Teste> novasSelecoes = new ArrayList<>();
    novasSelecoes.add(new Teste("Aaaaa", "asdjkhasjdk"));
    request.getSession().setAttribute("novasSelecoes", novasSelecoes);

%>
<% response.sendRedirect("index.html"); %>
