
<%@page import="java.io.FileNotFoundException"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.ListIterator"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.FileReader"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String name = null, type = null;
    if (session.getAttribute("username") == null || session.getAttribute("type") != "Company") {
        request.setAttribute("error", 2);
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    } else {
        name = (String) session.getAttribute("username");
        type = (String) session.getAttribute("type");
    }
%>

<h3 style="text-align: center;">Students List</h3>
<br>
<table align="center" style="text-align: center;width: 700px;">
    <tr>
        <td colspan="2">
            <div id="msg"></div>
        </td>
    </tr>
    <tr>
        <td style="text-align: left">Order By :</td>
        <td style="text-align: right">
            <input id="date" type="radio" name="order" value="" onclick="loadStudentPreferencesList()" checked="true" />Received Date
            <input id="alph" type="radio" name="order" value="" onclick="loadStudentPreferencesList()" />Alphabetical
        </td>
    </tr>
    <tr>
        <td style="text-align: left">Number of Students per page :</td>
        <td style="text-align: center">
            <select id="limit" name="limit" onchange="dropChange()">
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="5">5</option>
                <option selected="true" value="0">All</option>
            </select>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: right;">
            <a href="#" onclick="downloadZip()">Download all as Zip</a>
        </td>
    </tr>
    
</table>
<br>
<table align="center" style="text-align: center;width: 700px;" border="1">
    <thead>
    <th>Index</th>
    <th>Student Name</th>
    <th>Curriculum Vitae</th>
    <th>Status</th>
</thead>
<tbody id="cmpnyList">
</tbody>
</table>
<br><br>
<table align="center" style="width: 500px;text-align: center">
    <tr>
        <td>
            <input id="prev" disabled="true" type="submit" value="Previous" onclick="prev()" />
            <input id="idx" disabled="true" type="submit" value="Next" onclick="next()" />
        </td>
    </tr>
</table>
<div id="test" hidden="true">0</div>
