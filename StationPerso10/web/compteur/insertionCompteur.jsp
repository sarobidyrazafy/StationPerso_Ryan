<%--
  Created by IntelliJ IDEA.
  User: ryrab
  Date: 04/10/2024
  Time: 00:33
  To change this template use File | Settings | File Templates.
--%>
<%@page import="perso.pompe.Registre">
<%
    String compteur=request.getParameter("compteur");
    String idPompe=request.getParameter("idPompe");
    String idPompiste=request.getParameter("idPompiste");
    String date=request.getParameter("date");
    Registre newRegistre=new Registre(compteur,date,idPompiste,idPompe);

%>
