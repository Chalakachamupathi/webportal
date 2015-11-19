/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "addCompany", urlPatterns = {"/addCompany"})
public class addCompany extends HttpServlet {
    
    private static final String jsonFilePath = "C:\\Users\\Chalaka\\Documents\\NetBeansProjects\\webportal\\src\\main\\java\\JSONPack\\jsonFile.json";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        JSONParser jsonParser = new JSONParser();
        
        try {
            
            FileReader fileReader = new FileReader(jsonFilePath);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
            
            JSONArray names = (JSONArray) jsonObject.get("Company");
            
            if (names == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/homaAdmin.jsp");
                request.setAttribute("error", "2");
                rd.include(request, response);
            }
            
            PrintWriter a = response.getWriter();
            
            if (names.contains(request.getParameter("name"))) {
                a.write("3");
                
            } else if (request.getParameter("name") == null || request.getParameter("name").equals("")) {
                a.write("4");
            } else {
                names.add(request.getParameter("name"));
                jsonObject.put("Company", names);
                
                FileWriter jsonFileWriter = new FileWriter(jsonFilePath);
                jsonFileWriter.write(jsonObject.toJSONString());
                jsonFileWriter.flush();
                jsonFileWriter.close();
                
                a.write("1");
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
