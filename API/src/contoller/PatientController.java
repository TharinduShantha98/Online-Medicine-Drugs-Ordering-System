package contoller;

import Entity.Patient;
import db.DbConnection;
import model.PatientModel;
import model.impl.PatientImpl;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/Patient")
public class PatientController extends HttpServlet {


    PatientModel patientModel = new PatientImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);



    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = req.getParameter("option");
        resp.setContentType("application/json");

        try {
            switch (option){

                case "SEARCH":
                    break;

                case "GETALL":
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    ArrayList<Patient> allPatient = patientModel.getAllPatient();

                    for(int i=0; i < allPatient.size();i++){
                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
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

                    PrintWriter writer = resp.getWriter();

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


}
