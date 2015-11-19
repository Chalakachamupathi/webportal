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
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Sanduni
 */
@WebServlet(name = "staffStudentStates", urlPatterns = {"/staffStudentStates"})
public class staffStudentStates extends HttpServlet {

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

        String jsonFilePath = "C:\\Users\\Chalaka\\Documents\\NetBeansProjects\\webportal\\src\\main\\java\\JSONPack\\StaffStudents.json";
        String jsonFilePath2 = "C:\\Users\\Chalaka\\Documents\\NetBeansProjects\\webportal\\src\\main\\java\\JSONPack\\StudentPreferences.json";
        String jsonFilePath3 = "C:\\Users\\Chalaka\\Documents\\NetBeansProjects\\webportal\\src\\main\\java\\JSONPack\\jsonFile.json";
        JSONParser jsonParser = new JSONParser();

        String name = (String) request.getParameter("name");
        System.out.println(name);
        try {
            PrintWriter a = response.getWriter();
            FileReader fileReader = new FileReader(jsonFilePath);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);

            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            if (username != null) {
                JSONArray list = (JSONArray) jsonObject.get(username);

                ListIterator i = list.listIterator();

                FileReader fileReader2 = new FileReader(jsonFilePath2);
                JSONObject jsonObject2 = (JSONObject) jsonParser.parse(fileReader2);
                
                FileReader fileReader3 = new FileReader(jsonFilePath3);
                JSONObject jsonObject3 = (JSONObject) jsonParser.parse(fileReader3);

                JSONArray company = (JSONArray) jsonObject3.get("Company");

                JSONObject stat = (JSONObject) jsonObject2.get("Students");
                JSONObject jo;

                while (i.hasNext()) {
                    int j = i.nextIndex() + 1;
                    String stud = i.next().toString();
                    jo = (JSONObject) stat.get(stud);

                    System.out.println();

                    a.append("<tr>"
                            + "<td>" + j + "</td>"
                            + "<td>" + stud + "</td>"
                            + "<td>" + company.get(Integer.parseInt(jo.get("Preference1").toString())) + "</td>"
                            + "<td>" + getStatus(Integer.parseInt(jo.get("Status1").toString())) + "</td>"
                            + "<td>" + company.get(Integer.parseInt(jo.get("Preference2").toString())) + "</td>"
                            + "<td>" + getStatus(Integer.parseInt(jo.get("Status2").toString())) + "</td>"
                            + "<td>" + company.get(Integer.parseInt(jo.get("Preference3").toString())) + "</td>"
                            + "<td>" + getStatus(Integer.parseInt(jo.get("Status3").toString())) + "</td>"
                            + "<td>" + company.get(Integer.parseInt(jo.get("Preference4").toString())) + "</td>"
                            + "<td>" + getStatus(Integer.parseInt(jo.get("Status4").toString())) + "</td>"
                            + "</tr>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getStatus(int maxStatus) {
        String status;
        if (maxStatus == 3) {
            status = "Selected";
        } else if (maxStatus == 2) {
            status = "Short Listed";
        } else if (maxStatus == 1) {
            status = "CV sent";
        } else if (maxStatus == 0) {
            status = "Pending";
        } else {
            status = "Preferences not selected yet";
        }
        return status;
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
