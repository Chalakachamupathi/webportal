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
import java.util.Iterator;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
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
@WebServlet(name = "loadStudent", urlPatterns = {"/loadStudent"})
public class loadStudent extends HttpServlet {

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

        String jsonFilePath = "C:\\Users\\Chalaka\\Documents\\NetBeansProjects\\webportal\\src\\main\\java\\JSONPack\\studentPreferences.json";
        JSONParser jsonParser = new JSONParser();

        String order = request.getParameter("order");
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));

        try {
            PrintWriter a = response.getWriter();
            FileReader fileReader = new FileReader(jsonFilePath);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

            JSONObject students = (JSONObject) jsonObject.get("Students");

            Iterator<String> iterator = students.keySet().iterator();

            JSONObject obj = null;
            String key = null;

            String jsonFilePath2 = "C:\\Users\\Chalaka\\Documents\\NetBeansProjects\\webportal\\src\\main\\java\\JSONPack\\jsonFile.json";
            FileReader fileReader2 = new FileReader(jsonFilePath2);
            JSONObject jsonObject2 = (JSONObject) jsonParser.parse(fileReader2);

            JSONArray names = (JSONArray) jsonObject2.get("Company");

            int pref1, pref2, pref3, pref4, k = 1;
            ArrayList ll = new ArrayList();

            while (iterator.hasNext()) {
                key = (String) iterator.next();

                ll.add(key);
            }

            int start = 0, end = ll.size();

            if (limit != 0) {
                start = page * limit;
                end = start + limit;

                if (end > ll.size()) {
                    end = ll.size();
                }
            }

            if (order.equals("2")) {
                Collections.sort(ll);
            }

            for (int j = start; j < end; j++) {
                obj = (JSONObject) students.get(ll.get(j));
                if (obj != null) {
                    try {
                        pref1 = Integer.parseInt(String.valueOf(obj.get("Preference1")));
                        pref2 = Integer.parseInt(String.valueOf(obj.get("Preference2")));
                        pref3 = Integer.parseInt(String.valueOf(obj.get("Preference3")));
                        pref4 = Integer.parseInt(String.valueOf(obj.get("Preference4")));

                        a.append("<tr>"
                                + "<td>" + (j + 1) + "</td>"
                                + "<td>" + ll.get(j) + "</td>"
                                + "<td>" + names.get(pref1) + "</td>"
                                + "<td>" + names.get(pref2) + "</td>"
                                + "<td>" + names.get(pref3) + "</td>"
                                + "<td>" + names.get(pref4) + "</td>"
                                + "</tr>");

                    } catch (IndexOutOfBoundsException e) {
                        a.append("<tr>"
                                + "<td>" + (j + 1) + "</td>"
                                + "<td>" + ll.get(j) + "</td>"
                                + "<td colspan='4'>A referenced company is deleted from the database</td></tr>");
                    }
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
