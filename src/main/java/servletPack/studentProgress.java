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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "studentProgress", urlPatterns = {"/studentProgress"})
public class studentProgress extends HttpServlet {

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

        try {
            PrintWriter a = response.getWriter();
            FileReader fileReader = new FileReader(jsonFilePath);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

            JSONObject students = (JSONObject) jsonObject.get("Students");

            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            JSONObject user = (JSONObject) students.get(username);
            if (user != null) {
                String jsonFilePath2 = "C:\\Users\\Chalaka\\Documents\\NetBeansProjects\\webportal\\src\\main\\java\\JSONPack\\jsonFile.json";
                FileReader fileReader2 = new FileReader(jsonFilePath2);
                JSONObject jsonObject2 = (JSONObject) jsonParser.parse(fileReader2);

                JSONArray names = (JSONArray) jsonObject2.get("Company");

                int pref = 0, state = -1;

                for (int i = 1; i < 5; i++) {
                    switch (i) {
                        case 1:
                            pref = Integer.parseInt(user.get("Preference1").toString());
                            state = Integer.parseInt(user.get("Status1").toString());
                            break;
                        case 2:
                            pref = Integer.parseInt(user.get("Preference2").toString());
                            state = Integer.parseInt(user.get("Status2").toString());
                            break;
                        case 3:
                            pref = Integer.parseInt(user.get("Preference3").toString());
                            state = Integer.parseInt(user.get("Status3").toString());
                            break;
                        case 4:
                            pref = Integer.parseInt(user.get("Preference4").toString());
                            state = Integer.parseInt(user.get("Status4").toString());
                            break;
                    }

                    String status = getStatus(state);

                    a.write("<tr>"
                            + "<td>" + i + "</td>"
                            + "<td>" + names.get(pref - 1) + "</td>"
                            + "<td>" + status + "</td>"
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

    private String getStatus(int state) {
        String val = "Preferences not selected";
        switch (state) {
            case 0:
                val = "Pending";
                break;
            case 1:
                val = "Short Listed";
                break;
            case 2:
                val = "CV sent";
                break;
            case 3:
                val = "Selected";
                break;
        }
        return val;
    }

}
