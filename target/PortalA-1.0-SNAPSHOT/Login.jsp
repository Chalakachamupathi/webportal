<%-- 
    Document   : Login
    Created on : Nov 9, 2015, 11:07:20 AM
    Author     : Sanduni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <%
        String error = "";

        if (request.getAttribute("error") != null) {
            if (Integer.parseInt(request.getAttribute("error").toString()) == 1) {
                error = "Username or password incorrect.";
            } else if (Integer.parseInt(request.getAttribute("error").toString()) == 2) {
                error = "Please login to proceed.";
            }
        }
    %>
    <script >
        function loadFunc() {
            var error = "<%=error%>";
            document.getElementById("div1").innerHTML = error;
        }
    </script>
    <body onload="loadFunc()">
        <form action="Login" method="POST">
            <h2 style="text-align: center;padding-top: 50px;">Login</h2>
            <div id="div1" style="color: red;text-align: center;">

            </div>
            <table align="center" style="width: 25%;text-align: right;padding-top: 5px;">
                <tr>
                    <td style="height: 60px;">Username</td>
                    <td style="height: 60px;"><input type="text" name="username" value="" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" value="" /></td>
                </tr>
                <tr>
                    <td colspan="2" style="padding-top: 30px;">
                        <input type="submit" value="Login" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
