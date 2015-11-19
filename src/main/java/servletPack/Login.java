package servletPack;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    private static final String jsonFilePath = "C:\\Users\\Chalaka\\Documents\\NetBeansProjects\\webportal\\src\\main\\java\\JSONPack\\login.json";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            int type = -1;
            JSONArray names;
            ListIterator i;
            PrintWriter a = response.getWriter();

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            JSONObject jo = new JSONObject();
            jo.put("Username", username);
            jo.put("Password", password);

            JSONParser jsonParser = new JSONParser();

            FileReader fileReader = new FileReader(jsonFilePath);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
            names = (JSONArray) jsonObject.get("Student");

            if (names == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
                request.setAttribute("error", 1);
                rd.include(request, response);
            }
            i = names.listIterator();

            while (i.hasNext()) {
                Object un = i.next();
                if (un.equals(jo)) {
                    type = 1;
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("type", "Student");
                    session.setMaxInactiveInterval(30 * 60);
                    Cookie userName = new Cookie("username", username);
                    userName.setMaxAge(30 * 60);
                    response.addCookie(userName);
                    response.sendRedirect("homeStudent.jsp");
                }
            }

            names = (JSONArray) jsonObject.get("Admin");
            i = names.listIterator();

            while (i.hasNext()) {
                Object un = i.next();
                if (un.equals(jo)) {
                    type = 1;
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("type", "Admin");
                    session.setMaxInactiveInterval(30 * 60);
                    Cookie userName = new Cookie("username", username);
                    userName.setMaxAge(30 * 60);
                    response.addCookie(userName);
                    response.sendRedirect("homeAdmin.jsp");
                }
            }

            names = (JSONArray) jsonObject.get("Company");
            i = names.listIterator();

            while (i.hasNext()) {
                Object un = i.next();
                if (un.equals(jo)) {
                    type = 1;
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("type", "Company");
                    session.setMaxInactiveInterval(30 * 60);
                    Cookie userName = new Cookie("username", username);
                    userName.setMaxAge(30 * 60);
                    response.addCookie(userName);
                    response.sendRedirect("homeCompany.jsp");
                }
            }

            names = (JSONArray) jsonObject.get("Staff");
            i = names.listIterator();

            while (i.hasNext()) {
                Object un = i.next();
                if (un.equals(jo)) {
                    type = 1;
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("type", "Staff");
                    session.setMaxInactiveInterval(30 * 60);
                    Cookie userName = new Cookie("username", username);
                    userName.setMaxAge(30 * 60);
                    response.addCookie(userName);
                    response.sendRedirect("homeStaff.jsp");
                }
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.jsp");
            request.setAttribute("error", 1);
            rd.include(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
