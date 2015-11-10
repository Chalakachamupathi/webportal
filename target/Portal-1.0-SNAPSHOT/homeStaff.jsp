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
        <title>JSP Page</title>
    </head>
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
    <body>

        <h3><%=type%> <%= name%></h3>
    </body>
</html>
