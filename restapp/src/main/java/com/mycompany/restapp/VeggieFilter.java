/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restapp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC-EKY
 */
public class VeggieFilter extends HttpServlet {

    private Set<String> rootVegetables;
    private Set<String> bulbAndStemVegetables;
    private Set<String> allVegetables;
    private Map<String, Set<String>> filters;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        //Root vegetables
        rootVegetables = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        rootVegetables.addAll(Arrays.asList("Beetroot", "Carrot", "Ginger root",
                "Parsnip", "Potato", "Radish", "Turnip", "Yam"));

        //Bulb and stem vegetables
        bulbAndStemVegetables = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        bulbAndStemVegetables.addAll(Arrays.asList("Asparagus", "Celery",
                "Garlic", "Lemongrass", "Leek", "Onion"));

        //All vegetables
        allVegetables = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        allVegetables.addAll(rootVegetables);
        allVegetables.addAll(bulbAndStemVegetables);
        allVegetables.addAll(Arrays.asList("Cucumber", "Carrot", "Potato",
                "Onion", "Celery", "Yam", "Lettuce", "Radish", "Leek", "Beet",
                "Pea", "Peanut", "Soybean", "Lentil"));

        //Set up filter map
        filters = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        filters.put("root", rootVegetables);
        filters.put("bulb", bulbAndStemVegetables);
        filters.put("all", allVegetables);

        gson = new Gson();
    }

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VeggieFilter</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VeggieFilter at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");

            //1: Take a "text/plain" Content-Type body (the header not required, but if included, needs to be text/plain)
            //TODO 2: Respond with JSON format text?
            //TODO 3: The Response header should also state that the response type is JSON
            //TODO 4: status codes
            //TODO 5: Have a method that returns the names used for each available filter.
            //Check 'Content-Type' header
            String contentType = request.getContentType();
            if (contentType != null && contentType.contains("text/plain") == false) {
                //TODO: Abort mission!
            }

            Scanner scanner = new Scanner(request.getInputStream(), request.getCharacterEncoding());

            String s;
            while (scanner.hasNext()) {
                s = scanner.next();

                //clean up the end of tokens
                while (Character.isLetter(s.charAt(s.length() - 1)) == false) {
                    s = s.substring(0, s.length() - 1);
                }

                //check if word is filtered
                if (allVegetables.contains(s)) {
                    //TODO: Return result for message "violating" the filter.
                } else {
                    //TODO: Return whatever result for the message "passing" the filter.
                }
            }

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
        //Respond with the filter categories in JSON format
        JsonObject filtersAsJSON = new JsonObject();
        filtersAsJSON.add("filters", this.gson.toJsonTree(filters));

        //Set headers
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(filtersAsJSON);
        out.flush();
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

        boolean censored = false;
        String filterName;
        //1: Select filter
        Set<String> selectedFilter;
        String filterArg = request.getParameter("filter");
        if (filterArg == null || filters.keySet().contains(filterArg) == false) {
            selectedFilter = filters.get("all");
            filterName = "all";
        } else {
            selectedFilter = filters.get(filterArg);
            filterName = filterArg.toLowerCase();
        }

        //2: Check the "Content-Type"-header, and respond accordingly if Content-Type isn't either "text/plain" or missing
        String contentType = request.getContentType();
        if (contentType != null && contentType.contains("text/plain") == false) {
            response.setStatus(response.SC_UNSUPPORTED_MEDIA_TYPE);
            return;
        }

        //3: Read and censor the message body
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), request.getCharacterEncoding()));
        StringBuilder filteredMessage = new StringBuilder();
        String tempLine;
        //censor message
        while (br.ready()) {
            tempLine = br.readLine();
            for (String vegetable : selectedFilter) {
                if (tempLine.contains(vegetable)) {
                    tempLine = tempLine.replaceAll(vegetable, "****");
                    censored = true;
                }
            }

            filteredMessage.append(tempLine);
        }

        //4: Build JSON response and set Content-Type of the response header
        response.setContentType("application/json");
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("filter", filterName);
        jsonObj.addProperty("censored", censored);
        jsonObj.addProperty("filteredMessage", filteredMessage.toString());

        //5: Send the JSON object
        PrintWriter out = response.getWriter();
        out.print(jsonObj);
        out.flush();
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
