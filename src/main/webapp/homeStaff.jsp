<%-- 
    Document   : homeStaff
    Created on : Nov 9, 2015, 2:31:16 PM
    Author     : Sanduni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/menu.css">
        <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
        <script src="JS/script.js"></script>
        <title>Staff Page</title>
        <%
            String name = null, type = null;

            if (session.getAttribute("username") == null || session.getAttribute("type") != "Staff") {
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
        <script type="text/javascript">
            function loadStudents() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("loadingArea").innerHTML = xhttp.responseText;
                        loadStudentStatus();
                    }
                }
                xhttp.open("GET", "StaffStudents.jsp", true);
                xhttp.send();
            }

            function loadStudentStatus() {
                var val = '<%=name%>';
                var xhttp = new XMLHttpRequest();

                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        if (xhttp.responseText === "") {
                            document.getElementById("stdList").innerHTML = "No students assigned."
                        } else {
                            document.getElementById("stdList").innerHTML = xhttp.responseText;
                        }
                    }
                }

                xhttp.open("POST", "staffStudentStates", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("name=" + val);
            }
        </script>
    </head>
    <body onload="loadStudents()">
        <div id='cssmenu'>
            <ul>
                <li class="liclass"><a onclick="loadStudents()" href='#'><span>Student Status</span></a></li>
                <li class="liclass"><a href='#'><span>Manage Curriculum Vitae</span></a></li>
                <li class="liclass"><a href='#'><span>Messaging</span></a></li>
                <li class="liclass"><a href="Logout"><label id="labelhover">Logout</label></a></li>
                <li class="liclass2"><label>You are logged in as <%= name%></label></li>
            </ul>
        </div>
        <div id="loadingArea">

        </div>
    </body>
</html>
