<%-- 
    Document   : index
    Created on : 4 oct. 2024, 10:14:57
    Author     : ryrab
--%>
<%@page import="ejb.StationRemote"%>
<%@page import="ejb.PersoClient"%>
<%
    StationRemote stationRemote=PersoClient.lookUp();
    session.setAttribute("stationRemote",stationRemote);
    response.sendRedirect("compteur/formCompteur.jsp");
%>
