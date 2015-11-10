<%-- 
    Document   : homeAdmin
    Created on : Nov 9, 2015, 2:30:36 PM
    Author     : Sanduni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function loadCompany() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("demo").innerHTML = xhttp.responseText;
                    }
                }
                xhttp.open("GET", "AdminCompany.jsp", true);
                xhttp.send();
            }
        </script>
    </head>
    <%
        String name = null, type = null;

        if (session.getAttribute("username") == null || session.getAttribute("type") != "Admin") {
            request.setAttribute("error", 2);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            name = (String) session.getAttribute("username");
            type = (String) session.getAttribute("type");
        }

        String userName = null;
        String sessionID = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    userName = cookie.getValue();
                }
                if (cookie.getName().equals("JSESSIONID")) {
                    sessionID = cookie.getValue();
                }
            }
        }
    %>

    <body>

        <h3 style="text-align: center;"><%=type%> <%= name%></h3>
        <br><br>
        <div id="demo">
            <div style="text-align: center;">
                <input type="submit" value="Manage Company List" onclick="loadCompany()" />
            </div>
        </div>
    </body>
</html>
