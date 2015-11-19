package servletPack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Sanduni
 */
@WebServlet(urlPatterns = {"/companyPreferences"})
public class companyPreferences extends HttpServlet {

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

        HttpSession session = request.getSession();
        String company = (String) session.getAttribute("username");

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
            int index = names.indexOf(company) + 1;

            ArrayList ll = new ArrayList();

            while (iterator.hasNext()) {
                key = (String) iterator.next();

                ll.add(key);
            }

            int pref1, pref2, pref3, pref4, status = 0, pref = 0;
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

            int k = 1;
            boolean val = false;

            for (int j = start; j < end; j++) {
                val = false;
                
                obj = (JSONObject) students.get(ll.get(j));
                pref1 = Integer.parseInt(String.valueOf(obj.get("Preference1")));
                pref2 = Integer.parseInt(String.valueOf(obj.get("Preference2")));
                pref3 = Integer.parseInt(String.valueOf(obj.get("Preference3")));
                pref4 = Integer.parseInt(String.valueOf(obj.get("Preference4")));

                if (index == pref1) {
                    status = Integer.parseInt(String.valueOf(obj.get("Status1")));
                    pref = 1;
                } else if (index == pref2) {
                    status = Integer.parseInt(String.valueOf(obj.get("Status2")));
                    pref = 2;
                } else if (index == pref3) {
                    status = Integer.parseInt(String.valueOf(obj.get("Status3")));
                    pref = 3;
                } else if (index == pref4) {
                    status = Integer.parseInt(String.valueOf(obj.get("Status4")));
                    pref = 4;
                }
                
                String path="C:\\Users\\chalaka\\Documents\\upload";
 
                File directory = new File(path);
                String[] l = directory.list();
                String cv = null;
                
                for (String l1 : l) {
                    if(l1.equals(ll.get(j)+".pdf")){
                        val = true;
                        cv = l1;
                        break;
                    }
                }

                if ((index == pref1 || index == pref2 || index == pref3 || index == pref4) && val) {
                    a.append("<tr>"
                            + "<td>" + k + "</td>"
                            + "<td>" + ll.get(j) + "</td>"
                            + "<td><a href=\"DownloadFileServlet?cv="+cv+"\" >" + cv + "</a></td>");
                    if (status == 2) {
                        a.append("<td>"
                                + "<input id='status1' onclick=\"changeState('" + ll.get(j) + "', '1', '" + pref + "')\" type='radio' name='" + ll.get(j) + "' value='' />Pending&nbsp&nbsp"
                                + "<input id='status1' onclick=\"changeState('" + ll.get(j) + "', '2', '" + pref + "')\" type='radio' name='" + ll.get(j) + "' value='' checked='true' />Short Listed&nbsp&nbsp"
                                + "<input id='status1' onclick=\"changeState('" + ll.get(j) + "', '3', '" + pref + "')\" type='radio' name='" + ll.get(j) + "' value='' />Selected&nbsp&nbsp"
                                + "</tr>");
                        k++;
                    } else if (status == 3) {
                        a.append("<td>"
                                + "<input id='status1' onclick=\"changeState('" + ll.get(j) + "', '1', '" + pref + "')\"  type='radio' name='" + ll.get(j) + "' value='' />Pending&nbsp&nbsp"
                                + "<input id='status1' onclick=\"changeState('" + ll.get(j) + "', '2', '" + pref + "')\" type='radio' name='" + ll.get(j) + "' value='' />Short Listed&nbsp&nbsp"
                                + "<input id='status1' onclick=\"changeState('" + ll.get(j) + "', '3', '" + pref + "')\" type='radio' name='" + ll.get(j) + "' value='' checked='true' />Selected&nbsp&nbsp"
                                + "</tr>");
                        k++;
                    } else if (status == 1) {
                        a.append("<td>"
                                + "<input id='status1' onclick=\"changeState('" + ll.get(j) + "', '1', '" + pref + "')\" type='radio' name='" + ll.get(j) + "' value='' checked='true' />Pending&nbsp&nbsp"
                                + "<input id='status1' onclick=\"changeState('" + ll.get(j) + "', '2', '" + pref + "')\" type='radio' name='" + ll.get(j) + "' value='' />Short Listed&nbsp&nbsp"
                                + "<input id='status1' onclick=\"changeState('" + ll.get(j) + "', '3', '" + pref + "')\" type='radio' name='" + ll.get(j) + "' value='' />Selected&nbsp&nbsp"
                                + "</tr>");
                        k++;
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
