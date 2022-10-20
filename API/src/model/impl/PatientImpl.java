package model.impl;

import Entity.Patient;
import db.DbConnection;
import model.PatientModel;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientImpl implements PatientModel {


//    @Resource(name = "java:comp/env/jdbc/pool")
//    DataSource dataSource;


    @Override
    public ArrayList<Patient> getAllPatient() throws SQLException, ClassNotFoundException {

        ArrayList<Patient> patientArrayList = new ArrayList<>();




        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Patient");
        ResultSet resultSet = preparedStatement.executeQuery();


        while (resultSet.next()) {

            String patientId = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String userName = resultSet.getString(3);
            String secondName = resultSet.getString(4);
            String idNumber = resultSet.getString(5);
            String password = resultSet.getString(6);
            String email = resultSet.getString(7);
            String address = resultSet.getString(8);
            String birthday = resultSet.getString(8);


            patientArrayList.add(new Entity.Patient(patientId,firstName,userName,secondName,idNumber,password,email,address,birthday));


        }
        connection.close();
        return patientArrayList;


    }
}
