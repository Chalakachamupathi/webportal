<%-- 
    Document   : AdminCompany
    Created on : Nov 9, 2015, 4:13:21 PM
    Author     : Sanduni
--%>

<%@page import="java.io.FileNotFoundException"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.util.ListIterator"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.FileReader"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <script type="text/javascript">
            function loadCompany() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("cmpnyList").innerHTML = xhttp.responseText;
                    }
                }
                xhttp.open("GET", "loadCompany", true);
                xhttp.send();
            }

            function addCompany() {
                var val = document.getElementById("inputVal").value;
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "1") {
                            loadCompany();
                        }
                    }
                }
                xhttp.open("POST", "addCompany", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("name=" + val);
            }
            
            var glob = {};
            
            function updateC(val,ind){
                glob.name = ind;
                document.getElementById("updt").disabled = "";
                document.getElementById("click").disabled = "true";
                document.getElementById("inputVal").value = val;
            }
            
            function updateCompany(){
                var ind = glob.name;
                var val = document.getElementById("inputVal").value;
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "1") {
                            document.getElementById("inputVal").value = "";
                            loadCompany();
                        }
                    }
                }
                xhttp.open("POST", "updateCompany", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("name=" + val+"&index="+ind);
            }
            function deleteCompany(val){
                
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "1") {
                            loadCompany();
                        }
                    }
                }
                xhttp.open("POST", "deleteCompany", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("name=" + val);
            }
        </script>
    </head>
    <%
        String name = null, type = null;
        /*if (session.getAttribute("username") == null || session.getAttribute("type") != "Admin") {
         request.setAttribute("error", 2);
         request.getRequestDispatcher("Login.jsp").forward(request, response);
         } else {
         name = (String) session.getAttribute("username");
         type = (String) session.getAttribute("type");            
         }*/
    %>
    <body onload="loadCompany()">
        <h3 style="text-align: center;">Company List</h3>
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
    <table align="center" style="width: 500px;">
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
    <div id="test"></div>
</body>
</html>
