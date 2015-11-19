/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servletPack;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DownloadZip extends HttpServlet {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    
      protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        String jsonFilePath = "C:\\Users\\Chalaka\\Documents\\NetBeansProjects\\webportal\\src\\main\\java\\JSONPack\\studentPreferences.json";
        JSONParser jsonParser = new JSONParser();

        HttpSession session = request.getSession();
        String company = (String) session.getAttribute("username");
         List<String> iteams=new ArrayList<>();

        try {
           
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

            int k = 1;
            boolean val = false;

            for (int j = 0; j < ll.size(); j++) {
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

                String path = "C:\\Users\\chalaka\\Documents\\upload";

                File directory = new File(path);
                String[] l = directory.list();
                String cv = null;

                for (String l1 : l) {
                    if (l1.equals(ll.get(j) + ".pdf")) {
                        val = true;
                        cv = l1;
                        break;
                    }
                }

                if ((index == pref1 || index == pref2 || index == pref3 || index == pref4) && val) {
                    //System.out.println(ll.get(j));
                    iteams.add((String)ll.get(j));
                    
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //
            // The path below is the root directory of data to be
            // compressed.
            //
           // String path = getServletContext().getRealPath("data");
                String path="C:\\Users\\chalaka\\Documents\\upload";
 
                File directory = new File(path);
              
            
            String[] files = directory.list();
            String[] needFiles = new String[iteams.size()];
            
            for (int i=0;i<iteams.size();i++) {
                needFiles[i] = iteams.get(i)+".pdf";
                System.out.println(needFiles[i]);
            }
           
 
            //
            // Checks to see if the directory contains some files.
            //
            if (needFiles != null && needFiles.length > 0) {
                
 
                //
                // Call the zipFiles method for creating a zip stream.
                //
              
                     byte[] zip = zipFiles(directory, needFiles);
 
                //
                // Sends the response back to the user / browser. The
                // content for zip file type is "application/zip". We
                // also set the content disposition as attachment for
                // the browser to show a dialog that will let user 
                // choose what action will he do to the sent content.
                //
                ServletOutputStream sos = response.getOutputStream();
                response.setContentType("application/zip");
                response.setHeader("Content-Disposition", "attachment; filename=\"CV.zip\"");
 
                sos.write(zip);
                sos.flush();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
 private byte[] zipFiles(File directory, String[] files) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        byte bytes[] = new byte[2048];
 
        for (String fileName : files) {
            FileInputStream fis = new FileInputStream(directory.getPath() + 
                DownloadZip.FILE_SEPARATOR + fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
 
            zos.putNextEntry(new ZipEntry(fileName));
 
            int bytesRead;
            while ((bytesRead = bis.read(bytes)) != -1) {
                zos.write(bytes, 0, bytesRead);
            }
            zos.closeEntry();
            bis.close();
            fis.close();
        }
        zos.flush();
        baos.flush();
        zos.close();
        baos.close();
 
        return baos.toByteArray();
     }


}
