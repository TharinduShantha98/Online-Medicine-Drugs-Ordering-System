package contoller;

import Entity.Patient;
import bo.custom.PatientBo;
import bo.custom.impl.PatientBoImpl;

import javax.annotation.Resource;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/Patient")
public class PatientController extends HttpServlet {


    PatientBo patientBo = new PatientBoImpl();

    @Resource(name = "java:comp/env/jdbc/pool")
    public  static  DataSource dataSource;



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            boolean add = patientBo.add(new Patient(req.getParameter("patientId"),
                    req.getParameter("firstName"),
                    req.getParameter("userName"),
                    req.getParameter("secondName"),
                    req.getParameter("idNumber"),
                    req.getParameter("password"),
                    req.getParameter("email"),
                    req.getParameter("address"),
                    req.getParameter("birthday")));

            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            PrintWriter writer = resp.getWriter();
            if(add){
                objectBuilder.add("data", "");
                objectBuilder.add("message", "Successfully added!!");
                objectBuilder.add("status", "200");
                writer.print(objectBuilder.build());
            }else{
                objectBuilder.add("data", "");
                objectBuilder.add("message", "NOT Successfully added!!");
                objectBuilder.add("status", "500");
                writer.print(objectBuilder.build());
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        resp.setContentType("application/json");
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        PrintWriter writer = resp.getWriter();



        try {
            switch (option){

                case "SEARCH":
                    String patientId = req.getParameter("patientId");
                    System.out.println(patientId);

                    Patient search = patientBo.search(patientId);

                    JsonObjectBuilder objectBuilder1 = Json.createObjectBuilder();
                    objectBuilder1.add("patientId",search.getPatientId());
                    objectBuilder1.add("firstName",search.getFirstName());
                    objectBuilder1.add("userName",search.getUserName());
                    objectBuilder1.add("secondName",search.getSecondName());
                    objectBuilder1.add("idNumber",search.getIdNumber());
                    objectBuilder1.add("password",search.getPassword());
                    objectBuilder1.add("email",search.getEmail());
                    objectBuilder1.add("address",search.getAddress());
                    objectBuilder1.add("birthday",search.getBirthday());


                    if(search != null){
                        objectBuilder.add("data",objectBuilder1.build());
                        objectBuilder.add("message", "Search Successfully !!");
                        objectBuilder.add("status", "200");
                        writer.print(objectBuilder.build());

                    }else{
                        objectBuilder.add("data", "");
                        objectBuilder.add("message", "Search Not Successfully !!");
                        objectBuilder.add("status", "500");
                        writer.print(objectBuilder.build());

                    }

                    break;

                case "GETALL":
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    ArrayList<Patient> allPatient = patientBo.getAll();

                    for(int i=0; i < allPatient.size();i++){

                        objectBuilder.add("patientId",allPatient.get(i).getPatientId());
                        objectBuilder.add("firstName",allPatient.get(i).getFirstName());
                        objectBuilder.add("userName",allPatient.get(i).getUserName());
                        objectBuilder.add("secondName",allPatient.get(i).getSecondName());
                        objectBuilder.add("idNumber",allPatient.get(i).getIdNumber());
                        objectBuilder.add("password",allPatient.get(i).getPassword());
                        objectBuilder.add("email",allPatient.get(i).getEmail());
                        objectBuilder.add("address",allPatient.get(i).getAddress());
                        objectBuilder.add("birthday",allPatient.get(i).getBirthday());

                        arrayBuilder.add(objectBuilder.build());


                    }


                    JsonObjectBuilder objectBuilder2  = Json.createObjectBuilder();
                    objectBuilder2.add("data",arrayBuilder.build() );
                    objectBuilder2.add("message", "done");
                    objectBuilder2.add("status", "200");
                    writer.print(objectBuilder2.build());
                    break;

            }

        }catch (Exception e){

            e.printStackTrace();
        }


    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        System.out.println(  jsonObject.getString("firstName"));


        try {
            boolean update = patientBo.update(new Patient(jsonObject.getString("patientId"),
                    jsonObject.getString("firstName"),
                    jsonObject.getString("userName"),
                    jsonObject.getString("secondName"),
                    jsonObject.getString("idNumber"),
                    jsonObject.getString("password"),
                    jsonObject.getString("email"),
                    jsonObject.getString("address"),
                    jsonObject.getString("birthday")));



            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            PrintWriter writer = resp.getWriter();
            if(update){
                objectBuilder.add("data", "");
                objectBuilder.add("message", "Update Successfully !!");
                objectBuilder.add("status", "200");
                writer.print(objectBuilder.build());
            }else{
                objectBuilder.add("data", "");
                objectBuilder.add("message", "update Not Successfully added!!");
                objectBuilder.add("status", "500");
                writer.print(objectBuilder.build());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String patientId = req.getParameter("patientId");
        System.out.println(patientId);
        try {
            boolean delete = patientBo.delete(patientId);

            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            PrintWriter writer = resp.getWriter();
            if(delete){
                objectBuilder.add("data", "");
                objectBuilder.add("message", "Delete Successfully !!");
                objectBuilder.add("status", "200");
                writer.print(objectBuilder.build());

            }else{
                objectBuilder.add("data", "");
                objectBuilder.add("message", "Delete Successfully !!");
                objectBuilder.add("status", "500");
                writer.print(objectBuilder.build());
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }






    }


}
