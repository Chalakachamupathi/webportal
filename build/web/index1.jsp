<%-- 
    Document   : sample_jsp
    Created on : Nov 2, 2015, 11:46:51 PM
    Author     : Sanduni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sample Project</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>
            <form action="sampleServlet" method="post">
                Data <input id="iddata" type="text" name="data" value="" />
                <input type="submit" value="Submit" name="submit" />
            </form>
        </div>
    </body>
</html>