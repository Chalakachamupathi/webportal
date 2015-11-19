
<%@page import="java.io.FileNotFoundException"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.ListIterator"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.FileReader"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String name = null, type = null;
    if (session.getAttribute("username") == null || session.getAttribute("type") != "Admin") {
        request.setAttribute("error", 2);
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    } else {
        name = (String) session.getAttribute("username");
        type = (String) session.getAttribute("type");
    }
%>

<h3 style="text-align: center;">Company List</h3>
<br>
<table align="center" style="text-align: center;width: 500px;">
    <tr>
        <td style="text-align: left">Order By :</td>
        <td style="text-align: right">
            <input id="date" type="radio" name="order" value="" onclick="loadCompanyList()" checked="true" />Registered Date
            <input id="alph" type="radio" name="order" value="" onclick="loadCompanyList()" />Alphabetical
        </td>
    </tr>
    <tr>
        <td style="text-align: left">Number of Companies per page :</td>
        <td>
            <select id="limit" name="limit" onchange="dropChange('1')">
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="5">5</option>
                <option selected="true" value="0">All</option>
            </select>
        </td>
    </tr>
</table>
<br>
<table align="center" style="text-align: center;width: 500px;" border="1">
    <thead>
    <th>Index</th>
    <th>Company Name</th>
    <th>Edit/Delete</th>
</thead>
<tbody id="cmpnyList">
</tbody>
</table>
<br><br>
<table align="center" style="width: 500px;text-align: center">
    <tr>
        <td>
            <input id="prev" disabled="true" type="submit" value="Previous" onclick="prev('1')" />
            <input id="idx" disabled="true" type="submit" value="Next" onclick="next('1')" />
        </td>
    </tr>
    <tr>
        <td><br></td>
    </tr>
    <tr>
        <td>
            <div style="text-align: center;">
                Company Name <input id="inputVal" type="text" name="addCompany" value="" />
                <input id="click" type="submit" value="Add" onclick="addCompany()" />
                <input id="updt" type="submit" value="Update" onclick="updateCompany()" disabled="true" />
            </div>  
        </td>
    </tr>
</table>
<div id="test" hidden="true">0</div>
