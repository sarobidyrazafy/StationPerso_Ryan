<%--
  Created by IntelliJ IDEA.
  User: ryrab
  Date: 03/10/2024
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.HashMap"%>
<%@page import="pompe.Registre"%>
<%@page import="pompe.Registre"%>
<%@page import="pompe.Pompe" %>
<%@page import="pompe.PompisteLib" %>
<%@ page import="ejb.StationRemote" %>
<%@ page import="ejb.PersoClient" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    StationRemote station= (StationRemote)session.getAttribute("stationRemote");
    HashMap<String,Object[]> answer=Registre.getPompeAndPompiste(station);
    Pompe[] pompes=(Pompe[])answer.get("pompes");
    PompisteLib[] pompistes=(PompisteLib[])answer.get("pompiste");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        out.println("Pompes");
        for (int i=0;i<pompes.length;i++){
            out.println(pompes[i].getId());
            out.println(" -- ");
        }
        out.println("<br>Pompistes:");
        for (int i=0;i<pompistes.length;i++){
            out.println(pompistes[i].getRefuser());
        }
    %>
</body>
</html>
