/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "loadCompany", urlPatterns = {"/loadCompany"})
public class loadCompany extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String jsonFilePath = "C:\\Users\\Sanduni\\Documents\\NetBeansProjects\\PortalA\\src\\main\\java\\JSONPack\\jsonFile.json";
        JSONParser jsonParser = new JSONParser();

        try {
            PrintWriter a = response.getWriter();
            FileReader fileReader = new FileReader(jsonFilePath);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

            JSONArray company = (JSONArray) jsonObject.get("Company");
            ListIterator i = company.listIterator();
//<tr>
//                <td><%=i.nextIndex()%></td>
//                <td><%=i.next()%></td>
//                <td>
//                    <a href="#">Update</a>
//                    <a style="padding-left: 30px;" href="#">Delete</a>
//                </td>
//            </tr>
            while (i.hasNext()) {
                int j = i.nextIndex()+1;
                String comp = i.next().toString();
                a.append("<tr>"
                        + "<td>"+ j +"</td>"
                        + "<td>"+comp+"</td>"
                        + "<td>"
                        + "<a href=\"#\" onclick=\"updateC('"+ comp +"','"+ (j-1) +"')\">Update</a>"
                        + "<a style=\"padding-left: 30px;\" href=\"#\" onclick=\"deleteCompany("+j+")\">Delete</a>"
                        + "</td>"
                        + "</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
