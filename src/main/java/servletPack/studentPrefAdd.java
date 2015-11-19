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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "studentPrefAdd", urlPatterns = {"/studentPrefAdd"})
public class studentPrefAdd extends HttpServlet {

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

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        String jsonFilePath = "C:\\Users\\Chalaka\\Documents\\NetBeansProjects\\webportal\\src\\main\\java\\JSONPack\\StudentPreferences.json";
        JSONParser jsonParser = new JSONParser();
        System.out.println(username);
        try {
            int name1 = Integer.parseInt(request.getParameter("name1"));
            int name2 = Integer.parseInt(request.getParameter("name2"));
            int name3 = Integer.parseInt(request.getParameter("name3"));
            int name4 = Integer.parseInt(request.getParameter("name4"));

            PrintWriter a = response.getWriter();
            FileReader fileReader = new FileReader(jsonFilePath);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

            JSONObject names = (JSONObject) jsonObject.get("Students");

            if (names.containsKey(username)) {
                a.write("1");
            } else if(name1==name2 || name1==name3 || name1==name4 || name3==name2 || name4==name2 || name3==name4 || name1==name4){
                a.write("2");
            }else {
                JSONObject jo = new JSONObject();
                jo.put("Preference1", name1);
                jo.put("Preference2", name2);
                jo.put("Preference3", name3);
                jo.put("Preference4", name4);
                jo.put("Status1", 0);
                jo.put("Status2", 0);
                jo.put("Status3", 0);
                jo.put("Status4", 0);

                names.putIfAbsent(username, jo);

                FileWriter jsonFileWriter = new FileWriter(jsonFilePath);
                jsonFileWriter.write(jsonObject.toJSONString());
                jsonFileWriter.flush();
                jsonFileWriter.close();

                a.write("0");
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
