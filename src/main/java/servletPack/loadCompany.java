/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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

        String jsonFilePath = "C:\\Users\\Chalaka\\Documents\\NetBeansProjects\\webportal\\src\\main\\java\\JSONPack\\jsonFile.json";
        JSONParser jsonParser = new JSONParser();

        String order = request.getParameter("order");
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));

        try {
            PrintWriter a = response.getWriter();
            FileReader fileReader = new FileReader(jsonFilePath);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

            JSONArray company = (JSONArray) jsonObject.get("Company");
            ListIterator i = company.listIterator();
            ArrayList l = new ArrayList();

            while (i.hasNext()) {
                int j = i.nextIndex() + 1;
                String comp = i.next().toString();
                l.add(j - 1, comp);
            }

            int start = 0, end = l.size();

            if (limit != 0) {
                start = page * limit;
                end = start + limit;

                if (end > l.size()) {
                    end = l.size();
                }
            }

            if (order.equals("2")) {
                Collections.sort(l);

                for (int k = start; k < end; k++) {
                    a.append("<tr>"
                            + "<td>" + (k + 1) + "</td>"
                            + "<td>" + l.get(k) + "</td>"
                            + "<td>"
                            + "<a href=\"#\" onclick=\"updateC('" + l.get(k) + "','" + k + "')\">Update</a>"
                            + "<a style=\"padding-left: 30px;\" href=\"#\" onclick=\"deleteCompany(" + (k + 1) + ")\">Delete</a>"
                            + "</td>"
                            + "</tr>");
                }
            } else {
                for (int k = end-1; k >= start; k--) {
                    a.append("<tr>"
                            + "<td>" + (end - k) + "</td>"
                            + "<td>" + l.get(k) + "</td>"
                            + "<td>"
                            + "<a href=\"#\" onclick=\"updateC('" + l.get(k) + "','" + k + "')\">Update</a>"
                            + "<a style=\"padding-left: 30px;\" href=\"#\" onclick=\"deleteCompany(" + (k + 1) + ")\">Delete</a>"
                            + "</td>"
                            + "</tr>");
                }
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
