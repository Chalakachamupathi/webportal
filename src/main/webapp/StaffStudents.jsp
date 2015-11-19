
<%@page import="java.io.FileNotFoundException"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.ListIterator"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.FileReader"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <%
        String name = null, type = null;
        if (session.getAttribute("username") == null || session.getAttribute("type") != "Staff") {
         request.setAttribute("error", 2);
         request.getRequestDispatcher("Login.jsp").forward(request, response);
         } else {
         name = (String) session.getAttribute("username");
         type = (String) session.getAttribute("type");            
         }
    %>

        <h3 style="text-align: center;">Students List</h3>
        <br>
        <table align="center" style="text-align: center;width: 1200px;" border="1">
            <thead>
            <th>Index</th>
            <th style="width: 150px;">Student Registration Number</th>
            <th>Preference 1</th>
            <th>Status</th>
            <th>Preference 2</th>
            <th>Status</th>
            <th>Preference 3</th>
            <th>Status</th>
            <th>Preference 4</th>
            <th>Status</th>
        </thead>
        <tbody id="stdList">
        </tbody>
    </table>
    <br><br>
    
    <div id="test"></div>
